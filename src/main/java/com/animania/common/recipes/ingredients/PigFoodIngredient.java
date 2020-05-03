package com.animania.common.recipes.ingredients;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class PigFoodIngredient extends Ingredient
{

	public PigFoodIngredient()
	{
		super(getStacks());
	}

	public static ItemStack[] getStacks()
	{
		Item[] items = AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.slopIngredients);
		ItemStack[] stacks = new ItemStack[items.length];

		for (int i = 0; i < items.length; i++)
		{
			stacks[i] = new ItemStack(items[i]);
		}

		return stacks;
	}

	public static class Factory implements IIngredientFactory
	{

		@Override
		public Ingredient parse(JsonContext context, JsonObject json)
		{
			return new PigFoodIngredient();
		}

	}

}
