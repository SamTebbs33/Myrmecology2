package com.vivadaylight3.myrmecology.handler;

import net.minecraftforge.common.MinecraftForge;

import com.vivadaylight3.myrmecology.event.AntBreedEvent;
import com.vivadaylight3.myrmecology.event.MyrmopaediaOpenEvent;
import com.vivadaylight3.myrmecology.util.Log;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MEventHandler {
    
    public static void post(Event e){
	MinecraftForge.EVENT_BUS.post(e);
    }

}
