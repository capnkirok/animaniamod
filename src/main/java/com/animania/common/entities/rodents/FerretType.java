package com.animania.common.entities.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.entities.AnimaniaType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum FerretType implements AnimaniaType
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
	
	@Override
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

	@Override
	public EntityLivingBase getFemale(World world)
	{
		return null;
	}

	@Override
	public EntityLivingBase getChild(World world)
	{
		return null;
	}
	
	public StatBase getAchievement()
	{
		return this.achievement;
	}
	
}
