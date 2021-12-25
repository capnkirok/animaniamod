package com.animania.addons.template.common.handler;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class TemplateAddonEntityHandler
{

	/**
	 * Register Entities
	 */
	public static void preInit()
	{

	}

	private static Biome[] getBiomes(BiomeDictionary.Type type)
	{
		List<Biome> criteriaMet = Lists.newArrayList();
		for (Biome b : Biome.REGISTRY)
		{
			Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(b);
			if (types.contains(type))
			{
				criteriaMet.add(b);
			}
		}

		return criteriaMet.toArray(new Biome[criteriaMet.size()]);
	}

}
