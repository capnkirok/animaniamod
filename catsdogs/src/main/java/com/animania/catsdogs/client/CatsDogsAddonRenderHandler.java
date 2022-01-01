package com.animania.catsdogs.client;

import com.animania.Animania;
import com.animania.addons.catsdogs.client.models.cats.*;
import com.animania.addons.catsdogs.client.models.dogs.*;
import com.animania.addons.catsdogs.common.blockentity.BlockEntityPetBowl;
import com.animania.addons.catsdogs.common.blockentity.BlockEntityProp;
import com.animania.addons.catsdogs.common.entity.canids.DogBloodHound.EntityFemaleBloodHound;
import com.animania.addons.catsdogs.common.entity.canids.DogBloodHound.EntityMaleBloodHound;
import com.animania.addons.catsdogs.common.entity.canids.DogBloodHound.EntityPuppyBloodHound;
import com.animania.addons.catsdogs.common.entity.canids.DogChihuahua.EntityFemaleChihuahua;
import com.animania.addons.catsdogs.common.entity.canids.DogChihuahua.EntityMaleChihuahua;
import com.animania.addons.catsdogs.common.entity.canids.DogChihuahua.EntityPuppyChihuahua;
import com.animania.addons.catsdogs.common.entity.canids.DogCollie.EntityFemaleCollie;
import com.animania.addons.catsdogs.common.entity.canids.DogCollie.EntityMaleCollie;
import com.animania.addons.catsdogs.common.entity.canids.DogCollie.EntityPuppyCollie;
import com.animania.addons.catsdogs.common.entity.canids.DogCorgi.EntityFemaleCorgi;
import com.animania.addons.catsdogs.common.entity.canids.DogCorgi.EntityMaleCorgi;
import com.animania.addons.catsdogs.common.entity.canids.DogCorgi.EntityPuppyCorgi;
import com.animania.addons.catsdogs.common.entity.canids.DogDachshund.EntityFemaleDachshund;
import com.animania.addons.catsdogs.common.entity.canids.DogDachshund.EntityMaleDachshund;
import com.animania.addons.catsdogs.common.entity.canids.DogDachshund.EntityPuppyDachshund;
import com.animania.addons.catsdogs.common.entity.canids.DogFox.EntityFemaleFox;
import com.animania.addons.catsdogs.common.entity.canids.DogFox.EntityMaleFox;
import com.animania.addons.catsdogs.common.entity.canids.DogFox.EntityPuppyFox;
import com.animania.addons.catsdogs.common.entity.canids.DogGermanShepherd.EntityFemaleGermanShepherd;
import com.animania.addons.catsdogs.common.entity.canids.DogGermanShepherd.EntityMaleGermanShepherd;
import com.animania.addons.catsdogs.common.entity.canids.DogGermanShepherd.EntityPuppyGermanShepherd;
import com.animania.addons.catsdogs.common.entity.canids.DogGreatDane.EntityFemaleGreatDane;
import com.animania.addons.catsdogs.common.entity.canids.DogGreatDane.EntityMaleGreatDane;
import com.animania.addons.catsdogs.common.entity.canids.DogGreatDane.EntityPuppyGreatDane;
import com.animania.addons.catsdogs.common.entity.canids.DogGreyhound.EntityFemaleGreyhound;
import com.animania.addons.catsdogs.common.entity.canids.DogGreyhound.EntityMaleGreyhound;
import com.animania.addons.catsdogs.common.entity.canids.DogGreyhound.EntityPuppyGreyhound;
import com.animania.addons.catsdogs.common.entity.canids.DogHusky.EntityFemaleHusky;
import com.animania.addons.catsdogs.common.entity.canids.DogHusky.EntityMaleHusky;
import com.animania.addons.catsdogs.common.entity.canids.DogHusky.EntityPuppyHusky;
import com.animania.addons.catsdogs.common.entity.canids.DogLabrador.EntityFemaleLabrador;
import com.animania.addons.catsdogs.common.entity.canids.DogLabrador.EntityMaleLabrador;
import com.animania.addons.catsdogs.common.entity.canids.DogLabrador.EntityPuppyLabrador;
import com.animania.addons.catsdogs.common.entity.canids.DogPomeranian.EntityFemalePomeranian;
import com.animania.addons.catsdogs.common.entity.canids.DogPomeranian.EntityMalePomeranian;
import com.animania.addons.catsdogs.common.entity.canids.DogPomeranian.EntityPuppyPomeranian;
import com.animania.addons.catsdogs.common.entity.canids.DogPoodle.EntityFemalePoodle;
import com.animania.addons.catsdogs.common.entity.canids.DogPoodle.EntityMalePoodle;
import com.animania.addons.catsdogs.common.entity.canids.DogPoodle.EntityPuppyPoodle;
import com.animania.addons.catsdogs.common.entity.canids.DogPug.EntityFemalePug;
import com.animania.addons.catsdogs.common.entity.canids.DogPug.EntityMalePug;
import com.animania.addons.catsdogs.common.entity.canids.DogPug.EntityPuppyPug;
import com.animania.addons.catsdogs.common.entity.canids.DogWolf.EntityFemaleWolf;
import com.animania.addons.catsdogs.common.entity.canids.DogWolf.EntityMaleWolf;
import com.animania.addons.catsdogs.common.entity.canids.DogWolf.EntityPuppyWolf;
import com.animania.addons.catsdogs.common.entity.felids.CatAmericanShorthair.EntityKittenAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.felids.CatAmericanShorthair.EntityQueenAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.felids.CatAmericanShorthair.EntityTomAmericanShorthair;
import com.animania.addons.catsdogs.common.entity.felids.CatAsiatic.EntityKittenAsiatic;
import com.animania.addons.catsdogs.common.entity.felids.CatAsiatic.EntityQueenAsiatic;
import com.animania.addons.catsdogs.common.entity.felids.CatAsiatic.EntityTomAsiatic;
import com.animania.addons.catsdogs.common.entity.felids.CatExotic.EntityKittenExotic;
import com.animania.addons.catsdogs.common.entity.felids.CatExotic.EntityQueenExotic;
import com.animania.addons.catsdogs.common.entity.felids.CatExotic.EntityTomExotic;
import com.animania.addons.catsdogs.common.entity.felids.CatNorwegian.EntityKittenNorwegian;
import com.animania.addons.catsdogs.common.entity.felids.CatNorwegian.EntityQueenNorwegian;
import com.animania.addons.catsdogs.common.entity.felids.CatNorwegian.EntityTomNorwegian;
import com.animania.addons.catsdogs.common.entity.felids.CatOcelot.EntityKittenOcelot;
import com.animania.addons.catsdogs.common.entity.felids.CatOcelot.EntityQueenOcelot;
import com.animania.addons.catsdogs.common.entity.felids.CatOcelot.EntityTomOcelot;
import com.animania.addons.catsdogs.common.entity.felids.CatRagdoll.EntityKittenRagdoll;
import com.animania.addons.catsdogs.common.entity.felids.CatRagdoll.EntityQueenRagdoll;
import com.animania.addons.catsdogs.common.entity.felids.CatRagdoll.EntityTomRagdoll;
import com.animania.addons.catsdogs.common.entity.felids.CatSiamese.EntityKittenSiamese;
import com.animania.addons.catsdogs.common.entity.felids.CatSiamese.EntityQueenSiamese;
import com.animania.addons.catsdogs.common.entity.felids.CatSiamese.EntityTomSiamese;
import com.animania.addons.catsdogs.common.entity.felids.CatTabby.EntityKittenTabby;
import com.animania.addons.catsdogs.common.entity.felids.CatTabby.EntityQueenTabby;
import com.animania.addons.catsdogs.common.entity.felids.CatTabby.EntityTomTabby;
import com.animania.catsdogs.client.render.blocks.BlockEntityPetBowlRenderer;
import com.animania.catsdogs.client.render.blocks.BlockEntityPropRenderer;
import com.animania.catsdogs.client.render.cats.RenderCatGeneric;
import com.animania.catsdogs.client.render.dogs.RenderDogGeneric;
import com.animania.catsdogs.client.render.dogs.RenderFox;
import com.animania.catsdogs.common.handler.CatsDogsAddonBlockHandler;
import com.animania.catsdogs.common.handler.CatsDogsAddonItemHandler;
import com.leviathanstudio.craftstudio.client.registry.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;
import com.leviathanstudio.craftstudio.client.util.EnumRenderType;
import com.leviathanstudio.craftstudio.client.util.EnumResourceType;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CatsDogsAddonRenderHandler
{

	private static final String CATS = "animania:textures/entity/cats/";
	private static final String DOGS = "animania:textures/entity/dogs/";

	public static void preInit()
	{
		// registerCraftStudioAssets();
		renderEntitiesFactory();

		register(CatsDogsAddonBlockHandler.petBowl);
		register(CatsDogsAddonBlockHandler.catBed1);
		register(CatsDogsAddonBlockHandler.catBed2);
		register(CatsDogsAddonBlockHandler.catTower);
		register(CatsDogsAddonBlockHandler.dogHouse);
		register(CatsDogsAddonBlockHandler.dogPillow);
		register(CatsDogsAddonBlockHandler.litterBox);

		register(CatsDogsAddonItemHandler.entityeggrandomcat);
		register(CatsDogsAddonItemHandler.entityeggrandomdog);
	}

	/**
	 * Render TileEntities
	 */
	public static void init()
	{
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityPetBowl.class, new BlockEntityPetBowlRenderer());
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityProp.class, new BlockEntityPropRenderer());
	}

	@CraftStudioLoader
	public static void registerCraftStudioAssets()
	{
		CSRegistryHelper csRegistry = new CSRegistryHelper(Animania.MODID);

		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_pet_bowl");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_cat_bed_1");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_cat_bed_2");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_cat_tower");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_dog_house");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_dog_pillow");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_litter_box");

	}

	@SideOnly(Dist.CLIENT)
	static void renderEntitiesFactory()
	{
		// CATS
		RenderingRegistry.registerEntityRenderingHandler(EntityTomRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.655f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.65f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.635f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.78f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.765f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.35f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.60f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.585f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.59f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.575f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.85f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.82f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.555f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.70f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.68f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.3f));

		// DOGS
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleBloodHound.class, new RenderDogGeneric.Factory(new ModelBloodHound(), r(DOGS + "blood_hound.png"), r(DOGS + "blink_blood_hound.png"), -5938636, 1f, 0, -0.1, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleBloodHound.class, new RenderDogGeneric.Factory(new ModelBloodHound(), r(DOGS + "blood_hound.png"), r(DOGS + "blink_blood_hound.png"), -5938636, 0.9f, 0, -0.1, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyBloodHound.class, new RenderDogGeneric.Factory(new ModelBloodHound(), r(DOGS + "blood_hound.png"), r(DOGS + "blink_blood_hound.png"), -5938636, 0.5f, 0, -0.1, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleChihuahua.class, new RenderDogGeneric.Factory(new ModelChihuahua(), r(DOGS + "chihuahua.png"), r(DOGS + "blink_chihuahua.png"), -593428, 0.6f, 0, 0.1, -0.5, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleChihuahua.class, new RenderDogGeneric.Factory(new ModelChihuahua(), r(DOGS + "chihuahua.png"), r(DOGS + "blink_chihuahua.png"), -593428, 0.5f, 0, 0.1, -0.5, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyChihuahua.class, new RenderDogGeneric.Factory(new ModelChihuahua(), r(DOGS + "chihuahua.png"), r(DOGS + "blink_chihuahua.png"), -593428, 0.3f, 0, 0.1, -0.5, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleCollie.class, new RenderDogGeneric.Factory(new ModelCollie(), r(DOGS + "collie.png"), r(DOGS + "blink_collie.png"), -12570587, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleCollie.class, new RenderDogGeneric.Factory(new ModelCollie(), r(DOGS + "collie.png"), r(DOGS + "blink_collie.png"), -12570587, 0.9f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyCollie.class, new RenderDogGeneric.Factory(new ModelCollie(), r(DOGS + "collie.png"), r(DOGS + "blink_collie.png"), -12570587, 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleCorgi.class, new RenderDogGeneric.Factory(new ModelCorgi(), r(DOGS + "corgi.png"), r(DOGS + "blink_corgi.png"), -263173, 1f, 0, -0.05, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleCorgi.class, new RenderDogGeneric.Factory(new ModelCorgi(), r(DOGS + "corgi.png"), r(DOGS + "blink_corgi.png"), -263173, 0.9f, 0, -0.05, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyCorgi.class, new RenderDogGeneric.Factory(new ModelCorgi(), r(DOGS + "corgi.png"), r(DOGS + "blink_corgi.png"), -263173, 0.5f, 0, -0.05, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleDachshund.class, new RenderDogGeneric.Factory(new ModelDachshund(), r(DOGS + "dachshund.png"), r(DOGS + "blink_dachshund.png"), 0, 1.2f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleDachshund.class, new RenderDogGeneric.Factory(new ModelDachshund(), r(DOGS + "dachshund.png"), r(DOGS + "blink_dachshund.png"), 0, 1.1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyDachshund.class, new RenderDogGeneric.Factory(new ModelDachshund(), r(DOGS + "dachshund.png"), r(DOGS + "blink_dachshund.png"), 0, 0.6f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleFox.class, new RenderFox.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleFox.class, new RenderFox.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyFox.class, new RenderFox.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleGermanShepherd.class, new RenderDogGeneric.Factory(new ModelGermanShepherd(), r(DOGS + "german_shepherd.png"), r(DOGS + "blink_collie.png"), -8300224, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleGermanShepherd.class, new RenderDogGeneric.Factory(new ModelGermanShepherd(), r(DOGS + "german_shepherd.png"), r(DOGS + "blink_collie.png"), -8300224, 0.9f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyGermanShepherd.class, new RenderDogGeneric.Factory(new ModelGermanShepherd(), r(DOGS + "german_shepherd.png"), r(DOGS + "blink_collie.png"), -8300224, 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleGreatDane.class, new RenderDogGeneric.Factory(new ModelGreatDane(), r(DOGS + "great_dane.png"), r(DOGS + "blink_collie.png"), -8300224, 1f, 0, -0.1, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleGreatDane.class, new RenderDogGeneric.Factory(new ModelGreatDane(), r(DOGS + "great_dane.png"), r(DOGS + "blink_collie.png"), -8300224, 0.9f, 0, -0.1, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyGreatDane.class, new RenderDogGeneric.Factory(new ModelGreatDane(), r(DOGS + "great_dane.png"), r(DOGS + "blink_collie.png"), -8300224, 0.5f, 0, -0.1, 0, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleGreyhound.class, new RenderDogGeneric.Factory(new ModelGreyhound(), r(DOGS + "greyhound.png"), r(DOGS + "blink_greyhound.png"), -7578572, 0.8f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleGreyhound.class, new RenderDogGeneric.Factory(new ModelGreyhound(), r(DOGS + "greyhound.png"), r(DOGS + "blink_greyhound.png"), -7578572, 0.7f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyGreyhound.class, new RenderDogGeneric.Factory(new ModelGreyhound(), r(DOGS + "greyhound.png"), r(DOGS + "blink_greyhound.png"), -7578572, 0.4f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleHusky.class, new RenderDogGeneric.Factory(new ModelHusky(), r(DOGS + "husky.png"), r(DOGS + "blink_collie.png"), 0xC4C4C4, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleHusky.class, new RenderDogGeneric.Factory(new ModelHusky(), r(DOGS + "husky.png"), r(DOGS + "blink_collie.png"), 0xC4C4C4, 0.9f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyHusky.class, new RenderDogGeneric.Factory(new ModelHusky(), r(DOGS + "husky.png"), r(DOGS + "blink_collie.png"), 0xC4C4C4, 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleLabrador.class, new RenderDogGeneric.Factory(new ModelLabrador(), r(DOGS + "labrador.png"), r(DOGS + "blink_collie.png"), -4153993, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleLabrador.class, new RenderDogGeneric.Factory(new ModelLabrador(), r(DOGS + "labrador.png"), r(DOGS + "blink_collie.png"), -4153993, 0.9f, 0, 0, 0, dog -> {
			if (((Entity) dog).getCustomNameTag().equals("Gloria"))
				return r(DOGS + "gloria.png");
			return null;
		}));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyLabrador.class, new RenderDogGeneric.Factory(new ModelLabrador(), r(DOGS + "labrador.png"), r(DOGS + "blink_collie.png"), -4153993, 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMalePomeranian.class, new RenderDogGeneric.Factory(new ModelPomeranian(), r(DOGS + "pomeranian.png"), r(DOGS + "blink_pomeranian.png"), -197380, 0.6f, 0, 0, -0.5, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemalePomeranian.class, new RenderDogGeneric.Factory(new ModelPomeranian(), r(DOGS + "pomeranian.png"), r(DOGS + "blink_pomeranian.png"), -197380, 0.5f, 0, 0, -0.5, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyPomeranian.class, new RenderDogGeneric.Factory(new ModelPomeranian(), r(DOGS + "pomeranian.png"), r(DOGS + "blink_pomeranian.png"), -197380, 0.3f, 0, 0, -0.25, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityMalePoodle.class, new RenderDogGeneric.Factory(new ModelPoodle(), r(DOGS + "poodle.png"), r(DOGS + "blink_poodle.png"), -658707, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemalePoodle.class, new RenderDogGeneric.Factory(new ModelPoodle(), r(DOGS + "poodle.png"), r(DOGS + "blink_poodle.png"), -658707, 0.9f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyPoodle.class, new RenderDogGeneric.Factory(new ModelPoodle(), r(DOGS + "poodle.png"), r(DOGS + "blink_poodle.png"), -658707, 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMalePug.class, new RenderDogGeneric.Factory(new ModelPug(), r(DOGS + "pug.png"), r(DOGS + "blink_pug.png"), -1514529, 0.8f, 0, 0, -0.5, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemalePug.class, new RenderDogGeneric.Factory(new ModelPug(), r(DOGS + "pug.png"), r(DOGS + "blink_pug.png"), -1514529, 0.7f, 0, 0, -0.5, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyPug.class, new RenderDogGeneric.Factory(new ModelPug(), r(DOGS + "pug.png"), r(DOGS + "blink_pug.png"), -1514529, 0.4f, 0, 0, -0.25, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleWolf.class, new RenderDogGeneric.Factory(new ModelWolf(), r(DOGS + "wolf.png"), r(DOGS + "blink_collie.png"), -4409680, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleWolf.class, new RenderDogGeneric.Factory(new ModelWolf(), r(DOGS + "wolf.png"), r(DOGS + "blink_collie.png"), -4409680, 0.9f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyWolf.class, new RenderDogGeneric.Factory(new ModelWolf(), r(DOGS + "wolf.png"), r(DOGS + "blink_collie.png"), -4409680, 0.5f));

	}

	@SideOnly(Dist.CLIENT)
	private static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Dist.CLIENT)
	private static void register(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	@SideOnly(Dist.CLIENT)
	private static void register(Item item, String name, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name, "inventory"));
	}

	@SideOnly(Dist.CLIENT)

	private static void registerColored(Item item, String name)
	{
		for (int meta = 0; meta < 16; meta++)
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name + "_" + EnumDyeColor.byDyeDamage(meta).getName(), "inventory"));
	}

	@SideOnly(Dist.CLIENT)
	private static <T extends Entity> void registerEntityRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

	@SideOnly(Dist.CLIENT)
	private static <T extends BlockEntity> void registerBlockEntityRender(Class<T> BlockEntityClass, BlockEntitySpecialRenderer<? super T> specialRenderer)
	{
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityClass, specialRenderer);
	}

	private static ResourceLocation r(String s)
	{
		return new ResourceLocation(s);
	}

}
