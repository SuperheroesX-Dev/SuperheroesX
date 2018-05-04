package mini.SuperheroesX;

import mini.SuperheroesX.proxy.CommonProxy;
import mini.SuperheroesX.tabs.SuperheroesXTabs;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.config.Config;
import mini.SuperheroesX.util.handlers.PacketHandler;
import mini.SuperheroesX.util.handlers.RecipeHandler;
import mini.SuperheroesX.util.handlers.RegistryHandler;
import mini.SuperheroesX.util.handlers.SyncHandler;
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
        RegistryHandler.preInitRegistries();

        Config.preInit(event);
    }

	@EventHandler
    public static void init(FMLInitializationEvent event) {
        RegistryHandler.initRegistries();
        PROXY.registerHandlers();
        PROXY.initKeys();
        PacketHandler.init();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        RecipeHandler.addStandardRecipes();
        RegistryHandler.postInitRegistries();
    }

    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent evt) {
        SyncHandler.clearAll();
    }
}
