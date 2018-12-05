package mini.sx.objects.armor;

import mini.sx.SuperheroesX;
import mini.sx.init.ItemInit;
import mini.sx.util.interfaces.IHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorBase extends ItemArmor implements IHasModel {

    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
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
    @SideOnly(Side.CLIENT)
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
        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
	}

    public static class Set {

        public final ArmorBase helmet, chestplate, leggings, boots;

        public Set(String name, ArmorMaterial material) {
            helmet = new ArmorBase("helmet_" + name, material, 1, EntityEquipmentSlot.HEAD);
            chestplate = new ArmorBase("chestplate_" + name, material, 1, EntityEquipmentSlot.CHEST);
            leggings = new ArmorBase("leggings_" + name, material, 2, EntityEquipmentSlot.LEGS);
            boots = new ArmorBase("boots_" + name, material, 1, EntityEquipmentSlot.FEET);
        }

        public Set setCreativeTab(CreativeTabs tab) {
            this.helmet.setCreativeTab(tab);
            this.chestplate.setCreativeTab(tab);
            this.leggings.setCreativeTab(tab);
            this.boots.setCreativeTab(tab);
            return this;
        }

        public Set setRepairItem(ItemStack item) {
            helmet.getArmorMaterial().setRepairItem(item);
            return this;
        }
    }

}