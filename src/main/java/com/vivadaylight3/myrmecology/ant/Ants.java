package com.vivadaylight3.myrmecology.ant;

import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import com.vivadaylight3.myrmecology.init.ModItems;

public class Ants {

    public static final String[] typeNames = { "Larva", "Drone", "Worker",
	    "Queen" };

    public static AntSpecies forest = new AntSpecies(0x020202, 0x333333,
	    "Forest", "Lasius Niger", BiomeGenBase.forest){
	
    },
	    desert = new AntSpecies(0x898000, 0xeada00, "Desert",
		    "Antus Desertus", BiomeGenBase.desert);

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

    public static ItemStack getBreedingResult(final ItemStack queen,
	    final ItemStack drone) {
	return getBreedingResult(getSpecies(queen), getSpecies(drone));
    }

    private static ItemStack getBreedingResult(final AntSpecies queen,
	    final AntSpecies drone) {
	for (final AntBreedingRecipe recipe : AntBreedingRecipe.globalBreedingRecipes) {
	    if ((recipe.drone == drone) && (recipe.queen == queen)) return getItemStack(
		    recipe.output, AntType.LARVA,
		    Math.min(drone.fertility, queen.fertility));
	}
	return null;
    }

}
