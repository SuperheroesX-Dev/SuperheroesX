package com.sx_dev.sx.objects.armor;

import com.sx_dev.sx.SuperheroesX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.sx_dev.sx.init.ItemInit.ARMOR_AQUAMAN;

public class ArmorAquaman extends ArmorBase {
    public ArmorAquaman(String name, EntityEquipmentSlot equipmentSlotIn) {
        super(name, ARMOR_AQUAMAN, equipmentSlotIn, new Properties().group(SuperheroesX.SUPERHEROES_X_TAB_DC));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            ItemStack stack = new ItemStack(this);
            if (this.getEquipmentSlot() == EntityEquipmentSlot.FEET)
                stack.addEnchantment(Enchantments.DEPTH_STRIDER, 3);
            items.add(stack);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        super.onCreated(stack, world, player);
        if (this.getEquipmentSlot() == EntityEquipmentSlot.FEET) {
            stack.addEnchantment(Enchantments.DEPTH_STRIDER, 3);
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        if (this.isWearingFullSet(player)) {
            super.onArmorTick(stack, world, player);
            player.addPotionEffect(new PotionEffect(MobEffects.CONDUIT_POWER, 5, 2, true, false));
        }
    }
}
