package com.animania.manual.components;

import com.animania.manual.groups.ManualTopic;
import com.animania.manual.gui.GuiManual;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class LinkComponent implements IManualComponent
{

	private ResourceLocation link;

	private String text;
	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth;
	private int objectHeight;

	private Minecraft mc;

	public LinkComponent(int x, int y, String buttonText, ResourceLocation link)
	{
		this.link = link;
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.text = buttonText.trim();
		this.x = x;
		this.y = y;

		this.mc = Minecraft.getInstance();

		this.objectHeight = this.mc.font.lineHeight;
		this.objectWidth = this.mc.font.width(this.text);
	}

	@Override
	public void init()
	{

	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		this.mc.font.drawString((this.isHovering(mouseX, mouseY) ? ChatFormatting.BLUE + "" + ChatFormatting.UNDERLINE : ChatFormatting.BLUE) + this.text, this.absoluteX + this.manual.guiLeft, this.absoluteY + this.manual.guiTop, 0);
	}

	@Override
	public void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		if (this.isHovering(mouseX, mouseY))
		{
			ManualTopic t = this.manual.manualContent.get(this.link);
			String name = ChatFormatting.DARK_RED + "INVALID LINK";
			if (t != null)
				name = t.getName();
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			this.manual.drawHoveringText(name, mouseX, mouseY);
			GlStateManager.disableLighting();
			GlStateManager.popMatrix();
		}
	}

	private boolean isHovering(int mouseX, int mouseY)
	{
		return mouseX > this.absoluteX + this.manual.guiLeft && mouseX < this.absoluteX + this.manual.guiLeft + this.objectWidth && mouseY > this.absoluteY + this.manual.guiTop && mouseY < this.absoluteY + this.manual.guiTop + this.objectHeight;
	}

	@Override
	public void onLeftClick()
	{
		ManualTopic t = this.manual.manualContent.get(this.link);
		this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));

		if (t != null)
		{
			this.manual.lastTopic = this.manual.currentTopic;
			this.manual.currentTopic = t;
			this.manual.pageIndex = 0;
			this.manual.isPrevTopic = false;
			this.manual.updateButtons();
			this.manual.initComponents();
		}
	}

	@Override
	public void onRightClick()
	{

	}

	@Override
	public int getObjectWidth()
	{
		return this.objectWidth;
	}

	@Override
	public int getObjectHeight()
	{
		return this.objectHeight;
	}

	@Override
	public int getX()
	{
		return this.x;
	}

	@Override
	public int getY()
	{
		return this.y;
	}

	@Override
	public IManualComponent setX(int x)
	{
		this.x = x;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		return this;
	}

	@Override
	public IManualComponent setY(int y)
	{
		this.y = y;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;
		return this;
	}

	@Override
	public String toString()
	{
		return this.text + "@" + this.link;
	}

}
