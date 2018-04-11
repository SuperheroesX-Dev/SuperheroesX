package mini.SuperheroesX.objects.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockAtomFuser extends BlockBase
{
	public static final AxisAlignedBB ATOM_FUSER_AABB =  new AxisAlignedBB(0.1875D, 0, 0.1975D, 0.8125D, 0.625, 0.8125D);
	
	public BlockAtomFuser(String name) 
	{
		super(name, Material.IRON);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)

	{

		return false;

	}

	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		return ATOM_FUSER_AABB;
	}
}
