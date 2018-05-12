package mini.SuperheroesX.util.integration;

import mini.SuperheroesX.util.integration.TC.TCIntegration;
import net.minecraftforge.fml.common.Loader;

public class Integrations {

    public static final boolean TC = Loader.isModLoaded("tconstruct");
    public static final boolean TE = Loader.isModLoaded("thermalexpansion");

    public static void preInitIntegrations() {
        if (TC) {
            TCIntegration.preInit();
        }
    }

    public static void initIntegrations() {
        if (TC) {
            TCIntegration.init();
        }
    }

    public static void postInitIntegrations() {
        if (TC) {
            TCIntegration.postInit();
        }
    }
}
