package mini.SuperheroesX.objects.armor;


import cofh.redstoneflux.api.IEnergyContainerItem;
import cofh.redstoneflux.util.EnergyContainerItemWrapper;
import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.init.PotionInit;
import mini.SuperheroesX.util.config.Config;
import mini.SuperheroesX.util.handlers.SyncHandler;
import mini.SuperheroesX.util.helpers.NBTHelper;
import mini.SuperheroesX.util.helpers.SXStringHelper;
import mini.SuperheroesX.util.helpers.StringHelper;
import mini.SuperheroesX.util.interfaces.IHUDInfoProvider;
import mini.SuperheroesX.util.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
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
import java.awt.*;
import java.util.List;

import static mini.SuperheroesX.util.handlers.LivingTickHandler.floatingTickCount;

@EventBusSubscriber
public class ArmorIronMan extends ItemArmor implements IHasModel, ISpecialArmor
{

    protected float reductionAmmount = 1F;
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
                return new ArmorProperties(0, reductionAmmount * getArmorMaterial().getDamageReductionAmount(armorType) * 0.025, absorbMax);
            }
            int absorbMax = energyPerDamage > 0 ? 25 * chestplateIronMan.getEnergyStored(chestplate) / energyPerDamage : 0;
            return new ArmorProperties(0, reductionAmmount * getArmorMaterial().getDamageReductionAmount(armorType) * 0.05, absorbMax);
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
            if (((ChestplateIronMan) chestplate.getItem()).getEnergyStored(chestplate) - ((event.getAmount() - (event.getAmount() * reductionAmmount)) * energyPerDamage) > 0) {
                event.setAmount(event.getAmount() * reductionAmmount);
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

    /*@Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
                                    ModelBiped defaultModel) {

        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemArmor) {

                EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
                ModelBiped armorModel = null;
                switch (type) {
                    case HEAD:
                    case LEGS:
                        armorModel = SuperheroesX.PROXY.getArmorModel(0);
                        break;
                    case FEET:
                    case CHEST:
                        armorModel = SuperheroesX.PROXY.getArmorModel(1);
                        break;
                    default:
                        break;
                }

                armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST)
                        || (armorSlot == EntityEquipmentSlot.CHEST);
                armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                armorModel.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
                        || (armorSlot == EntityEquipmentSlot.FEET);
                armorModel.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
                        || (armorSlot == EntityEquipmentSlot.FEET);

                armorModel.isSneak = defaultModel.isSneak;
                armorModel.isRiding = defaultModel.isRiding;
                armorModel.isChild = defaultModel.isChild;
                armorModel.rightArmPose = defaultModel.rightArmPose;
                armorModel.leftArmPose = defaultModel.leftArmPose;

                return armorModel;
            }
        }
        return null;
    }*/

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

    @Override
    public void registerModels() {
        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
    }


    public static class ChestplateIronMan extends ArmorIronMan implements IEnergyContainerItem, IHUDInfoProvider {

        public static final String TAG_HOVERMODE_ON = "HoverModeOn";
        public static final String TAG_ON = "On";
        public static final String TAG_EHOVER_ON = "EHoverOn";
        private static final int COOLDOWN_MAX = 20;
        private double accelVertical = 0.5D;//0.15D
        private double speedVertical = 0.9D;
        private double sprintFuelModifier = 6.0D;
        private double speedVerticalHover = 0.45D;
        private double speedVerticalHoverSlow = 0.0D;
        private int capacity;
        private int maxTransfer = Integer.MAX_VALUE;
        private int fuelUsage = 200;
        private int energyPerShot = 200;
        private int cooldown;
        private float defaultSpeedSideways = 0.21F;
        private float sprintSpeedModifier = 2.4F;


        public ChestplateIronMan() {
            super("chestplate_ironman", 1, EntityEquipmentSlot.CHEST);
            this.capacity = this.getArmorMaterial().getDurability(this.armorType);
            setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB);
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
        public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
            if (cooldown == 0) {
                if (player.onGround && getEnergyStored(stack) != getMaxEnergyStored(stack)) {
                    receiveEnergy(stack, 100, false);
                }
            } else {
                cooldown--;
            }
            if (ArmorIronMan.fullSetEquipped(player)) {
                flyUser(player, stack, this, false);
                if (SyncHandler.isRightClickDown(player)) {
                    shootEnergyBlast(player, this);
                }
                super.onArmorTick(world, player, stack);
                player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 0, 3, true, false));
            }
            if (timer > 20) {
                for (ItemStack stack1 : player.getArmorInventoryList()) {
                    if ((!stack1.isEmpty() && !(stack1.getItem() instanceof ArmorIronMan))) {
                        timer = 0;
                        flag = true;
                        player.inventory.removeStackFromSlot(EntityEquipmentSlot.CHEST.getSlotIndex());
                        player.inventory.addItemStackToInventory(stack);
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
            }
            timer++;
        }

        private void shootEnergyBlast(EntityPlayer player, ChestplateIronMan item) {
            if (player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty()) {
                //do the shot
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

        @Override
        public int getMaxEnergyStored(ItemStack container) {
            return capacity;
        }

        public static ItemStack setDefaultEnergyTag(ItemStack container, int energy) {

            if (!container.hasTagCompound()) {
                container.setTagCompound(new NBTTagCompound());
            }
            container.getTagCompound().setInteger("Energy", energy);

            return container;
        }

        @Override
        public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {

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

                if (flyKeyDown || hoverMode && !user.onGround) {
                    item.extractEnergy(stack, (int) (user.isSprinting() ? Math.round(this.fuelUsage * this.sprintFuelModifier) : this.fuelUsage), false);

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

        public void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showInChat) {
            NBTHelper.setBoolean(stack, tag, !on);

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
