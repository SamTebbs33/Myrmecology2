package com.vivadaylight3.myrmecology.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.tileentity.TileEntityFormicarium;

public class InventoryUtil {

    public static void addItemStackToInventoryOrDrop(final Coordinate coord,
	    final ItemStack stack, final IInventory inv, final int from,
	    final int to) {
	final int left = addItemStackToInventory(stack, inv, from, to);
	if (left > 0) {
	    final ItemStack s = stack.copy();
	    s.stackSize = left;
	    WorldUtil.spawnItemStack(coord, stack);
	}
    }

    public static boolean inventoryHas(final ItemStack stack,
	    final IInventory inv, final int from, final int to) {
	int left = stack.stackSize;
	for (int slot = from; slot <= to; slot++) {
	    final ItemStack slotStack = inv.getStackInSlot(slot);
	    if (slotStack != null) if (slotStack.getItem() == stack.getItem()
		    && slotStack.getItemDamage() == stack.getItemDamage()) {
		left -= slotStack.stackSize;
		if (left <= 0) return true;
	    }
	}
	return false;
    }

    public static boolean decrStackSize(final ItemStack stack,
	    final IInventory inv, final int from, final int to) {
	for (int slot = from; slot <= to; slot++) {
	    final ItemStack slotStack = inv.getStackInSlot(slot);
	    if (slotStack != null) if (slotStack.getItem() == stack.getItem()
		    && slotStack.getItemDamage() == stack.getItemDamage()) {
		slotStack.stackSize--;
		inv.setInventorySlotContents(slot,
			slotStack.stackSize > 0 ? slotStack : null);
		return true;
	    }
	}
	return false;
    }

    public static boolean inventoryCanHold(final ItemStack item,
	    final IInventory inv) {
	return inventoryCanHold(item, inv, 0, inv.getSizeInventory());

    }

    public static boolean inventoryCanHold(final ItemStack item,
	    final IInventory inv, final int from, final int to) {
	if (item == null) return false;
	int left = item.stackSize;
	for (int k = 0; k < item.stackSize; k++) {
	    if (left < 1) return true;
	    for (int i = from; i <= to; i++) {
		if (left < 1) return true;
		final ItemStack stack = inv.getStackInSlot(i);
		if (stack == null) {

		    if (left <= inv.getInventoryStackLimit()) left -= inv
			    .getInventoryStackLimit();

		} else if (stack.getItem() == item.getItem()
			&& stack.getItemDamage() == item.getItemDamage()) if (stack.stackSize
			+ left <= inv.getInventoryStackLimit()) left -= inv
			.getInventoryStackLimit();
		else left -= inv.getInventoryStackLimit() - stack.stackSize;

	    }

	    if (left < 1) return true;

	}

	return false;

    }

    public static int addItemStackToInventory(final ItemStack item,
	    final IInventory inv, final int from, final int to) {

	if (item == null) return 0;

	if (item.stackSize < 1) return 0;

	int left = item.stackSize;
	final int max = inv.getInventoryStackLimit();

	for (int k = 0; k < item.stackSize; k++) {

	    if (left < 1) return 0;

	    for (int i = from; i <= to; i++) {

		if (left < 1) return 0;

		if (inv.getStackInSlot(i) == null) {

		    if (left <= max) {

			final ItemStack stack = item.copy();
			stack.stackSize = left;
			inv.setInventorySlotContents(i, stack);
			left -= max;

		    } else {

			final ItemStack stack = item.copy();
			stack.stackSize = max;
			inv.setInventorySlotContents(i, stack);
			left -= max;

		    }

		} else if (inv.getStackInSlot(i).getItem() == item.getItem()
			&& inv.getStackInSlot(i).getItemDamage() == item
				.getItemDamage()) if (inv.getStackInSlot(i).stackSize < max) if (inv
			.getStackInSlot(i).stackSize + left <= max) {

		    inv.getStackInSlot(i).stackSize += left;
		    left -= max;

		} else {

		    left -= max - inv.getStackInSlot(i).stackSize;
		    inv.getStackInSlot(i).stackSize = max;

		}

	    }

	}

	return left;

    }

    public static void addItemStackToInventory(final ItemStack stack,
	    final TileEntityFormicarium tile) {
	addItemStackToInventory(stack, tile, 0, tile.getSizeInventory() - 1);

    }

}
