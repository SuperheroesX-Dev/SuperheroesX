package mini.sx.tabs;

import mcp.MethodsReturnNonnullByDefault;
import mini.sx.init.BlockInit;
import mini.sx.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

@MethodsReturnNonnullByDefault
public abstract class SuperheroesXTabs extends CreativeTabs {
    public SuperheroesXTabs(String label) {
        super("superheroesx_" + label);
        //this.setBackgroundImageName("superheroesx.png");
    }

    public static class Materials extends SuperheroesXTabs {
        public Materials() {
            super("materials");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(BlockInit.ORE_TITANIUM);
        }
    }

    public static class DC extends SuperheroesXTabs {
        public DC() {
            super("dc");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemInit.LOGO_DC);
        }
    }

    public static class MARVEL extends SuperheroesXTabs {
        public MARVEL() {
            super("marvel");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemInit.LOGO_MARVEL);
        }
    }

}