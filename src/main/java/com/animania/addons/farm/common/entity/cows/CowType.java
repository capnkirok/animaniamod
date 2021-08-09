package com.animania.addons.farm.common.entity.cows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityBullAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityCalfAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.CowEntityAngus;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityBullFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityCalfFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.CowEntityFriesian;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityBullHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityCalfHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.CowEntityHereford;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityBullHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityCalfHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.CowEntityHighland;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityBullHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityCalfHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.CowEntityHolstein;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityBullJersey;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityCalfJersey;
import com.animania.addons.farm.common.entity.cows.CowJersey.CowEntityJersey;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityCalfLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.CowEntityLonghorn;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityCalfMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.CowEntityMooshroom;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.World;

public enum CowType implements AnimaniaType
{
	ANGUS(EntityBullAngus.class, CowEntityAngus.class, EntityCalfAngus.class, true),
	FRIESIAN(EntityBullFriesian.class, CowEntityFriesian.class, EntityCalfFriesian.class, false),
	HEREFORD(EntityBullHereford.class, CowEntityHereford.class, EntityCalfHereford.class, true),
	HOLSTEIN(EntityBullHolstein.class, CowEntityHolstein.class, EntityCalfHolstein.class, false),
	LONGHORN(EntityBullLonghorn.class, CowEntityLonghorn.class, EntityCalfLonghorn.class, true),
	HIGHLAND(EntityBullHighland.class, CowEntityHighland.class, EntityCalfHighland.class, true),
	JERSEY(EntityBullJersey.class, CowEntityJersey.class, EntityCalfJersey.class, true),
	MOOSHROOM(EntityBullMooshroom.class, CowEntityMooshroom.class, EntityCalfMooshroom.class, false);

	private Class bull;
	private Class cow;
	private Class calf;
	public boolean isPrime;
	
	private CowType(Class bull, Class cow, Class calf, boolean prime)
	{
		this.bull = bull;
		this.cow = cow;
		this.calf = calf;
		this.isPrime = prime;
	}

	@Override
	public EntityBullBase getMale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.bull.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityBullBase bull = null;
		try
		{
			bull = (EntityBullBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return bull;
	}

	@Override
	public CowEntityBase getFemale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.cow.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		CowEntityBase cow = null;
		try
		{
			cow = (CowEntityBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return cow;	
	}

	@Override
	public EntityCalfBase getChild(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.calf.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityCalfBase calf = null;
		try
		{
			calf = (EntityCalfBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return calf;	
	}

	public static CowType breed(CowType male, CowType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":cow";
	}


}
