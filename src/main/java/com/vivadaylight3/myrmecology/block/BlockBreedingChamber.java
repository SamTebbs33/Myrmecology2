package com.vivadaylight3.myrmecology.block;

import net.minecraft.block.material.Material;

public class BlockBreedingChamber extends BlockMyrmecology {

	public BlockBreedingChamber() {
		super(Material.glass);
		clearTextureSuffixes();
		suffixTop = "_Top";
		suffixBottom = "_Top";
		sidedTextures = true;
		this.setBlockBounds(0.1f, 0.1f, 0.1f, 0.9f, 0.9f, 0.9f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}

}
