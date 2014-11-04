package com.vivadaylight3.myrmecology.tileentity;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.ant.AntSpecies;
import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.ant.Ants.AntType;
import com.vivadaylight3.myrmecology.block.BlockMyrmecology;
import com.vivadaylight3.myrmecology.block.BlockMyrmecology.BlockSide;
import com.vivadaylight3.myrmecology.init.ModBlocks;
import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.reference.Names;
import com.vivadaylight3.myrmecology.util.Coordinate;

public class TileEntityFormicarium extends TileEntityMyrmecology {

    public static int rowsSymbiosis = 1, colsSymbiosis = 2, rowsWorkers = 2,
	    colsWorkers = 2, rowsProduce = 2, colsProduce = 4;
    public static int[] slotsWorkers = { 2, 3, 4, 5 },
	    slotsSymbiosis = { 0, 1 }, slotsProduce = { 6, 7, 8, 9, 10, 11, 12,
		    13 };
    public static final Item[] itemsSymbiosis = {}; // Length can be max
						    // slotsSymbiosis.length,
						    // will change system later
						    // on when needing more
						    // symbiotic items
    private final int[] symbiosisItemQuantities = new int[slotsSymbiosis.length];
    private final HashMap<AntSpecies, Integer> speciesMap = new HashMap<AntSpecies, Integer>();

    public TileEntityFormicarium() {
	super((rowsProduce * colsProduce) + (rowsSymbiosis * colsSymbiosis)
		+ (rowsWorkers * colsWorkers), 64, Names
		.getLocalisedName(ModBlocks.formicarium));
    }

    @Override
    protected void onInventoryChanged() {
	speciesMap.clear();
	slotsSymbiosis = new int[slotsSymbiosis.length];
	for (final int slot : slotsWorkers) {
	    if (inventory[slot] != null) {
		final AntSpecies species = Ants.getSpecies(inventory[slot]);
		final Integer current = speciesMap.get(species);
		if (current != null) {
		    speciesMap
			    .put(species, current + inventory[slot].stackSize);
		} else {
		    speciesMap.put(species, inventory[slot].stackSize);
		}
	    }
	}
	for (final int slot : slotsSymbiosis) {
	    if (inventory[slot] != null) {
		for (int c = 0; c < itemsSymbiosis.length; c++)
		    if (itemsSymbiosis[c] == inventory[slot].getItem()) {
			symbiosisItemQuantities[c]++;
		    }
	    }
	}
    }

    @Override
    public void updateEntity() {
	final Coordinate coord = getCoordinate();
	for (final AntSpecies species : speciesMap.keySet()) {
	    final int speciesNum = speciesMap.get(species);
	    species.tryFormicariumBehaviour(coord, speciesNum, this);
	    for (int c = 0; c < symbiosisItemQuantities.length; c++)
		if (symbiosisItemQuantities[c] > 0) {
		    species.symbioticProduce(getCoordinate(), this,
			    itemsSymbiosis[c], symbiosisItemQuantities[c],
			    speciesNum);
		}
	}
    }

    public void decreaseSpeciesStack(final AntSpecies species) {
	for (final int slot : slotsWorkers) {
	    if (inventory[slot] != null) {
		if (Ants.getSpecies(inventory[slot]) == species) {
		    decrStackSize(slot, 1);
		    return;
		}
	    }
	}
    }

    @Override
    public int[] getAccessibleSlotsFromSide(final int side) {
	final BlockSide blockSide = BlockMyrmecology.getBlockSide(side,
		worldObj.getBlockMetadata(xCoord, yCoord, zCoord), 0);
	if ((blockSide == BlockSide.TOP) || (blockSide == BlockSide.LEFT)) return slotsWorkers;
	else if (blockSide == BlockSide.BOTTOM) return slotsSymbiosis;
	else return slotsProduce;
    }

    @Override
    public boolean canInsertItem(final int slot, final ItemStack stack,
	    final int side) {
	for (final Integer i : slotsWorkers)
	    if (slot == i) if (stack.getItem() instanceof ItemAnt) if (Ants
		    .getType(stack) == AntType.WORKER.val) return true;
	for (final Integer i : slotsSymbiosis)
	    if (slot == i) {
		for (final Item item : itemsSymbiosis)
		    if (item == stack.getItem()) return true;
	    }
	if (slot > slotsWorkers[slotsWorkers.length - 1]) return true;
	return false;
    }

    @Override
    public boolean canExtractItem(final int slot, final ItemStack stack,
	    final int side) {
	return slot > slotsWorkers[slotsWorkers.length - 1];
    }

}
