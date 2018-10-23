package com.animania.common.tileentities.handler;

import com.animania.common.tileentities.TileEntityNest;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerNest extends ItemStackHandler
{
	TileEntityNest te;

	public ItemHandlerNest(TileEntityNest nest)
	{
		this.setSize(1);
		this.te = nest;
	}

    @Override
    public int getSlotLimit(int slot) {
        return 3;
    }
 
    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
    	return stack;
    }
    
    @Override
    protected void onContentsChanged(int slot)
    {
    	te.markDirty();
    	super.onContentsChanged(slot);
    }
}
