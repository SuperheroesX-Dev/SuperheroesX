package mini.SuperheroesX.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityKryptonian extends EntityPigZombie
{
	public EntityKryptonian(World worldIn) {
		
		super(worldIn);
		this.isImmuneToFire = true;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(85.0D);
    }
	
	@Override

	public float getEyeHeight()
	{
		return 1.6F;
	}
	public EntityPigZombie createChild(EntityAgeable ageable) 
	{
		return new EntityKryptonian(world);
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return super.getAmbientSound();
		
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) 
	{
		return super.getHurtSound(source);
	}
	
	@Override
	protected SoundEvent getDeathSound() 
	{
		return super.getDeathSound();
	}
}
