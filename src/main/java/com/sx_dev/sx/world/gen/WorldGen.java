package com.sx_dev.sx.world.gen;

/*import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;*/

import com.sx_dev.sx.init.BlockInit;
import com.sx_dev.sx.util.interfaces.IGeneratableOre;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.BasePlacement;
import net.minecraft.world.gen.placement.CountRange;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;

import javax.annotation.Nonnull;
import java.util.Random;

public class WorldGen {

    public static void setupOregen() {
        for (IGeneratableOre ore : BlockInit.ORES) {
            for (Biome biome : ore.getOreSpawnInfo().biome) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(ore));
            }
        }
    }

    private static CompositeFeature<?, ?> createCompositeFeature(IGeneratableOre ore) {
        IGeneratableOre.OreSpawnInfo spawnInfo = ore.getOreSpawnInfo();
        return new DimensionCompositeFeature<>(
                Feature.MINABLE,
                new MinableConfig(
                        state -> state.getBlock() == spawnInfo.baseBlock,
                        ore.asBlock().getDefaultState(),
                        spawnInfo.veinSize
                ),
                new CountRange(),
                new CountRangeConfig(spawnInfo.rarity, spawnInfo.minSpawnHeight, spawnInfo.minSpawnHeight, spawnInfo.maxSpawnHeight),
                spawnInfo.dimension
        );
    }

    private static class DimensionCompositeFeature<F extends IFeatureConfig, D extends IPlacementConfig> extends CompositeFeature<F, D> {
        private final DimensionType dimension;

        DimensionCompositeFeature(Feature<F> featureIn, F featureConfigIn, BasePlacement<D> basePlacementIn, D placementConfigIn, @Nonnull DimensionType dimension) {
            super(featureIn, featureConfigIn, basePlacementIn, placementConfigIn);
            this.dimension = dimension;
        }

        @Override
        public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> chunkGenerator, Random random, BlockPos pos, NoFeatureConfig config) {
            if (world.getDimension().getType().equals(dimension)) {
                return super.place(world, chunkGenerator, random, pos, config);
            }
            return false;
        }
    }
}