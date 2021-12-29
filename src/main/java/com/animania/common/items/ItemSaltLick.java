package com.animania.common.items;

import com.animania.config.AnimaniaConfig;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ItemSaltLick extends BlockItem
{

	public ItemSaltLick(Block block)
	{
		super(block, new Item.Properties().defaultDurability(AnimaniaConfig.careAndFeeding.saltLickMaxUses).stacksTo(1));
		this.hasSubtypes = true;
	}

}
