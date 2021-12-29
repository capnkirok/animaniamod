package com.animania.common.blockentities.handler;

import com.animania.common.blockentities.BlockEntityNest;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerNest extends ItemStackHandler
{
	BlockEntityNest be;

	public ItemHandlerNest(BlockEntityNest nest)
	{
		this.setSize(1);
		this.be = nest;
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
		this.be.setChanged();
		super.onContentsChanged(slot);
	}
}
