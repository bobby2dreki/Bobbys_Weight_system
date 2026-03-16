package bobby2dreki.equipment_weight;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

public class ConfigScreen {

    public static Screen create(Screen parent) {

        Config config = Config.HANDLER.instance();
        ClientConfig clientConfig = ClientConfig.HANDLER.instance();

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
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Enable Exhaustion"))
                                .description(OptionDescription.of(Text.literal("""
                Enables the exhaustion mechanic.

                Carrying equipment that is too heavy and staying awake for long will gradually exhaust the player.
                """)))
                                .binding(
                                        true,
                                        () -> config.exhaustion,
                                        val -> config.exhaustion = val
                                )
                                .controller(BooleanControllerBuilder::create)
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
                // TRAINING SETTINGS
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("⚓ Settings"))
                        .option(Option.<TrainingBarStyle>createBuilder()
                                .name(Text.literal("Server Training Bar Style"))
                                .description(OptionDescription.of(Text.literal("""
                Changes how the training progress bar looks, server default.

                Disabled
                Detailed
                Classic
                Bar
                Compact
                Minimal
                """)))
                                .binding(
                                        TrainingBarStyle.fromValue(config.trainingBar),
                                        () -> TrainingBarStyle.fromValue(config.trainingBar),
                                        val -> config.trainingBar = val.value
                                )
                                .controller(opt -> EnumControllerBuilder.create(opt)
                                        .enumClass(TrainingBarStyle.class))
                                .build())
                        .option(Option.<TrainingBarStyle>createBuilder()
                                .name(Text.literal("Personal Training Bar Style"))
                                .description(OptionDescription.of(Text.literal("""
        Overrides the server training bar style.

        DISABLED = SERVER DEFAULT
        DETAILED
        CLASSIC
        BAR
        COMPACT
        MINIMAL
        """)))
                                .binding(
                                        TrainingBarStyle.fromValue(clientConfig.personalTrainingBar),
                                        () -> TrainingBarStyle.fromValue(clientConfig.personalTrainingBar),
                                        val -> clientConfig.personalTrainingBar = val.value
                                )
                                .controller(opt -> EnumControllerBuilder.create(opt)
                                        .enumClass(TrainingBarStyle.class))
                                .build())
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
                        .option(Option.<Integer>createBuilder()
                                .name(Text.literal("Fatigue Threshold"))
                                .description(OptionDescription.of(Text.literal("""
                Determines when exhaustion begins.

                Default: 3000

                Increasing this allows players to
                carry heavy equipment longer before
                exhaustion effects start.
                """)))
                                .binding(
                                        3000,
                                        () -> config.fatigueThreshold,
                                        val -> config.fatigueThreshold = val
                                )
                                .controller(IntegerFieldControllerBuilder::create)
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
                    ClientConfig.HANDLER.save();

                    var client = net.minecraft.client.MinecraftClient.getInstance();

                    if (client.player != null) {

                        int value = ClientConfig.HANDLER.instance().personalTrainingBar;

                        if (value >= 0) {
                            client.player.networkHandler.sendChatCommand(
                                    "scoreboard players set @s training_bar " + value
                            );
                        }
                    }

                    if (EquipmentWeight.SERVER != null) {
                        EquipmentWeight.syncConfigToScoreboard(EquipmentWeight.SERVER);
                    }
                })

                .build()
                .generateScreen(parent);
    }
}