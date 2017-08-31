package com.animania.common.entities.rodents;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.rodents.rabbits.EntityRabbitChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDutch;
import com.animania.common.entities.rodents.rabbits.EntityRabbitHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitNewZealand;
import com.animania.common.entities.rodents.rabbits.EntityRabbitRex;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum RabbitType implements AnimaniaType
{

	LOP(EntityRabbitLop.class, null),
	REX(EntityRabbitRex.class, null),
	DUTCH(EntityRabbitDutch.class, null),
	HAVANA(EntityRabbitHavana.class, null),
	NEW_ZEALAND(EntityRabbitNewZealand.class, null),
	JACK(EntityRabbitJack.class, null),
	COTTONTAIL(EntityRabbitCottontail.class, null),
	CHINCHILLA(EntityRabbitChinchilla.class, null);
	
	
	private Class male;
	private StatBase achievement;
	
	private RabbitType(Class male, StatBase achievement)
	{
		this.male = male;
		this.achievement = achievement;
	}
	
	@Override
	public EntityRabbitBase getMale(World world)
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
		EntityRabbitBase Rabbit = null;
		try
		{
			Rabbit = (EntityRabbitBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return Rabbit;
	}

	@Override
	public EntityLivingBase getFemale(World world)
	{
		return null;
	}

	@Override
	public EntityLivingBase getChild(World world)
	{
		return null;
	}
	
	public StatBase getAchievement()
	{
		return this.achievement;
	}
	
}
