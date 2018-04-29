package mini.SuperheroesX.util.handlers;

import net.minecraft.item.EnumAction;
import net.minecraftforge.common.util.EnumHelper;

public class EnumHandler {

    public static final EnumAction THROW = EnumHelper.addEnum(EnumAction.class, "THROW", null, (Object) null);

    public enum ParticleType {
        DEFAULT
    }

    public enum HUDPositions {
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT,
        LEFT,
        RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
}