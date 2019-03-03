package com.sx_dev.sx.util.handlers;

public class RecipeHandler {
    public static void addStandardRecipes() {

        /* smelting *//*
        RecipeHelper.addSmelting(BlockInit.ORE_PALLADIUM, cloneStack(ItemInit.INGOT_PALLADIUM, 1), 1F);
        RecipeHelper.addSmelting(BlockInit.ORE_TITANIUM, cloneStack(ItemInit.INGOT_TITANIUM, 1), 1F);
        RecipeHelper.addSmelting(BlockInit.ORE_VIBRANIUM, cloneStack(ItemInit.INGOT_VIBRANIUM, 1), 1F);
        RecipeHelper.addSmelting("dustPalladium", cloneStack(ItemInit.INGOT_PALLADIUM, 1), 1F);
        RecipeHelper.addSmelting("dustTitanium", cloneStack(ItemInit.INGOT_TITANIUM, 1), 1F);
        RecipeHelper.addSmelting("dustVibranium", cloneStack(ItemInit.INGOT_VIBRANIUM, 1), 1F);
    }

    public static class RecipeHelper {

        /* SMELTING *//*
        static void addSmelting(Block input, ItemStack output, float xp) {
            FurnaceRecipes.instance().addSmeltingRecipeForBlock(input, output, xp);
        }

        static void addSmelting(Item input, ItemStack output, float xp) {
            FurnaceRecipes.instance().addSmelting(input, output, xp);
        }

        static void addSmelting(ItemStack input, ItemStack output, float xp) {
            FurnaceRecipes.instance().addSmeltingRecipe(input, output, xp);
        }

        static void addSmelting(String input, ItemStack output, float xp) {
            for (ItemStack in : OreDictionary.getOres(input)) {
                addSmelting(in, output, xp);
            }
        }

        public static void addSmelting(ItemStack input, ItemStack output) {

            addSmelting(input, output, 0F);
        }

        public static void addSmelting(Item input, ItemStack output) {

            addSmelting(input, output, 0F);
        }

        public static void addSmelting(Block input, ItemStack output) {

            addSmelting(input, output, 0F);
        }*/
    }
}
