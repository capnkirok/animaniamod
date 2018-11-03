package com.animania.common.entities;

import com.animania.Animania;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public enum EntityGender
{

	MALE, FEMALE, CHILD, RANDOM, NONE;

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
		case RANDOM:
			switch(Animania.RANDOM.nextInt(3))
			{
			case 0:
				return type.getMale(world);
			case 1:
				return type.getFemale(world);
			case 2:
				return type.getChild(world);
			}
		case NONE:
			return type.getMale(world);

		}
		
		return null;

	}

}
