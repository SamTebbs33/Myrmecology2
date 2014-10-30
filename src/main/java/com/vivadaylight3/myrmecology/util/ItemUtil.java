package com.vivadaylight3.myrmecology.util;

import net.minecraft.item.Item;

public class ItemUtil {

	public static Item get(String name) {
		return (Item) Item.itemRegistry.getObject(name);
	}

}
