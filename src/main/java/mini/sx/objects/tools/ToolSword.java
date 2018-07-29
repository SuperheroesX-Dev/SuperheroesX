package mini.sx.objects.tools;

import mini.sx.SuperheroesX;
import mini.sx.init.ItemInit;
import mini.sx.util.interfaces.IHasModel;
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