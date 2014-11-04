package com.vivadaylight3.myrmecology.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static void init(){
	// Items
	GameRegistry.addRecipe(new ItemStack(ModItems.myrmopaedia), "ggg", "rer", "qqq", 'g', new ItemStack(Blocks.glass_pane), 'r', new ItemStack(Items.redstone), 'e', new ItemStack(Items.emerald), 'q', new ItemStack(Items.quartz));
	GameRegistry.addRecipe(new ItemStack(ModItems.extractor), "   ", "sii", " di", 's', new ItemStack(Items.iron_shovel), 'i', new ItemStack(Items.iron_ingot), 'd', new ItemStack(Items.dye, 1, 2));
	// Blocks
	GameRegistry.addShapelessRecipe(new ItemStack(Blocks.dirt, 2, 2), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 15), new ItemStack(Blocks.brown_mushroom), new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt));
	GameRegistry.addRecipe(new ItemStack(ModBlocks.incubator), "sss", "gps", "srs", 's', new ItemStack(Blocks.stone), 'g', new ItemStack(Blocks.glass_pane),'p', new ItemStack(Blocks.wooden_pressure_plate), 'r', new ItemStack(Blocks.redstone_lamp));
	GameRegistry.addRecipe(new ItemStack(ModBlocks.breedingChamber), "sss", "sds", "sss", 's', new ItemStack(Blocks.wooden_slab), 'd', new ItemStack(Blocks.dirt, 1, 2));
	GameRegistry.addRecipe(new ItemStack(ModBlocks.formicarium), "srs", "gdg", "srs", 's', new ItemStack(Blocks.wooden_slab), 'r', new ItemStack(Items.dye, 1, 1), 'g', new ItemStack(Blocks.glass_pane), 'd', new ItemStack(Blocks.dirt, 2, 2));
    }

}