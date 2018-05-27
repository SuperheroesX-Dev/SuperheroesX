package mini.SuperheroesX.objects.multiblockStructures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IMultiblock {
    boolean checkMultiblock(World world, BlockPos pos);
}
