package mini.SuperheroesX.util.integration;

import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

import static mini.SuperheroesX.util.handlers.RecipeHandler.cloneStack;

@SuppressWarnings({"WeakerAccess", "unused", "Duplicates", "SpellCheckingInspection"})
public final class TEIntegration {

    public static final String ADD_FURNACE_RECIPE = "addfurnacerecipe";
    public static final String ADD_PULVERIZER_RECIPE = "addpulverizerrecipe";
    public static final String ADD_SAWMILL_RECIPE = "addsawmillrecipe";
    public static final String ADD_SMELTER_RECIPE = "addsmelterrecipe";
    public static final String ADD_INSOLATOR_RECIPE = "addinsolatorrecipe";
    public static final String ADD_COMPACTOR_RECIPE = "addcompactorrecipe";
    public static final String ADD_COMPACTOR_PLATE_RECIPE = "addcompactorplaterecipe";
    public static final String ADD_COMPACTOR_COIN_RECIPE = "addcompactorcoinrecipe";
    public static final String ADD_COMPACTOR_GEAR_RECIPE = "addcompactorgearrecipe";
    public static final String ADD_CRUCIBLE_RECIPE = "addcruciblerecipe";
    public static final String ADD_REFINERY_RECIPE = "addrefineryrecipe";
    public static final String ADD_TRANSPOSER_FILL_RECIPE = "addtransposerfillrecipe";
    public static final String ADD_TRANSPOSER_EXTRACT_RECIPE = "addtransposerextractrecipe";
    public static final String ADD_CHARGER_RECIPE = "addchargerrecipe";
    public static final String ADD_CENTRIFUGE_RECIPE = "addcentrifugerecipe";
    public static final String ADD_BREWER_RECIPE = "addbrewerrecipe";
    public static final String ADD_ENCHANTER_RECIPE = "addenchanterrecipe";
    public static final String REMOVE_FURNACE_RECIPE = "removefurnacerecipe";
    public static final String REMOVE_PULVERIZER_RECIPE = "removepulverizerrecipe";
    public static final String REMOVE_SAWMILL_RECIPE = "removesawmillrecipe";
    public static final String REMOVE_SMELTER_RECIPE = "removesmelterrecipe";
    public static final String REMOVE_INSOLATOR_RECIPE = "removeinsolatorrecipe";
    public static final String REMOVE_COMPACTOR_RECIPE = "removecompactorrecipe";
    public static final String REMOVE_COMPACTOR_PLATE_RECIPE = "removecompactorplaterecipe";
    public static final String REMOVE_COMPACTOR_COIN_RECIPE = "removecompactorcoinrecipe";
    public static final String REMOVE_COMPACTOR_GEAR_RECIPE = "removecompactorgearrecipe";
    public static final String REMOVE_CRUCIBLE_RECIPE = "removecruciblerecipe";
    public static final String REMOVE_REFINERY_RECIPE = "removerefineryrecipe";
    public static final String REMOVE_TRANSPOSER_FILL_RECIPE = "removetransposerfillrecipe";
    public static final String REMOVE_TRANSPOSER_EXTRACT_RECIPE = "removetransposerextractrecipe";
    public static final String REMOVE_CHARGER_RECIPE = "removechargerrecipe";
    public static final String REMOVE_CENTRIFUGE_RECIPE = "removecentrifugerecipe";
    public static final String REMOVE_BREWER_RECIPE = "removebrewerrecipe";
    public static final String REMOVE_ENCHANTER_RECIPE = "removeenchanterrecipe";
    static final String ENERGY = "energy";
    static final String EXPERIENCE = "experience";
    static final String FLUID = "fluid";
    static final String REVERSIBLE = "reversible";
    static final String CHANCE = "chance";
    static final String INPUT = "input";
    static final String OUTPUT = "output";
    static final String INPUT_2 = "input2";
    static final String OUTPUT_2 = "output2";


    public static void addTERecipes() {
        addOredictSmelterRecipe(4000, "ingotTitanium", "ingotGold", cloneStack(ItemInit.INGOT_TITANOGOLD, 1));
    }

    public static void addOredictSmelterRecipe(int energy, String input1, String input2, ItemStack output) {
        for (ItemStack stack1 : OreDictionary.getOres(input1)) {
            for (ItemStack stack2 : OreDictionary.getOres(input2)) {
                addSmelterRecipe(energy, cloneStack(stack1, 1), cloneStack(stack2, 1), output, ItemStack.EMPTY, 0);
            }
        }
    }

