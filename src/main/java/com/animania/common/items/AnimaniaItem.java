package com.animania.common.items;

import com.animania.Animania;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class AnimaniaItem extends Item
{
	public AnimaniaItem(String name, Item.Properties props)
	{
		super(props.tab(Animania.TabAnimaniaResources));
		this.setRegistryName(new ResourceLocation(Animania.MODID, name));
	}
}
