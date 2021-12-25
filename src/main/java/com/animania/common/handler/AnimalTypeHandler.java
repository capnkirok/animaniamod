package com.animania.common.handler;

import java.util.HashMap;
import java.util.Map;

import com.animania.api.interfaces.AnimaniaType;

public class AnimalTypeHandler
{

	private static Map<String, Class<? extends AnimaniaType>> animalTypes = new HashMap<>();

	public static void register(String name, Class<? extends AnimaniaType> type)
	{
		animalTypes.put(name, type);
	}

	public static AnimaniaType getType(String typeName, String name)
	{
		Class<? extends AnimaniaType> type = animalTypes.get(typeName);
		if (type == null)
			return null;

		AnimaniaType[] enums = type.getEnumConstants();
		for (AnimaniaType t : enums)
		{
			if (t.toString().equals(name))
				return t;
		}

		return null;
	}
}
