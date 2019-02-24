package com.sx_dev.sx.objects.potions;


import com.sx_dev.sx.util.Reference;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;


public class PotionAdv extends Potion {



    private final boolean visible;


    public PotionAdv(String name, boolean isBadEffectIn, int liquidColorIn, boolean visible) {
        super(isBadEffectIn, liquidColorIn);


        this.visible = visible;

        this.setRegistryName(new ResourceLocation(Reference.MODID, name));
    }



    @Override
    public boolean shouldRender(PotionEffect effect) {
        return super.shouldRender(effect) && visible;
    }



    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return super.shouldRenderHUD(effect) && visible;
    }



    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return super.shouldRenderInvText(effect) && visible;
    }

    @Override
    public Potion setEffectiveness(double effectivenessIn) {
        return super.setEffectiveness(effectivenessIn);
    }
}