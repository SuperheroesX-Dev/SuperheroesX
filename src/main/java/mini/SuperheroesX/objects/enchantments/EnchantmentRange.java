package mini.SuperheroesX.objects.enchantments;

import mini.SuperheroesX.init.EnchantmentInit;
import mini.SuperheroesX.util.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentRange extends Enchantment {

    public EnchantmentRange() {
        super(Rarity.RARE, EnchantmentInit.THROWABLE_SHIELD, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        this.setName("devils_feet");
        this.setRegistryName(new ResourceLocation(Reference.RESOURCE_PREFIX + "throwing_range"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }
}
