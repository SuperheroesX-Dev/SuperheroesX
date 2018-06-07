package mini.SuperheroesX.init;


import mini.SuperheroesX.objects.potions.CustomPotionEffect;
import mini.SuperheroesX.objects.potions.HealthBoostAdv;
import mini.SuperheroesX.objects.potions.PotionAdv;
import mini.SuperheroesX.objects.potions.StrengthAdv;
import mini.SuperheroesX.util.interfaces.IEffectProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;

import java.awt.*;
import java.util.ArrayList;





public class PotionInit {

    public static final java.util.List<Potion> POTIONS = new ArrayList<>();
    public static final java.util.List<PotionType> POTION_ITEMS = new ArrayList<>();



    public static final Potion GLIDE = new CustomPotionEffect("glide", false,
            new Color(215, 215, 215).getRGB(), new PotionEffectGlide(),
            new ItemStack(Items.MILK_BUCKET)
    );

    public static final Potion INV_GLIDE = new CustomPotionEffect("inv_glide", false,
            new Color(215, 215, 215).getRGB(), new PotionEffectInvGlide(),
            new ItemStack(Items.MILK_BUCKET)
    );

    public static final Potion INVISIBLE_SPEED = new PotionAdv(false, 8171462, false).setPotionName("effect.moveSpeed").registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2).setBeneficial();

    public static final Potion INVISIBLE_JUMP_BOOST = new PotionAdv(false, 2293580, false).setPotionName("effect.jump").setBeneficial();

    public static final Potion INVISIBLE_HEALTH_BOOST = new HealthBoostAdv(false).setPotionName("effect.healthBoost").registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0).setBeneficial();

    public static final Potion INVISIBLE_REGENERATION = new PotionAdv(false, 13458603, false).setEffectiveness(0.25D).setPotionName("effect.regeneration").setBeneficial();

    //public static final Potion INVISIBLE_SATURATION = new PotionHealth(false, 16262179).setPotionName("effect.saturation").setBeneficial());

    public static final Potion INVISIBLE_STRENGTH = new StrengthAdv(false).setPotionName("effect.damageBoost").registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0D, 0).setBeneficial();


    private static class PotionEffectInvGlide implements IEffectProvider {

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
            return false;
        }
    }

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


}