package com.vivadaylight3.myrmecology.init;

import com.vivadaylight3.myrmecology.block.BlockBreedingChamber;
import com.vivadaylight3.myrmecology.block.BlockFormicariumGel;
import com.vivadaylight3.myrmecology.block.BlockIncubator;
import com.vivadaylight3.myrmecology.block.BlockMyrmecology;
import com.vivadaylight3.myrmecology.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

	public static BlockMyrmecology blockBreedingChamber, blockFormicariumGel,
			blockIncubator;

	public static void init() {
		blockBreedingChamber = new BlockBreedingChamber();
		blockFormicariumGel = new BlockFormicariumGel();
		blockIncubator = new BlockIncubator();
		addBlock(blockBreedingChamber);
		addBlock(blockFormicariumGel);
		addBlock(blockIncubator);
	}

	private static void addBlock(BlockMyrmecology block) {
		GameRegistry.registerBlock(block, block.name);
	}

}
