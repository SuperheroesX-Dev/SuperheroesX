package com.sx_dev.sx.util.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Structure {

    private IBlockState[][][] area;

    public Structure(IBlockState[][][] area) {
        this.area = area;
    }

    public static Structure getArea(World world, BlockPos pos, int[] size) {
        IBlockState[][][] area = new IBlockState[size[0]][size[1]][size[2]];
        for (int y = 0; y < size[0]; y++) {
            for (int x = 0; x < size[1]; x++) {
                for (int z = 0; z < size[2]; z++) {
                    area[y][x][z] = world.getBlockState(pos.add(x, y, z));
                }
            }
        }
        return new Structure(area);
    }

    public IBlockState get(int x, int y, int z) {
        return area[y][x][z];
    }

    public boolean checkInWorld(World world, BlockPos start, EnumFacing facing) {
        BlockPos p;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                for (int k = 0; k < area[i][j].length; k++) {
                    p = start.offset(facing, j).offset(facing.rotateY().getOpposite(), k).offset(EnumFacing.UP, i);
                    if (area[i][j][k] != null) {
                        if (world.getBlockState(p) != area[i][j][k]) {
                            System.out.println("error " + p.toString() + "\n" + area[i][j][k].toString());
                            return false;
                        }
                    } else if (world.getBlockState(p).getMaterial() != Material.AIR) return false;
                }
            }
        }
        return true;

    }

    public boolean equals(Structure obj) {
        for (int y = 0; y < area.length; y++) {
            for (int x = 0; x < area[y].length; x++) {
                for (int z = 0; z < area[y][x].length; z++) {
                    if (obj.get(x, y, z) != get(x, y, z)) return false;
                }
            }
        }
        return true;
    }

    public int[] size() {
        return new int[]{area.length, area[0].length, area[0][0].length};
    }
}
