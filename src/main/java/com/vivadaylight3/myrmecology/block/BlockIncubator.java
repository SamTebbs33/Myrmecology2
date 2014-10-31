package com.vivadaylight3.myrmecology.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vivadaylight3.myrmecology.Myrmecology;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.tileentity.TileEntityIncubator;

public class BlockIncubator extends BlockMyrmecology {

	public BlockIncubator() {
		super(Material.iron);
		sidedTextures = true;
		suffixLeft = "_Side";
		suffixRight = "_Side";
		suffixBack = "_Side";
		hasTileEntity = true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return createTileEntity(p_149915_1_, p_149915_2_);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		// TODO Auto-generated method stub
		return new TileEntityIncubator();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote && !player.isSneaking()) {
			player.openGui(Myrmecology.instance, Reference.GUI_ID_INCUBATOR,
					world, x, y, z);
			return true;
		}
		return false;
	}

}
