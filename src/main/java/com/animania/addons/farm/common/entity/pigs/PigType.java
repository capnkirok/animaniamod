package com.animania.addons.farm.common.entity.pigs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntityHogDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntitySowDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.PigEntityletDuroc;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntityHogHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntitySowHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.PigEntityletHampshire;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntityHogLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntitySowLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.PigEntityletLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntityHogLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntitySowLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.PigEntityletLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntityHogOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntitySowOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.PigEntityletOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntitySowYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.PigEntityletYorkshire;
import com.animania.api.interfaces.AnimaniaType;

public enum PigType implements AnimaniaType
{
	DUROC(EntityHogDuroc.class, EntitySowDuroc.class, PigEntityletDuroc.class, true),
	HAMPSHIRE(EntityHogHampshire.class, EntitySowHampshire.class, PigEntityletHampshire.class, true),
	LARGE_BLACK(EntityHogLargeBlack.class, EntitySowLargeBlack.class, PigEntityletLargeBlack.class, true),
	LARGE_WHITE(EntityHogLargeWhite.class, EntitySowLargeWhite.class, PigEntityletLargeWhite.class, false),
	OLD_SPOT(EntityHogOldSpot.class, EntitySowOldSpot.class, PigEntityletOldSpot.class, true),
	YORKSHIRE(EntityHogYorkshire.class, EntitySowYorkshire.class, PigEntityletYorkshire.class, false);


	private Class hog;
	private Class sow;
	private Class piglet;
	public boolean isPrime;

	private PigType(Class hog, Class sow, Class piglet, boolean isPrime)
	{
		this.hog = hog;
		this.sow = sow;
		this.piglet = piglet;
		this.isPrime = isPrime;
	}

	@Override
	public EntityHogBase getMale(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.hog.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityHogBase bull = null;
		try
		{
			bull = (EntityHogBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return bull;
	}

	@Override
	public EntitySowBase getFemale(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.sow.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntitySowBase cow = null;
		try
		{
			cow = (EntitySowBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return cow;	
	}

	@Override
	public PigEntityletBase getChild(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.piglet.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		PigEntityletBase calf = null;
		try
		{
			calf = (PigEntityletBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return calf;	
	}

	public static PigType breed(PigType male, PigType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":pig";
	}
}
