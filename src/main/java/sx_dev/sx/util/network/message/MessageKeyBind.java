package sx_dev.sx.util.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import sx_dev.sx.objects.armor.ArmorAntman;
import sx_dev.sx.objects.armor.ArmorIronMan;
import sx_dev.sx.util.handlers.PacketHandler;

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
            if (stack.getItem() instanceof ArmorIronMan.ChestplateIronMan) {
                ArmorIronMan.ChestplateIronMan chestplateIronMan = (ArmorIronMan.ChestplateIronMan) stack.getItem();
                chestplateIronMan.toggleState(chestplateIronMan.isOn(stack), stack, null, ArmorIronMan.ChestplateIronMan.TAG_ON, player, false);
            }
        }
        if (msg.packetType == IronmanPacket.HOVER) {
            if (stack.getItem() instanceof ArmorIronMan.ChestplateIronMan) {
                ArmorIronMan.ChestplateIronMan chestplateIronMan = (ArmorIronMan.ChestplateIronMan) stack.getItem();
                chestplateIronMan.toggleState(chestplateIronMan.isHoverModeOn(stack), stack, null, ArmorIronMan.ChestplateIronMan.TAG_HOVERMODE_ON, player, false);
            }
        }
        if (msg.packetType == IronmanPacket.EHOVER) {
            if (stack.getItem() instanceof ArmorIronMan.ChestplateIronMan) {
                ArmorIronMan.ChestplateIronMan chestplateIronMan = (ArmorIronMan.ChestplateIronMan) stack.getItem();
                chestplateIronMan.toggleState(chestplateIronMan.isEHoverModeOn(stack), stack, null, ArmorIronMan.ChestplateIronMan.TAG_EHOVER_ON, player, false);
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
        HOVER,
        EHOVER
    }

    public enum AntmanPacket implements IPacketType {
        SHRINK,
        GROW
    }

    public interface IPacketType {
    }
}