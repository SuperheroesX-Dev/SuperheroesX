package com.sx_dev.sx.objects.tools;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTrident;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

public class TridentAquaman extends ItemTrident {
    private boolean first = true;

    public TridentAquaman() {
        super(new Properties().group(SuperheroesX.SUPERHEROES_X_TAB_DC).defaultMaxDamage(1).rarity(EnumRarity.RARE));
        setRegistryName(Reference.MODID, "trident_aquaman");
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        super.onCreated(stack, world, player);
        init(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return super.getUseDuration(stack) / 2;
    }

    private void init(ItemStack stack) {
        stack.addEnchantment(Enchantments.LOYALTY, 10);
        stack.addEnchantment(Enchantments.IMPALING, 10);
        NBTTagCompound tag = stack.getTag();
        if (tag == null) tag = new NBTTagCompound();
        tag.putBoolean("Unbreakable", true);
        stack.setTag(tag);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean selected) {
        super.inventoryTick(stack, world, entity, itemSlot, selected);
        if (first) {
            first = false;
            init(stack);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> stacks) {
        if (this.isInGroup(group)) {
            ItemStack stack = new ItemStack(this);
            init(stack);
            stacks.add(stack);
        }
    }
}
