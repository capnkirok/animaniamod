package com.animania.addons.farm.common.item;

import com.animania.Animania;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class ItemCheeseWheel extends BlockItem
{
	private String name = "cheese_wheel";

	public ItemCheeseWheel(String type, Block block)
	{
		super(block);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
	}

	public String getName()
	{
		return this.name;
	}

}
