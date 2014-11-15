package com.vivadaylight3.myrmecology.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockMyrmecologyNonContainer extends BlockMyrmecology {

    public BlockMyrmecologyNonContainer(final Material mat) {
	super(mat);
	isBlockContainer = false;
	hasTileEntity = false;
	// TODO Auto-generated constructor stub
    }

    @Override
    public void breakBlock(final World p_149749_1_, final int p_149749_2_,
	    final int p_149749_3_, final int p_149749_4_,
	    final Block p_149749_5_, final int p_149749_6_) {
	super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_,
		p_149749_5_, p_149749_6_);
    }

    @Override
    public boolean onBlockEventReceived(final World p_149696_1_,
	    final int p_149696_2_, final int p_149696_3_,
	    final int p_149696_4_, final int p_149696_5_, final int p_149696_6_) {
	return super.onBlockEventReceived(p_149696_1_, p_149696_2_,
		p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
    }

}
