package mini.SuperheroesX.util.integration.TC;


import mini.SuperheroesX.init.ItemInit;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TCIntegration {
    public static final List<TCMaterial> MATERIALS = new ArrayList<>();


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
            new ArrowShaftMaterialStats(1, 0));


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
    }

    public static void postInit() {

    }


}
