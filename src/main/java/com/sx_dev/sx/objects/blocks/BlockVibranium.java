package com.sx_dev.sx.objects.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockVibranium extends BlockBase
{

	public BlockVibranium(String name, Material material) 
	{
		
		super(name, Properties.create(material).sound(SoundType.METAL).hardnessAndResistance(8.0F,15.0F));

		//setHarvestLevel("pickaxe", 2);//TODO
	}
}