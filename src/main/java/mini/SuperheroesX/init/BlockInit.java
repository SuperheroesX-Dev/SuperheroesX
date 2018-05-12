package mini.SuperheroesX.init;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.objects.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit 
{
    public static final List<Block> BLOCKS = new ArrayList<>();
	
	//public static final Block Atom_Fuser = new BlockAtomFuser("atomfuser");

    public static final Block ORE_VIBRANIUM = new BlockVibraniumOre("ore_vibranium", Material.ROCK)
            .setOreDictName("oreVibranium")
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
    public static final Block ORE_PALLADIUM = new BlockPalladiumOre("ore_palladium", Material.ROCK)
            .setOreDictName("orePalladium")
            .setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
    public static final Block ORE_TITANIUM = new BlockTitaniumOre("ore_titanium", Material.ROCK)
            .setOreDictName("oreTitanium")
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

}
