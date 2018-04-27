package mini.SuperheroesX.objects.tools;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.util.interfaces.IHasModel;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword implements IHasModel
{

    public ToolSword(String name, ToolMaterial material)
	{
		super(material);

		setUnlocalizedName(name);
		setRegistryName(name);

		ItemInit.ITEMS.add(this);
	}

	@Override
    public void registerModels()
	{
        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
	}
}