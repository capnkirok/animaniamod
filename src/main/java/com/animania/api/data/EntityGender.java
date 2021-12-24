package com.animania.api.data;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.entity.LivingEntity;

public enum EntityGender
{

	MALE, FEMALE, CHILD, RANDOM, NONE;

	public static LivingEntity getEntity(AnimaniaType type, EntityGender gender, Level level)
	{
		switch (gender)
		{
		case MALE:
			return type.getMale(level);
		case FEMALE:
			return type.getFemale(level);
		case CHILD:
			return type.getChild(level);
		case RANDOM:
			switch(Animania.RANDOM.nextInt(3))
			{
			case 0:
				return type.getMale(level);
			case 1:
				return type.getFemale(level);
			case 2:
				return type.getChild(level);
			}
		case NONE:
			return type.getMale(level);

		}
		
		return null;

	}

}
