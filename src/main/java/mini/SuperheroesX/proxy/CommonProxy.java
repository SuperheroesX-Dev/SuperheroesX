package mini.SuperheroesX.proxy;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.util.handlers.EnumHandler;
import mini.SuperheroesX.util.handlers.LivingTickHandler;
import mini.SuperheroesX.util.handlers.SyncHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

//import mini.SuperheroesX.objects.armor.IronManArmorModel_NOTUSING;

public class CommonProxy 
{
    /*private static final IronManArmorModel_NOTUSING ironManArmorModelCest = new IronManArmorModel_NOTUSING(1.0f);
    private static final IronManArmorModel_NOTUSING ironManArmorModel = new IronManArmorModel_NOTUSING(0.5f);*/

	public void registerItemRenderer(Item item, int meta, String id) {}
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}

    public EntityPlayer getPlayer(MessageContext context) {
        return context.getServerHandler().player;
    }

    public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
    }

    public void showJetpackParticles(World world, EntityLivingBase wearer, EnumHandler.ParticleType particle) {
    }

    public void registerHandlers() {
        SuperheroesX.LOGGER.info("Registering handlers");
        MinecraftForge.EVENT_BUS.register(new SyncHandler());
        MinecraftForge.EVENT_BUS.register(new LivingTickHandler());
    }

    /*public ModelBiped getArmorModel(int id) {
        switch (id) {
            case 0:
                return ironManArmorModelCest;
            case 1:
                return ironManArmorModel;
        }
        return null;
    }*/

    public void initKeys() {
    }
}
