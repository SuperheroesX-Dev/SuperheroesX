package com.sx_dev.sx;

import com.sx_dev.sx.tabs.CustomItemGroup;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.handlers.RegistryHandler;
import com.sx_dev.sx.util.handlers.SyncHandler;
import com.sx_dev.sx.util.integration.Integrations;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID/*, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSION, dependencies = Reference.DEPENDENCIES*/)
public class SuperheroesX {

    public static final ItemGroup SUPERHEROES_X_TAB_MATERIALS = new CustomItemGroup.Materials();
    public static final ItemGroup SUPERHEROES_X_TAB_MARVEL = new CustomItemGroup.MARVEL();
    public static final ItemGroup SUPERHEROES_X_TAB_DC = new CustomItemGroup.DC();

    public SuperheroesX (){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStopping);
        MinecraftForge.EVENT_BUS.register(this);
    }

    //@SidedProxy(modId = Reference.MODID, clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    //public static CommonProxy PROXY;

    public static Logger LOGGER = LogManager.getLogger(Reference.MODID);

    /*==================Debug Variable==================*/
    /*==*/public static final boolean DEBUG = false;/*==*/
    /*==================================================*/

    private void setup(final FMLCommonSetupEvent event) {
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
