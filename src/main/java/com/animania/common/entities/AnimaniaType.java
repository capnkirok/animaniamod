package com.animania.common.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.cows.EntityCalfBase;

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

}
