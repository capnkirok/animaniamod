package com.animania.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public enum EntityGender
{

	MALE, FEMALE, CHILD;

	public static EntityLivingBase getEntity(AnimaniaType type, EntityGender gender, World world)
	{
		switch (gender)
		{
		case MALE:
			return type.getMale(world);
		case FEMALE:
			return type.getFemale(world);
		case CHILD:
			return type.getChild(world);

		}
		return null;

	}

}
