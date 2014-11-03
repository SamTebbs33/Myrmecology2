package com.vivadaylight3.myrmecology.util;

public class Time {

    public static int ticksFromMinutes(final int minutes) {
	return ticksFromSeconds(minutes * 60);
    }

    public static int ticksFromSeconds(final int seconds) {
	return seconds * 20;
    }

    public static int secondsFromTicks(final int ticks) {
	return ticks / 20;
    }

}
