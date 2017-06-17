package com.animania.common.entities.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.common.AnimaniaAchievements;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum FerretType
{

	GREY(EntityFerretGrey.class, AnimaniaAchievements.GreyFerret),
	WHITE(EntityFerretWhite.class, AnimaniaAchievements.WhiteFerret);
	
	
	private Class male;
	private StatBase achievement;
	
	private FerretType(Class male, StatBase achievement)
	{
		this.male = male;
		this.achievement = achievement;
	}
	
	public static Class getMale(FerretType type)
	{
		return type.male;
	}
	
	public EntityFerretBase getMale(World world)
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
		EntityFerretBase ferret = null;
		try
		{
			ferret = (EntityFerretBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return ferret;
	}
	
	public StatBase getAchievement()
	{
		return this.achievement;
	}
	
}
