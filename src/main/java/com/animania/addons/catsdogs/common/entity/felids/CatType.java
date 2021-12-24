package com.animania.addons.catsdogs.common.entity.felids;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.catsdogs.common.entity.felids.CatAmericanShorthair.EntityKittenAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.felids.CatAmericanShorthair.EntityQueenAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.felids.CatAmericanShorthair.EntityTomAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.felids.CatAsiatic.EntityKittenAsiatic;
import com.animania.addons.catsdogs.common.entity.felids.CatAsiatic.EntityQueenAsiatic;
import com.animania.addons.catsdogs.common.entity.felids.CatAsiatic.EntityTomAsiatic;
import com.animania.addons.catsdogs.common.entity.felids.CatExotic.EntityKittenExotic;
import com.animania.addons.catsdogs.common.entity.felids.CatExotic.EntityQueenExotic;
import com.animania.addons.catsdogs.common.entity.felids.CatExotic.EntityTomExotic;
import com.animania.addons.catsdogs.common.entity.felids.CatNorwegian.EntityKittenNorwegian;
import com.animania.addons.catsdogs.common.entity.felids.CatNorwegian.EntityQueenNorwegian;
import com.animania.addons.catsdogs.common.entity.felids.CatNorwegian.EntityTomNorwegian;
import com.animania.addons.catsdogs.common.entity.felids.CatOcelot.EntityKittenOcelot;
import com.animania.addons.catsdogs.common.entity.felids.CatOcelot.EntityQueenOcelot;
import com.animania.addons.catsdogs.common.entity.felids.CatOcelot.EntityTomOcelot;
import com.animania.addons.catsdogs.common.entity.felids.CatRagdoll.EntityKittenRagdoll;
import com.animania.addons.catsdogs.common.entity.felids.CatRagdoll.EntityQueenRagdoll;
import com.animania.addons.catsdogs.common.entity.felids.CatRagdoll.EntityTomRagdoll;
import com.animania.addons.catsdogs.common.entity.felids.CatSiamese.EntityKittenSiamese;
import com.animania.addons.catsdogs.common.entity.felids.CatSiamese.EntityQueenSiamese;
import com.animania.addons.catsdogs.common.entity.felids.CatSiamese.EntityTomSiamese;
import com.animania.addons.catsdogs.common.entity.felids.CatTabby.EntityKittenTabby;
import com.animania.addons.catsdogs.common.entity.felids.CatTabby.EntityQueenTabby;
import com.animania.addons.catsdogs.common.entity.felids.CatTabby.EntityTomTabby;
import com.animania.api.interfaces.AnimaniaType;

public enum CatType implements AnimaniaType
{
	AMERICAN_SHORTHAIR(EntityTomAmericanShorthair.class, EntityQueenAmericanShorthair.class, EntityKittenAmericanShorthair.class),
	ASIATIC(EntityTomAsiatic.class, EntityQueenAsiatic.class, EntityKittenAsiatic.class),
	EXOTIC(EntityTomExotic.class, EntityQueenExotic.class, EntityKittenExotic.class),
	NORWEGIAN(EntityTomNorwegian.class, EntityQueenNorwegian.class, EntityKittenNorwegian.class),
	OCELOT(EntityTomOcelot.class, EntityQueenOcelot.class, EntityKittenOcelot.class),
	RAGDOLL(EntityTomRagdoll.class, EntityQueenRagdoll.class, EntityKittenRagdoll.class),
	SIAMESE(EntityTomSiamese.class, EntityQueenSiamese.class, EntityKittenSiamese.class),
	TABBY(EntityTomTabby.class, EntityQueenTabby.class, EntityKittenTabby.class);
	
	//TODO achievements to above

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
	public EntityTomBase getMale(Level level)
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
		EntityTomBase bull = null;
		try
		{
			bull = (EntityTomBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return bull;
	}

	@Override
	public EntityQueenBase getFemale(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.female.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityQueenBase cow = null;
		try
		{
			cow = (EntityQueenBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return cow;	
	}

	@Override
	public EntityKittenBase getChild(Level level)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.child.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityKittenBase calf = null;
		try
		{
			calf = (EntityKittenBase) constructor.newInstance(level);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return calf;	
	}

	public static CatType breed(CatType male, CatType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}
	
	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":cat";
	}

}
