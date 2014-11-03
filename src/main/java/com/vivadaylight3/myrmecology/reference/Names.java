package com.vivadaylight3.myrmecology.reference;

import com.vivadaylight3.myrmecology.item.ItemMyrmecology;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;

public class Names {

    public static final String INCUBATOR = "incubator",
	    BREEDINGCHAMBER = "breedingChamber", FORMICARIUM = "formicarium", MYRMOPAEDIA = "myrmopaedia";

    public static String getLocalisedName(final Block block) {
	return StatCollector.translateToLocal(block.getUnlocalizedName()
		+ ".name");
    }

    public static String getLocalisedName(ItemMyrmecology item) {
	return StatCollector.translateToLocal(item.getUnlocalizedName()
		+ ".name");
    }

}
