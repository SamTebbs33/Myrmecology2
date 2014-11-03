package com.vivadaylight3.myrmecology.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.tileentity.TileEntityFormicarium;

public class BlockFormicarium extends BlockMyrmecology {

    public BlockFormicarium() {
	super(Material.glass);
	hasTileEntity = true;
	this.setHarvestLevel("pickaxe", 1);
	guiID = Reference.GUI_ID_FORMICARIUM;
    }

    @Override
    public TileEntity createTileEntity(final World world, final int metadata) {
	return new TileEntityFormicarium();
    }

}
