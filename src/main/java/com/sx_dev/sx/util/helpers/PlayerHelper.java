package com.sx_dev.sx.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerHelper {
    public static boolean isPlayerReal(EntityPlayer player) {
        return player != null &&
                player.world != null &&
                !player.world.isRemote &&
                (player.getClass() == EntityPlayerMP.class /*||
                        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers().contains(player)*/);

    }
}
