package com.sx_dev.sx.objects.armor;

import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.util.interfaces.IHasModel;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArmorBase extends ItemArmor implements IHasModel {

    public ArmorBase(String name, IArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn, Properties properties) {
        super(materialIn, equipmentSlotIn, properties);
		//setUnlocalizedName(name);
		setRegistryName(name);

		ItemInit.ITEMS.add(this);

	}

    protected boolean isWearingFullSet(EntityPlayer player) {
        for (ItemStack i : player.getArmorInventoryList())
            if (i.getItem().getClass() != this.getClass())
                return false;
        return true;
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

	@Override
    public void registerModels() {
        //SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
	}
}