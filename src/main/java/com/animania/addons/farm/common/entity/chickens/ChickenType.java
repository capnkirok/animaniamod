package com.animania.addons.farm.common.entity.chickens;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityChickLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityChickOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityChickPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityChickRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityChickWyandotte;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityRoosterWyandotte;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.level.Level;

public enum ChickenType implements AnimaniaType
{
	
	LEGHORN(EntityRoosterLeghorn.class, EntityHenLeghorn.class, EntityChickLeghorn.class, false),
	ORPINGTON(EntityRoosterOrpington.class, EntityHenOrpington.class, EntityChickOrpington.class, true),
	PLYMOUTH_ROCK(EntityRoosterPlymouthRock.class, EntityHenPlymouthRock.class, EntityChickPlymouthRock.class, true),
	RHODE_ISLAND_RED(EntityRoosterRhodeIslandRed.class, EntityHenRhodeIslandRed.class, EntityChickRhodeIslandRed.class, true),
	WYANDOTTE(EntityRoosterWyandotte.class, EntityHenWyandotte.class, EntityChickWyandotte.class, true);

	private Class male;
	private Class female;
	private Class child;
	public boolean isPrime;
	
	private ChickenType(Class male, Class female, Class child, boolean isPrime)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.isPrime = isPrime;
	}
	
	@Override
	public EntityRoosterBase getMale(Level world)
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
		EntityRoosterBase male = null;
		try
		{
			male = (EntityRoosterBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityHenBase getFemale(Level world)
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
		EntityHenBase female = null;
		try
		{
			female = (EntityHenBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;	
	}

	@Override
	public EntityChickBase getChild(Level world)
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
		EntityChickBase child = null;
		try
		{
			child = (EntityChickBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;	
	}

	public static ChickenType breed(ChickenType male, ChickenType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}
	
	@Override
	public String getTypeName()
	{
		return Animania.MODID + ":chicken";
	}

}
