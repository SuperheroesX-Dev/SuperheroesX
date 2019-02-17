package com.sx_dev.sx.util.client.handler;

import com.sx_dev.sx.util.handlers.EnumHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class RenderUtils {
    @OnlyIn(Dist.CLIENT)
    private static final Minecraft mc = Minecraft.getInstance();

    public static void drawStringLeft(String string, FontRenderer fontRenderer, int x, int y, int color) {
        fontRenderer.drawString(string, x, y, color);
    }

    public static void drawStringCenter(String string, FontRenderer fontRenderer, int x, int y, int color) {
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string) / 2, y, color);
    }

    public static void drawStringRight(String string, FontRenderer fontRenderer, int x, int y, int color) {
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string), y, color);
    }

    public static void drawStringAtHUDPosition(String string, EnumHandler.HUDPositions position, FontRenderer fontRenderer, int xOffset, int yOffset, double scale, int color, int lineOffset) {
        assert mc.currentScreen != null;
        int screenWidth = mc.currentScreen.width;
        screenWidth /= scale;
        int screenHeight = mc.currentScreen.height;
        screenHeight /= scale;

        switch (position) {
            case TOP_LEFT:
                yOffset += lineOffset * 9;
                drawStringLeft(string, fontRenderer, 2 + xOffset, 2 + yOffset, color);
                break;
            case TOP_CENTER:
                yOffset += lineOffset * 9;
                drawStringCenter(string, fontRenderer, screenWidth / 2 + xOffset, 2 + yOffset, color);
                break;
            case TOP_RIGHT:
                yOffset += lineOffset * 9;
                drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, 2 + yOffset, color);
                break;
            case LEFT:
                yOffset += lineOffset * 9;
                drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight / 2 + yOffset, color);
                break;
            case RIGHT:
                yOffset += lineOffset * 9;
                drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight / 2 + yOffset, color);
                break;
            case BOTTOM_LEFT:
                yOffset -= lineOffset * 9;
                drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight - 9 + yOffset, color);
                break;
            case BOTTOM_RIGHT:
                yOffset -= lineOffset * 9;
                drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight - 9 + yOffset, color);
        }
    }
}