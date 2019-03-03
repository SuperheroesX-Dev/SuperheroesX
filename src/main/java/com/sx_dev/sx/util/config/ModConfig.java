package com.sx_dev.sx.util.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.handlers.EnumHandler;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

import static net.minecraftforge.fml.Logging.CORE;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MODID)
public class ModConfig {

    public static final ForgeConfigSpec SERVER_CONFIG;
    public static final ForgeConfigSpec CLIENT_CONFIG;
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    static {
        ClientConfig.init(CLIENT_BUILDER);
        CommonConfig.init(SERVER_BUILDER);

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        SuperheroesX.LOGGER.debug("Loading config file {}", path);

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        SuperheroesX.LOGGER.debug("Built TOML config for {}", path.toString());
        configData.load();
        SuperheroesX.LOGGER.debug("Loaded TOML config file {}", path.toString());
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final net.minecraftforge.fml.config.ModConfig.Loading configEvent) {
        SuperheroesX.LOGGER.debug("Loaded {} config file {}", Reference.MODID, configEvent.getConfig().getFileName());

    }

    @SubscribeEvent
    public static void onFileChange(final net.minecraftforge.fml.config.ModConfig.ConfigReloading configEvent) {
        SuperheroesX.LOGGER.fatal(CORE, "{} config just got changed on the file system!", Reference.MODID);
    }

    public static class ClientConfig {

        public static void init(ForgeConfigSpec.Builder clientBuilder) {
            HUDConfig.init(clientBuilder);
            ControlsConfig.init(clientBuilder);
        }

        public static class HUDConfig {

            public static ForgeConfigSpec.IntValue HUDOffsetX;
            public static ForgeConfigSpec.IntValue HUDOffsetY;
            public static ForgeConfigSpec.BooleanValue holdShiftForDetails;
            public static ForgeConfigSpec.IntValue HUDPosition;
            public static ForgeConfigSpec.DoubleValue HUDScale;
            public static ForgeConfigSpec.BooleanValue showHUDWhileChatting;
            public static ForgeConfigSpec.BooleanValue enableFuelHUD;
            public static ForgeConfigSpec.BooleanValue minimalFuelHUD;
            public static ForgeConfigSpec.BooleanValue showExactFuelInHUD;
            public static ForgeConfigSpec.BooleanValue enableStateHUD;

            public static void init(ForgeConfigSpec.Builder clientBuilder) {
                //clientBuilder.comment("HUDConfig");//.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.title")


                HUDOffsetX = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.offset_x.title")
                        .defineInRange("HUDConfig.HUDOffsetX", 0, 0, Integer.MAX_VALUE);

                HUDOffsetY = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.offset_y.title")
                        .defineInRange("HUDConfig.HUDOffsetY", 0, 0, Integer.MAX_VALUE);

                holdShiftForDetails = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.shift.title")
                        .define("HUDConfig.holdShiftForDetails", true);

                HUDPosition = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.pos.title")
                        .defineInRange("HUDConfig.HUDPosition", EnumHandler.HUDPositions.TOPLEFT.ordinal(), 0, EnumHandler.HUDPositions.values().length - 1);

                HUDScale = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.scale.title")
                        .defineInRange("HUDConfig.HUDScale", 1D, 0.1D, 100D);

                showHUDWhileChatting = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.chat.title")
                        .define("HUDConfig.showHUDWhileChatting", true);

                enableFuelHUD = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.enable_fuel.title")
                        .define("HUDConfig.enableFuelHUD", true);

                minimalFuelHUD = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.minimal.title")
                        .define("HUDConfig.minimalFuelHUD", false);

                showExactFuelInHUD = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.exact.title")
                        .define("HUDConfig.showExactFuelInHUD", true);

                enableStateHUD = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.hud.enable_state.title")
                        .define("HUDConfig.enableStateHUD", true);
            }
        }

        public static class ControlsConfig {

            public static ForgeConfigSpec.BooleanValue customControls;
            public static ForgeConfigSpec.BooleanValue invertHoverSneakingBehavior;

            public static void init(ForgeConfigSpec.Builder clientBuilder) {
                //clientBuilder.comment("controls");
                //.translation(Reference.CONFIG_LANG_PREFIX + "client.controls.title")

                customControls = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.controls.custom.title")
                        .define("controls.customControls", false);

                invertHoverSneakingBehavior = clientBuilder
                        //.translation(Reference.CONFIG_LANG_PREFIX + "client.controls.invert_sneak.title")
                        .define("controls.invertHoverSneakingBehavior", false);
            }
        }
    }

    public static class CommonConfig {

        public static ForgeConfigSpec.BooleanValue marvelItems;
        public static ForgeConfigSpec.BooleanValue dcItems;

        public static void init(ForgeConfigSpec.Builder serverBuilder) {
            serverBuilder.comment("items");


            marvelItems = serverBuilder
                    .comment("When disabled MARVEL Items will not be available")
                    //.translation(Reference.CONFIG_LANG_PREFIX + "common.marvel.title")
                    .worldRestart()
                    .define("items.marvelItems", true);

            dcItems = serverBuilder
                    .comment("When disabled DC Items will not be available")
                    //.translation(Reference.CONFIG_LANG_PREFIX + "common.dc.title")
                    .worldRestart()
                    .define("items.dcItems", true);
        }
    }
}
