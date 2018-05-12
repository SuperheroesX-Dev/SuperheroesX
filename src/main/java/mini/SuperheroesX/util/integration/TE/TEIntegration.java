package mini.SuperheroesX.util.integration.TE;

import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import mini.SuperheroesX.init.ItemInit;

import static mini.SuperheroesX.util.handlers.RecipeHandler.cloneStack;

public class TEIntegration {

    public static void preInit() {
    }

    public static void init() {
    }

    public static void postInit() {
        SmelterManager.addAlloyRecipe(4000, "ingotTitanium", 1, "ingotGold", 1, cloneStack(ItemInit.INGOT_TITANOGOLD, 1));
    }
}
