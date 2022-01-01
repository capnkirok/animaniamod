package com.animania.catsdogs;

import com.animania.api.addons.AnimaniaAddon;
import com.animania.api.addons.LoadAddon;
import com.animania.catsdogs.client.CatsDogsAddonRenderHandler;
import com.animania.catsdogs.common.event.CatsDogsAddonSpawnHandler;
import com.animania.catsdogs.common.handler.*;
import net.minecraftforge.common.MinecraftForge;

@LoadAddon
public class CatsDogsAddon implements AnimaniaAddon
{

	@Override
	public void preInitCommon()
	{
		MinecraftForge.EVENT_BUS.register(CatsDogsVillagerProfessions.class);
		MinecraftForge.EVENT_BUS.register(CatsDogsAddonSpawnHandler.class);

		CatsDogsAddonInjectionHandler.preInit();
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
		return "required-after:animania@[2.0.3,);required-after:minecraft@[1.12,1.13)";
	}

}
