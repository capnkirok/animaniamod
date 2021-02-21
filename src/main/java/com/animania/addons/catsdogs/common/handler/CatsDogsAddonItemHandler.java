package com.animania.addons.catsdogs.common.handler;

import com.animania.addons.catsdogs.common.entity.canids.DogType;
import com.animania.addons.catsdogs.common.entity.felids.CatType;
import com.animania.api.data.EntityGender;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.item.Item;

public class CatsDogsAddonItemHandler
{

	public static Item entityeggrandomdog;
	public static Item entityeggrandomcat;

	/**
	 * Register Items
	 */
	public static void preInit()
	{
		entityeggrandomdog = new ItemEntityEgg("dog_random", DogType.BLOOD_HOUND, EntityGender.RANDOM);
		entityeggrandomcat = new ItemEntityEgg("cat_random", CatType.AMERICAN_SHORTHAIR, EntityGender.RANDOM);
	}

}
