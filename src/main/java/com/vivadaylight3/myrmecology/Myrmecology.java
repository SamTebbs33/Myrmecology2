package com.vivadaylight3.myrmecology;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.init.ModBlocks;
import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.init.ModNet;
import com.vivadaylight3.myrmecology.init.ModTileEntities;
import com.vivadaylight3.myrmecology.proxy.IProxy;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.util.Log;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class Myrmecology {

	@Instance(Reference.MOD_ID)
	public static Myrmecology instance;

	@SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
	public static IProxy proxy;

	public static CreativeTabs tab;

	/**
	 * Network handling, items and blocks
	 * 
	 * @param event
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Log.info("Pre init");
		tab = makeTab(Reference.MOD_ID, Items.stick);
		ModBlocks.init();
		ModItems.init();
		ModTileEntities.init();
		ModNet.init();
	}

	/**
	 * GUIs, tile entities, recipes and event handlers
	 * 
	 * @param event
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Log.info("Init");
	}

	/**
	 * Compatibility
	 * 
	 * @param event
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Log.info("Post init");
	}

	private static CreativeTabs makeTab(String tabName, final Item item) {
		return new CreativeTabs("tab" + Reference.MOD_NAME) {
			@Override
			public ItemStack getIconItemStack() {
				return new ItemStack(item);
			}

			@Override
			public Item getTabIconItem() {
				// TODO Auto-generated method stub
				return item;
			}
		};
	}

}
