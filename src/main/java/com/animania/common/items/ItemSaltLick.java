package com.animania.common.items;

import com.animania.config.AnimaniaConfig;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class ItemSaltLick extends BlockItem
{

	public ItemSaltLick(Block block)
	{
		super(block);
		this.hasSubtypes = true;
		this.setMaxDamage(AnimaniaConfig.careAndFeeding.saltLickMaxUses);
		this.setMaxStackSize(1);
		
	}
		

}
