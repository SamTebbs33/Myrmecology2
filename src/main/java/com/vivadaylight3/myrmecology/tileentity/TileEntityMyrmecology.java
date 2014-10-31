package com.vivadaylight3.myrmecology.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.vivadaylight3.myrmecology.util.Log;

public class TileEntityMyrmecology extends TileEntity implements IInventory {

	protected ItemStack[] inventory;
	protected int maxStackSize;
	protected String name;

	public TileEntityMyrmecology(int invSize, int maxStackSize, String name) {
		inventory = new ItemStack[invSize];
		this.maxStackSize = maxStackSize;
		this.name = name;
	}

	public int getSizeInventory() {
		return inventory.length;
	}

	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	public ItemStack decrStackSize(int index, int amount) {
		if (this.inventory[index] != null) {
			ItemStack itemstack;

			if (this.inventory[index].stackSize <= amount) {
				itemstack = this.inventory[index];
				this.inventory[index] = null;
				return itemstack;
			} else {
				itemstack = this.inventory[index].splitStack(amount);

				if (this.inventory[index].stackSize == 0) {
					this.inventory[index] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int slot) {
		return getStackInSlot(slot);
	}

	public void setInventorySlotContents(int slot, ItemStack stack) {
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

	public boolean isUseableByPlayer(EntityPlayer player) {
		Log.debug(player.getDistance(xCoord, yCoord, zCoord) < 6);
		// return player.getDistance(xCoord, yCoord, zCoord) < 6;
		return true;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tag.setTag("Items", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.inventory.length) {
				this.inventory[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net,
			S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
		worldObj.func_147479_m(xCoord, yCoord, zCoord);
	}

	public void openInventory() {

	}

	public void closeInventory() {

	}

	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

	public boolean inventoryCanHold(ItemStack item) {
		if (item == null) {
			return false;
		}
		int left = item.stackSize;
		for (int k = 0; k < item.stackSize; k++) {
			if (left < 1) {
				return true;
			}
			for (int i = 0; i < inventory.length; i++) {
				if (left < 1) {

					return true;

				}

				if (inventory[i] == null) {

					if (left <= getInventoryStackLimit()) {
						left -= getInventoryStackLimit();

					}

				} else {

					if (inventory[i].getItem() == item.getItem()
							&& inventory[i].getItemDamage() == item
									.getItemDamage()) {

						if (inventory[i].stackSize + left <= getInventoryStackLimit()) {

							left -= getInventoryStackLimit();

						} else {

							left -= (getInventoryStackLimit() - inventory[i].stackSize);

						}

					}

				}

			}

			if (left < 1) {

				return true;

			}

		}

		return false;

	}

	public void addItemStackToInventory(ItemStack item) {

		int left = item.stackSize;
		int max = getInventoryStackLimit();

		for (int k = 0; k < item.stackSize; k++) {

			if (left < 1) {

				return;

			}

			for (int i = 0; i < inventory.length; i++) {

				if (left < 1) {

					return;

				}

				if (inventory[i] == null) {

					if (left <= max) {

						ItemStack stack = item.copy();
						stack.stackSize = left;
						inventory[i] = stack;
						left -= max;

					} else {

						ItemStack stack = item.copy();
						stack.stackSize = max;
						inventory[i] = stack;
						left -= max;

					}

				} else {

					if (inventory[i].getItem() == item.getItem()
							&& inventory[i].getItemDamage() == item
									.getItemDamage()) {

						if (inventory[i].stackSize < max) {

							if (inventory[i].stackSize + left <= max) {

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

}
