package mini.sx.objects.armor;

import mini.sx.SuperheroesX;
import mini.sx.init.PotionInit;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ArmorCaptainAmerica extends ArmorBase {

    public ArmorCaptainAmerica(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
	}

	@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(world, player, stack);
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 1, true, false));
            player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 10, 4, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 10, 2, true, false));
        }
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
}