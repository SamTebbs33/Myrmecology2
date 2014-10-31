package com.vivadaylight3.myrmecology;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vivadaylight3.myrmecology.container.ContainerIncubator;
import com.vivadaylight3.myrmecology.gui.GuiIncubator;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.tileentity.TileEntityIncubator;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		switch (ID) {
		case Reference.GUI_ID_INCUBATOR:
			return new ContainerIncubator(player.inventory,
					(TileEntityIncubator) tileEntity);
		}

		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity tileEntity = world.getTileEntity(x, y, z);

		switch (ID) {
		case Reference.GUI_ID_INCUBATOR:
			return new GuiIncubator(player.inventory,
					(TileEntityIncubator) tileEntity);
		}

		return null;
	}

}
