package com.animania.addons.extra.common.entity.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public enum HamsterType implements AnimaniaType
{
	STANDARD(EntityHamster.class);

	private Class male;
	private HamsterType(Class male)
	{
		this.male = male;
	}
	
	@Override
	public EntityHamster getMale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.male.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityHamster hamster = null;
		try
		{
			hamster = (EntityHamster) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return hamster;
	}

	@Override
	public LivingEntity getFemale(World world)
	{
		return null;
	}

	@Override
	public LivingEntity getChild(World world)
	{
		return null;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":hamster";
	}
}
