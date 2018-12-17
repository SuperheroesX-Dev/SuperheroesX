package mini.sx.util.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.util.math.AxisAlignedBB;

public abstract class SizeHandler {

    protected static void setEntitySize(Entity entity, float width, float height) {
        if (width != entity.width || height != entity.height) {
            float oldWidth = entity.width;
            entity.width = width;
            entity.height = height;
            double halfWidth = width / 2.0D;
            if (entity.width < oldWidth) {
                entity.setEntityBoundingBox(new AxisAlignedBB(
                        entity.posX - halfWidth,
                        entity.posY,
                        entity.posZ - halfWidth,
                        entity.posX + halfWidth,
                        entity.posY + entity.height,
                        entity.posZ + halfWidth));
                return;
            }
            AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox();
            entity.setEntityBoundingBox(new AxisAlignedBB(
                    axisalignedbb.minX - halfWidth,
                    axisalignedbb.minY,
                    axisalignedbb.minZ - halfWidth,
                    axisalignedbb.minX + halfWidth,
                    axisalignedbb.minY + entity.height,
                    axisalignedbb.minZ + halfWidth));
            if (entity.width > oldWidth && (entity.ticksExisted > 1) && !entity.world.isRemote) {
                double val = oldWidth - entity.width;
                entity.move(MoverType.SELF, (val), 0.0D, (val));
            }
        }
    }
}
