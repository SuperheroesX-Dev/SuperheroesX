package com.sx_dev.sx.util.handlers;

import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.objects.armor.ArmorAntman;
import com.sx_dev.sx.util.Reference;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MODID)
public class AntmanSizeHandler extends SizeHandler {

    private static final float DEFAULT_HEIGHT = 1.8F;
    private static final float DEFAULT_WIDTH = 0.6F;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // if the phase was Phase.START, setting the player size wouldn't work
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            if (((ArmorAntman) ItemInit.CHESTPLATE_ANTMAN.asItem()).isWearingFullSet(player)) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                setEntitySize(player, DEFAULT_WIDTH * scalingFactor, DEFAULT_HEIGHT * scalingFactor);
                //player.eyeHeight = player.getDefaultEyeHeight() * scalingFactor;
            } else {
                //player.eyeHeight = player.getDefaultEyeHeight();
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void preRender(RenderLivingEvent.Pre event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (((ArmorAntman) ItemInit.CHESTPLATE_ANTMAN.asItem()).isWearingFullSet(player)) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                if (scalingFactor == 1) return;
                GL11.glPushMatrix();
                GL11.glScalef(scalingFactor, scalingFactor, scalingFactor);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void postRender(RenderLivingEvent.Post event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (((ArmorAntman) ItemInit.CHESTPLATE_ANTMAN.asItem()).isWearingFullSet(player)) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                if (scalingFactor == 1) return;
                GL11.glPopMatrix();
            }
        }
    }
}
