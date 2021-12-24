package com.animania.addons.farm.common.handler;

import com.animania.Animania;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class FarmAddonLootTableHandler
{
	public static void preInit()
	{
		reg("cow_prime");
		reg("cow_regular");
		reg("cow_mooshroom");
		reg("sheep_prime");
		reg("sheep_regular");
		reg("pig_prime");
		reg("pig_regular");
		reg("horse");
		reg("goat_prime");
		reg("goat_regular");
		reg("chicken_prime");
		reg("chicken_regular");
	}

	private static void reg(String name)
	{
		LootTableList.register(new ResourceLocation("farm/" + Animania.MODID, name));
	}
}
