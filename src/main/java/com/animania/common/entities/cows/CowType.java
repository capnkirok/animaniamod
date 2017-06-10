package com.animania.common.entities.cows;

import java.util.Random;

import com.animania.common.AnimaniaAchievements;

import net.minecraft.stats.StatBase;

public enum CowType
{
	ANGUS(EntityBullAngus.class, EntityCowAngus.class, EntityCalfAngus.class, AnimaniaAchievements.Angus),
	FRIESIAN(EntityBullFriesian.class, EntityCowFriesian.class, EntityCalfFriesian.class, AnimaniaAchievements.Friesian),
	HEREFORD(EntityBullHereford.class, EntityCowHereford.class, EntityCalfHereford.class, AnimaniaAchievements.Hereford),
	HOLSTEIN(EntityBullHolstein.class, EntityCowHolstein.class, EntityCalfHolstein.class, AnimaniaAchievements.Holstein),
	LONGHORN(EntityBullLonghorn.class, EntityCowLonghorn.class, EntityCalfLonghorn.class, AnimaniaAchievements.Longhorn);
	
	
	private Class bull;
	private Class cow;
	private Class calf;
	private StatBase achievement;
	
	private CowType(Class bull, Class cow, Class calf, StatBase achievement)
	{
		this.bull = bull;
		this.cow = cow;
		this.calf = calf;
		this.achievement = achievement;
	}
	
	public static Class getMale(CowType type)
	{
		return type.bull;
	}
	
	public static Class getFemale(CowType type)
	{
		return type.cow;
	}
	
	public static Class getChild(CowType type)
	{
		return type.calf;
	}
	
	public Class getMale()
	{
		return this.bull;
	}
	
	public Class getFemale()
	{
		return this.cow;
	}
	
	public Class getChild()
	{
		return this.calf;
	}
	
	public static CowType breed(CowType male, CowType female)
	{
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
			return male;
		else
			return female;
	}
	
	public StatBase getAchievement()
	{
		return this.achievement;
	}
	
}
