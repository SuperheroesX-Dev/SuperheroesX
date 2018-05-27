package mini.SuperheroesX.objects.multiblockStructures;

import mini.SuperheroesX.util.misc.Structure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractMultiblock implements IMultiblock {

    private final Structure structure;

    protected AbstractMultiblock(Structure structureIn) {
        structure = structureIn;
    }

    @Override
    public boolean checkMultiblock(World world, BlockPos pos) {
        return this.structure.equals(Structure.getArea(world, pos, this.structure.size()));
    }
}
