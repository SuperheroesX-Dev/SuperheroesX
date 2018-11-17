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
            scalingFactor = 0.5F;
        }

        public float getScalingFactor() {
            return scalingFactor;
        }
    }
}
