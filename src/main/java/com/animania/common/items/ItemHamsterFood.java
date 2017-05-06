package com.animania.common.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.animania.Animania;

public class ItemHamsterFood extends Item{
	private String name = "hamster_food";
	
	public ItemHamsterFood() {
		super();
		setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
	}

	public String getName()
	{
		return name;
	}	

}
