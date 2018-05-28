package mini.SuperheroesX.objects.multiblockStructures.Bifrost;

import mini.SuperheroesX.objects.blocks.BlockBase;
import mini.SuperheroesX.objects.multiblockStructures.IMultiblock;
import mini.SuperheroesX.objects.tools.SwordHeimdal;
import mini.SuperheroesX.util.misc.Structure;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BifrostCore extends BlockBase implements IMultiblock {

    private final Structure structure;
    private boolean formed;

    public BifrostCore() {
        super("bifrost_core", Material.IRON);

        IBlockState gold = Blocks.GOLD_BLOCK.getDefaultState();
        IBlockState thiz = getDefaultState();
        IBlockState glas = Blocks.STAINED_GLASS.getStateFromMeta(3);

        IBlockState[][] layer1 = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, gold, gold, gold, gold, gold, null, null},
                {null, null, gold, gold, gold, gold, gold, null, null},
                {null, null, gold, gold, gold, gold, gold, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };

        IBlockState[][] layer2 = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, gold, null, null, null, gold, null, null},
                {null, gold, null, null, null, null, null, gold, null},
                {null, gold, null, null, null, null, null, gold, null},
                {null, gold, null, null, null, null, null, gold, null},
                {null, null, gold, null, null, null, gold, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };

        IBlockState[][] layer3a = {
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, gold, glas, glas, glas, gold, null, null},
                {null, gold, glas, glas, glas, glas, glas, gold, null},
                {gold, glas, glas, glas, glas, glas, glas, glas, gold},
                {gold, glas, glas, glas, glas, glas, glas, glas, gold},
                {gold, glas, glas, glas, glas, glas, glas, glas, gold},
                {null, gold, glas, glas, glas, glas, glas, gold, null},
                {null, null, gold, glas, glas, glas, gold, null, null},
                {null, null, null, gold, gold, gold, null, null, null}
        };

        IBlockState[][] layer3 = {
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, gold, null, null, null, gold, null, null},
                {null, gold, null, null, null, null, null, gold, null},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {null, gold, null, null, null, null, null, gold, null},
                {null, null, gold, null, null, null, gold, null, null},
                {null, null, null, gold, gold, gold, null, null, null}
        };

        IBlockState[][] layer4 = {
                {null, null, gold, null, null, null, gold, null, null},
                {null, gold, null, null, null, null, null, gold, null},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, thiz, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {null, gold, null, null, null, null, null, gold, null},
                {null, null, gold, gold, gold, gold, gold, null, null}
        };

        IBlockState[][] layer5 = {
                {null, null, gold, null, null, null, gold, null, null},
                {null, gold, null, null, null, null, null, gold, null},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {gold, null, null, null, null, null, null, null, gold},
                {null, gold, null, null, null, null, null, gold, null},
                {null, null, gold, gold, gold, gold, gold, null, null}
        };

        IBlockState[][] layer6 = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, gold, null, null, null, gold, null, null},
                {null, null, gold, null, null, null, gold, null, null},
                {null, null, gold, null, null, null, gold, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };

        IBlockState[][] layer7 = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, gold, null, null, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, gold, gold, null, gold, gold, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, null, null, gold, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };

        IBlockState[][] layer8 = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, null, gold, null, gold, null, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };

        IBlockState[][] layer9 = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, gold, null, null, null, null},
                {null, null, null, gold, null, gold, null, null, null},
                {null, null, null, null, gold, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };

        IBlockState[][] layer10 = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, gold, null, null, null, null},
                {null, null, null, gold, gold, gold, null, null, null},
                {null, null, null, null, gold, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };

        IBlockState[][] layer11 = {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, gold, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
        };

        structure = new Structure(new IBlockState[][][]{
                layer1,
                layer2,
                layer3a,
                layer4,
                layer5,
                layer5,
                layer3,
                layer2,
                layer6,
                layer7,
                layer8,
                layer9,
                layer9,
                layer10,
                layer11,
                layer11,
                layer11
        });
    }

    @Override
    public boolean checkMultiblock(World world, BlockPos pos, EnumFacing facing) {
        return structure.checkInWorld(world, pos.offset(EnumFacing.DOWN, 3).offset(facing.rotateY(), 4).offset(facing.getOpposite(), 4), facing);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!formed) {
            if (checkMultiblock(worldIn, pos, facing)) {
                formed = true;
                formMultiblock();
            }
        } else {
            if (playerIn.getHeldItem(hand).getItem() instanceof SwordHeimdal) {
                activateBifrost(playerIn);
            }
        }
        return false;
    }

    private void activateBifrost(EntityPlayer playerIn) {

    }

    private void formMultiblock() {

    }
}
