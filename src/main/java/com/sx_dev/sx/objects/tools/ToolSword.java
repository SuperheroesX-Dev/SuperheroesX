package com.sx_dev.sx.objects.tools;

import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.util.interfaces.IHasModel;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword implements IHasModel
{

    public ToolSword(String name, IItemTier material, ItemGroup group) {
        super(material, Math.round(material.getAttackDamage()), material.getEfficiency(), new Properties().group(group));

		//setUnlocalizedName(name);
		setRegistryName(name);

		ItemInit.ITEMS.add(this);
	}

	@Override
    public void registerModels()
	{
        //SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
	}
}