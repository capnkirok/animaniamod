package com.animania.common.api.interfaces;

import java.util.Set;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.item.Item;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fluids.Fluid;

public interface IFoodEating
{
	public void setFed(boolean fed);
	
	public boolean getFed();
	
	public void setWatered(boolean watered);
	
	public boolean getWatered();
	
	public void setHandFed(boolean handfed);
	
	public boolean getHandFed();
	
	public Set<Item> getFoodItems();
	
	default Fluid getFoodFluid()
	{
		return null;
	}
	
	default void setLiquidFed(boolean liquidFed)
	{
		
	}
	
	default Class[] getFoodBlocks()
	{
		return new Class[]{BlockCrops.class, IPlantable.class, BlockBush.class};
	}
}
