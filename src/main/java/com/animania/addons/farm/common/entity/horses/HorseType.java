package com.animania.addons.farm.common.entity.horses;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityFoalDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.level.Level;

public enum HorseType implements AnimaniaType
{
	DRAFT(EntityStallionDraftHorse.class, EntityMareDraftHorse.class, EntityFoalDraftHorse.class);
	
	private Class stallion;
	private Class mare;
	private Class foal;

	private HorseType(Class stallion, Class mare, Class foal)
	{
		this.stallion = stallion;
		this.mare = mare;
		this.foal = foal;
	}

	@Override
	public EntityStallionBase getMale(Level world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.stallion.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityStallionBase stallion = null;
		try
		{
			stallion = (EntityStallionBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return stallion;
	}

	@Override
	public EntityMareBase getFemale(Level world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.mare.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityMareBase mare = null;
		try
		{
			mare = (EntityMareBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return mare;	
	}

	@Override
	public EntityFoalBase getChild(Level world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.foal.getConstructor(Level.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityFoalBase foal = null;
		try
		{
			foal = (EntityFoalBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return foal;	
	}

	public static HorseType breed(HorseType male, HorseType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":horse";
	}
}
