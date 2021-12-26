package com.animania.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.ISpawnable;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.helper.RegistryHelper.RItem;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemManual;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ItemHandler
{
	public static boolean hasSetEggColors = false;

	public static RItem animaniaManual;

	public static RItem entityeggrandomanimal;

	public static List<RItem> entityEggList = new ArrayList<RItem>();
	public static List<ItemStack> resourceTabItems = new ArrayList<>();

	public static void preInit()
	{
		ItemHandler.animaniaManual = new ItemManual();

		// Item Entity Eggs
		ItemHandler.entityeggrandomanimal = new ItemEntityEgg("random", new RandomAnimalType(), EntityGender.RANDOM);
	}

	public static void regItemEggColors(Level level)
	{
		if (!hasSetEggColors)
		{
			for (RItem item : entityEggList)
			{
				if (item instanceof ItemEntityEgg && item != ItemHandler.entityeggrandomanimal)
				{
					AnimalContainer animal = ((ItemEntityEgg) item).getAnimal();
					LivingEntity entity = EntityGender.getEntity(animal.getType(), animal.getGender(), level);

					if ((animal.getGender() != EntityGender.RANDOM) && (entity != null))
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
							}
							catch (Exception e)
							{
								Animania.LOGGER.warn("Failed to insert entity egg for " + entity);
							}
						}
						else
							ItemEntityEgg.ANIMAL_USES_COLOR.put(animal, false);

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
