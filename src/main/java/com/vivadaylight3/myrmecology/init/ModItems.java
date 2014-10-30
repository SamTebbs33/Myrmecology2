package com.vivadaylight3.myrmecology.init;

import net.minecraft.world.biome.BiomeGenBase;

import com.vivadaylight3.myrmecology.ant.AntSpecies;
import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.item.ItemMyrmecology;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

	public static void init() {
		addItem(new ItemAnt());
	}

	public static void addItem(ItemMyrmecology item) {
		GameRegistry.registerItem(item, item.name);
	}

}
