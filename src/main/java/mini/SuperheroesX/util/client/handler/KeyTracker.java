package mini.SuperheroesX.util.client.handler;

import mini.SuperheroesX.objects.armor.ArmorIronMan.ChestplateIronMan;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.config.Config;
import mini.SuperheroesX.util.handlers.PacketHandler;
import mini.SuperheroesX.util.handlers.SyncHandler;
import mini.SuperheroesX.util.network.message.MessageKeyBind;
import mini.SuperheroesX.util.network.message.MessageKeyboardSync;
import mini.SuperheroesX.util.network.message.MessageMouseSync;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
@Mod.EventBusSubscriber
public class KeyTracker {

    public static final KeyTracker instance = new KeyTracker();

    static final Minecraft mc = Minecraft.getMinecraft();
    private static int flyKey;
    private static int descendKey;
    private static boolean lastFlyState = false;
    private static boolean lastDescendState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;
    private static boolean lastRightClickState = false;

    private static KeyBinding engineKey;

    private static KeyBinding hoverKey;

    private static ArrayList<KeyBinding> keys = new ArrayList<>();

    public KeyTracker() {
        engineKey = new KeyBinding(Reference.PREFIX + "keybind.engine", Keyboard.KEY_G, Reference.PREFIX + "category.ironman");
        ClientRegistry.registerKeyBinding(engineKey);

        hoverKey = new KeyBinding(Reference.PREFIX + "keybind.hover", Keyboard.KEY_L, Reference.PREFIX + "category.ironman");
        ClientRegistry.registerKeyBinding(hoverKey);
    }

    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseInputEvent event) {
        EntityPlayer player = FMLClientHandler.instance().getClient().player;
        ItemStack chestStack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        Item chestItem = ChestplateIronMan.StackUtil.getItem(chestStack);

        for (KeyBinding keyBindings : keys) {
            int button = keyBindings.getKeyCode();
            if (button < 0 && keyBindings.isPressed()) {
                if (chestItem instanceof ChestplateIronMan) {
                    ChestplateIronMan jetpack = (ChestplateIronMan) chestItem;

                    if (keyBindings.getKeyDescription().equals(Reference.PREFIX + "keybind.engine")) {
                        jetpack.toggleState(jetpack.isOn(chestStack), chestStack, null, ChestplateIronMan.TAG_ON, player, true);
                        PacketHandler.instance.sendToServer(new MessageKeyBind(MessageKeyBind.JetpackPacket.ENGINE));
                    }
                    if (keyBindings.getKeyDescription().equals(Reference.PREFIX + "keybind.hover")) {
                        jetpack.toggleState(jetpack.isHoverModeOn(chestStack), chestStack, "hoverMode", ChestplateIronMan.TAG_HOVERMODE_ON, player, true);
                        PacketHandler.instance.sendToServer(new MessageKeyBind(MessageKeyBind.JetpackPacket.HOVER));
                    }
                }
            }
        }

    }


    private static void tickStart() {

        if (mc.player != null) {
            boolean flyState;
            boolean descendState;
            if (Config.customControls) {
                flyState = mc.inGameHasFocus && Keyboard.isKeyDown(flyKey);
                descendState = mc.inGameHasFocus && Keyboard.isKeyDown(descendKey);
            } else {
                flyState = mc.gameSettings.keyBindJump.isKeyDown();
                descendState = mc.gameSettings.keyBindSneak.isKeyDown();
            }

            boolean forwardState = mc.gameSettings.keyBindForward.isKeyDown();
            boolean backwardState = mc.gameSettings.keyBindBack.isKeyDown();
            boolean leftState = mc.gameSettings.keyBindLeft.isKeyDown();
            boolean rightState = mc.gameSettings.keyBindRight.isKeyDown();
            boolean rightClickState = mc.gameSettings.keyBindUseItem.isKeyDown();

            if (rightClickState != lastRightClickState || flyState != lastFlyState || descendState != lastDescendState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
                lastFlyState = flyState;
                lastDescendState = descendState;

                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;
                lastRightClickState = rightClickState;
                PacketHandler.instance.sendToServer(new MessageMouseSync(rightClickState));
                PacketHandler.instance.sendToServer(new MessageKeyboardSync(flyState, descendState, forwardState, backwardState, leftState, rightState));
                SyncHandler.processKeyUpdate(mc.player, flyState, descendState, forwardState, backwardState, leftState, rightState);
                SyncHandler.processMouseUpdate(mc.player, rightClickState);
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent evt) {
        if (evt.phase == TickEvent.Phase.START) {
            tickStart();
        }
    }

    public static void addKeys() {
        keys.add(engineKey);
        keys.add(hoverKey);
    }

    public static void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
        flyKey = Keyboard.getKeyIndex(flyKeyName);
        descendKey = Keyboard.getKeyIndex(descendKeyName);
    }

    @SubscribeEvent
    public static void onKeyInput(KeyInputEvent event) {
        EntityPlayer player = FMLClientHandler.instance().getClient().player;
        ItemStack chestStack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        Item chestItem = ChestplateIronMan.StackUtil.getItem(chestStack);

        for (KeyBinding keyBindings : keys) {
            int button = keyBindings.getKeyCode();
            if (button > 0 && keyBindings.isPressed()) {
                if (chestItem instanceof ChestplateIronMan) {
                    ChestplateIronMan jetpack = (ChestplateIronMan) chestItem;
                    if (keyBindings.getKeyDescription().equals(Reference.PREFIX + "keybind.engine")) {
                        System.out.println(jetpack.isOn(chestStack));
                        jetpack.toggleState(jetpack.isOn(chestStack), chestStack, null, ChestplateIronMan.TAG_ON, player, true);
                        PacketHandler.instance.sendToServer(new MessageKeyBind(MessageKeyBind.JetpackPacket.ENGINE));
                    }
                    if (keyBindings.getKeyDescription().equals(Reference.PREFIX + "keybind.hover")) {
                        jetpack.toggleState(jetpack.isHoverModeOn(chestStack), chestStack, "hoverMode", ChestplateIronMan.TAG_HOVERMODE_ON, player, true);
                        PacketHandler.instance.sendToServer(new MessageKeyBind(MessageKeyBind.JetpackPacket.HOVER));
                    }
                }
            }
        }
    }
}