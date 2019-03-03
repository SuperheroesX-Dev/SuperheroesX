package com.sx_dev.sx;

import com.sx_dev.sx.proxy.ClientProxy;
import com.sx_dev.sx.proxy.IProxy;
import com.sx_dev.sx.proxy.ServerProxy;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.config.ModConfig;
import com.sx_dev.sx.util.handlers.PacketHandler;
import com.sx_dev.sx.util.handlers.RecipeHandler;
import com.sx_dev.sx.util.handlers.RegistryHandler;
import com.sx_dev.sx.util.handlers.SyncHandler;
import com.sx_dev.sx.util.integration.Integrations;
import com.sx_dev.sx.world.gen.WorldGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
public final class SuperheroesX {

    /*========================Debug Variable========================*/
    /*========*/public static final boolean DEBUG = false;/*========*/
    /*==============================================================*/

    public static SuperheroesX INSTANCE;
    @SuppressWarnings("Convert2MethodRef")
    public static IProxy PROXY = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public SuperheroesX() {
        INSTANCE = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStopping);
        MinecraftForge.EVENT_BUS.register(this);

        ModConfig.loadConfig(ModConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.MODID + "-client.toml"));
        ModConfig.loadConfig(ModConfig.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.MODID + "-common.toml"));
    }

    private void setup(final FMLCommonSetupEvent event) {

        WorldGen.setupOregen();

        //pre init
        RegistryHandler.preInitRegistries(event);
        Integrations.preInitIntegrations();

        //init
        RegistryHandler.initRegistries();
        Integrations.initIntegrations();
        PROXY.registerHandlers();
        PROXY.initKeys();
        PacketHandler.registerMessages();

        //post init
        RegistryHandler.postInitRegistries();
        Integrations.postInitIntegrations();
        RecipeHandler.addStandardRecipes();
    }

    private void serverStopping(FMLServerStoppingEvent evt) {
        SyncHandler.clearAll();
    }
}
