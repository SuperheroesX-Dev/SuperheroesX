package mini.sx.init;

import mini.sx.SuperheroesX;
import mini.sx.objects.blocks.BlockPalladium;
import mini.sx.objects.blocks.BlockTitanium;
import mini.sx.objects.blocks.BlockVibranium;
import mini.sx.objects.blocks.OreBase;
import mini.sx.util.interfaces.IGeneratableOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockInit 
{
    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final List<OreBase> ORES = new ArrayList<>();
	
	//public static final Block Atom_Fuser = new BlockAtomFuser("atomfuser");

    /*public static final Block MOLD_NIDAWELLIR = new MoldNidawellir()
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);*/

    /*public static final Block HEART_OF_A_DIEING_STAR = new BlockBase("HEART_OF_A_DIEING_STAR".toLowerCase(), Material.IRON)
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);*/

    public static final Block ORE_VIBRANIUM = new OreBase("ore_vibranium", Material.ROCK, new IGeneratableOre.OreSpawnInfo(6, 2, 0, 5, 12, Blocks.STONE, Arrays.asList(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.MUTATED_SAVANNA, Biomes.MUTATED_SAVANNA_ROCK)), 8.0F, 15.0F, "pickaxe", 4, "Vibranium")
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
    public static final Block ORE_PALLADIUM = new OreBase("ore_palladium", Material.ROCK, 0, 4, 24, 0, 30, Blocks.STONE, 5.0F, 12.0F, "pickaxe", 3, "Palladium")
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
    public static final Block ORE_TITANIUM = new OreBase("ore_titanium", Material.ROCK, 0, 6, 18, 0, 23, Blocks.STONE, 5.0F, 12.0F, "pickaxe", 2, "Titanium")
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);

    public static final Block BLOCK_VIBRANIUM = new BlockVibranium("block_vibranium", Material.IRON)
            .setOreDictName("blockVibranium")
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
    public static final Block BLOCK_PALLADIUM = new BlockPalladium("block_palladium", Material.IRON)
            .setOreDictName("blockPalladium")
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
    public static final Block BLOCK_TITANIUM = new BlockTitanium("block_titanium", Material.IRON)
            .setOreDictName("blockTitanium")
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);

    /*public static final Block LENS_NIDAWELLIR = new NidawellirForge()
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);*/

    /*public static final Block BIFROST_CORE = new BifrostCore()
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);*/
}
