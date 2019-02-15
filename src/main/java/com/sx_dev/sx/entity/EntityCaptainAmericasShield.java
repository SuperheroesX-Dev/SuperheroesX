package com.sx_dev.sx.entity;

import com.google.common.collect.Iterables;
import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.EnchantmentInit;
import com.sx_dev.sx.util.helpers.ItemStackHelper;
import com.sx_dev.sx.util.helpers.PlayerHelper;
import com.sx_dev.sx.util.misc.DataWatcherItemStack;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

public class EntityCaptainAmericasShield extends Entity implements IEntityAdditionalSpawnData, IProjectile {
    public final static WeakHashMap<Object, WeakReference<EntityCaptainAmericasShield>> shieldOwners = new WeakHashMap<>();
    public final static WeakHashMap<Object, WeakReference<EntityCaptainAmericasShield>> shieldOwnersClient = SuperheroesX.PROXY.nullifyOnServer(new WeakHashMap<>());

    private static final DataParameter<Byte> DATAWATCHER_OUT_FLAG = EntityDataManager.createKey(EntityCaptainAmericasShield.class, DataSerializers.BYTE);
    private static final DataParameter<Rotations> DATAWATCHER_HOME = EntityDataManager.createKey(EntityCaptainAmericasShield.class, DataSerializers.ROTATIONS);
    private static final DataParameter<Integer> DATAWATCHER_OWNER_ID = EntityDataManager.createKey(EntityCaptainAmericasShield.class, DataSerializers.VARINT);
    private static final DataWatcherItemStack.Wrapper DATAWATCHER_STACK = new DataWatcherItemStack.Wrapper(EntityDataManager.createKey(EntityCaptainAmericasShield.class, DataSerializers.ITEM_STACK));
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 0.8, 0.4, 0.8);

    private int flyTime;
    private int potionColor = 0;
    private UUID owner = null;

    public EntityCaptainAmericasShield(World worldIn) {
        super(worldIn);
        this.setSize(0.8F, 0.1F);
        noClip = true;
        this.setEntityBoundingBox(BOUNDING_BOX.offset(this.posX, this.posY, this.posZ));
    }

    public EntityCaptainAmericasShield(World worldIn, double x, double y, double z, ItemStack stack, Object owner) {
        this(worldIn);
        setLocationAndAngles(x, y, z, 0, 0);
        setHome((float) posX, (float) posY, (float) posZ);
        DataWatcherItemStack.setStack(dataManager, stack, DATAWATCHER_STACK);
        if (owner != null) getShieldOwners(worldIn).put(owner, new WeakReference<>(this));
        this.setEntityBoundingBox(BOUNDING_BOX.offset(this.posX, this.posY, this.posZ));
    }

    public EntityCaptainAmericasShield(World worldIn, EntityLivingBase shooter, ItemStack stack) {
        this(worldIn);

        DataWatcherItemStack.setStack(dataManager, stack, DATAWATCHER_STACK);


        if (!(shooter instanceof EntityPlayer) || PlayerHelper.isPlayerReal((EntityPlayer) shooter)) {
            this.owner = shooter.getUniqueID();
        }

        if (worldIn.isRemote)
            shieldOwnersClient.put(shooter, new WeakReference<>(this));
        else
            shieldOwners.put(shooter, new WeakReference<>(this));


        Vec3d eyeVec = getEyeVec(shooter);
        this.setLocationAndAngles(eyeVec.x, eyeVec.y, eyeVec.z, shooter.rotationYaw, shooter.rotationPitch);
        this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
        this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
        this.motionY = (double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.25F * (1 + 0.3F * getEnchantmentLevel(EnchantmentInit.SHIELD_THROW_SPEED)), 0.0F);

        setHome((float) eyeVec.x, (float) eyeVec.y, (float) eyeVec.z);
        this.setEntityBoundingBox(BOUNDING_BOX.offset(this.posX, this.posY, this.posZ));
    }

    public static WeakHashMap<Object, WeakReference<EntityCaptainAmericasShield>> getShieldOwners(World worldIn) {
        return worldIn.isRemote ? shieldOwnersClient : shieldOwners;
    }

    public static boolean isNonNull(@Nullable ItemStack stack) {
        return stack != null && !stack.isEmpty();
    }

    public static boolean isNull(@Nullable ItemStack stack) {
        return stack == null || stack.isEmpty();
    }

    public static void move(EntityCaptainAmericasShield entityShield, double motionX, double motionY, double motionZ) {
        entityShield.move(MoverType.SELF, motionX, motionY, motionZ);
    }

    public static boolean activateBlock(Block block, World world, BlockPos newPos, IBlockState blockState, EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return block.onBlockActivated(world, newPos, blockState, player, hand, facing, hitX, hitY, hitZ);
    }

    public static void markBlockForUpdate(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        world.notifyBlockUpdate(pos, state, state, 0);
    }

    public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x /= (double) f;
        y /= (double) f;
        z /= (double) f;
        x += calc(inaccuracy);
        y += calc(inaccuracy);
        z += calc(inaccuracy);
        x *= (double) velocity;
        y *= (double) velocity;
        z *= (double) velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * 180.0D / Math.PI);
    }

    private double calc(float inaccuracy) {
        return this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) inaccuracy;
    }

    public void setHome(float x, float y, float z) {
        dataManager.set(DATAWATCHER_HOME, new Rotations(x, y, z));
    }

    public Vec3d getHome() {
        Rotations rotations = dataManager.get(DATAWATCHER_HOME);
        return new Vec3d(rotations.getX(), rotations.getY(), rotations.getZ());
    }

    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        }
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(DATAWATCHER_OUT_FLAG, (byte) 0);
        this.dataManager.register(DATAWATCHER_HOME, new Rotations(0, 0, 0));
        this.dataManager.register(DATAWATCHER_OWNER_ID, -1);
        DataWatcherItemStack.register(dataManager, DATAWATCHER_STACK);
    }

    @Override
    protected void readEntityFromNBT(@Nonnull NBTTagCompound tag) {
        if (tag.hasKey("Target_UUIDL")) {
            owner = new UUID(tag.getLong("Target_UUIDU"), tag.getLong("Target_UUIDL"));
        } else
            owner = null;

        tag.setByte("OutFlag", dataManager.get(DATAWATCHER_OUT_FLAG));
        tag.setTag("Home", dataManager.get(DATAWATCHER_HOME).writeToNBT());
        tag.setInteger("Owner", dataManager.get(DATAWATCHER_OWNER_ID));
        ItemStack stack = DataWatcherItemStack.getStack(dataManager, DATAWATCHER_STACK);
        if (!ItemStackHelper.isNonNull(stack)) {
            NBTTagCompound t = new NBTTagCompound();
            stack.writeToNBT(t);
            tag.setTag("Stack", t);
        }
    }

    @Override
    protected void writeEntityToNBT(@Nonnull NBTTagCompound tag) {
        if (owner != null) {
            tag.setLong("Target_UUIDL", owner.getLeastSignificantBits());
            tag.setLong("Target_UUIDU", owner.getMostSignificantBits());
        }
        dataManager.set(DATAWATCHER_OUT_FLAG, tag.getByte("OutFlag"));
        dataManager.set(DATAWATCHER_HOME, new Rotations(tag.getTagList("Home", Constants.NBT.TAG_INT)));
        dataManager.set(DATAWATCHER_OWNER_ID, tag.getInteger("Owner"));
        DataWatcherItemStack.setStack(dataManager, ItemStackHelper.loadFromNBT(tag.getCompoundTag("Stack")), DATAWATCHER_STACK);
    }

    public int getEnchantmentLevel(Enchantment enchantment) {
        ItemStack stack = DataWatcherItemStack.getStack(dataManager, DATAWATCHER_STACK);
        return isNonNull(stack) ? EnchantmentHelper.getEnchantmentLevel(enchantment, stack) : 0;
    }

    @Override
    public boolean isImmuneToExplosions() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        Entity owner = getOwner();
        boolean isRemote = world.isRemote;

        Vec3d dest = calcTargetVec();
        flyTime++;

        boolean returning = dataManager.get(DATAWATCHER_OUT_FLAG) != 0;


        Vec3d destDiff = dest.subtract(posX, posY, posZ);

        float d = MathHelper.sqrt(destDiff.x * destDiff.x + destDiff.y * destDiff.y + destDiff.z * destDiff.z);

        destDiff = destDiff.normalize();


        double acceleration = flyTime * 0.001 + (returning ? 0.05 : 0);

        if (returning)
            acceleration *= (1 + getEnchantmentLevel(EnchantmentInit.SHIELD_THROW_SPEED));

        if ((d < 1e-4D && flyTime > 25) || acceleration > 1) {
            setMeDead();
            return;
        }


        motionX *= (1 - acceleration);
        motionY *= (1 - acceleration);
        motionZ *= (1 - acceleration);


        motionX += destDiff.x * acceleration;
        motionY += destDiff.y * acceleration;
        motionZ += destDiff.z * acceleration;

        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * 180.0D / Math.PI);


        if (flyTime > 5 || returning)
            if (MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ) >= d) {
                setLocationAndAngles(dest.x, dest.y, dest.z, rotationYaw, rotationPitch);
                setMeDead();
                return;
            }

        if (this.world.isRemote)
            move(this, this.motionX, this.motionY, this.motionZ);
        else {
            HashSet<BlockPos> prevPosSet = new HashSet<>();
            if (flyTime > 1)
                Iterables.addAll(prevPosSet, getNeighbourBlocks());

            move(this, this.motionX, this.motionY, this.motionZ);

            for (BlockPos newPos : getNeighbourBlocks()) {
                if (prevPosSet.contains(newPos)) continue;
                IBlockState blockState = world.getBlockState(newPos);
                Block block = blockState.getBlock();
                if (block == Blocks.STONE_BUTTON || block == Blocks.WOODEN_BUTTON || block == Blocks.LEVER) {
                    activateBlock(block, world, newPos, blockState, FakePlayerFactory.getMinecraft((WorldServer) world), EnumHand.MAIN_HAND, null, EnumFacing.DOWN, 0, 0, 0);
                }
                if (block instanceof IPlantable || block instanceof IShearable) {
                    block.dropBlockAsItem(world, newPos, blockState, 0);
                    world.setBlockState(newPos, Blocks.AIR.getDefaultState(), 3);
                    markBlockForUpdate(world, newPos);
                }
            }
        }


        ItemStack potionStack = DataWatcherItemStack.getStack(dataManager, DATAWATCHER_STACK);
        if (isRemote) {
            if (!returning) {
                if (potionColor == -1) {
                    if (isNonNull(potionStack)) {
                        List<PotionEffect> effectsFromStack = PotionUtils.getEffectsFromStack(potionStack);
                        if (effectsFromStack.isEmpty()) {
                            potionColor = 0;
                        } else {
                            potionColor = PotionUtils.getPotionColorFromEffectList(effectsFromStack);
                        }
                    } else {
                        potionColor = 0;
                    }
                }

                double dx = posX - prevPosX;
                double dy = posY - prevPosY;
                double dz = posZ - prevPosZ;

                for (int k = 0; k < 4; ++k) {
                    double t = k / 4.0;

                    world.spawnParticle(EnumParticleTypes.CRIT,
                            this.posX + dx * t,
                            this.posY + dy * t,
                            this.posZ + dz * t,
                            -dx,
                            -dy + 0.2D,
                            -dz);
                }

                if (potionColor != 0) {
                    double d0 = (potionColor >> 16 & 255) / 255.0D;
                    double d1 = (potionColor >> 8 & 255) / 255.0D;
                    double d2 = (potionColor & 255) / 255.0D;

                    for (int j = 0; j < 3; ++j) {
                        double t = j / 3.0D;
                        this.world.spawnParticle(EnumParticleTypes.SPELL_MOB,
                                this.posX + dx * t,
                                this.posY + dy * t,
                                this.posZ + dz * t, d0, d1, d2);
                    }
                }

            }
        }

        Vec3d startVec = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d endVec = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult movingobjectposition = this.world.rayTraceBlocks(startVec, endVec, false, true, false);

        double distanceMooved = getHome().distanceTo(startVec);

        if (!isRemote) {
            Entity entity = null;
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().offset(this.motionX, this.motionY, this.motionZ).grow(1.0D, 1.0D, 1.0D));
            double d0 = -1;

            for (Entity e : list) {
                if (e instanceof EntityItem || e instanceof EntityXPOrb) {
                    if (e.getRidingEntity() == null) {
                        addItem(e);
                    }
                    continue;
                }

                if (returning) continue;

                if (e.canBeCollidedWith() && !isOwner(e)) {
                    if (e instanceof EntityPlayer) {
                        EntityPlayer entityplayer = (EntityPlayer) e;
                        if (entityplayer.capabilities.disableDamage || owner instanceof EntityPlayer && !(((EntityPlayer) owner).canAttackPlayer(entityplayer))) {
                            continue;
                        }
                    }

                    float f1 = 0.3F;
                    AxisAlignedBB axisAlignedBB = e.getEntityBoundingBox().grow((double) f1, (double) f1, (double) f1);
                    RayTraceResult mop = axisAlignedBB.calculateIntercept(startVec, endVec);

                    if (mop != null) {
                        double d1 = startVec.squareDistanceTo(mop.hitVec);

                        if (d1 < d0 || d0 == -1) {
                            entity = e;
                            d0 = d1;
                        }
                    }
                }
            }


            if (!returning && entity != null) {
                if (entity.attackEntityFrom(new DamageSourceCapShield(this, owner), 4.0F + 4 * getEnchantmentLevel(EnchantmentInit.SHIELD_THROW_DAMAGE))) {
                    if (entity instanceof EntityLivingBase) {
                        motionX = motionY = motionZ = 0;
                        dataManager.set(DATAWATCHER_OUT_FLAG, (byte) 1);

                        EntityLivingBase entitylivingbase = (EntityLivingBase) entity;
                        if (owner instanceof EntityLivingBase) {
                            EnchantmentHelper.applyThornEnchantments(entitylivingbase, owner);
                            EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) owner, entitylivingbase);
                        }

                        if (isNonNull(potionStack)) {
                            List<PotionEffect> potionEffects = PotionUtils.getEffectsFromStack(potionStack);
                            for (PotionEffect potionEffect : potionEffects) {
                                if (potionEffect.getPotion().isInstant()) {
                                    potionEffect.getPotion().affectEntity(this, owner, entitylivingbase, potionEffect.getAmplifier(), 0.25D);
                                } else {
                                    entitylivingbase.addPotionEffect(
                                            new PotionEffect(
                                                    potionEffect.getPotion(),
                                                    potionEffect.getDuration() / 8,
                                                    potionEffect.getAmplifier(),
                                                    potionEffect.getIsAmbient(),
                                                    potionEffect.doesShowParticles()));
                                }
                            }

                        }

                        if (entity != owner && entity instanceof EntityPlayer && owner instanceof EntityPlayerMP) {
                            ((EntityPlayerMP) owner).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                        }
                    }
                }
            }
        }

        if (movingobjectposition != null || distanceMooved >= 5 + 2 * getEnchantmentLevel(EnchantmentInit.SHIELD_THROW_RANGE)) {
            motionX = motionY = motionZ = 0;
            dataManager.set(DATAWATCHER_OUT_FLAG, (byte) 1);
        }
    }

    public void addItem(Entity entity) {
        if (isDead || entity == null || entity.isDead)
            return;

        EntityItem entityItem = entity instanceof EntityItem ? (EntityItem) entity : null;
        EntityXPOrb xp = entity instanceof EntityXPOrb ? (EntityXPOrb) entity : null;

        for (Entity checkEntity : getPassengers()) {
            if (entityItem != null && checkEntity instanceof EntityItem) {
                if (combine(entityItem, (EntityItem) checkEntity)) {
                    world.removeEntity(entityItem);
                    return;
                }
            }
            if (xp != null && checkEntity instanceof EntityXPOrb) {
                ((EntityXPOrb) checkEntity).xpValue += xp.xpValue;
                world.removeEntity(xp);
                return;
            }
        }

        entity.startRiding(this);
    }

    public boolean combine(EntityItem adding, EntityItem current) {
        if (adding == current) return true;
        if (!adding.isEntityAlive() || !current.isEntityAlive())
            return true;

        ItemStack addingStack = adding.getItem();
        ItemStack currentStack = current.getItem();
        if (isNull(addingStack) || isNull(currentStack)) return true;

        if (!ItemHandlerHelper.canItemStacksStack(addingStack, currentStack)) {
            return false;
        }

        int allowedAmount = currentStack.getMaxStackSize() - ItemStackHelper.getStacksize(currentStack);
        if (allowedAmount == 0) return false;

        int toAdd = ItemStackHelper.getStacksize(addingStack);
        if (toAdd <= allowedAmount) {
            ItemStackHelper.decrease(addingStack, toAdd);
            adding.setItem(addingStack);
            ItemStackHelper.increase(currentStack, toAdd);
            current.setItem(currentStack);
            return true;
        } else {
            ItemStackHelper.decrease(addingStack, allowedAmount);
            adding.setItem(addingStack);
            ItemStackHelper.increase(currentStack, allowedAmount);
            current.setItem(currentStack);
            return false;
        }
    }

    public void setMeDead() {
        motionX = motionY = motionZ = 0;

        removePassengers();

        if (getRidingEntity() != null) {
            dismountRidingEntity();
        }

        world.removeEntity(this);
    }

    @Nonnull
    public Iterable<BlockPos> getNeighbourBlocks() {
        AxisAlignedBB expand = getEntityBoundingBox();
        return BlockPos.getAllInBox(
                new BlockPos(
                        MathHelper.floor(expand.minX),
                        MathHelper.floor(expand.minY),
                        MathHelper.floor(expand.minZ)),
                new BlockPos(
                        MathHelper.ceil(expand.maxX),
                        MathHelper.ceil(expand.maxY),
                        MathHelper.ceil(expand.maxZ))
        );
    }

    public boolean isOwner(Entity entity) {
        if (world.isRemote) {
            int i = dataManager.get(DATAWATCHER_OWNER_ID);
            return entity.getEntityId() == i;
        } else {
            return owner != null && owner.equals(entity.getUniqueID());
        }
    }

    public Entity getOwner() {
        WeakHashMap<Object, WeakReference<EntityCaptainAmericasShield>> shieldOwners;
        Entity entity;
        if (world.isRemote) {
            int i = dataManager.get(DATAWATCHER_OWNER_ID);
            entity = world.getEntityByID(i);
            shieldOwners = EntityCaptainAmericasShield.shieldOwnersClient;
        } else {
            if (owner == null) return null;
            entity = ((WorldServer) world).getEntityFromUuid(owner);
            shieldOwners = EntityCaptainAmericasShield.shieldOwners;
        }

        if (entity != null) {
            WeakReference<EntityCaptainAmericasShield> reference = shieldOwners.get(entity);
            if (reference == null || reference.get() == null) {
                shieldOwners.put(entity, new WeakReference<>(this));
            }
        }
        return entity;
    }

    @Override
    public void setDead() {
        super.setDead();
        if (world.isRemote)
            shieldOwnersClient.remove(getOwner());
        else
            shieldOwners.remove(getOwner());
    }

    public Vec3d calcTargetVec() {
        if (world.isRemote) {
            int i = dataManager.get(DATAWATCHER_OWNER_ID);
            if (i != -1) {
                Entity entity = world.getEntityByID(i);
                if (entity != null) {
                    return getEyeVec(entity);
                }
            }
        } else {
            if (owner != null) {
                Entity entity = ((WorldServer) world).getEntityFromUuid(owner);
                if (entity != null) {
                    dataManager.set(DATAWATCHER_OWNER_ID, entity.getEntityId());
                    Vec3d eyeVec = getEyeVec(entity);
                    setHome((float) eyeVec.x, (float) eyeVec.y, (float) eyeVec.z);
                    return eyeVec;
                }
            }

            dataManager.set(DATAWATCHER_OWNER_ID, -1);
        }

        return getHome();
    }

    @Nonnull
    public Vec3d getEyeVec(Entity entity) {
        return new Vec3d(entity.posX, entity.posY + entity.getEyeHeight() * 0.8, entity.posZ);
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(flyTime);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);
        if (key == DATAWATCHER_STACK.wrapper) {
            potionColor = -1;
        }
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        flyTime = additionalData.readInt();
    }

    @Override
    public void shoot(double v, double v1, double v2, float v3, float v4) {

    }

    public class DamageSourceCapShield extends EntityDamageSourceIndirect {

        public DamageSourceCapShield(EntityCaptainAmericasShield indirectEntityIn, Entity owner) {
            super("shield", indirectEntityIn, owner);
        }
    }
}
