package com.animania.api.addons;

public interface AnimaniaAddon
{
	public void preInitCommon();
	
	public void initCommon();

	public void preInitClient();
	
	public void initClient();

	
	public String getVersion();
	
	public String getAddonID();
	
	public String getAddonName();
	
	public String getDependencies();
}
