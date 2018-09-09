package com.animania.addons;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface AnimaniaAddon
{
	public void preInitCommon();
	
	public void initCommon();

	public void preInitClient();
	
	public void initClient();


	public String getVersion();
	
	public String getAddonID();
	
	public String getAddonName();
	
	public String getRequiredAnimaniaVersion();
	
	public String getRequiredMinecraftVersion();
}
