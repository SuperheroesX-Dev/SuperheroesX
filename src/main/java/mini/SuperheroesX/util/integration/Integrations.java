package mini.SuperheroesX.util.integration;

import mini.SuperheroesX.util.integration.TC.TCIntegration;
import mini.SuperheroesX.util.integration.TE.TEIntegration;
import net.minecraftforge.fml.common.Loader;

public class Integrations {

    public static final boolean TC = Loader.isModLoaded("tconstruct");
    public static final boolean TE = Loader.isModLoaded("thermalexpansion");

    public static void preInitIntegrations() {
        if (TC) {
            TCIntegration.preInit();
        }
        if (TE) {
            TEIntegration.preInit();
        }
    }

    public static void initIntegrations() {
        if (TC) {
            TCIntegration.init();
        }
        if (TE) {
            TEIntegration.init();
        }
    }

    public static void postInitIntegrations() {
        if (TC) {
            TCIntegration.postInit();
        }
        if (TE) {
            TEIntegration.postInit();
        }
    }
}
