package com.vivadaylight3.myrmecology.ant;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;
import akka.japi.Pair;

import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.tileentity.TileEntityFormicarium;
import com.vivadaylight3.myrmecology.util.Coordinate;
import com.vivadaylight3.myrmecology.util.InventoryUtil;
import com.vivadaylight3.myrmecology.util.OreDictUtil;
import com.vivadaylight3.myrmecology.util.Plantable;
import com.vivadaylight3.myrmecology.util.WorldUtil;

public class Ants {

    public enum AntType {
	DRONE(1), LARVA(0), QUEEN(3), WORKER(2);
	public int metadata;

	AntType(final int val) {
	    metadata = val;
	}
    }

    public static DamageSource antDamageSource = new DamageSource("Ants");

    public static AntSpecies forest, desert, swamp, plains, stone, jungle,
	    common, x, barbaric, plentiful, cultivator, mason, carpenter,
	    leafcutter, scavenger, planter, hostile, sprouter, harvester,
	    rancher, slaughterer;

    public static final String[] typeNames = { "Larva", "Drone", "Worker",
	    "Queen" };

    static {
	// Tier 1
	forest = new AntSpecies(0x020202, 0x333333, "Forest", "Lasius Niger",
		BiomeGenBase.forest, BiomeGenBase.birchForest);
	desert = new AntSpecies(0x898000, 0xeada00, "Desert", "Antus Desertus",
		BiomeGenBase.desert, BiomeGenBase.desertHills);
	swamp = new AntSpecies(0x210020, 0x4B0049, "Swamp", "Antus Swampus",
		BiomeGenBase.swampland);
	plains = new AntSpecies(0x162308, 0x406618, "Plains", "Antus Fieldia",
		BiomeGenBase.plains);
	stone = new AntSpecies(0x2B2B2B, 0x636363, "Stone",
		"Formicidae Lapidus");
	jungle = new AntSpecies(0x3D0000, 0x790000, "Jungle", "Formica Rufa",
		BiomeGenBase.jungle);
	common = new AntSpecies(0x3F2A17, 0x684526, "Common", "Antus Communus").setMakesCommonAnt(false).setHillAnt(false);
	
	// Tier 2
	x = new AntSpecies(0x967C5E, 0xC7B299, "Mound",
		"Pogonomyrmex Occidentalis").setMakesCommonAnt(false).addBreedingRecipe(common,
		forest).setHillAnt(false);
	barbaric = new AntSpecies(0x382000, 0x6D3F00, "Barbaric",
		"Eciton Burchelli").setMakesCommonAnt(false).addBreedingRecipe(common, jungle)
		.setHillAnt(false);
	plentiful = new AntSpecies(0x7F5842, 0xF9AD81, "Plentiful",
		"Formica Copiosa").setMakesCommonAnt(false).addBreedingRecipe(common, plains)
		.setHillAnt(false);
	cultivator = new AntSpecies(0x603A60, 0xA864A8, "Cultivator", "").setMakesCommonAnt(false)
		.addBreedingRecipe(common, swamp).setHillAnt(false);
	mason = new AntSpecies(0x33CCFF, 0x666699, "Mason", "Formica Structor").setMakesCommonAnt(false).addBreedingRecipe(common, stone);
	
	// Tier 3
	scavenger = new AntSpecies(0x724539, 0xF69679, "Scavenger",
		"Formicidae Lutum"){

	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final List<Entity> items = WorldUtil.getSpawnedItems(coord, 6);
		for (final Entity item : items) {
		    final ItemStack stack = ((EntityItem) item).getEntityItem();
		    if (InventoryUtil.inventoryCanHold(stack, tile,
			    TileEntityFormicarium.slotsProduceRange.from,
			    TileEntityFormicarium.slotsProduceRange.to)) {
			InventoryUtil.addItemStackToInventory(stack, tile,
				TileEntityFormicarium.slotsProduceRange.from,
				TileEntityFormicarium.slotsProduceRange.to);
			item.setDead();
		    }
		}
	    }

	}.addBreedingRecipe(x, desert)
		.setBehaviourDesc("Collects nearby items")
		.setHasBehaviour(true).setHillAnt(false).setMakesCommonAnt(false);
	
