package com.sx_dev.sx.objects.items;

import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.interfaces.IOreDict;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IOreDict<ItemBase> {
    private String oreDictName;
    private boolean hasOreDictName;

    public ItemBase(String name)
	{
        super(new Properties());
        setRegistryName(Reference.MODID, name);
	}

    public ItemBase(String name, Properties properties) {
        super(properties);
        setRegistryName(Reference.MODID, name);
    }

    @Override
    public String getOreDictName() {
        return this.oreDictName;
    }

    @Override
    public ItemBase setOreDictName(String oreDictName) {
        this.oreDictName = oreDictName;
        //ItemInit.MOD_ORE_DICT.add(this);
        this.hasOreDictName = true;

        return this;
    }

    @Override
    public boolean hasOreDictName() {
        return this.hasOreDictName;
    }

    @Override
    public ItemBase getEntry() {
        return this;
    }
}
