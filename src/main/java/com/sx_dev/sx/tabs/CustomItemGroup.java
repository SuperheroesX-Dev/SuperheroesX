package com.sx_dev.sx.tabs;

import com.sx_dev.sx.init.BlockInit;
import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.util.Reference;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

//import net.minecraft.creativetab.CreativeTabs;

@MethodsReturnNonnullByDefault
public abstract class CustomItemGroup extends ItemGroup {
    CustomItemGroup(String label) {
        super(getGroupCountSafe(), Reference.MODID + "_" + label);
        //this.setBackgroundImageName("superheroesx.png");
    }


    public static class Materials extends CustomItemGroup {
        public Materials() {
            super("materials");
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.ORE_TITANIUM);
        }
    }

    public static class DC extends CustomItemGroup {
        public DC() {
            super("dc");
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.LOGO_DC);
        }
    }

    public static class MARVEL extends CustomItemGroup {
        public MARVEL() {
            super("marvel");
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.LOGO_MARVEL);
        }
    }
}