package com.animania.common.entities.chickens;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.World;

public enum ChickenType implements AnimaniaType
{
	
	LEGHORN(EntityRoosterLeghorn.class, EntityHenLeghorn.class, EntityChickLeghorn.class, false),
	ORPINGTON(EntityRoosterOrpington.class, EntityHenOrpington.class, EntityChickOrpington.class, true),
	PLYMOUTH_ROCK(EntityRoosterPlymouthRock.class, EntityHenPlymouthRock.class, EntityChickPlymouthRock.class, true),
	RHODE_ISLAND_RED(EntityRoosterRhodeIslandRed.class, EntityHenRhodeIslandRed.class, EntityChickRhodeIslandRed.class, true),
	WYANDOTTE(EntityRoosterWyandotte.class, EntityHenWyandotte.class, EntityChickWyandotte.class, true);

	private Class male;
	private Class female;
	private Class child;
	public boolean isPrime;
	
	private ChickenType(Class male, Class female, Class child, boolean isPrime)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.isPrime = isPrime;
	}
	
	@Override
	public EntityRoosterBase getMale(World world)
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
		EntityRoosterBase male = null;
		try
		{
			male = (EntityRoosterBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityHenBase getFemale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.female.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityHenBase female = null;
		try
		{
			female = (EntityHenBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;	
	}

	@Override
	public EntityChickBase getChild(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.child.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityChickBase child = null;
		try
		{
			child = (EntityChickBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;	
	}

	public static ChickenType breed(ChickenType male, ChickenType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

}
