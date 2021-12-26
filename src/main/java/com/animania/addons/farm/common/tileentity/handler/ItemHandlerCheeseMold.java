package com.animania.addons.farm.common.BlockEntity.handler;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerCheeseMold extends ItemStackHandler
{

	public ItemHandlerCheeseMold()
	{
		this.setSize(1);
	}

	@Override
	public int getSlotLimit(int slot)
	{
		return 1;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		return stack;
	}

}
