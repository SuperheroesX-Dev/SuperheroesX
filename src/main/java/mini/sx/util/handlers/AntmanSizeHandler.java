package mini.sx.util.handlers;

import mini.sx.init.ItemInit;
import mini.sx.objects.armor.ArmorAntman;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber
public class AntmanSizeHandler extends SizeHandler {

    private static final float DEFAULT_HEIGHT = 1.8F;
    private static final float DEFAULT_WIDTH = 0.6F;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // if the phase was Phase.START, setting the player size wouldn't work
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            if (((ArmorAntman) ItemInit.CHESTPLATE_ANTMAN).isWearingFullSet(player)) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                setEntitySize(player, DEFAULT_WIDTH * scalingFactor, DEFAULT_HEIGHT * scalingFactor);
                player.eyeHeight = player.getDefaultEyeHeight() * scalingFactor;

            } else {
                player.eyeHeight = player.getDefaultEyeHeight();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void preRender(RenderLivingEvent.Pre event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (((ArmorAntman) ItemInit.CHESTPLATE_ANTMAN).isWearingFullSet(player)) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                if (scalingFactor == 1) return;
                GL11.glPushMatrix();
                GL11.glScalef(scalingFactor, scalingFactor, scalingFactor);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void postRender(RenderLivingEvent.Post event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (((ArmorAntman) ItemInit.CHESTPLATE_ANTMAN).isWearingFullSet(player)) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                if (scalingFactor == 1) return;
                GL11.glPopMatrix();
            }
        }
    }
}
