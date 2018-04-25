package mini.SuperheroesX.tabs;

import mini.SuperheroesX.init.BlockInit;
import mini.SuperheroesX.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public abstract class SuperheroesXTabs extends CreativeTabs {
    public SuperheroesXTabs(String label) {
        super("superheroesx_" + label);
        this.setBackgroundImageName("superheroesx.png");
    }

    public static class Materials extends SuperheroesXTabs {
        public Materials() {
            super("materials");
        }

        public ItemStack getTabIconItem() {
            return new ItemStack(BlockInit.ORE_TITANIUM);
        }
    }

    public static class DC extends SuperheroesXTabs {
        public DC() {
            super("dc");
        }

        public ItemStack getTabIconItem() {
            return new ItemStack(ItemInit.HELMET_DEADPOOL);//TODO: change to DC logo or batman's logo
        }
    }

    public static class MARVEL extends SuperheroesXTabs {
        public MARVEL() {
            super("marvel");
        }

        public ItemStack getTabIconItem() {
            return new ItemStack(ItemInit.HELMET_DEADPOOL);//TODO: change to marvel logo
        }
    }

}