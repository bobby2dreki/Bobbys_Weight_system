package bobby2dreki.equipment_weight;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

public class ConfigScreen {

    public static Screen create(Screen parent) {

        Config config = Config.HANDLER.instance();

        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Equipment Weight"))

                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("⚙ General"))
                        .option(LabelOption.create(
                                Text.literal("""
                Equipment Weight adds a progression system where players become stronger by carrying heavy equipment.

                Configure the system below.
                """)
                        ))
                        .option(LabelOption.create(
                                Text.literal("""
                ⚠ If Training is disabled

                Recommended Global Strength: 26
                This allows a full iron loadout with no debuff.
                """)
                        ))
                        .option(Option.<Integer>createBuilder()
                                .name(Text.literal("Global Strength"))
                                .description(OptionDescription.of(
                                        Text.literal("""
                                                Default strength value applied to players.
                                                Higher values allow carrying heavier equipment without debuffs.
                                                Example: 26 allows a full iron loadout with no slowdown.""")
                                ))
                                .binding(
                                        10,
                                        () -> config.globalStrength,
                                        val -> config.globalStrength = val
                                )
                                .controller(IntegerFieldControllerBuilder::create)
                                .build())

                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Enable Training"))
                                .description(OptionDescription.of(
                                        Text.literal("""
                                                When enabled, players slowly become stronger by carrying equipment heavier than they can handle.
                                                Disable this if you want a static strength system.""")
                                ))
                                .binding(
                                        true,
                                        () -> config.training,
                                        val -> config.training = val
                                )
                                .controller(BooleanControllerBuilder::create)
                                .build())

                        .option(Option.<Integer>createBuilder()
                                .name(Text.literal("Training Threshold"))
                                .description(OptionDescription.of(
                                        Text.literal("""
                                                Controls how quickly players gain strength.
                                                Default: 10
                                                Higher values make training slower.
                                                Example:
                                                20 = training takes twice as long.
                                                5 = training goes twice as fast""")
                                ))
                                .binding(
                                        10,
                                        () -> config.trainingThreshold,
                                        val -> config.trainingThreshold = val
                                )
                                .controller(IntegerFieldControllerBuilder::create)
                                .build())
                        .option(ButtonOption.createBuilder()
                                .name(Text.literal("Reset Your Strength"))
                                .description(OptionDescription.of(Text.literal("""
                Resets your strength progression to 0.

                Equivalent command:
                /scoreboard players set @s strength 0

                Useful if you changed the default strength or want to restart your training arc.
                """)))
                                .action((screen, button) -> {
                                    var client = net.minecraft.client.MinecraftClient.getInstance();

                                    if (client.player != null) {
                                        client.player.networkHandler.sendChatCommand(
                                                "scoreboard players set @s strength 0"
                                        );
                                    }
                                })
                                .build())
                        .build())
                // LINKS TAB
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("🖇 Links"))
                        .option(ButtonOption.createBuilder()
                                .name(Text.literal("Open Modrinth Page"))
                                .action((screen, button) -> {
                                    net.minecraft.client.MinecraftClient.getInstance().setScreen(null);
                                    Util.getOperatingSystem().open("https://modrinth.com/datapack/bobbys-weight-system");
                                })
                                .build())
                        .option(ButtonOption.createBuilder()
                                .name(Text.literal("Join Discord"))
                                .action((screen, button) -> {
                                    net.minecraft.client.MinecraftClient.getInstance().setScreen(null);
                                    Util.getOperatingSystem().open("https://discord.gg/4eCpfzbNSN");
                                })
                                .build())
                        .build())
                .save(() -> {
                    Config.HANDLER.save();

                    if (EquipmentWeight.SERVER != null) {
                        EquipmentWeight.syncConfigToScoreboard(EquipmentWeight.SERVER);
                    }
                })

                .build()
                .generateScreen(parent);
    }
}