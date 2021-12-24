package com.animania.common.recipes.ingredients;

import com.animania.common.handler.AddonHandler;
import com.google.gson.JsonObject;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class AddonItemIngredient extends Ingredient
{

	private Ingredient main;
	private Ingredient fallback;
	private String addonID;

	public AddonItemIngredient(Ingredient main, Ingredient fallback, String addonId)
	{
		super(AddonHandler.isAddonLoaded(addonId) ? main.getItems() : fallback.getItems());

		this.main = main;
		this.fallback = fallback;
		this.addonID = addonId;
	}

	public static class Factory implements IIngredientFactory
	{
		@Override
		public Ingredient parse(JsonContext context, JsonObject json)
		{
			String id = json.get("addon").getAsString();

			Ingredient main = null;

			if (AddonHandler.isAddonLoaded(id))
				main = CraftingHelper.getIngredient(json.get("item"), context);

			Ingredient fallback = CraftingHelper.getIngredient(json.get("fallback"), context);

			AddonItemIngredient ing = new AddonItemIngredient(main, fallback, id);

			return ing;
		}

	}

}
