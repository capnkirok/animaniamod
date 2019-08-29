package com.animania.client.handler;

import com.animania.client.render.amphibians.RenderDartFrogs;
import com.animania.client.render.amphibians.RenderFrogs;
import com.animania.client.render.amphibians.RenderToad;
import com.animania.client.render.chickens.RenderChickBase;
import com.animania.client.render.chickens.RenderHenBase;
import com.animania.client.render.chickens.RenderRoosterBase;
import com.animania.client.render.cows.RenderBullAngus;
import com.animania.client.render.cows.RenderBullFriesian;
import com.animania.client.render.cows.RenderBullHereford;
import com.animania.client.render.cows.RenderBullHighland;
import com.animania.client.render.cows.RenderBullHolstein;
import com.animania.client.render.cows.RenderBullJersey;
import com.animania.client.render.cows.RenderBullLonghorn;
import com.animania.client.render.cows.RenderBullMooshroom;
import com.animania.client.render.cows.RenderCalfAngus;
import com.animania.client.render.cows.RenderCalfFriesian;
import com.animania.client.render.cows.RenderCalfHereford;
import com.animania.client.render.cows.RenderCalfHighland;
import com.animania.client.render.cows.RenderCalfHolstein;
import com.animania.client.render.cows.RenderCalfJersey;
import com.animania.client.render.cows.RenderCalfLonghorn;
import com.animania.client.render.cows.RenderCalfMooshroom;
import com.animania.client.render.cows.RenderCowAngus;
import com.animania.client.render.cows.RenderCowFriesian;
import com.animania.client.render.cows.RenderCowHereford;
import com.animania.client.render.cows.RenderCowHighland;
import com.animania.client.render.cows.RenderCowHolstein;
import com.animania.client.render.cows.RenderCowJersey;
import com.animania.client.render.cows.RenderCowLonghorn;
import com.animania.client.render.cows.RenderCowMooshroom;
import com.animania.client.render.goats.RenderBuckAlpine;
import com.animania.client.render.goats.RenderBuckAngora;
import com.animania.client.render.goats.RenderBuckFainting;
import com.animania.client.render.goats.RenderBuckKiko;
import com.animania.client.render.goats.RenderBuckKinder;
import com.animania.client.render.goats.RenderBuckNigerianDwarf;
import com.animania.client.render.goats.RenderBuckPygmy;
import com.animania.client.render.goats.RenderDoeAlpine;
import com.animania.client.render.goats.RenderDoeAngora;
import com.animania.client.render.goats.RenderDoeFainting;
import com.animania.client.render.goats.RenderDoeKiko;
import com.animania.client.render.goats.RenderDoeKinder;
import com.animania.client.render.goats.RenderDoeNigerianDwarf;
import com.animania.client.render.goats.RenderDoePygmy;
import com.animania.client.render.goats.RenderKidAlpine;
import com.animania.client.render.goats.RenderKidAngora;
import com.animania.client.render.goats.RenderKidFainting;
import com.animania.client.render.goats.RenderKidKiko;
import com.animania.client.render.goats.RenderKidKinder;
import com.animania.client.render.goats.RenderKidNigerianDwarf;
import com.animania.client.render.goats.RenderKidPygmy;
import com.animania.client.render.horses.RenderFoalDraftHorse;
import com.animania.client.render.horses.RenderMareDraftHorse;
import com.animania.client.render.horses.RenderStallionDraftHorse;
import com.animania.client.render.peacocks.RenderPeachickBase;
import com.animania.client.render.peacocks.RenderPeacockBase;
import com.animania.client.render.peacocks.RenderPeafowlBase;
import com.animania.client.render.pigs.RenderHogDuroc;
import com.animania.client.render.pigs.RenderHogHampshire;
import com.animania.client.render.pigs.RenderHogLargeBlack;
import com.animania.client.render.pigs.RenderHogLargeWhite;
import com.animania.client.render.pigs.RenderHogOldSpot;
import com.animania.client.render.pigs.RenderHogYorkshire;
import com.animania.client.render.pigs.RenderPigletDuroc;
import com.animania.client.render.pigs.RenderPigletHampshire;
import com.animania.client.render.pigs.RenderPigletLargeBlack;
import com.animania.client.render.pigs.RenderPigletLargeWhite;
import com.animania.client.render.pigs.RenderPigletOldSpot;
import com.animania.client.render.pigs.RenderPigletYorkshire;
import com.animania.client.render.pigs.RenderSowDuroc;
import com.animania.client.render.pigs.RenderSowHampshire;
import com.animania.client.render.pigs.RenderSowLargeBlack;
import com.animania.client.render.pigs.RenderSowLargeWhite;
import com.animania.client.render.pigs.RenderSowOldSpot;
import com.animania.client.render.pigs.RenderSowYorkshire;
import com.animania.client.render.props.RenderCart;
import com.animania.client.render.props.RenderTiller;
import com.animania.client.render.props.RenderWagon;
import com.animania.client.render.rabbits.RenderBuckChinchilla;
import com.animania.client.render.rabbits.RenderBuckCottontail;
import com.animania.client.render.rabbits.RenderBuckDutch;
import com.animania.client.render.rabbits.RenderBuckHavana;
import com.animania.client.render.rabbits.RenderBuckJack;
import com.animania.client.render.rabbits.RenderBuckLop;
import com.animania.client.render.rabbits.RenderBuckNewZealand;
import com.animania.client.render.rabbits.RenderBuckRex;
import com.animania.client.render.rabbits.RenderDoeChinchilla;
import com.animania.client.render.rabbits.RenderDoeCottontail;
import com.animania.client.render.rabbits.RenderDoeDutch;
import com.animania.client.render.rabbits.RenderDoeHavana;
import com.animania.client.render.rabbits.RenderDoeJack;
import com.animania.client.render.rabbits.RenderDoeLop;
import com.animania.client.render.rabbits.RenderDoeNewZealand;
import com.animania.client.render.rabbits.RenderDoeRex;
import com.animania.client.render.rabbits.RenderKitChinchilla;
import com.animania.client.render.rabbits.RenderKitCottontail;
import com.animania.client.render.rabbits.RenderKitDutch;
import com.animania.client.render.rabbits.RenderKitHavana;
import com.animania.client.render.rabbits.RenderKitJack;
import com.animania.client.render.rabbits.RenderKitLop;
import com.animania.client.render.rabbits.RenderKitNewZealand;
import com.animania.client.render.rabbits.RenderKitRex;
import com.animania.client.render.rodents.RenderFerretGrey;
import com.animania.client.render.rodents.RenderFerretWhite;
import com.animania.client.render.rodents.RenderHamster;
import com.animania.client.render.rodents.RenderHedgehog;
import com.animania.client.render.rodents.RenderHedgehogAlbino;
import com.animania.client.render.sheep.RenderEweDorper;
import com.animania.client.render.sheep.RenderEweDorset;
import com.animania.client.render.sheep.RenderEweFriesian;
import com.animania.client.render.sheep.RenderEweJacob;
import com.animania.client.render.sheep.RenderEweMerino;
import com.animania.client.render.sheep.RenderEweSuffolk;
import com.animania.client.render.sheep.RenderLambDorper;
import com.animania.client.render.sheep.RenderLambDorset;
import com.animania.client.render.sheep.RenderLambFriesian;
import com.animania.client.render.sheep.RenderLambJacob;
import com.animania.client.render.sheep.RenderLambMerino;
import com.animania.client.render.sheep.RenderLambSuffolk;
import com.animania.client.render.sheep.RenderRamDorper;
import com.animania.client.render.sheep.RenderRamDorset;
import com.animania.client.render.sheep.RenderRamFriesian;
import com.animania.client.render.sheep.RenderRamJacob;
import com.animania.client.render.sheep.RenderRamMerino;
import com.animania.client.render.sheep.RenderRamSuffolk;
import com.animania.client.render.tileEntity.TileEntityHamsterWheelRenderer;
import com.animania.client.render.tileEntity.TileEntityHiveRenderer;
import com.animania.client.render.tileEntity.TileEntityNestRenderer;
import com.animania.client.render.tileEntity.TileEntitySaltLickRenderer;
import com.animania.client.render.tileEntity.TileEntityTroughRenderer;
import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.common.entities.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.ChickenOrpington.EntityChickOrpington;
import com.animania.common.entities.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.common.entities.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityChickWyandotte;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityRoosterWyandotte;
import com.animania.common.entities.cows.CowAngus.EntityBullAngus;
import com.animania.common.entities.cows.CowAngus.EntityCalfAngus;
import com.animania.common.entities.cows.CowAngus.EntityCowAngus;
import com.animania.common.entities.cows.CowFriesian.EntityBullFriesian;
import com.animania.common.entities.cows.CowFriesian.EntityCalfFriesian;
import com.animania.common.entities.cows.CowFriesian.EntityCowFriesian;
import com.animania.common.entities.cows.CowHereford.EntityBullHereford;
import com.animania.common.entities.cows.CowHereford.EntityCalfHereford;
import com.animania.common.entities.cows.CowHereford.EntityCowHereford;
import com.animania.common.entities.cows.CowHighland.EntityBullHighland;
import com.animania.common.entities.cows.CowHighland.EntityCalfHighland;
import com.animania.common.entities.cows.CowHighland.EntityCowHighland;
import com.animania.common.entities.cows.CowHolstein.EntityBullHolstein;
import com.animania.common.entities.cows.CowHolstein.EntityCalfHolstein;
import com.animania.common.entities.cows.CowHolstein.EntityCowHolstein;
import com.animania.common.entities.cows.CowJersey.EntityBullJersey;
import com.animania.common.entities.cows.CowJersey.EntityCalfJersey;
import com.animania.common.entities.cows.CowJersey.EntityCowJersey;
import com.animania.common.entities.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.common.entities.cows.CowLonghorn.EntityCalfLonghorn;
import com.animania.common.entities.cows.CowLonghorn.EntityCowLonghorn;
import com.animania.common.entities.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.common.entities.cows.CowMooshroom.EntityCalfMooshroom;
import com.animania.common.entities.cows.CowMooshroom.EntityCowMooshroom;
import com.animania.common.entities.goats.GoatAlpine.EntityBuckAlpine;
import com.animania.common.entities.goats.GoatAlpine.EntityDoeAlpine;
import com.animania.common.entities.goats.GoatAlpine.EntityKidAlpine;
import com.animania.common.entities.goats.GoatAngora.EntityBuckAngora;
import com.animania.common.entities.goats.GoatAngora.EntityDoeAngora;
import com.animania.common.entities.goats.GoatAngora.EntityKidAngora;
import com.animania.common.entities.goats.GoatFainting.EntityBuckFainting;
import com.animania.common.entities.goats.GoatFainting.EntityDoeFainting;
import com.animania.common.entities.goats.GoatFainting.EntityKidFainting;
import com.animania.common.entities.goats.GoatKiko.EntityBuckKiko;
import com.animania.common.entities.goats.GoatKiko.EntityDoeKiko;
import com.animania.common.entities.goats.GoatKiko.EntityKidKiko;
import com.animania.common.entities.goats.GoatKinder.EntityBuckKinder;
import com.animania.common.entities.goats.GoatKinder.EntityDoeKinder;
import com.animania.common.entities.goats.GoatKinder.EntityKidKinder;
import com.animania.common.entities.goats.GoatNigerianDwarf.EntityBuckNigerianDwarf;
import com.animania.common.entities.goats.GoatNigerianDwarf.EntityDoeNigerianDwarf;
import com.animania.common.entities.goats.GoatNigerianDwarf.EntityKidNigerianDwarf;
import com.animania.common.entities.goats.GoatPygmy.EntityBuckPygmy;
import com.animania.common.entities.goats.GoatPygmy.EntityDoePygmy;
import com.animania.common.entities.goats.GoatPygmy.EntityKidPygmy;
import com.animania.common.entities.horses.HorseDraft.EntityFoalDraftHorse;
import com.animania.common.entities.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.common.entities.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeachickBlue;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeacockBlue;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeachickCharcoal;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeacockCharcoal;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeafowlCharcoal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeachickOpal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeacockOpal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeafowlOpal;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeachickPeach;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeacockPeach;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeafowlPeach;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeachickPurple;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeacockPurple;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeafowlPurple;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeachickTaupe;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeacockTaupe;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeafowlTaupe;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeachickWhite;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeacockWhite;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeafowlWhite;
import com.animania.common.entities.pigs.PigDuroc.EntityHogDuroc;
import com.animania.common.entities.pigs.PigDuroc.EntityPigletDuroc;
import com.animania.common.entities.pigs.PigDuroc.EntitySowDuroc;
import com.animania.common.entities.pigs.PigHampshire.EntityHogHampshire;
import com.animania.common.entities.pigs.PigHampshire.EntityPigletHampshire;
import com.animania.common.entities.pigs.PigHampshire.EntitySowHampshire;
import com.animania.common.entities.pigs.PigLargeBlack.EntityHogLargeBlack;
import com.animania.common.entities.pigs.PigLargeBlack.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.PigLargeBlack.EntitySowLargeBlack;
import com.animania.common.entities.pigs.PigLargeWhite.EntityHogLargeWhite;
import com.animania.common.entities.pigs.PigLargeWhite.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.PigLargeWhite.EntitySowLargeWhite;
import com.animania.common.entities.pigs.PigOldSpot.EntityHogOldSpot;
import com.animania.common.entities.pigs.PigOldSpot.EntityPigletOldSpot;
import com.animania.common.entities.pigs.PigOldSpot.EntitySowOldSpot;
import com.animania.common.entities.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.common.entities.pigs.PigYorkshire.EntityPigletYorkshire;
import com.animania.common.entities.pigs.PigYorkshire.EntitySowYorkshire;
import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityTiller;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.common.entities.rodents.rabbits.RabbitChinchilla.EntityRabbitBuckChinchilla;
import com.animania.common.entities.rodents.rabbits.RabbitChinchilla.EntityRabbitDoeChinchilla;
import com.animania.common.entities.rodents.rabbits.RabbitChinchilla.EntityRabbitKitChinchilla;
import com.animania.common.entities.rodents.rabbits.RabbitCottonail.EntityRabbitBuckCottontail;
import com.animania.common.entities.rodents.rabbits.RabbitCottonail.EntityRabbitDoeCottontail;
import com.animania.common.entities.rodents.rabbits.RabbitCottonail.EntityRabbitKitCottontail;
import com.animania.common.entities.rodents.rabbits.RabbitDutch.EntityRabbitBuckDutch;
import com.animania.common.entities.rodents.rabbits.RabbitDutch.EntityRabbitDoeDutch;
import com.animania.common.entities.rodents.rabbits.RabbitDutch.EntityRabbitKitDutch;
import com.animania.common.entities.rodents.rabbits.RabbitHavana.EntityRabbitBuckHavana;
import com.animania.common.entities.rodents.rabbits.RabbitHavana.EntityRabbitDoeHavana;
import com.animania.common.entities.rodents.rabbits.RabbitHavana.EntityRabbitKitHavana;
import com.animania.common.entities.rodents.rabbits.RabbitJack.EntityRabbitBuckJack;
import com.animania.common.entities.rodents.rabbits.RabbitJack.EntityRabbitDoeJack;
import com.animania.common.entities.rodents.rabbits.RabbitJack.EntityRabbitKitJack;
import com.animania.common.entities.rodents.rabbits.RabbitLop.EntityRabbitBuckLop;
import com.animania.common.entities.rodents.rabbits.RabbitLop.EntityRabbitDoeLop;
import com.animania.common.entities.rodents.rabbits.RabbitLop.EntityRabbitKitLop;
import com.animania.common.entities.rodents.rabbits.RabbitNewZealand.EntityRabbitBuckNewZealand;
import com.animania.common.entities.rodents.rabbits.RabbitNewZealand.EntityRabbitDoeNewZealand;
import com.animania.common.entities.rodents.rabbits.RabbitNewZealand.EntityRabbitKitNewZealand;
import com.animania.common.entities.rodents.rabbits.RabbitRex.EntityRabbitBuckRex;
import com.animania.common.entities.rodents.rabbits.RabbitRex.EntityRabbitDoeRex;
import com.animania.common.entities.rodents.rabbits.RabbitRex.EntityRabbitKitRex;
import com.animania.common.entities.sheep.SheepDorper.EntityEweDorper;
import com.animania.common.entities.sheep.SheepDorper.EntityLambDorper;
import com.animania.common.entities.sheep.SheepDorper.EntityRamDorper;
import com.animania.common.entities.sheep.SheepDorset.EntityEweDorset;
import com.animania.common.entities.sheep.SheepDorset.EntityLambDorset;
import com.animania.common.entities.sheep.SheepDorset.EntityRamDorset;
import com.animania.common.entities.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.common.entities.sheep.SheepFriesian.EntityLambFriesian;
import com.animania.common.entities.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.common.entities.sheep.SheepJacob.EntityEweJacob;
import com.animania.common.entities.sheep.SheepJacob.EntityLambJacob;
import com.animania.common.entities.sheep.SheepJacob.EntityRamJacob;
import com.animania.common.entities.sheep.SheepMerino.EntityEweMerino;
import com.animania.common.entities.sheep.SheepMerino.EntityLambMerino;
import com.animania.common.entities.sheep.SheepMerino.EntityRamMerino;
import com.animania.common.entities.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.common.entities.sheep.SheepSuffolk.EntityLambSuffolk;
import com.animania.common.entities.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.common.tileentities.TileEntityHamsterWheel;
import com.animania.common.tileentities.TileEntityHive;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntitySaltLick;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler
{

	public static void preInit()
	{
		renderEntitiesFactory();
	}

	public static void init()
	{
		renderTileEntity();
	}

	@SideOnly(Side.CLIENT)
	static void renderEntitiesFactory()
	{

		// Rabbits
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckCottontail.class, RenderBuckCottontail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeCottontail.class, RenderDoeCottontail.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitCottontail.class, RenderKitCottontail.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckChinchilla.class, RenderBuckChinchilla.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeChinchilla.class, RenderDoeChinchilla.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitChinchilla.class, RenderKitChinchilla.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckDutch.class, RenderBuckDutch.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeDutch.class, RenderDoeDutch.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitDutch.class, RenderKitDutch.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckHavana.class, RenderBuckHavana.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeHavana.class, RenderDoeHavana.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitHavana.class, RenderKitHavana.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckJack.class, RenderBuckJack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeJack.class, RenderDoeJack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitJack.class, RenderKitJack.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckNewZealand.class, RenderBuckNewZealand.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeNewZealand.class, RenderDoeNewZealand.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitNewZealand.class, RenderKitNewZealand.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckRex.class, RenderBuckRex.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeRex.class, RenderDoeRex.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitRex.class, RenderKitRex.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckLop.class, RenderBuckLop.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeLop.class, RenderDoeLop.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbitKitLop.class, RenderKitLop.FACTORY);

		// Sheep
		RenderingRegistry.registerEntityRenderingHandler(EntityRamFriesian.class, RenderRamFriesian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEweFriesian.class, RenderEweFriesian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLambFriesian.class, RenderLambFriesian.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRamSuffolk.class, RenderRamSuffolk.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEweSuffolk.class, RenderEweSuffolk.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLambSuffolk.class, RenderLambSuffolk.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRamDorper.class, RenderRamDorper.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEweDorper.class, RenderEweDorper.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLambDorper.class, RenderLambDorper.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRamDorset.class, RenderRamDorset.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEweDorset.class, RenderEweDorset.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLambDorset.class, RenderLambDorset.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRamMerino.class, RenderRamMerino.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEweMerino.class, RenderEweMerino.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLambMerino.class, RenderLambMerino.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityRamJacob.class, RenderRamJacob.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityEweJacob.class, RenderEweJacob.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLambJacob.class, RenderLambJacob.FACTORY);

		// Props
		RenderingRegistry.registerEntityRenderingHandler(EntityWagon.class, RenderWagon.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCart.class, RenderCart.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTiller.class, RenderTiller.FACTORY);

		// Goats
		RenderingRegistry.registerEntityRenderingHandler(EntityKidAlpine.class, RenderKidAlpine.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckAlpine.class, RenderBuckAlpine.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeAlpine.class, RenderDoeAlpine.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidAngora.class, RenderKidAngora.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckAngora.class, RenderBuckAngora.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeAngora.class, RenderDoeAngora.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidFainting.class, RenderKidFainting.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckFainting.class, RenderBuckFainting.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeFainting.class, RenderDoeFainting.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidKiko.class, RenderKidKiko.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckKiko.class, RenderBuckKiko.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeKiko.class, RenderDoeKiko.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidKinder.class, RenderKidKinder.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckKinder.class, RenderBuckKinder.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeKinder.class, RenderDoeKinder.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidNigerianDwarf.class, RenderKidNigerianDwarf.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckNigerianDwarf.class, RenderBuckNigerianDwarf.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoeNigerianDwarf.class, RenderDoeNigerianDwarf.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityKidPygmy.class, RenderKidPygmy.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBuckPygmy.class, RenderBuckPygmy.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoePygmy.class, RenderDoePygmy.FACTORY);

		// Frogs
		RenderingRegistry.registerEntityRenderingHandler(EntityFrogs.class, RenderFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDartFrogs.class, RenderDartFrogs.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, RenderToad.FACTORY);

		// Chickens
		RenderingRegistry.registerEntityRenderingHandler(EntityHenPlymouthRock.class, RenderHenBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterPlymouthRock.class, RenderRoosterBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickPlymouthRock.class, RenderChickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenLeghorn.class, RenderHenBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterLeghorn.class, RenderRoosterBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickWyandotte.class, RenderChickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenOrpington.class, RenderHenBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterOrpington.class, RenderRoosterBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickOrpington.class, RenderChickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenWyandotte.class, RenderHenBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterWyandotte.class, RenderRoosterBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickWyandotte.class, RenderChickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenRhodeIslandRed.class, RenderHenBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterRhodeIslandRed.class, RenderRoosterBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickRhodeIslandRed.class, RenderChickBase.FACTORY);

		// Cows
		RenderingRegistry.registerEntityRenderingHandler(EntityCowHolstein.class, RenderCowHolstein.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHolstein.class, RenderBullHolstein.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHolstein.class, RenderCalfHolstein.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowFriesian.class, RenderCowFriesian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullFriesian.class, RenderBullFriesian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfFriesian.class, RenderCalfFriesian.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowAngus.class, RenderCowAngus.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullAngus.class, RenderBullAngus.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfAngus.class, RenderCalfAngus.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowLonghorn.class, RenderCowLonghorn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullLonghorn.class, RenderBullLonghorn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfLonghorn.class, RenderCalfLonghorn.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowHereford.class, RenderCowHereford.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHereford.class, RenderBullHereford.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHereford.class, RenderCalfHereford.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowHighland.class, RenderCowHighland.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHighland.class, RenderBullHighland.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHighland.class, RenderCalfHighland.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowJersey.class, RenderCowJersey.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullJersey.class, RenderBullJersey.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfJersey.class, RenderCalfJersey.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityCowMooshroom.class, RenderCowMooshroom.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullMooshroom.class, RenderBullMooshroom.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfMooshroom.class, RenderCalfMooshroom.FACTORY);

		// Pigs
		RenderingRegistry.registerEntityRenderingHandler(EntitySowYorkshire.class, RenderSowYorkshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogYorkshire.class, RenderHogYorkshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletYorkshire.class, RenderPigletYorkshire.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowOldSpot.class, RenderSowOldSpot.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogOldSpot.class, RenderHogOldSpot.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletOldSpot.class, RenderPigletOldSpot.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeBlack.class, RenderSowLargeBlack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeBlack.class, RenderHogLargeBlack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletLargeBlack.class, RenderPigletLargeBlack.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeWhite.class, RenderSowLargeWhite.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeWhite.class, RenderHogLargeWhite.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletLargeWhite.class, RenderPigletLargeWhite.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowDuroc.class, RenderSowDuroc.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogDuroc.class, RenderHogDuroc.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletDuroc.class, RenderPigletDuroc.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowHampshire.class, RenderSowHampshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogHampshire.class, RenderHogHampshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPigletHampshire.class, RenderPigletHampshire.FACTORY);

		// Rodents
		RenderingRegistry.registerEntityRenderingHandler(EntityHamster.class, RenderHamster.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehog.class, RenderHedgehog.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHedgehogAlbino.class, RenderHedgehogAlbino.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretGrey.class, RenderFerretGrey.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFerretWhite.class, RenderFerretWhite.FACTORY);

		// Peacocks
		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockBlue.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlBlue.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickBlue.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockWhite.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlWhite.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickWhite.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockCharcoal.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlCharcoal.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickCharcoal.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockOpal.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlOpal.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickOpal.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockPeach.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPeach.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickPeach.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockPurple.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPurple.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickPurple.class, RenderPeachickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityPeacockTaupe.class, RenderPeacockBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlTaupe.class, RenderPeafowlBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityPeachickTaupe.class, RenderPeachickBase.FACTORY);

		// Horses
		RenderingRegistry.registerEntityRenderingHandler(EntityStallionDraftHorse.class, RenderStallionDraftHorse.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMareDraftHorse.class, RenderMareDraftHorse.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFoalDraftHorse.class, RenderFoalDraftHorse.FACTORY);

	}

	@SideOnly(Side.CLIENT)
	static void renderTileEntity()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNest.class, new TileEntityNestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrough.class, new TileEntityTroughRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHamsterWheel.class, new TileEntityHamsterWheelRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySaltLick.class, new TileEntitySaltLickRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHive.class, new TileEntityHiveRenderer());

	}
}
