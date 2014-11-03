package com.vivadaylight3.myrmecology.init;

import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.item.ItemExtractor;
import com.vivadaylight3.myrmecology.item.ItemMyrmecology;
import com.vivadaylight3.myrmecology.item.ItemMyrmopaedia;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    public static ItemMyrmecology extractor = new ItemExtractor(), myrmopaedia = new ItemMyrmopaedia(), ant = new ItemAnt();

    public static void init() {
	addItem(extractor);
	addItem(myrmopaedia);
	addItem(ant);
    }

    public static void addItem(final ItemMyrmecology item) {
	GameRegistry.registerItem(item, item.name);
    }

}
