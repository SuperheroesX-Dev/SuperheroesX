package mini.SuperheroesX.init;

import mini.SuperheroesX.objects.potions.CustomPotionEffect;
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

    public static final Potion INVISIBLE_STRENGTH = new StrengthAdv(false).setPotionName("effect.damageBoost").registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0D, 0).setBeneficial();


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
            return false;
        }
    }

}