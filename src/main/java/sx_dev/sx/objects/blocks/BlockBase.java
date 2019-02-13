package sx_dev.sx.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import sx_dev.sx.SuperheroesX;
import sx_dev.sx.init.BlockInit;
import sx_dev.sx.init.ItemInit;
import sx_dev.sx.util.interfaces.IHasModel;
import sx_dev.sx.util.interfaces.IOreDict;

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
