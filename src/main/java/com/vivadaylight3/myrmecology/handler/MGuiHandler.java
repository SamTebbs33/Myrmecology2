package com.vivadaylight3.myrmecology.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vivadaylight3.myrmecology.container.ContainerBreedingChamber;
import com.vivadaylight3.myrmecology.container.ContainerFormicarium;
import com.vivadaylight3.myrmecology.container.ContainerIncubator;
import com.vivadaylight3.myrmecology.container.ContainerMyrmecology;
import com.vivadaylight3.myrmecology.container.ContainerMyrmopaedia;
import com.vivadaylight3.myrmecology.container.InventoryItem;
import com.vivadaylight3.myrmecology.gui.GuiBreedingChamber;
import com.vivadaylight3.myrmecology.gui.GuiFormicarium;
import com.vivadaylight3.myrmecology.gui.GuiIncubator;
import com.vivadaylight3.myrmecology.gui.GuiMyrmecologyBook;
import com.vivadaylight3.myrmecology.gui.GuiMyrmopaedia;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.tileentity.TileEntityBreedingChamber;
import com.vivadaylight3.myrmecology.tileentity.TileEntityFormicarium;
import com.vivadaylight3.myrmecology.tileentity.TileEntityIncubator;

import cpw.mods.fml.common.network.IGuiHandler;

public class MGuiHandler implements IGuiHandler {

    public Object getClientGuiElement(final int ID, final EntityPlayer player,
	    final World world, final int x, final int y, final int z) {

	final TileEntity tileEntity = world.getTileEntity(x, y, z);
	switch (ID) {
	    case Reference.GUI_ID_INCUBATOR:
		return new GuiIncubator(player.inventory,
			(TileEntityIncubator) tileEntity);
	    case Reference.GUI_ID_BREEDINGCHAMBER:
		return new GuiBreedingChamber(player.inventory,
			(TileEntityBreedingChamber) tileEntity);
	    case Reference.GUI_ID_FORMICARIUM:
		return new GuiFormicarium(player.inventory,
			(TileEntityFormicarium) tileEntity);
	    case Reference.GUI_ID_MYRMOPAEDIA:
		return new GuiMyrmopaedia(new ContainerMyrmopaedia(
			new InventoryItem(player.getHeldItem(), 1),
			player.inventory, world));
	    case Reference.GUI_ID_BOOK:
		return new GuiMyrmecologyBook();
	}

	return null;
    }

    public Object getServerGuiElement(final int ID, final EntityPlayer player,
	    final World world, final int x, final int y, final int z) {
	final TileEntity tileEntity = world.getTileEntity(x, y, z);
	switch (ID) {
	    case Reference.GUI_ID_INCUBATOR:
		return new ContainerIncubator(player.inventory,
			(TileEntityIncubator) tileEntity);
	    case Reference.GUI_ID_BREEDINGCHAMBER:
		return new ContainerBreedingChamber(player.inventory,
			(TileEntityBreedingChamber) tileEntity);
	    case Reference.GUI_ID_FORMICARIUM:
		return new ContainerFormicarium(player.inventory,
			(TileEntityFormicarium) tileEntity);
	    case Reference.GUI_ID_MYRMOPAEDIA:
		return new ContainerMyrmopaedia(new InventoryItem(
			player.getHeldItem(), 1), player.inventory, world);
	    case Reference.GUI_ID_BOOK:
		return new ContainerMyrmecology(null) {
		    @Override
		    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
			return true;
		    }
		};
	}

	return null;
    }

}
