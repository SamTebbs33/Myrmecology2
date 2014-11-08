package com.vivadaylight3.myrmecology.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictUtil {

    public static boolean blockIs(final Block block, final String key) {
	for (final ItemStack stack : OreDictionary.getOres(key))
	    if (stack != null) if (stack.getItem() == Item
		    .getItemFromBlock(block)) return true;
	return false;
    }

}
