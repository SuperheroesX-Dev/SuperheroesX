package com.sx_dev.sx.init;

import com.sx_dev.sx.objects.CustomArmorMaterial;
import com.sx_dev.sx.objects.CustomItemTier;
import com.sx_dev.sx.objects.armor.*;
import com.sx_dev.sx.objects.armor.ArmorIronMan.ChestplateIronMan;
import com.sx_dev.sx.objects.items.ItemBase;
import com.sx_dev.sx.objects.items.ShieldCaptainAmerica;
import com.sx_dev.sx.objects.tools.SwordDeadpool;
import com.sx_dev.sx.objects.tools.TridentAquaman;
import com.sx_dev.sx.tabs.CustomItemGroup;
import com.sx_dev.sx.util.misc.Lazy;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;
import java.util.function.Supplier;


@SuppressWarnings({"SpellCheckingInspection", "unused"})
public enum ItemInit implements IItemProvider {

    //Items
    SPANDEX_RED(() -> new ItemBase("spandex_red", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    SPANDEX_BLACK(() -> new ItemBase("spandex_black", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    SPANDEX_YELLOW(() -> new ItemBase("spandex_yellow", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    SPANDEX_WHITE(() -> new ItemBase("spandex_white", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    SPANDEX_GREEN(() -> new ItemBase("spandex_green", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),

    SPANDEX_CLOTH_RED(() -> new ItemBase("spandex_cloth_red", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    SPANDEX_CLOTH_BLACK(() -> new ItemBase("spandex_cloth_black", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    SPANDEX_CLOTH_YELLOW(() -> new ItemBase("spandex_cloth_yellow", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    SPANDEX_CLOTH_WHITE(() -> new ItemBase("spandex_cloth_white", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    SPANDEX_CLOTH_GREEN(() -> new ItemBase("spandex_cloth_green", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),

    CLOTH_VIBRANIUM(() -> new ItemBase("cloth_vibranium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    STRENGTHENED_CLOTH(() -> new ItemBase("cloth_strengthened", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    CLOTH(() -> new ItemBase("cloth", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),

    LOGO_KID_FLASH(() -> new ItemBase("kidflash_logo"), Group.DC),

    SHIELD_HANDLE(() -> new ItemBase("shield_handle", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),


    INGOT_VIBRANIUM(() -> new ItemBase("ingot_vibranium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    INGOT_PALLADIUM(() -> new ItemBase("ingot_palladium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    INGOT_TITANIUM(() -> new ItemBase("ingot_titanium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    NUGGET_PALLADIUM(() -> new ItemBase("nugget_palladium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    NUGGET_TITANIUM(() -> new ItemBase("nugget_titanium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    PLATE_VIBRANIUM(() -> new ItemBase("plate_vibranium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    PLATE_TITANIUM(() -> new ItemBase("plate_titanium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    PLATE_GOLD(() -> new ItemBase("plate_gold", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    DUST_PALLADIUM(() -> new ItemBase("dust_palladium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    DUST_TITANIUM(() -> new ItemBase("dust_titanium", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.ANY),
    INGOT_TITANOGOLD(() -> new ItemBase("ingot_titanogold", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    PLATE_TITANOGOLD(() -> new ItemBase("plate_titanogold", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    CIRCUIT(() -> new ItemBase("circuit", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    MINI_ARC_REACTOR_BASE(() -> new ItemBase("mini_arc_reactor_base", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    MINI_ARC_REACTOR_MK1(() -> new ItemBase("mini_arc_reactor_mk1", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    MINI_ARC_REACTOR_MK2(() -> new ItemBase("mini_arc_reactor_mk2", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),
    SHIELD_CAPTAIN_AMERICA_UNCOLORED(() -> new ItemBase("shield_captain_america_uncolored", new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)), Group.MARVEL),


    //Tools
    KATANA_DEADPOOL(() -> new SwordDeadpool("deadpool_katana"), Group.MARVEL),
    SHIELD_CAPTAIN_AMERICA(ShieldCaptainAmerica::new, Group.MARVEL),
    TRIDENT_AQUAMAN(TridentAquaman::new, Group.DC),

    //Armor
    HELMET_DEADPOOL(() -> new ArmorDeadpool("deadpool_helmet", EntityEquipmentSlot.HEAD), Group.MARVEL),
    CHESTPLATE_DEADPOOL(() -> new ArmorDeadpool("deadpool_chestplate", EntityEquipmentSlot.CHEST), Group.MARVEL),
    LEGGINGS_DEADPOOL(() -> new ArmorDeadpool("deadpool_leggings", EntityEquipmentSlot.LEGS), Group.MARVEL),
    BOOTS_DEADPOOL(() -> new ArmorDeadpool("deadpool_boots", EntityEquipmentSlot.FEET), Group.MARVEL),

    CHESTPLATE_SUPERBOY(() -> new ArmorSuperboy("superboy_chestplate", EntityEquipmentSlot.CHEST), Group.DC),
    LEGGINGS_SUPERBOY(() -> new ArmorSuperboy("superboy_leggings", EntityEquipmentSlot.LEGS), Group.DC),
    BOOTS_SUPERBOY(() -> new ArmorSuperboy("superboy_boots", EntityEquipmentSlot.FEET), Group.DC),

    HELMET_KIDFLASH(() -> new ArmorKidFlash("kidflash_helmet", EntityEquipmentSlot.HEAD), Group.DC),
    CHESTPLATE_KIDFLASH(() -> new ArmorKidFlash("kidflash_chestplate", EntityEquipmentSlot.CHEST), Group.DC),
    LEGGINGS_KIDFLASH(() -> new ArmorKidFlash("kidflash_leggings", EntityEquipmentSlot.LEGS), Group.DC),
    BOOTS_KIDFLASH(() -> new ArmorKidFlash("kidflash_boots", EntityEquipmentSlot.FEET), Group.DC),

    HELMET_BLACKPANTHER(() -> new ArmorBlackPanther("blackpanther_helmet", EntityEquipmentSlot.HEAD), Group.MARVEL),
    CHESTPLATE_BLACKPANTHER(() -> new ArmorBlackPanther("blackpanther_chestplate", EntityEquipmentSlot.CHEST), Group.MARVEL),
    LEGGINGS_BLACKPANTHER(() -> new ArmorBlackPanther("blackpanther_leggings", EntityEquipmentSlot.LEGS), Group.MARVEL),
    BOOTS_BLACKPANTHER(() -> new ArmorBlackPanther("blackpanther_boots", EntityEquipmentSlot.FEET), Group.MARVEL),

    HELMET_ROBIN1(() -> new ArmorRobin1("robin1_helmet", EntityEquipmentSlot.HEAD), Group.DC),
    CHESTPLATE_ROBIN1(() -> new ArmorRobin1("robin1_chestplate", EntityEquipmentSlot.CHEST), Group.DC),
    LEGGINGS_ROBIN1(() -> new ArmorRobin1("robin1_leggings", EntityEquipmentSlot.LEGS), Group.DC),
    BOOTS_ROBIN1(() -> new ArmorRobin1("robin1_boots", EntityEquipmentSlot.FEET), Group.DC),

    HELMET_IRONMAN(() -> new ArmorIronMan("ironman_helmet", EntityEquipmentSlot.HEAD, new Item.Properties()), Group.MARVEL),
    CHESTPLATE_IRONMAN(ChestplateIronMan::new, Group.MARVEL),
    LEGGINGS_IRONMAN(() -> new ArmorIronMan("ironman_leggings", EntityEquipmentSlot.LEGS, new Item.Properties()), Group.MARVEL),
    BOOTS_IRONMAN(() -> new ArmorIronMan("ironman_boots", EntityEquipmentSlot.FEET, new Item.Properties()), Group.MARVEL),

    HELMET_CAPTAIN_AMERICA(() -> new ArmorCaptainAmerica("captain_america_helmet", EntityEquipmentSlot.HEAD), Group.MARVEL),
    CHESTPLATE_CAPTAIN_AMERICA(() -> new ArmorCaptainAmerica("captain_america_chestplate", EntityEquipmentSlot.CHEST), Group.MARVEL),
    LEGGINGS_CAPTAIN_AMERICA(() -> new ArmorCaptainAmerica("captain_america_leggings", EntityEquipmentSlot.LEGS), Group.MARVEL),
    BOOTS_CAPTAIN_AMERICA(() -> new ArmorCaptainAmerica("captain_america_boots", EntityEquipmentSlot.FEET), Group.MARVEL),

    HELMET_REDHOOD(() -> new ArmorRedHood("redhood_helmet", EntityEquipmentSlot.HEAD), Group.DC),
    CHESTPLATE_REDHOOD(() -> new ArmorRedHood("redhood_chestplate", EntityEquipmentSlot.CHEST), Group.DC),
    LEGGINGS_REDHOOD(() -> new ArmorRedHood("redhood_leggings", EntityEquipmentSlot.LEGS), Group.DC),
    BOOTS_REDHOOD(() -> new ArmorRedHood("redhood_boots", EntityEquipmentSlot.FEET), Group.DC),

    CHESTPLATE_AQUAMAN(() -> new ArmorAquaman("aquaman_chestplate", EntityEquipmentSlot.CHEST), Group.DC),
    LEGGINGS_AQUAMAN(() -> new ArmorAquaman("aquaman_leggings", EntityEquipmentSlot.LEGS), Group.DC),
    BOOTS_AQUAMAN(() -> new ArmorAquaman("aquaman_boots", EntityEquipmentSlot.FEET), Group.DC),

    HELMET_ANTMAN(() -> new ArmorAntman("antman_helmet", EntityEquipmentSlot.HEAD, ArmorAntman.Type.ANTMAN), Group.MARVEL),
    CHESTPLATE_ANTMAN(() -> new ArmorAntman.ChestplateAntman("antman_chestplate", EntityEquipmentSlot.CHEST, ArmorAntman.Type.ANTMAN), Group.MARVEL),
    LEGGINGS_ANTMAN(() -> new ArmorAntman("antman_leggings", EntityEquipmentSlot.LEGS, ArmorAntman.Type.ANTMAN), Group.MARVEL),
    BOOTS_ANTMAN(() -> new ArmorAntman("antman_boots", EntityEquipmentSlot.FEET, ArmorAntman.Type.ANTMAN), Group.MARVEL),

    HELMET_THE_WASP(() -> new ArmorAntman("the_wasp_helmet", EntityEquipmentSlot.HEAD, ArmorAntman.Type.THE_WASP), Group.MARVEL),
    CHESTPLATE_THE_WASP(() -> new ArmorAntman.ChestplateAntman("the_wasp_chestplate", EntityEquipmentSlot.CHEST, ArmorAntman.Type.THE_WASP), Group.MARVEL),
    LEGGINGS_THE_WASP(() -> new ArmorAntman("the_wasp_leggings", EntityEquipmentSlot.LEGS, ArmorAntman.Type.THE_WASP), Group.MARVEL),
    BOOTS_THE_WASP(() -> new ArmorAntman("the_wasp_boots", EntityEquipmentSlot.FEET, ArmorAntman.Type.THE_WASP), Group.MARVEL),

    //LOGOS
    LOGO_DC(() -> new ItemBase("logo_dc"), Group.DC),
    LOGO_MARVEL(() -> new ItemBase("logo_marvel"), Group.MARVEL);


    //Materials
    public static final IItemTier TOOL_DEADPOOL = new CustomItemTier("tool_deadpool", 0, 10000, 1.0F, 5.0F, 5, () -> Ingredient.EMPTY);
    public static final IItemTier SWORD_HEIMDAL = new CustomItemTier("sword_heimdal", 0, 10000, 1.0F, 15.0F, 6, () -> Ingredient.EMPTY);

    public static final IArmorMaterial ARMOR_DEADPOOL = new CustomArmorMaterial("armor_deadpool", 500, new int[]{5, 8, 10, 5}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3.0F, () -> Ingredient.fromItems(STRENGTHENED_CLOTH));
    public static final IArmorMaterial ARMOR_SUPERBOY = new CustomArmorMaterial("armor_superboy", 550, new int[]{10, 10, 10}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 4.0F, () -> null);
    public static final IArmorMaterial ARMOR_KIDFLASH = new CustomArmorMaterial("armor_kidflash", 400, new int[]{4, 7, 10, 4}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.5F, () -> null);
    public static final IArmorMaterial ARMOR_BLACKPANTHER = new CustomArmorMaterial("armor_blackpanther", 1000, new int[]{8, 10, 9, 6}, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.5F, () -> Ingredient.fromStacks(new ItemStack(CLOTH_VIBRANIUM)));
    public static final IArmorMaterial ARMOR_IRONMAN = new CustomArmorMaterial("armor_ironman", 16000, new int[]{7, 9, 8, 5}, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.7F, () -> null);
    public static final IArmorMaterial ARMOR_ROBIN1 = new CustomArmorMaterial("armor_robin1", 370, new int[]{4, 7, 9, 4}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.4F, () -> null);
    public static final IArmorMaterial ARMOR_CAPTAIN_AMERICA = new CustomArmorMaterial("armor_captain_america", 500, new int[]{4, 7, 10, 4}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.8F, () -> null);
    public static final IArmorMaterial ARMOR_AQUAMAN = new CustomArmorMaterial("armor_aquaman", 500, new int[]{2, 3, 5, 2}, 0, SoundEvents.ENTITY_DOLPHIN_SPLASH, 2F, () -> null);
    public static final IArmorMaterial ARMOR_REDHOOD = new CustomArmorMaterial("armor_redhood", 500, new int[]{4, 7, 10, 4}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.8F, () -> null);
    public static final IArmorMaterial ARMOR_ANTMAN = new CustomArmorMaterial("armor_antman", 500, new int[]{2, 3, 5, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.5F, () -> null);


    private final Lazy<Item> item;
    private final Group group;

    ItemInit(Supplier<Item> factory, Group group) {
        this.item = Lazy.of(factory);
        this.group = group;
    }

    @Override
    @Nonnull
    public Item asItem() {
        return item.get();
    }

    public boolean isEnabled() {
        return this.group.enabled();
    }
}
