package com.vivadaylight3.myrmecology.init;

import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.item.ItemMyrmecology;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

	public static ItemMyrmecology itemAnt = new ItemAnt();

	public static void init() {
		addItem(itemAnt);
	}

	public static void addItem(ItemMyrmecology item) {
		GameRegistry.registerItem(item, item.name);
	}

}
