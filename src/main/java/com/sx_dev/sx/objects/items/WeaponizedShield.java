package com.sx_dev.sx.objects.items;

import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

@SuppressWarnings("Duplicates")
public class WeaponizedShield extends ShieldBase {

    private final double attackDamage;
    private final double attackSpeed;

    public WeaponizedShield(String name, int blockTime, double attackDamage, double attackSpeed, int enchantability, int maxDamage, String repairIngot, ItemGroup group) {
        super(name, blockTime, enchantability, maxDamage, repairIngot, group);
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.attackSpeed, 0));
        }

        return multimap;
    }
}
