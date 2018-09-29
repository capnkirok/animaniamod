package com.animania.manual.components;

import com.animania.manual.gui.GuiManual;

public interface IManualComponent
{
	public void init();
	
	public void draw(int mouseX, int mouseY, float partialTicks);
	
	public void onLeftClick();
	
	public void onRightClick();
	
	default void onLeftClick(int mouseX, int mouseY)
	{
		onLeftClick();
	}
	
	default void onRightClick(int mouseX, int mouseY)
	{
		onRightClick();
	}
	
	public int getObjectWidth();

	public int getObjectHeight();
	
	public int getX();
	
	public int getY();
	
	public IManualComponent setX(int x);
	
	public IManualComponent setY(int y);
	
	default void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		
	}
	
	default void update()
	{
		
	}
}
