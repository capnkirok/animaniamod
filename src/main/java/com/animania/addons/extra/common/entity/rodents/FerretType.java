package com.animania.addons.extra.common.entity.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import LivingEntity;

public enum FerretType implements AnimaniaType
{

	GREY(EntityFerretGrey.class),
	WHITE(EntityFerretWhite.class);
	
	
	private Class male;
	
	private FerretType(Class male)
	{
		this.male = male;
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
	public LivingEntity getFemale(World world)
	{
		return null;
	}

	@Override
	public LivingEntity getChild(World world)
	{
		return null;
	}
	
	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":ferret";
	}
	
}
