package com.vivadaylight3.myrmecology.util;

import net.minecraft.world.World;

public class Coordinate {

	public World world;
	public int x, y, z;

	public Coordinate(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
