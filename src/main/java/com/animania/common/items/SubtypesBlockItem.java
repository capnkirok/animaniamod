package com.animania.common.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SubtypesBlockItem extends BlockItem
{

	public SubtypesBlockItem(Block block)
	{
		super(block, new Item.Properties());
		this.setRegistryName(block.getRegistryName());
		this.hasSubtypes = true;
	}
}
