package com.vivadaylight3.myrmecology.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vivadaylight3.myrmecology.Myrmecology;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.tileentity.TileEntityIncubator;

public class BlockIncubator extends BlockMyrmecology {

    public BlockIncubator() {
	super(Material.iron);
	sidedTextures = true;
	suffixLeft = "_Side";
	suffixRight = "_Side";
	suffixBack = "_Side";
	hasTileEntity = true;
	setHardness(3.5f);
	this.setHarvestLevel("pickaxe", 1);
	guiID = Reference.GUI_ID_INCUBATOR;
    }

    @Override
    public TileEntity createNewTileEntity(final World p_149915_1_,
	    final int p_149915_2_) {
	// TODO Auto-generated method stub
	return createTileEntity(p_149915_1_, p_149915_2_);
    }

    @Override
    public TileEntity createTileEntity(final World world, final int metadata) {
	// TODO Auto-generated method stub
	return new TileEntityIncubator();
    }

}
