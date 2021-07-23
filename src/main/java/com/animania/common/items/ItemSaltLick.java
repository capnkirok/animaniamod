package com.animania.common.items;

import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

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
