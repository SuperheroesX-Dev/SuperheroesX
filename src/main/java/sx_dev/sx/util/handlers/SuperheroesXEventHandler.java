package sx_dev.sx.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsManagerServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.lang3.tuple.Pair;
import sx_dev.sx.init.ItemInit;
import sx_dev.sx.init.PotionInit;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber
public class SuperheroesXEventHandler {

    private static final Map<Pair<String,UUID>, List<ItemStack>> eastereggs = new HashMap<>();

    private static void addToMap(){
        ItemStack container = new ItemStack(ItemInit.CHESTPLATE_IRONMAN, 1);
        if (!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }
        container.getTagCompound().setInteger("Multiplier", 4);
        eastereggs.put(Pair.of("Minecraftschurli", UUID.fromString("e057bfa8-bfe4-4725-9b5f-89f38906c2b2")), Collections.singletonList(container));
    }

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
        eastereggs.forEach((stringUUIDPair, itemStacks) -> {
            String name = stringUUIDPair.getKey();
            UUID id = stringUUIDPair.getValue();
            itemStacks.forEach(itemStack -> {
                if ((player != null && player.world != null && !player.world.isRemote && (player.getClass() == EntityPlayerMP.class || FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers().contains(player))) && name.equals(player.getGameProfile().getName()) && id.equals(player.getGameProfile().getId())) {
                    InventoryPlayer inventory = player.inventory;
                    int j = -1;
                    for (int i = 0; i < inventory.getSizeInventory(); i++) {
                        ItemStack stack = inventory.getStackInSlot(i);
                        if (stack.isEmpty()) {
                            if (j == -1) j = i;
                        } else if (stack.getItem() == itemStack.getItem()) {
                            return;
                        }
                    }

                    if (j != -1) {
                        if (itemStack.getItem() instanceof ItemArmor){
                            EntityEquipmentSlot slot = ((ItemArmor) itemStack.getItem()).armorType;
                            AtomicBoolean b = new AtomicBoolean(true);
                            inventory.armorInventory.forEach(itemStack2 -> {
                                if (!itemStack2.isEmpty()) {
                                    b.set(false);
                                }
                            });
                            if (b.get()) {
                                inventory.setInventorySlotContents(slot.getSlotIndex(), itemStack);
                            } else {
                                inventory.setInventorySlotContents(j, itemStack);
                            }
                        } else {
                            inventory.addItemStackToInventory(itemStack);
                        }
                    }
                }
            });
        });

    }

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        checkAndExecute(event.player);
    }

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerRespawnEvent event) {
        checkAndExecute(event.player);
    }

    private void checkAndExecute(EntityPlayer player){
        if(!player.world.isRemote) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            StatisticsManagerServer s = playerMP.getStatFile();
            int walked = s.readStat(StatList.WALK_ONE_CM);
            int timeSinceDeath = s.readStat(StatList.TIME_SINCE_DEATH);
            if (walked<=1&&timeSinceDeath<=0)cheatyEasterEgg(playerMP);
        }
    }
    static {addToMap();}
}