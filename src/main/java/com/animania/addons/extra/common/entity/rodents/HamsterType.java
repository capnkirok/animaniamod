package com.animania.addons.extra.common.entity.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public enum HamsterType implements AnimaniaType
{
	STANDARD(EntityHamster.class);

	private Class male;
	private HamsterType(Class male)
	{
		this.male = male;
	}
	
	@Override
	public EntityHamster getMale(Level world)
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
	public LivingEntity getFemale(Level world)
	{
		return null;
	}

	@Override
	public LivingEntity getChild(Level world)
	{
		return null;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":hamster";
	}
}
