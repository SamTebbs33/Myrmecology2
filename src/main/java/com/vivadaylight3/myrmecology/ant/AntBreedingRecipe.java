package com.vivadaylight3.myrmecology.ant;

import java.util.ArrayList;

public class AntBreedingRecipe {

    public AntSpecies input1, input2, output;
    public static ArrayList<AntBreedingRecipe> globalBreedingRecipes = new ArrayList<AntBreedingRecipe>();

    public AntBreedingRecipe(final AntSpecies ant1, final AntSpecies ant2,
	    final AntSpecies output) {
	this.input1 = ant1;
	this.input2 = ant2;
	this.output = output;
    }
    
    public boolean match(AntSpecies ant1, AntSpecies ant2){
	return (ant1 == input1 && ant2 == input2) || (ant1 == input2 && ant2 == input1);
    }

}
