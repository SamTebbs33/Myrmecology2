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

	public GuiIncubator(InventoryPlayer inventory,
			TileEntityIncubator tileEntity) {
		super(new ContainerIncubator(inventory, tileEntity));
		this.tile = tileEntity;
		type = this.tile.productType;
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonList.add(new GuiButton(0, this.guiLeft + 8, this.guiTop + 45, 46,
				20, Ants.typeNames[type]));
	}

	public int toggleProductType() {
		return (Maths.cyclicIncrement(type, 1, Ants.typeNames.length - 1));
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) {
		type = toggleProductType();
		ModNet.net.sendToServer(new PacketIncubator(tile.xCoord, tile.yCoord,
				tile.zCoord, type));
		((GuiButton) buttonList.get(0)).displayString = Ants.typeNames[type];
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_,
			int p_146979_2_) {
		String s = this.tile.getInventoryName();
		this.fontRendererObj.drawString(s, this.xSize / 2
				- this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(
				I18n.format("container.inventory", new Object[0]), 8,
				this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		/*
		 * if (this.tile.isBurning()) { int i1 =
		 * this.tile.getBurnTimeRemainingScaled(13);
		 * this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1,
		 * 14, i1 + 1); i1 = this.tile.getCookProgressScaled(24);
		 * this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16); }
		 */
	}

}
