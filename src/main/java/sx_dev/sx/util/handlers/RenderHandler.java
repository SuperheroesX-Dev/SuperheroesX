package sx_dev.sx.util.handlers;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sx_dev.sx.entity.EntityCaptainAmericasShield;
import sx_dev.sx.entity.render.RenderCaptainAmericasShield;

@SideOnly(Side.CLIENT)
public class RenderHandler 
{
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCaptainAmericasShield.class, RenderCaptainAmericasShield::new);
        //RenderingRegistry.registerEntityRenderingHandler(EntityKryptonian.class, RenderKryptonian::new);
	}
}