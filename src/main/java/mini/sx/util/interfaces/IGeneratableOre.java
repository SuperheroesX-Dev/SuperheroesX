package mini.sx.util.interfaces;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public interface IGeneratableOre {
    OreSpawnInfo getOreSpawnInfo();

    class OreSpawnInfo {
        public final int rarity;
        public final int dimension;
        public final int minSpawnHeight;
        public final int maxSpawnHeight;
        public final int veinSize;
        public final Block baseBlock;
        public final List<Biome> biome;

        public OreSpawnInfo(int veinSize, int rarity, int dimension, int minSpawnHeight, int maxSpawnHeight, Block baseBlock) {
            this(veinSize, rarity, dimension, minSpawnHeight, maxSpawnHeight, baseBlock, new ArrayList<>());
        }

        public OreSpawnInfo(int veinSize, int rarity, int dimension, int minSpawnHeight, int maxSpawnHeight, Block baseBlock, @Nonnull List<Biome> biome) {
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
