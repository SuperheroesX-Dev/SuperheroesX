package mini.SuperheroesX.util.handlers;

import mini.SuperheroesX.init.BlockInit;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.init.PotionInit;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.misc.MaterialTripplet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.GameData;

public class RecipeHandler {
    public static void addStandardRecipes() {


        /*  */
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.MINI_ARC_REACTOR_BASE, 1), "XXX", "B B", "XXX", 'X', "ingotIron", 'B', cloneStack(ItemInit.CIRCUIT, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.MINI_ARC_REACTOR_MK1, 1), "XXX", "XBX", "XXX", 'X', "ingotPalladium", 'B', cloneStack(ItemInit.MINI_ARC_REACTOR_BASE, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.MINI_ARC_REACTOR_MK2, 1), "XXX", "XBX", "XXX", 'X', "ingotVibranium", 'B', cloneStack(ItemInit.MINI_ARC_REACTOR_MK1, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.CIRCUIT, 1), "IGI", "GRG", "IGI", 'I', "ingotIron", 'G', "ingotGold", 'R', "dustRedstone");
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.CHESTPLATE_IRONMAN, 1), "P P", "PAP", "PPP", 'P', "plateTitanogold", 'A', cloneStack(ItemInit.MINI_ARC_REACTOR_MK1, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.CHESTPLATE_IRONMAN.setMultiplier(2), 1), "P P", "PAP", "PPP", 'P', "plateTitanogold", 'A', cloneStack(ItemInit.MINI_ARC_REACTOR_MK2, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.PLATE_GOLD, 1), "GG", "GG", 'G', "ingotGold");
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.PLATE_TITANIUM, 1), "TT", "TT", 'T', "ingotTitanium");
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.PLATE_VIBRANIUM, 1), "VV", "VV", 'V', "ingotVibranium");
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.INGOT_TITANOGOLD, 2), "TG", 'T', "ingotTitanium", 'G', "ingotGold");
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.PLATE_TITANOGOLD, 1), "TT", "TT", 'T', "ingotTitanogold");

        /* smelting */
        RecipeHelper.addSmelting(BlockInit.ORE_PALLADIUM, cloneStack(ItemInit.INGOT_PALLADIUM, 1), 1F);
        RecipeHelper.addSmelting(BlockInit.ORE_TITANIUM, cloneStack(ItemInit.INGOT_TITANIUM, 1), 1F);
        RecipeHelper.addSmelting(BlockInit.ORE_VIBRANIUM, cloneStack(ItemInit.INGOT_VIBRANIUM, 1), 1F);

        /* brewing */

    }

    public static ItemStack cloneStack(Item item, int stackSize) {

        if (item == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(item, stackSize);
    }

    public static ItemStack cloneStack(Block block, int stackSize) {

        if (block == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(block, stackSize);
    }

    public static ItemStack cloneStack(ItemStack stack, int stackSize) {

        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        ItemStack retStack = stack.copy();
        retStack.setCount(stackSize);

        return retStack;
    }

    public static ItemStack cloneStack(ItemStack stack) {

        return stack.isEmpty() ? ItemStack.EMPTY : stack.copy();
    }

    public static class RecipeHelper {

        /* GENERAL */
        static void addShapedRecipe(ItemStack output, Object... input) {

            ResourceLocation location = getNameForRecipe(output);
            CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(input);
            ShapedRecipes recipe = new ShapedRecipes(output.getItem().getRegistryName().toString(), primer.width, primer.height, primer.input, output);
            recipe.setRegistryName(location);
            GameData.register_impl(recipe);
        }

        public static void addShapedOreRecipe(ItemStack output, Object... input) {

            ResourceLocation location = getNameForRecipe(output);
            ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, input);
            recipe.setRegistryName(location);
            GameData.register_impl(recipe);
        }

        public static void addShapelessRecipe(ItemStack output, Object... input) {

            ResourceLocation location = getNameForRecipe(output);
            ShapelessRecipes recipe = new ShapelessRecipes(location.getResourceDomain(), output, buildInput(input));
            recipe.setRegistryName(location);
            GameData.register_impl(recipe);
        }

        public static void addShapelessOreRecipe(ItemStack output, Object... input) {

            ResourceLocation location = getNameForRecipe(output);
            ShapelessOreRecipe recipe = new ShapelessOreRecipe(location, output, input);
            recipe.setRegistryName(location);
            GameData.register_impl(recipe);
        }

        /* STORAGE */
        static void addStorageRecipe(ItemStack one, String nine) {

            addShapedRecipe(one, "XXX", "XXX", "XXX", 'X', nine);
        }

        static void addStorageRecipe(ItemStack one, ItemStack nine) {

            addShapedRecipe(one, "XXX", "XXX", "XXX", 'X', cloneStack(nine, 1));
        }

        static void addSmallStorageRecipe(ItemStack one, String four) {

            addShapedRecipe(one, "XX", "XX", 'X', four);
        }

        static void addSmallStorageRecipe(ItemStack one, ItemStack four) {

            addShapedRecipe(cloneStack(one), "XX", "XX", 'X', cloneStack(four, 1));
        }

        static void addReverseStorageRecipe(ItemStack nine, String one) {

            addShapelessRecipe(cloneStack(nine, 9), one);
        }

        static void addReverseStorageRecipe(ItemStack nine, ItemStack one) {

            addShapelessRecipe(cloneStack(nine, 9), cloneStack(one, 1));
        }

        static void addSmallReverseStorageRecipe(ItemStack four, String one) {

            addShapelessRecipe(cloneStack(four, 4), one);
        }

        static void addSmallReverseStorageRecipe(ItemStack four, ItemStack one) {

            addShapelessRecipe(cloneStack(four, 4), cloneStack(one, 1));
        }

        public static void addTwoWayStorageRecipe(ItemStack one, ItemStack nine) {

            addStorageRecipe(one, nine);
            addReverseStorageRecipe(nine, one);
        }

        public static void addTwoWayStorageRecipe(ItemStack one, String one_ore, ItemStack nine, String nine_ore) {

            addStorageRecipe(one, nine_ore);
            addReverseStorageRecipe(nine, one_ore);
        }

        public static void addSmallTwoWayStorageRecipe(ItemStack one, ItemStack four) {

            addSmallStorageRecipe(one, four);
            addSmallReverseStorageRecipe(four, one);
        }

        public static void addSmallTwoWayStorageRecipe(ItemStack one, String one_ore, ItemStack four, String four_ore) {

            addSmallStorageRecipe(one, four_ore);
            addSmallReverseStorageRecipe(four, one_ore);
        }

        public static void addMaterialStorageRecipe(MaterialTripplet mat) {
            addTwoWayStorageRecipe(cloneStack(mat.getBlock(), 1), cloneStack(mat.getIngot(), 1));
            addTwoWayStorageRecipe(cloneStack(mat.getIngot(), 1), cloneStack(mat.getNugget(), 1));
        }

        /* TOOLS */
        public static void addAxeRecipe(ItemStack axe, ItemStack headMat, ItemStack handleMat) {

            addShapedRecipe(axe, "XX", "XS", " S", 'X', cloneStack(headMat, 1), 'S', cloneStack(handleMat, 1));
            addShapedRecipe(axe, "XX", "SX", "S ", 'X', cloneStack(headMat, 1), 'S', cloneStack(handleMat, 1));
        }

        public static void addAxeRecipe(ItemStack axe, String headMat, String handleMat) {

            addShapedOreRecipe(axe, "XX", "XS", " S", 'X', headMat, 'S', handleMat);
            addShapedOreRecipe(axe, "XX", "SX", "S ", 'X', headMat, 'S', handleMat);
        }

        public static void addPickaxeRecipe(ItemStack axe, ItemStack headMat, ItemStack handleMat) {

            addShapedRecipe(axe, "XXX", " S ", " S ", 'X', cloneStack(headMat, 1), 'S', cloneStack(handleMat, 1));
        }

        public static void addPickaxeRecipe(ItemStack axe, String headMat, String handleMat) {

            addShapedOreRecipe(axe, "XXX", " S ", " S ", 'X', headMat, 'S', handleMat);
        }

        public static void addSwordRecipe(ItemStack axe, ItemStack headMat, ItemStack handleMat) {

            addShapedRecipe(axe, "X", "X", "S", 'X', cloneStack(headMat, 1), 'S', cloneStack(handleMat, 1));
        }

        public static void addSwordRecipe(ItemStack axe, String headMat, String handleMat) {

            addShapedOreRecipe(axe, "X", "X", "S", 'X', headMat, 'S', handleMat);
        }

        public static void addShovelRecipe(ItemStack axe, ItemStack headMat, ItemStack handleMat) {

            addShapedRecipe(axe, "X", "S", "S", 'X', cloneStack(headMat, 1), 'S', cloneStack(handleMat, 1));
        }

        public static void addShovelRecipe(ItemStack axe, String headMat, String handleMat) {

            addShapedOreRecipe(axe, "X", "S", "S", 'X', headMat, 'S', handleMat);
        }


        /*public static void addCommonToolRecipes(List<ImmutablePair<String,Item>> tools, ItemStack headMat, ItemStack handleMat) {
            for (Pair<String,Item> p : tools) {
                switch (p.getLeft()){
                    case "shovel":  addShovelRecipe(cloneStack(p.getRight(),1),headMat,handleMat);
                    break;
                    case "pickaxe": addPickaxeRecipe(cloneStack(p.getRight(),1),headMat,handleMat);
                    break;
                    case "axe" :    addAxeRecipe(cloneStack(p.getRight(),1),headMat,handleMat);
                    break;
                    case "sword":   addSwordRecipe(cloneStack(p.getRight(),1),headMat,handleMat);
                    break;
                    default:break;
                }
            }


        }*/

        /* HELPERS */
        static ResourceLocation getNameForRecipe(ItemStack output) {

            ModContainer activeContainer = Loader.instance().activeModContainer();
            ResourceLocation baseLoc = new ResourceLocation(activeContainer.getModId(), output.getItem().getRegistryName().getResourcePath());
            ResourceLocation recipeLoc = baseLoc;
            int index = 0;
            while (CraftingManager.REGISTRY.containsKey(recipeLoc)) {
                index++;
                recipeLoc = new ResourceLocation(activeContainer.getModId(), baseLoc.getResourcePath() + "_" + index);
            }
            return recipeLoc;
        }

        static NonNullList<Ingredient> buildInput(Object[] input) {

            NonNullList<Ingredient> list = NonNullList.create();

            for (Object obj : input) {
                if (obj instanceof Ingredient) {
                    list.add((Ingredient) obj);
                } else {
                    Ingredient ingredient = CraftingHelper.getIngredient(obj);

                    if (ingredient == null) {
                        ingredient = Ingredient.EMPTY;
                    }
                    list.add(ingredient);
                }
            }
            return list;
        }

        /* SMELTING */
        static void addSmelting(Block input, ItemStack output, float xp) {
            FurnaceRecipes.instance().addSmeltingRecipeForBlock(input, output, xp);
        }

        static void addSmelting(Item input, ItemStack output, float xp) {
            FurnaceRecipes.instance().addSmelting(input, output, xp);
        }

        static void addSmelting(ItemStack input, ItemStack output, float xp) {
            FurnaceRecipes.instance().addSmeltingRecipe(input, output, xp);
        }

        public static void addSmelting(ItemStack input, ItemStack output) {

            addSmelting(input, output, 0F);
        }

        public static void addSmelting(Item input, ItemStack output) {

            addSmelting(input, output, 0F);
        }

        public static void addSmelting(Block input, ItemStack output) {

            addSmelting(input, output, 0F);
        }

        /* BREWING */
        static void addBrewing(PotionType potionIn, Item ingredientIn, PotionType potionOut) {
            PotionHelper.addMix(potionIn, ingredientIn, potionOut);
        }

        static void addBrewing(PotionType potionIn, Item ingredientIn, ResourceLocation potionOutName) {
            PotionType potionOut = null;
            for (PotionType potion : PotionInit.POTION_ITEMS) {
                if (potion.getRegistryName().equals(potionOutName)) potionOut = potion;
            }
            if (potionOut != null) {
                addBrewing(potionIn, ingredientIn, potionOut);
            }
        }

        static void addBrewing(ResourceLocation potionInName, Item ingredientIn, ResourceLocation potionOutName) {
            PotionType potionIn = null;
            for (PotionType potion : PotionInit.POTION_ITEMS) {
                if (potion.getRegistryName().equals(potionInName)) potionIn = potion;
            }
            if (potionIn != null) {
                addBrewing(potionIn, ingredientIn, potionOutName);
            }
        }

        static void addBrewing(PotionType potionIn, Item ingredientIn, String potionOutName) {
            addBrewing(potionIn, ingredientIn, new ResourceLocation(Reference.MODID, potionOutName));
        }

        static void addBrewing(String potionInName, Item ingredientIn, String potionOutName) {
            addBrewing(new ResourceLocation(Reference.MODID, potionInName), ingredientIn, new ResourceLocation(Reference.MODID, potionOutName));
        }


    }
}
