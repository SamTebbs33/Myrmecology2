package com.vivadaylight3.myrmecology.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMyrmopaedia extends ContainerMyrmecology {

    public InventoryItem inventory;
    private final World world;

    public ContainerMyrmopaedia(final InventoryItem inv,
	    final InventoryPlayer player, final World world) {
	super(null);
	this.world = world;
	inventory = inv;
	addPlayerSlots(player);
	addSlotToContainer(new Slot(inv, 0, 18, 147));
    }

    @Override
    protected void addPlayerSlots(final InventoryPlayer inv) {
	int c = 0;
	for (int x = 0; x < 9; x++) {
	    addSlotToContainer(new Slot(inv, c, 18 + (x * 18), 231));
	    c++;
	}
	for (int y = 0; y < 3; y++) {
	    for (int x = 0; x < 9; x++) {
		addSlotToContainer(new Slot(inv, c, 18 + (x * 18),
			169 + (y * 18)));
		c++;
	    }
	}
	latestSlotID = c;
    }

    @Override
    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
	return true;
    }

    @Override
    public void onContainerClosed(final EntityPlayer player) {
	super.onContainerClosed(player);

	if (!world.isRemote) {
	    for (int i = 0; i < inventory.getSizeInventory(); i++) {
		final ItemStack itemstack = inventory
			.getStackInSlotOnClosing(i);

		if (itemstack != null) {
		    player.dropPlayerItemWithRandomChoice(itemstack, false);
		}
	    }
	}
    }

}
