package mini.sx.util.handlers;

import mini.sx.entity.EntityCaptainAmericasShield;
import mini.sx.entity.EntityKryptonian;
import mini.sx.entity.render.RenderCaptainAmericasShield;
import mini.sx.entity.render.RenderKryptonian;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
        RenderingRegistry.registerEntityRenderingHandler(EntityCaptainAmericasShield.class, RenderCaptainAmericasShield::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKryptonian.class, RenderKryptonian::new);
	}
}