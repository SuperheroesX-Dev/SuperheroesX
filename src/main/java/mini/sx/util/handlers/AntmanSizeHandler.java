package mini.sx.util.handlers;

import mini.sx.init.ItemInit;
import mini.sx.objects.armor.ArmorAntman;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber
public class AntmanSizeHandler {

    private static final float DEFAULT_HEIGHT = 1.8F;
    private static final float DEFAULT_WIDTH = 0.6F;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // if the phase was Phase.START, setting the player size wouldn't work
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            if (fullSet(player)) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                setPlayerSize(player, DEFAULT_WIDTH * scalingFactor, DEFAULT_HEIGHT * scalingFactor);
                player.eyeHeight = player.getDefaultEyeHeight() * scalingFactor;
            } else {
                player.eyeHeight = player.getDefaultEyeHeight();
            }
        }
    }

    private static void setPlayerSize(EntityPlayer player, float width, float height) {
        if (width != player.width || height != player.height) {
            float oldWidth = player.width;
            player.width = width;
            player.height = height;

            if (player.width < oldWidth) {
                double halfWidth = width / 2.0D;
                player.setEntityBoundingBox(
                        new AxisAlignedBB(player.posX - halfWidth, player.posY, player.posZ - halfWidth,
                                player.posX + halfWidth, player.posY + player.height, player.posZ + halfWidth));
                return;
            }

            AxisAlignedBB axisalignedbb = player.getEntityBoundingBox();
            player.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ,
                    axisalignedbb.minX + player.width, axisalignedbb.minY + player.height,
                    axisalignedbb.minZ + player.width));

            if (player.width > oldWidth && (player.ticksExisted > 1) && !player.world.isRemote) {
                player.move(MoverType.SELF, (oldWidth - player.width), 0.0D, (oldWidth - player.width));
            }
        }
    }

    private static boolean fullSet(EntityPlayer player) {
        AtomicBoolean flag = new AtomicBoolean(true);
        player.inventory.armorInventory.forEach(itemStack -> {
            if (!(itemStack.getItem() instanceof ItemArmor) || ((ItemArmor) itemStack.getItem()).getArmorMaterial() != ItemInit.ARMOR_ANTMAN)
                flag.set(false);
        });
        return flag.get();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void preRender(RenderLivingEvent.Pre event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (fullSet(player)) {
                float scalingFactor = ((ArmorAntman.ChestplateAntman) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getScalingFactor();
                if (scalingFactor == 1) return;
                GL11.glPushMatrix();
                GL11.glScalef(scalingFactor, scalingFactor, scalingFactor);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void postRender(RenderLivingEvent.Post event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            if (fullSet((EntityPlayer) event.getEntity())) {
                GL11.glPopMatrix();
            }
        }
    }
}
