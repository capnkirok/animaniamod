package com.animania.common.recipes;

import java.util.ArrayList;
import java.util.Iterator;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class SlopBucketRecipe2 implements IRecipe {

	private final ItemStack recipeOutput;
	public final ArrayList<ItemStack> recipeItems = new ArrayList();
	private int bucketSlotJ;
	private int bucketSlotI;
	private ItemStack bucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
	private ItemStack milkHolstein = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkHolstein);
	private ItemStack milkFriesian = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);

	public SlopBucketRecipe2() {
		this.recipeOutput = bucket;
		this.recipeItems.add(new ItemStack(Items.CARROT));
		this.recipeItems.add(new ItemStack(Items.BREAD));
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {

		ItemStack milk = ItemStack.EMPTY;
		ItemStack veggie1 = ItemStack.EMPTY;
		ItemStack veggie2 = ItemStack.EMPTY;
		ItemStack extra = ItemStack.EMPTY;

		for(int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack current = inv.getStackInSlot(i);

			if(!current.isEmpty())

			{
				if(recipeItems.get(0).getItem() == current.getItem() && veggie1.isEmpty())
				{
					veggie1 = current.copy();
				}
				else if (recipeItems.get(1).getItem() == current.getItem() && veggie2.isEmpty())
				{
					veggie2 = current.copy();
				}
				else if((ItemStack.areItemStacksEqual(current, this.milkFriesian) || ItemStack.areItemStacksEqual(current, this.milkHolstein) || current.getItem() == Items.MILK_BUCKET) && milk.isEmpty())
				{
					milk = current.copy();
				}
				else
				{
					extra = current.copy();
				}

			}

		}

		return !milk.isEmpty() && !veggie1.isEmpty() && !veggie2.isEmpty() && extra.isEmpty();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> bob = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
		// bob.set((bucketSlotJ + (bucketSlotI * (int)
		// (Math.sqrt(inv.getSizeInventory())))), new ItemStack(Items.BUCKET));
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
