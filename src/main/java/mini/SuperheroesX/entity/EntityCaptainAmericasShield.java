package mini.SuperheroesX.entity;

import mini.SuperheroesX.util.math.Pos3D;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityCaptainAmericasShield extends EntityThrowable {

    private static double maxDistanceTraveled = 10.0D;
    public EntityPlayer shootingEntity;
    private ItemStack stack;
    private float damage;
    private double distanceTraveled;
    private Pos3D startingPos;
    private boolean check;

    public EntityCaptainAmericasShield(World worldIn, @Nonnull EntityLivingBase throwerIn) {
        super(worldIn, throwerIn.posX, throwerIn.posY + (double) throwerIn.getEyeHeight() - 0.10000000149011612D, throwerIn.posZ);
        this.thrower = throwerIn;
        this.check = true;
    }

    public EntityCaptainAmericasShield(World worldIn, @Nonnull EntityPlayer shooter, @Nonnull ItemStack stack) {
        this(worldIn, shooter);
        this.stack = stack;
        this.shootingEntity = shooter;
        this.damage = 5.5F;
        this.startingPos = new Pos3D(shooter.posX, shooter.posY + (double) shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
    }

    public EntityCaptainAmericasShield(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        this.distanceTraveled = new Pos3D(this.posX, this.posY, this.posZ).distanceTo(startingPos);
        if (this.distanceTraveled >= maxDistanceTraveled && check) {
            this.motionX = -this.motionX;
            this.motionY = -this.motionY;
            this.motionZ = -this.motionZ;
            check = false;
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        super.onCollideWithPlayer(entityIn);
        if (entityIn == this.shootingEntity) {
            this.setDead();
            entityIn.getCooldownTracker().removeCooldown(this.stack.getItem());
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    protected void onImpact(RayTraceResult rayTraceResult) {
        if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            this.motionX = 0;
            this.motionY = 0;
            this.motionZ = 0;
        }
        if (rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY) {
            rayTraceResult.entityHit.attackEntityFrom(new EntityDamageSource("shield", shootingEntity), this.damage);
        }
    }
}
