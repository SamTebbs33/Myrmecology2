package com.vivadaylight3.myrmecology.gui;

import java.util.LinkedList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;

import org.lwjgl.opengl.GL11;

import com.vivadaylight3.myrmecology.ant.AntBreedingRecipe;
import com.vivadaylight3.myrmecology.ant.AntSpecies;
import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.container.ContainerMyrmopaedia;
import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.reference.Names;
import com.vivadaylight3.myrmecology.reference.Resources;
import com.vivadaylight3.myrmecology.util.Time;

public class GuiMyrmopaedia extends GuiContainer {

    public static final ResourceLocation texture = Resources
	    .getGuiResource(Names.MYRMOPAEDIA);

    private static final String[] buttons = { "Info", "Breeding" };
    private static final int buttonX = 328, buttonY = 243, buttonHeight = 16,
	    buttonSpacing = 2;
    private int currentScreen = 0;
    private static final int screenInfo = 0, screenBreeding = 1;
    private final ContainerMyrmopaedia container;
    private String[] lines = {};
    private ItemStack lastAnt = null;

    private boolean screenHasChanged;

    public GuiMyrmopaedia(final ContainerMyrmopaedia container) {
	super(container);
	this.container = container;
	xSize += xSize / 4;
	ySize += (ySize / 2) + 7;
    }

    @Override
    public void initGui() {
	super.initGui();
	int id = 0;
	int lastX = buttonX - buttonSpacing;
	for (final String button : buttons) {
	    final int width = (button.length() * 6) + (buttonSpacing * 2);
	    buttonList.add(new GuiButton(id, lastX + buttonSpacing, buttonY,
		    width, buttonHeight, button));
	    lastX = lastX + buttonSpacing + width;
	    System.out.println("Added button: " + button);
	    id++;
	}
    }

    private ItemStack getAnt() {
	return container.inventory.getStackInSlot(0);
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
	// TODO Auto-generated method stub
	super.actionPerformed(button);
	screenHasChanged = button.id != currentScreen ? true : false;
	currentScreen = button.id;
	if (screenHasChanged) {
	    refresh();
	    screenHasChanged = false;
	}
    }

    private void refresh() {
	lines = null;
	if (lastAnt != null) {
	    final AntSpecies species = Ants.getSpecies(lastAnt);
	    switch (currentScreen) {
		case screenInfo:
		    lines = new String[11];
		    lines[0] = "Species name: " + species.getLocalSpeciesName();
		    lines[1] = "Binomial name: " + species.binomialName;
		    lines[2] = "Incubation: "
			    + Time.secondsFromTicks(species.matureTicks)
			    + " seconds";
		    lines[3] = "Breeding: "
			    + Time.secondsFromTicks(species.breedTicks)
			    + " seconds";
		    lines[4] = "Fertility: " + species.fertility + " (+"
			    + species.fertilityPlus + ", -"
			    + species.fertilityMinus + ")";
		    lines[5] = "Hill rarity: "
			    + (species.isHillAnt ? species.hillRarity : "n/a");
		    lines[6] = "Winged: " + species.winged;
		    lines[7] = "Nocturnal: " + species.nocturnal;
		    lines[8] = "Behaviour: " + (species.hasBehaviour ? species.behaviourDesc : "n/a");
		    lines[9] = "Biomes:";
		    lines[10] = biomesToString(species.biomes);
		    break;
		case screenBreeding:
		    LinkedList<AntBreedingRecipe> recipes = new LinkedList<AntBreedingRecipe>();
		    recipes.addAll(species.breedingRecipes);
		    for(AntBreedingRecipe recipe : AntBreedingRecipe.globalBreedingRecipes){
			if(recipe.input1 == species || recipe.input2 == species) recipes.add(recipe);
		    }
		    lines = new String[recipes.size()];
		    for(int c = 0; c < recipes.size(); c++){
			AntBreedingRecipe recipe = recipes.get(c);
			lines[c] = recipe.input2.getLocalSpeciesName().trim() + " + " + recipe.input1.getLocalSpeciesName().trim() + " = " + recipe.output.getLocalSpeciesName().trim();
		    }
		    break;
	    }
	}
    }

    private String biomesToString(BiomeGenBase[] biomes) {
	String result = "";
	int progress = 0;
	int lineLength = this.width - 24;
	if(biomes == null) return "All";
	    for(int c = 0; c < biomes.length; c++){
		result += biomes[c].biomeName +  (c < biomes.length - 1 ? ", " : "");
	    }
	return result;
    }

    @Override
    public void updateScreen() {
	super.updateScreen();
	if (lastAnt != getAnt()) {
	    lastAnt = getAnt();
	    refresh();
	}
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int p_146979_1_,
	    final int p_146979_2_) {
	final String s = Names.getLocalisedName(ModItems.myrmopaedia);
	fontRendererObj.drawString(s,
		(xSize / 2) - (fontRendererObj.getStringWidth(s) / 2) - 10, 9,
		0xFFFFFF);
	if (lines != null) {
	    int lastY = 0;
	    for (int c = 0; c < lines.length; c++) {
		final String str = lines[c];
		fontRendererObj.drawString(str, 12, (8 * (c + 3)) + lastY,
			0xFFFFFF);
		lastY += 2;
	    }
	}
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float p_146976_1_,
	    final int p_146976_2_, final int p_146976_3_) {
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	mc.getTextureManager().bindTexture(texture);
	final int k = (width - xSize) / 2;
	final int l = (height - ySize) / 2;
	drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
    }

}
