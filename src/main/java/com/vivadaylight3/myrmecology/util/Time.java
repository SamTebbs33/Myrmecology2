package com.vivadaylight3.myrmecology.util;

public class Time {

	public static int ticks(int minutes) {
		return minutes * 60 * 20;
	}

	public static int ticksFromSeconds(int seconds) {
		return seconds * 20;
	}

}
