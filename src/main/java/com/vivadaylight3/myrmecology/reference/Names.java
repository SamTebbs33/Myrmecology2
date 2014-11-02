package com.vivadaylight3.myrmecology.reference;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;

public class Names {

    public static final String INCUBATOR = "incubator",
	    BREEDINGCHAMBER = "breedingChamber", FORMICARIUM = "formicarium";

    public static String getLocalisedBlockName(final Block block) {
	return StatCollector.translateToLocal(block.getUnlocalizedName()
		+ ".name");
    }

}
