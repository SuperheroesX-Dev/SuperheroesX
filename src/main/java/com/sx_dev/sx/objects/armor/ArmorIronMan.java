package com.sx_dev.sx.objects.armor;

import com.google.common.collect.Multimap;
import com.sx_dev.sx.SuperheroesX;
import com.sx_dev.sx.init.ItemInit;
import com.sx_dev.sx.init.PotionEffectInit;
import com.sx_dev.sx.tabs.CustomItemGroup;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.handlers.LivingTickHandler;
import com.sx_dev.sx.util.handlers.SyncHandler;
import com.sx_dev.sx.util.helpers.NBTHelper;
import com.sx_dev.sx.util.helpers.SXStringHelper;
import com.sx_dev.sx.util.helpers.StringHelper;
import com.sx_dev.sx.util.interfaces.IHUDInfoProvider;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
import java.util.List;
import java.util.UUID;

/*
import cofh.redstoneflux.api.IEnergyContainerItem;
import cofh.redstoneflux.util.EnergyContainerItemWrapper;*/
//import net.minecraftforge.common.ISpecialArmor;

@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Reference.MODID)
public class ArmorIronMan extends ArmorBase /*implements ISpecialArmor*/ {

    protected static float reductionAmount = 1F;
    protected static int energyPerDamage = 160;
    protected double absorbRatio = 1D;
    protected final EntityEquipmentSlot entityEquipmentSlot;


    public ArmorIronMan(String name, EntityEquipmentSlot equipmentSlotIn, Properties properties) {
        super(name, ItemInit.ARMOR_IRONMAN, equipmentSlotIn, properties);
        entityEquipmentSlot = equipmentSlotIn;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        if (stack.getItem() instanceof ArmorIronMan && this.getEquipmentSlot() != EntityEquipmentSlot.CHEST) {
            stack.addEnchantment(Enchantments.BINDING_CURSE, 1);
            stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public int getDamage(ItemStack stack) {
        return 0;
    }

    @SubscribeEvent
    public static void onGetHurt(LivingHurtEvent event) {
        if (SuperheroesX.DEBUG) System.out.println("<---------------------------->");
        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        if (SuperheroesX.DEBUG) System.out.println("<---------------------------->");
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ChestplateIronMan) {
            if (event.getSource() == DamageSource.FALL) {
                event.setAmount(0);
                return;
            }
            int energy = (int) (event.getAmount() * energyPerDamage);
            if (((ChestplateIronMan) chestplate.getItem()).extractEnergy(chestplate, energy, true) == energy) {
                event.setAmount(event.getAmount() - (event.getAmount() * reductionAmount));
                ((ChestplateIronMan) chestplate.getItem()).extractEnergy(chestplate, energy, false);
            } else {
                ChestplateIronMan chestplateIronMan = (ChestplateIronMan) chestplate.getItem();
                for (; chestplateIronMan.getEnergyStored(chestplate) > 0; chestplateIronMan.gotDamaged(chestplate)) {
                    event.setAmount(event.getAmount() - 1);
                }
            }
        }
    }

    /*@Override
    public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
        if (SuperheroesX.DEBUG) System.out.println(">---damageArmor---<");
        ItemStack chestplate = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ChestplateIronMan && entity instanceof EntityPlayer && isWearingFullSet((EntityPlayer) entity)) {
            if (entity.world.rand.nextInt(3) >= 3) {
                return;
            }
            ((ChestplateIronMan) chestplate.getItem()).extractEnergy(chestplate, damage * energyPerDamage, false);
        }
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ChestplateIronMan && ((ChestplateIronMan) chestplate.getItem()).getEnergyStored(chestplate) < energyPerDamage) {
            return -getArmorMaterial().getDamageReductionAmount(armorType);
        }
        return 0;
    }*/

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    private static boolean isChestplateEquipped(EntityPlayer player) {
        return player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ChestplateIronMan;
    }

    /*@Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (SuperheroesX.DEBUG) System.out.println(">---getProperties---<");
        ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ChestplateIronMan) {
            ChestplateIronMan chestplateIronMan = (ChestplateIronMan) chestplate.getItem();
            if (source.damageType.equals("fall")) {
                return new ArmorProperties(0, absorbRatio, Integer.MAX_VALUE);
            }
            *//*if (source.isUnblockable()) {
                int absorbMax = energyPerDamage > 0 ? 25 * chestplateIronMan.getEnergyStored(chestplate) / energyPerDamage : 0;
                return new ArmorProperties(0, reductionAmount * getArmorMaterial().getDamageReductionAmount(armorType) * 0.025, absorbMax);
            }*//*
            int absorbMax = energyPerDamage > 0 ? 25 * chestplateIronMan.getEnergyStored(armor) / energyPerDamage : 0;
            return new ArmorProperties(0, absorbRatio * getArmorMaterial().getDamageReductionAmount(armorType) * 0.05, absorbMax);
        }
        return new ArmorProperties(0, 0, Integer.MAX_VALUE);
    }*/

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        ModelBiped armorModel;

        if (itemStack != null) {
            armorModel = new ModelBiped(0.5F);

            armorModel.setModelAttributes(_default);
            return armorModel;
        }

        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        if (!isChestplateEquipped(player)) {
            if (SuperheroesX.DEBUG) System.out.println("<---------------------------->");
            stack.setCount(0);
        }
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, EntityItem entity) {
        if (!(this instanceof ChestplateIronMan)) stack.setCount(0);
        return false;
    }

