package com.vivadaylight3.myrmecology.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.vivadaylight3.myrmecology.ant.AntSpecies;
import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAnt extends ItemMyrmecology {

    public static IIcon[][] icons = new IIcon[2][Ants.typeNames.length];
    public static final String[] iconSuffixes = { "", "_Inside" };
    public static final int maxStackSize2 = 64;
    public String[] names = new String[AntSpecies.species.size()
	    * Ants.typeNames.length];

    public ItemAnt() {
	super();
	setMaxStackSize(maxStackSize2);
	setHasSubtypes(true);
	setMaxDamage(0);
	int c = 0;
	for (final AntSpecies s : AntSpecies.species) {
	    final String localSpeciesName = StatCollector
		    .translateToLocal(Reference.MOD_ID + ":antSpecies."
			    + s.speciesName);
	    final String localAntName = StatCollector
		    .translateToLocal(Reference.MOD_ID + ":ant");
	    for (int d = 0; d < Ants.typeNames.length; d++) {
		names[c] = localSpeciesName
			+ localAntName
			+ StatCollector.translateToLocal(Reference.MOD_ID
				+ ":antType." + Ants.typeNames[d]);
		names[c] = names[c].substring(names[c].indexOf('.') + 1).trim();
		c++;
	    }
	}
    }

    @Override
    public String getItemStackDisplayName(final ItemStack stack) {
	return names[stack.getItemDamage()];
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemstack) {
	return "ant" + Ants.getSpecies(itemstack).speciesName
		+ Ants.typeNames[Ants.getType(itemstack)];
    }

    @Override
    public void getSubItems(final Item item, final CreativeTabs tabs,
	    final List list) {
	int c = 0;
	for (final AntSpecies s : AntSpecies.species) {
	    for (int k = 0; k < Ants.typeNames.length; k++) {
		list.add(new ItemStack(item, 1, c));
		c++;
	    }
	}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
	return true;
    }

    @Override
    public void registerIcons(final IIconRegister reg) {
	for (int d = 0; d < 2; d++) {
	    for (int c = 0; c < Ants.typeNames.length; c++) {
		icons[d][c] = reg.registerIcon(Reference.MOD_ID + ":ant"
			+ Ants.typeNames[c] + iconSuffixes[d]);
	    }
	}
    }

    @Override
    public int getColorFromItemStack(final ItemStack par1ItemStack,
	    final int pass) {
	return Ants.getSpecies(par1ItemStack).colours[pass];
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public IIcon getIconFromDamageForRenderPass(final int meta, final int pass) {
	return icons[pass][Ants.getType(meta)];
    }

}
