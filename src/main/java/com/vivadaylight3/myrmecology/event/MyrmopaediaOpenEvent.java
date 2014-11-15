package com.vivadaylight3.myrmecology.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.util.Coordinate;

import cpw.mods.fml.common.eventhandler.Event;

/**
 * Called when a player opens a myrmopaedia, before the GUI opens.
 * 
 * @author samueltebbs
 * 
 */
public class MyrmopaediaOpenEvent extends Event {

    public Coordinate coord;
    public EntityPlayer player;
    public ItemStack stack;

    public MyrmopaediaOpenEvent(final Coordinate coord,
	    final EntityPlayer player, final ItemStack stack) {
	super();
	this.coord = coord;
	this.player = player;
	this.stack = stack;
    }

}
