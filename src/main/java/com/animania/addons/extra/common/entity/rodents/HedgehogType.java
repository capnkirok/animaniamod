package com.animania.addons.extra.common.entity.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.entity.LivingEntity;
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
	public LivingEntity getFemale(World world)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LivingEntity getChild(World world)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":hedgehog";
	}

}
