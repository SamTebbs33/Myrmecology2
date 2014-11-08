package com.vivadaylight3.myrmecology.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.vivadaylight3.myrmecology.init.ModTabs;
import com.vivadaylight3.myrmecology.reference.Reference;

public class ItemMyrmecology extends Item {

    public boolean colouredTextures = false;
    public String name;

    public ItemMyrmecology() {
	super();
	name = getClassName();
	setCreativeTab(ModTabs.tab);
	setUnlocalizedName(name);
    }

    @Override
    public void addInformation(final ItemStack par1ItemStack,
	    final EntityPlayer par2EntityPlayer, final List par3List,
	    final boolean par4) {
	final String info = StatCollector.translateToLocal(this
		.getUnlocalizedName() + ".desc");
	if (!info.equals("") && !info.equals(getUnlocalizedName() + ".desc")) par3List
		.add(info);
    }

    private String getClassName() {
	String name = getClass().getName().substring(
		getClass().getName().indexOf("Item") + 4);
	final Character ch = name.charAt(0);
	name = Character.toLowerCase(ch) + name.substring(1);
	return name;
    }

    @Override
    public String getItemStackDisplayName(final ItemStack p_77653_1_) {
	return StatCollector.translateToLocal(this.getUnlocalizedName()
		+ ".name");
    }

    private String getTexturePath() {
	return Reference.MOD_ID + ":" + name;
    }

    @Override
    public String getUnlocalizedName() {
	return "item." + getTexturePath();
    }

    @Override
    public void registerIcons(final IIconRegister reg) {
	itemIcon = reg.registerIcon(getTexturePath());
    }

}
