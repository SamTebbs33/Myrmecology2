package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.reference.Names;

public class InventoryItem implements IInventory {
    
    public ItemStack[] inventory;
    public ItemStack stack;

    public InventoryItem(ItemStack item, int size) {
	this.inventory = new ItemStack[size];
	this.stack = item;
    }

    public int getSizeInventory() {
	return inventory.length;
    }

    public ItemStack getStackInSlot(int slot) {
	return inventory[slot];
    }

    public ItemStack decrStackSize(int index, int amount) {
	if (inventory[index] != null) {
	    ItemStack itemstack;

	    if (inventory[index].stackSize <= amount) {
		itemstack = inventory[index];
		inventory[index] = null;
		this.onInventoryChanged();
		return itemstack;
	    } else {
		itemstack = inventory[index].splitStack(amount);

		if (inventory[index].stackSize == 0) {
		    inventory[index] = null;
		}
		this.onInventoryChanged();
		return itemstack;
	    }
	} else return null;
    }
    
    private void onInventoryChanged(){
	
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
	return inventory[slot];
    }

    public void setInventorySlotContents(int slot, ItemStack stack) {
	inventory[slot] = stack;

    }

    public String getInventoryName() {
	// TODO Auto-generated method stub
	return StatCollector.translateToLocal(Names.getLocalisedName(ModItems.myrmopaedia));
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

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
	// TODO Auto-generated method stub
	return true;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(int slot, ItemStack stack) {
	return stack.getItem() instanceof ItemAnt;
    }
    
    public void readFromNBT(NBTTagCompound tagcompound) {

	NBTTagList nbttaglist = tagcompound.getTagList("Items", 10);

	for (int i = 0; i < nbttaglist.tagCount(); ++i) {
	    NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
		    .tagAt(i);
	    int b0 = nbttagcompound1.getInteger("Slot");

	    // Just double-checking that the saved slot index is within our
	    // inventory array bounds
	    if (b0 >= 0 && b0 < this.getSizeInventory()) {
		this.setInventorySlotContents(b0,
			ItemStack.loadItemStackFromNBT(nbttagcompound1));
	    }
	}

    }
    
    public void writeToNBT(NBTTagCompound tagcompound) {
	// Create a new NBT Tag List to store itemstacks as NBT Tags
	NBTTagList nbttaglist = new NBTTagList();

	for (int i = 0; i < this.getSizeInventory(); ++i) {
	    // Only write stacks that contain items
	    if (this.getStackInSlot(i) != null) {
		// Make a new NBT Tag Compound to write the itemstack and slot
		// index to
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setInteger("Slot", i);
		// Writes the itemstack in slot(i) to the Tag Compound we just
		// made
		this.getStackInSlot(i).writeToNBT(nbttagcompound1);

		// add the tag compound to our tag list
		nbttaglist.appendTag(nbttagcompound1);
	    }
	}
	// Add the TagList to the ItemStack's Tag Compound with the name
	// "ItemInventory"
	tagcompound.setTag("ItemInventory", nbttaglist);
    }

}
