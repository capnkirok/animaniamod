package com.animania.addons.catsdogs.common.handler;

import static com.animania.common.handler.EntityHandler.entityID;

import java.util.List;
import java.util.Set;

import com.animania.addons.catsdogs.common.entity.cats.CatType;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenAsiatic;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenExotic;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenNorwegian;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenOcelot;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenRagdoll;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenSiamese;
import com.animania.addons.catsdogs.common.entity.cats.EntityKittenTabby;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenAsiatic;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenExotic;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenNorwegian;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenOcelot;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenRagdoll;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenSiamese;
import com.animania.addons.catsdogs.common.entity.cats.EntityQueenTabby;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomAsiatic;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomExotic;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomNorwegian;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomOcelot;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomRagdoll;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomSiamese;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomTabby;
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
				
		// CATS
		RegistryHelper.Entities.registerAnimal(EntityTomRagdoll.class, "tom_ragdoll", entityID++, CatType.RAGDOLL, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityQueenRagdoll.class, "queen_ragdoll", entityID++, CatType.RAGDOLL, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityKittenRagdoll.class, "kitten_ragdoll", entityID++, CatType.RAGDOLL, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityTomAmericanShorthair.class, "tom_american_shorthair", entityID++, CatType.AMERICAN_SHORTHAIR, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityQueenAmericanShorthair.class, "queen_american_shorthair", entityID++, CatType.AMERICAN_SHORTHAIR, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityKittenAmericanShorthair.class, "kitten_american_shorthair", entityID++, CatType.AMERICAN_SHORTHAIR, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityTomAsiatic.class, "tom_asiatic", entityID++, CatType.ASIATIC, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityQueenAsiatic.class, "queen_asiatic", entityID++, CatType.ASIATIC, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityKittenAsiatic.class, "kitten_asiatic", entityID++, CatType.ASIATIC, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityTomExotic.class, "tom_exotic", entityID++, CatType.EXOTIC, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityQueenExotic.class, "queen_exotic", entityID++, CatType.EXOTIC, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityKittenExotic.class, "kitten_exotic", entityID++, CatType.EXOTIC, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityTomNorwegian.class, "tom_norwegian", entityID++, CatType.NORWEGIAN, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityQueenNorwegian.class, "queen_norwegian", entityID++, CatType.NORWEGIAN, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityKittenNorwegian.class, "kitten_norwegian", entityID++, CatType.NORWEGIAN, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityTomOcelot.class, "tom_ocelot", entityID++, CatType.OCELOT, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityQueenOcelot.class, "queen_ocelot", entityID++, CatType.OCELOT, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityKittenOcelot.class, "kitten_ocelot", entityID++, CatType.OCELOT, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityTomSiamese.class, "tom_siamese", entityID++, CatType.SIAMESE, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityQueenSiamese.class, "queen_siamese", entityID++, CatType.SIAMESE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityKittenSiamese.class, "kitten_siamese", entityID++, CatType.SIAMESE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityTomTabby.class, "tom_tabby", entityID++, CatType.TABBY, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityQueenTabby.class, "queen_tabby", entityID++, CatType.TABBY, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityKittenTabby.class, "kitten_tabby", entityID++, CatType.TABBY, EntityGender.CHILD);		
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
