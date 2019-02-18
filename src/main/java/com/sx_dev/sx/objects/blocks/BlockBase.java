package com.sx_dev.sx.objects.blocks;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.BlockInit;
import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.Tag;

public class BlockBase extends Block implements IHasModel {
    private String oreDictName;
    private boolean hasOreDictName;

    public BlockBase(String name, Properties properties, ItemGroup group) {
        super(properties);
        //setUnlocalizedName(name);
        setRegistryName(name);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this, new Item.Properties().group(group)).setRegistryName(this.getRegistryName()));
    }

    public BlockBase(String name, Properties properties, Void x) {
        super(properties);
        //setUnlocalizedName(name);
        setRegistryName(name);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this, new Item.Properties()).setRegistryName(this.getRegistryName()));
    }

    public BlockBase(String name, Properties properties, Tag<Block> tag) {
        super(properties);
        //setUnlocalizedName(name);
        setRegistryName(name);
        tag.getAllElements().add(this);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this, new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName()));
    }

    public BlockBase(String name, Properties properties) {
        super(properties);
        //setUnlocalizedName(name);
        setRegistryName(name);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this, new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName()));
    }
	
	@Override
	public void registerModels() 
	{
        //SuperheroesX.PROXY.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
