package mini.SuperheroesX.objects.items;


import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.objects.entitys.EntityCaptainAmericasShield;
import mini.SuperheroesX.util.handlers.EnumHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
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
        if (entityLiving instanceof EntityPlayer && ticks <= 5) {
            this.throwShield((EntityPlayer) entityLiving, stack, worldIn);
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return this.ticks > 5 ? EnumAction.BLOCK : SuperheroesX.DEBUG ? EnumHandler.THROW : EnumAction.NONE;//TODO TEST
    }

    private void throwShield(EntityPlayer player, ItemStack stack, World worldIn) {
        player.inventory.removeStackFromSlot(player.inventory.getSlotFor(stack));
        Vec3d playerLookingVector = player.getLookVec();
        EntityCaptainAmericasShield shield = new EntityCaptainAmericasShield(worldIn, player, stack);
        shield.shoot(playerLookingVector.x, playerLookingVector.y, playerLookingVector.z, 1, 0);
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

