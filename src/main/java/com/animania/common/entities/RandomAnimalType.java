package com.animania.common.entities;

import java.util.HashSet;
import java.util.Set;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class RandomAnimalType implements AnimaniaType
{

	private static Set<Class<? extends AnimaniaType>> types = new HashSet<>();

	public static void addType(Class<? extends AnimaniaType> type)
	{
		types.add(type);
	}

	@Override
	public LivingEntity getMale(Level level)
	{
		if (types.size() == 0)
			return null;

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		LivingEntity entity = null;
		while (entity == null)
		{
			entity = breeds[Animania.RANDOM.nextInt(breeds.length)].getMale(level);
			if (entity == null)
			{
				type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
				breeds = type.getEnumConstants();
			}
		}

		return entity;

	}

	@Override
	public LivingEntity getFemale(Level level)
	{
		if (types.size() == 0)
			return null;

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		LivingEntity entity = null;
		while (entity == null)
		{
			entity = breeds[Animania.RANDOM.nextInt(breeds.length)].getFemale(level);
			if (entity == null)
			{
				type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
				breeds = type.getEnumConstants();
			}
		}

		return entity;
	}

	@Override
	public LivingEntity getChild(Level level)
	{
		if (types.size() == 0)
			return null;

		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		LivingEntity entity = null;
		while (entity == null)
		{
			entity = breeds[Animania.RANDOM.nextInt(breeds.length)].getChild(level);
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
