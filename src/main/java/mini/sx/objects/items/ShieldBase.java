package mini.sx.objects.items;

import com.google.common.base.Strings;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

public class ShieldBase extends ItemBase {
    private int blockTime;
    private int enchantability;
    private String repairIngot;

    public ShieldBase(String name, int blockTime, int enchantability, int maxDamage, String repairIngot) {
        super(name);
        this.blockTime = blockTime;
        this.enchantability = enchantability;
        this.repairIngot = repairIngot;
        setMaxStackSize(1);
        setMaxDamage(maxDamage);
        addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {

                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        String oreName;
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
        return OreDictionary.getOreName(id).equals(this.repairIngot);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
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
