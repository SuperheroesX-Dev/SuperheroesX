package mini.sx.util.handlers;

import mini.sx.entity.EntityCaptainAmericasShield;
import mini.sx.entity.render.RenderCaptainAmericasShield;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler 
{
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCaptainAmericasShield.class, RenderCaptainAmericasShield::new);
        //RenderingRegistry.registerEntityRenderingHandler(EntityKryptonian.class, RenderKryptonian::new);
	}
}