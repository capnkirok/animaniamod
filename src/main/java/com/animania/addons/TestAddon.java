package com.animania.addons;

import com.animania.Animania;
import com.animania.addons.AddonHandler.AddonRegistryEvent;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Animania.MODID)
public class TestAddon implements AnimaniaAddon
{

	@Override
	public void preInitCommon()
	{
		
	}

	@Override
	public void initCommon()
	{
		
	}

	@Override
	public void preInitClient()
	{
		
	}

	@Override
	public void initClient()
	{
		
	}

	@Override
	public String getVersion()
	{
		return "0.1";
	}

	@Override
	public String getAddonID()
	{
		return "testaddon";
	}

	@Override
	public String getAddonName()
	{
		return "Test Addon";
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
