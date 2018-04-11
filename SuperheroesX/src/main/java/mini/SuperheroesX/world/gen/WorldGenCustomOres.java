package mini.SuperheroesX.world.gen;

import mini.SuperheroesX.init.BlockInit;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator

{

	private WorldGenerator vibranium_ore;

	private WorldGenerator palladium_ore;

	private WorldGenerator titanium_ore;


	public WorldGenCustomOres() 

	{

        vibranium_ore = new WorldGenMinable(BlockInit.ORE_VIBRANIUM.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));

        palladium_ore = new WorldGenMinable(BlockInit.ORE_PALLADIUM.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));

        titanium_ore = new WorldGenMinable(BlockInit.ORE_TITANIUM.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));
	}

	

	@Override

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 

	{

		switch(world.provider.getDimension())

		{

		case 0:

			
			runGenerator(titanium_ore, world, random, chunkX, chunkZ, 18, 0, 23);
			
			runGenerator(palladium_ore, world, random, chunkX, chunkZ, 24, 0, 30);

            runGenerator(vibranium_ore, world, random, chunkX, chunkZ, 2, 5, 12);

		}

	}

	

	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)

	{

		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of the natural zone");

		

		int heightDiff = maxHeight - minHeight + 1;

		for(int i = 0; i < chance; i++)

		{

			int x = chunkX * 16 + rand.nextInt(16);

			int y = minHeight + rand.nextInt(heightDiff);

			int z = chunkZ * 16 + rand.nextInt(16);

			

			gen.generate(world, rand, new BlockPos(x,y,z));

		}

	}

}