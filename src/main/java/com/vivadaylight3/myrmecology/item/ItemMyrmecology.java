package com.vivadaylight3.myrmecology.item;

import com.vivadaylight3.myrmecology.Myrmecology;
import com.vivadaylight3.myrmecology.reference.Reference;

import net.minecraft.item.Item;

public class ItemMyrmecology extends Item {

	public boolean colouredTextures = false;
	public String name;

	public ItemMyrmecology() {
		super();
		this.name = getClassName();
		this.setCreativeTab(Myrmecology.tab);
		this.setTextureName(name);
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
		return String.format("item.%s:%s", Reference.MOD_ID,
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	public String getUnwrappedUnlocalizedName(String unlocalizedName) {
		// TODO Auto-generated method stub
		return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
	}

}
