package com.sx_dev.sx.util.handlers;

import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.objects.armor.ArmorIronMan;
import com.sx_dev.sx.util.network.message.MessageIronmanArmorSync;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
/*import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;*/

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber
public class LivingTickHandler {
    private static final Map<Integer, EnumHandler.ParticleType> lastJetpackState = new ConcurrentHashMap<Integer, EnumHandler.ParticleType>();


    public static Field floatingTickCount = null;

    public LivingTickHandler() {
        try {
            floatingTickCount = ReflectionHelper.findField(NetHandlerPlayServer.class, "floatingTickCount", "field_147365_f");
        } catch (Exception e) {
            SuperheroesX.LOGGER.error("Unable to find field \"floatingTickCount\"");
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent evt) {
        if (!evt.getEntityLiving().world.isRemote) {
            EnumHandler.ParticleType jetpackState = null;
            ItemStack armor = evt.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ArmorIronMan.ChestplateIronMan jetpack = null;
            if (armor.getItem() instanceof ArmorIronMan.ChestplateIronMan) {
                jetpack = new ArmorIronMan.ChestplateIronMan();
                jetpackState = EnumHandler.ParticleType.DEFAULT;
            }

            if (jetpackState != lastJetpackState.get(evt.getEntityLiving().getEntityId())) {
                if (jetpackState == null) {
                    lastJetpackState.remove(evt.getEntityLiving().getEntityId());
                } else {
                    lastJetpackState.put(evt.getEntityLiving().getEntityId(), jetpackState);
                }
                PacketHandler.instance.sendToAllAround(new MessageIronmanArmorSync(evt.getEntityLiving().getEntityId(), jetpackState != null ? jetpackState.ordinal() : -1), new NetworkRegistry.TargetPoint(evt.getEntityLiving().dimension, evt.getEntityLiving().posX, evt.getEntityLiving().posY, evt.getEntityLiving().posZ, 256));
            } else if (jetpack != null && evt.getEntityLiving().world.getTotalWorldTime() % 160L == 0) {
                PacketHandler.instance.sendToAllAround(new MessageIronmanArmorSync(evt.getEntityLiving().getEntityId(), jetpackState.ordinal()), new NetworkRegistry.TargetPoint(evt.getEntityLiving().dimension, evt.getEntityLiving().posX, evt.getEntityLiving().posY, evt.getEntityLiving().posZ, 256));
            }

            if (evt.getEntityLiving().world.getTotalWorldTime() % 200L == 0) {
                lastJetpackState.keySet().removeIf(entityId -> evt.getEntityLiving().world.getEntityByID(entityId) == null);
            }
        }
    }
}