package mini.SuperheroesX.init;

import mini.SuperheroesX.objects.blocks.BlockBase;
import mini.SuperheroesX.objects.blocks.BlockPalladiumOre;
import mini.SuperheroesX.objects.blocks.BlockTitaniumOre;
import mini.SuperheroesX.objects.blocks.BlockVibraniumOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit 
{
    public static final List<Block> BLOCKS = new ArrayList<>();
	
	//public static final Block Atom_Fuser = new BlockAtomFuser("atomfuser");

    public static final Block ORE_VIBRANIUM = new BlockVibraniumOre("ore_vibranium", Material.ROCK).setOreDictName("oreVibranium");
    public static final Block ORE_PALLADIUM = new BlockPalladiumOre("ore_palladium", Material.ROCK).setOreDictName("orePalladium");
    public static final Block ORE_TITANIUM = new BlockTitaniumOre("ore_titanium", Material.ROCK).setOreDictName("oreTitanium");

    public static final Block BLOCK_VIBRANIUM = new BlockBase("block_vibranium", Material.IRON).setOreDictName("blockVibranium");
    public static final Block BLOCK_PALLADIUM = new BlockBase("block_palladium", Material.IRON).setOreDictName("blockPalladium");
    public static final Block BLOCK_TITANIUM = new BlockBase("block_titanium", Material.IRON).setOreDictName("blockTitanium");

}
