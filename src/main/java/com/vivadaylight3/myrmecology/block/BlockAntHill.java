package com.vivadaylight3.myrmecology.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import com.vivadaylight3.myrmecology.ant.AntSpecies;
import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.ant.Ants.AntType;
import com.vivadaylight3.myrmecology.item.ItemExtractor;
import com.vivadaylight3.myrmecology.util.Coordinate;
import com.vivadaylight3.myrmecology.util.Maths;

public class BlockAntHill extends BlockMyrmecologyNonContainer {

    public static ArrayList<BlockAntHill> hills = new ArrayList<BlockAntHill>();
    public AntSpecies species;

    public BlockAntHill(final AntSpecies species) {
	super(Material.ground);
	this.species = species;
	sidedTextures = true;
	clearTextureSuffixes();
	suffixTop = "_Top";
	suffixBottom = "_Top";
	name += species.speciesName;
	setBlockName(name);
	BlockAntHill.hills.add(this);
	setHardness(0.5f);
    }

    @Override
    public boolean canHarvestBlock(final EntityPlayer player, final int meta) {

	final ItemStack tool = player.getCurrentEquippedItem();

	if (tool != null) if (tool.getItem() instanceof ItemExtractor) return true;

	return false;
    }

    @Override
    public boolean canSilkHarvest(final World world, final EntityPlayer player,
	    final int x, final int y, final int z, final int metadata) {

	return false;

    }

    public boolean generate(final Coordinate coord, final BiomeGenBase biome,
	    final Random rand) {
	final Block blockUnder = coord.world
		.getBlock(coord.x, coord.y, coord.z);
	if (blockUnder != Blocks.water && blockUnder != Blocks.flowing_water) if (getBiomes() == null
		|| Arrays.asList(getBiomes()).contains(biome)) if (Maths
		.chance(10 + species.hillRarity)) {
	    coord.world.setBlock(coord.x, coord.y, coord.z, this);
	    return true;
	}
	return false;
    }

    public BiomeGenBase[] getBiomes() {
	return species.biomes;
    }

    @Override
    public ArrayList<ItemStack> getDrops(final World world, final int x,
	    final int y, final int z, final int metadata, final int fortune) {
	final ArrayList<ItemStack> res = new ArrayList<ItemStack>();
	res.add(Ants.getItemStack(species, AntType.LARVA,
		2 + (fortune > 0 ? new Random().nextInt(fortune) : 0)));
	return res;
    }

}
