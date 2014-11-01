package com.vivadaylight3.myrmecology.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import scala.actors.threadpool.Arrays;

import com.vivadaylight3.myrmecology.ant.AntSpecies;
import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.util.Coordinate;
import com.vivadaylight3.myrmecology.util.Maths;

public class BlockAntHill extends BlockMyrmecology {

    public AntSpecies species;

    public BlockAntHill(final AntSpecies species) {
	super(Material.ground);
	this.species = species;
	sidedTextures = true;
	clearTextureSuffixes();
	suffixTop = "_Top";
	suffixBottom = "_Top";
	name += species.speciesName;
	setBlockName(name);
	Ants.hills.add(this);
    }

    public BiomeGenBase[] getBiomes() {
	return species.biomes;
    }

    public boolean generate(final Coordinate coord, final BiomeGenBase biome,
	    final Random rand) {
	final Block blockUnder = coord.world
		.getBlock(coord.x, coord.y, coord.z);
	if ((blockUnder != Blocks.water)
		&& (blockUnder != Blocks.flowing_water)) {
	    if ((getBiomes() == null)
		    || Arrays.asList(getBiomes()).contains(biome)) {
		if (Maths.chance(10 + species.hillRarity)) {
		    coord.world.setBlock(coord.x, coord.y, coord.z, this);
		    return true;
		}
	    }
	}
	return false;
    }

}
