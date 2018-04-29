package mini.SuperheroesX.init;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.entity.EntityKryptonian;
import mini.SuperheroesX.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{

    public static void registerEntities()
	{
		registerEntity("kryptonian", EntityKryptonian.class, Reference.ENTITY_KRYPTONIAN, 89, 000000, 49152);
	}

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, SuperheroesX.INSTANCE, range, 1, true, color1, color2);
	}
}
