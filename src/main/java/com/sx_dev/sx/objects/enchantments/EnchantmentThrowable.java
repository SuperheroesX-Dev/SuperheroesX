package com.sx_dev.sx.objects.enchantments;

import com.sx_dev.sx.init.EnchantmentInit;
import com.sx_dev.sx.util.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentThrowable extends Enchantment {

    public EnchantmentThrowable(String name) {
        super(Rarity.RARE, EnchantmentInit.THROWABLE_SHIELD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        //this.setName(name);
        this.setRegistryName(new ResourceLocation(Reference.MODID, name));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }
}
