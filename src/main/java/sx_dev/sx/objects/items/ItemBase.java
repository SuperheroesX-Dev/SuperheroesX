package sx_dev.sx.objects.items;

import net.minecraft.item.Item;
import sx_dev.sx.SuperheroesX;
import sx_dev.sx.init.ItemInit;
import sx_dev.sx.util.interfaces.IHasModel;
import sx_dev.sx.util.interfaces.IOreDict;

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
