package com.sx_dev.sx.util.network.message;

import com.sx_dev.sx.SuperheroesX;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageConfigSync {
    public NBTTagCompound recv;
    //public Configuration test;

    public void toBytes(ByteBuf buf) {
        NBTTagCompound toSend = new NBTTagCompound();
        //buf.writeCharSequence(toSend.toString(), Charset.defaultCharset());
        //Configuration toSendTest = new Configuration();
    }

    public void fromBytes(ByteBuf buf) {
        //this.recv = new NBTTagCompound().write(buf.getS).readTag(buf);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            SuperheroesX.LOGGER.info("Received server configuration");
        });
        ctx.get().setPacketHandled(true);
    }
}