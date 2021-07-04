package com.animania.common.entities;

import java.util.HashSet;
import java.util.Set;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class RandomAnimalType implements AnimaniaType
{

	private static Set<Class<? extends AnimaniaType>> types = new HashSet<Class<? extends AnimaniaType>>();

	public static void addType(Class<? extends AnimaniaType> type)
	{
		types.add(type);
	}

	@Override
	public LivingEntity getMale(World world)
	{
		if (types.size() == 0)
			return null;

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		LivingEntity entity = null;
		while (entity == null)
		{
			entity = breeds[Animania.RANDOM.nextInt(breeds.length)].getMale(world);
			if (entity == null)
			{
				type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
				breeds = type.getEnumConstants();
			}
		}

		return entity;

	}

	@Override
	public LivingEntity getFemale(World world)
	{
		if (types.size() == 0)
			return null;

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		LivingEntity entity = null;
		while (entity == null)
		{
			entity = breeds[Animania.RANDOM.nextInt(breeds.length)].getFemale(world);
			if (entity == null)
			{
				type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
				breeds = type.getEnumConstants();
			}
		}

		return entity;
	}

	@Override
	public LivingEntity getChild(World world)
	{
		if (types.size() == 0)
			return null;

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		LivingEntity entity = null;
		while (entity == null)
		{
			entity = breeds[Animania.RANDOM.nextInt(breeds.length)].getChild(world);
			if (entity == null)
			{
				type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
				breeds = type.getEnumConstants();
			}
		}

		return entity;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":random";
	}

}
