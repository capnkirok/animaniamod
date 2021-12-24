package com.animania.common.tileentities.handler;

import com.animania.common.blocks.BlockTrough;

import net.minecraft.world.item.ItemStack;
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
    	
    	if(!stack.isEmpty())
    	{
    		if(stack.getItem() == Items.WHEAT || BlockTrough.isModdedFoodItem(stack))
    			return super.insertItem(slot, stack, simulate);
    		
    		return stack;
    	}
    	
    	return stack;
    }
    
   

}
