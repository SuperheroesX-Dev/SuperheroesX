package mini.sx.entity.render;

import mini.sx.entity.EntityKryptonian;
import mini.sx.entity.model.ModelKryptonian;
import mini.sx.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKryptonian extends RenderLiving<EntityKryptonian>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.RESOURCE_PREFIX + "textures/entitys/kryptonian.png");
	
	public RenderKryptonian(RenderManager manager) 
	{
		super(manager, new ModelKryptonian(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityKryptonian entity) 
	{
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityKryptonian entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}