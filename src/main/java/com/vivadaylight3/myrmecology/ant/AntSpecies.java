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
    public boolean isHillAnt = true, winged = false, nocturnal = false,
	    hasEntity = false, hasBehaviour = false;
    public BiomeGenBase[] biomes;
    public ArrayList<AntBreedingRecipe> breedingRecipes = new ArrayList<AntBreedingRecipe>();

    public AntSpecies(final int colourOutside, final int colourInside,
	    final String speciesName, final String binomialName,
	    final BiomeGenBase... biomes) {
	colours = new int[] { colourOutside, colourInside };
	this.biomes = biomes;
	this.speciesName = speciesName;
	this.binomialName = binomialName;
	Ants.species.add(this);
    }

    public void doBehaviour(final Coordinate coord, final int strength) {

    }

    public AntSpecies addBreedingRecipe(final AntSpecies drone,
	    final AntSpecies queen) {
	final AntBreedingRecipe recipe = new AntBreedingRecipe(drone, queen,
		this);
	breedingRecipes.add(recipe);
	AntBreedingRecipe.globalBreedingRecipes.add(recipe);
	return this;
    }

    public AntSpecies setMatureTicks(final int matureTicks) {
	this.matureTicks = matureTicks;
	return this;
    }

    public AntSpecies setBreedTicks(final int breedTicks) {
	this.breedTicks = breedTicks;
	return this;
    }

    public AntSpecies setFertility(final int fertility) {
	this.fertility = fertility;
	return this;
    }

    public AntSpecies setFertilityPlus(final int fertilityPlus) {
	this.fertilityPlus = fertilityPlus;
	return this;
    }

    public AntSpecies setFertilityMinus(final int fertilityMinus) {
	this.fertilityMinus = fertilityMinus;
	return this;
    }

    public AntSpecies setHillRarity(final int hillRarity) {
	this.hillRarity = hillRarity;
	return this;
    }

    public AntSpecies setHillAnt(final boolean hillAnt) {
	isHillAnt = hillAnt;
	return this;
    }

    public AntSpecies setWinged(final boolean winged) {
	this.winged = winged;
	return this;
    }

    public AntSpecies setNocturnal(final boolean nocturnal) {
	this.nocturnal = nocturnal;
	return this;
    }

    public AntSpecies setHasEntity(final boolean hasEntity) {
	this.hasEntity = hasEntity;
	return this;
    }

    public AntSpecies setHasBehaviour(final boolean hasBehaviour) {
	this.hasBehaviour = hasBehaviour;
	return this;
    }

}