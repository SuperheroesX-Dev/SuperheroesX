package com.sx_dev.sx.objects.armor;

import com.sx_dev.sx.SuperheroesX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ArmorAntman extends ArmorBase {

    Type type;

    public ArmorAntman(String name, ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot entityEquipmentSlot, Type type) {
        super(name, armorMaterial, renderIndex, entityEquipmentSlot, new Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MARVEL));
        this.type = type;
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
    public boolean isWearingFullSet(EntityPlayer player) {
        for (ItemStack i : player.getArmorInventoryList()) {
            if (!(i.getItem() instanceof ArmorAntman) || i.getItem().getRegistryName().toString().contains(this.type.toString().toLowerCase()))
                return false;
        }
        return true;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }


    public static class ChestplateAntman extends ArmorAntman {

        private static final float SCALING_FACTOR_SMALL = 0.2F;
        private static final float SCALING_FACTOR_NORMAL = 1F;
        private static final float SCALING_FACTOR_LARGE = 5F;
        private static final int SCALING_FACTOR_SMALL_PERCENT = (int) (SCALING_FACTOR_SMALL * 100);
        private static final int SCALING_FACTOR_NORMAL_PERCENT = (int) (SCALING_FACTOR_NORMAL * 100);
        private static final int SCALING_FACTOR_LARGE_PERCENT = (int) (SCALING_FACTOR_LARGE * 100);
        private float scalingFactor;

        private int timer = 0;

        /**
         * @param name                the registry name of the item
         * @param armorMaterial       the armor material
         * @param renderIndex         the render index of the armor item
         * @param entityEquipmentSlot the equipment slot of the armor item
         */
        public ChestplateAntman(String name, ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot entityEquipmentSlot, Type type) {
            super(name, armorMaterial, renderIndex, entityEquipmentSlot, type);
            this.scalingFactor = SCALING_FACTOR_NORMAL;
        }

        /**
         * Get the scaling factor for the player wearing this Chestplate
         *
         * @return the scaling factor
         */
        public float getScalingFactor() {
            return scalingFactor;
        }

        /**
         * Set the scaling factor
         *
         * @param scalingFactor the scaling factor
         */
        private void setScalingFactor(float scalingFactor) {
            if (scalingFactor >= SCALING_FACTOR_SMALL && scalingFactor <= SCALING_FACTOR_LARGE)
                this.scalingFactor = scalingFactor;
        }

        /**
         * Shrink the player to the next smaller state
         */
        public void shrink() {
            if (this.scalingFactor > SCALING_FACTOR_NORMAL) setScalingFactor(SCALING_FACTOR_NORMAL);
            else if (this.scalingFactor == SCALING_FACTOR_NORMAL) setScalingFactor(SCALING_FACTOR_SMALL);
        }


        /*public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (!(entityIn instanceof EntityPlayer) || !((EntityPlayer) entityIn).capabilities.allowFlying) return;
            EntityPlayer player = (EntityPlayer) entityIn;
            if (!isWearingFullSet(player) || stack.getItemDamage() != 1) {
                player.capabilities.allowFlying = player.isCreative();
                player.capabilities.isFlying = player.isCreative() && player.capabilities.isFlying;
            }
        }*/

        /**
         * Let the player grow to the next bigger state
         */
        public void grow() {
            if (this.scalingFactor < SCALING_FACTOR_NORMAL) setScalingFactor(SCALING_FACTOR_NORMAL);
            //else if (scalingFactor == SCALING_FACTOR_NORMAL) setScalingFactor(SCALING_FACTOR_LARGE);
        }

        /**
         * Tick handling for the armor
         *
         * @param world     the world
         * @param player    the player {@link net.minecraft.entity.player.EntityPlayer}
         * @param stack     the {@link net.minecraft.item.ItemStack} of the ticking item
         */
        @Override
        public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
            if (isWearingFullSet(player) && this.type == Type.THE_WASP) {
                //player.capabilities.allowFlying = true;
            } else {
                //player.capabilities.allowFlying = player.isCreative();
                //player.capabilities.isFlying = player.isCreative();
            }
            switch (Math.round(this.scalingFactor * 100)) {
                case SCALING_FACTOR_SMALL_PERCENT:
                    player.abilities.setWalkSpeed(1F);
                    break;
                case SCALING_FACTOR_LARGE_PERCENT:
                    player.abilities.setWalkSpeed(0.01F);
                    if (timer > 20) {
                        timer = 0;
                        player.getFoodStats().addExhaustion(30);
                    }
                    timer++;
                    break;
                case SCALING_FACTOR_NORMAL_PERCENT:
                default:
                    player.abilities.setWalkSpeed(0.1F);
                    break;
            }
        }
    }

    public enum Type{
        THE_WASP,
        ANTMAN
    }
}
