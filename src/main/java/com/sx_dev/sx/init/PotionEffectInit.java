package com.sx_dev.sx.init;


import com.sx_dev.sx.objects.potions.HealthBoostAdv;
import com.sx_dev.sx.objects.potions.PotionAdv;
import com.sx_dev.sx.objects.potions.StrengthAdv;
import com.sx_dev.sx.util.interfaces.IEffectProvider;
import com.sx_dev.sx.util.interfaces.IPotionEffectProvider;
import com.sx_dev.sx.util.misc.Lazy;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;

import java.util.ArrayList;
import java.util.function.Supplier;


public enum PotionEffectInit implements IPotionEffectProvider {
    //GLIDE(() -> new CustomPotionEffect("glide", false, new Color(215, 215, 215).getRGB(), new PotionEffectGlide(), new ItemStack(Items.MILK_BUCKET))),
    //INVISIBLE_GLIDE(() -> new CustomPotionEffect("inv_glide", false, new Color(215, 215, 215).getRGB(), new PotionEffectInvGlide(), new ItemStack(Items.MILK_BUCKET))),
    INVISIBLE_SPEED(() -> new PotionAdv("inv_speed", false, 8171462, false).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2).setBeneficial()),
    INVISIBLE_JUMP_BOOST(() -> new PotionAdv("inv_jump_boost", false, 2293580, false).setBeneficial()),
    INVISIBLE_HEALTH_BOOST(() -> new HealthBoostAdv("inv_health_boost", false).registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0).setBeneficial()),
    INVISIBLE_REGENERATION(() -> new PotionAdv("inv_regeneration", false, 13458603, false).setEffectiveness(0.25D).setBeneficial()),
    INVISIBLE_STRENGTH(() -> new StrengthAdv("inv_strength", false).registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0D, 0).setBeneficial());

    public static final java.util.List<PotionType> POTION_ITEMS = new ArrayList<>();
    private final Lazy<Potion> potion;

    PotionEffectInit(Supplier<Potion> supplier) {
        this.potion = Lazy.of(supplier);
    }

    @Override
    public Potion asPotionEffect() {
        return potion.get();
    }


    //public static final Potion INVISIBLE_SATURATION = new PotionHealth(false, 16262179).setPotionName("effect.saturation").setBeneficial());

    private static class PotionEffectGlide implements IEffectProvider {

        @Override
        public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
            int fallingSpeed = 2;
            if (!entityLivingBaseIn.onGround && !entityLivingBaseIn.isSneaking()) {
                double newMotionY = entityLivingBaseIn.motionY - entityLivingBaseIn.motionY / fallingSpeed;
                if (newMotionY < 0 && !entityLivingBaseIn.isElytraFlying()) {
                    entityLivingBaseIn.motionY = newMotionY;
                }
                entityLivingBaseIn.fallDistance = 0;
            }
        }

        @Override
        public boolean isVisible() {
            return true;
        }
    }

    private static class PotionEffectInvGlide extends PotionEffectGlide {

        @Override
        public boolean isVisible() {
            return false;
        }
    }
}