package com.animania.api.data;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import LivingEntity;

public enum EntityGender
{

	MALE, FEMALE, CHILD, RANDOM, NONE;

	public static LivingEntity getEntity(AnimaniaType type, EntityGender gender, World world)
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
