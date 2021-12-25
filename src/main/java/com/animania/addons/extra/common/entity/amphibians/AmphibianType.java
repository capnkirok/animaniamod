package com.animania.addons.extra.common.entity.amphibians;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

public enum AmphibianType implements AnimaniaType
{
	FROG(EntityFrogs.class), DART_FROG(EntityDartFrogs.class), TOAD(EntityToad.class);

	private Class male;

	private AmphibianType(Class male)
	{
		this.male = male;
	}

	@Override
	public EntityAmphibian getMale(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.male.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityAmphibian frog = null;
		try
		{
			frog = (EntityAmphibian) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return frog;
	}

	@Override
	public EntityAmphibian getFemale(Level level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityAmphibian getChild(Level level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":amphibians";
	}

}
