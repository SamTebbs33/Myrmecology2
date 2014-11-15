package com.vivadaylight3.myrmecology.reference;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;

import com.vivadaylight3.myrmecology.item.ItemMyrmecology;

public class Names {

    public static final String INCUBATOR = "incubator",
	    BREEDINGCHAMBER = "breedingChamber", FORMICARIUM = "formicarium",
	    MYRMOPAEDIA = "myrmopaedia", BOOK = "myrmecologyBook";

    public static String getLocalisedName(final Block block) {
	return StatCollector.translateToLocal(block.getUnlocalizedName()
		+ ".name");
    }

    public static String getLocalisedName(final ItemMyrmecology item) {
	return StatCollector.translateToLocal(item.getUnlocalizedName()
		+ ".name");
    }

}
