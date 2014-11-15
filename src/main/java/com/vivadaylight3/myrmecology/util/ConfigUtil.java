package com.vivadaylight3.myrmecology.util;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigUtil {

    public static Configuration config;

    public static void init(final FMLPreInitializationEvent event) {
	Log.info("Init Config");
	config = new Configuration(event.getSuggestedConfigurationFile());
	Log.info("Found config at " + config.toString());
    }

}
