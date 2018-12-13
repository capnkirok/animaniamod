package com.animania.common.entities.peacocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

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
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

}
