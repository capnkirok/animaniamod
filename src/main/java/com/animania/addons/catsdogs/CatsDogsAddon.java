package com.animania.addons.catsdogs;

import com.animania.addons.catsdogs.client.CatsDogsAddonRenderHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonBlockHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonCraftingHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonEntityHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonItemHandler;
import com.animania.api.addons.AnimaniaAddon;
import com.animania.api.addons.LoadAddon;

@LoadAddon
public class CatsDogsAddon implements AnimaniaAddon
{

	@Override
	public void preInitCommon()
	{
		CatsDogsAddonEntityHandler.preInit();
		CatsDogsAddonItemHandler.preInit();
		CatsDogsAddonBlockHandler.preInit();
	}

	@Override
	public void initCommon()
	{
		CatsDogsAddonCraftingHandler.init();
	}

	@Override
	public void preInitClient()
	{
		CatsDogsAddonRenderHandler.preInit();
	}

	@Override
	public void initClient()
	{
		CatsDogsAddonRenderHandler.init();
	}

	@Override
	public String getVersion()
	{
		return "GRADLE:catsdogs_version";
	}

	@Override
	public String getAddonID()
	{
		return "catsdogs";
	}

	@Override
	public String getAddonName()
	{
		return "Animania - Cats & Dogs";
	}

	@Override
	public String getDependencies()
	{
		return "required-after:animania@[2.0.0,);required-after:minecraft@[1.12,1.13)";
	}

}
