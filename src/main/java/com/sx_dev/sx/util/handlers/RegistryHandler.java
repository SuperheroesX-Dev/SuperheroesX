package com.sx_dev.sx.util.handlers;


import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.BlockInit;
import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.init.PotionEffectInit;
import com.sx_dev.sx.init.PotionInit;
import com.sx_dev.sx.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Arrays;


@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(final Register<Item> event) {
        SuperheroesX.LOGGER.info("Registering Items: Start");
        event.getRegistry().registerAll(Arrays.stream(ItemInit.values()).parallel().filter(ItemInit::isEnabled).map(ItemInit::asItem).toArray(Item[]::new));
        event.getRegistry().registerAll(Arrays.stream(BlockInit.values()).parallel().filter(BlockInit::isEnabled).map(BlockInit::asItem).toArray(Item[]::new));
        SuperheroesX.LOGGER.info("Registering Items: Done");
    }

    @SubscribeEvent
    public static void onBlockRegister(final Register<Block> event) {
        SuperheroesX.LOGGER.info("Registering Blocks: Start");
        event.getRegistry().registerAll(Arrays.stream(BlockInit.values()).parallel().filter(BlockInit::isEnabled).map(BlockInit::asBlock).toArray(Block[]::new));
        SuperheroesX.LOGGER.info("Registering Blocks: Done");
    }

    /*private static void onFluidRegister(final Register<Block> event) {
        if (Integrations.TC) {
            FluidInit.FLUIDS.addAll(FluidInit.TC_FLUIDS);
        }
        *//*for (Fluid fluid : FluidInit.FLUIDS) {
            if (fluid != null) {
                //IRegistry.field_212619_h.put(fluid.getRegistryName(),fluid); // fluid has to be registered
                //FluidRegistry.addBucketForFluid(fluid); // add a bucket for the fluid
                //try {
                    //IRegistry.field_212619_h.put(new BlockFluidClassic(fluid, net.minecraft.block.material.Material.LAVA));
                //} catch (Exception ignored) {
                //}
            }

        }*//*
    }*/

    @SubscribeEvent
    public static void onPotionRegister(final Register<Potion> event) {
        event.getRegistry().registerAll(Arrays.stream(PotionEffectInit.values()).parallel().map(PotionEffectInit::asPotionEffect).toArray(Potion[]::new));
    }

    @SubscribeEvent
    public static void onEnchantmentRegister(final Register<Enchantment> event) {
        //event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray(new Enchantment[0]));
    }

    @SubscribeEvent
    public static void onPotionTypeRegister(final Register<PotionType> event) {
        Arrays.stream(PotionInit.values()).parallel().map(PotionInit::asPotion).forEach(potion -> {
            event.getRegistry().register(potion);
            PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), potion);
        });
    }

    @SubscribeEvent
    public static void onModelRegister(final ModelRegistryEvent event) {
    }

    public static void preInitRegistries(final FMLCommonSetupEvent event) {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> //GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
                RenderHandler::registerEntityRenders);
    }

    public static void initRegistries() {
    }

    public static void postInitRegistries() {
    }
}