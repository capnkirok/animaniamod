package com.animania.common.handler;

import java.util.HashMap;
import java.util.Map;

public class AddonInjectionHandler
{
	private static Map<String, Map<String, AddonInjection>> injections = new HashMap<String, Map<String, AddonInjection>>();

	public static void addInjection(String addonID, String injectionName, AddonInjection injection)
	{
		Map<String, AddonInjection> map = injections.get(addonID);
		if (map == null)
			map = new HashMap<String, AddonInjection>();

		map.put(injectionName, injection);

		injections.put(addonID, map);
	}

	public static <R> R runInjection(String addonID, String injectionName, Class<? extends R> returnType, Object... args)
	{
		if (!AddonHandler.isAddonLoaded(addonID))
		{
			if (returnType.equals(Boolean.class))
				return (R) Boolean.FALSE;
			if (returnType.equals(Float.class))
				return (R) new Float(0.0f);
			if (returnType.equals(Integer.class))
				return (R) new Integer(0);
			if (returnType.equals(Double.class))
				return (R) new Double(0.0);
			else
				return null;
		}

		Map<String, AddonInjection> map = injections.get(addonID);
		if (map == null)
		{
			if (returnType.equals(Boolean.class))
				return (R) Boolean.FALSE;
			if (returnType.equals(Float.class))
				return (R) new Float(0.0f);
			if (returnType.equals(Integer.class))
				return (R) new Integer(0);
			if (returnType.equals(Double.class))
				return (R) new Double(0.0);
			else
				return null;
		}

		AddonInjection inj = map.get(injectionName);
		if (inj != null)
			return (R) inj.run(args);

		return null;
	}

	public static interface AddonInjection<R>
	{
		public R run(Object... args);
	}

}
