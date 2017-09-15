package com.animania.common.entities.pigs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.entities.AnimaniaType;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum PigType implements AnimaniaType
{
	DUROC(EntityHogDuroc.class, EntitySowDuroc.class, EntityPigletDuroc.class),
	HAMPSHIRE(EntityHogHampshire.class, EntitySowHampshire.class, EntityPigletHampshire.class),
	LARGE_BLACK(EntityHogLargeBlack.class, EntitySowLargeBlack.class, EntityPigletLargeBlack.class),
	LARGE_WHITE(EntityHogLargeWhite.class, EntitySowLargeWhite.class, EntityPigletLargeWhite.class),
	OLD_SPOT(EntityHogOldSpot.class, EntitySowOldSpot.class, EntityPigletOldSpot.class),
	YORKSHIRE(EntityHogYorkshire.class, EntitySowYorkshire.class, EntityPigletYorkshire.class);


	private Class hog;
	private Class sow;
	private Class piglet;

	private PigType(Class hog, Class sow, Class piglet)
	{
		this.hog = hog;
		this.sow = sow;
		this.piglet = piglet;
	}
	
	

	public void setAchievement(StatBase achievement)
	{
		this.achievement = achievement;
	}



	@Override
	public EntityHogBase getMale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.hog.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityHogBase bull = null;
		try
		{
			bull = (EntityHogBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return bull;
	}

	@Override
	public EntitySowBase getFemale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.sow.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntitySowBase cow = null;
		try
		{
			cow = (EntitySowBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return cow;	
	}

	@Override
	public EntityPigletBase getChild(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.piglet.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityPigletBase calf = null;
		try
		{
			calf = (EntityPigletBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return calf;	
	}

	public static PigType breed(PigType male, PigType female)
	{
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			return male;
		else
			return female;
	}

	
}
