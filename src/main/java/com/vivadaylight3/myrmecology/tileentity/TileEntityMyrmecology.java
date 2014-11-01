package com.vivadaylight3.myrmecology.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.vivadaylight3.myrmecology.util.Log;

public class TileEntityMyrmecology extends TileEntity implements
	ISidedInventory {

    protected ItemStack[] inventory;
    protected int maxStackSize;
    protected String name;

    public TileEntityMyrmecology(final int invSize, final int maxStackSize,
	    final String name) {
	inventory = new ItemStack[invSize];
	this.maxStackSize = maxStackSize;
	this.name = name;
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
		return itemstack;
	    } else {
		itemstack = inventory[index].splitStack(amount);

		if (inventory[index].stackSize == 0) {
		    inventory[index] = null;
		}

		return itemstack;
	    }
	} else return null;
    }

    public ItemStack getStackInSlotOnClosing(final int slot) {
	return getStackInSlot(slot);
    }

    public void setInventorySlotContents(final int slot, final ItemStack stack) {
	inventory[slot] = stack;
    }

    public String getInventoryName() {
	return name;
    }

    public boolean hasCustomInventoryName() {
	return true;
    }

    public int getInventoryStackLimit() {
	return maxStackSize;
    }

    public boolean isUseableByPlayer(final EntityPlayer player) {
	Log.debug(player.getDistance(xCoord, yCoord, zCoord) < 6);
	// return player.getDistance(xCoord, yCoord, zCoord) < 6;
	return true;
    }

    @Override
    public void writeToNBT(final NBTTagCompound tag) {
	super.writeToNBT(tag);
	final NBTTagList nbttaglist = new NBTTagList();

	for (int i = 0; i < inventory.length; ++i) {
	    if (inventory[i] != null) {
		final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setByte("Slot", (byte) i);
		inventory[i].writeToNBT(nbttagcompound1);
		nbttaglist.appendTag(nbttagcompound1);
	    }
	}

	tag.setTag("Items", nbttaglist);
    }

    @Override
    public void readFromNBT(final NBTTagCompound tag) {
	super.readFromNBT(tag);
	final NBTTagList nbttaglist = tag.getTagList("Items", 10);
	inventory = new ItemStack[getSizeInventory()];

	for (int i = 0; i < nbttaglist.tagCount(); ++i) {
	    final NBTTagCompound nbttagcompound1 = nbttaglist
		    .getCompoundTagAt(i);
	    final int j = nbttagcompound1.getByte("Slot") & 255;

	    if ((j >= 0) && (j < inventory.length)) {
		inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
	    }
	}
    }

    @Override
    public Packet getDescriptionPacket() {
	final NBTTagCompound tag = new NBTTagCompound();
	writeToNBT(tag);
	return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(final NetworkManager net,
	    final S35PacketUpdateTileEntity packet) {
	readFromNBT(packet.func_148857_g());
	worldObj.func_147479_m(xCoord, yCoord, zCoord);
    }

    public void openInventory() {

    }

    public void closeInventory() {

    }

    public boolean isItemValidForSlot(final int slot, final ItemStack stack) {
	return false;
    }

    public boolean inventoryCanHold(final ItemStack item) {
	if (item == null) return false;
	int left = item.stackSize;
	for (int k = 0; k < item.stackSize; k++) {
	    if (left < 1) return true;
	    for (int i = 0; i < inventory.length; i++) {
		if (left < 1) return true;

		if (inventory[i] == null) {

		    if (left <= getInventoryStackLimit()) {
			left -= getInventoryStackLimit();

		    }

		} else {

		    if ((inventory[i].getItem() == item.getItem())
			    && (inventory[i].getItemDamage() == item
				    .getItemDamage())) {

			if ((inventory[i].stackSize + left) <= getInventoryStackLimit()) {

			    left -= getInventoryStackLimit();

			} else {

			    left -= (getInventoryStackLimit() - inventory[i].stackSize);

			}

		    }

		}

	    }

	    if (left < 1) return true;

	}

	return false;

    }

    public void addItemStackToInventory(final ItemStack item) {

	int left = item.stackSize;
	final int max = getInventoryStackLimit();

	for (int k = 0; k < item.stackSize; k++) {

	    if (left < 1) return;

	    for (int i = 0; i < inventory.length; i++) {

		if (left < 1) return;

		if (inventory[i] == null) {

		    if (left <= max) {

			final ItemStack stack = item.copy();
			stack.stackSize = left;
			inventory[i] = stack;
			left -= max;

		    } else {

			final ItemStack stack = item.copy();
			stack.stackSize = max;
			inventory[i] = stack;
			left -= max;

		    }

		} else {

		    if ((inventory[i].getItem() == item.getItem())
			    && (inventory[i].getItemDamage() == item
				    .getItemDamage())) {

			if (inventory[i].stackSize < max) {

			    if ((inventory[i].stackSize + left) <= max) {

				inventory[i].stackSize += left;
				left -= max;

			    } else {

				left -= (max - inventory[i].stackSize);
				inventory[i].stackSize = max;

			    }

			}

		    }

		}

	    }

	}

	return;

    }

    public int[] getAccessibleSlotsFromSide(final int side) {
	// TODO Auto-generated method stub
	return null;
    }

    public boolean canInsertItem(final int slot, final ItemStack stack,
	    final int side) {
	// TODO Auto-generated method stub
	return false;
    }

    public boolean canExtractItem(final int slot, final ItemStack stack,
	    final int side) {
	// TODO Auto-generated method stub
	return false;
    }

}
