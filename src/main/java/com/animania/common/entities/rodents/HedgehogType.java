package com.animania.common.entities.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.common.entities.AnimaniaType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public enum HedgehogType implements AnimaniaType
{
	NORMAL(EntityHedgehog.class),
	ALBINO(EntityHedgehogAlbino.class);

	private Class male;
	
	private HedgehogType(Class male)
	{
		this.male = male;
	}
	
	@Override
	public EntityHedgehogBase getMale(World world)
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
		EntityHedgehogBase hedgehog = null;
		try
		{
			hedgehog = (EntityHedgehogBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return hedgehog;
	}

	@Override
	public EntityLivingBase getFemale(World world)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityLivingBase getChild(World world)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
