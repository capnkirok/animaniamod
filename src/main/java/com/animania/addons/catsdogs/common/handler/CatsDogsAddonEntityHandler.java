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
import com.animania.addons.catsdogs.common.entity.dogs.DogType;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleBloodHound;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleChihuahua;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleCollie;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleCorgi;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleDachshund;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleFox;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleGermanShepherd;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleGreatDane;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleGreyhound;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleHusky;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleLabrador;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemalePomeranian;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemalePoodle;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemalePug;
import com.animania.addons.catsdogs.common.entity.dogs.EntityFemaleWolf;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleBloodHound;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleChihuahua;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleCollie;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleCorgi;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleDachshund;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleFox;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleGermanShepherd;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleGreatDane;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleGreyhound;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleHusky;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleLabrador;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMalePomeranian;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMalePoodle;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMalePug;
import com.animania.addons.catsdogs.common.entity.dogs.EntityMaleWolf;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyBloodHound;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyChihuahua;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyCollie;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyCorgi;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyDachshund;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyFox;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyGermanShepherd;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyGreatDane;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyGreyhound;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyHusky;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyLabrador;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyPomeranian;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyPoodle;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyPug;
import com.animania.addons.catsdogs.common.entity.dogs.EntityPuppyWolf;
import com.animania.api.data.EntityGender;
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

		//DOGS
		RegistryHelper.Entities.registerAnimal(EntityMaleBloodHound.class, "male_blood_hound", entityID++, DogType.BLOOD_HOUND, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleBloodHound.class, "female_blood_hound", entityID++, DogType.BLOOD_HOUND, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyBloodHound.class, "puppy_blood_hound", entityID++, DogType.BLOOD_HOUND, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleChihuahua.class, "male_chihuahua", entityID++, DogType.CHIHUAHUA, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleChihuahua.class, "female_chihuahua", entityID++, DogType.CHIHUAHUA, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyChihuahua.class, "puppy_chihuahua", entityID++, DogType.CHIHUAHUA, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleCollie.class, "male_collie", entityID++, DogType.COLLIE, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleCollie.class, "female_collie", entityID++, DogType.COLLIE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyCollie.class, "puppy_collie", entityID++, DogType.COLLIE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleCorgi.class, "male_corgi", entityID++, DogType.CORGI, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleCorgi.class, "female_corgi", entityID++, DogType.CORGI, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyCorgi.class, "puppy_corgi", entityID++, DogType.CORGI, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleDachshund.class, "male_dachshund", entityID++, DogType.DACHSHUND, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleDachshund.class, "female_dachshund", entityID++, DogType.DACHSHUND, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyDachshund.class, "puppy_dachshund", entityID++, DogType.DACHSHUND, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleFox.class, "male_fox", entityID++, DogType.FOX, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleFox.class, "female_fox", entityID++, DogType.FOX, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyFox.class, "puppy_fox", entityID++, DogType.FOX, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleGermanShepherd.class, "male_german_shepherd", entityID++, DogType.GERMAN_SHEPHERD, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleGermanShepherd.class, "female_german_shepherd", entityID++, DogType.GERMAN_SHEPHERD, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyGermanShepherd.class, "puppy_german_shepherd", entityID++, DogType.GERMAN_SHEPHERD, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleGreatDane.class, "male_great_dane", entityID++, DogType.GREAT_DANE, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleGreatDane.class, "female_great_dane", entityID++, DogType.GREAT_DANE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyGreatDane.class, "puppy_great_dane", entityID++, DogType.GREAT_DANE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleGreyhound.class, "male_greyhound", entityID++, DogType.GREYHOUND, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleGreyhound.class, "female_greyhound", entityID++, DogType.GREYHOUND, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyGreyhound.class, "puppy_greyhound", entityID++, DogType.GREYHOUND, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleHusky.class, "male_husky", entityID++, DogType.HUSKY, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleHusky.class, "female_husky", entityID++, DogType.HUSKY, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyHusky.class, "puppy_husky", entityID++, DogType.HUSKY, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleLabrador.class, "male_labrador", entityID++, DogType.LABRADOR, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleLabrador.class, "female_labrador", entityID++, DogType.LABRADOR, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyLabrador.class, "puppy_labrador", entityID++, DogType.LABRADOR, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMalePomeranian.class, "male_pomeranian", entityID++, DogType.POMERANIAN, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemalePomeranian.class, "female_pomeranian", entityID++, DogType.POMERANIAN, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyPomeranian.class, "puppy_pomeranian", entityID++, DogType.POMERANIAN, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMalePoodle.class, "male_poodle", entityID++, DogType.POODLE, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemalePoodle.class, "female_poodle", entityID++, DogType.POODLE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyPoodle.class, "puppy_poodle", entityID++, DogType.POODLE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMalePug.class, "male_pug", entityID++, DogType.PUG, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemalePug.class, "female_pug", entityID++, DogType.PUG, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyPug.class, "puppy_pug", entityID++, DogType.PUG, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityMaleWolf.class, "male_wolf", entityID++, DogType.WOLF, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFemaleWolf.class, "female_wolf", entityID++, DogType.WOLF, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPuppyWolf.class, "puppy_wolf", entityID++, DogType.WOLF, EntityGender.CHILD);

	
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
