package com.animania.api.interfaces;

import com.animania.Animania;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public interface AnimaniaType
{

	public LivingEntity getMale(Level level);

	public LivingEntity getFemale(Level level);

	public LivingEntity getChild(Level level);

	public static AnimaniaType valueOf(String type)
	{
		return null;
	}

	default AnimaniaType breed(AnimaniaType other)
	{
		return Animania.RANDOM.nextBoolean() ? this : other;
	}

	public String getTypeName();

}
