package com.sx_dev.sx.init;

import com.sx_dev.sx.objects.enchantments.EnchantmentThrowable;
import com.sx_dev.sx.objects.items.ShieldCaptainAmerica;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentInit {
    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();

    public static final EnumEnchantmentType THROWABLE_SHIELD = EnumHelper.addEnchantmentType("throwable_shield", input -> input instanceof ShieldCaptainAmerica);

    public static final Enchantment SHIELD_THROW_RANGE = new EnchantmentThrowable("shield_throw_range");
    public static final Enchantment SHIELD_THROW_SPEED = new EnchantmentThrowable("shield_throw_speed");
    public static final Enchantment SHIELD_THROW_DAMAGE = new EnchantmentThrowable("shield_throw_damage");
}
