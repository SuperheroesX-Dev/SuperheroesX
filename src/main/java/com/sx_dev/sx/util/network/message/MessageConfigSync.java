package com.sx_dev.sx.util.network.message;

import com.sx_dev.sx.SuperheroesX;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage> {
    public NBTTagCompound recv;
    public Configuration test;

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound toSend = new NBTTagCompound();

        Configuration toSendTest = new Configuration();
        ByteBufUtils.writeTag(buf, toSend);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.recv = ByteBufUtils.readTag(buf);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IMessage onMessage(MessageConfigSync msg, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> handleMessage(msg, ctx));

        return null;
    }

    public void handleMessage(MessageConfigSync msg, MessageContext ctx) {
        SuperheroesX.LOGGER.info("Received server configuration");
    }
}