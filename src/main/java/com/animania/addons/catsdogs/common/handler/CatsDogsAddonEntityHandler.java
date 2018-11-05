package com.animania.addons.catsdogs.common.handler;

import java.util.List;
import java.util.Set;

import com.animania.addons.catsdogs.common.entity.cats.CatType;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomRagdoll;
import com.animania.common.entities.EntityGender;
import com.animania.common.helper.RegistryHelper;
import com.google.common.collect.Lists;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class CatsDogsAddonEntityHandler
{
	
	/**
	 * Register Entities
	 */
	public static void preInit()
	{
		
		int entityID = 3000; //TODO Could be a problem, might need to find latest entityID from Animania
		
		// CATS
		RegistryHelper.Entities.registerAnimal(EntityTomRagdoll.class, "tom_ragdoll", entityID++, CatType.RAGDOLL, EntityGender.MALE);
		

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
