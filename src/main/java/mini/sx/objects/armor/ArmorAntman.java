package mini.sx.objects.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorAntman extends ArmorBase {

    public ArmorAntman(String name, ItemArmor.ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot entityEquipmentSlot) {
        super(name, armorMaterial, renderIndex, entityEquipmentSlot);
    }

    public static class ChestplateAntman extends ArmorAntman {

        private float scalingFactor;

        public ChestplateAntman(String name, ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot entityEquipmentSlot) {
            super(name, armorMaterial, renderIndex, entityEquipmentSlot);
            scalingFactor = 1F;
        }

        public float getScalingFactor() {
            return scalingFactor;
        }

        public void shrink() {
            if (scalingFactor > 1) setScalingFactor(1);
            if (scalingFactor == 1) setScalingFactor(0.2F);
        }

        public void grow() {
            if (scalingFactor < 1) setScalingFactor(1);
            //else if (scalingFactor == 1) setScalingFactor(5);
        }

        private void setScalingFactor(float scalingFactor) {
            if (scalingFactor >= 0.1F && scalingFactor <= 10F) this.scalingFactor = scalingFactor;
        }
    }
}
