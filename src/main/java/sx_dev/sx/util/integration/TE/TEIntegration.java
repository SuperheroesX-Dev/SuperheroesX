package sx_dev.sx.util.integration.TE;

import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import sx_dev.sx.init.ItemInit;

import static sx_dev.sx.util.handlers.RecipeHandler.cloneStack;

public class TEIntegration {

    public static void preInit() {
    }

    public static void init() {
    }

    public static void postInit() {
        SmelterManager.addAlloyRecipe(4000, "ingotTitanium", 1, "ingotGold", 1, cloneStack(ItemInit.INGOT_TITANOGOLD, 1));
    }
}
