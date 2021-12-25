package com.animania.manual.groups;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.common.handler.AddonHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

public class ManualTopic
{
	private List<ManualPage> pages = new ArrayList<>();
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
		return this.pages;
	}

	public ResourceLocation getId()
	{
		return this.id;
	}

	public boolean isFirstPage()
	{
		return this.isFirstPage;
	}

	public String getRequiredModid()
	{
		return this.requiredModid;
	}

	public void setRequiredModid(String requiredModid)
	{
		this.requiredModid = requiredModid;
	}

	public boolean isPermitted()
	{
		return ModList.get().isLoaded(this.requiredModid) || AddonHandler.isAddonLoaded(this.requiredModid);
	}

	public static boolean isPermitted(String requiredModid)
	{
		return ModList.get().isLoaded(requiredModid) || AddonHandler.isAddonLoaded(requiredModid);
	}

	public String getName()
	{
		return this.name;
	}

}
