package com.sx_dev.sx.util.misc;

import com.sx_dev.sx.util.handlers.EnumHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class ParticleUtils {
    @OnlyIn(Dist.CLIENT)
    private static final Minecraft mc = Minecraft.getInstance();

    public static void spawnParticle(EnumHandler.ParticleType particle, World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        switch (particle) {
            case DEFAULT:
            default:
                //mc.effectRenderer.addEffect(new EntityCustomFlameFX(world, posX, posY, posZ, velX, velY, velZ));
                return;
        }
    }
}

class EntityCustomFlameFX extends ParticleFlame {
    @OnlyIn(Dist.CLIENT)
    private static final Minecraft mc = Minecraft.getInstance();

    public EntityCustomFlameFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        super(world, posX, posY, posZ, velX, velY, velZ);
    }

    @Override
    public int getBrightnessForRender(float p_70013_1_) {
        return 190 + (int) (20F * (1.0F - mc.gameSettings.gammaSetting));
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {

        /*if (this.particleAge > 0) {
            super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        }*/
    }
}