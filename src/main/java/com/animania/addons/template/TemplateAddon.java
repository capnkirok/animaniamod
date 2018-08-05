package com.animania.addons.template;

import com.animania.Animania;
import com.animania.addons.AddonHandler;
import com.animania.addons.AnimaniaAddon;
import com.animania.addons.template.client.TemplateAddonRenderHandler;
import com.animania.addons.template.common.handler.TemplateAddonBlockHandler;
import com.animania.addons.template.common.handler.TemplateAddonCraftingHandler;
import com.animania.addons.template.common.handler.TemplateAddonEntityHandler;
import com.animania.addons.template.common.handler.TemplateAddonItemHandler;
import com.animania.addons.AddonHandler.AddonRegistryEvent;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Animania.MODID)
public class TemplateAddon implements AnimaniaAddon
{

	@Override
	public void preInitCommon()
	{
		TemplateAddonEntityHandler.preInit();
		TemplateAddonItemHandler.preInit();
		TemplateAddonBlockHandler.preInit();
	}

	@Override
	public void initCommon()
	{
		TemplateAddonCraftingHandler.init();
	}

	@Override
	public void preInitClient()
	{
		TemplateAddonRenderHandler.preInit();
	}

	@Override
	public void initClient()
	{
		TemplateAddonRenderHandler.init();
	}

	@Override
	public String getVersion()
	{
		return "0.1";
	}

	@Override
	public String getAddonID()
	{
		return "template";
	}

	@Override
	public String getAddonName()
	{
		return "Template Addon";
	}

	@Override
	public String getRequiredAnimaniaVersion()
	{
		return "[1.4.7,)";
	}

	@Override
	public String getRequiredMinecraftVersion()
	{
		return "[1.12,1.13)";
	}
	
	@SubscribeEvent
	public static void register(AddonRegistryEvent event)
	{
		//event.register(new TestAddon());
	}

}
