package com.vivadaylight3.myrmecology.init;

import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.item.ItemExtractor;
import com.vivadaylight3.myrmecology.item.ItemMyrmecology;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    public static ItemMyrmecology ant = new ItemAnt(),
	    extractor = new ItemExtractor();

    public static void init() {
	addItem(ant);
	addItem(extractor);
    }

    public static void addItem(final ItemMyrmecology item) {
	GameRegistry.registerItem(item, item.name);
    }

}