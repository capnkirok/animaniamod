package com.animania.common.entities;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class RandomAnimalType implements AnimaniaType
{

	private static Set<Class<? extends AnimaniaType>> types = new HashSet<Class<? extends AnimaniaType>>();

	public static void addType(Class<? extends AnimaniaType> type)
	{
		types.add(type);
	}

	@Override
	public EntityLivingBase getMale(World world)
	{
		Random rand = new Random();

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[rand.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		EntityLivingBase entity = null;
		while (entity == null)
		{
			entity = breeds[rand.nextInt(breeds.length)].getMale(world);
			if (entity == null)
			{
				type = types.toArray(new Class[types.size()])[rand.nextInt(types.size())];
				breeds = type.getEnumConstants();
			}
		}

		return entity;

	}

	@Override
	public EntityLivingBase getFemale(World world)
	{
		Random rand = new Random();

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[rand.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		EntityLivingBase entity = null;
		while (entity == null)
		{
			entity = breeds[rand.nextInt(breeds.length)].getFemale(world);
			if (entity == null)
			{
				type = types.toArray(new Class[types.size()])[rand.nextInt(types.size())];
				breeds = type.getEnumConstants();
			}
		}

		return entity;
	}

	@Override
	public EntityLivingBase getChild(World world)
	{
		Random rand = new Random();

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[rand.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		EntityLivingBase entity = null;
		while (entity == null)
		{
			entity = breeds[rand.nextInt(breeds.length)].getChild(world);
			if (entity == null)
			{
				type = types.toArray(new Class[types.size()])[rand.nextInt(types.size())];
				breeds = type.getEnumConstants();
			}
		}

		return entity;
	}

}
