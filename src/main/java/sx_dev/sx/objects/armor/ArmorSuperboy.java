package sx_dev.sx.objects.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import sx_dev.sx.init.PotionInit;

public class ArmorSuperboy extends ArmorBase {
    public ArmorSuperboy(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(world, player, stack);

            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3, 1, false, false));
            player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 3, 4, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 3, 2, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 3, 2, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3, 4, false, false));
        }
    }
}
