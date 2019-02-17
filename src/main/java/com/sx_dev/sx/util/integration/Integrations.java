package com.sx_dev.sx.util.integration;

import com.sx_dev.sx.util.integration.TC.TCIntegration;
import com.sx_dev.sx.util.integration.TE.TEIntegration;
import net.minecraftforge.fml.ModList;
//import net.minecraftforge.fml.common.Loader;

public class Integrations {

    private static final ModList modList = ModList.get();
    public static final boolean TC = modList.isLoaded("tconstruct");
    public static final boolean TE = modList.isLoaded("thermalexpansion");
    public static final boolean TF = modList.isLoaded("thermalfoundation");

    public static void preInitIntegrations() {
        if (TC) {
            //TCIntegration.preInit();
        }
        if (TE) {
            TEIntegration.preInit();
        }
    }

    public static void initIntegrations() {
        if (TC) {
            //TCIntegration.init();
        }
        if (TE) {
            TEIntegration.init();
        }
    }

    public static void postInitIntegrations() {
        if (TC) {
            //TCIntegration.postInit();
        }
        if (TE) {
            TEIntegration.postInit();
        }
    }
}
