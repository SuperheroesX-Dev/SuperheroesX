package com.sx_dev.sx.objects.tools;

import com.sx_dev.sx.util.Reference;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword
{

    public ToolSword(String name, IItemTier material, ItemGroup group) {
        super(material, Math.round(material.getAttackDamage()), material.getEfficiency(), new Properties().group(group));

        setRegistryName(Reference.MODID, name);
	}
}