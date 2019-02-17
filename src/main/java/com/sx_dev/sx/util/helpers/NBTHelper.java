package com.sx_dev.sx.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class NBTHelper {
    public static final String DATA_ID = "sxData";


    /*public static NBTTagCompound getDataMap(ItemStack stack) {
        initStack(stack);

        return stack.getTag().getTag(DATA_ID);
    }

    public static boolean hasData(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).hasKey(key);
    }

    public static void removeData(ItemStack stack, String key) {
        initStack(stack);

        getDataMap(stack).removeTag(key);
    }

    public static int getInt(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getInteger(key);
    }

    public static boolean getBoolean(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getBoolean(key);
    }

    public static boolean getBooleanFallback(ItemStack stack, String key, boolean fallback) {
        initStack(stack);

        if (!getDataMap(stack).hasKey(key)) {
            getDataMap(stack).setBoolean(key, fallback);
        }
        return getDataMap(stack).getBoolean(key);
    }

    public static double getDouble(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getDouble(key);
    }

    public static String getString(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getString(key);
    }

    public static NBTTagCompound getCompound(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getCompoundTag(key);
    }

    public static NBTTagList getList(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getTagList(key, NBT.TAG_COMPOUND);
    }

    public static void setInt(ItemStack stack, String key, int i) {
        initStack(stack);

        getDataMap(stack).setInteger(key, i);
    }

    public static void setBoolean(ItemStack stack, String key, boolean b) {
        initStack(stack);

        getDataMap(stack).setBoolean(key, b);
    }

    public static void setDouble(ItemStack stack, String key, double d) {
        initStack(stack);

        getDataMap(stack).setDouble(key, d);
    }

    public static void setString(ItemStack stack, String key, String s) {
        initStack(stack);

        getDataMap(stack).setString(key, s);
    }

    public static void setCompound(ItemStack stack, String key, NBTTagCompound tag) {
        initStack(stack);

        getDataMap(stack).setTag(key, tag);
    }

    public static void setList(ItemStack stack, String key, NBTTagList tag) {
        initStack(stack);

        getDataMap(stack).setTag(key, tag);
    }*/

    private static void initStack(ItemStack stack) {
        if (stack.getTag() == null) {
            stack.setTag(new NBTTagCompound());
        }

        if (!stack.getTag().contains(DATA_ID)) {
            stack.getTag().put(DATA_ID, new NBTTagCompound());
        }
    }
}
