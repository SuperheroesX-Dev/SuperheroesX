package com.sx_dev.sx.tabs;

import com.sx_dev.sx.init.BlockInit;
import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.config.ModConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

//import net.minecraft.creativetab.CreativeTabs;

@MethodsReturnNonnullByDefault
public abstract class CustomItemGroup extends ItemGroup {
    public static class Groups {
        public static final ItemGroup SUPERHEROES_X_TAB_MATERIALS = new Materials();
        public static final ItemGroup SUPERHEROES_X_TAB_MARVEL = ModConfig.CommonConfig.marvelItems.get() ? new MARVEL() : null;
        public static final ItemGroup SUPERHEROES_X_TAB_DC = ModConfig.CommonConfig.dcItems.get() ? new DC() : null;
    }

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