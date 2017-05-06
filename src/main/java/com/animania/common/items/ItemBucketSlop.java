package com.animania.common.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.animania.Animania;

public class ItemBucketSlop extends Item {
	private String name = "bucket_slop";
	
	public ItemBucketSlop() {
		super();
		setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		this.setMaxStackSize(1);
	}

	public String getName()
	{
		return name;
	}	

}
