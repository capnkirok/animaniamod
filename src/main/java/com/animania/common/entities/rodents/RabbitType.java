package com.animania.common.entities.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeBase;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitBase;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitLop;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum RabbitType implements AnimaniaType
{

	LOP(EntityRabbitBuckLop.class, EntityRabbitDoeLop.class, EntityRabbitKitLop.class, null);
	//REX(EntityRabbitRex.class, null),
	//DUTCH(EntityRabbitDutch.class, null),
	//HAVANA(EntityRabbitHavana.class, null),
	//NEW_ZEALAND(EntityRabbitNewZealand.class, null),
	//JACK(EntityRabbitJack.class, null),
	//COTTONTAIL(EntityRabbitCottontail.class, null),
	//CHINCHILLA(EntityRabbitChinchilla.class, null);
	
	
	private Class male;
	private Class female;
	private Class child;
	private StatBase achievement;
	
	private RabbitType(Class male, Class female, Class child, StatBase achievement)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.achievement = achievement;
	}
	
	@Override
	public EntityAnimaniaRabbit getMale(World world)
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
		EntityAnimaniaRabbit male = null;
		try
		{
			male = (EntityAnimaniaRabbit) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityRabbitDoeBase getFemale(World world)
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
		EntityRabbitDoeBase female = null;
		try
		{
			female = (EntityRabbitDoeBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;
	}

	@Override
	public EntityRabbitKitBase getChild(World world)
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
		EntityRabbitKitBase child = null;
		try
		{
			child = (EntityRabbitKitBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;
	}

	public static RabbitType breed(RabbitType male, RabbitType female)
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
