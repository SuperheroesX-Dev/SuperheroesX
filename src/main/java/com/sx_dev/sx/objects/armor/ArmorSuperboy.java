package com.sx_dev.sx.objects.armor;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.PotionInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import static com.sx_dev.sx.init.ItemInit.ARMOR_SUPERBOY;

public class ArmorSuperboy extends ArmorBase {
    public ArmorSuperboy(String name, EntityEquipmentSlot equipmentSlotIn) {
        super(name, ARMOR_SUPERBOY, equipmentSlotIn, new Properties().group(SuperheroesX.SUPERHEROES_X_TAB_DC));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(stack, world, player);

            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3, 1, false, false));
            player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 3, 4, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 3, 2, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 3, 2, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3, 4, false, false));
        }
    }
}
