package com.animania.addons.catsdogs.common.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.animania.addons.catsdogs.common.entity.canids.DogType;
import com.animania.addons.catsdogs.common.entity.felids.CatType;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.entity.merchant.villager.VillagerEntity.ListItemForEmeralds;
import net.minecraft.entity.merchant.villager.VillagerEntity.PriceInfo;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.VillagerRegistry.VillagerCareer;

public class CatsDogsVillagerProfessions
{
	public static VillagerProfession petSeller;

	@SubscribeEvent
	public static void register(RegistryEvent.Register<VillagerProfession> event)
	{
		petSeller = new VillagerProfession("animania:pet_seller", "animania:textures/entity/villager/pet_seller.png", "animania:textures/entity/villager/pet_seller_zombie.png");

		VillagerCareer c = new VillagerCareer(petSeller, "pet_merchant");

		c.addTrade(1, trades(CatType.AMERICAN_SHORTHAIR, 10, 20));
		c.addTrade(2, trades(CatType.RAGDOLL, 10, 20));
		c.addTrade(3, trades(CatType.NORWEGIAN, 10, 20));
		c.addTrade(2, trades(CatType.ASIATIC, 15, 25));
		c.addTrade(3, trades(CatType.EXOTIC, 15, 25));
		c.addTrade(2, trades(CatType.TABBY, 15, 25));
		c.addTrade(3, trades(CatType.SIAMESE, 25, 35));

		c.addTrade(1, trades(DogType.BLOOD_HOUND, 15, 30));
		c.addTrade(2, trades(DogType.CHIHUAHUA, 20, 30));
		c.addTrade(3, trades(DogType.COLLIE, 20, 30));
		c.addTrade(1, trades(DogType.CORGI, 15, 25));
		c.addTrade(2, trades(DogType.DACHSHUND, 10, 20));
		c.addTrade(3, trades(DogType.GERMAN_SHEPHERD, 20, 30));
		c.addTrade(1, trades(DogType.GREAT_DANE, 20, 30));
		c.addTrade(2, trades(DogType.GREYHOUND, 20, 30));
		c.addTrade(3, trades(DogType.HUSKY, 20, 25));
		c.addTrade(1, trades(DogType.LABRADOR, 20, 30));
		c.addTrade(2, trades(DogType.POMERANIAN, 15, 25));
		c.addTrade(3, trades(DogType.POODLE, 15, 25));
		c.addTrade(1, trades(DogType.PUG, 15, 25));

		event.getRegistry().register(petSeller);
	}

	private static ListItemForEmeralds t(Item i, int a, int b)
	{
		PriceInfo p = new PriceInfo(a, b);
		ListItemForEmeralds li = new ListItemForEmeralds(i, p);
		return li;
	}

	private static ListItemForEmeralds[] trades(AnimaniaType type, int a, int b)
	{
		ListItemForEmeralds[] array = new ListItemForEmeralds[3];

		array[0] = t(ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(type, EntityGender.MALE)), a, b);
		array[1] = t(ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(type, EntityGender.FEMALE)), a, b);
		array[2] = t(ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(type, EntityGender.CHILD)), a + a / 2, b + b / 2);

		return array;
	}

	private static ListItemForEmeralds[] combine(ListItemForEmeralds[]... list)
	{
		List<ListItemForEmeralds> l = new ArrayList<ListItemForEmeralds>();
		for (ListItemForEmeralds[] ll : list)
			l.addAll(Arrays.asList(ll));

		Collections.shuffle(l);
		return l.toArray(new ListItemForEmeralds[l.size()]);
	}
}
