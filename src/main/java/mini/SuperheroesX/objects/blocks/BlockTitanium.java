package mini.SuperheroesX.objects.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

import mini.SuperheroesX.objects.blocks.BlockBase;

public class BlockTitanium extends BlockBase
{

	public BlockTitanium(String name, Material material) 
	{
		
		super(name, material);
	
		setSoundType(SoundType.STONE);
		setHardness(7.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
	}
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	return Item.getItemFromBlock(this);
	}
}