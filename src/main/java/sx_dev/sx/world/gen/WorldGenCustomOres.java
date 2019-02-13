package sx_dev.sx.world.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import sx_dev.sx.init.BlockInit;
import sx_dev.sx.objects.blocks.OreBase;
import sx_dev.sx.util.interfaces.IGeneratableOre;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

public class WorldGenCustomOres implements IWorldGenerator {
    private Map<WorldGenerator, IGeneratableOre.OreSpawnInfo> ores;

    public WorldGenCustomOres() {
        ores = new HashMap<>();

        for (OreBase ore : BlockInit.ORES) {
            addOreToGenerate(ore.getDefaultState(), ore.getOreSpawnInfo());
        }
    }

    public void addOreToGenerate(IBlockState ore, IGeneratableOre.OreSpawnInfo info) {
        ores.put(new WorldGenMinable(ore, info.veinSize, BlockMatcher.forBlock(info.baseBlock)), info);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        ores.forEach((ore, info) -> {
            if (info.dimension == world.provider.getDimension()) {
                runGenerator(ore, world, random, chunkX, chunkZ, info.rarity, info.minSpawnHeight, info.maxSpawnHeight, (blockPos) -> info.biome == null || info.biome.contains(world.provider.getBiomeForCoords(blockPos)));
            }
        });
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight, Predicate<BlockPos> isSpawnable) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
            throw new IllegalArgumentException("Ore generated out of bounds");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chance; i++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            BlockPos pos = new BlockPos(x, y, z);
            if (isSpawnable.test(pos)) gen.generate(world, rand, pos);
        }
    }
}