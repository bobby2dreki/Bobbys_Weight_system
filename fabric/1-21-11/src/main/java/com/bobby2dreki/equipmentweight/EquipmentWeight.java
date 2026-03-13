package com.bobby2dreki.equipmentweight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.scoreboard.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquipmentWeight implements ModInitializer {
	public static final String MOD_ID = "equipment-weight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static MinecraftServer SERVER;
	public static Config CONFIG;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing RPG Equipment Weight...");

		// Load config
		Config.HANDLER.load();
		CONFIG = Config.HANDLER.instance();


		// Sync config value to scoreboard:
		// - When server starts
		ServerLifecycleEvents.SERVER_STARTED.register(server -> SERVER = server);
		ServerLifecycleEvents.SERVER_STARTED.register(EquipmentWeight::syncStrengthToScoreboard);
		// - And every time datapacks reload (/reload)
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resources, success) -> {
			if (success) {
				syncStrengthToScoreboard(server);
			}
		});

		LOGGER.info("Equipment Weight ready – global strength will sync from config");
	}

	public static void syncStrengthToScoreboard(MinecraftServer server) {
		Scoreboard scoreboard = server.getScoreboard();
		String objectiveName = "strength";
		String fakePlayerName = "#global";

		ScoreboardObjective objective = scoreboard.getNullableObjective(objectiveName);
		if (objective == null) {
			LOGGER.debug("Objective '{}' missing, creating fallback", objectiveName);
			objective = scoreboard.addObjective(
					objectiveName,
					ScoreboardCriterion.DUMMY,
					Text.literal("Strength"),
					ScoreboardCriterion.RenderType.INTEGER,
					false,
					null
			);
		}

		int newValue = CONFIG.globalStrength;

		// getOrCreateScore(String name, ScoreboardObjective) is fine for fake players
		ScoreHolder holder = ScoreHolder.fromName(fakePlayerName);
		ScoreAccess score = scoreboard.getOrCreateScore(holder, objective);

		int current = score.getScore();
		if (current != newValue) {
			score.setScore(newValue);
			LOGGER.info("Synced config → scoreboard: {} strength = {}", fakePlayerName, newValue);
		}
	}
}