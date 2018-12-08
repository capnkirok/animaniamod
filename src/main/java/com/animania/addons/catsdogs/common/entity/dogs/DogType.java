package com.animania.addons.catsdogs.common.entity.dogs;

import java.lang.reflect.Constructor;

import com.animania.Animania;
import com.animania.common.entities.AnimaniaType;

import net.minecraft.world.World;

public enum DogType implements AnimaniaType
{
	BLOOD_HOUND(EntityMaleBloodHound.class, EntityFemaleBloodHound.class, EntityPuppyBloodHound.class),
	CHIHUAHUA(EntityMaleChihuahua.class, EntityFemaleChihuahua.class, EntityPuppyChihuahua.class),
	COLLIE(EntityMaleCollie.class, EntityFemaleCollie.class, EntityPuppyCollie.class),
	CORGI(EntityMaleCorgi.class, EntityFemaleCorgi.class, EntityPuppyCorgi.class),
	DACHSHUND(EntityMaleDachshund.class, EntityFemaleDachshund.class, EntityPuppyDachshund.class),
	FOX(EntityMaleFox.class, EntityFemaleFox.class, EntityPuppyFox.class),
	GERMAN_SHEPHERD(EntityMaleGermanShepherd.class, EntityFemaleGermanShepherd.class, EntityPuppyGermanShepherd.class),
	GREAT_DANE(EntityMaleGreatDane.class, EntityFemaleGreatDane.class, EntityPuppyGreatDane.class),
	GREYHOUND(EntityMaleGreyhound.class, EntityFemaleGreyhound.class, EntityPuppyGreyhound.class),
	HUSKY(EntityMaleHusky.class, EntityFemaleHusky.class, EntityPuppyHusky.class),
	LABRADOR(EntityMaleLabrador.class, EntityFemaleLabrador.class, EntityPuppyLabrador.class),
	POMERANIAN(EntityMalePomeranian.class, EntityFemalePomeranian.class, EntityPuppyPomeranian.class),
	POODLE(EntityMalePoodle.class, EntityFemalePoodle.class, EntityPuppyPoodle.class),
	PUG(EntityMalePug.class, EntityFemalePug.class, EntityPuppyPug.class),
	WOLF(EntityMaleWolf.class, EntityFemaleWolf.class, EntityPuppyWolf.class);
	
	private Class male;
	private Class female;
	private Class child;
	
	private DogType(Class male, Class female, Class child)
	{
		this.male = male;
		this.female = female;
		this.child = child;
	}
	
	@Override
	public EntityMaleDogBase getMale(World world)
	{
		try
		{
			Constructor<?> constructor = this.male.getConstructor(World.class);
			EntityMaleDogBase male = (EntityMaleDogBase) constructor.newInstance(world);
			return male;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public EntityFemaleDogBase getFemale(World world)
	{
		try
		{
			Constructor<?> constructor = this.female.getConstructor(World.class);
			EntityFemaleDogBase female = (EntityFemaleDogBase) constructor.newInstance(world);
			return female;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public EntityPuppyBase getChild(World world)
	{
		try
		{
			Constructor<?> constructor = this.child.getConstructor(World.class);
			EntityPuppyBase child = (EntityPuppyBase) constructor.newInstance(world);
			return child;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static DogType breed(DogType male, DogType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}
}
