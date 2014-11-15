package com.vivadaylight3.myrmecology.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.ant.Ants.AntType;
import com.vivadaylight3.myrmecology.block.BlockMyrmecology;
import com.vivadaylight3.myrmecology.block.BlockMyrmecology.BlockSide;
import com.vivadaylight3.myrmecology.event.AntBreedEvent;
import com.vivadaylight3.myrmecology.handler.MEventHandler;
import com.vivadaylight3.myrmecology.init.ModBlocks;
import com.vivadaylight3.myrmecology.item.ItemAnt;
import com.vivadaylight3.myrmecology.reference.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityBreedingChamber extends TileEntityMyrmecology {

    public static int rows = 3, cols = 5, progress = 0, targetTime = -1;
    public static final int SLOT_DRONE = 1;
    public static final int SLOT_QUEEN = 0;

    public TileEntityBreedingChamber() {
	super(2 + rows * cols, 64, Names
		.getLocalisedName(ModBlocks.breedingChamber));
    }

    @Override
    public boolean canExtractItem(final int slot, final ItemStack stack,
	    final int side) {
	return slot > 1;
    }

    @Override
    public boolean canInsertItem(final int slot, final ItemStack stack,
	    final int side) {
	if (slot < 2 && stack.getItem() instanceof ItemAnt) {
	    if (slot == SLOT_QUEEN
		    && Ants.getType(stack) == AntType.QUEEN.metadata) return true;
	    if (slot == SLOT_DRONE
		    && Ants.getType(stack) == AntType.DRONE.metadata) return true;
	}
	return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(final int side) {
	final BlockSide blockSide = BlockMyrmecology.getBlockSide(side,
		worldObj.getBlockMetadata(xCoord, yCoord, zCoord), 0);
	if (blockSide == BlockSide.TOP || blockSide == BlockSide.LEFT) return new int[] {
		SLOT_QUEEN, SLOT_DRONE };
	else {
	    final int[] res = new int[getSizeInventory() - 2];
	    for (int c = 2; c < getSizeInventory(); c++)
		res[c - 2] = c;
	    return res;
	}
    }

    @SideOnly(Side.CLIENT)
    public int getProgressScaled(final int scale) {
	return progress * scale / targetTime;
    }

    @Override
    public void readFromNBT(final NBTTagCompound tag) {
	super.readFromNBT(tag);
	progress = tag.getInteger("Progress");
    }

    private void reset() {
	targetTime = -1;
	progress = 0;
    }

    @Override
    public void updateEntity() {
	final ItemStack drone = inventory[SLOT_DRONE];
	if (drone != null) {
	    final ItemStack queen = inventory[SLOT_QUEEN];
	    if (queen != null) {
		ItemStack product;
		if ((product = Ants.getBreedingResult(queen, drone)) != null) {
		    if (targetTime > -1) {
			if (inventoryCanHold(product)) {
			    progress++;
			    if (progress >= targetTime) {
				addItemStackToInventory(product);
				decrStackSize(SLOT_QUEEN, 1);
				decrStackSize(SLOT_DRONE, 1);
				MEventHandler.post(new AntBreedEvent.AntFinishBreedEvent(
						drone, queen, product, this,
						targetTime));
				reset();
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			    }
			}
		    } else {
			targetTime = Math.max(
				Ants.getSpecies(queen).breedTicks,
				Ants.getSpecies(drone).breedTicks);
			MEventHandler
				.post(new AntBreedEvent.AntStartBreedEvent(
					drone, queen, null, this, targetTime));
		    }
		} else reset();
	    } else reset();
	} else reset();
    }

    @Override
    public void writeToNBT(final NBTTagCompound tag) {
	super.writeToNBT(tag);
	tag.setInteger("Progress", progress);
    }

}
