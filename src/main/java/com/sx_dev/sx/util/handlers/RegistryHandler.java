package com.sx_dev.sx.util.handlers;


import com.sx_dev.sx.init.*;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.integration.Integrations;
import com.sx_dev.sx.util.interfaces.IHasModel;
//import com.sx_dev.sx.util.interfaces.IOreDict;
//import com.sx_dev.sx.world.gen.WorldGenCustomOres;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;*/
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;*/


@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(final Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
        /*for (IOreDict oreDictEntry : ItemInit.MOD_ORE_DICT) {
            if (oreDictEntry.hasOreDictName() && (oreDictEntry.getEntry() instanceof Item || oreDictEntry.getEntry() instanceof Block)) {
                if (oreDictEntry.getEntry() instanceof Item) {
                    OreDictionary.registerOre(oreDictEntry.getOreDictName(), (Item) oreDictEntry.getEntry());
                }
                if (oreDictEntry.getEntry() instanceof Block) {
                    OreDictionary.registerOre(oreDictEntry.getOreDictName(), (Block) oreDictEntry.getEntry());
                }
            }
        }*/
    }

    @SubscribeEvent
    public static void onBlockRegister(final Register<Block> event) {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));

        onFluidRegister(event);
    }

    private static void onFluidRegister(final Register<Block> event) {
        if (Integrations.TC) {
            FluidInit.FLUIDS.addAll(FluidInit.TC_FLUIDS);
        }
        for (Fluid fluid : FluidInit.FLUIDS) {
            if (fluid != null) {
                //IRegistry.field_212619_h.put(fluid.getRegistryName(),fluid); // fluid has to be registered
                //FluidRegistry.addBucketForFluid(fluid); // add a bucket for the fluid
                //try {
                    //IRegistry.field_212619_h.put(new BlockFluidClassic(fluid, net.minecraft.block.material.Material.LAVA));
                //} catch (Exception ignored) {
                //}
            }

        }
    }

    @SubscribeEvent
    public static void onPotionRegister(Register<Potion> event) {
        event.getRegistry().registerAll(PotionInit.POTIONS.toArray(new Potion[0]));
    }

    @SubscribeEvent
    public static void onEnchantmentRegister(Register<Enchantment> event) {
        event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray(new Enchantment[0]));
    }

    @SubscribeEvent
    public static void onPotionTypeRegister(Register<PotionType> event) {
        PotionInit.POTION_ITEMS.add(new PotionType(new PotionEffect(PotionInit.GLIDE, 3600, 0)).setRegistryName(Reference.MODID, "fly"));
        event.getRegistry().registerAll(PotionInit.POTION_ITEMS.toArray(new PotionType[0]));
        for (PotionType potion : PotionInit.POTION_ITEMS) {
            PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), potion);
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

    public static void preInitRegistries(FMLCommonSetupEvent event) {
        EntityInit.registerEntities();
        //if (event.getSide() == Side.CLIENT) {
            //RenderHandler.registerEntityRenders();
            //GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
        //}
    }

    public static void initRegistries() {
    }

    public static void postInitRegistries() {
    }
}