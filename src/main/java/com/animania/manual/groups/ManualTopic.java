package com.animania.manual.groups;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.common.handler.AddonHandler;

import net.minecraft.util.ResourceLocation;

public class ManualTopic
{
	private List<ManualPage> pages = new ArrayList<ManualPage>();
	private ResourceLocation id;
	private boolean isFirstPage;
	private String requiredModid = Animania.MODID;
	private String name;
	
	public ManualTopic(ResourceLocation id, boolean firstPage, String name, ManualPage... pages)
	{
		this.pages = Arrays.asList(pages);
		this.id = id;
		this.isFirstPage = firstPage;
		this.name = name;
	}
	
	public ManualTopic(ResourceLocation id, boolean firstPage, String name, List<ManualPage> components)
	{
		this.pages = components;
		this.id = id;
		this.isFirstPage = firstPage;
		this.name = name;
	}
	
	public ManualTopic(ResourceLocation id, String name, ManualPage... components)
	{
		this(id, false, name, components);
	}

	public List<ManualPage> getPages()
	{
		return pages;
	}

	public ResourceLocation getId()
	{
		return id;
	}

	public boolean isFirstPage()
	{
		return isFirstPage;
	}

	public String getRequiredModid()
	{
		return requiredModid;
	}

	public void setRequiredModid(String requiredModid)
	{
		this.requiredModid = requiredModid;
	}
	
	public boolean isPermitted()
	{
		return Loader.isModLoaded(requiredModid) || AddonHandler.isAddonLoaded(requiredModid);
	}
	
	public static boolean isPermitted(String requiredModid)
	{
		return Loader.isModLoaded(requiredModid) || AddonHandler.isAddonLoaded(requiredModid);
	}

	public String getName()
	{
		return name;
	}
	
	
	
}
