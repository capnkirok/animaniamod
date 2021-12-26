package com.animania.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemManual;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemHandler
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Animania.MODID);

	public static boolean hasSetEggColors = false;

	public static RegistryObject<Item> animaniaManual;
	public static RegistryObject<Item> entityeggrandomanimal;

	public static List<Item> entityEggList = new ArrayList<Item>();
	public static List<ItemStack> resourceTabItems = new ArrayList<>();

	public static void preInit()
	{
		ItemHandler.animaniaManual = ITEMS.register("animania_manual", () -> new ItemManual());
		ItemHandler.entityeggrandomanimal = ITEMS.register("entity_egg_random", () -> new ItemEntityEgg("random", new RandomAnimalType(), EntityGender.RANDOM));
	}

	// TODO: remove
	// public static void regItemEggColors(Level level)
	// {
	// if (!hasSetEggColors)
	// {
	// for (Item item : entityEggList)
	// {
	// if (item instanceof ItemEntityEgg && item !=
	// ItemHandler.entityeggrandomanimal)
	// {
	// AnimalContainer animal = ((ItemEntityEgg) item).getAnimal();
	// LivingEntity entity = EntityGender.getEntity(animal.getType(),
	// animal.getGender(), level);
	//
	// if ((animal.getGender() != EntityGender.RANDOM) && (entity != null))
	// {
	// if (((ISpawnable) entity).usesEggColor())
	// {
	// ISpawnable ispawnable = (ISpawnable) entity;
	// ItemEntityEgg.ANIMAL_USES_COLOR.put(animal, true);
	// ItemEntityEgg.ANIMAL_COLOR_PRIMARY.put(animal,
	// ispawnable.getPrimaryEggColor());
	// ItemEntityEgg.ANIMAL_COLOR_SECONDARY.put(animal,
	// ispawnable.getSecondaryEggColor());
	//
	// try
	// {
	// EntityList.ENTITY_EGGS.put(EntityEggHandler.getEntryFromEntity(entity).getRegistryName(),
	// new
	// EntityEggInfo(EntityEggHandler.getEntryFromEntity(entity).getRegistryName(),
	// ispawnable.getPrimaryEggColor(), ispawnable.getSecondaryEggColor()));
	// }
	// catch (Exception e)
	// {
	// Animania.LOGGER.warn("Failed to insert entity egg for " + entity);
	// }
	// }
	// else
	// ItemEntityEgg.ANIMAL_USES_COLOR.put(animal, false);
	//
	// }
	// }
	// }
	//
	// hasSetEggColors = true;
	// }
	// }

	// Todo: see where this is used, otherwise remove
	public static void addItemToTab(ItemStack stack)
	{
		resourceTabItems.add(stack);
	}

}
