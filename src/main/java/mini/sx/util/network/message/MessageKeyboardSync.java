package mini.sx.util.network.message;

import io.netty.buffer.ByteBuf;
import mini.sx.SuperheroesX;
import mini.sx.util.handlers.SyncHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyboardSync implements IMessage, IMessageHandler<MessageKeyboardSync, IMessage> {
    public boolean flyState;
    public boolean descendState;

    public boolean forwardState;
    public boolean backwardState;
    public boolean leftState;
    public boolean rightState;

    public MessageKeyboardSync() {
    }

    public MessageKeyboardSync(boolean flyState, boolean descendState, boolean forwardState, boolean backwardState, boolean leftState, boolean rightState) {
        this.flyState = flyState;
        this.descendState = descendState;
        this.forwardState = forwardState;
        this.backwardState = backwardState;
        this.leftState = leftState;
        this.rightState = rightState;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.flyState = buf.readBoolean();
        this.descendState = buf.readBoolean();

        this.forwardState = buf.readBoolean();
        this.backwardState = buf.readBoolean();
        this.leftState = buf.readBoolean();
        this.rightState = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.flyState);
        buf.writeBoolean(this.descendState);

        buf.writeBoolean(this.forwardState);
        buf.writeBoolean(this.backwardState);
        buf.writeBoolean(this.leftState);
        buf.writeBoolean(this.rightState);
    }

    @Override
    public IMessage onMessage(MessageKeyboardSync msg, MessageContext ctx) {
        EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
        WorldServer worldServer = entityPlayerMP.getServerWorld();

        worldServer.addScheduledTask(() -> handleMessage(msg, ctx));

        return null;
    }

    public void handleMessage(MessageKeyboardSync msg, MessageContext ctx) {
        if (SuperheroesX.DEBUG) System.out.println(">handleMessage<");
        EntityPlayer entityPlayer = ctx.getServerHandler().player;
        if (entityPlayer != null) {
            SyncHandler.processKeyUpdate(entityPlayer, msg.flyState, msg.descendState, msg.forwardState, msg.backwardState, msg.leftState, msg.rightState);
        }
    }
}