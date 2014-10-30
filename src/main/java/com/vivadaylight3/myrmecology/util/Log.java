package com.vivadaylight3.myrmecology.util;

import org.apache.logging.log4j.Level;

import com.vivadaylight3.myrmecology.reference.Reference;

import cpw.mods.fml.common.FMLLog;

public class Log {

	public static boolean debug = true;

	public static void log(org.apache.logging.log4j.Level level, Object obj) {
		FMLLog.log(Reference.MOD_NAME, level, "", obj);
	}

	public static void debug(Object obj) {
		if (debug)
			log(Level.DEBUG, obj);
	}

	public static void info(Object obj) {
		log(Level.INFO, obj);
	}

	public static void warn(Object obj) {
		log(Level.WARN, obj);
	}

}
