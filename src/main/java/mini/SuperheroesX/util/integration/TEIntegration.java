package mini.SuperheroesX.util.integration;

import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import mini.SuperheroesX.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import static mini.SuperheroesX.util.handlers.RecipeHandler.cloneStack;

public class TEIntegration {

    public static void addTERecipes() {
        for (ItemStack stack1 : OreDictionary.getOres("ingotTitanium")) {
            for (ItemStack stack2 : OreDictionary.getOres("ingotGold")) {
                SmelterManager.addAlloyRecipe(SmelterManager.DEFAULT_ENERGY, cloneStack(stack1, 1),
                        cloneStack(stack2, 1), cloneStack(ItemInit.INGOT_TITANOGOLD, 1));
            }
        }
    }
}
