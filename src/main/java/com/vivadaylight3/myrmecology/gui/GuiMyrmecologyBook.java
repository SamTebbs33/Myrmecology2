package com.vivadaylight3.myrmecology.gui;

import java.util.LinkedList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.vivadaylight3.myrmecology.init.ModItems;
import com.vivadaylight3.myrmecology.reference.Names;
import com.vivadaylight3.myrmecology.reference.Resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiMyrmecologyBook extends GuiScreen {

    public static final ResourceLocation texture = Resources
	    .getGuiResource(Names.BOOK);
    public static LinkedList<BookSection> sections = new LinkedList<BookSection>();
    BookSection section;
    String title;
    int pageNum;
    Object[] lines;

    private final int bookImageWidth = 192;
    private final int bookImageHeight = 192;

    @Override
    public void initGui() {
	section = null;
	sections.clear();
	sections.add(new BookSection(
		"I",
		"Capturing Ants",
		"In order to start your career in Myrmecology, you will need an ant extractor. Craft one, and then find an ant hill in the wild. Each ant hill will hold a certain species of ant and may only be found in a certain biome, so it may take some time to find the desired species. Once you have found an ant hill, break it with the ant extractor and you will find at least one ant larva!"));
	sections.add(new BookSection(
		"II",
		"Incubation",
		"Once you have some ant larvae, you will need to incubate them so that they amture into fully grown ants. Construct and place an incubator and then supply it with a redstone signal. Place the larvae that you would like to incubate inside the top left slot and the click the button to choose what kind of ant the larva should mature into. In order to breed more ants, you will need at least one drone and one queen. Once the incubation period is finished, the resulting ant can be fiund in the slots to the right!"));
	sections.add(new BookSection(
		"III",
		"Breeding Ants",
		"To breed ants, you will need to construct a breeding chamber. Once you have done this, place a drone in the top slot and a queen in the bottom slot and as long as they can breed (consult a myrmopaedia for information), they will create at least one more larva!"));
	title = Names.getLocalisedName(ModItems.book);
	final int lastX = width - bookImageWidth - 146;
	int lastY = 80;
	int id = 0;
	for (final BookSection section : sections) {
	    buttonList
		    .add(new GuiButton(id, lastX, lastY, 25, 18, section.tag));
	    lastY += 18;
	    id++;
	}
	buttonList.add(new NextPageButton(id,
		(width - bookImageWidth) / 2 + 35, 225, false));
	buttonList.add(new NextPageButton(id + 1,
		(width - bookImageWidth) / 2 + 120, 225, true));
    }

    private void refresh() {
	if (section != null) {
	    title = "Ch. " + section.tag + ": " + section.title;
	    lines = section.pages.get(pageNum).lines.toArray();
	} else lines = null;
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
	if (button.id < sections.size()) {
	    section = sections.get(button.id);
	    pageNum = 0;
	} else if (button.id == sections.size()) prevPage();
	else nextPage();
	refresh();
    }

    private void prevPage() {
	if (pageNum > 0) pageNum--;
	else pageNum = section.pages.size() - 1;
    }

    private void nextPage() {
	if (section != null) if (pageNum < section.pages.size() - 1) pageNum++;
	else pageNum = 0;
    }

    @Override
    public boolean doesGuiPauseGame() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY,
	    final float partialTicks) {
	GL11.glColor4f(1, 1, 1, 1);
	mc.renderEngine.bindTexture(texture);
	drawTexturedModalRect((width - bookImageWidth) / 2, 70, 0, 0,
		bookImageWidth, bookImageHeight);
	if (title != null) fontRendererObj.drawString(title, width
		- bookImageWidth - 269, 85, 0x000000);
	if (lines != null) {
	    int lastY = 97;
	    final int x = width - bookImageWidth - 267;
	    for (final Object str : lines) {
		fontRendererObj.drawString(str.toString(), x, lastY, 0x000000);
		lastY += 10;
	    }
	}
	super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen() {}

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat
     * events
     */
    @Override
    public void onGuiClosed() {}

}

@SideOnly(Side.CLIENT)
class NextPageButton extends GuiButton {
    private final boolean field_146151_o;

    public NextPageButton(final int p_i1079_1_, final int p_i1079_2_,
	    final int p_i1079_3_, final boolean p_i1079_4_) {
	super(p_i1079_1_, p_i1079_2_, p_i1079_3_, 23, 13, "");
	field_146151_o = p_i1079_4_;
	visible = true;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(final Minecraft p_146112_1_, final int p_146112_2_,
	    final int p_146112_3_) {
	if (visible) {
	    final boolean flag = p_146112_2_ >= xPosition
		    && p_146112_3_ >= yPosition
		    && p_146112_2_ < xPosition + width
		    && p_146112_3_ < yPosition + height;
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    p_146112_1_.getTextureManager().bindTexture(
		    GuiMyrmecologyBook.texture);
	    int k = 0;
	    int l = 192;

	    if (flag) k += 23;

	    if (!field_146151_o) l += 13;

	    drawTexturedModalRect(xPosition, yPosition, k, l, 23, 13);
	}
    }
}
