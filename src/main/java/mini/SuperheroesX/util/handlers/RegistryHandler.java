package mini.SuperheroesX.util.handlers;


import mini.SuperheroesX.init.*;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.interfaces.IHasModel;
import mini.SuperheroesX.util.interfaces.IOreDict;
import mini.SuperheroesX.world.gen.WorldGenCustomOres;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;





@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onPotionRegister(RegistryEvent.Register<Potion> event) {
        event.getRegistry().registerAll(PotionInit.POTIONS.toArray(new Potion[0]));
    }

    @SubscribeEvent
    public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray(new Enchantment[0]));
    }

    @SubscribeEvent
    public static void onPotionTypeRegister(RegistryEvent.Register<PotionType> event) {
        PotionInit.POTION_ITEMS.add(new PotionType(new PotionEffect(PotionInit.GLIDE, 3600, 0)).setRegistryName(Reference.MODID, "fly"));
        event.getRegistry().registerAll(PotionInit.POTION_ITEMS.toArray(new PotionType[0]));
        for (PotionType potion : PotionInit.POTION_ITEMS) {
            PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), potion);
        }
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }
        for (Block block : BlockInit.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerModels();
            }
        }
    }

    public static void registerOres() {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IOreDict && ((IOreDict) item).hasOreDictName()) {
                OreDictionary.registerOre(((IOreDict) item).getOreDictName(), item);
            }
        }
        for (Block block : BlockInit.BLOCKS) {
            if (block instanceof IOreDict && ((IOreDict) block).hasOreDictName()) {
                OreDictionary.registerOre(((IOreDict) block).getOreDictName(), block);
            }
        }
    }

    public static void preInitRegistries() {
        EntityInit.registerEntities();
        RenderHandler.registerEntityRenders();
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);

    }

    public static void initRegistries() {
    	registerOres();
    }

    public static void postInitRegistries() {
    }

    public static void serverRegistries(FMLServerStartingEvent event) {
    }



}