package com.vivadaylight3.myrmecology.util;

public class Maths {

	public static int cyclicIncrement(int var, int min, int max) {
		if (var >= max)
			return min;
		if (var < min)
			return min;
		else
			return var + 1;
	}

	public static int cyclicDecrement(int var, int min, int max) {
		if (var < min)
			return max;
		if (var > max)
			return max;
		else
			return var - 1;
	}

}
