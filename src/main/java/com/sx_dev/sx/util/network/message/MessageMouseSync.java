package com.sx_dev.sx.util.network.message;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.util.handlers.SyncHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageMouseSync {
    public boolean rightClickState;

    public MessageMouseSync(ByteBuf buf) {
        fromBytes(buf);
    }

    public MessageMouseSync(boolean rightClick) {
        this.rightClickState = rightClick;
    }

    public void fromBytes(ByteBuf buf) {
        this.rightClickState = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.rightClickState);
    }

    public static void handle(MessageMouseSync msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            EntityPlayerMP entityPlayer = ctx.get().getSender();
            if (entityPlayer != null) {
                SyncHandler.processMouseUpdate(entityPlayer, msg.rightClickState);
            }
        });
        if (SuperheroesX.DEBUG) System.out.println(">handleMessage<");
        ctx.get().setPacketHandled(true);
    }
}
