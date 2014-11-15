package com.vivadaylight3.myrmecology.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class WorldUtil {

    public static List<Entity> getPlayers(final Coordinate coord,
	    final double radius) {
	return getPlayers(coord, radius, radius, radius);
    }

    public static List<Entity> getSpawnedItems(final Coordinate coord,
	    final double radius) {
	return getSpawnedItems(coord, radius, radius, radius);
    }

    public static List<Entity> getHostileMobs(final Coordinate coord,
	    final double radius) {
	return getHostileMobs(coord, radius, radius, radius);
    }

    public static List<Entity> getAnimals(final Coordinate coord,
	    final double radius) {
	return getAnimals(coord, radius, radius, radius);
    }

    public static List<Entity> getPlayers(final Coordinate coord,
	    final double xRadius, final double yRadius, final double zRadius) {
	return getEntities(coord, xRadius, yRadius, zRadius, EntityPlayer.class);
    }

    public static List<Entity> getSpawnedItems(final Coordinate coord,
	    final double xRadius, final double yRadius, final double zRadius) {
	return getEntities(coord, xRadius, yRadius, zRadius, EntityItem.class);
    }

    public static List<Entity> getHostileMobs(final Coordinate coord,
	    final double xRadius, final double yRadius, final double zRadius) {
	return getEntities(coord, xRadius, yRadius, zRadius, EntityMob.class);
    }

    public static List<Entity> getAnimals(final Coordinate coord,
	    final double xRadius, final double yRadius, final double zRadius) {
	return getEntities(coord, xRadius, yRadius, zRadius, EntityAnimal.class);
    }

    public static List<Entity> getEntities(final Coordinate coord,
	    final double xRadius, final double yRadius, final double zRadius,
	    final Class<? extends Entity> cls) {
	return coord.world.getEntitiesWithinAABB(cls, AxisAlignedBB
		.getBoundingBox(coord.x - xRadius, coord.y - yRadius, coord.z
			- zRadius, coord.x + xRadius, coord.y + yRadius,
			coord.z + zRadius));
    }

    public static ArrayList<ItemStack> breakBlockAndGetDrops(
	    final Coordinate coord) {
	final int x = coord.x, y = coord.y, z = coord.z;
	final World world = coord.world;
	final Block block = world.getBlock(x, y, z);
	ArrayList<ItemStack> items = null;
	if (block.getMaterial() == Material.air) return null;
	else {
	    final int l = world.getBlockMetadata(x, y, z);
	    world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block)
		    + (l << 12));
	    items = block.getDrops(world, x, y, z, l, 0);
	    block.breakBlock(world, x, y, z, block, l);
	    world.setBlockToAir(x, y, z);
	    coord.world.markBlockForUpdate(x, y, z);
	    return items;
	}
    }

    public static void breakBlockAndSpawnDrops(final Coordinate coord) {
	final int x2 = coord.x, y2 = coord.y, z2 = coord.z;
	final Block block = coord.world.getBlock(x2, y2, z2);
	final int meta = coord.world.getBlockMetadata(x2, y2, z2);
	block.dropBlockAsItem(coord.world, x2, y2, z2, meta, 0);
	block.breakBlock(coord.world, x2, y2, z2, block, meta);
	coord.world.playAuxSFX(2001, x2, y2, z2, Block.getIdFromBlock(block)
		+ (meta << 12));
	coord.world.setBlockToAir(x2, y2, z2);
	coord.world.markBlockForUpdate(x2, y2, z2);
    }

    public static void spawnItemStack(final Coordinate coord,
	    final ItemStack itemstack) {
	final Random rand = new Random();
	if (itemstack != null) {
	    final float f = rand.nextFloat() * 0.8F + 0.1F;
	    final float f1 = rand.nextFloat() * 0.8F + 0.1F;
	    EntityItem entityitem;

	    for (final float f2 = rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; coord.world
		    .spawnEntityInWorld(entityitem)) {
		int j1 = rand.nextInt(21) + 10;

		if (j1 > itemstack.stackSize) j1 = itemstack.stackSize;

		itemstack.stackSize -= j1;
		entityitem = new EntityItem(coord.world, coord.x + f, coord.y
			+ f1, coord.z + f2, new ItemStack(itemstack.getItem(),
			j1, itemstack.getItemDamage()));
		final float f3 = 0.05F;
		entityitem.motionX = (float) rand.nextGaussian() * f3;
		entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
		entityitem.motionZ = (float) rand.nextGaussian() * f3;

		if (itemstack.hasTagCompound()) entityitem.getEntityItem()
			.setTagCompound(
				(NBTTagCompound) itemstack.getTagCompound()
					.copy());
	    }
	}
    }

    public static Block getBlock(final Coordinate coord) {
	return coord.world.getBlock(coord.x, coord.y, coord.z);
    }

}
