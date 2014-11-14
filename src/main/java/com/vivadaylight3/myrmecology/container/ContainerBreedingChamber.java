package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.ant.Ants.AntType;
import com.vivadaylight3.myrmecology.item.ItemAnt;
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
    
    @Override
    public ItemStack transferStackInSlot(final EntityPlayer player,
	    final int slotID) {
	ItemStack stack = null;
	final Slot slot = (Slot) inventorySlots.get(slotID);

	if (slot != null && slot.getHasStack()) {
	    final ItemStack slotStack = slot.getStack();
	    stack = slotStack.copy();
	    if (slotID < ((IInventory) tile).getSizeInventory()) {
		if (!mergeItemStack(slotStack,
			((IInventory) tile).getSizeInventory(),
			inventorySlots.size(), true)) return null;
	    } else if (!mergeItemStack(slotStack, 0,
		    ((IInventory) tile).getSizeInventory(), false)) return null;

	    if (slotStack.stackSize == 0) slot.putStack((ItemStack) null);
	    else slot.onSlotChanged();
	}

	return stack;
    }

}
