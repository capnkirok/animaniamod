package com.animania.common.handler;

import com.animania.Animania;
import com.animania.common.loottables.AddMoreFunction;
import com.animania.common.loottables.EntityFedProperty;
import com.animania.common.loottables.EntityGenderProperty;
import com.animania.common.loottables.EntityWateredProperty;
import com.animania.common.loottables.WoolColorFunction;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraft.world.storage.loot.properties.EntityPropertyManager;

public class LootTableHandler
{
	public static void preInit()
	{
		EntityPropertyManager.registerProperty(new EntityFedProperty.Serializer());
		EntityPropertyManager.registerProperty(new EntityWateredProperty.Serializer());
		EntityPropertyManager.registerProperty(new EntityGenderProperty.Serializer());
		LootFunctionManager.registerFunction(new AddMoreFunction.Serializer());
		LootFunctionManager.registerFunction(new WoolColorFunction.Serializer());

		reg("cow_prime");
		reg("cow_regular");
		reg("cow_mooshroom");
		reg("sheep_prime");
		reg("sheep_regular");
		reg("hedgehog");
		reg("hamster");
		reg("ferret");
		reg("rabbit_prime");
		reg("rabbit_regular");
		reg("pig_prime");
		reg("pig_regular");
		reg("peacocks/peacock_blue");
		reg("peacocks/peacock_charcoal");
		reg("peacocks/peacock_opal");
		reg("peacocks/peacock_peach");
		reg("peacocks/peacock_purple");
		reg("peacocks/peacock_taupe");
		reg("peacocks/peacock_white");
		reg("horse");
		reg("goat_prime");
		reg("goat_regular");
		reg("chicken_prime");
		reg("chicken_regular");
		reg("frog");
		reg("toad");
		reg("dart_frog");

	}
	
	
	private static void reg(String name)
	{
		LootTableList.register(new ResourceLocation(Animania.MODID, name));
	}
}
