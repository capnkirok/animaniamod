package com.animania.common.entities.peacocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.cows.EntityCalfBase;
import com.animania.common.entities.cows.EntityCowBase;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum PeacockType implements AnimaniaType
{
	BLUE(EntityPeacockBlue.class, EntityPeafowlBlue.class, EntityPeachickBlue.class),
	WHITE(EntityPeacockWhite.class, EntityPeafowlWhite.class, EntityPeachickWhite.class),
	TAUPE(EntityPeacockTaupe.class, EntityPeafowlTaupe.class, EntityPeachickTaupe.class),
	PURPLE(EntityPeacockPurple.class, EntityPeafowlPurple.class, EntityPeachickPurple.class),
	PEACH(EntityPeacockPeach.class, EntityPeafowlPeach.class, EntityPeachickPeach.class),
	OPAL(EntityPeacockOpal.class, EntityPeafowlOpal.class, EntityPeachickOpal.class),
	CHARCOAL(EntityPeacockCharcoal.class, EntityPeafowlCharcoal.class, EntityPeachickCharcoal.class);
	
	private Class male;
	private Class female;
	private Class child;
	
	private PeacockType(Class male, Class female, Class child)
	{
		this.male = male;
		this.female = female;
		this.child = child;
	}
	
	
	
	public void setAchievement(StatBase achievement)
	{
		this.achievement = achievement;
	}



	@Override
	public EntityPeacockBase getMale(World world)
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
		EntityPeacockBase male = null;
		try
		{
			male = (EntityPeacockBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityPeafowlBase getFemale(World world)
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
		EntityPeafowlBase female = null;
		try
		{
			female = (EntityPeafowlBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;	
	}

	@Override
	public EntityPeachickBase getChild(World world)
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
		EntityPeachickBase child = null;
		try
		{
			child = (EntityPeachickBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;	
	}

	public static PeacockType breed(PeacockType male, PeacockType female)
	{
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			return male;
		else
			return female;
	}

}