	rancher = new AntSpecies(0x8C52100, 0xF7941D, "Rancher",
		"Formicidae Progenus") {

	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final List<Entity> entities = WorldUtil.getAnimals(coord, 7);
		for (final Entity entity : entities) {
		    final EntityAnimal e = (EntityAnimal) entity;
		    for (int c = TileEntityFormicarium.slotsProduceRange.from; c <= TileEntityFormicarium.slotsProduceRange.to; c++) {
			final ItemStack stack = tile.getStackInSlot(c);
			if (stack != null) if (e.isBreedingItem(stack)) if (!e
				.isInLove() && e.getGrowingAge() == 0) {
			    e.func_146082_f(null);
			    stack.stackSize--;
			    tile.setInventorySlotContents(c, stack);
			}
		    }
		}
	    }

	}.addBreedingRecipe(x, plains).setBehaviourDesc("Breeds animals")
		.setHasBehaviour(true).setHillAnt(false).setMakesCommonAnt(false);
	
	hostile = new AntSpecies(0x381400, 0x7B2E00, "Hostile",
		"Formicidae Infestus") {

	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final List<Entity> entities = WorldUtil
			.getHostileMobs(coord, 5);
		for (final Entity entity : entities)
		    entity.attackEntityFrom(antDamageSource, strength);
	    }

	}.addBreedingRecipe(barbaric, desert)
		.setBehaviourDesc("Damages hostile mobs").setHasBehaviour(true)
		.setHillAnt(false).setMakesCommonAnt(false);
	
	slaughterer = new AntSpecies(0x8C5210, 0xF7941D, "Red",
		"Formicidae Barbica") {

	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final List<Entity> entities = WorldUtil.getAnimals(coord, 7);
		for (final Entity entity : entities) {
		    final EntityAnimal e = (EntityAnimal) entity;
		    if (!e.isInLove() && e.getGrowingAge() == 0) entity
			    .attackEntityFrom(antDamageSource, 10);
		}
	    }

	}.addBreedingRecipe(barbaric, plains)
		.setBehaviourDesc("Slaughters animals").setHasBehaviour(true)
		.setHillAnt(false).setMakesCommonAnt(false);
	
	carpenter = new AntSpecies(0x253810, 0x4B7021, "Carpenter",
		"Camponotus atriceps") {

	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final int radius = 8;
		for (int x = -radius; x <= radius; x++)
		    for (int y = -radius; y <= radius + 10; y++)
			for (int z = -radius; z <= radius; z++) {
			    final int x2 = coord.x + x, y2 = coord.y + y, z2 = coord.z
				    + z;
			    final Block block = coord.world
				    .getBlock(x2, y2, z2);
			    if (block instanceof BlockLog
				    || OreDictUtil.blockIs(block, "logWood")) {
				WorldUtil
					.breakBlockAndSpawnDrops(new Coordinate(
						coord.world, x2, y2, z2));
				return;
			    }
			}
	    }

	}.addBreedingRecipe(plentiful, forest).setHillAnt(false)
		.setBehaviourDesc("Chops down nearby logs")
		.setHasBehaviour(true).setMakesCommonAnt(false);

	leafcutter = new AntSpecies(0x253810, 0x4B7021, "Leafcutter",
		"Camponotus atriceps") {

	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final int radius = 8;
		for (int x = -radius; x <= radius; x++)
		    for (int y = -radius; y <= radius + 10; y++)
			for (int z = -radius; z <= radius; z++) {
			    final int x2 = coord.x + x, y2 = coord.y + y, z2 = coord.z
				    + z;
			    final Block block = coord.world
				    .getBlock(x2, y2, z2);
			    if (block instanceof BlockLeaves
				    || OreDictUtil.blockIs(block, "treeLeaves")) {
				WorldUtil
					.breakBlockAndSpawnDrops(new Coordinate(
						coord.world, x2, y2, z2));
				return;
			    }
			}
	    }

	}.addBreedingRecipe(plentiful, plains).setHillAnt(false)
		.setBehaviourDesc("Cuts down nearby leaves")
		.setHasBehaviour(true).setMakesCommonAnt(false);
	
	harvester = new AntSpecies(0x332F00, 0x827B00, "Harvester",
		"Formicidae Invicta") {

	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final int radius = 7;
		for (int x = coord.x - radius; x <= coord.x + radius; x++)
		    for (int y = coord.y - radius; y <= coord.y + radius; y++)
			for (int z = coord.z - radius; z <= coord.z + radius; z++) {
			    final Block block = coord.world.getBlock(x, y, z);
			    if (block instanceof BlockGrass) continue;
			    if (block instanceof IGrowable) if (!((IGrowable) block)
				    .func_149851_a(coord.world, x, y, z, true)) WorldUtil
				    .breakBlockAndSpawnDrops(new Coordinate(
					    coord.world, x, y, z));
			}
	    }

	}.addBreedingRecipe(plentiful, swamp)
		.setBehaviourDesc("Harvests crops").setHasBehaviour(true)
		.setHillAnt(false).setMakesCommonAnt(false);

	planter = new AntSpecies(0x333333, 0x99CC00, "Planter",
		"Formicidae Sator") {
	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final LinkedList<Pair<ItemStack, Integer>> items = new LinkedList<Pair<ItemStack, Integer>>();
		for (int c = TileEntityFormicarium.slotsProduceRange.from; c <= TileEntityFormicarium.slotsProduceRange.to; c++) {
		    final ItemStack stack = tile.getStackInSlot(c);
		    if (stack != null) if (Plantable.getPlantable(stack) != null) items
			    .add(new Pair(stack, c));
		}
		if (items.size() == 0) return;
		final int radius = 5;
		int slot = 0, invSlot = items.get(0).second();
		ItemStack stack = items.get(0).first();
		Pair<ItemStack, Integer> pair = items.get(0);
		for (int x = coord.x - radius; x <= coord.x + radius; x++)
		    for (int y = coord.y - radius; y <= coord.y + radius; y++)
			for (int z = coord.z - radius; z <= coord.z + radius; z++) {
			    if (stack == null) {
				slot++;
				if (slot >= items.size()) return;
				pair = items.get(slot);
				stack = pair.first();
				invSlot = pair.second();
				continue;
			    }
			    if (stack.stackSize <= 0) {
				tile.setInventorySlotContents(invSlot, null);
				stack = null;
			    } else {
				final Plantable plantable = Plantable
					.getPlantable(stack);
				final Coordinate blockCoord = new Coordinate(
					coord.world, x, y, z);
				if (WorldUtil.getBlock(blockCoord) == Blocks.air) if (plantable
					.canBePlanted(blockCoord)) {
				    plantable.plant(blockCoord);
				    stack.stackSize--;
				}
			    }
			}
		if (invSlot != -1) tile
			.setInventorySlotContents(invSlot, stack);
	    }
	}.addBreedingRecipe(cultivator, plains)
		.setBehaviourDesc("Plants crops and saplings")
		.setHasBehaviour(true).setHillAnt(false).setMakesCommonAnt(false);

	sprouter = new AntSpecies(0x00202D, 0x005B7F, "Sprouter",
		"Formicidae Germino") {

	    @Override
	    public void doFormicariumBehaviour(final Coordinate coord,
		    final int strength, final TileEntityFormicarium tile) {
		final int radius = 7;
		if (InventoryUtil
			.inventoryHas(
				new ItemStack(Items.dye, 1, 15),
				tile,
				tile.slotsProduceRange.from,
				tile.slotsProduceRange.to)) for (int x = coord.x
			- radius; x <= coord.x + radius; x++)
		    for (int y = coord.y - radius; y <= coord.y + radius; y++)
			for (int z = coord.z - radius; z <= coord.z + radius; z++) {
			    final Block block = coord.world.getBlock(x, y, z);
			    if (block instanceof BlockGrass) continue;
			    if (block instanceof IGrowable) if (((IGrowable) block)
				    .func_149851_a(coord.world, x, y, z, true)) {
				((IGrowable) block).func_149853_b(coord.world,
					new Random(), x, y, z);
				InventoryUtil
					.decrStackSize(
						new ItemStack(Items.dye, 1, 15),
						tile,
						tile.slotsProduceRange.from,
						tile.slotsProduceRange.to);
				if (!InventoryUtil
					.inventoryHas(
						new ItemStack(Items.dye, 1, 15),
						tile,
						tile.slotsProduceRange.from,
						tile.slotsProduceRange.to)) return;
			    }
			}
	    }

	}.addBreedingRecipe(cultivator, forest)
		.setBehaviourDesc("Fertilises crops").setHasBehaviour(true)
		.setHillAnt(false).setMakesCommonAnt(false);

    }

    private static ItemStack getBreedingResult(final AntSpecies ant1,
	    final AntSpecies ant2) {
	if(ant1 == ant2) return getItemStack(ant1,
		    AntType.LARVA, ant1.fertility);
	for (final AntBreedingRecipe recipe : AntBreedingRecipe.globalBreedingRecipes)
	    if (recipe.match(ant1, ant2)) return getItemStack(recipe.output,
		    AntType.LARVA, Math.min(ant2.fertility, ant1.fertility));
	return null;
    }

    public static ItemStack getBreedingResult(final ItemStack ant1,
	    final ItemStack ant2) {
	return getBreedingResult(getSpecies(ant1.getItemDamage()), getSpecies(ant2.getItemDamage()));
    }

    public static ItemStack getItemStack(final AntSpecies s,
	    final AntType type, final int quantity) {
	return new ItemStack(ModItems.ant, quantity,
		AntSpecies.species.indexOf(s) * typeNames.length
			+ type.metadata);
    }

    public static AntSpecies getSpecies(final int meta) {
	return AntSpecies.species.get(meta / typeNames.length);
    }
    
    public static AntSpecies getSpecies(ItemStack stack){
	return getSpecies(stack.getItemDamage());
    }

    public static int getType(final int meta) {
	return meta % typeNames.length;
    }

    public static int getType(final ItemStack stack) {
	return getType(stack.getItemDamage());
    }

    // Inefficient, may optimise later on
    public static void initRecipes() {
	for (final AntSpecies s1 : AntSpecies.species)
	    if (s1.makesCommonAnt) for (final AntSpecies s2 : AntSpecies.species)
		if (s2.makesCommonAnt) if (s1 != s2) if (!recipeExists(s1, s2,
			common)) common.addBreedingRecipe(s1, s2);
    }

    public static boolean recipeExists(final AntSpecies s1,
	    final AntSpecies s2, final AntSpecies output) {
	for (final AntBreedingRecipe recipe : AntBreedingRecipe.globalBreedingRecipes)
	    if (recipe.match(s1, s2) && recipe.output == output) return true;
	return false;
    }

}
