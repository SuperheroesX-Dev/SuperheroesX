package sx_dev.sx.util.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import sx_dev.sx.SuperheroesX;
import sx_dev.sx.util.handlers.SyncHandler;

public class MessageMouseSync implements IMessage, IMessageHandler<MessageMouseSync, IMessage> {
    public boolean rightClickState;

    public MessageMouseSync() {
    }

    public MessageMouseSync(boolean rightClick) {
        this.rightClickState = rightClick;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.rightClickState = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.rightClickState);
    }

    @Override
    public IMessage onMessage(MessageMouseSync msg, MessageContext ctx) {
        EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
        WorldServer worldServer = entityPlayerMP.getServerWorld();

        worldServer.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                handleMessage(msg, ctx);
            }
        });

        return null;
    }

    public void handleMessage(MessageMouseSync msg, MessageContext ctx) {
        if (SuperheroesX.DEBUG) System.out.println(">handleMessage<");
        EntityPlayer entityPlayer = ctx.getServerHandler().player;
        if (entityPlayer != null) {
            SyncHandler.processMouseUpdate(entityPlayer, msg.rightClickState);
        }
    }
}
