package mini.SuperheroesX.init;

import mini.SuperheroesX.objects.potions.CustomPotionEffect;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.interfaces.IEffectProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;


public class PotionInit {

    public static final java.util.List<Potion> POTIONS = new ArrayList<>();
    public static final java.util.List<PotionType> POTION_ITEMS = new ArrayList<>();

    public static final Potion FLY = new CustomPotionEffect("fly", false,
            new Color(215, 215, 215).getRGB(), new PotionEffectGlide(),
            new ItemStack(Items.MILK_BUCKET)
    );


    private static class PotionEffectGlide implements IEffectProvider {
        @Override
        public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
            int fallingSpeed = 2;
            if (!entityLivingBaseIn.onGround && !entityLivingBaseIn.isSneaking()) {
                double newMotionY = entityLivingBaseIn.motionY - entityLivingBaseIn.motionY / fallingSpeed;
                if (newMotionY < 0 && !entityLivingBaseIn.isElytraFlying()) {
                    entityLivingBaseIn.motionY = newMotionY;
                }
                entityLivingBaseIn.fallDistance = 0; }
            }
        
        @Override
        public boolean hasStatusIcon() {
        	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/container/custom_effects.png"));
        	return true;
        }
    }
}