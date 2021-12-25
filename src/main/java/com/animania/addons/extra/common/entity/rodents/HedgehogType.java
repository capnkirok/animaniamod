package com.animania.addons.extra.common.entity.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public enum HedgehogType implements AnimaniaType
{
	NORMAL(EntityHedgehog.class), ALBINO(EntityHedgehogAlbino.class);

	private Class male;

	private HedgehogType(Class male)
	{
		this.male = male;
	}

	@Override
	public EntityHedgehogBase getMale(Level level)
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
		EntityHedgehogBase hedgehog = null;
		try
		{
			hedgehog = (EntityHedgehogBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return hedgehog;
	}

	@Override
	public LivingEntity getFemale(Level level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LivingEntity getChild(Level level)
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
