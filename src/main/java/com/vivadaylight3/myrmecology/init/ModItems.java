package com.vivadaylight3.myrmecology.init;

import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.item.ItemExtractor;
import com.vivadaylight3.myrmecology.item.ItemMyrmecology;
import com.vivadaylight3.myrmecology.item.ItemMyrmecologyBook;
import com.vivadaylight3.myrmecology.item.ItemMyrmopaedia;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    public static ItemMyrmecology ant = new ItemAnt(),
	    extractor = new ItemExtractor(),
	    myrmopaedia = new ItemMyrmopaedia(), book = new ItemMyrmecologyBook();

    public static void addItem(final ItemMyrmecology item) {
	GameRegistry.registerItem(item, item.name);
    }

    public static void init() {
	addItem(extractor);
	addItem(myrmopaedia);
	addItem(ant);
	addItem(book);
    }

}
