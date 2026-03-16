package bobby2dreki.equipment_weight;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class Config {

    public static final ConfigClassHandler<Config> HANDLER =
            ConfigClassHandler.createBuilder(Config.class)
                    .id(Identifier.of("equipment_weight", "config"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(FabricLoader.getInstance()
                                    .getConfigDir()
                                    .resolve("equipment_weight.json"))
                            .setJson5(true)
                            .build())
                    .build();

    @SerialEntry(comment = "Enable the training system. Players become stronger by carrying heavy equipment.")
    public boolean training = true;

    @SerialEntry(comment = "Enable exhaustion when carrying equipment heavier than your strength and just by being awake.")
    public boolean exhaustion = true;

    @SerialEntry(comment = "Starting player strength. Higher values allow carrying heavier equipment without debuffs.")
    public int globalStrength = 10;

    @SerialEntry(comment = "Controls how fast players gain strength. Higher values make training slower. (20 = double the time, 5 = half the time")
    public int trainingThreshold = 10;

    @SerialEntry(comment = "Training bar style.\n0 = disabled\n1–5 = different looks\nPlayers can override this with: /scoreboard players set @s training_bar 0-5")
    public int trainingBar = 1;

    @SerialEntry(comment = "How fast your fatigue increases. Increasing this delays fatigue.")
    public int fatigueThreshold = 3000;
}