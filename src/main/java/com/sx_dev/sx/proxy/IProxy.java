package com.sx_dev.sx.proxy;

import com.sx_dev.sx.util.handlers.EnumHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public interface IProxy {
    void registerItemRenderer(Item item, int meta, String id);

    void registerVariantRenderer(Item item, int meta, String filename, String id);

    void showJetpackParticles(World world, EntityLivingBase wearer, EnumHandler.ParticleType particle);

    void registerHandlers();

    <T> T nullifyOnServer(T object);

    void initKeys();
}
