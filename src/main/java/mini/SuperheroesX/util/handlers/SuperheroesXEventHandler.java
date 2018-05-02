package mini.SuperheroesX.util.handlers;

import mini.SuperheroesX.init.PotionInit;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class SuperheroesXEventHandler {
    @SubscribeEvent
    public void onFly(TickEvent.PlayerTickEvent event) {
        boolean fly = false;

        if (event.player.isPotionActive(PotionInit.INV_GLIDE))
            fly = true;

        if (fly || event.player.isCreative() || event.player.isSpectator()) {
            event.player.capabilities.allowFlying = true;
            event.player.fallDistance = 0.0f;
        } else {
            fly = false;
            event.player.capabilities.isFlying = false;
            event.player.capabilities.allowFlying = false;
        }
    }
    
}


