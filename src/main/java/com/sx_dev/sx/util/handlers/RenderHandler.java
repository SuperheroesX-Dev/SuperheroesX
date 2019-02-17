package com.sx_dev.sx.util.handlers;

import com.sx_dev.sx.entity.EntityCaptainAmericasShield;
import com.sx_dev.sx.entity.render.RenderCaptainAmericasShield;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class RenderHandler 
{
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCaptainAmericasShield.class, RenderCaptainAmericasShield::new);
        //RenderingRegistry.registerEntityRenderingHandler(EntityKryptonian.class, RenderKryptonian::new);
	}
}