package sx_dev.sx.util.handlers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.HashMap;
import java.util.Map;

public class SyncHandler {
    private static final Map<EntityPlayer, Boolean> flyKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> descendKeyState = new HashMap<EntityPlayer, Boolean>();

    private static final Map<EntityPlayer, Boolean> forwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> backwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> leftKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> rightKeyState = new HashMap<EntityPlayer, Boolean>();

    private static final Map<EntityPlayer, Boolean> rightClickState = new HashMap<EntityPlayer, Boolean>();

    private static final Map<Integer, EnumHandler.ParticleType> jetpackState = new HashMap<Integer, EnumHandler.ParticleType>();

    public static boolean isFlyKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || flyKeyState.containsKey(user) && flyKeyState.get(user);
    }

    public static boolean isDescendKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && descendKeyState.containsKey(user) && descendKeyState.get(user);
    }

    public static boolean isForwardKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || forwardKeyState.containsKey(user) && forwardKeyState.get(user);
    }

    public static boolean isBackwardKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && backwardKeyState.containsKey(user) && backwardKeyState.get(user);
    }

    public static boolean isLeftKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && leftKeyState.containsKey(user) && leftKeyState.get(user);
    }

    public static boolean isRightKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && rightKeyState.containsKey(user) && rightKeyState.get(user);
    }

    public static boolean isRightClickDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && rightClickState.containsKey(user) && rightClickState.get(user);
    }

    public static void processKeyUpdate(EntityPlayer player, boolean keyFly, boolean keyDescend, boolean keyForward, boolean keyBackward, boolean keyLeft, boolean keyRight) {
        flyKeyState.put(player, keyFly);
        descendKeyState.put(player, keyDescend);

        forwardKeyState.put(player, keyForward);
        backwardKeyState.put(player, keyBackward);
        leftKeyState.put(player, keyLeft);
        rightKeyState.put(player, keyRight);
    }

    public static void processIronmanUpdate(int entityId, EnumHandler.ParticleType particleType) {
        if (particleType != null) {
            jetpackState.put(entityId, particleType);
        } else {
            jetpackState.remove(entityId);
        }
    }

    public static Map<Integer, EnumHandler.ParticleType> getIronmanStates() {
        return jetpackState;
    }

    public static void clearAll() {
        flyKeyState.clear();
        forwardKeyState.clear();
        backwardKeyState.clear();
        leftKeyState.clear();
        rightKeyState.clear();
        rightClickState.clear();
    }

    private static void removeFromAll(EntityPlayer player) {
        flyKeyState.remove(player);
        forwardKeyState.remove(player);
        backwardKeyState.remove(player);
        leftKeyState.remove(player);
        rightKeyState.remove(player);
        rightClickState.remove(player);
    }

    public static void processMouseUpdate(EntityPlayer player, boolean rightClick) {
        rightClickState.put(player, rightClick);
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent evt) {
        removeFromAll(evt.player);
    }

    @SubscribeEvent
    public void onDimChanged(PlayerEvent.PlayerChangedDimensionEvent evt) {
        removeFromAll(evt.player);
    }

    @SubscribeEvent
    public void onClientDisconnectedFromServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent evt) {
    }
}