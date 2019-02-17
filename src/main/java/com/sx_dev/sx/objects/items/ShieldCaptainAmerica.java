package com.sx_dev.sx.objects.items;


import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.entity.EntityCaptainAmericasShield;
import com.sx_dev.sx.util.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MODID)
public class ShieldCaptainAmerica extends WeaponizedShield {

    private int ticks;

    public ShieldCaptainAmerica() {
        super("shield_captain_america", Integer.MAX_VALUE, 8, -2.4000000953674316D, 10, 0, "");
        addPropertyOverride(new ResourceLocation("throwing"), new IItemPropertyGetter() {
            @OnlyIn(Dist.CLIENT)
            public float call(@Nonnull ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {

                return entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).getCooldownTracker().hasCooldown(stack.getItem()) ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        if (!player.world.isRemote) {
            this.ticks = -(count - Integer.MAX_VALUE);
            if (SuperheroesX.DEBUG) System.out.println(ticks);
        }
    }

    public boolean canThrowShield(EntityPlayer playerIn, WeakHashMap<Object, WeakReference<EntityCaptainAmericasShield>> shieldCapOwners) {
        if (playerIn != null && !playerIn.abilities.isCreativeMode) {
            WeakReference<EntityCaptainAmericasShield> reference = shieldCapOwners.get(playerIn);
            if (reference != null) {
                EntityCaptainAmericasShield shieldCap = reference.get();
                return shieldCap == null;
            }
        }
        return true;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (!worldIn.isRemote && entityLiving instanceof EntityPlayer && ticks <= 5 && ticks >= 0) {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;
            if (!canThrowShield(playerIn, EntityCaptainAmericasShield.shieldOwners))
                return;

            EntityCaptainAmericasShield shieldCap = new EntityCaptainAmericasShield(EntityType.ARROW, worldIn, playerIn, stack.copy());//TODO

            worldIn.spawnEntity(shieldCap);
        }
    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return this.ticks > 5 ? EnumAction.BLOCK : /*SuperheroesX.DEBUG ? EnumHandler.THROW :*/ EnumAction.NONE;//TODO TEST
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

