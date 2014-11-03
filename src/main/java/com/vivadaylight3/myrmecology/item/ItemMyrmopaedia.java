package com.vivadaylight3.myrmecology.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.vivadaylight3.myrmecology.Myrmecology;
import com.vivadaylight3.myrmecology.reference.Reference;

public class ItemMyrmopaedia extends ItemMyrmecology {

    public ItemMyrmopaedia() {
	super();
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
	    EntityPlayer par3EntityPlayer) {

	this.onItemUse(par1ItemStack, par3EntityPlayer, par2World,
		(int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY,
		(int) par3EntityPlayer.posZ, 0, 0f, 0f, 0f);
	return par1ItemStack;
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player,
	    World world, int x, int y, int z, int par7, float par8, float par9,
	    float par10) {

	if (!world.isRemote && !player.isSneaking()) {
	    player.openGui(Myrmecology.instance, Reference.GUI_ID_MYRMOPAEDIA,
		world, x, y, z);
	}

	return true;

    }

}
