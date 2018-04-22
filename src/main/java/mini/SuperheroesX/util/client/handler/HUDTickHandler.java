package mini.SuperheroesX.util.client.handler;

import mini.SuperheroesX.util.config.Config;
import mini.SuperheroesX.util.handlers.EnumHandler;
import mini.SuperheroesX.util.interfaces.IHUDInfoProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class HUDTickHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();

    private static void tickEnd() {
        if (mc.player != null) {
            if ((mc.currentScreen == null || Config.showHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
                ItemStack chestplate = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                if (chestplate.getItem() instanceof IHUDInfoProvider) {
                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();

                    List<String> info = new ArrayList<String>();
                    provider.addHUDInfo(info, chestplate, Config.enableFuelHUD, Config.enableStateHUD);
                    if (info.isEmpty()) {
                        return;
                    }

                    GL11.glPushMatrix();
                    mc.entityRenderer.setupOverlayRendering();
                    GL11.glScaled(Config.HUDScale, Config.HUDScale, 1.0D);

                    int i = 0;
                    for (String s : info) {
                        RenderUtils.drawStringAtHUDPosition(s, EnumHandler.HUDPositions.values()[Config.HUDPosition], mc.fontRenderer, Config.HUDOffsetX, Config.HUDOffsetY, Config.HUDScale, 0xeeeeee, true, i);
                        i++;
                    }

                    GL11.glPopMatrix();
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt) {
        if (evt.phase == Phase.END /*&& (Config.enableFuelHUD || Config.enableStateHUD)*/) {
            tickEnd();
        }
    }
}