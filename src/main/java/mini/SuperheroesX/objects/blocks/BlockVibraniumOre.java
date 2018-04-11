package mini.SuperheroesX.objects.blocks;

import java.util.Random;

import mini.SuperheroesX.init.ItemInit;
import net.minecraft.block.Block;import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockVibraniumOre extends BlockBase
{

	public BlockVibraniumOre(String name, Material material) 
	{
		
		super(name, material);
	
		setSoundType(SoundType.STONE);
		setHardness(8.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 3);
	}
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	return Item.getItemFromBlock(this);
	}
}