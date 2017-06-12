package com.animania.common.tileentities.handler;

import com.animania.common.handler.ItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerHamsterWheel extends ItemStackHandler
{
	
	public ItemHandlerHamsterWheel()
	{
		this.setSize(1);
	}

    @Override
    public int getSlotLimit(int slot) {
        return 16;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
    	
    	if(!stack.isEmpty())
    	{
    		if(stack.getItem() == ItemHandler.hamsterFood)
    			return super.insertItem(slot, stack, simulate);
    		
    		return stack;
    	}
    	
    	return stack;
    }
    
   

}
