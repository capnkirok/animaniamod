package com.animania.addons.catsdogs.common.entity.canids;

import java.lang.reflect.Constructor;

import com.animania.Animania;
import com.animania.addons.catsdogs.common.entity.canids.DogBloodHound.EntityFemaleBloodHound;
import com.animania.addons.catsdogs.common.entity.canids.DogBloodHound.EntityMaleBloodHound;
import com.animania.addons.catsdogs.common.entity.canids.DogBloodHound.EntityPuppyBloodHound;
import com.animania.addons.catsdogs.common.entity.canids.DogChihuahua.EntityFemaleChihuahua;
import com.animania.addons.catsdogs.common.entity.canids.DogChihuahua.EntityMaleChihuahua;
import com.animania.addons.catsdogs.common.entity.canids.DogChihuahua.EntityPuppyChihuahua;
import com.animania.addons.catsdogs.common.entity.canids.DogCollie.EntityFemaleCollie;
import com.animania.addons.catsdogs.common.entity.canids.DogCollie.EntityMaleCollie;
import com.animania.addons.catsdogs.common.entity.canids.DogCollie.EntityPuppyCollie;
import com.animania.addons.catsdogs.common.entity.canids.DogCorgi.EntityFemaleCorgi;
import com.animania.addons.catsdogs.common.entity.canids.DogCorgi.EntityMaleCorgi;
import com.animania.addons.catsdogs.common.entity.canids.DogCorgi.EntityPuppyCorgi;
import com.animania.addons.catsdogs.common.entity.canids.DogDachshund.EntityFemaleDachshund;
import com.animania.addons.catsdogs.common.entity.canids.DogDachshund.EntityMaleDachshund;
import com.animania.addons.catsdogs.common.entity.canids.DogDachshund.EntityPuppyDachshund;
import com.animania.addons.catsdogs.common.entity.canids.DogFox.EntityFemaleFox;
import com.animania.addons.catsdogs.common.entity.canids.DogFox.EntityMaleFox;
import com.animania.addons.catsdogs.common.entity.canids.DogFox.EntityPuppyFox;
import com.animania.addons.catsdogs.common.entity.canids.DogGermanShepherd.EntityFemaleGermanShepherd;
import com.animania.addons.catsdogs.common.entity.canids.DogGermanShepherd.EntityMaleGermanShepherd;
import com.animania.addons.catsdogs.common.entity.canids.DogGermanShepherd.EntityPuppyGermanShepherd;
import com.animania.addons.catsdogs.common.entity.canids.DogGreatDane.EntityFemaleGreatDane;
import com.animania.addons.catsdogs.common.entity.canids.DogGreatDane.EntityMaleGreatDane;
import com.animania.addons.catsdogs.common.entity.canids.DogGreatDane.EntityPuppyGreatDane;
import com.animania.addons.catsdogs.common.entity.canids.DogGreyhound.EntityFemaleGreyhound;
import com.animania.addons.catsdogs.common.entity.canids.DogGreyhound.EntityMaleGreyhound;
import com.animania.addons.catsdogs.common.entity.canids.DogGreyhound.EntityPuppyGreyhound;
import com.animania.addons.catsdogs.common.entity.canids.DogHusky.EntityFemaleHusky;
import com.animania.addons.catsdogs.common.entity.canids.DogHusky.EntityMaleHusky;
import com.animania.addons.catsdogs.common.entity.canids.DogHusky.EntityPuppyHusky;
import com.animania.addons.catsdogs.common.entity.canids.DogLabrador.EntityFemaleLabrador;
import com.animania.addons.catsdogs.common.entity.canids.DogLabrador.EntityMaleLabrador;
import com.animania.addons.catsdogs.common.entity.canids.DogLabrador.EntityPuppyLabrador;
import com.animania.addons.catsdogs.common.entity.canids.DogPomeranian.EntityFemalePomeranian;
import com.animania.addons.catsdogs.common.entity.canids.DogPomeranian.EntityMalePomeranian;
import com.animania.addons.catsdogs.common.entity.canids.DogPomeranian.EntityPuppyPomeranian;
import com.animania.addons.catsdogs.common.entity.canids.DogPoodle.EntityFemalePoodle;
import com.animania.addons.catsdogs.common.entity.canids.DogPoodle.EntityMalePoodle;
import com.animania.addons.catsdogs.common.entity.canids.DogPoodle.EntityPuppyPoodle;
import com.animania.addons.catsdogs.common.entity.canids.DogPug.EntityFemalePug;
import com.animania.addons.catsdogs.common.entity.canids.DogPug.EntityMalePug;
import com.animania.addons.catsdogs.common.entity.canids.DogPug.EntityPuppyPug;
import com.animania.addons.catsdogs.common.entity.canids.DogWolf.EntityFemaleWolf;
import com.animania.addons.catsdogs.common.entity.canids.DogWolf.EntityMaleWolf;
import com.animania.addons.catsdogs.common.entity.canids.DogWolf.EntityPuppyWolf;
import com.animania.api.interfaces.AnimaniaType;

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
