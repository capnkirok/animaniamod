package com.animania.addons.extra.common.entity.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.entity.LivingEntity;

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
	public EntityFerretBase getMale(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.male.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityFerretBase ferret = null;
		try
		{
			ferret = (EntityFerretBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return ferret;
	}

	@Override
	public LivingEntity getFemale(Level level)
	{
		return null;
	}

	@Override
	public LivingEntity getChild(Level level)
	{
		return null;
	}
	
	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":ferret";
	}
	
}
