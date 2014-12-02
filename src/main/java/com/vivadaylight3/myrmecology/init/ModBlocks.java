package com.vivadaylight3.myrmecology.init;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.block.BlockAntHill;
import com.vivadaylight3.myrmecology.block.BlockBreedingChamber;
import com.vivadaylight3.myrmecology.block.BlockFormicarium;
import com.vivadaylight3.myrmecology.block.BlockIncubator;
import com.vivadaylight3.myrmecology.block.BlockMyrmecology;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.util.Coordinate;
import com.vivadaylight3.myrmecology.util.Log;
import com.vivadaylight3.myrmecology.util.Maths;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static BlockMyrmecology breedingChamber, incubator, formicarium;
    public static BlockAntHill hillForest, hillDesert, hillJungle, hillPlains, hillStone, hillSwamp;

    private static void addBlock(final BlockMyrmecology block) {
	GameRegistry.registerBlock(block, block.name);
    }

    public static void init() {
	Log.info("Init ModBlocks");
	breedingChamber = new BlockBreedingChamber();
	incubator = new BlockIncubator();
	formicarium = new BlockFormicarium();
	
	hillForest = new BlockAntHill(Ants.forest);
	hillDesert = (BlockAntHill) new BlockAntHill(Ants.desert)
		.clearTextureSuffixes();
	hillJungle = new BlockAntHill(Ants.jungle);
	hillPlains = (BlockAntHill) new BlockAntHill(Ants.plains).clearTextureSuffixes();
	hillStone = (BlockAntHill) new BlockAntHill(Ants.stone){

	    @Override
	    public boolean generate(Coordinate coord, BiomeGenBase biome,
		    Random rand) {
		int y = rand.nextInt(64) + 1;
		new WorldGenMinable(this, 1).generate(coord.world, rand, coord.x, y, coord.z);
		return true;
	    }
	    
	}.clearTextureSuffixes();
	
	hillStone.name = "antHillStone";
	
	hillSwamp = (BlockAntHill) new BlockAntHill(Ants.swamp).clearTextureSuffixes();

	addBlock(breedingChamber);
	addBlock(incubator);
	addBlock(formicarium);
	
	addBlock(hillForest);
	addBlock(hillDesert);
	addBlock(hillJungle);
	addBlock(hillPlains);
	addBlock(hillStone);
	addBlock(hillSwamp);
    }

}
