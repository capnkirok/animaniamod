package com.animania.common.entities.sheep;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.entities.AnimaniaType;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum SheepType implements AnimaniaType
{
	DORPER(EntityRamDorper.class, EntityEweDorper.class, EntityLambDorper.class, null, true),
	DORSET(EntityRamDorset.class, EntityEweDorset.class, EntityLambDorset.class, null, true),
	FRIESIAN(EntityRamFriesian.class, EntityEweFriesian.class, EntityLambFriesian.class, null, false),
	JACOB(EntityRamJacob.class, EntityEweJacob.class, EntityLambJacob.class, null, false),
	MERINO(EntityRamMerino.class, EntityEweMerino.class, EntityLambMerino.class, null, false),
	SUFFOLK(EntityRamSuffolk.class, EntityEweSuffolk.class, EntityLambSuffolk.class, null, true);
	
	private Class male;
	private Class female;
	private Class child;
	private StatBase achievement;
	public boolean isPrime;
	
	private SheepType(Class male, Class female, Class child, StatBase achievement, boolean prime)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.achievement = achievement;
		this.isPrime = prime;
	}
	
	@Override
	public EntityRamBase getMale(World world)
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
		EntityRamBase male = null;
		try
		{
			male = (EntityRamBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityEweBase getFemale(World world)
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
		EntityEweBase female = null;
		try
		{
			female = (EntityEweBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;
	}

	@Override
	public EntityLambBase getChild(World world)
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
		EntityLambBase child = null;
		try
		{
			child = (EntityLambBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;
	}

	public static SheepType breed(SheepType male, SheepType female)
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
