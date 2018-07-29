package mini.sx.util.integration.TC;


import mini.sx.init.FluidInit;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class TCFluid extends Fluid {
    private Color color;

    public TCFluid(String fluidName, Color color) {
        super(fluidName, new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow"));
        this.color = color;

        FluidInit.TC_FLUIDS.add(this);
    }

    @Override
    public int getColor() {
        return this.color.getRGB();
    }
}
