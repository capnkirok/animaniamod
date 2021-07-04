package com.animania.addons.farm.common.entity.goats;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityBuckAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityDoeAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityKidAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityDoeAngora;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityKidAngora;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityBuckFainting;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityDoeFainting;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityKidFainting;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityBuckKiko;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityDoeKiko;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityKidKiko;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityBuckKinder;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityDoeKinder;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityKidKinder;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityBuckNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityDoeNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityKidNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityBuckPygmy;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityDoePygmy;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityKidPygmy;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

import StatBase;

public enum GoatType implements AnimaniaType
{
	ALPINE(EntityBuckAlpine.class, EntityDoeAlpine.class, EntityKidAlpine.class, null, true),
	ANGORA(EntityBuckAngora.class, EntityDoeAngora.class, EntityKidAngora.class, null, false),
	FAINTING(EntityBuckFainting.class, EntityDoeFainting.class, EntityKidFainting.class, null, false),
	KIKO(EntityBuckKiko.class, EntityDoeKiko.class, EntityKidKiko.class, null, true),
	KINDER(EntityBuckKinder.class, EntityDoeKinder.class, EntityKidKinder.class, null, false),
	NIGERIAN_DWARF(EntityBuckNigerianDwarf.class, EntityDoeNigerianDwarf.class, EntityKidNigerianDwarf.class, null, false),
	PYGMY(EntityBuckPygmy.class, EntityDoePygmy.class, EntityKidPygmy.class, null, true);
	

	private Class male;
	private Class female;
	private Class child;
	private StatBase achievement;
	public boolean isPrime;
	
	private GoatType(Class male, Class female, Class child, StatBase achievement, boolean prime)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.achievement = achievement;
		this.isPrime = prime;
	}
	
	@Override
	public EntityBuckBase getMale(World world)
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
		EntityBuckBase male = null;
		try
		{
			male = (EntityBuckBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityDoeBase getFemale(World world)
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
		EntityDoeBase female = null;
		try
		{
			female = (EntityDoeBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;
	}

	@Override
	public EntityKidBase getChild(World world)
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
		EntityKidBase child = null;
		try
		{
			child = (EntityKidBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;
	}

	public static GoatType breed(GoatType male, GoatType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

	public StatBase getAchievement()
	{
		return achievement;
	}
	
	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":goat";
	}

	
}
