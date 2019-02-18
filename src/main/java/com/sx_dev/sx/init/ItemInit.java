package com.sx_dev.sx.init;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.objects.armor.*;
import com.sx_dev.sx.objects.armor.ArmorIronMan.ChestplateIronMan;
import com.sx_dev.sx.objects.items.ItemBase;
import com.sx_dev.sx.objects.items.ShieldCaptainAmerica;
import com.sx_dev.sx.objects.items.TestItem;
import com.sx_dev.sx.objects.tools.ToolSword;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.interfaces.IOreDict;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemInit 
{
    public static final List<IOreDict> MOD_ORE_DICT = new ArrayList<>();
    public static final List<Item> ITEMS = new ArrayList<>();
	
	//Materials
    public static final IItemTier TOOL_DEADPOOL = new CustomItemTier("tool_deadpool", 0, 10000, 1.0F, 5.0F, 5, () -> Ingredient.EMPTY);
    public static final IItemTier SWORD_HEIMDAL = new CustomItemTier("sword_heimdal", 0, 10000, 1.0F, 15.0F, 6, () -> Ingredient.EMPTY);

    public static final ArmorMaterial ARMOR_DEADPOOL = ArmorMaterial.create("armor_deadpool", Reference.RESOURCE_PREFIX + "deadpool", 500, new int[]{5, 8, 10, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3.0F,() -> null);
    public static final ArmorMaterial ARMOR_SUPERBOY = ArmorMaterial.create("armor_superboy", Reference.RESOURCE_PREFIX + "superboy", 550, new int[]{10, 10, 10}, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 4.0F,() -> null);
    public static final ArmorMaterial ARMOR_KIDFLASH = ArmorMaterial.create("armor_kidflash", Reference.RESOURCE_PREFIX + "kidflash", 400, new int[]{4, 7, 10, 4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.5F,() -> null);
    public static final ArmorMaterial ARMOR_BLACKPANTHER = ArmorMaterial.create("armor_blackpanther", Reference.RESOURCE_PREFIX + "blackpanther", 1000, new int[]{8, 10, 9, 6}, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.5F,() -> null);
    public static final ArmorMaterial ARMOR_IRONMAN = ArmorMaterial.create("armor_ironman", Reference.RESOURCE_PREFIX + "ironman", 16000, new int[]{7, 9, 8, 5}, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.7F,() -> null);
    public static final ArmorMaterial ARMOR_ROBIN1 = ArmorMaterial.create("armor_robin1", Reference.RESOURCE_PREFIX + "robin1", 370, new int[]{4, 7, 9, 4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.4F,() -> null);
    public static final ArmorMaterial ARMOR_CAPTAIN_AMERICA = ArmorMaterial.create("armor_captain_america", Reference.RESOURCE_PREFIX + "captain_america", 500, new int[]{4, 7, 10, 4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.8F,() -> null);
    public static final ArmorMaterial ARMOR_REDHOOD = ArmorMaterial.create("armor_redhood", Reference.RESOURCE_PREFIX + "redhood", 500, new int[]{4, 7, 10, 4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.8F,() -> null);
    public static final ArmorMaterial ARMOR_ANTMAN = ArmorMaterial.create("armor_antman", Reference.RESOURCE_PREFIX + "antman", 500, new int[]{2, 3, 5, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.5F,() -> null);


    public static final Item TEST_ITEM;

	//Items
    public static final Item SPANDEX_RED;
    public static final Item SPANDEX_BLACK;
    public static final Item SPANDEX_YELLOW;
    public static final Item SPANDEX_WHITE;
    public static final Item SPANDEX_GREEN;

    public static final Item SPANDEX_CLOTH_RED;
    public static final Item SPANDEX_CLOTH_BLACK;
    public static final Item SPANDEX_CLOTH_YELLOW;
    public static final Item SPANDEX_CLOTH_WHITE;
    public static final Item SPANDEX_CLOTH_GREEN;

    public static final Item CLOTH_VIBRANIUM;
    public static final Item STRENGTHENED_CLOTH;
    public static final Item CLOTH;

    public static final Item LOGO_KID_FLASH;

    public static final Item SHIELD_HANDLE;


    public static final Item INGOT_VIBRANIUM;
    public static final Item INGOT_PALLADIUM;
    public static final Item INGOT_TITANIUM;
    public static final Item NUGGET_PALLADIUM;
    public static final Item NUGGET_TITANIUM;
    public static final Item PLATE_VIBRANIUM;
    public static final Item PLATE_TITANIUM;
    public static final Item PLATE_GOLD;
    public static final Item DUST_PALLADIUM;
    public static final Item DUST_TITANIUM;
    public static final Item INGOT_TITANOGOLD;
    public static final Item PLATE_TITANOGOLD;
    public static final Item CIRCUIT;
    public static final Item MINI_ARC_REACTOR_BASE;
    public static final Item MINI_ARC_REACTOR_MK1;
    public static final Item MINI_ARC_REACTOR_MK2;
    public static final Item SHIELD_CAPTAIN_AMERICA_UNCOLORED;


	
	//Tools
    public static final Item KATANA_DEADPOOL;
    public static final Item SHIELD_CAPTAIN_AMERICA;

    //Armor
    public static final Item HELMET_DEADPOOL;
    public static final Item CHESTPLATE_DEADPOOL;
    public static final Item LEGGINGS_DEADPOOL;
    public static final Item BOOTS_DEADPOOL;

    public static final Item CHESTPLATE_SUPERBOY;
    public static final Item LEGGINGS_SUPERBOY;
    public static final Item BOOTS_SUPERBOY;

    public static final Item HELMET_KIDFLASH;
    public static final Item CHESTPLATE_KIDFLASH;
    public static final Item LEGGINGS_KIDFLASH;
    public static final Item BOOTS_KIDFLASH;

    public static final Item HELMET_BLACKPANTHER;
    public static final Item CHESTPLATE_BLACKPANTHER;
    public static final Item LEGGINGS_BLACKPANTHER;
    public static final Item BOOTS_BLACKPANTHER;

    public static final Item HELMET_ROBIN1;
    public static final Item CHESTPLATE_ROBIN1;
    public static final Item LEGGINGS_ROBIN1;
    public static final Item BOOTS_ROBIN1;

    public static final Item HELMET_IRONMAN;
    public static final ChestplateIronMan CHESTPLATE_IRONMAN;
    public static final Item LEGGINGS_IRONMAN;
    public static final Item BOOTS_IRONMAN;

    public static final Item HELMET_CAPTAIN_AMERICA;
    public static final Item CHESTPLATE_CAPTAIN_AMERICA;
    public static final Item LEGGINGS_CAPTAIN_AMERICA;
    public static final Item BOOTS_CAPTAIN_AMERICA;
    
    public static final Item HELMET_REDHOOD;
    public static final Item CHESTPLATE_REDHOOD;
    public static final Item LEGGINGS_REDHOOD;
    public static final Item BOOTS_REDHOOD;

    public static final Item HELMET_ANTMAN;
    public static final Item CHESTPLATE_ANTMAN;
    public static final Item LEGGINGS_ANTMAN;
    public static final Item BOOTS_ANTMAN;

    public static final Item HELMET_THE_WASP;
    public static final Item CHESTPLATE_THE_WASP;
    public static final Item LEGGINGS_THE_WASP;
    public static final Item BOOTS_THE_WASP;

    //LOGOS
    public static final Item LOGO_DC;
    public static final Item LOGO_MARVEL;


    static {

        LOGO_DC = new ItemBase("logo_dc");
        LOGO_MARVEL = new ItemBase("logo_marvel");


        //MARVEL
        if (/*ModConfig.common.marvelItems*/true) {
            //MCU
            INGOT_PALLADIUM = new ItemBase("ingot_palladium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS))
                    .setOreDictName("ingotPalladium");
            INGOT_VIBRANIUM = new ItemBase("ingot_vibranium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS))
                    .setOreDictName("ingotVibranium");
            INGOT_TITANOGOLD = new ItemBase("ingot_titanogold", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS))
                    .setOreDictName("ingotTitanogold");
            PLATE_TITANOGOLD = new ItemBase("plate_titanogold", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS))
                    .setOreDictName("plateTitanogold");
            PLATE_VIBRANIUM = new ItemBase("plate_vibranium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS))
                    .setOreDictName("plateVibranium");
            DUST_PALLADIUM = new ItemBase("dust_palladium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS))
                    .setOreDictName("dustPalladium");
            NUGGET_PALLADIUM = new ItemBase("nugget_palladium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS))
                    .setOreDictName("nuggetPalladium");


            CLOTH_VIBRANIUM = new ItemBase("cloth_vibranium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
            STRENGTHENED_CLOTH = new ItemBase("strengthened_cloth", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));

            MINI_ARC_REACTOR_BASE = new ItemBase("mini_arc_reactor_base", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
            MINI_ARC_REACTOR_MK1 = new ItemBase("mini_arc_reactor_mk1", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
            MINI_ARC_REACTOR_MK2 = new ItemBase("mini_arc_reactor_mk2", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));

            //MCU
            HELMET_BLACKPANTHER = new ArmorBlackPanther("blackpanther_helmet", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.HEAD);
            CHESTPLATE_BLACKPANTHER = new ArmorBlackPanther("blackpanther_chestplate", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.CHEST);
            LEGGINGS_BLACKPANTHER = new ArmorBlackPanther("blackpanther_leggings", ARMOR_BLACKPANTHER, 2, EntityEquipmentSlot.LEGS);
            BOOTS_BLACKPANTHER = new ArmorBlackPanther("blackpanther_boots", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.FEET);

            HELMET_IRONMAN = new ArmorIronMan("ironman_helmet", 1, EntityEquipmentSlot.HEAD, new Item.Properties());
            CHESTPLATE_IRONMAN = new ChestplateIronMan();
            LEGGINGS_IRONMAN = new ArmorIronMan("ironman_leggings", 2, EntityEquipmentSlot.LEGS, new Item.Properties());
            BOOTS_IRONMAN = new ArmorIronMan("ironman_boots", 1, EntityEquipmentSlot.FEET, new Item.Properties());

            SHIELD_CAPTAIN_AMERICA = new ShieldCaptainAmerica();
            SHIELD_CAPTAIN_AMERICA_UNCOLORED = new ItemBase("shield_captain_america_uncolored");
            SHIELD_HANDLE = new ItemBase("shield_handle");

            HELMET_CAPTAIN_AMERICA = new ArmorCaptainAmerica("captain_america_helmet", ARMOR_CAPTAIN_AMERICA, 1, EntityEquipmentSlot.HEAD);
            CHESTPLATE_CAPTAIN_AMERICA = new ArmorCaptainAmerica("captain_america_chestplate", ARMOR_CAPTAIN_AMERICA, 1, EntityEquipmentSlot.CHEST);
            LEGGINGS_CAPTAIN_AMERICA = new ArmorCaptainAmerica("captain_america_leggings", ARMOR_CAPTAIN_AMERICA, 2, EntityEquipmentSlot.LEGS);
            BOOTS_CAPTAIN_AMERICA = new ArmorCaptainAmerica("captain_america_boots", ARMOR_CAPTAIN_AMERICA, 1, EntityEquipmentSlot.FEET);

            HELMET_ANTMAN = new ArmorAntman("antman_helmet", ARMOR_ANTMAN, 1, EntityEquipmentSlot.HEAD, ArmorAntman.Type.ANTMAN);
            CHESTPLATE_ANTMAN = new ArmorAntman.ChestplateAntman("antman_chestplate", ARMOR_ANTMAN, 1, EntityEquipmentSlot.CHEST, ArmorAntman.Type.ANTMAN);
            LEGGINGS_ANTMAN = new ArmorAntman("antman_leggings", ARMOR_ANTMAN, 2, EntityEquipmentSlot.LEGS, ArmorAntman.Type.ANTMAN);
            BOOTS_ANTMAN = new ArmorAntman("antman_boots", ARMOR_ANTMAN, 1, EntityEquipmentSlot.FEET, ArmorAntman.Type.ANTMAN);

            HELMET_THE_WASP = new ArmorAntman("the_wasp_helmet", ARMOR_ANTMAN, 1, EntityEquipmentSlot.HEAD, ArmorAntman.Type.THE_WASP);
            CHESTPLATE_THE_WASP = new ArmorAntman.ChestplateAntman("the_wasp_chestplate", ARMOR_ANTMAN, 1, EntityEquipmentSlot.CHEST, ArmorAntman.Type.THE_WASP);
            LEGGINGS_THE_WASP = new ArmorAntman("the_wasp_leggings", ARMOR_ANTMAN, 2, EntityEquipmentSlot.LEGS, ArmorAntman.Type.THE_WASP);
            BOOTS_THE_WASP = new ArmorAntman("the_wasp_boots", ARMOR_ANTMAN, 1, EntityEquipmentSlot.FEET, ArmorAntman.Type.THE_WASP);



            //XMen
            HELMET_DEADPOOL = new ArmorDeadpool("deadpool_helmet", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.HEAD);
            CHESTPLATE_DEADPOOL = new ArmorDeadpool("deadpool_chestplate", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.CHEST);
            LEGGINGS_DEADPOOL = new ArmorDeadpool("deadpool_leggings", ARMOR_DEADPOOL, 2, EntityEquipmentSlot.LEGS);
            BOOTS_DEADPOOL = new ArmorDeadpool("deadpool_boots", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.FEET);

            KATANA_DEADPOOL = new ToolSword("deadpool_katana", TOOL_DEADPOOL);


        } else {
            INGOT_PALLADIUM = null;
            INGOT_VIBRANIUM = null;
            INGOT_TITANOGOLD = null;
            PLATE_TITANOGOLD = null;
            PLATE_VIBRANIUM = null;

            DUST_PALLADIUM = null;
            NUGGET_PALLADIUM = null;

            CLOTH_VIBRANIUM = null;
            STRENGTHENED_CLOTH = null;

            MINI_ARC_REACTOR_BASE = null;
            MINI_ARC_REACTOR_MK1 = null;
            MINI_ARC_REACTOR_MK2 = null;

            HELMET_BLACKPANTHER = null;
            CHESTPLATE_BLACKPANTHER = null;
            LEGGINGS_BLACKPANTHER = null;
            BOOTS_BLACKPANTHER = null;

            HELMET_IRONMAN = null;
            CHESTPLATE_IRONMAN = null;
            LEGGINGS_IRONMAN = null;
            BOOTS_IRONMAN = null;

            SHIELD_CAPTAIN_AMERICA = null;
            SHIELD_CAPTAIN_AMERICA_UNCOLORED = null;
            SHIELD_HANDLE = null;

            HELMET_CAPTAIN_AMERICA = null;
            CHESTPLATE_CAPTAIN_AMERICA = null;
            LEGGINGS_CAPTAIN_AMERICA = null;
            BOOTS_CAPTAIN_AMERICA = null;

            HELMET_ANTMAN = null;
            CHESTPLATE_ANTMAN = null;
            LEGGINGS_ANTMAN = null;
            BOOTS_ANTMAN = null;

            HELMET_THE_WASP = null;
            CHESTPLATE_THE_WASP = null;
            LEGGINGS_THE_WASP = null;
            BOOTS_THE_WASP = null;

            //XMen
            HELMET_DEADPOOL = null;
            CHESTPLATE_DEADPOOL = null;
            LEGGINGS_DEADPOOL = null;
            BOOTS_DEADPOOL = null;

            KATANA_DEADPOOL = null;
        }

        //DC
        if (/*ModConfig.common.dcItems*/true) {
            LOGO_KID_FLASH = new ItemBase("kidflash_logo");

            CHESTPLATE_SUPERBOY = new ArmorSuperboy("superboy_chestplate", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.CHEST);
            LEGGINGS_SUPERBOY = new ArmorSuperboy("superboy_leggings", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.LEGS);
            BOOTS_SUPERBOY = new ArmorSuperboy("superboy_boots", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.FEET);

            HELMET_REDHOOD = new ArmorRedHood("helmet_redhood", ARMOR_REDHOOD, 1, EntityEquipmentSlot.HEAD);
            CHESTPLATE_REDHOOD = new ArmorRedHood("chestplate_redhood", ARMOR_REDHOOD, 1, EntityEquipmentSlot.CHEST);
            LEGGINGS_REDHOOD = new ArmorRedHood("leggings_redhood", ARMOR_REDHOOD, 2, EntityEquipmentSlot.LEGS);
            BOOTS_REDHOOD = new ArmorRedHood("boots_redhood", ARMOR_REDHOOD, 1, EntityEquipmentSlot.FEET);

            //CW
            HELMET_KIDFLASH = new ArmorKidFlash("kidflash_helmet", ARMOR_KIDFLASH, 1, EntityEquipmentSlot.HEAD);
            CHESTPLATE_KIDFLASH = new ArmorKidFlash("kidflash_chestplate", ARMOR_KIDFLASH, 1, EntityEquipmentSlot.CHEST);
            LEGGINGS_KIDFLASH = new ArmorKidFlash("kidflash_leggings", ARMOR_KIDFLASH, 2, EntityEquipmentSlot.LEGS);
            BOOTS_KIDFLASH = new ArmorKidFlash("kidflash_boots", ARMOR_KIDFLASH, 1, EntityEquipmentSlot.FEET);

            //Teen Titans
            HELMET_ROBIN1 = new ArmorRobin1("robin1_helmet", ARMOR_ROBIN1, 1, EntityEquipmentSlot.HEAD);
            CHESTPLATE_ROBIN1 = new ArmorRobin1("robin1_chestplate", ARMOR_ROBIN1, 1, EntityEquipmentSlot.CHEST);
            LEGGINGS_ROBIN1 = new ArmorRobin1("robin1_leggings", ARMOR_ROBIN1, 2, EntityEquipmentSlot.LEGS);
            BOOTS_ROBIN1 = new ArmorRobin1("robin1_boots", ARMOR_ROBIN1, 1, EntityEquipmentSlot.FEET);
        } else {
            LOGO_KID_FLASH = null;

            //DC
            CHESTPLATE_SUPERBOY = null;
            LEGGINGS_SUPERBOY = null;
            BOOTS_SUPERBOY = null;

            HELMET_REDHOOD = null;
            CHESTPLATE_REDHOOD = null;
            LEGGINGS_REDHOOD = null;
            BOOTS_REDHOOD = null;

            //CW
            HELMET_KIDFLASH = null;
            CHESTPLATE_KIDFLASH = null;
            LEGGINGS_KIDFLASH = null;
            BOOTS_KIDFLASH = null;

            //Teen Titans
            HELMET_ROBIN1 = null;
            CHESTPLATE_ROBIN1 = null;
            LEGGINGS_ROBIN1 = null;
            BOOTS_ROBIN1 = null;
        }

        //Materials
        SPANDEX_GREEN = new ItemBase("spandex_green", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        SPANDEX_WHITE = new ItemBase("spandex_white", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        SPANDEX_YELLOW = new ItemBase("spandex_yellow", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        SPANDEX_BLACK = new ItemBase("spandex_black", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        SPANDEX_RED = new ItemBase("spandex_red", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));

        SPANDEX_CLOTH_GREEN = new ItemBase("spandex_cloth_green", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        SPANDEX_CLOTH_WHITE = new ItemBase("spandex_cloth_white", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        SPANDEX_CLOTH_YELLOW = new ItemBase("spandex_cloth_yellow", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        SPANDEX_CLOTH_BLACK = new ItemBase("spandex_cloth_black", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        SPANDEX_CLOTH_RED = new ItemBase("spandex_cloth_red", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));
        CLOTH = new ItemBase("cloth", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));

        /*if (!OreDictionary.doesOreNameExist("plateGold")) {*/
        PLATE_GOLD = new ItemBase("plate_gold", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS)).setOreDictName("plateGold");
        /*} else {
            //PLATE_GOLD = OreDictionary.getOres("plateGold").get(0).getItem();
        }*/

        INGOT_TITANIUM = new ItemBase("ingot_titanium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS)).setOreDictName("ingotTitanium");
        PLATE_TITANIUM = new ItemBase("plate_titanium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS)).setOreDictName("plateTitanium");
        NUGGET_TITANIUM = new ItemBase("nugget_titanium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS)).setOreDictName("nuggetTitanium");
        DUST_TITANIUM = new ItemBase("dust_titanium", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS)).setOreDictName("dustTitanium");

        CIRCUIT = new ItemBase("circuit", new Item.Properties().group(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS));


        if (SuperheroesX.DEBUG) {
            TEST_ITEM = new TestItem();
        } else {
            TEST_ITEM = null;
        }
    }

    private static class CustomItemTier implements IItemTier {
        /** The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = WOOD/GOLD) */
        private final int harvestLevel;
        /** The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32) */
        private final int maxUses;
        /** The strength of this tool material against blocks which it is effective against. */
        private final float efficiency;
        /** Damage versus entities. */
        private final float attackDamage;
        /** Defines the natural enchantability factor of the material. */
        private final int enchantability;
        private final LazyLoadBase<Ingredient> repairMaterial;

        public CustomItemTier(String name, int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
            this.harvestLevel = harvestLevelIn;
            this.maxUses = maxUsesIn;
            this.efficiency = efficiencyIn;
            this.attackDamage = attackDamageIn;
            this.enchantability = enchantabilityIn;
            this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
        }

        public int getMaxUses() {
            return this.maxUses;
        }

        public float getEfficiency() {
            return this.efficiency;
        }

        public float getAttackDamage() {
            return this.attackDamage;
        }

        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        public int getEnchantability() {
            return this.enchantability;
        }

        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }
    }
}