package mini.SuperheroesX.init;

import java.util.ArrayList;
import java.util.List;

import mini.SuperheroesX.objects.blocks.BlockPalladium;
import mini.SuperheroesX.objects.blocks.BlockPalladiumOre;
import mini.SuperheroesX.objects.blocks.BlockTitanium;
import mini.SuperheroesX.objects.blocks.BlockTitaniumOre;
import mini.SuperheroesX.objects.blocks.BlockVibranium;
import mini.SuperheroesX.objects.blocks.BlockVibraniumOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
    public static final List<Block> BLOCKS = new ArrayList<>();
	
	//public static final Block Atom_Fuser = new BlockAtomFuser("atomfuser");

    public static final Block ORE_VIBRANIUM = new BlockVibraniumOre("ore_vibranium", Material.ROCK).setOreDictName("oreVibranium");
    public static final Block ORE_PALLADIUM = new BlockPalladiumOre("ore_palladium", Material.ROCK).setOreDictName("orePalladium");
    public static final Block ORE_TITANIUM = new BlockTitaniumOre("ore_titanium", Material.ROCK).setOreDictName("oreTitanium");

    public static final Block BLOCK_VIBRANIUM = new BlockVibranium("block_vibranium", Material.IRON).setOreDictName("blockVibranium");
    public static final Block BLOCK_PALLADIUM = new BlockPalladium("block_palladium", Material.IRON).setOreDictName("blockPalladium");
    public static final Block BLOCK_TITANIUM = new BlockTitanium("block_titanium", Material.IRON).setOreDictName("blockTitanium");

}
