package com.animania.api.data;

import com.animania.api.interfaces.AnimaniaType;

public class AnimalContainer
{

	private final AnimaniaType type;
	private final EntityGender gender;

	public AnimalContainer(AnimaniaType animalType, EntityGender gender)
	{
		this.type = animalType;
		this.gender = gender;
	}

	public AnimaniaType getType()
	{
		return this.type;
	}

	public EntityGender getGender()
	{
		return this.gender;
	}

	@Override
	public String toString()
	{
		return this.type.toString() + ":" + this.gender.toString();
	}

	public static AnimalContainer fromString(String string)
	{
		// String[] s = string.split(":");
		// String type = s[0];
		// String gender = s[1];
		//
		// AnimaniaType t = AnimaniaType.valueOf(type);
		// EntityGender g = EntityGender.valueOf(gender);
		//
		// if(t != null && g != null)
		// return new AnimalContainer(t,g);
		//
		return null;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof AnimalContainer o)
		{
			return o.getGender() == this.getGender() && o.getType() == this.getType();
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return this.gender.hashCode() + this.type.hashCode();
	}

}
