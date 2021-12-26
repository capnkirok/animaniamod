package com.animania.common.tileentities.handler;

import com.animania.common.tileentities.BlockEntityNest;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerNest extends ItemStackHandler
{
	BlockEntityNest te;

	public ItemHandlerNest(BlockEntityNest nest)
	{
		this.setSize(1);
		this.te = nest;
	}

	@Override
	public int getSlotLimit(int slot)
	{
		return 3;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		return stack;
	}

	@Override
	protected void onContentsChanged(int slot)
	{
		this.te.setChanged();
		super.onContentsChanged(slot);
	}
}
