package com.sx_dev.sx.util.config;

import com.sx_dev.sx.util.handlers.EnumHandler;

/*
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.handlers.EnumHandler;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MODID)
@Config.LangKey(Reference.CONFIG_LANG_PREFIX + "category.title")*/
public class ModConfig {

    //    @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "common.title")
    public static final CommonConfig common = new CommonConfig();

    //    @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.title")
    public static final ClientConfig client = new ClientConfig();

    public static class ClientConfig {

        //        @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.title")
        public final HUDConfig hud = new HUDConfig();

        //        @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.controls.title")
        public final ControlsConfig controls = new ControlsConfig();

        public static class HUDConfig {

            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.offset_x.title")
            public final int HUDOffsetX = 0;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.offset_y.title")
            public final int HUDOffsetY = 0;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.shift.title")
            public boolean holdShiftForDetails = true;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.pos.title")
            public EnumHandler.HUDPositions HUDPosition = EnumHandler.HUDPositions.TOP_LEFT;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.scale.title")
            public double HUDScale = 1.0D;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.chat.title")
            public boolean showHUDWhileChatting = true;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.enable_fuel.title")
            public boolean enableFuelHUD = true;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.minimal.title")
            public boolean minimalFuelHUD = false;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.exact.title")
            public boolean showExactFuelInHUD = false;
            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.hud.enable_state.title")
            public boolean enableStateHUD = true;
        }

        public static class ControlsConfig {

            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.controls.custom.title")
            public boolean customControls = false;

            //            @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "client.controls.invert_sneak.title")
            public boolean invertHoverSneakingBehavior = false;
        }
    }

    public static class CommonConfig {

        //        @Config.Comment("When disabled MARVEL Items will not be available")
//        @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "common.marvel.title")
//        @Config.RequiresMcRestart
        public boolean marvelItems = true;

        //        @Config.Comment("When disabled DC Items will not be available")
//        @Config.LangKey(Reference.CONFIG_LANG_PREFIX + "common.dc.title")
//        @Config.RequiresMcRestart
        public boolean dcItems = true;
    }

//    @Mod.EventBusSubscriber(modid = Reference.MODID)
    /*private static class EventHandler {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         *//*
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MODID)) {
                ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
            }
        }
    }*/
}
