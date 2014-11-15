package com.vivadaylight3.myrmecology.handler;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

import com.vivadaylight3.myrmecology.block.BlockAntHill;
import com.vivadaylight3.myrmecology.util.Coordinate;

import cpw.mods.fml.common.IWorldGenerator;

public class MWorldGenHandler implements IWorldGenerator {

    public MWorldGenHandler() {
	// TODO Auto-generated constructor stub
    }

    public void generate(final Random random, final int chunkX,
	    final int chunkZ, final World world,
	    final IChunkProvider chunkGenerator,
	    final IChunkProvider chunkProvider) {

	switch (world.provider.dimensionId) {
	    case -1:
		// Nether
		break;
	    case 0:
		// Overworld
		final Random rand = new Random();
		final int blockX = chunkX * 16 + rand.nextInt(16);
		final int blockZ = chunkZ * 16 + rand.nextInt(16);
		final int blockY = world.getHeightValue(blockX, blockZ);
		final BiomeGenBase biome = world.getBiomeGenForCoords(blockX,
			blockZ);
		for (final BlockAntHill block : BlockAntHill.hills)
		    if (block.generate(new Coordinate(world, blockX, blockY,
			    blockZ), biome, rand)) break;
		break;
	    case 1:
		// End
		break;
	}

    }

}
