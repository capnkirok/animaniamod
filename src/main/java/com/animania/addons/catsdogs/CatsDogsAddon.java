package com.animania.addons.catsdogs;

import com.animania.addons.AnimaniaAddon;
import com.animania.addons.LoadAddon;
import com.animania.addons.catsdogs.client.CatsDogsAddonRenderHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonBlockHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonCraftingHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonEntityHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonItemHandler;

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
		return "0.1";
	}

	@Override
	public String getAddonID()
	{
		return "catsdogs";
	}

	@Override
	public String getAddonName()
	{
		return "Cats & Dogs";
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

}
