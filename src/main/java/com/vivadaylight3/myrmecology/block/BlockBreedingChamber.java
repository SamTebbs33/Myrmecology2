package com.vivadaylight3.myrmecology.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.tileentity.TileEntityBreedingChamber;

public class BlockBreedingChamber extends BlockMyrmecology {

    public BlockBreedingChamber() {
	super(Material.glass);
	clearTextureSuffixes();
	sidedTextures = true;
	hasTileEntity = true;
	this.setHarvestLevel("pickaxe", 1);
	guiID = Reference.GUI_ID_BREEDINGCHAMBER;
    }

    @Override
    public boolean renderAsNormalBlock() {
	return true;
    }

    @Override
    public TileEntity createTileEntity(final World world, final int metadata) {
	// TODO Auto-generated method stub
	return new TileEntityBreedingChamber();
    }

}
