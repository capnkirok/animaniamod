package com.animania.template;

import com.animania.addons.template.client.TemplateAddonRenderHandler;
import com.animania.addons.template.common.handler.TemplateAddonBlockHandler;
import com.animania.addons.template.common.handler.TemplateAddonCraftingHandler;
import com.animania.addons.template.common.handler.TemplateAddonEntityHandler;
import com.animania.addons.template.common.handler.TemplateAddonItemHandler;
import com.animania.api.addons.AnimaniaAddon;
import com.animania.api.addons.LoadAddon;

@LoadAddon
public class TemplateAddon implements AnimaniaAddon
{

	/*
	 * Registration Order: GUI Handler, Injections, Entities, Items, Blocks
	 */
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
		return "GRADLE:template_version";
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
	public String getDependencies()
	{
		return "required-after:animania@[2.0.0,);required-after:minecraft@[1.12,1.13)";
	}

}
