package sx_dev.sx.objects.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sx_dev.sx.SuperheroesX;
import sx_dev.sx.init.PotionInit;

public class ArmorKidFlash extends ArmorBase {

    public ArmorKidFlash(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(world, player, stack);
            if (player.isSprinting()) {
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 50, false, false));
                world.spawnParticle(EnumParticleTypes.REDSTONE, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 1, 1, 0);
            }
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, 4, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 0, 2, false, false));
            player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 0, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 0, 2, true, false));
        }
        if (player.isInWater()) {
            player.motionY = 0.2;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        ModelBiped armorModel;

        if (itemStack != null) {
            armorModel = new ModelBiped(0.5F);

            armorModel.setModelAttributes(_default);
            return armorModel;
        }

        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }

    @Override
    public void registerModels() {
        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
    }

}
