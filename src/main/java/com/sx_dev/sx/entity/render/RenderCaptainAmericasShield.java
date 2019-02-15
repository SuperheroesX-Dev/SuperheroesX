package com.sx_dev.sx.entity.render;

import com.sx_dev.sx.entity.EntityCaptainAmericasShield;
import com.sx_dev.sx.util.Reference;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderCaptainAmericasShield extends Render<EntityCaptainAmericasShield> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.RESOURCE_PREFIX + "textures/entitys/captain_americas_shield.png");

    public RenderCaptainAmericasShield(RenderManager renderManager) {
        super(renderManager);
        bindTexture(TEXTURES);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityCaptainAmericasShield entityCaptainAmericasShield) {
        return TEXTURES;
    }

    @Override
    public void doRender(@Nonnull EntityCaptainAmericasShield entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
