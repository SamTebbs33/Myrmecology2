package com.vivadaylight3.myrmecology.ant;

import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import com.vivadaylight3.myrmecology.init.ModItems;

public class Ants {

    public static final String[] typeNames = { "Larva", "Drone", "Worker",
	    "Queen" };

    public static AntSpecies forest = new AntSpecies(0x020202, 0x333333,
	    "Forest", "Lasius Niger", BiomeGenBase.forest, BiomeGenBase.birchForest), desert = new AntSpecies(0x898000, 0xeada00, "Desert", "Antus Desertus",
	    BiomeGenBase.desert, BiomeGenBase.desertHills),
	    swamp = new AntSpecies(0x210020, 0x4B0049, "Swamp", "Antus Swampus", BiomeGenBase.swampland);

    public enum AntType {
	LARVA(0), DRONE(1), WORKER(2), QUEEN(3);
	public int val;

	AntType(final int val) {
	    this.val = val;
	}
    }

    public static ItemStack getItemStack(final AntSpecies s,
	    final AntType type, final int quantity) {
	return new ItemStack(ModItems.ant, quantity,
		(AntSpecies.species.indexOf(s) * typeNames.length) + type.val);
    }

    public static int getType(final ItemStack stack) {
	return stack.getItemDamage() % typeNames.length;
    }

    public static int getType(final int meta) {
	return meta % typeNames.length;
    }

    public static AntSpecies getSpecies(final ItemStack stack) {
	return AntSpecies.species.get(stack.getItemDamage() / typeNames.length);
    }

    public static ItemStack getBreedingResult(final ItemStack ant1,
	    final ItemStack ant2) {
	return getBreedingResult(getSpecies(ant1), getSpecies(ant2));
    }

    private static ItemStack getBreedingResult(final AntSpecies ant1,
	    final AntSpecies ant2) {
	for (final AntBreedingRecipe recipe : AntBreedingRecipe.globalBreedingRecipes) {
	    if (recipe.match(ant1, ant2)) return getItemStack(
		    recipe.output, AntType.LARVA,
		    Math.min(ant2.fertility, ant1.fertility));
	}
	return null;
    }

}
