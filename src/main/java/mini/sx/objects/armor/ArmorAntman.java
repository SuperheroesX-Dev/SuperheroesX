package mini.sx.objects.armor;

import mini.sx.SuperheroesX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ArmorAntman extends ArmorBase {

    public ArmorAntman(String name, ItemArmor.ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot entityEquipmentSlot) {
        super(name, armorMaterial, renderIndex, entityEquipmentSlot);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return getMaxDamage(stack);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this, 1, 0));
            items.add(new ItemStack(this, 1, 1));
        }
    }

    @Override
    public void registerModels() {
        SuperheroesX.PROXY.registerVariantRenderer(this, 0, "armor_antman", "inventory");
        SuperheroesX.PROXY.registerVariantRenderer(this, 1, "armor_wasp", "inventory");
    }

    @Override
    public boolean isWearingFullSet(EntityPlayer player) {
        int meta = -1;
        for (ItemStack i : player.getArmorInventoryList()) {
            if (!(i.getItem() instanceof ArmorAntman))
                return false;
            else if (meta == -1)
                meta = i.getItemDamage();
            else if (meta != i.getItemDamage())
                return false;
        }
        return true;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "_" + getDamage(stack);
    }

    public static class ChestplateAntman extends ArmorAntman {

        private static final float SCALING_FACTOR_SMALL = 0.2F;
        private static final float SCALING_FACTOR_NORMAL = 1F;
        private static final float SCALING_FACTOR_LARGE = 5F;
        private static final int SCALING_FACTOR_SMALL_PERCENT = (int) (SCALING_FACTOR_SMALL * 100);
        private static final int SCALING_FACTOR_NORMAL_PERCENT = (int) (SCALING_FACTOR_NORMAL * 100);
        private static final int SCALING_FACTOR_LARGE_PERCENT = (int) (SCALING_FACTOR_LARGE * 100);
        private float scalingFactor;

        public ChestplateAntman(String name, ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot entityEquipmentSlot) {
            super(name, armorMaterial, renderIndex, entityEquipmentSlot);
            this.scalingFactor = SCALING_FACTOR_NORMAL;
        }

        public float getScalingFactor() {
            return scalingFactor;
        }

        public void shrink() {
            if (this.scalingFactor > SCALING_FACTOR_NORMAL) setScalingFactor(SCALING_FACTOR_NORMAL);
            else if (this.scalingFactor == SCALING_FACTOR_NORMAL) setScalingFactor(SCALING_FACTOR_SMALL);
        }

        public void grow() {
            if (this.scalingFactor < SCALING_FACTOR_NORMAL) setScalingFactor(SCALING_FACTOR_NORMAL);
            //else if (scalingFactor == SCALING_FACTOR_NORMAL) setScalingFactor(SCALING_FACTOR_LARGE);
        }

        private void setScalingFactor(float scalingFactor) {
            if (scalingFactor >= SCALING_FACTOR_SMALL && scalingFactor <= SCALING_FACTOR_LARGE)
                this.scalingFactor = scalingFactor;
        }

        @Override
        public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!(entityIn instanceof EntityPlayer) || !((EntityPlayer) entityIn).capabilities.allowFlying) return;
            EntityPlayer player = (EntityPlayer) entityIn;
            if (!isWearingFullSet(player) || stack.getItemDamage() != 1) {
                player.capabilities.allowFlying = player.isCreative();
                player.capabilities.isFlying = player.isCreative() && player.capabilities.isFlying;
            }
        }

        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
            if (isWearingFullSet(player) && itemStack.getItemDamage() == 1) {
                player.capabilities.allowFlying = true;
            } else {
                player.capabilities.allowFlying = player.isCreative();
                player.capabilities.isFlying = player.isCreative();
            }
            switch (Math.round(this.scalingFactor * 10)) {
                case SCALING_FACTOR_SMALL_PERCENT:
                    player.capabilities.setPlayerWalkSpeed(1F);
                    break;
                case SCALING_FACTOR_LARGE_PERCENT:
                    player.capabilities.setPlayerWalkSpeed(0.01F);
                    break;
                case SCALING_FACTOR_NORMAL_PERCENT:
                default:
                    player.capabilities.setPlayerWalkSpeed(0.1F);
                    break;
            }
        }
    }
}
