package com.sx_dev.sx.init;

import com.sx_dev.sx.util.interfaces.IPotionProvider;
import com.sx_dev.sx.util.misc.Lazy;
import net.minecraft.potion.PotionType;

import java.util.function.Supplier;

public enum PotionInit implements IPotionProvider {
    //GLIDE(() -> new PotionType(new PotionEffect(PotionEffectInit.GLIDE.asPotionEffect(), 3600, 0)).setRegistryName(Reference.MODID, "fly"))
    ;


    private final Lazy<PotionType> potion;

    PotionInit(Supplier<PotionType> supplier) {
        this.potion = Lazy.of(supplier);
    }

    @Override
    public PotionType asPotion() {
        return potion.get();
    }
}
