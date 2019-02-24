package com.sx_dev.sx.util.network.message;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.util.handlers.SyncHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageKeyboardSync /*implements IMessage, IMessageHandler<MessageKeyboardSync, IMessage> */{
    public boolean flyState;
    public boolean descendState;

    public boolean forwardState;
    public boolean backwardState;
    public boolean leftState;
    public boolean rightState;

    public MessageKeyboardSync(ByteBuf buf) {
        fromBytes(buf);
    }

    public MessageKeyboardSync(boolean flyState, boolean descendState, boolean forwardState, boolean backwardState, boolean leftState, boolean rightState) {
        this.flyState = flyState;
        this.descendState = descendState;
        this.forwardState = forwardState;
        this.backwardState = backwardState;
        this.leftState = leftState;
        this.rightState = rightState;
    }

    public void fromBytes(ByteBuf buf) {
        this.flyState = buf.readBoolean();
        this.descendState = buf.readBoolean();

        this.forwardState = buf.readBoolean();
        this.backwardState = buf.readBoolean();
        this.leftState = buf.readBoolean();
        this.rightState = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.flyState);
        buf.writeBoolean(this.descendState);

        buf.writeBoolean(this.forwardState);
        buf.writeBoolean(this.backwardState);
        buf.writeBoolean(this.leftState);
        buf.writeBoolean(this.rightState);
    }

    public static void handle(MessageKeyboardSync msg, Supplier<NetworkEvent.Context> ctx) {
        if (SuperheroesX.DEBUG) System.out.println(">handleMessage<");
        ctx.get().enqueueWork(() -> {
            EntityPlayerMP entityPlayer = ctx.get().getSender();
            if (entityPlayer != null) {
                SyncHandler.processKeyUpdate(entityPlayer, msg.flyState, msg.descendState, msg.forwardState, msg.backwardState, msg.leftState, msg.rightState);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}