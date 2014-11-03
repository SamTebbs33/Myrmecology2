package com.vivadaylight3.myrmecology.util;

import java.util.Random;

public class Maths {

    public static int cyclicIncrement(final int var, final int min,
	    final int max) {
	if (var >= max) return min;
	if (var < min) return min;
	else return var + 1;
    }

    public static int cyclicDecrement(final int var, final int min,
	    final int max) {
	if (var < min) return max;
	if (var > max) return max;
	else return var - 1;
    }

    public static boolean chance(final int j) {
	return new Random().nextInt(j == 0 ? 1 : j) == 0;
    }

}
