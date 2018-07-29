package mini.sx.util.integration.TE;

import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import mini.sx.init.ItemInit;

import static mini.sx.util.handlers.RecipeHandler.cloneStack;

public class TEIntegration {

    public static void preInit() {
    }

    public static void init() {
    }

    public static void postInit() {
        SmelterManager.addAlloyRecipe(4000, "ingotTitanium", 1, "ingotGold", 1, cloneStack(ItemInit.INGOT_TITANOGOLD, 1));
    }
}
