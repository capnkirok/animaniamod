package com.animania.client.gui;

import java.util.List;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfigAnimania extends GuiConfig
{
    private static final String LANG_PREFIX = Animania.MODID + ".category.";

    public GuiConfigAnimania(GuiScreen parent) {
        super(parent, getConfigElements(), "animania", false, false, "Animania Configuration");
    }

    private static List<IConfigElement> getConfigElements() {

        final Configuration configuration = AnimaniaConfig.EventHandler.getConfiguration();

        final ConfigCategory topLevelCategory = configuration.getCategory(Configuration.CATEGORY_GENERAL);
        topLevelCategory.getChildren()
                .forEach(configCategory -> configCategory.setLanguageKey(GuiConfigAnimania.LANG_PREFIX + configCategory.getName()));

        return new ConfigElement(topLevelCategory).getChildElements();
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
