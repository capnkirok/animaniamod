package com.animania.common.entities.sheep;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.entities.sheep.SheepDorper.EntityEweDorper;
import com.animania.common.entities.sheep.SheepDorper.EntityLambDorper;
import com.animania.common.entities.sheep.SheepDorper.EntityRamDorper;
import com.animania.common.entities.sheep.SheepDorset.EntityEweDorset;
import com.animania.common.entities.sheep.SheepDorset.EntityLambDorset;
import com.animania.common.entities.sheep.SheepDorset.EntityRamDorset;
import com.animania.common.entities.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.common.entities.sheep.SheepFriesian.EntityLambFriesian;
import com.animania.common.entities.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.common.entities.sheep.SheepJacob.EntityEweJacob;
import com.animania.common.entities.sheep.SheepJacob.EntityLambJacob;
import com.animania.common.entities.sheep.SheepJacob.EntityRamJacob;
import com.animania.common.entities.sheep.SheepMerino.EntityEweMerino;
import com.animania.common.entities.sheep.SheepMerino.EntityLambMerino;
import com.animania.common.entities.sheep.SheepMerino.EntityRamMerino;
import com.animania.common.entities.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.common.entities.sheep.SheepSuffolk.EntityLambSuffolk;
import com.animania.common.entities.sheep.SheepSuffolk.EntityRamSuffolk;

import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public enum SheepType implements AnimaniaType
{
	DORPER(EntityRamDorper.class, EntityEweDorper.class, EntityLambDorper.class, null, true),
	DORSET(EntityRamDorset.class, EntityEweDorset.class, EntityLambDorset.class, null, true),
	FRIESIAN(EntityRamFriesian.class, EntityEweFriesian.class, EntityLambFriesian.class, null, false),
	JACOB(EntityRamJacob.class, EntityEweJacob.class, EntityLambJacob.class, null, false),
	MERINO(EntityRamMerino.class, EntityEweMerino.class, EntityLambMerino.class, null, false),
	SUFFOLK(EntityRamSuffolk.class, EntityEweSuffolk.class, EntityLambSuffolk.class, null, true);
	
	private Class male;
	private Class female;
	private Class child;
	private StatBase achievement;
	public boolean isPrime;
	
	private SheepType(Class male, Class female, Class child, StatBase achievement, boolean prime)
	{
		this.male = male;
		this.female = female;
		this.child = child;
		this.achievement = achievement;
		this.isPrime = prime;
	}
	
	@Override
	public EntityRamBase getMale(World world)
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
		EntityRamBase male = null;
		try
		{
			male = (EntityRamBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return male;
	}

	@Override
	public EntityEweBase getFemale(World world)
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
		EntityEweBase female = null;
		try
		{
			female = (EntityEweBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return female;
	}

	@Override
	public EntityLambBase getChild(World world)
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
		EntityLambBase child = null;
		try
		{
			child = (EntityLambBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return child;
	}

	public static SheepType breed(SheepType male, SheepType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}

	public StatBase getAchievement()
	{
		return achievement;
	}
	
	
	
	

	
}
