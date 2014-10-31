package com.vivadaylight3.myrmecology.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.vivadaylight3.myrmecology.Myrmecology;
import com.vivadaylight3.myrmecology.reference.Reference;
import com.vivadaylight3.myrmecology.tileentity.TileEntityMyrmecology;

public class BlockMyrmecology extends BlockContainer {

	public String name;
	public IIcon iconTop, iconBottom, iconFront, iconBack, iconRight, iconLeft;
	public String suffixTop = "_Top", suffixBottom = "_Bottom",
			suffixFront = "_Front", suffixBack = "_Back",
			suffixRight = "_Right", suffixLeft = "_Left";
	public boolean sidedTextures = false;
	protected boolean hasTileEntity = false;

	public enum EnumBlockSide {
		BOTTOM, TOP, BACK, FRONT, LEFT, RIGHT
	}

	public BlockMyrmecology(Material mat) {
		super(mat);
		this.name = getClassName();
		this.setBlockName(this.name);
		this.setCreativeTab(Myrmecology.tab);
	}

	protected void clearTextureSuffixes() {
		suffixTop = "";
		suffixBottom = "";
		suffixFront = "";
		suffixBack = "";
		suffixLeft = "";
		suffixRight = "";
	}

	private String getClassName() {
		String name = getClass().getName().substring(
				getClass().getName().indexOf("Block") + 5);
		Character ch = name.charAt(0);
		name = Character.toLowerCase(ch) + name.substring(1);
		return name;
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s:%s", Reference.MOD_ID,
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	public String getUnwrappedUnlocalizedName(String unlocalizedName) {
		// TODO Auto-generated method stub
		return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack stack) {
		if (sidedTextures) {
			int angle = MathHelper
					.floor_double((entity.rotationYaw * 4.0f / 360.0f) + 0.5d) & 3;
			// angle = angle < 2 ? angle + 2 : angle - 2;
			world.setBlockMetadataWithNotify(x, y, z, angle, 2);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (sidedTextures) {
			EnumBlockSide bSide = getBlockSide(side, meta, 0);
			switch (bSide) {
			case TOP:
				return iconTop;
			case BOTTOM:
				return iconBottom;
			case FRONT:
				return iconFront;
			case BACK:
				return iconBack;
			case LEFT:
				return iconLeft;
			case RIGHT:
				return iconRight;
			}
		}
		return this.blockIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		String uName = getUnwrappedUnlocalizedName(getUnlocalizedName());
		if (sidedTextures) {
			iconTop = reg.registerIcon(uName + suffixTop);
			iconBottom = reg.registerIcon(uName + suffixBottom);
			iconFront = reg.registerIcon(uName + suffixFront);
			iconBack = reg.registerIcon(uName + suffixBack);
			iconRight = reg.registerIcon(uName + suffixRight);
			iconLeft = reg.registerIcon(uName + suffixLeft);
		} else
			this.blockIcon = reg.registerIcon(uName);
	}

	public static EnumBlockSide getBlockSide(int side, int metadata,
			int basemeta) {

		int meta1 = basemeta;
		int meta2 = basemeta + 1;
		int meta3 = basemeta + 2;
		int meta4 = basemeta + 3;

		if (side == 1)
			return EnumBlockSide.TOP;
		else if (side == 0)
			return EnumBlockSide.BOTTOM;
		else if ((metadata == meta1 && side == 2)
				|| (metadata == meta2 && side == 5)
				|| (metadata == meta3 && side == 3)
				|| (metadata == meta4 && side == 4))
			return EnumBlockSide.FRONT;
		else if ((metadata == meta1 && side == 4)
				|| (metadata == meta2 && side == 2)
				|| (metadata == meta3 && side == 5)
				|| (metadata == meta4 && side == 3))
			return EnumBlockSide.LEFT;
		else if ((metadata == meta1 && side == 5)
				|| (metadata == meta2 && side == 3)
				|| (metadata == meta3 && side == 4)
				|| (metadata == meta4 && side == 2))
			return EnumBlockSide.RIGHT;
		else
			return EnumBlockSide.BACK;

	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			Block par5, int par6) {
		if (this.hasTileEntity) {
			TileEntityMyrmecology tileEntity = (TileEntityMyrmecology) par1World
					.getTileEntity(par2, par3, par4);

			if (tileEntity != null) {
				Random random = new Random();
				for (int j1 = 0; j1 < tileEntity.getSizeInventory(); ++j1) {
					ItemStack itemstack = tileEntity.getStackInSlot(j1);

					if (itemstack != null) {
						float f = random.nextFloat() * 0.8F + 0.1F;
						float f1 = random.nextFloat() * 0.8F + 0.1F;
						EntityItem entityitem;

						for (float f2 = random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World
								.spawnEntityInWorld(entityitem)) {
							int k1 = random.nextInt(21) + 10;

							if (k1 > itemstack.stackSize) {
								k1 = itemstack.stackSize;
							}

							itemstack.stackSize -= k1;
							entityitem = new EntityItem(par1World, par2 + f,
									par3 + f1, par4 + f2, itemstack);
							float f3 = 0.05F;
							entityitem.motionX = (float) random.nextGaussian()
									* f3;
							entityitem.motionY = (float) random.nextGaussian()
									* f3 + 0.2F;
							entityitem.motionZ = (float) random.nextGaussian()
									* f3;

							if (itemstack.hasTagCompound()) {
								entityitem.getEntityItem().setTagCompound(
										(NBTTagCompound) itemstack
												.getTagCompound().copy());
							}
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
	
	public boolean hasTileEntity(int metadata)
    {
        return hasTileEntity;
    }

	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return null;
	}

}
