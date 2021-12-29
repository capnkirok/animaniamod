package com.animania.api.interfaces;

import com.animania.Animania;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public interface AnimaniaType
{

	LivingEntity getMale(Level level);

	LivingEntity getFemale(Level level);

	LivingEntity getChild(Level level);

	static AnimaniaType valueOf(String type)
	{
		return null;
	}

	default AnimaniaType breed(AnimaniaType other)
	{
		return Animania.RANDOM.nextBoolean() ? this : other;
	}

	String getTypeName();

}
