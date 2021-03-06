package sx_dev.sx.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
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
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.GameData;
import sx_dev.sx.init.BlockInit;
import sx_dev.sx.init.ItemInit;
import sx_dev.sx.init.PotionInit;
import sx_dev.sx.objects.blocks.BlockBase;
import sx_dev.sx.objects.items.ItemBase;
import sx_dev.sx.util.Reference;
import sx_dev.sx.util.integration.Integrations;
import sx_dev.sx.util.misc.MaterialTripplet;

public class RecipeHandler {
    public static void addStandardRecipes() {


        /*  */
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.MINI_ARC_REACTOR_BASE, 1), "XXX", "B B", "XXX", 'X', "ingotIron", 'B', cloneStack(ItemInit.CIRCUIT, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.MINI_ARC_REACTOR_MK1, 1), "XXX", "XBX", "XXX", 'X', "ingotPalladium", 'B', cloneStack(ItemInit.MINI_ARC_REACTOR_BASE, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.MINI_ARC_REACTOR_MK2, 1), "XXX", "XBX", "XXX", 'X', "ingotVibranium", 'B', cloneStack(ItemInit.MINI_ARC_REACTOR_MK1, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.CHESTPLATE_IRONMAN.getTieredItemStack(1, false), 1), "P P", "PAP", "PPP", 'P', "plateTitanogold", 'A', cloneStack(ItemInit.MINI_ARC_REACTOR_MK1, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.CHESTPLATE_IRONMAN.getTieredItemStack(2, false), 1), "P P", "PAP", "PPP", 'P', "plateTitanogold", 'A', cloneStack(ItemInit.MINI_ARC_REACTOR_MK2, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.CIRCUIT, 1), "RRR", "GGG", 'R', Items.REDSTONE, 'G', "plateGold");
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.SHIELD_CAPTAIN_AMERICA_UNCOLORED, 1), "PPP", "PHP", "PPP", 'P', "plateVibranium", 'H', cloneStack(ItemInit.SHIELD_HANDLE, 1));
        RecipeHelper.addShapedRecipe(cloneStack(ItemInit.SHIELD_HANDLE, 1), "L L", "L L", "L L", 'L', Items.LEATHER);
        RecipeHelper.addShapelessOreRecipe(cloneStack(ItemInit.SHIELD_CAPTAIN_AMERICA, 1), "dyeBlue", "dyeWhite", "dyeRed", cloneStack(ItemInit.SHIELD_CAPTAIN_AMERICA_UNCOLORED, 1));


        if (!Integrations.TE) {
            if (!Integrations.TC) {
                RecipeHelper.addShapelessOreRecipe(cloneStack(ItemInit.INGOT_TITANOGOLD, 2), "ingotTitanium", "ingotGold");
            }
            RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.PLATE_TITANOGOLD, 1), "TT", "TT", 'T', "ingotTitanogold");
            RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.PLATE_TITANIUM, 1), "TT", "TT", 'T', "ingotTitanium");
            RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.PLATE_VIBRANIUM, 1), "VV", "VV", 'V', "ingotVibranium");
            RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.PLATE_GOLD, 1), "GG", "GG", 'G', "ingotGold");
        }

        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.CLOTH, 1), "SSS", "S S", "SSS", 'S', cloneStack(Items.STRING, 1));
        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.STRENGTHENED_CLOTH, 1), "CCC", "ITI", "CCC", 'C', cloneStack(ItemInit.CLOTH, 1), 'I', "ingotIron", 'T', "ingotTitanium");

        RecipeHelper.addArmorRecipe(ItemInit.HELMET_CAPTAIN_AMERICA, ItemInit.CHESTPLATE_CAPTAIN_AMERICA, ItemInit.LEGGINGS_CAPTAIN_AMERICA, ItemInit.BOOTS_CAPTAIN_AMERICA, cloneStack(ItemInit.STRENGTHENED_CLOTH, 1));

        RecipeHelper.addTwoWayStorageRecipe((BlockBase) BlockInit.BLOCK_VIBRANIUM, (ItemBase) ItemInit.INGOT_VIBRANIUM);
        RecipeHelper.addTwoWayStorageRecipe((BlockBase) BlockInit.BLOCK_TITANIUM, (ItemBase) ItemInit.INGOT_TITANIUM);
        RecipeHelper.addTwoWayStorageRecipe((BlockBase) BlockInit.BLOCK_PALLADIUM, (ItemBase) ItemInit.INGOT_PALLADIUM);

        //RecipeHelper.addMaterialStorageRecipe(new MaterialTripplet(BlockInit.BLOCK_PALLADIUM, ItemInit.INGOT_PALLADIUM, ItemInit.NUGGET_PALLADIUM));
        //RecipeHelper.addMaterialStorageRecipe(new MaterialTripplet(BlockInit.BLOCK_TITANIUM, ItemInit.INGOT_TITANIUM, ItemInit.NUGGET_TITANIUM));

        RecipeHelper.addShapedOreRecipe(cloneStack(ItemInit.LOGO_KID_FLASH, 1), "TTT", "TST", "TTT", 'T', "plateTitanium", 'S', cloneStack(ItemInit.SPANDEX_CLOTH_RED, 1));

        RecipeHelper.addSmallStorageRecipe(cloneStack(ItemInit.SPANDEX_CLOTH_BLACK, 1), cloneStack(ItemInit.SPANDEX_BLACK, 1));
        RecipeHelper.addSmallStorageRecipe(cloneStack(ItemInit.SPANDEX_CLOTH_RED, 1), cloneStack(ItemInit.SPANDEX_RED, 1));
        RecipeHelper.addSmallStorageRecipe(cloneStack(ItemInit.SPANDEX_CLOTH_GREEN, 1), cloneStack(ItemInit.SPANDEX_GREEN, 1));
        RecipeHelper.addSmallStorageRecipe(cloneStack(ItemInit.SPANDEX_CLOTH_WHITE, 1), cloneStack(ItemInit.SPANDEX_WHITE, 1));
        RecipeHelper.addSmallStorageRecipe(cloneStack(ItemInit.SPANDEX_CLOTH_YELLOW, 1), cloneStack(ItemInit.SPANDEX_YELLOW, 1));

        RecipeHelper.addShapelessOreRecipe(cloneStack(ItemInit.SPANDEX_BLACK, 1), "string", "string", "string", "dyeBlack");
        RecipeHelper.addShapelessOreRecipe(cloneStack(ItemInit.SPANDEX_GREEN, 1), "string", "string", "string", "dyeGreen");
        RecipeHelper.addShapelessOreRecipe(cloneStack(ItemInit.SPANDEX_RED, 1), "string", "string", "string", "dyeRed");
        RecipeHelper.addShapelessOreRecipe(cloneStack(ItemInit.SPANDEX_WHITE, 1), "string", "string", "string", "dyeWhite");
        RecipeHelper.addShapelessOreRecipe(cloneStack(ItemInit.SPANDEX_YELLOW, 1), "string", "string", "string", "dyeYellow");

        /* smelting */
        RecipeHelper.addSmelting(BlockInit.ORE_PALLADIUM, cloneStack(ItemInit.INGOT_PALLADIUM, 1), 1F);
        RecipeHelper.addSmelting(BlockInit.ORE_TITANIUM, cloneStack(ItemInit.INGOT_TITANIUM, 1), 1F);
        RecipeHelper.addSmelting(BlockInit.ORE_VIBRANIUM, cloneStack(ItemInit.INGOT_VIBRANIUM, 1), 1F);
        RecipeHelper.addSmelting("dustPalladium", cloneStack(ItemInit.INGOT_PALLADIUM, 1), 1F);
        RecipeHelper.addSmelting("dustTitanium", cloneStack(ItemInit.INGOT_TITANIUM, 1), 1F);
        RecipeHelper.addSmelting("dustVibranium", cloneStack(ItemInit.INGOT_VIBRANIUM, 1), 1F);

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

        static void addShapedOreRecipe(ItemStack output, Object... input) {
            ResourceLocation location = getNameForRecipe(output);
            addShapedOreRecipeWithCustomName(location, output, input);
        }

        static void addShapedOreRecipeWithCustomName(ResourceLocation location, ItemStack output, Object... input) {
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

        public static void addTwoWayStorageRecipe(BlockBase block, ItemBase item) {
            if (block.hasOreDictName() && item.hasOreDictName())
                addTwoWayStorageRecipe(cloneStack(block, 1), block.getOreDictName(), cloneStack(item, 1), item.getOreDictName());
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
            if (mat.getBlock() instanceof BlockBase && mat.getIngot() instanceof ItemBase &&
                    mat.getNugget() instanceof ItemBase && ((BlockBase) mat.getBlock()).hasOreDictName() &&
                    ((ItemBase) mat.getIngot()).hasOreDictName() && ((ItemBase) mat.getNugget()).hasOreDictName()) {
                addTwoWayStorageRecipe(cloneStack(mat.getBlock(), 1), ((BlockBase) mat.getBlock()).getOreDictName(), cloneStack(mat.getIngot(), 1), ((ItemBase) mat.getIngot()).getOreDictName());
                addTwoWayStorageRecipe(cloneStack(mat.getIngot(), 1), ((ItemBase) mat.getIngot()).getOreDictName(), cloneStack(mat.getNugget(), 1), ((ItemBase) mat.getNugget()).getOreDictName());
            } else {
                addTwoWayStorageRecipe(cloneStack(mat.getBlock(), 1), cloneStack(mat.getIngot(), 1));
                addTwoWayStorageRecipe(cloneStack(mat.getIngot(), 1), cloneStack(mat.getNugget(), 1));
            }
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


        static void addArmorRecipe(Item helmet, Item chestplate, Item leggings, Item boots, ItemStack stack) {
            addShapedOreRecipe(cloneStack(helmet, 1), "XXX", "X X", 'X', stack);
            addShapedOreRecipe(cloneStack(chestplate, 1), "X X", "XXX", "XXX", 'X', stack);
            addShapedOreRecipe(cloneStack(leggings, 1), "XXX", "X X", "X X", 'X', stack);
            addShapedOreRecipe(cloneStack(boots, 1), "X X", "X X", 'X', stack);
        }
    }
}
