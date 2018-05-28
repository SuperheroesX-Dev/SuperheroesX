package mini.SuperheroesX.objects.multiblockStructures.NidawellirForge;

import mini.SuperheroesX.init.BlockInit;
import mini.SuperheroesX.objects.blocks.BlockBase;
import mini.SuperheroesX.objects.multiblockStructures.IMultiblock;
import mini.SuperheroesX.util.misc.Structure;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class NidawellirForge extends BlockBase implements ITileEntityProvider, IMultiblock {

    private final Structure structure;

    public NidawellirForge() {
        super("lens_nidawellir", Material.IRON);
        IBlockState tita = BlockInit.BLOCK_TITANIUM.getDefaultState();
        IBlockState star = BlockInit.HEART_OF_A_DIEING_STAR.getDefaultState();
        IBlockState lens = getDefaultState();
        structure = new Structure(new IBlockState[][][]{
                {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null},
                        {null, null, tita, tita, tita, null, null},
                        {null, null, null, tita, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                },
                {
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, tita, null, null, null, tita, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null},
                        {null, null, null, null, null, null, null}
                },
                {
                        {null, null, null, tita, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {tita, null, null, null, null, null, tita},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null}
                },
                {
                        {null, null, null, lens, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {tita, null, null, star, null, null, tita},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null}
                },
                {
                        {null, null, null, tita, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {tita, null, null, null, null, null, tita},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null}
                },
                {
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, tita, null, null, null, tita, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null},
                        {null, null, null, null, null, null, null}
                },
                {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, tita, null, null, null},
                        {null, null, tita, tita, tita, null, null},
                        {null, null, null, tita, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                }
        });
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityNidawellir(world);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (checkMultiblock(worldIn, pos, placer.getHorizontalFacing())) {
            System.out.println("test");
        }
    }

    @Override
    public boolean checkMultiblock(World world, BlockPos pos, EnumFacing facing) {
        BlockPos start = pos.offset(EnumFacing.DOWN, 3).offset(facing.rotateY(), 3);
        return structure.checkInWorld(world, start, facing);
    }
}
