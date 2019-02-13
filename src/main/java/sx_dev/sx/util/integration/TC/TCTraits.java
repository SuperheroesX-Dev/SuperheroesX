package sx_dev.sx.util.integration.TC;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static net.minecraft.init.Biomes.*;

public class TCTraits {

    public static final ITrait unbreakable = new TraitUnbreakable();
    public static final ITrait temperate = new TraitTemperate();

    private static class TraitUnbreakable extends AbstractTrait {
        public TraitUnbreakable() {
            super("unbreakable", new Color(0x4FA9A8).getRGB());
        }

        @Override
        public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
            return 0;
        }
    }

    public static final String ALWAYS = null;

    private static class TraitTemperate extends AbstractTrait {

        public static final List<Biome> coldBiomes = Arrays.asList(COLD_BEACH, COLD_TAIGA, COLD_TAIGA_HILLS, MUTATED_TAIGA_COLD, FROZEN_OCEAN, FROZEN_RIVER, ICE_MOUNTAINS, ICE_PLAINS, MUTATED_ICE_FLATS);
        public static final List<Biome> hotBiomes = Arrays.asList(HELL, DESERT, DESERT_HILLS);
        private final float bonus = 0.1f;

        public TraitTemperate() {
            super("temperate", new Color(0xBA5D16).getRGB());
        }

        @Override
        public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
            Biome biome = event.getEntityPlayer().world.getBiome(event.getPos());
            if (coldBiomes.contains(biome)) {
                event.setNewSpeed(event.getNewSpeed() * (1 + bonus * 2));
            }
            if (hotBiomes.contains(biome)) {
                event.setNewSpeed(event.getNewSpeed() * (1 + bonus));
            }
        }
    }
}
