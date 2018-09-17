package com.animania.addons.catsdogs.common.entity.cats;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.cows.EntityBullMooshroom;
import com.animania.common.entities.cows.EntityCalfBase;
import com.animania.common.entities.cows.EntityCalfMooshroom;
import com.animania.common.entities.cows.EntityCowBase;
import com.animania.common.entities.cows.EntityCowMooshroom;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public enum CatType implements AnimaniaType
{

	TODO(null, null, null);

	private Class male;
	private Class female;
	private Class child;

	private CatType(Class male, Class female, Class child)
	{
		this.male = male;
		this.female = female;
		this.child = child;
	}

	@Override
	public EntityTomBase getMale(World world)
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
		EntityTomBase bull = null;
		try
		{
			bull = (EntityTomBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return bull;
	}

	@Override
	public EntityQueenBase getFemale(World world)
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
		EntityQueenBase cow = null;
		try
		{
			cow = (EntityQueenBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return cow;	
	}

	@Override
	public EntityKittenBase getChild(World world)
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
		EntityKittenBase calf = null;
		try
		{
			calf = (EntityKittenBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return calf;	
	}

	public static CatType breed(CatType male, CatType female)
	{
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			return male;
		else
			return female;
	}

}
