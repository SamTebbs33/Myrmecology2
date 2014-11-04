package com.vivadaylight3.myrmecology.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import com.vivadaylight3.myrmecology.ant.AntSpecies;
import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.block.BlockMyrmecology;
import com.vivadaylight3.myrmecology.block.BlockMyrmecology.BlockSide;
import com.vivadaylight3.myrmecology.init.ModBlocks;
import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.item.ItemAnt;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityIncubator extends TileEntityMyrmecology {

    public int productType = 1, progress = 0, targetTime = 0, meta = -1;
    public static int rows = 3, columns = 5;

    public TileEntityIncubator() {
	super(1 + (rows * columns), 64, StatCollector
		.translateToLocal(ModBlocks.incubator.getUnlocalizedName()
			+ ".name"));
    }

    private ItemStack getProduct(final ItemStack larva) {
	return new ItemStack(ModItems.ant, 1, (AntSpecies.species.indexOf(Ants
		.getSpecies(larva)) * Ants.typeNames.length) + productType);
    }

    @SideOnly(Side.CLIENT)
    public int getProgressScaled(final int scale) {
	return (progress * scale) / targetTime;
    }

    private void reset() {
	meta = -1;
	progress = 0;
	targetTime = 0;
    }

    @Override
    public void updateEntity() {
	final ItemStack larva = inventory[0];
	if (larva != null) {
	    if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){
	    if (Ants.getType(larva) == Ants.AntType.LARVA.val) {
		if (meta == larva.getItemDamage()) {
		    if (inventoryCanHold(getProduct(larva))) {
			progress++;
			if (progress >= targetTime) {
			    addItemStackToInventory(getProduct(larva));
			    decrStackSize(0, 1);
			    reset();
			}
		    }
		} else {
		    progress = 0;
		    targetTime = Ants.getSpecies(larva).matureTicks;
		    meta = larva.getItemDamage();
		}
	    } else {
		reset();
	    }
	}else{
	    reset();
	}
	} else {
	    reset();
	}
	// Output inventory contents?
    }

    private void updateClientGUI() {
	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public boolean isItemValidForSlot(final int slot, final ItemStack stack) {
	if (stack.getItem() instanceof ItemAnt) {
	    if (slot == 0) return Ants.getType(stack) == Ants.AntType.LARVA.val;
	    else return true;
	} else return false;
    }

    public void updateProductType(final int type) {
	productType = type;
	markDirty();
    }

    @Override
    public void writeToNBT(final NBTTagCompound tag) {
	super.writeToNBT(tag);
	tag.setInteger("ProductType", productType);
	tag.setInteger("Progress", progress);
    }

    @Override
    public void readFromNBT(final NBTTagCompound tag) {
	super.readFromNBT(tag);
	productType = tag.getInteger("ProductType");
	progress = tag.getInteger("Progress");
    }

    @Override
    public int[] getAccessibleSlotsFromSide(final int side) {
	final BlockSide blockSide = BlockMyrmecology.getBlockSide(side,
		worldObj.getBlockMetadata(xCoord, yCoord, zCoord), 0);
	if ((blockSide == BlockSide.TOP) || (blockSide == BlockSide.LEFT)) return new int[] { 0 };
	else {
	    final int[] res = new int[getSizeInventory() - 1];
	    for (int c = 1; c < getSizeInventory(); c++) {
		res[c - 1] = c;
	    }
	    return res;
	}
    }

    @Override
    public boolean canInsertItem(final int slot, final ItemStack stack,
	    final int side) {
	return (slot == 0) && (stack.getItem() instanceof ItemAnt)
		&& (Ants.getType(stack) == Ants.AntType.LARVA.val);
    }

    @Override
    public boolean canExtractItem(final int slot, final ItemStack stack,
	    final int side) {
	return slot > 0;
    }

}
