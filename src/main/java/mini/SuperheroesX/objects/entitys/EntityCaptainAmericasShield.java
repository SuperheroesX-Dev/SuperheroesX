package mini.SuperheroesX.objects.entitys;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCaptainAmericasShield extends Entity implements IProjectile {

    public EntityPlayer shootingEntity;
    private int xTile;
    private int yTile;
    private int zTile;
    private double damage;
    private ItemStack stack;

    public EntityCaptainAmericasShield(World worldIn, EntityPlayer shooter, ItemStack stack) {
        super(worldIn);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.damage = 2.0D;
        this.setSize(0.5F, 0.5F);
        this.setPosition(shooter.posX, shooter.posY + (double) shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
        this.shootingEntity = shooter;
        this.stack = stack;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        super.onCollideWithPlayer(entityIn);
        if (entityIn == this.shootingEntity) {
            this.setDead();
            entityIn.addItemStackToInventory(stack);
        }
    }


    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x /= (double) f;
        y /= (double) f;
        z /= (double) f;
        x += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        y += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        z += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        x *= (double) velocity;
        y *= (double) velocity;
        z *= (double) velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
    }
}
