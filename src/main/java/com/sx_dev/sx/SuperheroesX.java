package com.sx_dev.sx;

import com.sx_dev.sx.proxy.ClientProxy;
import com.sx_dev.sx.proxy.CommonProxy;
import com.sx_dev.sx.tabs.CustomItemGroup;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.handlers.RegistryHandler;
import com.sx_dev.sx.util.handlers.SyncHandler;
import com.sx_dev.sx.util.integration.Integrations;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
public class SuperheroesX {

    public static final ItemGroup SUPERHEROES_X_TAB_MATERIALS = new CustomItemGroup.Materials();
    public static final ItemGroup SUPERHEROES_X_TAB_MARVEL = /*ModConfig.common.marvelItems*/true ? new CustomItemGroup.MARVEL() : null;
    public static final ItemGroup SUPERHEROES_X_TAB_DC = /*ModConfig.common.dcItems*/true ? new CustomItemGroup.DC() : null;
    /*========================Debug Variable========================*/
    /*========*/public static final boolean DEBUG = false;/*========*/
    public static SuperheroesX INSTANCE;
    public static CommonProxy PROXY;
    public static Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public SuperheroesX (){
        INSTANCE = this;
        PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStopping);
        MinecraftForge.EVENT_BUS.register(this);
    }
    /*==============================================================*/

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
