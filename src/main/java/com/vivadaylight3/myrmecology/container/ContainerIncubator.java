package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.tileentity.TileEntityIncubator;

public class ContainerIncubator extends ContainerMyrmecology {

    public ContainerIncubator(final InventoryPlayer inventory,
	    final TileEntityIncubator tileEntity) {
	super(tileEntity);
	addSlotToContainer(new SlotAnt(tileEntity, 0, 8, 17, Ants.AntType.LARVA));
	addSlots(TileEntityIncubator.rows, TileEntityIncubator.columns, 1,
		(IInventory) tile, 62, 17);
	addPlayerSlots(inventory);
    }

    @Override
    protected void addSlots(final int rows, final int cols, final int baseID,
	    final IInventory inv, final int baseX, final int baseY) {
	int c = baseID;
	for (int y = 0; y < rows; y++) {
	    for (int x = 0; x < cols; x++) {
		addSlotToContainer(new SlotAnt(inv, c, baseX + (x * 18), baseY
			+ (y * 18), Ants.AntType.DRONE, Ants.AntType.QUEEN,
			Ants.AntType.WORKER));
		c++;
	    }
	}
    }

    @Override
    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
	return ((IInventory) tile).isUseableByPlayer(p_75145_1_);
    }

}
