package com.animania.api.interfaces;

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
