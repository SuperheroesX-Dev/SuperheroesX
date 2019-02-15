package com.sx_dev.sx.objects.fluids;

import com.sx_dev.sx.init.FluidInit;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class FluidBase extends Fluid {

    public FluidBase(String fluidName, ResourceLocation still, ResourceLocation flowing, Color color) {
        super(fluidName, still, flowing, color);

        FluidInit.FLUIDS.add(this);
    }
}
