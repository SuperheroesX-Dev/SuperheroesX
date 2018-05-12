package mini.SuperheroesX.init;

import mini.SuperheroesX.objects.enchantments.EnchantmentRange;
import mini.SuperheroesX.objects.items.ShieldCaptainAmerica;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentInit {
    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();

    public static final EnumEnchantmentType THROWABLE_SHIELD = EnumHelper.addEnchantmentType("throwable_shield", input -> input instanceof ShieldCaptainAmerica);

    public static final Enchantment THROWING_RANGE = new EnchantmentRange();
}
