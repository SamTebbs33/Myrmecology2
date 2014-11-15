package com.vivadaylight3.myrmecology.util;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Plantable {

    public Item obj;
    public Block block;
    public int meta;
    public static LinkedList<Plantable> plantables = new LinkedList<Plantable>();

    public Plantable(final Item obj) {
	super();
	plantables.add(this);
	this.obj = obj;
    }

    public Plantable(final Block block) {
	this(Item.getItemFromBlock(block));
	this.block = block;
    }

    public Plantable(final Item item, final Block block) {
	this(item);
	this.block = block;
    }

    public static void init() {
	Log.info("Init Plantable");
	new Plantable(Items.wheat_seeds, Blocks.wheat);
	new Plantable(Items.reeds, Blocks.reeds);
	new Plantable(Items.melon_seeds, Blocks.melon_stem);
	new Plantable(Items.pumpkin_seeds, Blocks.pumpkin_stem);
	new Plantable(Items.nether_wart, Blocks.nether_wart);
	new Plantable(Items.wheat_seeds, Blocks.wheat);
	new Plantable(Items.carrot, Blocks.carrots);
	new Plantable(Items.potato, Blocks.potatoes);
	new Plantable(Items.wheat_seeds, Blocks.wheat);
	for (int c = 0; c < BlockSapling.field_149882_a.length; c++)
	    new Plantable(Blocks.sapling).setMeta(c);
	new Plantable(Blocks.yellow_flower);
	new Plantable(Blocks.red_flower);
	new Plantable(Blocks.red_mushroom);
	new Plantable(Blocks.brown_mushroom);
    }

    public void setMeta(final int meta) {
	this.meta = meta;
    }

    public boolean canBePlanted(final Coordinate coord) {
	return block.canPlaceBlockAt(coord.world, coord.x, coord.y, coord.z);
    }

    public void plant(final Coordinate coord) {
	coord.world.setBlock(coord.x, coord.y, coord.z, block);
	coord.world.setBlockMetadataWithNotify(coord.x, coord.y, coord.z, meta,
		3);
	block.onBlockAdded(coord.world, coord.x, coord.y, coord.z);
    }

    public static Plantable getPlantable(final ItemStack stack) {
	if (stack == null) return null;
	final Item item = stack.getItem();
	for (final Plantable p : plantables)
	    if (p.obj == item && p.meta == stack.getItemDamage()) return p;
	return null;
    }

}
