package com.sx_dev.sx.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockTitanium extends BlockBase
{

	public BlockTitanium(String name, Material material) 
	{

		super(name, Block.Properties.create(material).hardnessAndResistance(7.0F, 15.0F).sound(SoundType.METAL));

		//setHarvestLevel("pickaxe", 2);TODO
	}
}