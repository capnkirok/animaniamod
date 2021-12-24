package com.animania.common.items;

import com.animania.common.blocks.IMetaBlockName;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class SubtypesBlockItem extends BlockItem
{

	public SubtypesBlockItem(Block block)
	{
		super(block);
		this.setRegistryName(block.getRegistryName());
		this.hasSubtypes = true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return block.getUnlocalizedName() + "_" + ((IMetaBlockName)block).getSpecialName(stack);
	}

}
