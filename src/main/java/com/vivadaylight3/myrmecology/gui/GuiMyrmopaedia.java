package com.vivadaylight3.myrmecology.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.vivadaylight3.myrmecology.container.ContainerMyrmopaedia;
import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.reference.Names;
import com.vivadaylight3.myrmecology.reference.Resources;

public class GuiMyrmopaedia extends GuiContainer {

    public static final ResourceLocation texture = Resources
	    .getGuiResource(Names.MYRMOPAEDIA);

    public GuiMyrmopaedia(final ContainerMyrmopaedia container) {
	super(container);
	xSize += xSize / 4;
	ySize += (ySize / 2) + 7;
    }

    @Override
    public void onGuiClosed() {
	// ((ContainerMyrmopaedia) this.inventorySlots).inventory.writeToNBT();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int p_146979_1_,
	    final int p_146979_2_) {
	final String s = Names.getLocalisedName(ModItems.myrmopaedia);
	fontRendererObj.drawString(s,
		(xSize / 2) - (fontRendererObj.getStringWidth(s) / 2) - 10, 9,
		0xFFFFFF);
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
