package com.animania.common.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.animania.Animania;

public class ItemPeacockFeather extends Item {
	private String name = "peacock_feather";

	public ItemPeacockFeather(String type) {
		super(); 
		name = type + "_" + name;
		this.setRegistryName(new ResourceLocation(Animania.MODID, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.MODID + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setMaxStackSize(64);
	}
		
	public String getName()
	{
		return name;
	}
	
}
