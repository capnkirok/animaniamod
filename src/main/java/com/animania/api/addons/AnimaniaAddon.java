package com.animania.api.addons;

public interface AnimaniaAddon
{
	void preInitCommon();

	void initCommon();

	void preInitClient();

	void initClient();

	String getVersion();

	String getAddonID();

	String getAddonName();

	String getDependencies();
}
