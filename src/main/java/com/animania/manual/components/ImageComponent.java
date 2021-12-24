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
			Resource resource = mc.getResourceManager().getResource(image);
			if (resource != null)
			{
				InputStream stream = resource.getInputStream();
				BufferedImage img = ImageIO.read(stream);
				this.imageHeight = img.getHeight();
				this.imageWidth = img.getWidth();

				this.objectHeight = imageHeight;
				this.objectWidth = imageWidth;
				
				if (imageHeight > imageWidth)
				{
					if (imageHeight > GuiManual.MANUAL_MAX_Y)
					{
						float multiplier = (float) (GuiManual.MANUAL_MAX_Y - 12) / (float) imageHeight;
						this.objectHeight = (int) (imageHeight * multiplier);
						this.objectWidth = (int) (imageWidth * multiplier);
						this.multiplier = multiplier;
					}
				}
				else if (imageWidth > imageHeight)
				{
					if (imageWidth > GuiManual.MANUAL_MAX_X)
					{
						float multiplier = (float) (GuiManual.MANUAL_MAX_X - 12) / (float) imageWidth;
						this.objectHeight = (int) (imageHeight * multiplier);
						this.objectWidth = (int) (imageWidth * multiplier);
						this.multiplier = multiplier;
					}
				}
				else
				{
					if (imageWidth > GuiManual.MANUAL_MAX_X)
					{
						float multiplier = (float) (GuiManual.MANUAL_MAX_X - 12) / (float) imageWidth;
						this.objectHeight = (int) (imageHeight * multiplier);
						this.objectWidth = (int) (imageWidth * multiplier);
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
		mc.renderEngine.bindTexture(image);
		int border = (GuiManual.MANUAL_MAX_X - objectWidth)/2;
		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1);
		manual.drawModalRectWithCustomSizedTexture((int)((absoluteX + manual.guiLeft + border)/multiplier), (int)((absoluteY + manual.guiTop)/multiplier), 0 , 0, imageWidth, imageHeight, imageWidth, imageHeight, multiplier);
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
}
