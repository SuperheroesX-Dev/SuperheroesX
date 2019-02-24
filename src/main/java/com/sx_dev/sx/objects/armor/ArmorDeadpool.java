package com.sx_dev.sx.objects.armor;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.PotionEffectInit;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.sx_dev.sx.init.ItemInit.ARMOR_DEADPOOL;

public class ArmorDeadpool extends ArmorBase {

    public ArmorDeadpool(String name, EntityEquipmentSlot equipmentSlotIn) {
        super(name, ARMOR_DEADPOOL, equipmentSlotIn, new Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MARVEL));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(stack, world, player);
            player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(PotionEffectInit.INVISIBLE_STRENGTH.asPotionEffect(), 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5, 3, true, false));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        ModelBiped armorModel = null;

        if (itemStack != null) {
            armorModel = new ModelBiped(0.5F);

            if (armorModel != null) {
                armorModel.setModelAttributes(_default);
                return armorModel;
            }
        }

        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }
}