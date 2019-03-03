package com.sx_dev.sx.objects.blocks;

import com.sx_dev.sx.init.BlockInit;
import com.sx_dev.sx.tabs.CustomItemGroup;
import com.sx_dev.sx.util.Reference;
import com.sx_dev.sx.util.interfaces.IGeneratableOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

public class OreBase extends Block implements IGeneratableOre {

    private final ItemStack item;
    private final int exp;
    private final OreSpawnInfo oreSpawnInfo;
    private final Item itemBlock;

    /**
     * @param name       The name of the Ore
     * @param material   The material of the Ore
     * @param dimension  The dimension the Ore generates in
     * @param veinSize   The size of the Ore vein
     * @param rarity     The rarity of the Ore
     * @param minHeight  The minimum height the Ore spawns at
     * @param maxHeight  The maximum height the Ore spawns at
     * @param exp        The experience the Ore drops when harvested
     * @param baseBlock  The base of the Ore
     * @param hardness   The block hardness
     * @param resistance The explosion resistance of the Ore
     * @param toolClass  The tool class the Ore can be broken with
     * @param level      The harvest level of the Ore
     * @param item       The Item that drops from the Ore
     */
    public OreBase(String name, Material material, DimensionType dimension, int veinSize, int rarity, int minHeight, int maxHeight, int exp, Block baseBlock, float hardness, float resistance, ToolType toolClass, int level, ItemStack item) {
        super(Properties.create(material).hardnessAndResistance(hardness,resistance));
        setRegistryName(Reference.MODID, name);
        //setHarvestLevel(toolClass, level);
        this.item = item;
        this.exp = exp;

        BlockInit.ORES.add(this);
        this.itemBlock = new ItemBlock(this, new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName());
        oreSpawnInfo = new OreSpawnInfo(veinSize, rarity, dimension, minHeight, maxHeight, baseBlock);
    }

    /**
     * @param name       The name of the Ore
     * @param material   The material of the Ore
     * @param dimension  The dimension the Ore generates in
     * @param veinSize   The size of the Ore vein
     * @param rarity     The rarity of the Ore
     * @param minHeight  The minimum height the Ore spawns at
     * @param maxHeight  The maximum height the Ore spawns at
     * @param exp        The experience the Ore drops when harvested
     * @param hardness   The block hardness
     * @param resistance The explosion resistance of the Ore
     * @param toolClass  The tool class the Ore can be broken with
     * @param level      The harvest level of the Ore
     * @param item       The Item that drops from the Ore
     */
    public OreBase(String name, Material material, DimensionType dimension, int veinSize, int rarity, int minHeight, int maxHeight, int exp, float hardness, float resistance, ToolType toolClass, int level, ItemStack item) {
        super(Properties.create(material).hardnessAndResistance(hardness,resistance));
        setRegistryName(Reference.MODID, name);
        //setHarvestLevel(toolClass, level);
        this.item = item;
        this.exp = exp;

        BlockInit.ORES.add(this);
        this.itemBlock = new ItemBlock(this, new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName());
        oreSpawnInfo = new OreSpawnInfo(veinSize, rarity, dimension, minHeight, maxHeight, dimension == DimensionType.NETHER ? Blocks.NETHERRACK : dimension == DimensionType.THE_END ? Blocks.END_STONE : Blocks.STONE);
    }

    /**
     * @param name       The name of the Ore
     * @param material   The material of the Ore
     * @param dimension  The dimension the Ore generates in
     * @param veinSize   The size of the Ore vein
     * @param rarity     The rarity of the Ore
     * @param minHeight  The minimum height the Ore spawns at
     * @param maxHeight  The maximum height the Ore spawns at
     * @param baseBlock  The base of the Ore
     * @param hardness   The block hardness
     * @param resistance The explosion resistance of the Ore
     * @param toolClass  The tool class the Ore can be broken with
     * @param level      The harvest level of the Ore
     */
    public OreBase(String name, Material material, DimensionType dimension, int veinSize, int rarity, int minHeight, int maxHeight, Block baseBlock, float hardness, float resistance, ToolType toolClass, int level) {
        super(Properties.create(material).hardnessAndResistance(hardness,resistance));
        setRegistryName(Reference.MODID, name);
        //setHarvestLevel(toolClass, level);
        this.item = null;
        this.exp = 0;

        BlockInit.ORES.add(this);
        this.itemBlock = new ItemBlock(this, new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName());
        oreSpawnInfo = new OreSpawnInfo(veinSize, rarity, dimension, minHeight, maxHeight, baseBlock);
    }

