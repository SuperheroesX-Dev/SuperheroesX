package sx_dev.sx.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import sx_dev.sx.SuperheroesX;
import sx_dev.sx.entity.EntityKryptonian;
import sx_dev.sx.util.Reference;

public class EntityInit
{

    public static void registerEntities()
	{
		registerEntity("kryptonian", EntityKryptonian.class, Reference.ENTITY_KRYPTONIAN, 89, 000000, 49152);
        //EntityRegistry.registerModEntity(new ResourceLocation(Reference.RESOURCE_PREFIX+"shield_cap"),EntityCaptainAmericasShield.class,"shield_cap",Reference.ENTITY_SHIELD,sx.INSTANCE,0,1,true);
	}

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, SuperheroesX.INSTANCE, range, 1, true, color1, color2);
	}
}
