package com.bobby2dreki.equipmentweight;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {

    public static Screen create(Screen parent) {

        Config config = Config.HANDLER.instance();

        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Equipment Weight"))

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("General"))

                        .option(Option.<Integer>createBuilder()
                                .name(Text.literal("Global Strength"))
                                .binding(
                                        26,
                                        () -> config.globalStrength,
                                        val -> config.globalStrength = val
                                )
                                .controller(IntegerFieldControllerBuilder::create)
                                .build())

                        .build())

                .save(() -> {
                    Config.HANDLER.save();

                    if (EquipmentWeight.SERVER != null) {
                        EquipmentWeight.syncStrengthToScoreboard(EquipmentWeight.SERVER);
                    }
                })

                .build()
                .generateScreen(parent);
    }
}