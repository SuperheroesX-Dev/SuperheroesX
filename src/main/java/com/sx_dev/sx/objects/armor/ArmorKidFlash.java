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

import static com.sx_dev.sx.init.ItemInit.ARMOR_KIDFLASH;

public class ArmorKidFlash extends ArmorBase {

    public ArmorKidFlash(String name, EntityEquipmentSlot equipmentSlotIn) {
        super(name, ARMOR_KIDFLASH, equipmentSlotIn, new Properties().group(SuperheroesX.SUPERHEROES_X_TAB_DC));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(stack, world, player);
            if (player.isSprinting()) {
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 50, false, false));
                //world.spawnParticle(Particles.DRAGON_BREATH, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 1, 1, 0);
            }
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, 4, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 0, 2, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectInit.INVISIBLE_STRENGTH.asPotionEffect(), 0, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 0, 2, true, false));
        }
        if (player.isInWater()) {
            player.motionY = 0.2;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        ModelBiped armorModel;

        if (itemStack != null) {
            armorModel = new ModelBiped(0.5F);

            armorModel.setModelAttributes(_default);
            return armorModel;
        }

        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }

}
