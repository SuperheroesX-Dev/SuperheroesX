package com.sx_dev.sx.util.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public interface IHUDInfoProvider {
    @OnlyIn(Dist.CLIENT)
    void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState);
}