    /*@SuppressWarnings("SpellCheckingInspection")
    @Override
    public void registerModels() {
        if (this instanceof ChestplateIronMan) {
            SuperheroesX.PROXY.registerVariantRenderer(this, this.getMetadata(new ItemStack(this)), "chestplate_ironman", "inventory")/*registerItemRenderer(this, 0, "inventory")*//*;
        }
        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
    }*/

    @Override
    public boolean isWearingFullSet(EntityPlayer player) {
        for (ItemStack stack : player.getArmorInventoryList())
            if (!(stack.getItem() instanceof ArmorIronMan))
                return false;
        return true;
    }

    @SuppressWarnings({"UnusedReturnValue", "SpellCheckingInspection"})
    public static class ChestplateIronMan extends ArmorIronMan implements /*IEnergyContainerItem,*/ IHUDInfoProvider {

        public static final String TAG_HOVERMODE_ON = "HoverModeOn";
        public static final String TAG_ON = "On";
        public static final String TAG_EHOVER_ON = "EHoverOn";
        public static final String TAG_MULTIPLIER = "Multiplier";
        private static final int COOLDOWN_MAX = 20;
        private double accelVertical = 0.5D;//0.15D
        private double speedVertical = 0.9D;
        private double sprintFuelModifier = 6.0D;
        private double speedVerticalHover = 0.55D;
        private double speedVerticalHoverSlow = 0.0D;
        private int maxTransfer = Integer.MAX_VALUE;
        private int fuelUsage = 10;
        //private int energyPerShot = 200;
        private int cooldown;
        private float defaultSpeedSideways = 0.55F;
        private float sprintSpeedModifier = 5F;
        private float damagePerHit = 5;
        public int multiplier;
        //private boolean rightClickMoved;


        public ChestplateIronMan() {
            super("ironman_chestplate", EntityEquipmentSlot.CHEST, new Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MARVEL).defaultMaxDamage(0));
            setMultiplier(new ItemStack(this), this.multiplier < 1 ? 1 : this.multiplier);
            setDefaultMaxEnergyTag(ChestplateIronMan.setDefaultEnergyTag(new ItemStack(this, 1), 0), this.getArmorMaterial().getDurability(this.entityEquipmentSlot));
        }

        public static ItemStack setDefaultMaxEnergyTag(ItemStack container, int maxEnergy) {
            if (container.getTag().contains("MaxEnergy")) return container;
            if (!container.hasTag()) {
                container.setTag(new NBTTagCompound());
            }
            container.getTag().putInt("MaxEnergy", maxEnergy);

            return container;
        }

        public static ItemStack setDefaultEnergyTag(ItemStack container, int energy) {

            if (!container.hasTag()) {
                container.setTag(new NBTTagCompound());
            }
            container.getTag().putInt("Energy", energy);

            return container;
        }

