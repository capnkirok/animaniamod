package com.animania.manual.components;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;

import com.animania.manual.gui.GuiManual;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class ItemComponent implements IManualComponent
{
	private ItemStack[] stacks;

	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth;
	private int objectHeight;

	private Minecraft mc;

	private static final int ITEM_OFFSET = 3;

	public ItemComponent(int x, int y, ItemStack[] stacks)
	{
		this.stacks = stacks;
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.mc = Minecraft.getInstance();

		this.objectHeight = 16;
		this.objectWidth = 0;
		for (int i = 0; i < stacks.length; i++)
		{
			this.objectWidth += 16;
			if (i != stacks.length - 1)
				this.objectWidth += ITEM_OFFSET;
		}
	}

	@Override
	public void init()
	{

	}

	@Override
	public void draw(PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		int border = (GuiManual.MANUAL_MAX_X - this.objectWidth) / 2;
		for (int i = 0; i < this.stacks.length; i++)
		{
			ItemStack stack = this.stacks[i];
			int width = absoluteX + manual.guiLeft + border + i * (16 + ITEM_OFFSET);
			int height = absoluteY + manual.guiTop;
			poseStack.pushPose();
			// RenderHelper.enableGUIStandardItemLighting();
			Lighting.setupForFlatItems(); // TODO: Check this
			this.manual.drawItemStack(stack, this.getX(), this.getY(), null);
			// GlStateManager.disableLighting();
			poseStack.popPose();
		}
	}

	@Override
	public void drawLater(PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		int border = (GuiManual.MANUAL_MAX_X - this.objectWidth) / 2;
		for (int i = 0; i < this.stacks.length; i++)
		{
			ItemStack stack = this.stacks[i];
			int width = absoluteX + manual.guiLeft + border + i * (16 + ITEM_OFFSET);
			int height = absoluteY + manual.guiTop;

			if (mouseX > this.getX() && mouseX < this.getX() + 16 && mouseY > this.getY() && mouseY < this.getY() + 16)
			{
				poseStack.pushPose();
				this.manual.renderToolTip(stack, mouseX, mouseY);
				// GlStateManager.disableLighting();
				poseStack.popPose();
			}
		}
	}

	private boolean isHovering(int mouseX, int mouseY)
	{
		return mouseX > this.absoluteX + this.manual.guiLeft && mouseX < this.absoluteX + this.manual.guiLeft + this.objectWidth && mouseY > this.absoluteY + this.manual.guiTop && mouseY < this.absoluteY + this.manual.guiTop + this.objectHeight;
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
