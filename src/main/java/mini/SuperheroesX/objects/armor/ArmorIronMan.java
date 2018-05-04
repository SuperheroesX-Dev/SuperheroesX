package mini.SuperheroesX.objects.armor;


import cofh.redstoneflux.api.IEnergyContainerItem;
import cofh.redstoneflux.util.EnergyContainerItemWrapper;
import mcp.MethodsReturnNonnullByDefault;
import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.init.PotionInit;
import mini.SuperheroesX.util.Reference;
import mini.SuperheroesX.util.config.Config;
import mini.SuperheroesX.util.handlers.SyncHandler;
import mini.SuperheroesX.util.helpers.NBTHelper;
import mini.SuperheroesX.util.helpers.SXStringHelper;
import mini.SuperheroesX.util.helpers.StringHelper;
import mini.SuperheroesX.util.interfaces.IHUDInfoProvider;
import mini.SuperheroesX.util.interfaces.IHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
import java.util.List;

import static mini.SuperheroesX.util.handlers.LivingTickHandler.floatingTickCount;

@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@EventBusSubscriber(modid = Reference.MODID)
public class ArmorIronMan extends ItemArmor implements IHasModel, ISpecialArmor {

    protected float reductionAmount = 1F;
    protected int energyPerDamage = 160;

    public ArmorIronMan(String name, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(ItemInit.ARMOR_IRONMAN, renderIndexIn, equipmentSlotIn);

        setUnlocalizedName(name);
        setRegistryName(name);

        ItemInit.ITEMS.add(this);
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

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
        if (SuperheroesX.DEBUG) System.out.println(">---damageArmor---<");
        ItemStack chestplate = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ChestplateIronMan && fullSetEquipped(entity)) {
            if (entity.world.rand.nextInt(3) >= 3) {
                return;
            }
            ((ChestplateIronMan) chestplate.getItem()).extractEnergy(chestplate, damage * energyPerDamage, false);
        }
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (SuperheroesX.DEBUG) System.out.println(">---getProperties---<");
        ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ChestplateIronMan) {
            ChestplateIronMan chestplateIronMan = (ChestplateIronMan) chestplate.getItem();
            if (source.isUnblockable()) {
                int absorbMax = energyPerDamage > 0 ? 25 * chestplateIronMan.getEnergyStored(chestplate) / energyPerDamage : 0;
                return new ArmorProperties(0, reductionAmount * getArmorMaterial().getDamageReductionAmount(armorType) * 0.025, absorbMax);
            }
            int absorbMax = energyPerDamage > 0 ? 25 * chestplateIronMan.getEnergyStored(chestplate) / energyPerDamage : 0;
            return new ArmorProperties(0, reductionAmount * getArmorMaterial().getDamageReductionAmount(armorType) * 0.05, absorbMax);
            // 0.05 = 1 / 20 (max armor)
        }
        return new ArmorProperties(0, 0, Integer.MAX_VALUE);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ChestplateIronMan && ((ChestplateIronMan) chestplate.getItem()).getEnergyStored(chestplate) < energyPerDamage) {
            return -getArmorMaterial().getDamageReductionAmount(armorType);
        }
        return 0;
    }

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

    @SubscribeEvent
    public void onGetHurt(LivingHurtEvent event) {
        if (SuperheroesX.DEBUG) System.out.println("<---------------------------->");
        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        if (SuperheroesX.DEBUG) System.out.println("<---------------------------->");
        event.setCanceled(true);
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (chestplate.getItem() instanceof ChestplateIronMan) {
            if (((ChestplateIronMan) chestplate.getItem()).getEnergyStored(chestplate) - ((event.getAmount() - (event.getAmount() * reductionAmount)) * energyPerDamage) > 0) {
                event.setAmount(event.getAmount() * reductionAmount);
            } else {
                ChestplateIronMan chestplateIronMan = (ChestplateIronMan) chestplate.getItem();
                for (; chestplateIronMan.getEnergyStored(chestplate) > 0; chestplateIronMan.gotDamaged(chestplate)) {
                    event.setAmount(event.getAmount() - 1);
                }
            }
        }

    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;

    }

        @Override
        @SideOnly(Side.CLIENT)
        public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
            ModelBiped armorModel;

            if (itemStack != null) {
               armorModel = new ModelBiped(0.5F);

                armorModel.setModelAttributes(_default);
                return armorModel;
            }

            return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
        }

    private static boolean fullSetEquipped(EntityLivingBase entity) {
        for (ItemStack armor : entity.getArmorInventoryList()) {
            if (!(armor.getItem() instanceof ArmorIronMan)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

        if (!isChestplateEquipped(player)) {
            if (SuperheroesX.DEBUG) System.out.println("<---------------------------->");
            player.inventory.armorItemInSlot(this.getEquipmentSlot().getIndex()).setCount(0);
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public void registerModels() {
        if (this instanceof ChestplateIronMan) {
            SuperheroesX.PROXY.registerVariantRenderer(this, this.getMetadata(new ItemStack(this)), "chestplate_ironman", "inventory")/*registerItemRenderer(this, 0, "inventory")*/;

        }
        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
    }

    @SuppressWarnings({"UnusedReturnValue", "SpellCheckingInspection"})
    public static class ChestplateIronMan extends ArmorIronMan implements IEnergyContainerItem, IHUDInfoProvider {

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
        private float defaultSpeedSideways = 0.8F;
        private float sprintSpeedModifier = 2.4F;
        private float damagePerHit = 5;
        public int multiplier;
        //private boolean rightClickMoved;


        public ChestplateIronMan() {
            super("chestplate_ironman", 1, EntityEquipmentSlot.CHEST);
            this.setHasSubtypes(true);
            this.setMaxDamage(0);
            setMultiplier(new ItemStack(this), this.multiplier < 1 ? 1 : this.multiplier);
            setDefaultMaxEnergyTag(ChestplateIronMan.setDefaultEnergyTag(new ItemStack(this, 1), 0), this.getArmorMaterial().getDurability(this.getEquipmentSlot()));
        }

        public static ItemStack setDefaultMaxEnergyTag(ItemStack container, int maxEnergy) {
            if (!container.hasTagCompound()) {
                container.setTagCompound(new NBTTagCompound());
            }
            container.getTagCompound().setInteger("MaxEnergy", maxEnergy);

            return container;
        }

        @Override
        public void onCreated(ItemStack container, World worldIn, EntityPlayer playerIn) {
            if (playerIn.getName().toLowerCase().equals("minecraftschurli")) {
                setDefaultMultiplierTag(container, 4);
            }
            setDefaultMaxEnergyTag(container, getMaxEnergyStored(container));
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

        @SideOnly(Side.CLIENT)
        public String getHUDEnergyInfo(ItemStack stack, ChestplateIronMan item) {
            int energy = item.getEnergyStored(stack);
            int maxFuel = item.getMaxEnergyStored(stack);
            int percent = (int) Math.ceil((double) energy / (double) maxFuel * 100D);
            return SXStringHelper.getHUDFuelText(this.getUnlocalizedName(), percent, energy);
        }

        @Override
        public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
            return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) && (slotChanged || getEnergyStored(oldStack) > 0 != getEnergyStored(newStack) > 0);
        }

        int timer = 0;
        boolean flag = false;

        @Override
        public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            setDefaultMaxEnergyTag(stack, this.getArmorMaterial().getDurability(this.getEquipmentSlot()) * (getMultiplier(stack)));
        }

        private void shootEnergyBlast(EntityPlayer player, @SuppressWarnings("unused") ChestplateIronMan item) {
            if (player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty()) {
                //do the shot
                RayTraceResult pew = player.rayTrace(Double.MAX_VALUE, 10);
                Entity e = pew.entityHit;
                if (pew.entityHit instanceof EntityLivingBase) {
                    EntityLivingBase hit = (EntityLivingBase) e;
                    hit.attackEntityFrom(new DamageSource("energy-blast").setProjectile(), damagePerHit);
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
        public boolean showDurabilityBar(ItemStack stack) {
            return true;
        }

        @Override
        public int getRGBDurabilityForDisplay(ItemStack stack) {
            return new Color(0x74C6D0).getRGB();
        }

        @Override
        public double getDurabilityForDisplay(ItemStack stack) {

            if (stack.getTagCompound() == null) {
                setDefaultEnergyTag(stack, 0);
                setDefaultMaxEnergyTag(stack, this.getArmorMaterial().getDurability(this.getEquipmentSlot()) * (getMultiplier(stack)));
            }
            return 1D - (double) stack.getTagCompound().getInteger("Energy") / (double) getMaxEnergyStored(stack);
        }

        @Override
        public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
            if (SuperheroesX.DEBUG) System.out.println(">---receiveEnergy---<");

            if (container.getTagCompound() == null) {
                setDefaultEnergyTag(container, 0);
            }
            int stored = Math.min(container.getTagCompound().getInteger("Energy"), getMaxEnergyStored(container));
            int receive = Math.min(maxReceive, Math.min(getMaxEnergyStored(container) - stored, maxTransfer));

            if (!simulate) {
                stored += receive;
                container.getTagCompound().setInteger("Energy", stored);
            }
            return receive;
        }

        @Override
        public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
            if (SuperheroesX.DEBUG) System.out.println(">---extractEnergy---<");

            if (container.getTagCompound() == null) {
                setDefaultEnergyTag(container, 0);
            }
            int stored = Math.min(container.getTagCompound().getInteger("Energy"), getMaxEnergyStored(container));
            int extract = Math.min(maxExtract, stored);

            if (!simulate) {
                stored -= extract;
                container.getTagCompound().setInteger("Energy", stored);
            }
            return extract;
        }

        @Override
        public int getEnergyStored(ItemStack container) {

            if (container.getTagCompound() == null) {
                setDefaultEnergyTag(container, 0);
            }
            return Math.min(container.getTagCompound().getInteger("Energy"), getMaxEnergyStored(container));
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

        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            if (ArmorIronMan.fullSetEquipped(player)) {
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
                super.onArmorTick(world, player, stack);
                player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 0, 3, true, false));

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
                    Item[] items = {ItemInit.BOOTS_IRONMAN, ItemInit.LEGGINGS_IRONMAN, ItemInit.HELMET_IRONMAN};
                    int[] ints = {0, 1, 3};
                    ItemStack itemStackBuffer;
                    NBTTagCompound nbt;
                    for (int i = 0; i < items.length; i++) {
                        Item item = items[i];
                        itemStackBuffer = new ItemStack(item);
                        itemStackBuffer.addEnchantment(Enchantments.BINDING_CURSE, 1);
                        itemStackBuffer.addEnchantment(Enchantments.VANISHING_CURSE, 1);
                        nbt = itemStackBuffer.getTagCompound();
                        nbt.setInteger("HideFlags", 1);
                        itemStackBuffer.setTagCompound(nbt);
                        player.inventory.armorInventory.set(ints[i], itemStackBuffer);
                    }
                    return;
                }
                timer++;
                flag = false;
            }
        }

        public static ItemStack setDefaultEnergyTag(ItemStack container, int energy) {

            if (!container.hasTagCompound()) {
                container.setTagCompound(new NBTTagCompound());
            }
            container.getTagCompound().setInteger("Energy", energy);

            return container;
        }

        @Override
        public int getMaxEnergyStored(ItemStack container) {
            if (container.getTagCompound() == null) {
                setDefaultMaxEnergyTag(container, this.getArmorMaterial().getDurability(this.getEquipmentSlot()) * (getMultiplier(container)));
            }
            return container.getTagCompound().getInteger("MaxEnergy");
        }

        @Override
        public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {

            return new EnergyContainerItemWrapper(stack, this);
        }

        public void flyUser(EntityPlayer user, ItemStack stack, ChestplateIronMan item, boolean force) {
            Item chestItem = StackUtil.getItem(stack);
            ChestplateIronMan jetpack = (ChestplateIronMan) chestItem;
            if (jetpack.isOn(stack)) {
                boolean hoverMode = jetpack.isHoverModeOn(stack);
                double hoverSpeed = Config.invertHoverSneakingBehavior == SyncHandler.isDescendKeyDown(user) ? this.speedVerticalHoverSlow : this.speedVerticalHover;
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
                                    floatingTickCount.setInt(((EntityPlayerMP) user).connection, 0);
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
                        if (!user.capabilities.isCreativeMode && user.fallDistance - 1.2F >= user.getHealth()) {
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

        @SideOnly(Side.CLIENT)
        @SuppressWarnings("unchecked")
        public void shiftInformation(ItemStack stack, List list) {

            list.add(SXStringHelper.getStateText(this.isOn(stack)));
            list.add(SXStringHelper.getHoverModeText(this.isHoverModeOn(stack)));
            SXStringHelper.addDescriptionLines(list, this.getUnlocalizedName(), TextFormatting.GREEN.toString());
        }

        @Override
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            information(stack, this, tooltip);
            if (SXStringHelper.canShowDetails()) {
                shiftInformation(stack, tooltip);
            } else {
                tooltip.add(SXStringHelper.getShiftText());
            }
        }

        @SideOnly(Side.CLIENT)
        @SuppressWarnings("unchecked")
        public void information(ItemStack stack, ChestplateIronMan item, List list) {
            list.add(SXStringHelper.getFuelText(item.getEnergyStored(stack), item.getMaxEnergyStored(stack), false));
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

        @SideOnly(Side.CLIENT)
        public String getHUDStatesInfo(ItemStack stack) {
            Boolean engine = this.isOn(stack);
            Boolean hover = this.isHoverModeOn(stack);
            return SXStringHelper.getHUDStateText(engine, hover, null);
        }

        public void toggleState(boolean on, ItemStack stack, @Nullable String type, String tag, EntityPlayer player, boolean showInChat) {
            if (tag.equals(TAG_HOVERMODE_ON) || tag.equals(TAG_ON))
                NBTHelper.setBoolean(stack, tag, (!on));

            if (player != null && showInChat) {
                String color = on ? TextFormatting.RED.toString() : TextFormatting.GREEN.toString();
                type = type != null && !type.equals("") ? "chat." + this.getUnlocalizedName() + "." + type + ".on" : "chat." + this.getUnlocalizedName() + ".on";
                String msg = SXStringHelper.localize(type) + " " + color + SXStringHelper.localize("chat." + (on ? "disabled" : "enabled"));
                player.sendStatusMessage(new TextComponentString(msg), false);
            }
        }

        public void doEHover(ItemStack armor, EntityLivingBase user) {
            NBTHelper.setBoolean(armor, TAG_ON, true);
            NBTHelper.setBoolean(armor, TAG_HOVERMODE_ON, true);

            if (user instanceof EntityPlayer) {

                ((EntityPlayer) user).sendStatusMessage(new TextComponentString(StringHelper.LIGHT_RED + SXStringHelper.localize("chat.itemJetpack.emergencyHoverMode.msg")), false);
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
            if (container.getTagCompound() == null) {
                setDefaultMultiplierTag(container, 1);
            }
            this.multiplier = multiplier;
            container.getTagCompound().setInteger(TAG_MULTIPLIER, multiplier);
        }

        public int getMultiplier(ItemStack container) {
            if (container.getTagCompound() == null) {
                setDefaultMultiplierTag(container, 1);
            }
            int multiplier = container.getTagCompound().getInteger(TAG_MULTIPLIER);
            this.multiplier = multiplier;
            return multiplier;
        }

        private ItemStack setDefaultMultiplierTag(ItemStack container, int multiplier) {
            if (!container.hasTagCompound()) {
                container.setTagCompound(new NBTTagCompound());
            }
            container.getTagCompound().setInteger(TAG_MULTIPLIER, multiplier);
            return container;
        }

        @SuppressWarnings("PointlessArithmeticExpression")
        @Override
        @SideOnly(Side.CLIENT)
        public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
            if (tab == SuperheroesX.SUPERHEROES_X_TAB_MARVEL) {
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
                            full ? this.getArmorMaterial().getDurability(this.getEquipmentSlot()) * tier : 0),
                    this.getArmorMaterial().getDurability(this.getEquipmentSlot()) * tier);
            stack.getTagCompound().setBoolean(TAG_HOVERMODE_ON, false);
            stack.getTagCompound().setBoolean(TAG_ON, true);
            return stack;
        }

        @Override
        public String getUnlocalizedName(ItemStack stack) {
            return super.getUnlocalizedName(stack) + "_" + getMultiplier(stack);
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
