package com.vivadaylight3.myrmecology.block;

import net.minecraft.block.material.Material;

public class BlockIncubator extends BlockMyrmecology {

	public BlockIncubator() {
		super(Material.iron);
		sidedTextures = true;
		suffixLeft = "_Side";
		suffixRight = "_Side";
		suffixBack = "_Side";
	}

}
