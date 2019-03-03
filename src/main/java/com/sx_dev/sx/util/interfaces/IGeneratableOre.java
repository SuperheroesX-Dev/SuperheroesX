package com.sx_dev.sx.util.interfaces;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public interface IGeneratableOre extends IOre {
    OreSpawnInfo getOreSpawnInfo();

    class OreSpawnInfo {
        public final int rarity;
        public final DimensionType dimension;
        public final int minSpawnHeight;
        public final int maxSpawnHeight;
        public final int veinSize;
        public final Block baseBlock;
        public final List<Biome> biome;

        public OreSpawnInfo(int veinSize, int rarity, DimensionType dimension, int minSpawnHeight, int maxSpawnHeight, Block baseBlock) {
            this(veinSize, rarity, dimension, minSpawnHeight, maxSpawnHeight, baseBlock, Lists.newArrayList(ForgeRegistries.BIOMES));
        }

        public OreSpawnInfo(int veinSize, int rarity, DimensionType dimension, int minSpawnHeight, int maxSpawnHeight, Block baseBlock, @Nonnull List<Biome> biome) {
            this.veinSize = veinSize;
            this.dimension = dimension;
            this.rarity = rarity;
            this.maxSpawnHeight = maxSpawnHeight;
            this.minSpawnHeight = minSpawnHeight;
            this.baseBlock = baseBlock;
            this.biome = biome;
        }
    }
}
