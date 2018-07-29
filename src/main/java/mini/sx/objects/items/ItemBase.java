package mini.sx.objects.items;

import mini.sx.SuperheroesX;
import mini.sx.init.ItemInit;
import mini.sx.util.interfaces.IHasModel;
import mini.sx.util.interfaces.IOreDict;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel, IOreDict<ItemBase> {
    private String oreDictName;
    private boolean hasOreDictName;

    public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		
		ItemInit.ITEMS.add(this);
	}


    @Override
	public void registerModels() 
	{
        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
	}

    @Override
    public String getOreDictName() {
        return this.oreDictName;
    }

    @Override
    public ItemBase setOreDictName(String oreDictName) {
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
    public ItemBase getEntry() {
        return this;
    }
}
