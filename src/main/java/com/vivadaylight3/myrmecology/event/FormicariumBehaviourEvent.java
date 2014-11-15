package com.vivadaylight3.myrmecology.event;

import com.vivadaylight3.myrmecology.ant.AntSpecies;
import com.vivadaylight3.myrmecology.tileentity.TileEntityFormicarium;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

@Cancelable
public class FormicariumBehaviourEvent extends Event {

    public AntSpecies species;
    public TileEntityFormicarium tileEntity;
    public int antQuantity;

    public FormicariumBehaviourEvent(final AntSpecies species,
	    final TileEntityFormicarium tileEntity, final int antQuantity) {
	super();
	this.species = species;
	this.tileEntity = tileEntity;
	this.antQuantity = antQuantity;
    }

}
