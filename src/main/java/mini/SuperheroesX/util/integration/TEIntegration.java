package mini.SuperheroesX.util.integration;

import mini.SuperheroesX.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.oredict.OreDictionary;

import static mini.SuperheroesX.util.handlers.RecipeHandler.cloneStack;

public class TEIntegration {

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

    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput, int chance) {
        if (!primaryInput.isEmpty() && !secondaryInput.isEmpty() && !primaryOutput.isEmpty()) {
            NBTTagCompound toSend = new NBTTagCompound();
            toSend.setInteger("energy", energy);
            toSend.setTag("primaryInput", new NBTTagCompound());
            toSend.setTag("secondaryInput", new NBTTagCompound());
            toSend.setTag("primaryOutput", new NBTTagCompound());
            primaryInput.writeToNBT(toSend.getCompoundTag("primaryInput"));
            secondaryInput.writeToNBT(toSend.getCompoundTag("secondaryInput"));
            primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
            if (!secondaryOutput.isEmpty()) {
                toSend.setTag("secondaryOutput", new NBTTagCompound());
                secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
                toSend.setInteger("chance", chance);
            }

            FMLInterModComms.sendMessage("thermalexpansion", "addsmelterrecipe", toSend);
        }
    }

}