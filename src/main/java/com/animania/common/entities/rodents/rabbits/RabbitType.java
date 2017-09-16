package com.animania.common.entities.rodents.rabbits;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.entities.AnimaniaType;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum RabbitType implements AnimaniaType
{

	LOP(EntityRabbitBuckLop.class, EntityRabbitDoeLop.class, EntityRabbitKitLop.class, null),
	REX(EntityRabbitBuckRex.class, EntityRabbitDoeRex.class, EntityRabbitKitRex.class, null),
	DUTCH(EntityRabbitBuckDutch.class, EntityRabbitDoeDutch.class, EntityRabbitKitDutch.class, null),
	HAVANA(EntityRabbitBuckHavana.class, EntityRabbitDoeHavana.class, EntityRabbitKitHavana.class, null),
	NEW_ZEALAND(EntityRabbitBuckNewZealand.class, EntityRabbitDoeNewZealand.class, EntityRabbitKitNewZealand.class, null),
	JACK(EntityRabbitBuckJack.class, EntityRabbitDoeJack.class, EntityRabbitKitJack.class, null),
	COTTONTAIL(EntityRabbitBuckCottontail.class, EntityRabbitDoeCottontail.class, EntityRabbitKitCottontail.class, null),
	CHINCHILLA(EntityRabbitBuckChinchilla.class, EntityRabbitDoeChinchilla.class, EntityRabbitKitChinchilla.class, null);
	
	
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
	
	public void setAchievement(StatBase achievement)
	{
		this.achievement = achievement;
	}

	
	@Override
	public EntityRabbitBuckBase getMale(World world)
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
		EntityRabbitBuckBase male = null;
		try
		{
			male = (EntityRabbitBuckBase) constructor.newInstance(world);
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
