package sx_dev.sx.objects.multiblockStructures;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IMultiblock {
    boolean checkMultiblock(World world, BlockPos pos, EnumFacing facing);
}
