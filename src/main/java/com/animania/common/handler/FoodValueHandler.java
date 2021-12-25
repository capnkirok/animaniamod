package com.animania.common.handler;

import java.util.HashMap;
import java.util.Map;

import com.animania.common.helper.InvalidConfigException;
import com.animania.common.helper.StringParser;
import com.animania.common.items.ItemAnimaniaFood;
import com.animania.config.AnimaniaConfig;

public class FoodValueHandler
{
	private static Map<ItemAnimaniaFood, String> food_values = new HashMap<>();

	public static void init()
	{
		String[] overrides = AnimaniaConfig.foodValues.foodValueOverrides;
		for (String s : overrides)
		{
			if (!s.isEmpty())
			{
				String name = s;
				boolean errored = false;

				if (!name.contains("("))
				{
					new InvalidConfigException("Missing ( in line: " + s).printException();
					errored = true;
				}

				if (!name.contains(")"))
				{
					new InvalidConfigException("Missing ) in line: " + s).printException();
					errored = true;
				}

				if (!errored)
				{
					String values = name.substring(name.indexOf("("));
					name = name.replace(values, "");

					Item item = StringParser.getItem(name);

					if (!(item instanceof ItemAnimaniaFood))
					{
						new InvalidConfigException("The item specified is not a food item from Animania: " + name).printException();
						errored = true;
					}

					values = values.replace("(", "");
					values = values.replace(")", "");

					if (!values.contains(","))
					{
						new InvalidConfigException("Missing number separator (comma) in line: " + s).printException();
						errored = true;
					}

					if (!errored)
					{
						String[] v = values.split(",");
						try
						{
							int healAmount = Integer.parseInt(v[0]);
						}
						catch (Exception e)
						{
							new InvalidConfigException(v[0] + " at line " + s + " is not a valid Integer").printException();
							errored = true;
						}

						try
						{
							float saturation = Float.parseFloat(v[1]);
						}
						catch (Exception e)
						{
							new InvalidConfigException(v[1] + " at line " + s + " is not a valid Float").printException();
							errored = true;
						}

						if (!errored)
							food_values.put((ItemAnimaniaFood) item, values);
					}
				}
			}
		}
	}

	public static boolean hasOverride(Item item)
	{
		return food_values.containsKey(item);
	}

	public static int getHealAmount(Item item)
	{
		String s = food_values.get(item);
		String[] values = s.split(",");

		return Integer.parseInt(values[0]);
	}

	public static float getSaturation(Item item)
	{
		String s = food_values.get(item);
		String[] values = s.split(",");

		return Float.parseFloat(values[1]);
	}
}
