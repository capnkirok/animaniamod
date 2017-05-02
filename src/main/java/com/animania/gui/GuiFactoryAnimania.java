package com.animania.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.animania.Animania;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiFactoryAnimania implements IModGuiFactory 
{
    @Override
    public void initialize(Minecraft minecraftInstance) 
    {
    	// Do nothing
    }
 
    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() 
    {
        return GuiConfigAnimania.class;
    }
 
    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() 
    {
        return null;
    }
 
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) 
    {
        return null;
    }
    
    
    
}