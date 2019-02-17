package com.sx_dev.sx.util.network.message;
/*
import com.sx_dev.sx.util.handlers.EnumHandler;
import com.sx_dev.sx.util.handlers.SyncHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;*/
//TODO
public class MessageIronmanArmorSync /*implements IMessage, IMessageHandler<MessageIronmanArmorSync, IMessage>*/ {/*
    public int entityId;
    public int particleId;

    public MessageIronmanArmorSync() {
    }

    public MessageIronmanArmorSync(int entityId, int particleId) {
        this.entityId = entityId;
        this.particleId = particleId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
        this.particleId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeInt(this.particleId);
    }

    @Override
    public IMessage onMessage(MessageIronmanArmorSync msg, MessageContext ctx) {

        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            @Override
            public void run() {
                handleMessage(msg, ctx);
            }
        });

        return null;
    }

    public void handleMessage(MessageIronmanArmorSync msg, MessageContext ctx) {

        Entity entity = FMLClientHandler.instance().getClient().world.getEntityByID(msg.entityId);
        if (entity instanceof EntityLivingBase && entity != FMLClientHandler.instance().getClient().player) {
            if (msg.particleId >= 0) {
                EnumHandler.ParticleType particle = EnumHandler.ParticleType.values()[msg.particleId];
                SyncHandler.processIronmanUpdate(msg.entityId, particle);
            } else {
                SyncHandler.processIronmanUpdate(msg.entityId, null);
            }
        }
    }*/
}