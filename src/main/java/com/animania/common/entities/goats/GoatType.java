package com.animania.common.entities.goats;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.PigType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum GoatType implements AnimaniaType
{
	ALPINE(EntityBuckAlpine.class, null, null, null),
	NIGERIAN_DWARF(null, null, null, null),
	KIKO(null, null, null, null),
	FAINTING(null, null, null, null),
	KINDER(null, null, null, null),
	PYGMY(null, null, null, null),
	ANGORA(null, null, null, null);

	private Class male;
	private Class female;
	private Class child;
	private StatBase achievement;
	
	
	private GoatType(Class male, Class female, Class child, StatBase achievement)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.achievement = achievement;
	}
	
	@Override
	public EntityBuckBase getMale(World world)
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
		EntityBuckBase male = null;
		try
		{
			male = (EntityBuckBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityDoeBase getFemale(World world)
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
		EntityDoeBase female = null;
		try
		{
			female = (EntityDoeBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;
	}

	@Override
	public EntityKidBase getChild(World world)
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
		EntityKidBase child = null;
		try
		{
			child = (EntityKidBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;
	}

	public static GoatType breed(GoatType male, GoatType female)
	{
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			return male;
		else
			return female;
	}

	public StatBase getAchievement()
	{
		return achievement;
	}

	
}
