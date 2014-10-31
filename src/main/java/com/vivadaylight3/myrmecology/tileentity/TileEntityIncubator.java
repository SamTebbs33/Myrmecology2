package com.vivadaylight3.myrmecology.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.init.ModBlocks;
import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.item.ItemAnt;

public class TileEntityIncubator extends TileEntityMyrmecology {

	public int productType = 1, progress = 0, targetTime = 0, meta = -1;
	public static int rows = 3, columns = 5;

	public TileEntityIncubator() {
		super(1 + (rows * columns), 64, StatCollector
				.translateToLocal(ModBlocks.blockIncubator.getUnlocalizedName()
						+ ".name"));
	}

	private ItemStack getProduct(ItemStack larva) {
		return new ItemStack(ModItems.itemAnt, 1, Ants.species.indexOf(Ants
				.getSpecies(larva)) * Ants.typeNames.length + productType);
	}

	private void reset() {
		meta = -1;
		progress = 0;
		targetTime = 0;
	}

	@Override
	public void updateEntity() {
		ItemStack larva = this.inventory[0];
		if (larva != null) {
			if (Ants.getType(larva) == Ants.AntType.LARVA.val) {
				if (meta == larva.getItemDamage()) {
					if (inventoryCanHold(getProduct(larva))) {
						progress++;
						if (progress >= targetTime) {
							System.out.println("Done");
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
		} else {
			reset();
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (stack.getItem() instanceof ItemAnt) {
			if (slot == 0)
				return Ants.getType(stack) == Ants.AntType.LARVA.val;
			else
				return true;
		} else
			return false;
	}

	public void updateProductType(int type) {
		this.productType = type;
		this.markDirty();
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("ProductType", productType);
		tag.setInteger("Progress", progress);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		productType = tag.getInteger("ProductType");
		progress = tag.getInteger("Progress");
	}

}
