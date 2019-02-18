package com.sx_dev.sx.objects.armor;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.PotionInit;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class ArmorRedHood extends ArmorBase {

    public ArmorRedHood(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn, new Properties().group(SuperheroesX.SUPERHEROES_X_TAB_DC));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(stack, world, player);

            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1, 1, true, false));
            player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 1, 4, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 1, 2, true, false));
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