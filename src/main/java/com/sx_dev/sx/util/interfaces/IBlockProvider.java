package com.sx_dev.sx.util.interfaces;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public interface IBlockProvider {
    /**
     * Get the block this object represents.
     *
     * @return The block, which may be newly constructed
     */
    Block asBlock();

    /**
     * Shortcut for getting the default state of the block.
     *
     * @return Default block state
     */
    default IBlockState asBlockState() {
        return asBlock().getDefaultState();
    }
}
