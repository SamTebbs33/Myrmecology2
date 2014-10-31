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

	public ContainerMyrmecology(TileEntity tile) {
		this.tile = tile;
	}

	protected void addSlots(int rows, int cols, int baseID, IInventory inv,
			int baseX, int baseY) {
		int c = baseID;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				this.addSlotToContainer(new Slot(inv, c, baseX + (x * 18),
						baseY + (y * 18)));
				c++;
			}
		}
	}

	protected void addPlayerSlots(InventoryPlayer inv) {
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
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		ItemStack stack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (slotID < ((IInventory) tile).getSizeInventory()) {
				if (!this.mergeItemStack(slotStack,
						((IInventory) tile).getSizeInventory(),
						this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(slotStack, 0,
					((IInventory) tile).getSizeInventory(), false)) {
				return null;
			}

			if (slotStack.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return stack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		// TODO Auto-generated method stub
		return false;
	}

}
