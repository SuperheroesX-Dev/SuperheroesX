package mini.SuperheroesX.util.handlers;

import mini.SuperheroesX.entity.EntityKryptonian;
import mini.SuperheroesX.entity.render.RenderKryptonian;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityKryptonian.class, new IRenderFactory<EntityKryptonian>()
		{
			@Override
			public Render<? super EntityKryptonian> createRenderFor(RenderManager manager) 
			{
				return new RenderKryptonian(manager);
			}
		});
	}
}