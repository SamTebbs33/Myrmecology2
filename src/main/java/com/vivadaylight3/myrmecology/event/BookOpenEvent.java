package com.vivadaylight3.myrmecology.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.util.Coordinate;

import cpw.mods.fml.common.eventhandler.Event;

/**
 * Called when a player opens a MyrmecologyBook, before the GUI is opened
 * 
 * @author samueltebbs
 * 
 */
public class BookOpenEvent extends Event {

    public Coordinate coord;
    public EntityPlayer player;
    public ItemStack stack;

    public BookOpenEvent(final Coordinate coord, final EntityPlayer player,
	    final ItemStack stack) {
	super();
	this.coord = coord;
	this.player = player;
	this.stack = stack;
    }

}
