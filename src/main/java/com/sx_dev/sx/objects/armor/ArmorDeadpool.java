package com.sx_dev.sx.objects.armor;

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

public class ArmorDeadpool extends ArmorBase {

    public ArmorDeadpool(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(stack, world, player);
            player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5, 2, true, false));
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