package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerMyrmecology extends Container {

    protected TileEntity tile;

    public ContainerMyrmecology(final TileEntity tile) {
	this.tile = tile;
    }

    protected void addSlots(final int rows, final int cols, final int baseID,
	    final IInventory inv, final int baseX, final int baseY) {
	int c = baseID;
	for (int y = 0; y < rows; y++) {
	    for (int x = 0; x < cols; x++) {
		addSlotToContainer(new Slot(inv, c, baseX + (x * 18), baseY
			+ (y * 18)));
		c++;
	    }
	}
    }

    protected void addPlayerSlots(final InventoryPlayer inv) {
	int c = 0;
	for (int x = 0; x < 9; x++) {
	    addSlotToContainer(new Slot(inv, c, 8 + (x * 18), 142));
	    c++;
	}
	for (int y = 0; y < 3; y++) {
	    for (int x = 0; x < 9; x++) {
		addSlotToContainer(new Slot(inv, c, 8 + (x * 18), 84 + (y * 18)));
		c++;
	    }
	}
    }

    @Override
    public ItemStack transferStackInSlot(final EntityPlayer player,
	    final int slotID) {
	ItemStack stack = null;
	final Slot slot = (Slot) inventorySlots.get(slotID);

	if ((slot != null) && slot.getHasStack()) {
	    final ItemStack slotStack = slot.getStack();
	    stack = slotStack.copy();

	    if (slotID < ((IInventory) tile).getSizeInventory()) {
		if (!mergeItemStack(slotStack,
			((IInventory) tile).getSizeInventory(),
			inventorySlots.size(), true)) return null;
	    } else if (!mergeItemStack(slotStack, 0,
		    ((IInventory) tile).getSizeInventory(), false)) return null;

	    if (slotStack.stackSize == 0) {
		slot.putStack((ItemStack) null);
	    } else {
		slot.onSlotChanged();
	    }
	}

	return stack;
    }

    @Override
    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
	return ((IInventory) tile).isUseableByPlayer(p_75145_1_);
    }

}
