package sx_dev.sx;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sx_dev.sx.proxy.CommonProxy;
import sx_dev.sx.tabs.SuperheroesXTabs;
import sx_dev.sx.util.Reference;
import sx_dev.sx.util.handlers.PacketHandler;
import sx_dev.sx.util.handlers.RecipeHandler;
import sx_dev.sx.util.handlers.RegistryHandler;
import sx_dev.sx.util.handlers.SyncHandler;
import sx_dev.sx.util.integration.Integrations;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSION, dependencies = Reference.DEPENDENCIES)
public class SuperheroesX {

    public static final CreativeTabs SUPERHEROES_X_TAB_MATERIALS = new SuperheroesXTabs.Materials();
    public static final CreativeTabs SUPERHEROES_X_TAB_MARVEL = new SuperheroesXTabs.MARVEL();
    public static final CreativeTabs SUPERHEROES_X_TAB_DC = new SuperheroesXTabs.DC();

    @Instance(Reference.MODID)
    public static SuperheroesX INSTANCE;


    @SidedProxy(modId = Reference.MODID, clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy PROXY;

    public static Logger LOGGER = LogManager.getLogger(Reference.MODID);

    /*==================Debug Variable==================*/
    /*==*/public static final boolean DEBUG = false;/*==*/
    /*==================================================*/

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries(event);
        Integrations.preInitIntegrations();
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        RegistryHandler.initRegistries();
        Integrations.initIntegrations();
        PROXY.registerHandlers();
        PROXY.initKeys();
        PacketHandler.init();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        RegistryHandler.postInitRegistries();
        Integrations.postInitIntegrations();
        RecipeHandler.addStandardRecipes();
    }

    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent evt) {
        SyncHandler.clearAll();
    }
}
