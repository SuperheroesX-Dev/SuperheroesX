package mini.SuperheroesX.init;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.objects.armor.*;
import mini.SuperheroesX.objects.armor.ArmorIronMan.ChestplateIronMan;
import mini.SuperheroesX.objects.items.ItemBase;
import mini.SuperheroesX.objects.items.ShieldCaptainAmerica;
import mini.SuperheroesX.objects.items.TestItem;
import mini.SuperheroesX.objects.tools.ToolSword;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.config.Config;
import mini.SuperheroesX.util.interfaces.IOreDict;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ItemInit 
{
    public static final List<IOreDict> MOD_ORE_DICT = new ArrayList<>();
    public static final List<Item> ITEMS = new ArrayList<>();
	
	//Materials
    public static final ToolMaterial TOOL_DEADPOOL = EnumHelper.addToolMaterial("tool_deadpool", 0, 10000, 1.0F, 5.0F, 5);
    public static final ToolMaterial SWORD_HEIMDAL = EnumHelper.addToolMaterial("sword_heimdal", 0, 10000, 1.0F, 15.0F, 6);

    public static final ArmorMaterial ARMOR_DEADPOOL = EnumHelper.addArmorMaterial("armor_deadpool", Reference.RESOURCE_PREFIX + "deadpool", 500, new int[]{5, 8, 10, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3.0F);
    public static final ArmorMaterial ARMOR_SUPERBOY = EnumHelper.addArmorMaterial("armor_superboy", Reference.RESOURCE_PREFIX + "superboy", 550, new int[]{10, 10, 10}, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 4.0F);
    public static final ArmorMaterial ARMOR_KIDFLASH = EnumHelper.addArmorMaterial("armor_kidflash", Reference.RESOURCE_PREFIX + "kidflash", 400, new int[]{4, 7, 10, 4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.5F);
    public static final ArmorMaterial ARMOR_BLACKPANTHER = EnumHelper.addArmorMaterial("armor_blackpanther", Reference.RESOURCE_PREFIX + "blackpanther", 1000, new int[]{8, 10, 9, 6}, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.5F);
    public static final ArmorMaterial ARMOR_IRONMAN = EnumHelper.addArmorMaterial("armor_ironman", Reference.RESOURCE_PREFIX + "ironman", 16000, new int[]{7, 9, 8, 5}, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.7F);
    public static final ArmorMaterial ARMOR_ROBIN1 = EnumHelper.addArmorMaterial("armor_robin1", Reference.RESOURCE_PREFIX + "robin1", 370, new int[]{4, 7, 9, 4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.4F);
    public static final ArmorMaterial ARMOR_CAPTAIN_AMERICA = EnumHelper.addArmorMaterial("armor_captain_america", Reference.RESOURCE_PREFIX + "captain_america", 500, new int[]{4, 7, 10, 4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.8F);
    public static final ArmorMaterial ARMOR_REDHOOD = EnumHelper.addArmorMaterial("armor_redhood", Reference.RESOURCE_PREFIX + "redhood", 500, new int[]{4, 7, 10, 4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.8F);


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
   // public static final Item NUGGET_PALLADIUM;
   // public static final Item NUGGET_TITANIUM;
    public static final Item PLATE_VIBRANIUM;
    public static final Item PLATE_TITANIUM;
    public static final Item PLATE_GOLD;
    //public static final Item DUST_PALLADIUM;
    //public static final Item DUST_TITANIUM;
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


    //LOGOS
    public static final Item LOGO_DC;
    public static final Item LOGO_MARVEL;


    static {

        LOGO_DC = new ItemBase("logo_dc");
        LOGO_MARVEL = new ItemBase("logo_marvel");


        //MARVEL
        if (Config.marvelItems) {
            //MCU
            INGOT_PALLADIUM = new ItemBase("ingot_palladium").setOreDictName("ingotPalladium").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            INGOT_VIBRANIUM = new ItemBase("ingot_vibranium").setOreDictName("ingotVibranium").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            INGOT_TITANOGOLD = new ItemBase("ingot_titanogold").setOreDictName("ingotTitanogold").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            PLATE_TITANOGOLD = new ItemBase("plate_titanogold").setOreDictName("plateTitanogold").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            PLATE_VIBRANIUM = new ItemBase("plate_vibranium").setOreDictName("plateVibranium").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            //DUST_PALLADIUM = new ItemBase("dust_palladium").setOreDictName("dustPalladium").
                    //setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            //NUGGET_PALLADIUM = new ItemBase("nugget_palladium").setOreDictName("nuggetPalladium").
                    //setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);


            CLOTH_VIBRANIUM = new ItemBase("cloth_vibranium").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            STRENGTHENED_CLOTH = new ItemBase("strengthened_cloth").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);

            MINI_ARC_REACTOR_BASE = new ItemBase("mini_arc_reactor_base").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            MINI_ARC_REACTOR_MK1 = new ItemBase("mini_arc_reactor_mk1").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            MINI_ARC_REACTOR_MK2 = new ItemBase("mini_arc_reactor_mk2").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);

            //MCU
            HELMET_BLACKPANTHER = new ArmorBlackPanther("blackpanther_helmet", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.HEAD).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            CHESTPLATE_BLACKPANTHER = new ArmorBlackPanther("blackpanther_chestplate", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.CHEST).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            LEGGINGS_BLACKPANTHER = new ArmorBlackPanther("blackpanther_leggings", ARMOR_BLACKPANTHER, 2, EntityEquipmentSlot.LEGS).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            BOOTS_BLACKPANTHER = new ArmorBlackPanther("blackpanther_boots", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.FEET).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            
            HELMET_IRONMAN = new ArmorIronMan("helmet_ironman", 1, EntityEquipmentSlot.HEAD);
            CHESTPLATE_IRONMAN = (ChestplateIronMan) new ChestplateIronMan().
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            LEGGINGS_IRONMAN = new ArmorIronMan("leggings_ironman", 2, EntityEquipmentSlot.LEGS);
            BOOTS_IRONMAN = new ArmorIronMan("boots_ironman", 1, EntityEquipmentSlot.FEET);

            SHIELD_CAPTAIN_AMERICA = new ShieldCaptainAmerica().
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            SHIELD_CAPTAIN_AMERICA_UNCOLORED = new ItemBase("shield_captain_america_uncolored").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
            SHIELD_HANDLE = new ItemBase("shield_handle").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);

            HELMET_CAPTAIN_AMERICA = new ArmorCaptainAmerica("captain_america_helmet", ARMOR_CAPTAIN_AMERICA, 1, EntityEquipmentSlot.HEAD).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            CHESTPLATE_CAPTAIN_AMERICA = new ArmorCaptainAmerica("captain_america_chestplate", ARMOR_CAPTAIN_AMERICA, 1, EntityEquipmentSlot.CHEST).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            LEGGINGS_CAPTAIN_AMERICA = new ArmorCaptainAmerica("captain_america_leggings", ARMOR_CAPTAIN_AMERICA, 2, EntityEquipmentSlot.LEGS).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            BOOTS_CAPTAIN_AMERICA = new ArmorCaptainAmerica("captain_america_boots", ARMOR_CAPTAIN_AMERICA, 1, EntityEquipmentSlot.FEET).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);



            //XMen
            HELMET_DEADPOOL = new ArmorDeadpool("deadpool_helmet", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.HEAD).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            CHESTPLATE_DEADPOOL = new ArmorDeadpool("deadpool_chestplate", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.CHEST).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            LEGGINGS_DEADPOOL = new ArmorDeadpool("deadpool_leggings", ARMOR_DEADPOOL, 2, EntityEquipmentSlot.LEGS).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            BOOTS_DEADPOOL = new ArmorDeadpool("deadpool_boots", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.FEET).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);

            KATANA_DEADPOOL = new ToolSword("deadpool_katana", TOOL_DEADPOOL).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MARVEL);
            
            
        } else {
            INGOT_PALLADIUM = null;
            INGOT_VIBRANIUM = null;
            INGOT_TITANOGOLD = null;
            PLATE_TITANOGOLD = null;
            PLATE_VIBRANIUM = null;

           // DUST_PALLADIUM = null;
            //NUGGET_PALLADIUM = null;


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

            //XMen
            HELMET_DEADPOOL = null;
            CHESTPLATE_DEADPOOL = null;
            LEGGINGS_DEADPOOL = null;
            BOOTS_DEADPOOL = null;

            KATANA_DEADPOOL = null;
        }

        //DC
        if (Config.dcItems) {
            LOGO_KID_FLASH = new ItemBase("kidflash_logo").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            
            CHESTPLATE_SUPERBOY = new ArmorBase("superboy_chestplate", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.CHEST).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            LEGGINGS_SUPERBOY = new ArmorBase("superboy_leggings", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.LEGS).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            BOOTS_SUPERBOY = new ArmorBase("superboy_boots", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.FEET).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            
            HELMET_REDHOOD = new ArmorRedHood("helmet_redhood", ARMOR_REDHOOD, 1, EntityEquipmentSlot.HEAD).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            CHESTPLATE_REDHOOD = new ArmorRedHood("chestplate_redhood", ARMOR_REDHOOD, 1, EntityEquipmentSlot.CHEST).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            LEGGINGS_REDHOOD = new ArmorRedHood("leggings_redhood", ARMOR_REDHOOD, 2, EntityEquipmentSlot.LEGS).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            BOOTS_REDHOOD = new ArmorRedHood("boots_redhood", ARMOR_REDHOOD, 1, EntityEquipmentSlot.FEET).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);

            //CW
            HELMET_KIDFLASH = new ArmorKidFlash("kidflash_helmet", ARMOR_KIDFLASH, 1, EntityEquipmentSlot.HEAD).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            CHESTPLATE_KIDFLASH = new ArmorKidFlash("kidflash_chestplate", ARMOR_KIDFLASH, 1, EntityEquipmentSlot.CHEST).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            LEGGINGS_KIDFLASH = new ArmorKidFlash("kidflash_leggings", ARMOR_KIDFLASH, 2, EntityEquipmentSlot.LEGS).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            BOOTS_KIDFLASH = new ArmorKidFlash("kidflash_boots", ARMOR_KIDFLASH, 1, EntityEquipmentSlot.FEET).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);

            //Teen Titans
            HELMET_ROBIN1 = new ArmorRobin1("robin1_helmet", ARMOR_ROBIN1, 1, EntityEquipmentSlot.HEAD).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            CHESTPLATE_ROBIN1 = new ArmorRobin1("robin1_chestplate", ARMOR_ROBIN1, 1, EntityEquipmentSlot.CHEST).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            LEGGINGS_ROBIN1 = new ArmorRobin1("robin1_leggings", ARMOR_ROBIN1, 2, EntityEquipmentSlot.LEGS).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
            BOOTS_ROBIN1 = new ArmorRobin1("robin1_boots", ARMOR_ROBIN1, 1, EntityEquipmentSlot.FEET).
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_DC);
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
        SPANDEX_GREEN = new ItemBase("spandex_green").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        SPANDEX_WHITE = new ItemBase("spandex_white").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        SPANDEX_YELLOW = new ItemBase("spandex_yellow").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        SPANDEX_BLACK = new ItemBase("spandex_black").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        SPANDEX_RED = new ItemBase("spandex_red").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);

        SPANDEX_CLOTH_GREEN = new ItemBase("spandex_cloth_green").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        SPANDEX_CLOTH_WHITE = new ItemBase("spandex_cloth_white").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        SPANDEX_CLOTH_YELLOW = new ItemBase("spandex_cloth_yellow").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        SPANDEX_CLOTH_BLACK = new ItemBase("spandex_cloth_black").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        SPANDEX_CLOTH_RED = new ItemBase("spandex_cloth_red").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        CLOTH = new ItemBase("cloth").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);


        if (!Loader.isModLoaded("thermalfoundation")) {
            PLATE_GOLD = new ItemBase("plate_gold").setOreDictName("plateGold").
                    setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        } else {
            PLATE_GOLD = OreDictionary.getOres("plateGold").get(0).getItem();
        }


        INGOT_TITANIUM = new ItemBase("ingot_titanium").setOreDictName("ingotTitanium").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        PLATE_TITANIUM = new ItemBase("plate_titanium").setOreDictName("plateTitanium").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        //NUGGET_TITANIUM = new ItemBase("nugget_titanium").setOreDictName("nuggetTitanium").
                //setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);
        //DUST_TITANIUM = new ItemBase("dust_titanium").setOreDictName("dustTitanium").
                //setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);

        CIRCUIT = new ItemBase("circuit").
                setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);


        if (SuperheroesX.DEBUG) {
            TEST_ITEM = new TestItem();
        } else {
            TEST_ITEM = null;
        }
    }
}