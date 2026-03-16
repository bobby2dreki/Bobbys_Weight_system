package bobby2dreki.equipment_weight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.scoreboard.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquipmentWeight implements ModInitializer {

	public static final String MOD_ID = "equipment_weight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static MinecraftServer SERVER;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Equipment Weight...");

		// Load config
		Config.HANDLER.load();

		// Sync when server starts
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			SERVER = server;
			syncConfigToScoreboard(server);
		});

		// Sync when datapacks reload (/reload)
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resources, success) -> {
			if (success) {
				syncConfigToScoreboard(server);
			}
		});

		LOGGER.info("Equipment Weight ready – config will sync to scoreboard");
	}

	public static void syncConfigToScoreboard(MinecraftServer server) {

		Config config = Config.HANDLER.instance();
		Scoreboard scoreboard = server.getScoreboard();

		String fakePlayer = "#global";

		syncScore(scoreboard, "strength", config.globalStrength, "Strength", fakePlayer);
		syncScore(scoreboard, "training", config.training ? 1 : 0, "Training", fakePlayer);
		syncScore(scoreboard, "training_threshold", config.trainingThreshold, "Training Threshold", fakePlayer);
		syncScore(scoreboard, "exhaustion", config.exhaustion ? 1 : 0, "Exhaustion", fakePlayer);
		syncScore(scoreboard, "training_bar", config.trainingBar, "Training Bar Style", fakePlayer);
		syncScore(scoreboard, "fatigue_threshold", config.fatigueThreshold, "Fatigue Threshold", fakePlayer);
	}

	private static void syncScore(
			Scoreboard scoreboard,
			String objectiveName,
			int newValue,
			String displayName,
			String fakePlayer
	) {

		ScoreboardObjective objective = scoreboard.getNullableObjective(objectiveName);

		if (objective == null) {
			objective = scoreboard.addObjective(
					objectiveName,
					ScoreboardCriterion.DUMMY,
					Text.literal(displayName),
					ScoreboardCriterion.RenderType.INTEGER,
					false,
					null
			);
		}

		ScoreHolder holder = ScoreHolder.fromName(fakePlayer);
		ScoreAccess score = scoreboard.getOrCreateScore(holder, objective);

		if (score.getScore() != newValue) {
			score.setScore(newValue);
			LOGGER.info("Synced config → scoreboard: {} {} = {}", fakePlayer, objectiveName, newValue);
		}
	}
}