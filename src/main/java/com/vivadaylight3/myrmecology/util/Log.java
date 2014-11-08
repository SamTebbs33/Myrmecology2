package com.vivadaylight3.myrmecology.util;

import org.apache.logging.log4j.Level;

import com.vivadaylight3.myrmecology.reference.Reference;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;

public class Log {

    public static boolean debug = true;

    public static void debug(final Object obj) {
	if (debug) log(Level.DEBUG, obj);
    }

    public static void info(final Object obj) {
	log(Level.INFO, obj);
    }

    public static void log(final org.apache.logging.log4j.Level level,
	    final Object obj) {
	FMLLog.log(Reference.MOD_NAME, level, obj.toString(), "");
    }

    public static void player(final String str) {
	FMLClientHandler.instance().getClientPlayerEntity()
		.sendChatMessage(str);
    }

    public static void warn(final Object obj) {
	log(Level.WARN, obj);
    }

}
