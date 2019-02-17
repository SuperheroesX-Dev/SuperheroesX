package com.sx_dev.sx;

import com.sx_dev.sx.proxy.CommonProxy;
import com.sx_dev.sx.tabs.SuperheroesXTabs;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.handlers.PacketHandler;
import com.sx_dev.sx.util.handlers.RecipeHandler;
import com.sx_dev.sx.util.handlers.RegistryHandler;
import com.sx_dev.sx.util.handlers.SyncHandler;
import com.sx_dev.sx.util.integration.Integrations;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID/*, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSION, dependencies = Reference.DEPENDENCIES*/)
public class SuperheroesX {

    /*public static final CreativeTabs SUPERHEROES_X_TAB_MATERIALS = new SuperheroesXTabs.Materials();
    public static final CreativeTabs SUPERHEROES_X_TAB_MARVEL = new SuperheroesXTabs.MARVEL();
    public static final CreativeTabs SUPERHEROES_X_TAB_DC = new SuperheroesXTabs.DC();*/

    public SuperheroesX (){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStopping);
    }

    //@Instance(Reference.MODID)
    public static SuperheroesX INSTANCE;


    //@SidedProxy(modId = Reference.MODID, clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    //public static CommonProxy PROXY;

    public static Logger LOGGER = LogManager.getLogger(Reference.MODID);

    /*==================Debug Variable==================*/
    /*==*/public static final boolean DEBUG = false;/*==*/
    /*==================================================*/

    private void preInit(final FMLCommonSetupEvent event) {
        RegistryHandler.preInitRegistries(event);
        Integrations.preInitIntegrations();
    }

    /*@EventHandler
    public static void init(FMLLoadCompleteEvent event) {
        RegistryHandler.initRegistries();
        Integrations.initIntegrations();
        PROXY.registerHandlers();
        PROXY.initKeys();
        PacketHandler.init();
    }*/

    /*@EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        RegistryHandler.postInitRegistries();
        Integrations.postInitIntegrations();
        RecipeHandler.addStandardRecipes();
    }*/

    //@EventHandler
    private void serverStopping(FMLServerStoppingEvent evt) {
        SyncHandler.clearAll();
    }
}
