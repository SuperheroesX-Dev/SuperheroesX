package mini.SuperheroesX.objects.items;


import mini.SuperheroesX.objects.armor.ArmorIronMan;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class TestItem extends ItemBase {
    public TestItem() {
        super("test");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack chestplate = playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ArmorIronMan.ChestplateIronMan) {
            ((ArmorIronMan.ChestplateIronMan) chestplate.getItem()).receiveEnergy(chestplate, ((ArmorIronMan.ChestplateIronMan) chestplate.getItem()).getMaxEnergyStored(chestplate), false);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
