package mini.SuperheroesX.objects.blocks;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.init.BlockInit;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.util.interfaces.IHasModel;
import mini.SuperheroesX.util.interfaces.IOreDict;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel, IOreDict<BlockBase> {
    private String oreDictName;
    private boolean hasOreDictName;

    public BlockBase(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
        setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName())); 
	}
	
	@Override
	public void registerModels() 
	{
        SuperheroesX.PROXY.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		}

    @Override
    public String getOreDictName() {
        return oreDictName;
    }

    @Override
    public BlockBase setOreDictName(String oreDictName) {
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
    public BlockBase getEntry() {
        return this;
    }
}
