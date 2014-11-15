package com.vivadaylight3.myrmecology.event;

import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.tileentity.TileEntityBreedingChamber;

import cpw.mods.fml.common.eventhandler.Event;

public class AntBreedEvent extends Event {

    public ItemStack drone, queen, output;
    public int timeLeft, timePassed;
    public TileEntityBreedingChamber tileEntity;

    public AntBreedEvent(final ItemStack drone, final ItemStack queen,
	    final ItemStack output, final TileEntityBreedingChamber tileEntity,
	    final int timeLeft, final int timePassed) {
	super();
	this.drone = drone;
	this.queen = queen;
	this.output = output;
	this.tileEntity = tileEntity;
	this.timeLeft = timeLeft;
	this.timePassed = timePassed;
    }

    public static class AntFinishBreedEvent extends AntBreedEvent {

	public AntFinishBreedEvent(final ItemStack drone,
		final ItemStack queen, final ItemStack output,
		final TileEntityBreedingChamber tileEntity, final int timePassed) {
	    super(drone, queen, output, tileEntity, 0, timePassed);
	}

    }

    public static class AntStartBreedEvent extends AntBreedEvent {
	public AntStartBreedEvent(final ItemStack drone, final ItemStack queen,
		final ItemStack output,
		final TileEntityBreedingChamber tileEntity, final int timeLeft) {
	    super(drone, queen, output, tileEntity, timeLeft, 0);
	}
    }

}