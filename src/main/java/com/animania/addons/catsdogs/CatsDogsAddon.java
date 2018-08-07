package com.animania.addons.catsdogs;

import com.animania.Animania;
import com.animania.addons.AddonHandler.AddonRegistryEvent;
import com.animania.addons.AnimaniaAddon;
import com.animania.addons.catsdogs.client.CatsDogsAddonRenderHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonBlockHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonCraftingHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonEntityHandler;
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonItemHandler;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Animania.MODID)
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
	
	@SubscribeEvent
	public static void register(AddonRegistryEvent event)
	{
		event.register(new CatsDogsAddon());
	}

}
