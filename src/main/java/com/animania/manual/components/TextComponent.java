package com.animania.manual.components;

import com.animania.manual.gui.GuiManual;

import net.minecraft.client.Minecraft;

public class TextComponent implements IManualComponent
{

	private String text;
	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth;
	private int objectHeight;

	private Minecraft mc;

	public TextComponent(int x, int y, String text)
	{
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.text = text.trim();
		this.mc = Minecraft.getInstance();

		this.objectHeight = this.mc.font.lineHeight;
		this.objectWidth = this.mc.font.width(text);
	}

	@Override
	public void init()
	{

	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		this.mc.font.drawString(this.text, this.absoluteX + this.manual.guiLeft, this.absoluteY + this.manual.guiTop, 0);
	}

	@Override
	public void onLeftClick()
	{

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
		return this.text;
	}

}
