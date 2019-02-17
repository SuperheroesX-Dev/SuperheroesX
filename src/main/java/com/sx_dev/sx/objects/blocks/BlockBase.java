package com.sx_dev.sx.objects.blocks;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.BlockInit;
import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.util.interfaces.IHasModel;
import com.sx_dev.sx.util.interfaces.IOreDict;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel, IOreDict<BlockBase> {
    private String oreDictName;
    private boolean hasOreDictName;

    public BlockBase(String name, Properties properties)
	{
		super(properties);
		//setUnlocalizedName(name);
		setRegistryName(name);
        //setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
		
		BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this, new Item.Properties()).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() 
	{
        //SuperheroesX.PROXY.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

    @Override
    public String getOreDictName() {
        return oreDictName;
    }

    @Override
    public BlockBase setOreDictName(String oreDictName) {
        this.oreDictName = oreDictName;
        ItemInit.MOD_ORE_DICT.add(this);
        this.hasOreDictName = true;

        return this;
    }

    @Override
    public boolean hasOreDictName() {
        return this.hasOreDictName;
    }

    @Override
    public BlockBase getEntry() {
        return this;
    }
}
