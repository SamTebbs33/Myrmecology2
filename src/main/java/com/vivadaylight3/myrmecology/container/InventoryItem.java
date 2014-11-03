package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.reference.Names;

public class InventoryItem implements IInventory {

    public ItemStack[] inventory;
    public ItemStack stack;

    public InventoryItem(final ItemStack item, final int size) {
	inventory = new ItemStack[size];
	stack = item;
    }

    public int getSizeInventory() {
	return inventory.length;
    }

    public ItemStack getStackInSlot(final int slot) {
	return inventory[slot];
    }

    public ItemStack decrStackSize(final int index, final int amount) {
	if (inventory[index] != null) {
	    ItemStack itemstack;

	    if (inventory[index].stackSize <= amount) {
		itemstack = inventory[index];
		inventory[index] = null;
		onInventoryChanged();
		return itemstack;
	    } else {
		itemstack = inventory[index].splitStack(amount);

		if (inventory[index].stackSize == 0) {
		    inventory[index] = null;
		}
		onInventoryChanged();
		return itemstack;
	    }
	} else return null;
    }

    private void onInventoryChanged() {

    }

    public ItemStack getStackInSlotOnClosing(final int slot) {
	return inventory[slot];
    }

    public void setInventorySlotContents(final int slot, final ItemStack stack) {
	inventory[slot] = stack;

    }

    public String getInventoryName() {
	// TODO Auto-generated method stub
	return Names.getLocalisedName(ModItems.myrmopaedia);
    }

    public boolean hasCustomInventoryName() {
	// TODO Auto-generated method stub
	return true;
    }

    public int getInventoryStackLimit() {
	// TODO Auto-generated method stub
	return 1;
    }

    public void markDirty() {}

    public boolean isUseableByPlayer(final EntityPlayer p_70300_1_) {
	// TODO Auto-generated method stub
	return true;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(final int slot, final ItemStack stack) {
	return stack.getItem() instanceof ItemAnt;
    }

}
