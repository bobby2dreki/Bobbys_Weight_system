package bobby2dreki.equipment_weight;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class ClientConfig {

    public static final ConfigClassHandler<ClientConfig> HANDLER =
            ConfigClassHandler.createBuilder(ClientConfig.class)
                    .id(Identifier.of("equipment_weight", "client_config"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(FabricLoader.getInstance()
                                    .getConfigDir()
                                    .resolve("equipment_weight_client.json"))
                            .setJson5(true)
                            .build())
                    .build();

    @SerialEntry(comment = "Personal training bar style override. 0 = use server default")
    public int personalTrainingBar = 0;
}