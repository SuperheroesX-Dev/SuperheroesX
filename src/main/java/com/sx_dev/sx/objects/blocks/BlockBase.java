package com.sx_dev.sx.objects.blocks;

import com.sx_dev.sx.tabs.CustomItemGroup;
import com.sx_dev.sx.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.Tag;

import javax.annotation.Nonnull;

public class BlockBase extends Block {
    private final Item itemBlock;
    private String oreDictName;
    private boolean hasOreDictName;

    public BlockBase(String name, Properties properties, ItemGroup group) {
        super(properties);
        setRegistryName(Reference.MODID, name);

        this.itemBlock = new ItemBlock(this, new Item.Properties().group(group)).setRegistryName(this.getRegistryName());
    }

    public BlockBase(String name, Properties properties, Void x) {
        super(properties);
        setRegistryName(Reference.MODID, name);

        this.itemBlock = new ItemBlock(this, new Item.Properties()).setRegistryName(this.getRegistryName());
    }

    public BlockBase(String name, Properties properties, Tag<Block> tag) {
        super(properties);
        setRegistryName(Reference.MODID, name);
        tag.getAllElements().add(this);

        this.itemBlock = new ItemBlock(this, new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName());
    }

    public BlockBase(String name, Properties properties) {
        super(properties);
        setRegistryName(Reference.MODID, name);

        this.itemBlock = new ItemBlock(this, new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName());
    }

    @Override
    @Nonnull
    public Item asItem() {
        return itemBlock;
    }
}
