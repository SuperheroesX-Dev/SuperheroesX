package mini.sx.util.handlers;

import mini.sx.init.ItemInit;
import mini.sx.objects.armor.ArmorAntman;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.concurrent.atomic.AtomicBoolean;

public class AntmanSizeHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void preRender(RenderLivingEvent.Pre event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            AtomicBoolean flag = new AtomicBoolean(true);
            player.inventory.armorInventory.forEach(itemStack -> {
                if (!(itemStack.getItem() instanceof ItemArmor) || ((ItemArmor) itemStack.getItem()).getArmorMaterial() != ItemInit.ARMOR_ANTMAN)
                    flag.set(false);
            });
            if (flag.get()) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                if (scalingFactor == 1) return;
                GL11.glScalef(scalingFactor, scalingFactor, scalingFactor);
            }
        }
    }
}
