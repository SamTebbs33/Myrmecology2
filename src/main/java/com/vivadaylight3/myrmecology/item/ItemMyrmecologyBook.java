package com.vivadaylight3.myrmecology.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.vivadaylight3.myrmecology.Myrmecology;
import com.vivadaylight3.myrmecology.event.BookOpenEvent;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.util.Coordinate;

public class ItemMyrmecologyBook extends ItemMyrmecology {

    public ItemMyrmecologyBook() {
	super();
    }

    @Override
    public ItemStack onItemRightClick(final ItemStack par1ItemStack,
	    final World par2World, final EntityPlayer par3EntityPlayer) {

	onItemUse(par1ItemStack, par3EntityPlayer, par2World,
		(int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY,
		(int) par3EntityPlayer.posZ, 0, 0f, 0f, 0f);
	return par1ItemStack;
    }

    @Override
    public boolean onItemUse(final ItemStack stack, final EntityPlayer player,
	    final World world, final int x, final int y, final int z,
	    final int par7, final float par8, final float par9,
	    final float par10) {

	if (!world.isRemote && !player.isSneaking()) {
	    MinecraftForge.EVENT_BUS.post(new BookOpenEvent(new Coordinate(
		    world, x, y, z), player, stack));
	    player.openGui(Myrmecology.instance, Reference.GUI_ID_BOOK, world,
		    x, y, z);
	}

	return true;

    }

}
