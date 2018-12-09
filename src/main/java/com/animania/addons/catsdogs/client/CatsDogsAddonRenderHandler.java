package com.animania.addons.catsdogs.client;

import com.animania.Animania;
import com.animania.addons.catsdogs.client.models.cats.ModelCatAmericanShorthair;
import com.animania.addons.catsdogs.client.models.cats.ModelCatAsiatic;
import com.animania.addons.catsdogs.client.models.cats.ModelCatExotic;
import com.animania.addons.catsdogs.client.models.cats.ModelCatOcelot;
import com.animania.addons.catsdogs.client.models.cats.ModelCatRagdoll;
import com.animania.addons.catsdogs.client.models.cats.ModelCatSiamese;
import com.animania.addons.catsdogs.client.models.cats.ModelCatTabby;
import com.animania.addons.catsdogs.client.models.dogs.ModelBloodHound;
import com.animania.addons.catsdogs.client.models.dogs.ModelChihuahua;
import com.animania.addons.catsdogs.client.models.dogs.ModelCollie;
import com.animania.addons.catsdogs.client.models.dogs.ModelCorgi;
import com.animania.addons.catsdogs.client.models.dogs.ModelDachshund;
import com.animania.addons.catsdogs.client.models.dogs.ModelFox;
import com.animania.addons.catsdogs.client.models.dogs.ModelGermanShepherd;
import com.animania.addons.catsdogs.client.models.dogs.ModelGreatDane;
import com.animania.addons.catsdogs.client.models.dogs.ModelGreyhound;
import com.animania.addons.catsdogs.client.models.dogs.ModelHusky;
import com.animania.addons.catsdogs.client.models.dogs.ModelLabrador;
import com.animania.addons.catsdogs.client.models.dogs.ModelPomeranian;
import com.animania.addons.catsdogs.client.models.dogs.ModelPoodle;
import com.animania.addons.catsdogs.client.models.dogs.ModelPug;
import com.animania.addons.catsdogs.client.models.dogs.ModelWolf;
import com.animania.addons.catsdogs.client.render.blocks.TileEntityPetBowlRenderer;
import com.animania.addons.catsdogs.client.render.blocks.TileEntityPropRenderer;
import com.animania.addons.catsdogs.client.render.cats.RenderCatGeneric;
import com.animania.addons.catsdogs.client.render.dogs.RenderDogGeneric;
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
import com.animania.addons.catsdogs.common.handler.CatsDogsAddonBlockHandler;
import com.animania.addons.catsdogs.common.tileentity.TileEntityPetBowl;
import com.animania.addons.catsdogs.common.tileentity.TileEntityProp;
import com.leviathanstudio.craftstudio.client.registry.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;
import com.leviathanstudio.craftstudio.client.util.EnumRenderType;
import com.leviathanstudio.craftstudio.client.util.EnumResourceType;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
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
	}

	/**
	 * Render TileEntities
	 */
	public static void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPetBowl.class, new TileEntityPetBowlRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityProp.class, new TileEntityPropRenderer());
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

	@SideOnly(Side.CLIENT)
	static void renderEntitiesFactory()
	{
		//CATS
		RenderingRegistry.registerEntityRenderingHandler(EntityTomRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenRagdoll.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "ragdoll.png"), r(CATS + "blink_2.png"), 0x83786D, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenAmericanShorthair.class, new RenderCatGeneric.Factory(new ModelCatAmericanShorthair(), r(CATS + "american_shorthair.png"), r(CATS + "blink_1.png"), 0x7D7D7D, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenAsiatic.class, new RenderCatGeneric.Factory(new ModelCatAsiatic(), r(CATS + "asiatic.png"), r(CATS + "blink_1.png"), 0x836951, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenExotic.class, new RenderCatGeneric.Factory(new ModelCatExotic(), r(CATS + "exotic.png"), r(CATS + "blink_1.png"), 0xA75823, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenNorwegian.class, new RenderCatGeneric.Factory(new ModelCatRagdoll(), r(CATS + "norwegian.png"), r(CATS + "blink_2.png"), 0x4E3C30, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenOcelot.class, new RenderCatGeneric.Factory(new ModelCatOcelot(), r(CATS + "ocelot.png"), r(CATS + "blink_1.png"), 0xA47947, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenSiamese.class, new RenderCatGeneric.Factory(new ModelCatSiamese(), r(CATS + "siamese.png"), r(CATS + "blink_1.png"), 0x271D1B, 0.37f));
		RenderingRegistry.registerEntityRenderingHandler(EntityTomTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.67f));
		RenderingRegistry.registerEntityRenderingHandler(EntityQueenTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.57f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKittenTabby.class, new RenderCatGeneric.Factory(new ModelCatTabby(), r(CATS + "tabby.png"), r(CATS + "blink_1.png"), 0x594336, 0.37f));

		//DOGS
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleBloodHound.class, new RenderDogGeneric.Factory(new ModelBloodHound(), r(DOGS + "blood_hound.png"), r(DOGS + "blink_blood_hound.png"), -5938636, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleBloodHound.class, new RenderDogGeneric.Factory(new ModelBloodHound(), r(DOGS + "blood_hound.png"), r(DOGS + "blink_blood_hound.png"), -5938636, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyBloodHound.class, new RenderDogGeneric.Factory(new ModelBloodHound(), r(DOGS + "blood_hound.png"), r(DOGS + "blink_blood_hound.png"), -5938636, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleChihuahua.class, new RenderDogGeneric.Factory(new ModelChihuahua(), r(DOGS + "chihuahua.png"), r(DOGS + "blink_chihuahua.png"), -593428, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleChihuahua.class, new RenderDogGeneric.Factory(new ModelChihuahua(), r(DOGS + "chihuahua.png"), r(DOGS + "blink_chihuahua.png"), -593428, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyChihuahua.class, new RenderDogGeneric.Factory(new ModelChihuahua(), r(DOGS + "chihuahua.png"), r(DOGS + "blink_chihuahua.png"), -593428, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleCollie.class, new RenderDogGeneric.Factory(new ModelCollie(), r(DOGS + "collie.png"), r(DOGS + "blink_collie.png"), -12570587, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleCollie.class, new RenderDogGeneric.Factory(new ModelCollie(), r(DOGS + "collie.png"), r(DOGS + "blink_collie.png"), -12570587, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyCollie.class, new RenderDogGeneric.Factory(new ModelCollie(), r(DOGS + "collie.png"), r(DOGS + "blink_collie.png"), -12570587, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleCorgi.class, new RenderDogGeneric.Factory(new ModelCorgi(), r(DOGS + "corgi.png"), r(DOGS + "blink_corgi.png"), -263173, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleCorgi.class, new RenderDogGeneric.Factory(new ModelCorgi(), r(DOGS + "corgi.png"), r(DOGS + "blink_corgi.png"), -263173, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyCorgi.class, new RenderDogGeneric.Factory(new ModelCorgi(), r(DOGS + "corgi.png"), r(DOGS + "blink_corgi.png"), -263173, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleDachshund.class, new RenderDogGeneric.Factory(new ModelDachshund(), r(DOGS + "dachshund.png"), r(DOGS + "blink_dachshund.png"), -197380, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleDachshund.class, new RenderDogGeneric.Factory(new ModelDachshund(), r(DOGS + "dachshund.png"), r(DOGS + "blink_dachshund.png"), -197380, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyDachshund.class, new RenderDogGeneric.Factory(new ModelDachshund(), r(DOGS + "dachshund.png"), r(DOGS + "blink_dachshund.png"), -197380, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleFox.class, new RenderDogGeneric.Factory(new ModelFox(), r(DOGS + "fox.png"), r(DOGS + "blink_fox.png"), -5415620, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleFox.class, new RenderDogGeneric.Factory(new ModelFox(), r(DOGS + "fox.png"), r(DOGS + "blink_fox.png"), -5415620, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyFox.class, new RenderDogGeneric.Factory(new ModelFox(), r(DOGS + "fox.png"), r(DOGS + "blink_fox.png"), -5415620, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleGermanShepherd.class, new RenderDogGeneric.Factory(new ModelGermanShepherd(), r(DOGS + "german_shepherd.png"), r(DOGS + "blink_collie.png"), -8300224, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleGermanShepherd.class, new RenderDogGeneric.Factory(new ModelGermanShepherd(), r(DOGS + "german_shepherd.png"), r(DOGS + "blink_collie.png"), -8300224, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyGermanShepherd.class, new RenderDogGeneric.Factory(new ModelGermanShepherd(), r(DOGS + "german_shepherd.png"), r(DOGS + "blink_collie.png"), -8300224, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleGreatDane.class, new RenderDogGeneric.Factory(new ModelGreatDane(), r(DOGS + "great_dane.png"), r(DOGS + "blink_collie.png"), -8300224, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleGreatDane.class, new RenderDogGeneric.Factory(new ModelGreatDane(), r(DOGS + "great_dane.png"), r(DOGS + "blink_collie.png"), -8300224, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyGreatDane.class, new RenderDogGeneric.Factory(new ModelGreatDane(), r(DOGS + "great_dane.png"), r(DOGS + "blink_collie.png"), -8300224, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleGreyhound.class, new RenderDogGeneric.Factory(new ModelGreyhound(), r(DOGS + "greyhound.png"), r(DOGS + "blink_greyhound.png"), -7578572, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleGreyhound.class, new RenderDogGeneric.Factory(new ModelGreyhound(), r(DOGS + "greyhound.png"), r(DOGS + "blink_greyhound.png"), -7578572, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyGreyhound.class, new RenderDogGeneric.Factory(new ModelGreyhound(), r(DOGS + "greyhound.png"), r(DOGS + "blink_greyhound.png"), -7578572, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleHusky.class, new RenderDogGeneric.Factory(new ModelHusky(), r(DOGS + "husky.png"), r(DOGS + "blink_collie.png"), -14606304, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleHusky.class, new RenderDogGeneric.Factory(new ModelHusky(), r(DOGS + "husky.png"), r(DOGS + "blink_collie.png"), -14606304, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyHusky.class, new RenderDogGeneric.Factory(new ModelHusky(), r(DOGS + "husky.png"), r(DOGS + "blink_collie.png"), -14606304, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleLabrador.class, new RenderDogGeneric.Factory(new ModelLabrador(), r(DOGS + "labrador.png"), r(DOGS + "blink_collie.png"), -4153993, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleLabrador.class, new RenderDogGeneric.Factory(new ModelLabrador(), r(DOGS + "labrador.png"), r(DOGS + "blink_collie.png"), -4153993, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyLabrador.class, new RenderDogGeneric.Factory(new ModelLabrador(), r(DOGS + "labrador.png"), r(DOGS + "blink_collie.png"), -4153993, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMalePomeranian.class, new RenderDogGeneric.Factory(new ModelPomeranian(), r(DOGS + "pomeranian.png"), r(DOGS + "blink_pomeranian.png"), -197380, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemalePomeranian.class, new RenderDogGeneric.Factory(new ModelPomeranian(), r(DOGS + "pomeranian.png"), r(DOGS + "blink_pomeranian.png"), -197380, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyPomeranian.class, new RenderDogGeneric.Factory(new ModelPomeranian(), r(DOGS + "pomeranian.png"), r(DOGS + "blink_pomeranian.png"), -197380, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMalePoodle.class, new RenderDogGeneric.Factory(new ModelPoodle(), r(DOGS + "poodle.png"), r(DOGS + "blink_poodle.png"), -658707, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemalePoodle.class, new RenderDogGeneric.Factory(new ModelPoodle(), r(DOGS + "poodle.png"), r(DOGS + "blink_poodle.png"), -658707, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyPoodle.class, new RenderDogGeneric.Factory(new ModelPoodle(), r(DOGS + "poodle.png"), r(DOGS + "blink_poodle.png"), -658707, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMalePug.class, new RenderDogGeneric.Factory(new ModelPug(), r(DOGS + "pug.png"), r(DOGS + "blink_pug.png"), -1514529, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemalePug.class, new RenderDogGeneric.Factory(new ModelPug(), r(DOGS + "pug.png"), r(DOGS + "blink_pug.png"), -1514529, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyPug.class, new RenderDogGeneric.Factory(new ModelPug(), r(DOGS + "pug.png"), r(DOGS + "blink_pug.png"), -1514529, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityMaleWolf.class, new RenderDogGeneric.Factory(new ModelWolf(), r(DOGS + "wolf.png"), r(DOGS + "blink_collie.png"), -4409680, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFemaleWolf.class, new RenderDogGeneric.Factory(new ModelWolf(), r(DOGS + "wolf.png"), r(DOGS + "blink_collie.png"), -4409680, 1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPuppyWolf.class, new RenderDogGeneric.Factory(new ModelWolf(), r(DOGS + "wolf.png"), r(DOGS + "blink_collie.png"), -4409680, 1f));

	}

	@SideOnly(Side.CLIENT)
	private static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void register(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void register(Item item, String name, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name, "inventory"));
	}

	@SideOnly(Side.CLIENT)

	private static void registerColored(Item item, String name)
	{
		for (int meta = 0; meta < 16; meta++)
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name + "_" + EnumDyeColor.byDyeDamage(meta).getName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static <T extends Entity> void registerEntityRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

	@SideOnly(Side.CLIENT)
	private static <T extends TileEntity> void registerTileEntityRender(Class<T> tileEntityClass, TileEntitySpecialRenderer<? super T> specialRenderer)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}

	private static ResourceLocation r(String s)
	{
		return new ResourceLocation(s);
	}

}
