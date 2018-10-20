package com.animania.manual.components;

import com.animania.manual.groups.ManualTopic;
import com.animania.manual.gui.GuiManual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

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
		
		this.mc = Minecraft.getMinecraft();
		
		this.objectHeight = mc.fontRenderer.FONT_HEIGHT;
		this.objectWidth = mc.fontRenderer.getStringWidth(text);
	}

	@Override
	public void init()
	{
		
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		mc.fontRenderer.drawString((isHovering(mouseX, mouseY) ? (TextFormatting.BLUE + "" + TextFormatting.UNDERLINE) : (TextFormatting.BLUE)) + text, absoluteX + manual.guiLeft, absoluteY + manual.guiTop, 0);
	}
	
	@Override
	public void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		if(isHovering(mouseX, mouseY))
		{
			ManualTopic t = manual.manualContent.get(link);
			String name = TextFormatting.DARK_RED + "INVALID LINK";
			if(t != null)
				name = t.getName();
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			manual.drawHoveringText(name, mouseX, mouseY);
			GlStateManager.disableLighting();
			GlStateManager.popMatrix();
		}
	}
	
	private boolean isHovering(int mouseX, int mouseY)
	{
		return mouseX > absoluteX + manual.guiLeft && mouseX < absoluteX + manual.guiLeft + objectWidth && mouseY > absoluteY + manual.guiTop && mouseY < absoluteY + manual.guiTop + objectHeight;
	}

	@Override
	public void onLeftClick()
	{
		ManualTopic t = manual.manualContent.get(link);
        mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));

		if(t != null)
		{
			manual.lastTopic = manual.currentTopic;
			manual.currentTopic = t;
			manual.pageIndex = 0;
			manual.isPrevTopic = false;
			manual.updateButtons();
			manual.initComponents();
		}
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
		return text + "@" + link;
	}

}
