package com.vivadaylight3.myrmecology.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.item.ItemAnt;

public class SlotAnt extends Slot {

	Ants.AntType[] types;

	public SlotAnt(IInventory inventory, int ID, int x, int y,
			Ants.AntType... types) {
		super(inventory, ID, x, y);
		this.types = types;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		// TODO Auto-generated method stub
		if (stack.getItem() instanceof ItemAnt) {
			for (Ants.AntType type : types) {
				if (type.val == Ants.getType(stack))
					return true;
			}
		}
		return false;
	}

}
