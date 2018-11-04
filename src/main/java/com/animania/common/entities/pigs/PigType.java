package com.animania.common.entities.pigs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.common.entities.AnimaniaType;

import net.minecraft.world.World;

public enum PigType implements AnimaniaType
{
	DUROC(EntityHogDuroc.class, EntitySowDuroc.class, EntityPigletDuroc.class, true),
	HAMPSHIRE(EntityHogHampshire.class, EntitySowHampshire.class, EntityPigletHampshire.class, true),
	LARGE_BLACK(EntityHogLargeBlack.class, EntitySowLargeBlack.class, EntityPigletLargeBlack.class, true),
	LARGE_WHITE(EntityHogLargeWhite.class, EntitySowLargeWhite.class, EntityPigletLargeWhite.class, false),
	OLD_SPOT(EntityHogOldSpot.class, EntitySowOldSpot.class, EntityPigletOldSpot.class, true),
	YORKSHIRE(EntityHogYorkshire.class, EntitySowYorkshire.class, EntityPigletYorkshire.class, false);


	private Class hog;
	private Class sow;
	private Class piglet;
	public boolean isPrime;

	private PigType(Class hog, Class sow, Class piglet, boolean isPrime)
	{
		this.hog = hog;
		this.sow = sow;
		this.piglet = piglet;
		this.isPrime = isPrime;
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
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

	
}
