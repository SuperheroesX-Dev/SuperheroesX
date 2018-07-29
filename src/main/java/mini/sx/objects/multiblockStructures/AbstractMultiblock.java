package mini.sx.objects.multiblockStructures;

import mini.sx.util.misc.Structure;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractMultiblock implements IMultiblock {

    private final Structure structure;

    protected AbstractMultiblock(Structure structureIn) {
        structure = structureIn;
    }

    @Override
    public boolean checkMultiblock(World world, BlockPos pos, EnumFacing facing) {
        return this.structure.equals(Structure.getArea(world, pos, this.structure.size()));
    }
}
