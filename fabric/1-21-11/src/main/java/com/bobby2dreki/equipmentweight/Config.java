package com.bobby2dreki.equipmentweight;

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
                            .build())
                    .build();

    @SerialEntry
    public int globalStrength = 26;

}