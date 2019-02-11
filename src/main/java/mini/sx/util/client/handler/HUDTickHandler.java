package mini.sx.util.client.handler;

import mini.sx.util.config.ModConfig;
import mini.sx.util.interfaces.IHUDInfoProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class HUDTickHandler {
    @SideOnly(Side.CLIENT)
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SideOnly(Side.CLIENT)
    private static void tickEnd() {
        if (mc.player != null) {
            if ((mc.currentScreen == null || ModConfig.client.hud.showHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
                ItemStack chestplate = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                if (chestplate.getItem() instanceof IHUDInfoProvider) {
                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();

                    List<String> info = new ArrayList<>();
                    provider.addHUDInfo(info, chestplate, ModConfig.client.hud.enableFuelHUD, ModConfig.client.hud.enableStateHUD);
                    if (info.isEmpty()) {
                        return;
                    }

                    GL11.glPushMatrix();
                    mc.entityRenderer.setupOverlayRendering();
                    GL11.glScaled(ModConfig.client.hud.HUDScale, ModConfig.client.hud.HUDScale, 1.0D);

                    int i = 0;
                    for (String s : info) {
                        RenderUtils.drawStringAtHUDPosition(s, ModConfig.client.hud.HUDPosition, mc.fontRenderer, ModConfig.client.hud.HUDOffsetX, ModConfig.client.hud.HUDOffsetY, ModConfig.client.hud.HUDScale, 0xeeeeee, true, i);
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