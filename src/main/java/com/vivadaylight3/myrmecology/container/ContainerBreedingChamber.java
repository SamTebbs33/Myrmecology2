package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.tileentity.TileEntityBreedingChamber;

public class ContainerBreedingChamber extends ContainerMyrmecology {

    public ContainerBreedingChamber(final InventoryPlayer inventory,
	    final TileEntityBreedingChamber tile) {
	super(tile);
	addSlotToContainer(new SlotAnt(tile, 0, 27, 17, Ants.AntType.DRONE));
	addSlotToContainer(new SlotAnt(tile, 1, 27, 53, Ants.AntType.QUEEN));
	addSlots(TileEntityBreedingChamber.rows,
		TileEntityBreedingChamber.cols, 2, tile, 62, 17);
	addPlayerSlots(inventory);
    }

    @Override
    protected void addSlots(final int rows, final int cols, final int baseID,
	    final IInventory inv, final int baseX, final int baseY) {
	int c = baseID;
	for (int y = 0; y < rows; y++)
	    for (int x = 0; x < cols; x++) {
		addSlotToContainer(new SlotAnt(inv, c, baseX + x * 18, baseY
			+ y * 18, Ants.AntType.LARVA));
		c++;
	    }
    }

}
