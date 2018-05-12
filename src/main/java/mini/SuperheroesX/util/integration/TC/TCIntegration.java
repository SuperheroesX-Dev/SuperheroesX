package mini.SuperheroesX.util.integration.TC;


import mini.SuperheroesX.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TCIntegration {
    public static final List<TCMaterial> MATERIALS = new ArrayList<>();

    //TODO: set stat values
    public static final Material VIBRANIUM = new TCMaterial("Vibranium", new Color(0x7FB0BF),
            new ItemStack(ItemInit.INGOT_VIBRANIUM), true,
            new TraitList()
                    .addE(new TraitMap(TinkerTraits.lightweight, TCTraits.ALWAYS))
                    .addE(new TraitMap(TinkerTraits.lightweight, MaterialTypes.HEAD))
                    .addE(new TraitMap(TCTraits.unbreakable, TCTraits.ALWAYS))
                    .addE(new TraitMap(TCTraits.unbreakable, MaterialTypes.HEAD))
                    .addE(new TraitMap(TinkerTraits.sharp, MaterialTypes.HEAD)),
            new HeadMaterialStats(1, 30, 10, HarvestLevels.COBALT + 2),
            new HandleMaterialStats(1, 1),
            new ExtraMaterialStats(0),
            new ArrowShaftMaterialStats(1, 0)).setFluidTemp(1000);

    public static final Material TITANIUM = new TCMaterial("Titanium", new Color(0x999999),
            new ItemStack(ItemInit.INGOT_VIBRANIUM), true,
            new TraitList()
                    .addE(new TraitMap(TinkerTraits.dense, TCTraits.ALWAYS))
                    .addE(new TraitMap(TinkerTraits.heavy, TCTraits.ALWAYS)),
            new HeadMaterialStats(1, 30, 10, HarvestLevels.COBALT),
            new HandleMaterialStats(1, 1),
            new ExtraMaterialStats(0),
            new BowMaterialStats(10, 30, 10),
            new ArrowShaftMaterialStats(1, 0)).setFluidTemp(600);

    public static final Material PALLADIUM = new TCMaterial("Palladium", new Color(0x535353),
            new ItemStack(ItemInit.INGOT_VIBRANIUM), true,
            new TraitList()
                    .addE(new TraitMap(TinkerTraits.poisonous, MaterialTypes.HEAD))
                    .addE(new TraitMap(TinkerTraits.heavy, MaterialTypes.HEAD))
                    .addE(new TraitMap(TinkerTraits.heavy, TCTraits.ALWAYS)),
            new HeadMaterialStats(1, 30, 10, HarvestLevels.COBALT),
            new HandleMaterialStats(1, 1),
            new ExtraMaterialStats(0),
            new BowMaterialStats(10, 30, 10),
            new ArrowShaftMaterialStats(1, 0)).setFluidTemp(400);

    public static final Material TITANOGOLD = new TCMaterial("Titanogold", new Color(0x918A2B),
            new ItemStack(ItemInit.INGOT_VIBRANIUM), true,
            new TraitList()
                    .addE(new TraitMap(TCTraits.temperate, MaterialTypes.HEAD)),
            new HeadMaterialStats(1, 30, 10, HarvestLevels.OBSIDIAN),
            new HandleMaterialStats(1, 1),
            new ExtraMaterialStats(0),
            new BowMaterialStats(10, 30, 10),
            new ArrowShaftMaterialStats(1, 0)).setFluidTemp(400);


    public static void preInit() {
        for (TCMaterial material : MATERIALS) {
            TinkerRegistry.addMaterialStats(material, material.stat, material.stats);
            TinkerRegistry.integrate(material).preInit();
        }
    }

    public static void init() {
        for (TCMaterial material : MATERIALS) {
            if (material.fluid != null && material.oreDictSuffix != null) {
                TinkerSmeltery.registerOredictMeltingCasting(material.fluid, material.oreDictSuffix);
            }
            TinkerSmeltery.registerToolpartMeltingCasting(material);
            material.addTraits(material.traits)
                    .setCraftable(material.craftable)
                    .setCastable(material.castable)
                    .setRepresentativeItem(material.representativeItemStack);
        }
        TinkerRegistry.registerAlloy(new FluidStack(TITANOGOLD.getFluid(), 2), new FluidStack(TITANIUM.getFluid(), 1), new FluidStack(TinkerFluids.gold, 1));
        TinkerRegistry.registerBasinCasting(new ItemStack(ItemInit.SHIELD_CAPTAIN_AMERICA_UNCOLORED, 1), new ItemStack(ItemInit.SHIELD_HANDLE, 1), VIBRANIUM.getFluid(), Material.VALUE_Ingot * 8);
    }

    public static void postInit() {

    }


}
