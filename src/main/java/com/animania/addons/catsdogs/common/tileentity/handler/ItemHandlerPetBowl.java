package com.animania.addons.catsdogs.common.tileentity.handler;

import com.animania.addons.catsdogs.common.block.BlockPetBowl;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerPetBowl extends ItemStackHandler
{
	
	public ItemHandlerPetBowl()
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
    		if(BlockPetBowl.isFoodItem(stack))
    			return super.insertItem(slot, stack, simulate);
    		
    		return stack;
    	}
    	
    	return stack;
    }
    
   

}
