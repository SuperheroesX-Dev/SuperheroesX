package mini.sx.objects.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ArmorAntman extends ArmorBase {

    public ArmorAntman(String name, ItemArmor.ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot entityEquipmentSlot) {
        super(name, armorMaterial, renderIndex, entityEquipmentSlot);
    }

    public static class ChestplateAntman extends ArmorAntman {

        private float scalingFactor;
        private boolean flag;

        public ChestplateAntman(String name, ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot entityEquipmentSlot) {
            super(name, armorMaterial, renderIndex, entityEquipmentSlot);
            scalingFactor = 0.5F;
            flag = true;
        }

        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            super.onArmorTick(world, player, stack);
            player.eyeHeight = player.getDefaultEyeHeight() * scalingFactor;
            player.setEntityBoundingBox(player.getRenderBoundingBox());
            if (flag) {
                flag = false;
                player.height *= scalingFactor;
            }
        }

        public float getScalingFactor() {
            return scalingFactor;
        }
    }
}
