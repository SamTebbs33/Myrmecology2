package com.vivadaylight3.myrmecology.ant;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;

import com.vivadaylight3.myrmecology.util.Coordinate;
import com.vivadaylight3.myrmecology.util.Time;

public class AntSpecies {

	public int[] colours;
	public int matureTicks = Time.ticksFromSeconds(10), breedTicks = Time
			.ticks(1), fertility = 2, fertilityPlus = 1, fertilityMinus = 0,
			hillRarity = 1;
	public String speciesName = "Default", binomialName = "Antus Defaultus";
	public boolean hillAnt = true, winged = false, nocturnal = false,
			hasEntity = false, hasBehaviour = false;
	public BiomeGenBase[] biomes;
	public ArrayList<AntBreedingRecipe> breedingRecipes = new ArrayList<AntBreedingRecipe>();

	public AntSpecies(int colourOutside, int colourInside, String speciesName,
			String binomialName, BiomeGenBase... biomes) {
		colours = new int[] { colourOutside, colourInside };
		this.biomes = biomes;
		this.speciesName = speciesName;
		this.binomialName = binomialName;
		Ants.species.add(this);
	}

	public void doBehaviour(Coordinate coord, int strength) {

	}

	public AntSpecies addBreedingRecipe(AntSpecies drone, AntSpecies queen) {
		AntBreedingRecipe recipe = new AntBreedingRecipe(drone, queen, this);
		this.breedingRecipes.add(recipe);
		AntBreedingRecipe.globalBreedingRecipes.add(recipe);
		return this;
	}

	public AntSpecies setMatureTicks(int matureTicks) {
		this.matureTicks = matureTicks;
		return this;
	}

	public AntSpecies setBreedTicks(int breedTicks) {
		this.breedTicks = breedTicks;
		return this;
	}

	public AntSpecies setFertility(int fertility) {
		this.fertility = fertility;
		return this;
	}

	public AntSpecies setFertilityPlus(int fertilityPlus) {
		this.fertilityPlus = fertilityPlus;
		return this;
	}

	public AntSpecies setFertilityMinus(int fertilityMinus) {
		this.fertilityMinus = fertilityMinus;
		return this;
	}

	public AntSpecies setHillRarity(int hillRarity) {
		this.hillRarity = hillRarity;
		return this;
	}

	public AntSpecies setHillAnt(boolean hillAnt) {
		this.hillAnt = hillAnt;
		return this;
	}

	public AntSpecies setWinged(boolean winged) {
		this.winged = winged;
		return this;
	}

	public AntSpecies setNocturnal(boolean nocturnal) {
		this.nocturnal = nocturnal;
		return this;
	}

	public AntSpecies setHasEntity(boolean hasEntity) {
		this.hasEntity = hasEntity;
		return this;
	}

	public AntSpecies setHasBehaviour(boolean hasBehaviour) {
		this.hasBehaviour = hasBehaviour;
		return this;
	}

}