package com.vivadaylight3.myrmecology.ant;

import java.util.ArrayList;

public class AntBreedingRecipe {

	public AntSpecies drone, queen, output;
	public static ArrayList<AntBreedingRecipe> globalBreedingRecipes = new ArrayList<AntBreedingRecipe>();

	public AntBreedingRecipe(AntSpecies drone, AntSpecies queen,
			AntSpecies output) {
		this.drone = drone;
		this.queen = queen;
		this.output = output;
	}

}
