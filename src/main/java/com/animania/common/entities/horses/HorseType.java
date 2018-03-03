package com.animania.common.entities.horses;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.entities.AnimaniaType;

import net.minecraft.world.World;

public enum HorseType implements AnimaniaType
{
	DRAFT(EntityStallionDraftHorse.class, EntityMareDraftHorse.class, EntityFoalDraftHorse.class);
	
	private Class stallion;
	private Class mare;
	private Class foal;

	private HorseType(Class stallion, Class mare, Class foal)
	{
		this.stallion = stallion;
		this.mare = mare;
		this.foal = foal;
	}

	@Override
	public EntityStallionBase getMale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.stallion.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityStallionBase stallion = null;
		try
		{
			stallion = (EntityStallionBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return stallion;
	}

	@Override
	public EntityMareBase getFemale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.mare.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityMareBase mare = null;
		try
		{
			mare = (EntityMareBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return mare;	
	}

	@Override
	public EntityFoalBase getChild(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.foal.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityFoalBase foal = null;
		try
		{
			foal = (EntityFoalBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return foal;	
	}

	public static HorseType breed(HorseType male, HorseType female)
	{
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			return male;
		else
			return female;
	}

}
