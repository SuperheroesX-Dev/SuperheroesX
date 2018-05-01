package mini.SuperheroesX.util.handlers;

import mini.SuperheroesX.entity.EntityCaptainAmericasShield;
import mini.SuperheroesX.entity.EntityKryptonian;
import mini.SuperheroesX.entity.render.RenderCaptainAmericasShield;
import mini.SuperheroesX.entity.render.RenderKryptonian;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
        RenderingRegistry.registerEntityRenderingHandler(EntityCaptainAmericasShield.class, RenderCaptainAmericasShield::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKryptonian.class, RenderKryptonian::new);
	}
}