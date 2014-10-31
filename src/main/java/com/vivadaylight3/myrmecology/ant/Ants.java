package com.vivadaylight3.myrmecology.ant;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

public class Ants {

	public static ArrayList<AntSpecies> species = new ArrayList<AntSpecies>();
	public static final String[] typeNames = { "Larva", "Drone", "Worker",
			"Queen" };

	public static AntSpecies forest = new AntSpecies(0x020202, 0x333333,
			"Forest", "Lasius Niger", BiomeGenBase.forest),
			desert = new AntSpecies(0x898000, 0xeada00, "Desert",
					"Antus Desertus", BiomeGenBase.desert);

	public enum AntType {
		LARVA(0), DRONE(1), WORKER(2), QUEEN(3);
		public int val;

		AntType(int val) {
			this.val = val;
		}
	}

	public static int getType(ItemStack stack) {
		return stack.getItemDamage() % typeNames.length;
	}

	public static int getType(int meta) {
		return meta % typeNames.length;
	}

	public static AntSpecies getSpecies(ItemStack stack) {
		return species.get(stack.getItemDamage() / typeNames.length);
	}

}
