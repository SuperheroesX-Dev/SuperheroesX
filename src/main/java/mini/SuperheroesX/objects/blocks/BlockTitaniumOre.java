package mini.SuperheroesX.objects.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockTitaniumOre extends BlockBase {
    public BlockTitaniumOre(String name, Material material) {
        super(name, material);

        setSoundType(SoundType.STONE);
        setHardness(5.0F);
        setResistance(12.0F);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	return Item.getItemFromBlock(this);    }
}