package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerMyrmopaedia extends ContainerMyrmecology {

    public ContainerMyrmopaedia(InventoryItem inv, InventoryPlayer player) {
	super(null);
	addPlayerSlots(player);
	addSlotToContainer(new Slot(inv, 0,
		18, 147));
    }
    
    protected void addPlayerSlots(final InventoryPlayer inv) {
	int c = 0;
	for (int x = 0; x < 9; x++) {
	    addSlotToContainer(new Slot(inv, c, 18 + (x * 18), 231));
	    c++;
	}
	for (int y = 0; y < 3; y++) {
	    for (int x = 0; x < 9; x++) {
		addSlotToContainer(new Slot(inv, c, 18 + (x * 18), 169 + (y * 18)));
		c++;
	    }
	}
	latestSlotID = c;
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
	return true;
    }

}
