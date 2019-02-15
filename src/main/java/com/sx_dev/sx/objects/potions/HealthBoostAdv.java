package com.sx_dev.sx.objects.potions;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealthBoost;

public class HealthBoostAdv extends PotionHealthBoost {

    private final boolean visible;

    public HealthBoostAdv(boolean visible) {
        super(false, 16284963);
        this.visible = visible;
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
}
