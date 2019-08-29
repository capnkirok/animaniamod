package com.animania.common.entities.peacocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeachickBlue;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeacockBlue;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeachickCharcoal;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeacockCharcoal;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeafowlCharcoal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeachickOpal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeacockOpal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeafowlOpal;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeachickPeach;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeacockPeach;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeafowlPeach;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeachickPurple;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeacockPurple;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeafowlPurple;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeachickTaupe;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeacockTaupe;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeafowlTaupe;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeachickWhite;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeacockWhite;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeafowlWhite;

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
