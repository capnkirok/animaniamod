package com.animania.addons.catsdogs.common.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.animania.Animania;
import com.animania.addons.catsdogs.common.entity.cats.CatType;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.EntityGender;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.entity.passive.EntityVillager.ListItemForEmeralds;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

@EventBusSubscriber(modid = Animania.MODID)
public class CatsDogsVillagerProfessions
{
	public static VillagerProfession petSeller;
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<VillagerProfession> event)
	{
		petSeller = new VillagerProfession("animania:pet_seller", "animania:textures/entity/villager/pet_seller.png", "animania:textures/entity/villager/pet_seller_zombie.png");

		VillagerCareer c = new VillagerCareer(petSeller, "pet_merchant");
				
		c.addTrade(1, combine(trades(CatType.AMERICAN_SHORTHAIR, 10, 20), trades(CatType.RAGDOLL, 10, 20), trades(CatType.NORWEGIAN, 10, 20), trades(CatType.ASIATIC, 15, 25), trades(CatType.EXOTIC, 15, 25), trades(CatType.TABBY, 15, 25), trades(CatType.SIAMESE, 25, 35)));

		event.getRegistry().register(petSeller);
	}
	
	private static ListItemForEmeralds t(Item i, int a, int b)
	{
		PriceInfo p = new PriceInfo(a,b);
		ListItemForEmeralds li = new ListItemForEmeralds(i, p);
		return li;
	}
	
	private static ListItemForEmeralds[] trades(AnimaniaType type, int a, int b)
	{
		ListItemForEmeralds[] array = new ListItemForEmeralds[3];
		
		array[0] = t(ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(type, EntityGender.MALE)), a, b);
		array[1] = t(ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(type, EntityGender.FEMALE)), a, b);
		array[2] = t(ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(type, EntityGender.CHILD)), a + a/4, b + b/4);

		return array;
	}
	
	private static ListItemForEmeralds[] combine(ListItemForEmeralds[]... list)
	{
		List<ListItemForEmeralds> l = new ArrayList<ListItemForEmeralds>();
		for(ListItemForEmeralds[] ll : list)
			l.addAll(Arrays.asList(ll));
		
		Collections.shuffle(l);
		return l.toArray(new ListItemForEmeralds[l.size()]);
	}
}
