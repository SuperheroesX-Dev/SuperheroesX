package mini.SuperheroesX.util.network.message;

import io.netty.buffer.ByteBuf;
import mini.SuperheroesX.objects.armor.ArmorIronMan.ChestplateIronMan;
import mini.SuperheroesX.util.handlers.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyBind implements IMessage, IMessageHandler<MessageKeyBind, IMessage> {

    public JetpackPacket packetType;

    public MessageKeyBind() {
    }

    public MessageKeyBind(JetpackPacket type) {
        packetType = type;
    }

    @Override
    public void toBytes(ByteBuf dataStream) {
        dataStream.writeInt(packetType.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf dataStream) {
        packetType = JetpackPacket.values()[dataStream.readInt()];
    }

    @Override
    public IMessage onMessage(MessageKeyBind msg, MessageContext ctx) {
        EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
        WorldServer worldServer = entityPlayerMP.getServerWorld();

        worldServer.addScheduledTask(() -> handleMessage(msg, ctx));

        return null;
    }

    public void handleMessage(MessageKeyBind msg, MessageContext ctx) {
        EntityPlayer player = PacketHandler.getPlayer(ctx);
        ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (msg.packetType == JetpackPacket.ENGINE) {
            if (stack.getItem() instanceof ChestplateIronMan) {
                ChestplateIronMan jetpack = (ChestplateIronMan) stack.getItem();
                ((ChestplateIronMan) stack.getItem()).toggleState(jetpack.isOn(stack), stack, null, ChestplateIronMan.TAG_ON, player, false);
            }
        }
        if (msg.packetType == JetpackPacket.HOVER) {
            if (stack.getItem() instanceof ChestplateIronMan) {
                ChestplateIronMan jetpack = (ChestplateIronMan) stack.getItem();
                ((ChestplateIronMan) stack.getItem()).toggleState(jetpack.isHoverModeOn(stack), stack, null, ChestplateIronMan.TAG_HOVERMODE_ON, player, false);
            }
        }
    }

    public enum JetpackPacket {
        ENGINE,
        HOVER
    }
}