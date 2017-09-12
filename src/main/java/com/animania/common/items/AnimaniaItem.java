package com.animania.common.items;

import com.animania.Animania;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AnimaniaItem extends Item
{
	public AnimaniaItem(String name)
	{
		this.setRegistryName(new ResourceLocation(Animania.MODID, name));
		ForgeRegistries.ITEMS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);

	}
}
