package com.animania.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfigAnimania extends GuiConfig
{
	private static final String LANG_PREFIX = Animania.MODID + ".category.";

	public GuiConfigAnimania(Screen parent)
	{
		super(parent, getConfigElements(), "animania", false, false, "Animania Configuration");
	}

	private static List<IConfigElement> getConfigElements()
	{

		final List<Configuration> configuration = AnimaniaConfig.EventHandler.getConfiguration();

		List<IConfigElement> configElements = new ArrayList<IConfigElement>();

		for (Configuration cfg : configuration)
		{
			ConfigCategory topLevelCategory = cfg.getCategory(Configuration.CATEGORY_GENERAL);
			topLevelCategory.getChildren().forEach(configCategory -> configCategory.setLanguageKey(GuiConfigAnimania.LANG_PREFIX + configCategory.getName()));
			configElements.addAll(new ConfigElement(topLevelCategory).getChildElements());
		}

		return configElements;
	}

	@Override
	public void initGui()
	{
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(Button button)
	{
		super.actionPerformed(button);
	}
}
