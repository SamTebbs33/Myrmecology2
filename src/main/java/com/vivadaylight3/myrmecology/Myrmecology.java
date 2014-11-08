package com.vivadaylight3.myrmecology;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.init.ModBlocks;
import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.init.ModNet;
import com.vivadaylight3.myrmecology.init.ModRecipes;
import com.vivadaylight3.myrmecology.init.ModTileEntities;
import com.vivadaylight3.myrmecology.proxy.IProxy;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.util.Log;
import com.vivadaylight3.myrmecology.util.Plantable;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class Myrmecology {

    @Instance(Reference.MOD_ID)
    public static Myrmecology instance;

    @SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
    public static IProxy proxy;

    /**
     * GUIs, tile entities, recipes and event handlers
     * 
     * @param event
     */
    @EventHandler
    public void init(final FMLInitializationEvent event) {
	Log.info("Init");
	ModRecipes.init();
	Ants.initRecipes();
    }

    /**
     * Compatibility
     * 
     * @param event
     */
    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
	Log.info("Post init");
    }

    /**
     * Network handling, items and blocks
     * 
     * @param event
     */
    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
	Log.info("Pre init");
	/*
	 * tab = new CreativeTabs(Reference.MOD_ID) {
	 * @Override public Item getTabIconItem() { // TODO Auto-generated
	 * method stub return ModItems.ant; } };
	 */
	ModBlocks.init();
	ModItems.init();
	ModTileEntities.init();
	ModNet.init();
	Plantable.init();
	GameRegistry.registerWorldGenerator(new WorldGenHandler(), 10);
    }

}
