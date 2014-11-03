package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.tileentity.TileEntityFormicarium;

public class ContainerFormicarium extends ContainerMyrmecology {

    public ContainerFormicarium(final InventoryPlayer inventory,
	    final TileEntityFormicarium tile) {
	super(tile);
	super.addSlots(TileEntityFormicarium.rowsSymbiosis,
		TileEntityFormicarium.colsSymbiosis, 0, tile, 49, 56);
	addSlots(TileEntityFormicarium.rowsWorkers,
		TileEntityFormicarium.colsWorkers, latestSlotID, tile, 18, 17);
	super.addSlots(TileEntityFormicarium.rowsProduce,
		TileEntityFormicarium.colsProduce, latestSlotID, tile, 80, 17);
	addPlayerSlots(inventory);
    }

    @Override
    protected void addSlots(final int rows, final int cols, final int baseID,
	    final IInventory inv, final int baseX, final int baseY) {
	int c = baseID;
	for (int y = 0; y < rows; y++) {
	    for (int x = 0; x < cols; x++) {
		addSlotToContainer(new SlotAnt(inv, c, baseX + (x * 18), baseY
			+ (y * 18), Ants.AntType.WORKER));
		c++;
	    }
	}
	latestSlotID = c;
    }

}
