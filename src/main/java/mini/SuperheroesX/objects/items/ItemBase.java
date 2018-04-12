package mini.SuperheroesX.objects.items;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.util.interfaces.IHasModel;
import mini.SuperheroesX.util.interfaces.IOreDict;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel, IOreDict<ItemBase> {
    private String oreDictName;
    private boolean hasOreDictName;

    public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
        setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB);
		
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
        this.hasOreDictName = true;

        return this;
    }

    @Override
    public boolean hasOreDictName() {
        return this.hasOreDictName;
    }
}
