package mini.SuperheroesX.util.handlers;

import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.init.PotionInit;
import mini.SuperheroesX.util.helpers.StringHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber
public class SuperheroesXEventHandler {

    private static final UUID id = UUID.fromString("e057bfa8-bfe4-4725-9b5f-89f38906c2b2");

    @SubscribeEvent
    public void onFly(TickEvent.PlayerTickEvent event) {
        boolean fly = false;

        if (event.player.isPotionActive(PotionInit.INV_GLIDE))
            fly = true;

        if (fly || event.player.isCreative() || event.player.isSpectator()) {
            event.player.capabilities.allowFlying = true;
            event.player.fallDistance = 0.0f;
        } else {
            fly = false;
            event.player.capabilities.isFlying = false;
            event.player.capabilities.allowFlying = false;
        }
    }

    public static void cheatyEasterEgg(EntityPlayer player) {
        Item item = ItemInit.CHESTPLATE_IRONMAN;
        ItemStack stack1 = new ItemStack(ItemInit.CHESTPLATE_IRONMAN, 1);
        item.onCreated(stack1, player.world, player);
        if ((player != null && player.world != null && !player.world.isRemote && (player.getClass() == EntityPlayerMP.class || FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers().contains(player))) && "Minecraftschurli".equals(player.getGameProfile().getName()) && id.equals(player.getGameProfile().getId())) {
            player.setCustomNameTag(StringHelper.ORANGE + "Tony Stark");
            InventoryPlayer inventory = player.inventory;
            int j = -1;
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (stack.isEmpty()) {
                    if (j == -1) j = i;
                } else if (stack.getItem() == item) {
                    return;
                }
            }

            if (j != -1) {
                AtomicBoolean b = new AtomicBoolean(true);
                inventory.armorInventory.forEach(itemStack -> {
                    if (!itemStack.isEmpty()) {
                        b.set(false);
                    }
                });
                if (b.get()) {
                    inventory.setInventorySlotContents(EntityEquipmentSlot.CHEST.getSlotIndex(), stack1);
                } else {
                    inventory.setInventorySlotContents(j, stack1);
                }
            }
        }
    }

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        cheatyEasterEgg(event.player);
    }

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerRespawnEvent event) {
        cheatyEasterEgg(event.player);
    }
}


