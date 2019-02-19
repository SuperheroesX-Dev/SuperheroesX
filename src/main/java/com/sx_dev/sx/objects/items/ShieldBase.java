package com.sx_dev.sx.objects.items;

import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class ShieldBase extends ItemBase {
    private int blockTime;
    private int enchantability;
    private String repairIngot;

    public ShieldBase(String name, int blockTime, int enchantability, int maxDamage, String repairIngot, ItemGroup group) {
        super(name, new Properties().maxStackSize(1).defaultMaxDamage(maxDamage).group(group));
        this.blockTime = blockTime;
        this.enchantability = enchantability;
        this.repairIngot = repairIngot;

        addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
            @OnlyIn(Dist.CLIENT)
            @Override
            public float call(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        BlockDispenser.registerDispenseBehavior(this, ItemArmor.DISPENSER_BEHAVIOR);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        /*String oreName;
        int id;
        int[] ids = OreDictionary.getOreIDs(repair);
        if (ids != null && ids.length >= 1) {
            oreName = OreDictionary.getOreName(ids[0]);
        } else
            oreName = "";

        if (Strings.isNullOrEmpty(oreName)) {
            id = -1;
        } else
            id = OreDictionary.getOreID(oreName);
        return OreDictionary.getOreName(id).equals(this.repairIngot);*/
        return super.getIsRepairable(toRepair, repair);
    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return this.blockTime;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return this.enchantability;
    }


}
