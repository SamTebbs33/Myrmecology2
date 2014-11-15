package com.vivadaylight3.myrmecology.init;

import com.vivadaylight3.myrmecology.GuiHandler;
import com.vivadaylight3.myrmecology.Myrmecology;
import com.vivadaylight3.myrmecology.net.PacketIncubator;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.util.Log;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class ModNet {

    public static SimpleNetworkWrapper net;

    public static void init() {
	Log.info("Init ModNet");
	NetworkRegistry.INSTANCE.registerGuiHandler(Myrmecology.instance,
		new GuiHandler());
	net = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	net.registerMessage(PacketIncubator.class, PacketIncubator.class, 0,
		Side.SERVER);
    }

}