        @Override
        public void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState) {
            if (showFuel || SuperheroesX.DEBUG) {
                list.add(this.getHUDEnergyInfo(stack, this));
            }
            if (showState || SuperheroesX.DEBUG) {
                list.add(this.getHUDStatesInfo(stack));
            }
        }

        @Override
        public void onCreated(ItemStack container, World worldIn, EntityPlayer playerIn) {
            if (playerIn.getName().getString().toLowerCase().equals("minecraftschurli")) {
                setDefaultMultiplierTag(container, 4);
            }
            setDefaultMaxEnergyTag(container, getMaxEnergyStored(container));
        }

        @Override
        public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
            return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) && (slotChanged || getEnergyStored(oldStack) > 0 != getEnergyStored(newStack) > 0);
        }

        int timer = 0;
        boolean flag = false;

        @Override
        public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            setDefaultMaxEnergyTag(stack, this.getArmorMaterial().getDurability(this.entityEquipmentSlot) * (getMultiplier(stack)));
        }

        @OnlyIn(Dist.CLIENT)
        public String getHUDEnergyInfo(ItemStack stack, ChestplateIronMan item) {
            int energy = item.getEnergyStored(stack);
            int maxFuel = item.getMaxEnergyStored(stack);
            int percent = (int) Math.ceil((double) energy / (double) maxFuel * 100D);
            return SXStringHelper.getHUDFuelText(this.getName().getString(), percent, energy);
        }

        @Override
        public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
            Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

            if (slot == this.armorType) {
                multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", 0.25, 0));
            }

            return multimap;
        }

        @Override
        public boolean showDurabilityBar(ItemStack stack) {
            return true;
        }

        @Override
        public int getRGBDurabilityForDisplay(ItemStack stack) {
            return new Color(0x74C6D0).getRGB();
        }

        private void shootEnergyBlast(EntityPlayer player, @SuppressWarnings("unused") ChestplateIronMan item) {
            if (player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty()) {
                //do the shot
                RayTraceResult pew = player.rayTrace(Double.MAX_VALUE, 10, RayTraceFluidMode.NEVER);
                Entity e = pew.entity;
                if (e instanceof EntityLivingBase) {
                    EntityLivingBase hit = (EntityLivingBase) e;
                    hit.attackEntityFrom(new DamageSource("energy-blast"), damagePerHit);
                }
                if (SuperheroesX.DEBUG) System.out.println("==both==");
            } else if (player.getHeldItemMainhand().isEmpty()) {
                //do the shot
                if (SuperheroesX.DEBUG) System.out.println("==main==");
            } else if (player.getHeldItemOffhand().isEmpty()) {
                //do the shot
                if (SuperheroesX.DEBUG) System.out.println("==off==");
            }
        }

        @Override
        public double getDurabilityForDisplay(ItemStack stack) {
            if (stack.getTag() == null) {
                setDefaultEnergyTag(stack, 0);
                setDefaultMaxEnergyTag(stack, this.getArmorMaterial().getDurability(this.getEquipmentSlot()) * (getMultiplier(stack)));
            }
            return 1D - (double) stack.getTag().getInt("Energy") / (double) getMaxEnergyStored(stack);
        }

        //TODO@Override
        public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
            if (container.getTag() == null) {
                setDefaultEnergyTag(container, 0);
            }
            int stored = Math.min(container.getTag().getInt("Energy"), getMaxEnergyStored(container));
            int receive = Math.min(maxReceive, Math.min(getMaxEnergyStored(container) - stored, maxTransfer));

            if (!simulate) {
                stored += receive;
                container.getTag().putInt("Energy", stored);
            }
            return receive;
        }

        //TODO@Override
        public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
            if (container.getTag() == null) {
                setDefaultEnergyTag(container, 0);
            }
            int stored = Math.min(container.getTag().getInt("Energy"), getMaxEnergyStored(container));
            int extract = Math.min(maxExtract, stored);

            if (!simulate) {
                stored -= extract;
                container.getTag().putInt("Energy", stored);
            }
            return extract;
        }

        @SuppressWarnings("unchecked")
        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
            ItemStack itemstack = playerIn.getHeldItem(handIn);
            for (ItemStack itemstack1 : playerIn.inventory.armorInventory) {
                if (!itemstack1.isEmpty()) {
                    return new ActionResult(EnumActionResult.FAIL, itemstack);
                }
            }
            playerIn.setItemStackToSlot(EntityEquipmentSlot.CHEST, itemstack.copy());
            itemstack.setCount(0);
            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
        }

        //TODO@Override
        public int getEnergyStored(ItemStack container) {
            if (container.getTag() == null) {
                setDefaultEnergyTag(container, 0);
            }
            return Math.min(container.getTag().getInt("Energy"), getMaxEnergyStored(container));
        }

        @Override
        public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
            if (isWearingFullSet(player)) {
                if (cooldown == 0) {
                    if (player.onGround && getEnergyStored(stack) != getMaxEnergyStored(stack)) {
                        receiveEnergy(stack, 100 * (this.getMultiplier(stack)), false);
                    }
                } else {
                    cooldown--;
                }
                flyUser(player, stack, this, false);
                if (SyncHandler.isRightClickDown(player)) {
                    shootEnergyBlast(player, this);
                }
                super.onArmorTick(stack, world, player);
                player.addPotionEffect(new PotionEffect(PotionEffectInit.INVISIBLE_STRENGTH.asPotionEffect(), 0, 3, true, false));

            } else {
                if (!world.isRemote && timer > 20) {
                    if (SuperheroesX.DEBUG) System.out.println("test");
                    for (ItemStack stack1 : player.getArmorInventoryList()) {
                        if ((!stack1.isEmpty() && !(stack1.getItem() instanceof ArmorIronMan))) {
                            if (SuperheroesX.DEBUG) System.out.println(stack + "" + stack1);
                            timer = 0;
                            if (!flag) {
                                ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                                flag = true;
                                player.addItemStackToInventory(chest.copy());
                                chest.setCount(0);
                            }

                            player.sendStatusMessage(new TextComponentString("You need an empty armor inventory to equip this"), false);
                            return;
                        }
                    }
                    Item[] items = {ItemInit.BOOTS_IRONMAN.asItem(), ItemInit.LEGGINGS_IRONMAN.asItem(), ItemInit.HELMET_IRONMAN.asItem()};
                    int[] ints = {0, 1, 3};
                    ItemStack itemStackBuffer;
                    NBTTagCompound nbt;
                    for (int i = 0; i < items.length; i++) {
                        Item item = items[i];
                        itemStackBuffer = new ItemStack(item);
                        itemStackBuffer.addEnchantment(Enchantments.BINDING_CURSE, 1);
                        itemStackBuffer.addEnchantment(Enchantments.VANISHING_CURSE, 1);
                        nbt = itemStackBuffer.getTag();
                        nbt.putInt("HideFlags", 1);
                        itemStackBuffer.setTag(nbt);
                        player.inventory.armorInventory.set(ints[i], itemStackBuffer);
                    }
                    return;
                }
                timer++;
                flag = false;
            }
        }

        //TODO@Override
        public int getMaxEnergyStored(ItemStack container) {
            if (container.getTag() == null) {
                setDefaultMaxEnergyTag(container, this.getArmorMaterial().getDurability(this.getEquipmentSlot()) * (getMultiplier(container)));
            }
            return container.getTag().getInt("MaxEnergy");
        }

        /*@Override TODO
        public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
            return new EnergyContainerItemWrapper(stack, this);
        }*/

        public void flyUser(EntityPlayer user, ItemStack stack, ChestplateIronMan item, boolean force) {
            Item chestItem = StackUtil.getItem(stack);
            ChestplateIronMan jetpack = (ChestplateIronMan) chestItem;
            if (jetpack.isOn(stack)) {
                boolean hoverMode = jetpack.isHoverModeOn(stack);
                double hoverSpeed = /*ModConfig.client.controls.invertHoverSneakingBehavior == */SyncHandler.isDescendKeyDown(user) ? this.speedVerticalHoverSlow : this.speedVerticalHover;
                boolean flyKeyDown = force || SyncHandler.isFlyKeyDown(user);
                boolean descendKeyDown = SyncHandler.isDescendKeyDown(user);
                double currentAccel = this.accelVertical * (user.motionY < 0.3D ? 2.5D : 1.0D);
                double currentSpeedVertical = this.speedVertical * (user.isInWater() ? 0.4D : 1.0D);

                if (flyKeyDown || (hoverMode && !user.onGround)) {
                    jetpack.extractEnergy(stack, (int) (user.isSprinting() ? Math.round(this.fuelUsage * this.sprintFuelModifier) : this.fuelUsage), false);

                    if (item.getEnergyStored(stack) > 0) {
                        if (flyKeyDown) {
                            cooldown = COOLDOWN_MAX;
                            if (!hoverMode) {
                                user.motionY = Math.min(user.motionY + currentAccel, currentSpeedVertical);
                            } else {
                                if (descendKeyDown) {
                                    user.motionY = Math.min(user.motionY + currentAccel, -speedVerticalHoverSlow);
                                } else {
                                    user.motionY = Math.min(user.motionY + currentAccel, speedVerticalHover);
                                }
                            }
                        } else {
                            user.motionY = Math.min(user.motionY + currentAccel, -hoverSpeed);
                        }

                        float speedSideways = user.isSneaking() ? this.defaultSpeedSideways * 0.5F : this.defaultSpeedSideways;
                        float speedForward = user.isSprinting() ? speedSideways * this.sprintSpeedModifier : speedSideways;
                        if (SyncHandler.isForwardKeyDown(user)) {
                            if (SuperheroesX.DEBUG) System.out.println("forward " + speedForward);
                            user.moveRelative(0, speedForward, speedForward, 0);
                        }
                        if (SyncHandler.isBackwardKeyDown(user)) {
                            user.moveRelative(0, -speedSideways, speedSideways * 0.8F, 0);
                        }
                        if (SyncHandler.isLeftKeyDown(user)) {
                            user.moveRelative(speedSideways, 0, speedSideways, 0);
                        }
                        if (SyncHandler.isRightKeyDown(user)) {
                            user.moveRelative(-speedSideways, 0, speedSideways, 0);
                        }

                        if (!user.world.isRemote) {
                            user.fallDistance = 0.0F;

                            if (user instanceof EntityPlayerMP) {
                                try {
                                    LivingTickHandler.floatingTickCount.setInt(((EntityPlayerMP) user).connection, 0);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

            //Emergency Hover
            if (!user.world.isRemote && this.isEHoverModeOn(stack)) {
                if (item.getEnergyStored(stack) > 0 && (!this.isHoverModeOn(stack) || !this.isOn(stack))) {
                    if (user.posY < -5) {
                        this.doEHover(stack, user);
                    } else {
                        if (!user.abilities.isCreativeMode && user.fallDistance - 1.2F >= user.getHealth()) {
                            for (int j = 0; j <= 16; j++) {
                                int x = Math.round((float) user.posX - 0.5F);
                                int y = Math.round((float) user.posY) - j;
                                int z = Math.round((float) user.posZ - 0.5F);
                                if (!user.world.isAirBlock(new BlockPos(x, y, z))) {
                                    this.doEHover(stack, user);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        @OnlyIn(Dist.CLIENT)
        @SuppressWarnings("unchecked")
        public void shiftInformation(ItemStack stack, List<ITextComponent> list) {
            list.add(SXStringHelper.getStateText(this.isOn(stack)));
            list.add(SXStringHelper.getHoverModeText(this.isHoverModeOn(stack)));
            SXStringHelper.addDescriptionLines(list, this.getName().getString(), TextFormatting.GREEN);
        }

        /*@Override
        @OnlyIn(Dist.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            information(stack, this, tooltip);
            if (SXStringHelper.canShowDetails()) {
                shiftInformation(stack, tooltip);
            } else {
                tooltip.add(new TextComponentString(SXStringHelper.getShiftText()));
            }
        }*/

        @OnlyIn(Dist.CLIENT)
        @SuppressWarnings("unchecked")
        public void information(ItemStack stack, ChestplateIronMan item, List<ITextComponent> list) {
            list.add(new TextComponentString(SXStringHelper.getFuelText(item.getEnergyStored(stack), item.getMaxEnergyStored(stack), false)));
        }

        public boolean isOn(ItemStack stack) {
            return NBTHelper.getBooleanFallback(stack, TAG_ON, true);
        }

        public boolean isHoverModeOn(ItemStack stack) {
            return NBTHelper.getBoolean(stack, TAG_HOVERMODE_ON);
        }

        public boolean isEHoverModeOn(ItemStack stack) {
            return NBTHelper.getBooleanFallback(stack, TAG_EHOVER_ON, true);
        }

        @OnlyIn(Dist.CLIENT)
        public String getHUDStatesInfo(ItemStack stack) {
            Boolean engine = this.isOn(stack);
            Boolean hover = this.isHoverModeOn(stack);
            return SXStringHelper.getHUDStateText(engine, hover, null);
        }

        public void toggleState(boolean on, ItemStack stack, @Nullable String type, String tag, EntityPlayer player, boolean showInChat) {
            if (tag.equals(TAG_HOVERMODE_ON) || tag.equals(TAG_ON) || tag.equals(TAG_EHOVER_ON))
                NBTHelper.setBoolean(stack, tag, (!on));

            if (player != null && showInChat) {
                String color = on ? TextFormatting.RED.toString() : TextFormatting.GREEN.toString();
                type = type != null && !type.equals("") ? "chat." + this.getName().getString() + "." + type + ".on" : "chat." + this.getName().getString() + ".on";
                String msg = SXStringHelper.localize(type) + " " + color + SXStringHelper.localize("chat." + (on ? "disabled" : "enabled"));
                player.sendStatusMessage(new TextComponentString(msg), false);
            }
        }

        public void doEHover(ItemStack armor, EntityLivingBase user) {
            NBTHelper.setBoolean(armor, TAG_ON, true);
            NBTHelper.setBoolean(armor, TAG_HOVERMODE_ON, true);

            if (user instanceof EntityPlayer) {
                ((EntityPlayer) user).sendStatusMessage(new TextComponentString(StringHelper.LIGHT_RED + SXStringHelper.localize("chat.item.chestplate_ironman.emergencyHoverMode.msg")), false);
            }
        }

        public void gotDamaged(ItemStack chestplate) {
            ((ChestplateIronMan) chestplate.getItem()).extractEnergy(chestplate, energyPerDamage, false);
            cooldown = COOLDOWN_MAX;
        }

        /*@Override
        public int getMetadata(ItemStack stack) {
            return getMultiplier(stack) - 1;
        }*/

        public void setMultiplier(ItemStack container, int multiplier) {
            if (container.getTag() == null) {
                setDefaultMultiplierTag(container, 1);
            }
            this.multiplier = multiplier;
            container.getTag().putInt(TAG_MULTIPLIER, multiplier);
        }

        public int getMultiplier(ItemStack container) {
            if (container.getTag() == null) {
                setDefaultMultiplierTag(container, 1);
            }
            int multiplier = container.getTag().getInt(TAG_MULTIPLIER);
            this.multiplier = multiplier;
            return multiplier;
        }

        private ItemStack setDefaultMultiplierTag(ItemStack container, int multiplier) {
            if (!container.hasTag()) {
                container.setTag(new NBTTagCompound());
            }
            container.getTag().putInt(TAG_MULTIPLIER, multiplier);
            return container;
        }

        @Override
        public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
            if (group == CustomItemGroup.Groups.SUPERHEROES_X_TAB_MARVEL) {
                items.add(getTieredItemStack(1, false));
                items.add(getTieredItemStack(1, true));
                items.add(getTieredItemStack(2, false));
                items.add(getTieredItemStack(2, true));
            }
        }

        public ItemStack getTieredItemStack(int tier, boolean full) {
            ItemStack stack = new ItemStack(this, 1);
            setDefaultMaxEnergyTag(
                    setDefaultEnergyTag(
                            setDefaultMultiplierTag(stack, tier),
                            full ? this.getArmorMaterial().getDurability(this.entityEquipmentSlot) * tier : 0),
                    this.getArmorMaterial().getDurability(this.entityEquipmentSlot) * tier);
            stack.getTag().putBoolean(TAG_HOVERMODE_ON, false);
            stack.getTag().putBoolean(TAG_ON, true);
            return stack;
        }

        @Override
        public ITextComponent getName() {
            return new TextComponentString(super.getName().getString() + "_" + multiplier);
        }

        public static final class StackUtil {

            public static Item getItem(ItemStack stack) {
                if (stack == null) {
                    return null;
                }

                return stack.getItem();
            }
        }
    }
}