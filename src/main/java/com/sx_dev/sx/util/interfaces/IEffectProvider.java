package com.sx_dev.sx.util.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public interface IEffectProvider {
	void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier);

    default void renderCustomHUD(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
    }

    default boolean hasCustomHUD() {
        return false;
    }

    default boolean isVisible() {
        return true;
    }
}
