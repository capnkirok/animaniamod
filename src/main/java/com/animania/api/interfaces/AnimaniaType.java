package com.animania.api.interfaces;

import com.animania.Animania;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public interface AnimaniaType
{

	public LivingEntity getMale(Level world);
	
	public LivingEntity getFemale(Level world);
	
	public LivingEntity getChild(Level world);
	
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
