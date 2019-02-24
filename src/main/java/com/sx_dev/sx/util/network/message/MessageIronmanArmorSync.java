package com.sx_dev.sx.util.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageIronmanArmorSync {
    public int entityId;

    public MessageIronmanArmorSync(ByteBuf buf) {
        this.entityId = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityId);
    }

    public void handle(MessageIronmanArmorSync msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(false);
    }
}