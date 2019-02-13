package sx_dev.sx.tabs;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import sx_dev.sx.init.BlockInit;
import sx_dev.sx.init.ItemInit;

import javax.annotation.ParametersAreNonnullByDefault;

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

    @ParametersAreNonnullByDefault
    public static class MARVEL extends SuperheroesXTabs {
        public MARVEL() {
            super("marvel");
        }

        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> items) {
            super.displayAllRelevantItems(items);
            /*items.sort((item1, item2) -> {
                if (item1.getItem() instanceof ItemArmor && item2.getItem() instanceof ItemArmor && ((ItemArmor) item1.getItem()).getArmorMaterial() == ((ItemArmor) item2.getItem()).getArmorMaterial()){
                    int i = ((ItemArmor) item1.getItem()).armorType.getIndex() - ((ItemArmor) item1.getItem()).armorType.getIndex();
                    if (i == 0){
                        i = item1.getItem().getUnlocalizedName(item1).compareTo(item2.getItem().getUnlocalizedName(item2));
                    }
                    return i;
                } else {
                    return item1.getItem().getUnlocalizedName(item1).compareTo(item2.getItem().getUnlocalizedName(item2));
                }
            });*/
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemInit.LOGO_MARVEL);
        }
    }

}