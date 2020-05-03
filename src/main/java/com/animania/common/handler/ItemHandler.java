package com.animania.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.ISpawnable;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemManual;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHandler
{
	public static boolean hasSetEggColors = false;

	public static Item animaniaManual;

	public static Item entityeggrandomanimal;

	public static List<Item> entityEggList = new ArrayList<Item>();
	public static List<ItemStack> resourceTabItems = new ArrayList<ItemStack>();

	public static void preInit()
	{
		ItemHandler.animaniaManual = new ItemManual();

		// Item Entity Eggs
		ItemHandler.entityeggrandomanimal = new ItemEntityEgg("random", new RandomAnimalType(), EntityGender.RANDOM);
	}

	public static void regItemEggColors(World world)
	{
		if (!hasSetEggColors)
		{
			for (Item item : entityEggList)
			{
				if (item instanceof ItemEntityEgg)
				{
					if (item != ItemHandler.entityeggrandomanimal)
					{
						AnimalContainer animal = ((ItemEntityEgg) item).getAnimal();
						EntityLivingBase entity = EntityGender.getEntity(animal.getType(), animal.getGender(), world);

						if (animal.getGender() != EntityGender.RANDOM)
						{
							if (entity != null)
							{
								if (((ISpawnable) entity).usesEggColor())
								{
									ISpawnable ispawnable = (ISpawnable) entity;
									ItemEntityEgg.ANIMAL_USES_COLOR.put(animal, true);
									ItemEntityEgg.ANIMAL_COLOR_PRIMARY.put(animal, ispawnable.getPrimaryEggColor());
									ItemEntityEgg.ANIMAL_COLOR_SECONDARY.put(animal, ispawnable.getSecondaryEggColor());

									try
									{
										EntityList.ENTITY_EGGS.put(EntityEggHandler.getEntryFromEntity(entity).getRegistryName(), new EntityEggInfo(EntityEggHandler.getEntryFromEntity(entity).getRegistryName(), ispawnable.getPrimaryEggColor(), ispawnable.getSecondaryEggColor()));
									} catch (Exception e)
									{
										Animania.LOGGER.warn("Failed to insert entity egg for " + EntityEggHandler.getEntryFromEntity(entity).getRegistryName());
									}
								} else
									ItemEntityEgg.ANIMAL_USES_COLOR.put(animal, false);

							}
						}
					}

				}
			}

			hasSetEggColors = true;
		}
	}

	public static void addItemToTab(ItemStack stack)
	{
		resourceTabItems.add(stack);
	}

}
