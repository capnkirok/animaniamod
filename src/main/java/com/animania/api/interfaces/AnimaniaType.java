package com.animania.api.interfaces;

import com.animania.Animania;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public interface AnimaniaType
{

	public LivingEntity getMale(World world);
	
	public LivingEntity getFemale(World world);
	
	public LivingEntity getChild(World world);
	
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
