package mini.SuperheroesX.init;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.objects.armor.*;
import mini.SuperheroesX.objects.armor.ArmorIronMan.ChestplateIronMan;
import mini.SuperheroesX.objects.items.ItemBase;
import mini.SuperheroesX.objects.items.TestItem;
import mini.SuperheroesX.objects.tools.ToolSword;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.misc.Element;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit 
{
    public static final List<Item> ITEMS = new ArrayList<>();
	
	//Materials
	public static final ToolMaterial TOOL_DEADPOOL = EnumHelper.addToolMaterial("tool_deadpool", 0, 10000, 1.0F, 5.0F, 5);
    public static final ArmorMaterial ARMOR_DEADPOOL = EnumHelper.addArmorMaterial("armor_deadpool", Reference.RESOURCE_PREFIX + "deadpool", 500, new int[]{5, 8, 10, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3.0F);
    public static final ArmorMaterial ARMOR_SUPERBOY = EnumHelper.addArmorMaterial("armor_superboy", Reference.RESOURCE_PREFIX + "superboy", 550, new int[]{10, 10, 10}, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 4.0F);
    public static final ArmorMaterial ARMOR_KIDFLASH = EnumHelper.addArmorMaterial("armor_kidflash", Reference.RESOURCE_PREFIX + "kidflash", 400, new int[]{4, 7, 10, 4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.5F);
    public static final ArmorMaterial ARMOR_BLACKPANTHER = EnumHelper.addArmorMaterial("armor_blackpanther", Reference.RESOURCE_PREFIX + "blackpanther", 1000, new int[]{8, 10, 9, 6}, 14, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.5F);
    public static final ArmorMaterial ARMOR_IRONMAN = EnumHelper.addArmorMaterial("armor_ironman", Reference.RESOURCE_PREFIX + "ironman", 16000, new int[]{7, 9, 8, 5}, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.7F);
    public static final ArmorMaterial ARMOR_ROBIN1 = EnumHelper.addArmorMaterial("armor_robin1", Reference.RESOURCE_PREFIX + "robin1", 370, new int[]{4, 7, 9, 4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.4F);


    public static final Element<Item> TEST_ITEM;

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

    public static final Item LOGO_KID_FLASH;


    public static final Item INGOT_VIBRANIUM;
    public static final Item INGOT_PALLADIUM;
    public static final Item INGOT_TITANIUM;
    public static final Item INGOT_TITANOGOLD;
    public static final Item PLATE_VIBRANIUM;
    public static final Item PLATE_TITANIUM;
    public static final Item PLATE_GOLD;
    public static final Item PLATE_TITANOGOLD;
    public static final Item CIRCUIT;
    public static final Item MINI_ARC_REACTOR_BASE;
    public static final Item MINI_ARC_REACTOR_MK1;
    public static final Item MINI_ARC_REACTOR_MK2;



	
	//Tools
    public static final Item KATANA_DEADPOOL;

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


    static {


        //MARVEL
        //MCU
        INGOT_PALLADIUM = new ItemBase("ingot_palladium").setOreDictName("ingotPalladium");
        INGOT_VIBRANIUM = new ItemBase("ingot_vibranium").setOreDictName("ingotVibranium");
        INGOT_TITANOGOLD = new ItemBase("ingot_titanogold").setOreDictName("ingotTitanogold");
        PLATE_TITANOGOLD = new ItemBase("plate_titanogold").setOreDictName("plateTitanogold");
        PLATE_VIBRANIUM = new ItemBase("plate_vibranium").setOreDictName("plateVibranium");
        MINI_ARC_REACTOR_BASE = new ItemBase("mini_arc_reactor_base");
        MINI_ARC_REACTOR_MK1 = new ItemBase("mini_arc_reactor_mk1");
        MINI_ARC_REACTOR_MK2 = new ItemBase("mini_arc_reactor_mk2");

        HELMET_BLACKPANTHER = new ArmorBlackPanther("blackpanther_helmet", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.HEAD);
        CHESTPLATE_BLACKPANTHER = new ArmorBlackPanther("blackpanther_chestplate", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.CHEST);
        LEGGINGS_BLACKPANTHER = new ArmorBlackPanther("blackpanther_leggings", ARMOR_BLACKPANTHER, 2, EntityEquipmentSlot.LEGS);
        BOOTS_BLACKPANTHER = new ArmorBlackPanther("blackpanther_boots", ARMOR_BLACKPANTHER, 1, EntityEquipmentSlot.FEET);

        HELMET_IRONMAN = new ArmorIronMan("helmet_ironman", 1, EntityEquipmentSlot.HEAD);
        CHESTPLATE_IRONMAN = new ChestplateIronMan();
        LEGGINGS_IRONMAN = new ArmorIronMan("leggings_ironman", 2, EntityEquipmentSlot.LEGS);
        BOOTS_IRONMAN = new ArmorIronMan("boots_ironman", 1, EntityEquipmentSlot.FEET);


        //XMen
        HELMET_DEADPOOL = new ArmorDeadpool("deadpool_helmet", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.HEAD);
        CHESTPLATE_DEADPOOL = new ArmorDeadpool("deadpool_chestplate", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.CHEST);
        LEGGINGS_DEADPOOL = new ArmorDeadpool("deadpool_leggings", ARMOR_DEADPOOL, 2, EntityEquipmentSlot.LEGS);
        BOOTS_DEADPOOL = new ArmorDeadpool("deadpool_boots", ARMOR_DEADPOOL, 1, EntityEquipmentSlot.FEET);

        KATANA_DEADPOOL = new ToolSword("deadpool_katana", TOOL_DEADPOOL);


        //DC
        LOGO_KID_FLASH = new ItemBase("kidflash_logo");

        CHESTPLATE_SUPERBOY = new ArmorBase("superboy_chestplate", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.CHEST);
        LEGGINGS_SUPERBOY = new ArmorBase("superboy_leggings", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.LEGS);
        BOOTS_SUPERBOY = new ArmorBase("superboy_boots", ARMOR_SUPERBOY, 1, EntityEquipmentSlot.FEET);

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


        //Materials
        SPANDEX_GREEN = new ItemBase("spandex_green");
        SPANDEX_WHITE = new ItemBase("spandex_white");
        SPANDEX_YELLOW = new ItemBase("spandex_yellow");
        SPANDEX_BLACK = new ItemBase("spandex_black");
        SPANDEX_RED = new ItemBase("spandex_red");

        SPANDEX_CLOTH_GREEN = new ItemBase("spandex_cloth_green");
        SPANDEX_CLOTH_WHITE = new ItemBase("spandex_cloth_white");
        SPANDEX_CLOTH_YELLOW = new ItemBase("spandex_cloth_yellow");
        SPANDEX_CLOTH_BLACK = new ItemBase("spandex_cloth_black");
        SPANDEX_CLOTH_RED = new ItemBase("spandex_cloth_red");

        INGOT_TITANIUM = new ItemBase("ingot_titanium").setOreDictName("ingotTitanium");
        PLATE_GOLD = new ItemBase("plate_gold").setOreDictName("plateGold");
        PLATE_TITANIUM = new ItemBase("plate_titanium").setOreDictName("plateTitanium");
        CIRCUIT = new ItemBase("circuit");


        TEST_ITEM = new Element<>();
        if (SuperheroesX.DEBUG) {
            TEST_ITEM.setObj(new TestItem());
        }
    }
}