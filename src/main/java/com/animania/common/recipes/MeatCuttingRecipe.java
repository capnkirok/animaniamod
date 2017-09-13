package com.animania.common.recipes;

import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.RecipeUtil;
import com.google.gson.JsonObject;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class MeatCuttingRecipe extends ShapelessOreRecipe
{

	public MeatCuttingRecipe(ResourceLocation group, NonNullList<Ingredient> input, ItemStack result)
	{
		super(group, input, result);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		NonNullList<ItemStack> list = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		for (int i = 0; i < list.size(); ++i)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if(!stack.isEmpty() && stack.getItem() == ItemHandler.carvingKnife)
			{
				ItemStack newstack = stack.getItemDamage() >= stack.getMaxDamage() ? ItemStack.EMPTY : stack.copy();
				newstack.damageItem(1, ForgeHooks.getCraftingPlayer());
				list.set(i, newstack);
			}
			
		}
		
		return list;
	}

	public static class Factory implements IRecipeFactory
	{
		@Override
		public IRecipe parse(final JsonContext context, final JsonObject json)
		{
			final String group = JsonUtils.getString(json, "group", "");
			final NonNullList<Ingredient> ingredients = RecipeUtil.parseShapeless(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

			return new MeatCuttingRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
		}
	}

}
