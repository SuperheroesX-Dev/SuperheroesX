package com.sx_dev.sx.objects.items;

import com.sx_dev.sx.util.Reference;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(String name)
	{
        super(new Properties());
        setRegistryName(Reference.MODID, name);
	}

    public ItemBase(String name, Properties properties) {
        super(properties);
        setRegistryName(Reference.MODID, name);
    }
}
