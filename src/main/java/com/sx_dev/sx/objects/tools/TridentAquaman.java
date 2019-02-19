package com.sx_dev.sx.objects.tools;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTrident;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

public class TridentAquaman extends ItemTrident {
    public TridentAquaman() {
        super(new Properties().group(SuperheroesX.SUPERHEROES_X_TAB_DC).defaultMaxDamage(0).rarity(EnumRarity.RARE));
        setRegistryName("trident_aquaman");

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        super.onCreated(stack, world, player);
        stack.addEnchantment(Enchantments.LOYALTY, 1);
        stack.addEnchantment(Enchantments.IMPALING, 3);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> stacks) {
        if (this.isInGroup(group)) {
            ItemStack stack = new ItemStack(this);
            stack.addEnchantment(Enchantments.LOYALTY, 1);
            stack.addEnchantment(Enchantments.IMPALING, 3);
            stacks.add(stack);
        }
    }

    @Override
    public boolean isDamageable() {
        return false;
    }
}