    /* FURNACE */
    public static void addFurnaceRecipe(int energy, ItemStack input, ItemStack output) {

        if (input.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));
        FMLInterModComms.sendMessage(Reference.MODID, ADD_FURNACE_RECIPE, toSend);
    }

    public static void removeFurnaceRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_FURNACE_RECIPE, toSend);
    }

    /* PULVERIZER */
    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack output) {

        addPulverizerRecipe(energy, input, output, ItemStack.EMPTY, 0);
    }

    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack output, ItemStack output2) {

        addPulverizerRecipe(energy, input, output, output2, 100);
    }

    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack output, ItemStack output2, int chance) {

        if (input.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));

        if (!output2.isEmpty()) {
            toSend.setTag(OUTPUT_2, new NBTTagCompound());
            output2.writeToNBT(toSend.getCompoundTag(OUTPUT_2));
            toSend.setInteger(CHANCE, chance);
        }
        FMLInterModComms.sendMessage(Reference.MODID, ADD_PULVERIZER_RECIPE, toSend);
    }

    public static void removePulverizerRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_PULVERIZER_RECIPE, toSend);
    }

    /* SAWMILL */
    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack output) {

        addSawmillRecipe(energy, input, output, ItemStack.EMPTY, 0);
    }

    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack output, ItemStack output2) {

        addSawmillRecipe(energy, input, output, output2, 100);
    }

    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack output, ItemStack output2, int chance) {

        if (input.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));

        if (!output2.isEmpty()) {
            toSend.setTag(OUTPUT_2, new NBTTagCompound());
            output2.writeToNBT(toSend.getCompoundTag(OUTPUT_2));
            toSend.setInteger(CHANCE, chance);
        }
        FMLInterModComms.sendMessage(Reference.MODID, ADD_SAWMILL_RECIPE, toSend);
    }

    public static void removeSawmillRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_SAWMILL_RECIPE, toSend);
    }

    /* SMELTER */
    public static void addSmelterRecipe(int energy, ItemStack input, ItemStack input2, ItemStack output) {

        addSmelterRecipe(energy, input, input2, output, ItemStack.EMPTY, 0);
    }

    public static void addSmelterRecipe(int energy, ItemStack input, ItemStack input2, ItemStack output, ItemStack output2) {

        addSmelterRecipe(energy, input, input2, output, output2, 100);
    }

    public static void addSmelterRecipe(int energy, ItemStack input, ItemStack input2, ItemStack output, ItemStack output2, int chance) {

        if (input.isEmpty() || input2.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(INPUT_2, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        input2.writeToNBT(toSend.getCompoundTag(INPUT_2));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));

        if (!output2.isEmpty()) {
            toSend.setTag(OUTPUT_2, new NBTTagCompound());
            output2.writeToNBT(toSend.getCompoundTag(OUTPUT_2));
            toSend.setInteger(CHANCE, chance);
        }
        FMLInterModComms.sendMessage(Reference.MODID, ADD_SMELTER_RECIPE, toSend);
    }

    public static void removeSmelterRecipe(ItemStack input, ItemStack input2) {

        if (input.isEmpty() || input2.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(INPUT_2, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        input2.writeToNBT(toSend.getCompoundTag(INPUT_2));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_SMELTER_RECIPE, toSend);
    }

    /* INSOLATOR */
    public static void addInsolatorRecipe(int energy, ItemStack input, ItemStack input2, ItemStack output) {

        addInsolatorRecipe(energy, input, input2, output, ItemStack.EMPTY, 0);
    }

    public static void addInsolatorRecipe(int energy, ItemStack input, ItemStack input2, ItemStack output, ItemStack output2) {

        addInsolatorRecipe(energy, input, input2, output, output2, 100);
    }

    public static void addInsolatorRecipe(int energy, ItemStack input, ItemStack input2, ItemStack output, ItemStack output2, int chance) {

        if (input.isEmpty() || input2.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(INPUT_2, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        input2.writeToNBT(toSend.getCompoundTag(INPUT_2));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));

        if (!output2.isEmpty()) {
            toSend.setTag(OUTPUT_2, new NBTTagCompound());
            output2.writeToNBT(toSend.getCompoundTag(OUTPUT_2));
            toSend.setInteger(CHANCE, chance);
        }
        FMLInterModComms.sendMessage(Reference.MODID, ADD_INSOLATOR_RECIPE, toSend);
    }

    public static void removeInsolatorRecipe(ItemStack input, ItemStack input2) {

        if (input.isEmpty() || input2.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(INPUT_2, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        input2.writeToNBT(toSend.getCompoundTag(INPUT_2));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_INSOLATOR_RECIPE, toSend);
    }

    /* COMPACTOR */
    public static void addCompactorRecipe(int energy, ItemStack input, ItemStack output) {

        if (input.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));
        FMLInterModComms.sendMessage(Reference.MODID, ADD_COMPACTOR_RECIPE, toSend);
    }

    public static void addCompactorPlateRecipe(int energy, ItemStack input, ItemStack output) {

        if (input.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));
        FMLInterModComms.sendMessage(Reference.MODID, ADD_COMPACTOR_PLATE_RECIPE, toSend);
    }

    public static void addCompactorCoinRecipe(int energy, ItemStack input, ItemStack output) {

        if (input.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));
        FMLInterModComms.sendMessage(Reference.MODID, ADD_COMPACTOR_COIN_RECIPE, toSend);
    }

    public static void addCompactorGearRecipe(int energy, ItemStack input, ItemStack output) {

        if (input.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));
        FMLInterModComms.sendMessage(Reference.MODID, ADD_COMPACTOR_GEAR_RECIPE, toSend);
    }

    public static void removeCompactorRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_COMPACTOR_RECIPE, toSend);
    }

    public static void removeCompactorPlateRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_COMPACTOR_PLATE_RECIPE, toSend);
    }

    public static void removeCompactorCoinRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_COMPACTOR_COIN_RECIPE, toSend);
    }

    public static void removeCompactorGearRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_COMPACTOR_GEAR_RECIPE, toSend);
    }

    /* CRUCIBLE */
    public static void addCrucibleRecipe(int energy, ItemStack input, FluidStack output) {

        if (input.isEmpty() || output == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));

        FMLInterModComms.sendMessage(Reference.MODID, ADD_CRUCIBLE_RECIPE, toSend);
    }

    public static void removeCrucibleRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_CRUCIBLE_RECIPE, toSend);
    }

    /* REFINERY */
    public static void addRefineryRecipe(int energy, FluidStack input, FluidStack output, ItemStack outputItem) {

        if (input == null || output == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        if (!outputItem.isEmpty()) {
            toSend.setTag(OUTPUT_2, new NBTTagCompound());
            outputItem.writeToNBT(toSend.getCompoundTag(OUTPUT_2));
        }
        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));

        FMLInterModComms.sendMessage(Reference.MODID, ADD_REFINERY_RECIPE, toSend);
    }

    public static void removeRefineryRecipe(FluidStack input) {

        if (input == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_REFINERY_RECIPE, toSend);
    }

    /* TRANSPOSER */
    public static void addTransposerFill(int energy, ItemStack input, ItemStack output, FluidStack fluid, boolean reversible) {

        if (input.isEmpty() || output.isEmpty() || fluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());
        toSend.setTag(FLUID, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));
        toSend.setBoolean(REVERSIBLE, reversible);
        fluid.writeToNBT(toSend.getCompoundTag(FLUID));

        FMLInterModComms.sendMessage(Reference.MODID, ADD_TRANSPOSER_FILL_RECIPE, toSend);
    }

    public static void addTransposerExtract(int energy, ItemStack input, ItemStack output, FluidStack fluid, int chance, boolean reversible) {

        if (input.isEmpty() || output.isEmpty() || fluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());
        toSend.setTag(FLUID, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));
        toSend.setBoolean(REVERSIBLE, reversible);
        toSend.setInteger(CHANCE, chance);
        fluid.writeToNBT(toSend.getCompoundTag(FLUID));

        FMLInterModComms.sendMessage(Reference.MODID, ADD_TRANSPOSER_EXTRACT_RECIPE, toSend);
    }

    public static void removeTransposerFill(ItemStack input, FluidStack fluid) {

        if (input.isEmpty() || fluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(FLUID, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        fluid.writeToNBT(toSend.getCompoundTag(FLUID));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_TRANSPOSER_FILL_RECIPE, toSend);
    }

    public static void removeTransposerExtract(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_TRANSPOSER_EXTRACT_RECIPE, toSend);
    }

    /* CHARGER */
    public static void addChargerRecipe(int energy, ItemStack input, ItemStack output) {

        if (input.isEmpty() || output.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));
        FMLInterModComms.sendMessage(Reference.MODID, ADD_CHARGER_RECIPE, toSend);
    }

    public static void removeChargerRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_CHARGER_RECIPE, toSend);
    }

    /* CENTRIFUGE */
    public static void addCentrifugeRecipe(int energy, ItemStack input, List<ItemStack> output) {

        addCentrifugeRecipe(energy, input, output, new ArrayList<>(), null);
    }

    public static void addCentrifugeRecipe(int energy, ItemStack input, List<ItemStack> output, List<Integer> chance) {

        addCentrifugeRecipe(energy, input, output, chance, null);
    }

    public static void addCentrifugeRecipe(int energy, ItemStack input, FluidStack fluid) {

        addCentrifugeRecipe(energy, input, new ArrayList<>(), new ArrayList<>(), fluid);
    }

    public static void addCentrifugeRecipe(int energy, ItemStack input, List<ItemStack> output, List<Integer> chance, FluidStack fluid) {

        if (input.isEmpty() || output.size() > 4 || chance.size() > 4 || (!chance.isEmpty() && chance.size() != output.size()) || output.isEmpty() && fluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));

        if (!output.isEmpty()) {
            NBTTagList list = new NBTTagList();

            for (int i = 0; i < output.size(); i++) {
                NBTTagCompound tag = new NBTTagCompound();
                output.get(i).writeToNBT(tag);
                if (!chance.isEmpty()) {
                    tag.setInteger(CHANCE, chance.get(i));
                }
                list.appendTag(tag);
            }
            toSend.setTag(OUTPUT, list);
        }
        if (fluid != null) {
            toSend.setTag(FLUID, new NBTTagCompound());
            fluid.writeToNBT(toSend.getCompoundTag(FLUID));
        }
        FMLInterModComms.sendMessage(Reference.MODID, ADD_CENTRIFUGE_RECIPE, toSend);
    }

    public static void removeCentrifugeRecipe(ItemStack input) {

        if (input.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_CENTRIFUGE_RECIPE, toSend);
    }

    /* BREWER */
    public static void addBrewerRecipe(int energy, ItemStack input, FluidStack inputFluid, FluidStack outputFluid) {

        if (input.isEmpty() || inputFluid == null || outputFluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(INPUT_2, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        inputFluid.writeToNBT(toSend.getCompoundTag(INPUT_2));
        outputFluid.writeToNBT(toSend.getCompoundTag(OUTPUT));

        FMLInterModComms.sendMessage(Reference.MODID, ADD_BREWER_RECIPE, toSend);
    }

    public static void removeBrewerRecipe(ItemStack input, FluidStack fluid) {

        if (input.isEmpty() || fluid == null) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(FLUID, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        fluid.writeToNBT(toSend.getCompoundTag(FLUID));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_BREWER_RECIPE, toSend);
    }

    /* ENCHANTER */
    public static void addEnchanterRecipe(int energy, ItemStack input, ItemStack input2, ItemStack output, int fluidExp) {

        if (input.isEmpty() || input2.isEmpty() || output.isEmpty() || fluidExp < 0) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger(ENERGY, energy);
        toSend.setInteger(EXPERIENCE, fluidExp);
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(INPUT_2, new NBTTagCompound());
        toSend.setTag(OUTPUT, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        input2.writeToNBT(toSend.getCompoundTag(INPUT_2));
        output.writeToNBT(toSend.getCompoundTag(OUTPUT));

        FMLInterModComms.sendMessage(Reference.MODID, ADD_ENCHANTER_RECIPE, toSend);
    }

    public static void removeEnchanterRecipe(ItemStack input, ItemStack input2) {

        if (input.isEmpty() || input2.isEmpty()) {
            return;
        }
        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setTag(INPUT, new NBTTagCompound());
        toSend.setTag(INPUT_2, new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag(INPUT));
        input2.writeToNBT(toSend.getCompoundTag(INPUT_2));
        FMLInterModComms.sendMessage(Reference.MODID, REMOVE_ENCHANTER_RECIPE, toSend);
    }

}
