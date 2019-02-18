package com.sx_dev.sx.objects.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockVibranium extends BlockBase
{

	public BlockVibranium(String name, Material material) 
	{

		super(name, Properties.create(material).sound(SoundType.METAL).hardnessAndResistance(8.0F, 15.0F));

		//setHarvestLevel("pickaxe", 2);//TODO
	}
}