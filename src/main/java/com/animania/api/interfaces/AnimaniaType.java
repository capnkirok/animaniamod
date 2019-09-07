package com.animania.api.interfaces;

import com.animania.Animania;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public interface AnimaniaType
{

	public EntityLivingBase getMale(World world);
	
	public EntityLivingBase getFemale(World world);
	
	public EntityLivingBase getChild(World world);
	
	public static AnimaniaType valueOf(String type)
	{
		return null;
	}

	default AnimaniaType breed(AnimaniaType other)
	{
		return Animania.RANDOM.nextBoolean() ? this : other;
	}

}
