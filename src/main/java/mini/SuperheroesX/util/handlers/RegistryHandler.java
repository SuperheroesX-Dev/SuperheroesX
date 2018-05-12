package mini.SuperheroesX.util.handlers;


import mini.SuperheroesX.init.*;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.integration.Integrations;
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
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;


@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
        for (IOreDict oreDictEntry : ItemInit.MOD_ORE_DICT) {
            if (oreDictEntry.hasOreDictName() && (oreDictEntry.getEntry() instanceof Item || oreDictEntry.getEntry() instanceof Block)) {
                if (oreDictEntry.getEntry() instanceof Item) {
                    OreDictionary.registerOre(oreDictEntry.getOreDictName(), (Item) oreDictEntry.getEntry());
                }
                if (oreDictEntry.getEntry() instanceof Block) {
                    OreDictionary.registerOre(oreDictEntry.getOreDictName(), (Block) oreDictEntry.getEntry());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockRegister(Register<Block> event) {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));

        onFluidRegister(event);
    }

    private static void onFluidRegister(Register<Block> event) {
        if (Integrations.TC) {
            FluidInit.FLUIDS.addAll(FluidInit.TC_FLUIDS);
        }
        for (Fluid fluid : FluidInit.FLUIDS) {
            if (fluid != null) {
                FluidRegistry.registerFluid(fluid); // fluid has to be registered
                FluidRegistry.addBucketForFluid(fluid); // add a bucket for the fluid
                try {
                    event.getRegistry().register(new BlockFluidClassic(fluid, net.minecraft.block.material.Material.LAVA));
                } catch (Exception ignored) {
                }
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

    public static void preInitRegistries() {
        EntityInit.registerEntities();
        RenderHandler.registerEntityRenders();
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
    }

    public static void initRegistries() {

    }

    public static void postInitRegistries() {
    }




}