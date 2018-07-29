package mini.sx.util.config;

import jline.internal.Log;
import mini.sx.SuperheroesX;
import mini.sx.util.Reference;
import mini.sx.util.handlers.PacketHandler;
import mini.sx.util.network.message.MessageConfigSync;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public static final List<Section> configSections = new ArrayList<>();
    private static final Section sectionItems = new Section(false, "Item Settings", "item");
    private static final Section sectionControls = new Section(true, "Controls Settings", "controls");
    public static Configuration config;
    public static Configuration configClient;

    // items
    public static boolean marvelItems = Defaults.marvelItems;
    public static boolean dcItems = Defaults.dcItems;

    // controls
    public static boolean customControls = Defaults.customControls;
    public static String flyKey = Defaults.flyKey;
    public static String descendKey = Defaults.descendKey;
    public static boolean invertHoverSneakingBehavior = Defaults.invertHoverSneakingBehavior;
    public static boolean doubleTapSprintInAir = Defaults.doubleTapSprintInAir;


    // gui
    public static boolean holdShiftForDetails = Defaults.holdShiftForDetails;
    public static int HUDPosition = Defaults.HUDPosition;
    public static int HUDOffsetX = Defaults.HUDOffsetX;
    public static int HUDOffsetY = Defaults.HUDOffsetY;
    public static double HUDScale = Defaults.HUDScale;
    public static boolean showHUDWhileChatting = Defaults.showHUDWhileChatting;
    public static boolean enableFuelHUD = Defaults.enableFuelHUD;
    public static boolean minimalFuelHUD = Defaults.minimalFuelHUD;
    public static boolean showExactFuelInHUD = Defaults.showExactFuelInHUD;
    public static boolean enableStateHUD = Defaults.enableStateHUD;

    public static void preInit(FMLPreInitializationEvent evt) {
        MinecraftForge.EVENT_BUS.register(new Config());

        config = new Configuration(new File(evt.getModConfigurationDirectory(), Reference.MODID + ".cfg"));
        configClient = new Configuration(new File(evt.getModConfigurationDirectory(), Reference.MODID + "-client.cfg"));

        syncConfig(false);
        SuperheroesX.PROXY.updateCustomKeybinds(flyKey, descendKey);
    }

    private static void syncConfig(boolean load) {
        SuperheroesX.LOGGER.info("Loading configuration files");
        try {
            if (load) {
                config.load();
            }
            processConfig();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
            if (configClient.hasChanged()) {
                configClient.save();
            }
        }
    }

    public static void onConfigChanged(String modid) {
        if (modid.equals(Reference.MODID)) {
            syncConfig(false);
            SuperheroesX.PROXY.updateCustomKeybinds(flyKey, descendKey);
        }
    }

    private static void processConfig() {

        marvelItems = configClient.get(sectionItems.name,"Enable MARVEL Items",Defaults.marvelItems,"When disabled MARVEL Items will not be available").getBoolean(Defaults.marvelItems);
        dcItems = configClient.get(sectionItems.name,"Enable DC Items",Defaults.dcItems,"When disabled DC Items will not be available").getBoolean(Defaults.dcItems);

        customControls = configClient.get(sectionControls.name, "Custom controls", Defaults.customControls, "When enabled, the key codes specified here will be used for the fly and descend keys. Otherwise, the vanilla jump and sneak keys will be used.").getBoolean(Defaults.customControls);
        flyKey = configClient.get(sectionControls.name, "Custom Fly key", Defaults.flyKey, "The name of the Fly key when custom controls are enabled.").getString();
        descendKey = configClient.get(sectionControls.name, "Custom Descend key", Defaults.descendKey, "The name of the Descend key when custom controls are enabled.").getString();
        doubleTapSprintInAir = configClient.get(sectionControls.name, "Allow double-tap sprinting while flying", Defaults.doubleTapSprintInAir, "When enabled, sprinting by double-tapping the forward key will work while flying with a jetpack. Can be used as an easier way to activate a jetpack's boost while airborne (the sprint key also works).").getBoolean(Defaults.doubleTapSprintInAir);


    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent evt) {
        onConfigChanged(evt.getModID());
    }

    @SubscribeEvent
    public void ConfigFileChanged(ConfigChangedEvent evt) {
        if (evt.getModID().equals(Reference.MODID)) {
            Log.info("Updating config...");
            syncConfig(true);
        }
    }

    @SubscribeEvent
    public void onPlayerLoggon(PlayerLoggedInEvent evt) {
        PacketHandler.instance.sendTo(new MessageConfigSync(), (EntityPlayerMP) evt.player);
    }
}