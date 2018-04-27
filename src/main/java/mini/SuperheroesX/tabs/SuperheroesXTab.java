package mini.SuperheroesX.tabs;

import mini.SuperheroesX.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SuperheroesXTab extends CreativeTabs
{
    public SuperheroesXTab() {
        super("superheroesx");
        this.setBackgroundImageName("superheroesx.png");
    }

    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.HELMET_DEADPOOL);
    }
}