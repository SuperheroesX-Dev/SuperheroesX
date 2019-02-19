package com.sx_dev.sx.init;

import com.sx_dev.sx.objects.blocks.BlockPalladium;
import com.sx_dev.sx.objects.blocks.BlockTitanium;
import com.sx_dev.sx.objects.blocks.BlockVibranium;
import com.sx_dev.sx.objects.blocks.OreBase;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.interfaces.IBlockProvider;
import com.sx_dev.sx.util.interfaces.IGeneratableOre;
import com.sx_dev.sx.util.misc.Lazy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public enum BlockInit implements IBlockProvider {

    ORE_VIBRANIUM(() -> new OreBase("ore_vibranium", Material.ROCK, new IGeneratableOre.OreSpawnInfo(6, 2, 0, 5, 12, Blocks.STONE, Arrays.asList(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU)), 8.0F, 15.0F, "pickaxe", 4)),
    ORE_PALLADIUM(() -> new OreBase("ore_palladium", Material.ROCK, 0, 4, 24, 0, 30, Blocks.STONE, 5.0F, 12.0F, "pickaxe", 3)),
    ORE_TITANIUM(() -> new OreBase("ore_titanium", Material.ROCK, 0, 6, 18, 0, 23, Blocks.STONE, 5.0F, 12.0F, "pickaxe", 2)),

    BLOCK_VIBRANIUM(() -> new BlockVibranium("block_vibranium", Material.IRON)),
    BLOCK_PALLADIUM(() -> new BlockPalladium("block_palladium", Material.IRON)),
    BLOCK_TITANIUM(() -> new BlockTitanium("block_titanium", Material.IRON));


    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final List<OreBase> ORES = new ArrayList<>();


    static {
        System.out.println(Reference.NAME + " Blocks: Start");
        Arrays.asList(values()).forEach(BlockInit::asItem);
        System.out.println(Reference.NAME + " Blocks: Done");
    }

    private final Lazy<Block> block;

    BlockInit(Supplier<Block> factory) {
        this.block = Lazy.of(factory);
    }

    @Override
    public Block asBlock() {
        return block.get();
    }

    @Override
    @Nonnull
    public Item asItem() {
        return asBlock().asItem();
    }
}
/*
    public static final Tag<Block> TAG_ORE_VIBRANIUM = new BlockTags.Wrapper(new ResourceLocation("forge", "ores/vibranium"));
    public static final Tag<Block> TAG_BLOCK_VIBRANIUM = new BlockTags.Wrapper(new ResourceLocation("forge", "storage_blocks/vibranium"));
    public static final Tag<Block> TAG_ORE_TITANIUM = new BlockTags.Wrapper(new ResourceLocation("forge", "ores/titanium"));
    public static final Tag<Block> TAG_BLOCK_TITANIUM = new BlockTags.Wrapper(new ResourceLocation("forge", "storage_blocks/titanium"));
    public static final Tag<Block> TAG_ORE_PALLADIUM = new BlockTags.Wrapper(new ResourceLocation("forge", "ores/palladium"));
    public static final Tag<Block> TAG_BLOCK_PALLADIUM = new BlockTags.Wrapper(new ResourceLocation("forge", "storage_blocks/palladium"));
    */
/*public static final Block MOLD_NIDAWELLIR = new MoldNidawellir();*/

/*public static final Block HEART_OF_A_DIEING_STAR = new BlockBase("HEART_OF_A_DIEING_STAR".toLowerCase(), Material.IRON);*/




/*public static final Block LENS_NIDAWELLIR = new NidawellirForge();*/

/*public static final Block BIFROST_CORE = new BifrostCore();*/