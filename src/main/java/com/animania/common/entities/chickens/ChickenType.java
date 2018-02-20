package com.animania.common.entities.chickens;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.entities.AnimaniaType;

import net.minecraft.world.World;

public enum ChickenType implements AnimaniaType
{
	
	LEGHORN(EntityRoosterLeghorn.class, EntityHenLeghorn.class, EntityChickLeghorn.class),
	ORPINGTON(EntityRoosterOrpington.class, EntityHenOrpington.class, EntityChickOrpington.class),
	PLYMOUTH_ROCK(EntityRoosterPlymouthRock.class, EntityHenPlymouthRock.class, EntityChickPlymouthRock.class),
	RHODE_ISLAND_RED(EntityRoosterRhodeIslandRed.class, EntityHenRhodeIslandRed.class, EntityChickRhodeIslandRed.class),
	WYANDOTTE(EntityRoosterWyandotte.class, EntityHenWyandotte.class, EntityChickWyandotte.class);

	private Class male;
	private Class female;
	private Class child;
	
	private ChickenType(Class male, Class female, Class child)
	{
		this.male = male;
		this.female = female;
		this.child = child;
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
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			return male;
		else
			return female;
	}

}
