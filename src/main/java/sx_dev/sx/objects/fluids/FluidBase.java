package sx_dev.sx.objects.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import sx_dev.sx.init.FluidInit;

import java.awt.*;

public class FluidBase extends Fluid {

    public FluidBase(String fluidName, ResourceLocation still, ResourceLocation flowing, Color color) {
        super(fluidName, still, flowing, color);

        FluidInit.FLUIDS.add(this);
    }
}
