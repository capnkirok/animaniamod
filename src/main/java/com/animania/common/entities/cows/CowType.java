package com.animania.common.entities.cows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.entities.AnimaniaType;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum CowType implements AnimaniaType
{
	ANGUS(EntityBullAngus.class, EntityCowAngus.class, EntityCalfAngus.class),
	FRIESIAN(EntityBullFriesian.class, EntityCowFriesian.class, EntityCalfFriesian.class),
	HEREFORD(EntityBullHereford.class, EntityCowHereford.class, EntityCalfHereford.class),
	HOLSTEIN(EntityBullHolstein.class, EntityCowHolstein.class, EntityCalfHolstein.class),
	LONGHORN(EntityBullLonghorn.class, EntityCowLonghorn.class, EntityCalfLonghorn.class);


	private Class bull;
	private Class cow;
	private Class calf;

	private CowType(Class bull, Class cow, Class calf)
	{
		this.bull = bull;
		this.cow = cow;
		this.calf = calf;
	}

	public void setAchievement(StatBase achievement)
	{
		this.achievement = achievement;
	}

	@Override
	public EntityBullBase getMale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.bull.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityBullBase bull = null;
		try
		{
			bull = (EntityBullBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return bull;
	}

	@Override
	public EntityCowBase getFemale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.cow.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityCowBase cow = null;
		try
		{
			cow = (EntityCowBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return cow;	
	}

	@Override
	public EntityCalfBase getChild(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.calf.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityCalfBase calf = null;
		try
		{
			calf = (EntityCalfBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return calf;	
	}

	public static CowType breed(CowType male, CowType female)
	{
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			return male;
		else
			return female;
	}



}
