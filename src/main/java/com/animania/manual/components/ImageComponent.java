package com.animania.manual.components;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.animania.Animania;
import com.animania.manual.gui.GuiManual;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;

public class ImageComponent implements IManualComponent
{
	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth;
	private int objectHeight;

	private ResourceLocation image;

	private Minecraft mc;

	private int imageHeight;
	private int imageWidth;

	private float multiplier = 1;

	public ImageComponent(int x, int y, ResourceLocation image)
	{
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.image = image;

		this.mc = Minecraft.getInstance();

		try
		{
			Resource resource = this.mc.getResourceManager().getResource(image);
			if (resource != null)
			{
				InputStream stream = resource.getInputStream();
				BufferedImage img = ImageIO.read(stream);
				this.imageHeight = img.getHeight();
				this.imageWidth = img.getWidth();

				this.objectHeight = this.imageHeight;
				this.objectWidth = this.imageWidth;

				if (this.imageHeight > this.imageWidth)
				{
					if (this.imageHeight > GuiManual.MANUAL_MAX_Y)
					{
						float multiplier = (float) (GuiManual.MANUAL_MAX_Y - 12) / (float) this.imageHeight;
						this.objectHeight = (int) (this.imageHeight * multiplier);
						this.objectWidth = (int) (this.imageWidth * multiplier);
						this.multiplier = multiplier;
					}
				}
				else if (this.imageWidth > GuiManual.MANUAL_MAX_X)
				{
					if (this.imageWidth > this.imageHeight)
					{
						float multiplier = (float) (GuiManual.MANUAL_MAX_X - 12) / (float) this.imageWidth;
						this.objectHeight = (int) (this.imageHeight * multiplier);
						this.objectWidth = (int) (this.imageWidth * multiplier);
						this.multiplier = multiplier;
					}
					else
					{
						float multiplier = (float) (GuiManual.MANUAL_MAX_X - 12) / (float) this.imageWidth;
						this.objectHeight = (int) (this.imageHeight * multiplier);
						this.objectWidth = (int) (this.imageWidth * multiplier);
						this.multiplier = multiplier;
					}
				}

			}
		}
		catch (Exception e)
		{
			Animania.LOGGER.error(e);
		}
	}

	@Override
	public void init()
	{

	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		this.mc.renderEngine.bindTexture(this.image);
		int border = (GuiManual.MANUAL_MAX_X - this.objectWidth) / 2;
		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1);
		GuiManual.drawModalRectWithCustomSizedTexture((int) ((this.absoluteX + this.manual.guiLeft + border) / this.multiplier), (int) ((this.absoluteY + this.manual.guiTop) / this.multiplier), 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight, this.multiplier);
		GlStateManager.popMatrix();

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
}
