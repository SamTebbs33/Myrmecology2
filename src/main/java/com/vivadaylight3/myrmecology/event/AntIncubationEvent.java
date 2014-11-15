package com.vivadaylight3.myrmecology.event;

import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.tileentity.TileEntityIncubator;

import cpw.mods.fml.common.eventhandler.Event;

public class AntIncubationEvent extends Event {

    public ItemStack larva, output;
    public int timeLeft, timePassed;
    public TileEntityIncubator tileEntity;

    public AntIncubationEvent(final ItemStack larva, final ItemStack output,
	    final TileEntityIncubator tileEntity, final int timeLeft,
	    final int timePassed) {
	super();
	this.larva = larva;
	this.output = output;
	this.tileEntity = tileEntity;
	this.timeLeft = timeLeft;
	this.timePassed = timePassed;
    }

    public static class AntFinishIncubationEvent extends AntIncubationEvent {

	public AntFinishIncubationEvent(final ItemStack larva,
		final ItemStack output, final TileEntityIncubator tileEntity,
		final int timePassed) {
	    super(larva, output, tileEntity, 0, timePassed);
	}

    }

    public static class AntStartIncubationEvent extends AntIncubationEvent {
	public AntStartIncubationEvent(final ItemStack larva,
		final ItemStack output, final TileEntityIncubator tileEntity,
		final int timeLeft) {
	    super(larva, output, tileEntity, timeLeft, 0);
	}
    }

}