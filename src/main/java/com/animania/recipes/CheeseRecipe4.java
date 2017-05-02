package com.animania.recipes;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import com.animania.Animania;

public class CheeseRecipe4 implements IRecipe {

	private final ItemStack recipeOutput;
	public final ArrayList recipeItems = new ArrayList();
	private int knifeSlotJ;
	private int knifeSlotI;
	private ItemStack knifeStack;
	
	public CheeseRecipe4()
	{
		this.recipeOutput = new ItemStack(Animania.cheeseWedgeFriesian, 4);
		this.recipeItems.add(new ItemStack(Animania.carvingKnife));
		this.recipeItems.add(new ItemStack(Animania.cheeseWheelFriesian));
		
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {

		ArrayList arraylist = new ArrayList(this.recipeItems);

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

				if (itemstack != ItemStack.EMPTY)
				{
					boolean flag = false;
					Iterator iterator = arraylist.iterator();

					while (iterator.hasNext())
					{
						ItemStack itemstack1 = (ItemStack)iterator.next();

						if (itemstack.getItem() == itemstack1.getItem())
						{
							flag = true;
							
							if (itemstack.getItem() == Animania.carvingKnife) {
								knifeSlotJ = j;
								knifeSlotI = i;
								knifeStack = itemstack.copy();
							}
							
							arraylist.remove(itemstack1);
							break;
						}
					}

					if (!flag)
					{
						return false;
					}
				}
			}
		}

		return arraylist.isEmpty();
	}

	
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
	   
		NonNullList<ItemStack> bob = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
		knifeStack.setItemDamage(knifeStack.getItemDamage() + 1);
		
		if (knifeStack.getItemDamage() >= knifeStack.getMaxDamage()) {
			knifeStack = ItemStack.EMPTY;
		}
		
		bob.set((knifeSlotJ + (knifeSlotI* (int)(Math.sqrt(inv.getSizeInventory())))), knifeStack);
		
		return bob;
	}
	

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		return this.recipeOutput.copy();
	}

	@Override
	public int getRecipeSize()
	{
		return this.recipeItems.size();
	}

	@Override
	public ItemStack getRecipeOutput()
	{		
		return ItemStack.EMPTY;
	}
}


