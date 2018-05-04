package mini.SuperheroesX.objects.blocks.machines;

import cofh.redstoneflux.api.IEnergyReceiver;
import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.objects.blocks.BlockBase;
import mini.SuperheroesX.util.Reference;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings({"unused", "WeakerAccess", "NullableProblems"})
public final class MachineBase {

    private MachineBase(){}

    public static abstract class BlockMachine extends BlockBase implements ITileEntityProvider {

        public static final PropertyDirection FACING = BlockHorizontal.FACING;
        public static final PropertyBool ACTIVE = PropertyBool.create("active");
        private final int guiId;

        public BlockMachine(String name, Material material, int guiId) {
            super(name, material);
            this.guiId = guiId;
            setDefaultState(getBlockState().getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
        }

        @Nonnull
        @Override
        public Item getItemDropped(IBlockState state, Random rand, int fortune) {
            return Item.getItemFromBlock(this);
        }

        @Nonnull
        @Override
        public ItemStack getPickBlock(@Nonnull IBlockState state, RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, EntityPlayer player) {
            return new ItemStack(getItemDropped(state, null, 0));
        }

        @Nonnull
        @Override
        public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, EnumHand hand) {
            return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
        }

        @Override
        public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            if (!worldIn.isRemote) {
                playerIn.openGui(SuperheroesX.INSTANCE, this.guiId, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        }

        @Override
        public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        }

        @Override
        public abstract void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state);

        @Override
        public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
            if (!worldIn.isRemote) {
                IBlockState north = worldIn.getBlockState(pos.north());
                IBlockState south = worldIn.getBlockState(pos.south());
                IBlockState west = worldIn.getBlockState(pos.west());
                IBlockState east = worldIn.getBlockState(pos.east());
                EnumFacing face = state.getValue(FACING);

                if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
                else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock())
                    face = EnumFacing.NORTH;
                else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
                else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
                worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
            }
        }

        @Nullable
        @Override
        public abstract TileEntity createNewTileEntity(@Nonnull World world, int i);

        @Nonnull
        @Override
        protected BlockStateContainer createBlockState() {
            return new BlockStateContainer(this, ACTIVE, FACING);
        }

        @Override
        public int getMetaFromState(IBlockState state) {
            return state.getValue(FACING).getIndex();
        }

    }

    public static abstract class TileMachine extends TileEntity implements IInventory, ITickable {

        private NonNullList<ItemStack> inventory;

        public TileMachine(int invSize) {
            super();
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
        public abstract void update();

        @Override
        @Nonnull
        public abstract String getName();

        @Override
        public abstract boolean hasCustomName();

        @Nullable
        @Override
        public abstract ITextComponent getDisplayName();

        @Override
        public abstract void readFromNBT(NBTTagCompound compound);

        @Nonnull
        @Override
        public abstract NBTTagCompound writeToNBT(NBTTagCompound compound);

    }

    public static abstract class TileEnergyMachine extends TileMachine implements IEnergyReceiver {

        private int maxTransfer;
        private int energyStored;
        private int maxEnergyStored;

        public TileEnergyMachine(int invSize, int maxEnergyStored, int maxTransfer) {
            super(invSize);
            this.maxTransfer = maxTransfer;
            this.maxEnergyStored = maxEnergyStored;
        }

        @Override
        public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
            int stored = Math.min(getEnergyStored(from), getMaxEnergyStored(from));
            int receive = Math.min(maxReceive, Math.min(getMaxEnergyStored(from) - stored, this.maxTransfer));

            if (!simulate) {
                stored += receive;
                this.energyStored = stored;
            }

            return receive;
        }

        @Override
        public int getEnergyStored(EnumFacing from) {
            return this.energyStored;
        }

        @Override
        public int getMaxEnergyStored(EnumFacing from) {
            return this.maxEnergyStored;
        }

        @Override
        public abstract boolean canConnectEnergy(EnumFacing from);

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
                    this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
                }
            }

            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
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
