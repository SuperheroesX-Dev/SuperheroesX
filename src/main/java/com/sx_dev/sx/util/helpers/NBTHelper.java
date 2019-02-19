package com.sx_dev.sx.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public abstract class NBTHelper {
    public static final String DATA_ID = "sxData";


    public static NBTTagCompound getDataMap(ItemStack stack) {
        initStack(stack);

        return (NBTTagCompound) stack.getTag().get(DATA_ID);
    }

    public static boolean hasData(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).contains(key);
    }

    public static void removeData(ItemStack stack, String key) {
        initStack(stack);

        getDataMap(stack).remove(key);
    }

    public static int getInt(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getInt(key);
    }

    public static boolean getBoolean(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getBoolean(key);
    }

    public static boolean getBooleanFallback(ItemStack stack, String key, boolean fallback) {
        initStack(stack);

        if (!getDataMap(stack).contains(key)) {
            getDataMap(stack).putBoolean(key, fallback);
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

        return (NBTTagCompound) getDataMap(stack).get(key);
    }

    public static NBTTagList getList(ItemStack stack, String key) {
        initStack(stack);

        return getDataMap(stack).getList(key, Constants.NBT.TAG_COMPOUND);
    }

    public static void setInt(ItemStack stack, String key, int i) {
        initStack(stack);

        getDataMap(stack).putInt(key, i);
    }

    public static void setBoolean(ItemStack stack, String key, boolean b) {
        initStack(stack);

        getDataMap(stack).putBoolean(key, b);
    }

    public static void setDouble(ItemStack stack, String key, double d) {
        initStack(stack);

        getDataMap(stack).putDouble(key, d);
    }

    public static void setString(ItemStack stack, String key, String s) {
        initStack(stack);

        getDataMap(stack).putString(key, s);
    }

    public static void setCompound(ItemStack stack, String key, NBTTagCompound tag) {
        initStack(stack);

        getDataMap(stack).put(key, tag);
    }

    public static void setList(ItemStack stack, String key, NBTTagList tag) {
        initStack(stack);

        getDataMap(stack).put(key, tag);
    }

    private static void initStack(ItemStack stack) {
        if (stack.getTag() == null) {
            stack.setTag(new NBTTagCompound());
        }

        if (!stack.getTag().contains(DATA_ID)) {
            stack.getTag().put(DATA_ID, new NBTTagCompound());
        }
    }
}
