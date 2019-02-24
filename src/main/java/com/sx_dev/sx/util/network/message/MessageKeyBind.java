package com.sx_dev.sx.util.network.message;

import com.sx_dev.sx.objects.armor.ArmorAntman;
import com.sx_dev.sx.objects.armor.ArmorIronMan;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageKeyBind {

    public IPacketType packetType;

    public MessageKeyBind(ByteBuf dataStream) {
        fromBytes(dataStream);
    }

    public MessageKeyBind(IPacketType type) {
        packetType = type;
    }

    public void toBytes(ByteBuf dataStream) {
        if (packetType.getClass().isEnum()) {
            dataStream.writeInt(((Enum) packetType).ordinal());
        }

    }

    public void fromBytes(ByteBuf dataStream) {
        packetType = IronmanPacket.values()[dataStream.readInt()];
    }

    public static void handle(MessageKeyBind msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            EntityPlayerMP player = ctx.get().getSender();
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
        });
        ctx.get().setPacketHandled(true);
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