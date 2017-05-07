package com.animania.common.recipes;

import java.util.ArrayList;
import java.util.Iterator;

import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class CheeseRecipe1 implements IRecipe {

	private final ItemStack recipeOutput;
	public final ArrayList recipeItems = new ArrayList();
	private int bucketSlotJ;
	private int bucketSlotI;
	private int moldSlotJ;
	private int moldSlotI;
	private ItemStack moldStack;

	public CheeseRecipe1() {
		this.recipeOutput = new ItemStack(ItemHandler.cheeseWheelHolstein);
		this.recipeItems.add(new ItemStack(ItemHandler.cheeseMold));
		this.recipeItems.add(new ItemStack(ItemHandler.milkBucketHolstein));

	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {

		ArrayList arraylist = new ArrayList(this.recipeItems);

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

				if (itemstack != ItemStack.EMPTY) {
					boolean flag = false;
					Iterator iterator = arraylist.iterator();

					while (iterator.hasNext()) {
						ItemStack itemstack1 = (ItemStack) iterator.next();

						if (itemstack.getItem() == itemstack1.getItem()) {
							flag = true;

							if (itemstack.getItem() == ItemHandler.milkBucketHolstein) {
								bucketSlotJ = j;
								bucketSlotI = i;
							}

							if (itemstack.getItem() == ItemHandler.cheeseMold) {
								moldSlotJ = j;
								moldSlotI = i;
								moldStack = itemstack.copy();
							}

							arraylist.remove(itemstack1);
							break;
						}
					}

					if (!flag) {
						return false;
					}
				}
			}
		}

		return arraylist.isEmpty();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {

		NonNullList<ItemStack> bob = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
		bob.set((bucketSlotJ + (bucketSlotI * (int) (Math.sqrt(inv.getSizeInventory())))), new ItemStack(Items.BUCKET));
		moldStack.setItemDamage(moldStack.getItemDamage() + 1);

		if (moldStack.getItemDamage() >= moldStack.getMaxDamage()) {
			moldStack = ItemStack.EMPTY;
		}

		bob.set((moldSlotJ + (moldSlotI * (int) (Math.sqrt(inv.getSizeInventory())))), moldStack);

		return bob;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return this.recipeOutput.copy();
	}

	@Override
	public int getRecipeSize() {
		return this.recipeItems.size();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}
}
