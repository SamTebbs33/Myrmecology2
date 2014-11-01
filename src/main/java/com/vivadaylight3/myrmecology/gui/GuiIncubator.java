package com.vivadaylight3.myrmecology.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.vivadaylight3.myrmecology.ant.Ants;
import com.vivadaylight3.myrmecology.container.ContainerIncubator;
import com.vivadaylight3.myrmecology.init.ModNet;
import com.vivadaylight3.myrmecology.net.PacketIncubator;
import com.vivadaylight3.myrmecology.reference.Names;
import com.vivadaylight3.myrmecology.reference.Resources;
import com.vivadaylight3.myrmecology.tileentity.TileEntityIncubator;
import com.vivadaylight3.myrmecology.util.Maths;

public class GuiIncubator extends GuiContainer {

    TileEntityIncubator tile;
    int type = 0;
    public static final ResourceLocation texture = Resources
	    .getGuiResource(Names.INCUBATOR);

    public GuiIncubator(final InventoryPlayer inventory,
	    final TileEntityIncubator tileEntity) {
	super(new ContainerIncubator(inventory, tileEntity));
	tile = tileEntity;
	type = tile.productType;
    }

    @Override
    public void initGui() {
	super.initGui();
	buttonList.add(new GuiButton(0, guiLeft + 8, guiTop + 45, 46, 20,
		Ants.typeNames[type]));
    }

    public int toggleProductType() {
	return (Maths.cyclicIncrement(type, 1, Ants.typeNames.length - 1));
    }

    @Override
    protected void actionPerformed(final GuiButton guibutton) {
	type = toggleProductType();
	ModNet.net.sendToServer(new PacketIncubator(tile.xCoord, tile.yCoord,
		tile.zCoord, type));
	((GuiButton) buttonList.get(0)).displayString = Ants.typeNames[type];
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int p_146979_1_,
	    final int p_146979_2_) {
	final String s = tile.getInventoryName();
	fontRendererObj.drawString(s,
		(xSize / 2) - (fontRendererObj.getStringWidth(s) / 2), 6,
		4210752);
	fontRendererObj.drawString(
		I18n.format("container.inventory", new Object[0]), 8,
		(ySize - 96) + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float p_146976_1_,
	    final int p_146976_2_, final int p_146976_3_) {
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	mc.getTextureManager().bindTexture(texture);
	final int k = (width - xSize) / 2;
	final int l = (height - ySize) / 2;
	drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

	final float time = tile.progress;

	if (time > 0) {
	    final int progress = tile.getProgressScaled(24);
	    drawTexturedModalRect(k + 31, l + 16, 176, 0, (progress + 1), 16);
	}

    }

}
