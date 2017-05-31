package com.animania.common.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class MilkConversionRecipe extends ShapelessOreRecipe
{

	public MilkConversionRecipe(ItemStack result, Object[] recipe)
	{
		super(result, recipe);
	}
	
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        
		return ret;
	}

}
