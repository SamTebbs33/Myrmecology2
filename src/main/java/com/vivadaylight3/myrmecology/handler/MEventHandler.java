package com.vivadaylight3.myrmecology.handler;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event;

public class MEventHandler {

    public static void post(final Event e) {
	MinecraftForge.EVENT_BUS.post(e);
    }

}
