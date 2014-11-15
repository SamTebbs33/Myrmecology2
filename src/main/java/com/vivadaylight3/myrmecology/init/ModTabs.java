package com.vivadaylight3.myrmecology.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.reference.Reference;

public class ModTabs {

    public static final CreativeTabs tab = new CreativeTabs(Reference.MOD_ID) {

	@Override
	public Item getTabIconItem() {
	    // TODO Auto-generated method stub
	    return ModItems.myrmopaedia;
	}

	@Override
	public boolean hasSearchBar() {
	    // TODO Auto-generated method stub
	    return true;
	}

    }, ants = new CreativeTabs(Reference.MOD_ID + "Ants") {
	@Override
	public int func_151243_f() {
	    // TODO Auto-generated method stub
	    return Ants.AntType.QUEEN.metadata;
	}

	@Override
	public Item getTabIconItem() {
	    // TODO Auto-generated method stub
	    return ModItems.ant;
	}

	@Override
	public boolean hasSearchBar() {
	    // TODO Auto-generated method stub
	    return true;
	}
    };

}