    /**
     * @param name       The name of the Ore
     * @param material   The material of the Ore
     * @param info       The {@link IGeneratableOre.OreSpawnInfo OreSpawnInfo} of the Ore
     * @param hardness   The block hardness
     * @param resistance The explosion resistance of the Ore
     * @param toolClass  The tool class the Ore can be broken with
     * @param level      The harvest level of the Ore
     */
    public OreBase(String name, Material material, OreSpawnInfo info, float hardness, float resistance, ToolType toolClass, int level) {
        super(Properties.create(material).hardnessAndResistance(hardness,resistance));
        setRegistryName(Reference.MODID, name);
        //setHarvestLevel(toolClass, level);
        this.item = null;
        this.exp = 0;
        this.oreSpawnInfo = info;

        BlockInit.ORES.add(this);
        this.itemBlock = new ItemBlock(this, new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName());
    }

    /**
     * @param name       The name of the Ore
     * @param material   The material of the Ore
     * @param dimension  The dimension the Ore generates in
     * @param veinSize   The size of the Ore vein
     * @param rarity     The rarity of the Ore
     * @param minHeight  The minimum height the Ore spawns at
     * @param maxHeight  The maximum height the Ore spawns at
     * @param hardness   The block hardness
     * @param resistance The explosion resistance of the Ore
     * @param toolClass  The tool class the Ore can be broken with
     * @param level      The harvest level of the Ore
     */
    public OreBase(String name, Material material, DimensionType dimension, int veinSize, int rarity, int minHeight, int maxHeight, float hardness, float resistance, ToolType toolClass, int level) {
        super(Properties.create(material).hardnessAndResistance(hardness,resistance));
        setRegistryName(Reference.MODID, name);
        //setHarvestLevel(toolClass, level);
        this.item = null;
        this.exp = 0;

        BlockInit.ORES.add(this);
        this.itemBlock = new ItemBlock(this, new Item.Properties().group(CustomItemGroup.Groups.SUPERHEROES_X_TAB_MATERIALS)).setRegistryName(this.getRegistryName());
        oreSpawnInfo = new OreSpawnInfo(veinSize, rarity, dimension, minHeight, maxHeight, dimension == DimensionType.NETHER ? Blocks.NETHERRACK : dimension == DimensionType.THE_END ? Blocks.END_STONE : Blocks.STONE);
    }

    @Override
    public Item asItem() {
        return itemBlock/*this.item != null ? item.getItem() : super.asItem()*/;
    }

    @Override
    @Nonnull
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return super.getSilkTouchDrop(state);
    }

    @Override
    @Nonnull
    public IItemProvider getItemDropped(IBlockState state, World world, BlockPos pos, int fortune) {
        return this.item.getItem();
    }

    @Override
    public int getItemsToDropCount(IBlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
        if (fortune > 0 && super.asItem() != this.getItemDropped(state, worldIn, pos, fortune)) {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0) {
                i = 0;
            }

            return this.quantityDropped(random, worldIn, pos) * (i + 1);
        } else {
            return this.quantityDropped(random, worldIn, pos);
        }
    }

    public int quantityDropped(Random random, World world, BlockPos pos) {
        return (super.asItem() != this.getItemDropped(this.getDefaultState(), world, pos, 0)) ? 1 + random.nextInt(2) : 1;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, IBlockReader world, BlockPos pos, EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        return (stack.getItem().getHarvestLevel(stack, Objects.requireNonNull(this.getHarvestTool(state)), player, state) >= this.getHarvestLevel(state)) && (!stack.isEmpty()) && (ForgeHooks.canHarvestBlock(state, player, world, pos));
    }

    @Override
    public int getExpDrop(IBlockState state, IWorldReader world, BlockPos pos, int fortune) {
        if (this.getItemDropped(state, (World) world, pos, fortune) != super.asItem() && this.item != null) {
            return RANDOM.nextInt(this.exp);
        }
        return 0;
    }

    @Override
    public boolean isToolEffective(IBlockState state, ToolType type) {
        return type != null && type.equals(this.getHarvestTool(state));
    }

    @Override
    public OreSpawnInfo getOreSpawnInfo() {
        return this.oreSpawnInfo;
    }

    @Override
    public Block asBlock() {
        return this;
    }
}
