package com.animania.common.entities;

import java.util.HashSet;
import java.util.Set;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

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
		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		EntityLivingBase entity = null;
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
	public EntityLivingBase getFemale(World world)
	{
		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		EntityLivingBase entity = null;
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
	public EntityLivingBase getChild(World world)
	{
		Class<? extends AnimaniaType> type = types.toArray(new Class[types.size()])[Animania.RANDOM.nextInt(types.size())];
		AnimaniaType[] breeds = type.getEnumConstants();

		EntityLivingBase entity = null;
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
