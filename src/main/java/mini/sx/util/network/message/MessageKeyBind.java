package mini.sx.util.network.message;

import io.netty.buffer.ByteBuf;
import mini.sx.objects.armor.ArmorAntman;
import mini.sx.objects.armor.ArmorIronMan.ChestplateIronMan;
import mini.sx.util.handlers.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyBind implements IMessage, IMessageHandler<MessageKeyBind, IMessage> {

    public IPacketType packetType;

    public MessageKeyBind() {
    }

    public MessageKeyBind(IPacketType type) {
        packetType = type;
    }

    @Override
    public void toBytes(ByteBuf dataStream) {
        if (packetType.getClass().isEnum()) {
            dataStream.writeInt(((Enum) packetType).ordinal());
        }

    }

    @Override
    public void fromBytes(ByteBuf dataStream) {
        packetType = IronmanPacket.values()[dataStream.readInt()];
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
        if (msg.packetType == IronmanPacket.ENGINE) {
            if (stack.getItem() instanceof ChestplateIronMan) {
                ChestplateIronMan chestplateIronMan = (ChestplateIronMan) stack.getItem();
                chestplateIronMan.toggleState(chestplateIronMan.isOn(stack), stack, null, ChestplateIronMan.TAG_ON, player, false);
            }
        }
        if (msg.packetType == IronmanPacket.HOVER) {
            if (stack.getItem() instanceof ChestplateIronMan) {
                ChestplateIronMan chestplateIronMan = (ChestplateIronMan) stack.getItem();
                chestplateIronMan.toggleState(chestplateIronMan.isHoverModeOn(stack), stack, null, ChestplateIronMan.TAG_HOVERMODE_ON, player, false);
            }
        }
        if (msg.packetType == AntmanPacket.SHRINK) {
            if (stack.getItem() instanceof ArmorAntman.ChestplateAntman) {
                ArmorAntman.ChestplateAntman chestplateAntman = (ArmorAntman.ChestplateAntman) stack.getItem();
                chestplateAntman.shrink();
            }
        }
        if (msg.packetType == AntmanPacket.GROW) {
            if (stack.getItem() instanceof ArmorAntman.ChestplateAntman) {
                ArmorAntman.ChestplateAntman chestplateAntman = (ArmorAntman.ChestplateAntman) stack.getItem();
                chestplateAntman.grow();
            }
        }
    }

    public enum IronmanPacket implements IPacketType {
        ENGINE,
        HOVER
    }

    public enum AntmanPacket implements IPacketType {
        SHRINK,
        GROW
    }

    public interface IPacketType {
    }
}