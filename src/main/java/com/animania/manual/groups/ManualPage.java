package com.animania.manual.groups;

import java.util.ArrayList;
import java.util.List;

import com.animania.manual.components.IManualComponent;

public class ManualPage
{
	private List<IManualComponent> components = new ArrayList<>();

	public ManualPage(IManualComponent... components)
	{
		this.components = Arrays.asList(components);
	}

	public ManualPage(List<IManualComponent> components)
	{
		this.components = components;
	}

	public List<IManualComponent> getComponents()
	{
		return this.components;
	}

}
