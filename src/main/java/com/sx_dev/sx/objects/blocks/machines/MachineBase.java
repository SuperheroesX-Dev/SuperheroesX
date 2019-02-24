package com.sx_dev.sx.objects.blocks.machines;

import com.sx_dev.sx.objects.blocks.BlockBase;
import com.sx_dev.sx.util.Reference;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({"unused", "WeakerAccess", "NullableProblems"})
public final class MachineBase {

    private MachineBase(){}

    public static abstract class BlockMachine extends BlockBase {

        public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
        public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
        private final int guiId;

        public BlockMachine(String name, Properties properties, int guiId) {
            super(name, properties.doesNotBlockMovement().sound(SoundType.METAL));
            this.guiId = guiId;
            setDefaultState(getDefaultState().with(FACING, EnumFacing.NORTH).with(ACTIVE, false));
        }

        @Override
        public ItemStack getPickBlock(IBlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, EntityPlayer player) {
            return new ItemStack(getItemDropped(state, null, pos, 0));
        }

        @Override
        public IBlockState getStateForPlacement(IBlockState state, EnumFacing facing, IBlockState state2, IWorld world, BlockPos pos1, BlockPos pos2, EnumHand hand) {
            return this.getDefaultState().with(FACING, facing);
        }

        @Override
        public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
            if (!worldIn.isRemote) {
                //SuperheroesX.INSTANCE, this.guiId, worldIn, pos.getX(), pos.getY(), pos.getZ()
            }
            return true;
        }

        @Override
        public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
            worldIn.setBlockState(pos, this.getDefaultState().with(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        }

        /*@Override
        public abstract void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state);*/

        @Override
        public void onBlockAdded(IBlockState state, World worldIn, BlockPos pos, IBlockState oldState) {
            if (!worldIn.isRemote) {
                IBlockState north = worldIn.getBlockState(pos.north());
                IBlockState south = worldIn.getBlockState(pos.south());
                IBlockState west = worldIn.getBlockState(pos.west());
                IBlockState east = worldIn.getBlockState(pos.east());
                EnumFacing face = state.get(FACING);

                if (face == EnumFacing.NORTH && north.isFullCube() && !south.isFullCube()) face = EnumFacing.SOUTH;
                else if (face == EnumFacing.SOUTH && south.isFullCube() && !north.isFullCube())
                    face = EnumFacing.NORTH;
                else if (face == EnumFacing.WEST && west.isFullCube() && !east.isFullCube()) face = EnumFacing.EAST;
                else if (face == EnumFacing.EAST && east.isFullCube() && !west.isFullCube()) face = EnumFacing.WEST;
                worldIn.setBlockState(pos, state.with(FACING, face), 2);
            }
        }

        @Nullable
        @Override
        public abstract TileEntity createTileEntity(IBlockState state, IBlockReader world);

        @Override
        public boolean hasTileEntity(IBlockState state) {
            return true;
        }
    }

    public static abstract class TileMachine extends TileEntity implements IInventory, ITickable {

        private NonNullList<ItemStack> inventory;

        public TileMachine(TileEntityType<?> type, int invSize) {
            super(type);
            inventory = NonNullList.withSize(invSize, ItemStack.EMPTY);
        }

        @Override
        public int getSizeInventory() {
            return inventory.size();
        }

        @Override
        public boolean isEmpty() {
            for (ItemStack stack : this.inventory) {
                if (!stack.isEmpty()) return false;
            }
            return true;
        }

        @Override
        @Nonnull
        public ItemStack getStackInSlot(int index) {
            return inventory.get(index);
        }

        @Override
        @Nonnull
        public ItemStack decrStackSize(int index, int count) {
            return ItemStackHelper.getAndSplit(this.inventory, index, count);
        }

        @Override
        @Nonnull
        public ItemStack removeStackFromSlot(int index) {
            return ItemStackHelper.getAndRemove(this.inventory, index);
        }

        @Override
        public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
            if (isItemValidForSlot(index, stack)) {
                this.inventory.set(index, stack);
            }
        }

        @Override
        public int getInventoryStackLimit() {
            return 64;
        }

        @Override
        public abstract boolean isUsableByPlayer(@Nonnull EntityPlayer player);

        @Override
        public void openInventory(@Nonnull EntityPlayer player) {
        }

        @Override
        public void closeInventory(@Nonnull EntityPlayer player) {
        }

        @Override
        public abstract boolean isItemValidForSlot(int index, @Nonnull ItemStack stack);

        @Override
        public abstract int getField(int id);

        @Override
        public abstract void setField(int id, int value);

        @Override
        public abstract int getFieldCount();

        @Override
        public void clear() {
            this.inventory.clear();
        }

        @Override
        public abstract void tick();

        @Override
        public abstract ITextComponent getName();

        @Override
        public abstract boolean hasCustomName();

        @Nullable
        @Override
        public abstract ITextComponent getDisplayName();

        @Override
        public abstract void read(NBTTagCompound compound);

        @Nonnull
        @Override
        public abstract NBTTagCompound write(NBTTagCompound compound);
    }

    public static abstract class TileEnergyMachine extends TileMachine implements IEnergyStorage {

        private int maxTransfer;
        private int energyStored;
        private int maxEnergyStored;

        public TileEnergyMachine(TileEntityType<?> type,int invSize, int maxEnergyStored, int maxTransfer) {
            super(type, invSize);
            this.maxTransfer = maxTransfer;
            this.maxEnergyStored = maxEnergyStored;
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int stored = Math.min(getEnergyStored(), getMaxEnergyStored());
            int receive = Math.min(maxReceive, Math.min(getMaxEnergyStored() - stored, this.maxTransfer));

            if (!simulate) {
                stored += receive;
                this.energyStored = stored;
            }

            return receive;
        }

        @Override
        public int getEnergyStored() {
            return this.energyStored;
        }

        @Override
        public int getMaxEnergyStored() {
            return this.maxEnergyStored;
        }
    }

    public static abstract class GuiMachine extends GuiContainer {

        private final ResourceLocation textures;
        private final InventoryPlayer player;
        private final TileMachine tileEntity;

        public GuiMachine(String name, InventoryPlayer player, TileMachine tileEntity, ContainerMachine inventorySlotsIn) {
            super(inventorySlotsIn);
            this.player = player;
            this.tileEntity = tileEntity;
            this.textures = new ResourceLocation(Reference.RESOURCE_PREFIX + "textures/gui/" + name + ".png");
        }

        @Override
        protected abstract void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY);

        @Override
        protected abstract void drawGuiContainerForegroundLayer(int mouseX, int mouseY);

    }

    public static abstract class ContainerMachine extends Container {

        private final TileMachine tileEntity;

        public ContainerMachine(InventoryPlayer player, TileMachine tileEntity) {
            this.tileEntity = tileEntity;

            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 9; x++) {
                    this.addSlot(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
                }
            }

            for (int x = 0; x < 9; x++) {
                this.addSlot(new Slot(player, x, 8 + x * 18, 142));
            }
        }

        @Override
        public abstract boolean canInteractWith(EntityPlayer entityPlayer);

        @Override
        public void addListener(IContainerListener listener) {
            super.addListener(listener);
            listener.sendAllWindowProperties(this, this.tileEntity);
        }

        @Override
        public abstract void detectAndSendChanges();

    }

}
