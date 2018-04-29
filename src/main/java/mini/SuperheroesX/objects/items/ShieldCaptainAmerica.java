package mini.SuperheroesX.objects.items;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class ShieldCaptainAmerica extends WeaponizedShield {

    private int ticks;

    public ShieldCaptainAmerica() {
        super("shield_captain_america", Integer.MAX_VALUE, 8, -2.4000000953674316D, 10, 0, "");
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        this.ticks = count;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (ticks < 10) {
            this.throwShield(stack, worldIn, entityLiving);
        }
    }

    private void throwShield(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

    }

    @SubscribeEvent
    public void onGetHurt(LivingHurtEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        if ((!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() instanceof ShieldCaptainAmerica && player.isHandActive()) || (!player.getHeldItemOffhand().isEmpty() && player.getHeldItemOffhand().getItem() instanceof ShieldCaptainAmerica && player.isHandActive())) {
            event.setCanceled(true);
        }
    }

}

