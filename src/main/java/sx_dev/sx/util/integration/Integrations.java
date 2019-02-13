package sx_dev.sx.util.integration;

import net.minecraftforge.fml.common.Loader;
import sx_dev.sx.util.integration.TC.TCIntegration;
import sx_dev.sx.util.integration.TE.TEIntegration;

public class Integrations {

    public static final boolean TC = Loader.isModLoaded("tconstruct");
    public static final boolean TE = Loader.isModLoaded("thermalexpansion");
    public static final boolean TF = Loader.isModLoaded("thermalfoundation");

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
