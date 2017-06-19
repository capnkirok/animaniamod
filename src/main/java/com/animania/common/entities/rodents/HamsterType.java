package com.animania.common.entities.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.amphibians.EntityAmphibian;

import net.minecraft.entity.EntityLivingBase;
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
	public EntityLivingBase getFemale(World world)
	{
		return null;
	}

	@Override
	public EntityLivingBase getChild(World world)
	{
		return null;
	}

}
