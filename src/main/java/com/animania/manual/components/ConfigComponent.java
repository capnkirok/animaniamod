package com.animania.manual.components;

import com.animania.manual.gui.GuiManual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class ConfigComponent implements IManualComponent
{
	private String text;
	private String configOption;
	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth;
	private int objectHeight;

	private Minecraft mc;

	public ConfigComponent(int x, int y, String text, String configOption)
	{
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.text = text.trim();
		this.mc = Minecraft.getInstance();

		this.objectHeight = mc.font.lineHeight;
		this.objectWidth = mc.font.width(text);

		this.configOption = configOption;
	}

	@Override
	public void init()
	{
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		boolean isBool = text.equalsIgnoreCase("true") || text.equalsIgnoreCase("false");

		mc.font.drawString((isBool ? (Boolean.valueOf(text) ? TextFormatting.GREEN : TextFormatting.RED) : TextFormatting.DARK_PURPLE) + text, absoluteX + manual.guiLeft, absoluteY + manual.guiTop, 0);
	}

	@Override
	public void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		if (manual.isHovering(this, mouseX, mouseY))
		{
			String c = configOption.replace("general.", "");
			String[] split = c.split("[.;]");

			String tooltip = "Config: ";

			for (int i = 0; i < split.length; i++)
			{
				if (i != split.length - 1)
					tooltip += I18n.translateToLocal("animania.category." + split[i]) + " \u2192 ";
				else
					tooltip += split[i];
			}

			GlStateManager.pushMatrix();
			manual.drawHoveringText(tooltip, mouseX, mouseY);
			GlStateManager.disableLighting();
			GlStateManager.popMatrix();
		}
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
		return objectWidth;
	}

	@Override
	public int getObjectHeight()
	{
		return objectHeight;
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
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
		return text;
	}
}
