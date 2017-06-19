package com.animania.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import scala.util.Random;

public enum EntityGender
{

	MALE, FEMALE, CHILD, RANDOM;

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
			Random rand = new Random();
			switch(rand.nextInt(3))
			{
			case 0:
				return type.getMale(world);
			case 1:
				return type.getFemale(world);
			case 2:
				return type.getChild(world);
			}

		}
		return null;

	}

}
