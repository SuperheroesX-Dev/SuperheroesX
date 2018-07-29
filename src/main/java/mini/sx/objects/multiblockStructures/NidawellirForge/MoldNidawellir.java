package mini.sx.objects.multiblockStructures.NidawellirForge;

import mini.sx.objects.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class MoldNidawellir extends BlockBase {

    public MoldNidawellir() {
        super("mold_nidawellir", Material.ROCK);
    }

    public static ItemStack setDefaultTypeTag(ItemStack container, Type type) {

        if (!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }
        container.getTagCompound().setString("Type", type.name());

        return container;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (Type t : Type.values()) {
            items.add(setDefaultTypeTag(new ItemStack(this), t));
        }
    }

    public enum Type {
        MJOLNIR,
        STORMBREAKER
    }
}
