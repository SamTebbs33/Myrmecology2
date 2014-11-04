package com.vivadaylight3.myrmecology.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static void init(){
	GameRegistry.addRecipe(new ItemStack(ModItems.myrmopaedia), "ggg", "rer", "qqq", 'g', new ItemStack(Blocks.glass_pane), 'r', new ItemStack(Items.redstone), 'e', new ItemStack(Items.emerald), 'q', new ItemStack(Items.quartz));
	GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.formicariumDirt), new ItemStack(Items.dye, 1, 15), new ItemStack(Blocks.dirt), new ItemStack(Items.sugar), new ItemStack(Items.clay_ball));
	GameRegistry.addRecipe(new ItemStack(ModBlocks.incubator), "sss", "gps", "srs", 's', new ItemStack(Blocks.stone), 'g', new ItemStack(Blocks.glass_pane),'p', new ItemStack(Blocks.wooden_pressure_plate), 'r', new ItemStack(Blocks.redstone_lamp));
    }

}
