package mini.SuperheroesX.util.integration.TC;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;

import java.awt.*;

public class TCTraits {

    public static final ITrait unbreakable = new TraitUnbreakable();
    public static final String ALWAYS = null;

    private static class TraitUnbreakable extends AbstractTrait {
        public TraitUnbreakable() {
            super("unbreakable", new Color(0x4FA9A8).getRGB());
        }

        @Override
        public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
            return 0;
        }
    }
}
