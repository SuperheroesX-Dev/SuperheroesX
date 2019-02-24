package com.sx_dev.sx.proxy;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.util.handlers.EnumHandler;
import com.sx_dev.sx.util.handlers.LivingTickHandler;
import com.sx_dev.sx.util.handlers.SyncHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

//import mini.sx.objects.armor.IronManArmorModel_NOTUSING;

public class ServerProxy implements IProxy {
    /*private static final IronManArmorModel_NOTUSING ironManArmorModelCest = new IronManArmorModel_NOTUSING(1.0f);
    private static final IronManArmorModel_NOTUSING ironManArmorModel = new IronManArmorModel_NOTUSING(0.5f);*/

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
    }

    //public EntityPlayer getPlayer(MessageContext context) {return context.getServerHandler().player;}

    public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
    }

    @Override
    public void showJetpackParticles(World world, EntityLivingBase wearer, EnumHandler.ParticleType particle) {
    }

    @Override
    public <T> T nullifyOnServer(T object) {
        return null;
    }

    @Override
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

    @Override
    public void initKeys() {
    }
}
