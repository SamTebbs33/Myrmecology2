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
import com.vivadaylight3.myrmecology.init.ModTabs;
import com.vivadaylight3.myrmecology.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAnt extends ItemMyrmecology {

    public static IIcon[][] icons = new IIcon[2][Ants.typeNames.length];
    public static final String[] iconSuffixes = { "", "_Inside" };
    public static final int maxStackSize2 = 64;
    public String[] names = new String[AntSpecies.species.size()
	    * Ants.typeNames.length];
    public AntSpecies species;

    public ItemAnt(final AntSpecies species) {
	super();
	this.species = species;
	Ants.antMap.put(species, this);
	setCreativeTab(ModTabs.ants);
	setMaxStackSize(maxStackSize2);
	setHasSubtypes(true);
	setMaxDamage(0);
	final String localSpeciesName = species.getLocalSpeciesName();
	final String localAntName = StatCollector.translateToLocal("ant."
		+ Reference.MOD_ID);
	for (int d = 0; d < Ants.typeNames.length; d++) {
	    names[d] = localSpeciesName
		    + localAntName
		    + StatCollector.translateToLocal("ant." + Reference.MOD_ID
			    + ":" + Ants.typeNames[d]);
	    names[d] = names[d].substring(names[d].indexOf('.') + 1).trim();
	}
    }

    @Override
    public int getColorFromItemStack(final ItemStack par1ItemStack,
	    final int pass) {
	return species.colours[pass];
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public IIcon getIconFromDamageForRenderPass(final int meta, final int pass) {
	return icons[pass][meta];
    }

    @Override
    public String getItemStackDisplayName(final ItemStack stack) {
	return names[stack.getItemDamage()];
    }

    @Override
    public void getSubItems(final Item item, final CreativeTabs tabs,
	    final List list) {
	// for (final AntSpecies s : AntSpecies.species)
	for (int k = 0; k < Ants.typeNames.length; k++)
	    list.add(new ItemStack(item, 1, k));
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemstack) {
	return "ant" + species.speciesName
		+ Ants.typeNames[itemstack.getItemDamage()];
    }

    @Override
    public void registerIcons(final IIconRegister reg) {
	for (int d = 0; d < 2; d++)
	    for (int c = 0; c < Ants.typeNames.length; c++)
		icons[d][c] = reg.registerIcon(Reference.MOD_ID + ":ant"
			+ Ants.typeNames[c] + iconSuffixes[d]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
	return true;
    }

    public static class Barbaric extends ItemAnt {

	public Barbaric(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Carpenter extends ItemAnt {

	public Carpenter(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Common extends ItemAnt {

	public Common(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Cultivator extends ItemAnt {

	public Cultivator(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class X extends ItemAnt {

	public X(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Swamp extends ItemAnt {

	public Swamp(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Forest extends ItemAnt {

	public Forest(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Stone extends ItemAnt {

	public Stone(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Sprouter extends ItemAnt {

	public Sprouter(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Slaughterer extends ItemAnt {

	public Slaughterer(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Scavenger extends ItemAnt {

	public Scavenger(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Rancher extends ItemAnt {

	public Rancher(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Plentiful extends ItemAnt {

	public Plentiful(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Planter extends ItemAnt {

	public Planter(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Plains extends ItemAnt {

	public Plains(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Mason extends ItemAnt {

	public Mason(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Leafcutter extends ItemAnt {

	public Leafcutter(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Jungle extends ItemAnt {

	public Jungle(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Hostile extends ItemAnt {

	public Hostile(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Harvester extends ItemAnt {

	public Harvester(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

    public static class Desert extends ItemAnt {

	public Desert(final AntSpecies species) {
	    super(species);
	    // TODO Auto-generated constructor stub
	}

    }

}
