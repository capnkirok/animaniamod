package com.animania.common.tileentities;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerTrough extends ItemStackHandler
{
	
	public ItemHandlerTrough()
	{
		this.setSize(1);
	}

    @Override
    public int getSlotLimit(int slot) {
        return 3;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return !stack.isEmpty() && (stack.getItem() == Items.WHEAT) ? super.insertItem(slot, stack, simulate) : stack;
    }
    
   

}
