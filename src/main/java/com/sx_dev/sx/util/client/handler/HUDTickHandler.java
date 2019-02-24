package com.sx_dev.sx.util.client.handler;

import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.config.ModConfig;
import com.sx_dev.sx.util.interfaces.IHUDInfoProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MODID)
public class HUDTickHandler {
    @OnlyIn(Dist.CLIENT)
    private static final Minecraft mc = Minecraft.getInstance();

    @OnlyIn(Dist.CLIENT)
    private static void tickEnd() {
        if (mc.player != null) {

            if ((ModConfig.client.hud.showHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
                ItemStack chestplate = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                if (chestplate.getItem() instanceof IHUDInfoProvider) {
                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();

                    List<String> info = new ArrayList<>();
                    provider.addHUDInfo(info, chestplate, ModConfig.client.hud.enableFuelHUD, ModConfig.client.hud.enableStateHUD);
                    if (info.isEmpty()) {
                        return;
                    }

                    GL11.glPushMatrix();
                    mc.mainWindow.setupOverlayRendering();
                    GL11.glScaled(ModConfig.client.hud.HUDScale, ModConfig.client.hud.HUDScale, 1.0D);

                    int i = 0;
                    for (String s : info) {
                        RenderUtils.drawStringAtHUDPosition(s, ModConfig.client.hud.HUDPosition, mc.fontRenderer, ModConfig.client.hud.HUDOffsetX, ModConfig.client.hud.HUDOffsetY, ModConfig.client.hud.HUDScale, 0xeeeeee, i);
                        i++;
                    }

                    GL11.glPopMatrix();
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt) {
        if (evt.phase == Phase.END /*&& (ModConfig.enableFuelHUD || ModConfig.enableStateHUD)*/) {
            tickEnd();
        }
    }
}