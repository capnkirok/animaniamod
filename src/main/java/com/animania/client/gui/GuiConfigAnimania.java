package com.animania.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import com.animania.Animania;


public class GuiConfigAnimania extends GuiConfig {
	public GuiConfigAnimania(GuiScreen parent) {
		
		super(parent, getConfigElements(), "animania", false, false, "Animania Configuration");
	}
	
	private static List<IConfigElement> getConfigElements() {
       
		List<IConfigElement> list = new ArrayList<IConfigElement>();
      
        //Add categories to config GUI
        list.add(new ConfigElement(Animania.config.getCategory("Game Rules")));
        list.add(new ConfigElement(Animania.config.getCategory("Care and Feeding")));
        list.add(new ConfigElement(Animania.config.getCategory("Spawn")));
        list.add(new ConfigElement(Animania.config.getCategory("Drops")));
        list.add(new ConfigElement(Animania.config.getCategory("Entities")));
      
        return list;
    }
	
	@Override
	public void initGui() {
		super.initGui();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		super.actionPerformed(button);
	}
}
