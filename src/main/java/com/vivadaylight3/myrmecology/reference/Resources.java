package com.vivadaylight3.myrmecology.reference;

import net.minecraft.util.ResourceLocation;

public class Resources {

    public static final String LOCATION_TEXTURES = Reference.MOD_ID
	    + ":textures", LOCATION_GUI = LOCATION_TEXTURES + "/gui";

    public static ResourceLocation getGuiResource(final String name) {
	return new ResourceLocation(LOCATION_GUI + "/" + name + ".png");
    }

}
