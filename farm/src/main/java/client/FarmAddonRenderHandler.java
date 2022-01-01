package client;

import client.render.chickens.RenderChickBase;
import client.render.chickens.RenderHenBase;
import client.render.chickens.RenderRoosterBase;
import client.render.cows.*;
import client.render.goats.*;
import client.render.horses.RenderFoalDraftHorse;
import client.render.horses.RenderMareDraftHorse;
import client.render.horses.RenderStallionDraftHorse;
import client.render.pigs.*;
import client.render.props.RenderCart;
import client.render.props.RenderTiller;
import client.render.props.RenderWagon;
import client.render.sheep.*;
import com.animania.Animania;
import com.animania.addons.farm.client.render.tileentity.BlockEntityHiveRenderer;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityChickLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityChickOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityChickPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityChickRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityChickWyandotte;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityRoosterWyandotte;
import com.animania.addons.farm.common.entity.cows.CowAngus.CowEntityAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityBullAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityCalfAngus;
import com.animania.addons.farm.common.entity.cows.CowFriesian.CowEntityFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityBullFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityCalfFriesian;
import com.animania.addons.farm.common.entity.cows.CowHereford.CowEntityHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityBullHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityCalfHereford;
import com.animania.addons.farm.common.entity.cows.CowHighland.CowEntityHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityBullHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityCalfHighland;
import com.animania.addons.farm.common.entity.cows.CowHolstein.CowEntityHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityBullHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityCalfHolstein;
import com.animania.addons.farm.common.entity.cows.CowJersey.CowEntityJersey;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityBullJersey;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityCalfJersey;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.CowEntityLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityCalfLonghorn;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.CowEntityMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityCalfMooshroom;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityBuckAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityDoeAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityKidAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityDoeAngora;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityKidAngora;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityBuckFainting;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityDoeFainting;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityKidFainting;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityBuckKiko;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityDoeKiko;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityKidKiko;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityBuckKinder;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityDoeKinder;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityKidKinder;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityBuckNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityDoeNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityKidNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityBuckPygmy;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityDoePygmy;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityKidPygmy;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityFoalDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntityHogDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntitySowDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.PigEntityletDuroc;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntityHogHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntitySowHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.PigEntityletHampshire;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntityHogLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntitySowLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.PigEntityletLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntityHogLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntitySowLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.PigEntityletLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntityHogOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntitySowOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.PigEntityletOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntitySowYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.PigEntityletYorkshire;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityEweDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityLambDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityRamDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityEweDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityLambDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityRamDorset;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityLambFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityEweJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityLambJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityRamJacob;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityEweMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityLambMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityRamMerino;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityLambSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.addons.farm.common.tileentity.BlockEntityHive;
import com.animania.common.blocks.IMetaBlockName;
import com.leviathanstudio.craftstudio.client.registry.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;
import com.leviathanstudio.craftstudio.client.util.EnumRenderType;
import com.leviathanstudio.craftstudio.client.util.EnumResourceType;
import common.entity.pullables.EntityCart;
import common.entity.pullables.EntityTiller;
import common.entity.pullables.EntityWagon;
import common.handler.FarmAddonBlockHandler;
import common.handler.FarmAddonItemHandler;
import config.FarmConfig;
import net.minecraft.client.renderer.BlockEntity.BlockEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.NonNullList;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FarmAddonRenderHandler
{

	/**
	 * Render Entities <br>
	 * Render Items
	 */
	public static void preInit()
	{
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

		// Chickens
		RenderingRegistry.registerEntityRenderingHandler(EntityHenPlymouthRock.class, RenderHenBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterPlymouthRock.class, RenderRoosterBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickPlymouthRock.class, RenderChickBase.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntityHenLeghorn.class, RenderHenBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityRoosterLeghorn.class, RenderRoosterBase.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityChickLeghorn.class, RenderChickBase.FACTORY);

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
		RenderingRegistry.registerEntityRenderingHandler(CowEntityHolstein.class, RenderCowHolstein.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHolstein.class, RenderBullHolstein.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHolstein.class, RenderCalfHolstein.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(CowEntityFriesian.class, RenderCowFriesian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullFriesian.class, RenderBullFriesian.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfFriesian.class, RenderCalfFriesian.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(CowEntityAngus.class, RenderCowAngus.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullAngus.class, RenderBullAngus.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfAngus.class, RenderCalfAngus.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(CowEntityLonghorn.class, RenderCowLonghorn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullLonghorn.class, RenderBullLonghorn.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfLonghorn.class, RenderCalfLonghorn.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(CowEntityHereford.class, RenderCowHereford.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHereford.class, RenderBullHereford.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHereford.class, RenderCalfHereford.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(CowEntityHighland.class, RenderCowHighland.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullHighland.class, RenderBullHighland.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfHighland.class, RenderCalfHighland.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(CowEntityJersey.class, RenderCowJersey.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullJersey.class, RenderBullJersey.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfJersey.class, RenderCalfJersey.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(CowEntityMooshroom.class, RenderCowMooshroom.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullMooshroom.class, RenderBullMooshroom.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCalfMooshroom.class, RenderCalfMooshroom.FACTORY);

		// Pigs
		RenderingRegistry.registerEntityRenderingHandler(EntitySowYorkshire.class, RenderSowYorkshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogYorkshire.class, RenderHogYorkshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(PigEntityletYorkshire.class, RenderPigletYorkshire.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowOldSpot.class, RenderSowOldSpot.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogOldSpot.class, RenderHogOldSpot.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(PigEntityletOldSpot.class, RenderPigletOldSpot.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeBlack.class, RenderSowLargeBlack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeBlack.class, RenderHogLargeBlack.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(PigEntityletLargeBlack.class, RenderPigletLargeBlack.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowLargeWhite.class, RenderSowLargeWhite.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogLargeWhite.class, RenderHogLargeWhite.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(PigEntityletLargeWhite.class, RenderPigletLargeWhite.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowDuroc.class, RenderSowDuroc.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogDuroc.class, RenderHogDuroc.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(PigEntityletDuroc.class, RenderPigletDuroc.FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(EntitySowHampshire.class, RenderSowHampshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityHogHampshire.class, RenderHogHampshire.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(PigEntityletHampshire.class, RenderPigletHampshire.FACTORY);

		// Horses
		RenderingRegistry.registerEntityRenderingHandler(EntityStallionDraftHorse.class, RenderStallionDraftHorse.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMareDraftHorse.class, RenderMareDraftHorse.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFoalDraftHorse.class, RenderFoalDraftHorse.FACTORY);

		// Items
		register(FarmAddonItemHandler.truffle);
		register(FarmAddonItemHandler.carvingKnife);
		register(FarmAddonItemHandler.salt);
		register(FarmAddonItemHandler.cheeseMold);
		register(FarmAddonItemHandler.cheeseWedgeFriesian);
		register(FarmAddonItemHandler.cheeseWedgeHolstein);
		register(FarmAddonItemHandler.cheeseWedgeJersey);
		register(FarmAddonItemHandler.cheeseWedgeGoat);
		register(FarmAddonItemHandler.cheeseWedgeSheep);
		register(FarmAddonItemHandler.truffleSoup);
		register(FarmAddonItemHandler.chocolateTruffle);
		register(FarmAddonItemHandler.plainOmelette);
		register(FarmAddonItemHandler.cheeseOmelette);
		register(FarmAddonItemHandler.baconOmelette);
		register(FarmAddonItemHandler.truffleOmelette);
		register(FarmAddonItemHandler.ultimateOmelette);
		register(FarmAddonItemHandler.ridingCrop);
		register(FarmAddonItemHandler.milkBottle);
		register(FarmAddonItemHandler.honeyJar);
		register(FarmAddonItemHandler.brownEgg);
		register(FarmAddonItemHandler.wheel);

		// Horse
		register(FarmAddonItemHandler.rawHorse);
		register(FarmAddonItemHandler.cookedHorse);

		// Beef Generics
		register(FarmAddonItemHandler.rawPrimeBeef);
		register(FarmAddonItemHandler.cookedPrimeBeef);
		register(FarmAddonItemHandler.rawPrimeSteak);
		register(FarmAddonItemHandler.cookedPrimeSteak);

		// Pork Generics
		register(FarmAddonItemHandler.rawPrimePork);
		register(FarmAddonItemHandler.cookedPrimePork);
		register(FarmAddonItemHandler.rawPrimeBacon);
		register(FarmAddonItemHandler.cookedPrimeBacon);

		// Chicken Generics
		register(FarmAddonItemHandler.rawPrimeChicken);
		register(FarmAddonItemHandler.cookedPrimeChicken);

		// Goats
		register(FarmAddonItemHandler.rawChevon);
		register(FarmAddonItemHandler.cookedChevon);
		register(FarmAddonItemHandler.rawPrimeChevon);
		register(FarmAddonItemHandler.cookedPrimeChevon);
		// Sheep
		register(FarmAddonItemHandler.rawPrimeMutton);
		register(FarmAddonItemHandler.cookedPrimeMutton);

		// EGGS
		register(FarmAddonItemHandler.entityeggrandomcow);
		register(FarmAddonItemHandler.entityeggrandomchicken);
		register(FarmAddonItemHandler.entityeggrandompig);
		register(FarmAddonItemHandler.entityeggrandomgoat);

		register(FarmAddonItemHandler.entityeggrandomsheep);

		register(Item.byBlock(FarmAddonBlockHandler.blockCheeseFriesian));
		register(Item.byBlock(FarmAddonBlockHandler.blockCheeseHolstein));
		register(Item.byBlock(FarmAddonBlockHandler.blockCheeseJersey));
		register(Item.byBlock(FarmAddonBlockHandler.blockCheeseGoat));
		register(Item.byBlock(FarmAddonBlockHandler.blockCheeseSheep));
		register(Item.byBlock(FarmAddonBlockHandler.blockHive));
		register(Item.byBlock(FarmAddonBlockHandler.blockWildHive));

		regSpecial(FarmAddonBlockHandler.blockAnimaniaWool);
		if (!FarmConfig.settings.disableRollingVehicles)
		{
			register(FarmAddonItemHandler.cart);
			register(FarmAddonItemHandler.wagon);
			register(FarmAddonItemHandler.tiller);
		}

		Animania.proxy.registerFluidBlockRendering(FarmAddonBlockHandler.blockMilkFriesian, "milk_friesian");
		Animania.proxy.registerFluidBlockRendering(FarmAddonBlockHandler.blockMilkHolstein, "milk_holstein");
		Animania.proxy.registerFluidBlockRendering(FarmAddonBlockHandler.blockMilkJersey, "milk_jersey");
		Animania.proxy.registerFluidBlockRendering(FarmAddonBlockHandler.blockMilkGoat, "milk_goat");
		Animania.proxy.registerFluidBlockRendering(FarmAddonBlockHandler.blockMilkSheep, "milk_sheep");
		Animania.proxy.registerFluidBlockRendering(FarmAddonBlockHandler.blockHoney, "honey");
	}

	/**
	 * Render TileEntities
	 */
	public static void init()
	{
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityHive.class, new BlockEntityHiveRenderer());

	}

	@CraftStudioLoader
	public static void registerCraftStudioAssets()
	{
		CSRegistryHelper csRegistry = new CSRegistryHelper(Animania.MODID);

		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_bee_hive");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.BLOCK, "model_wild_hive");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_wagon");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_cart");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_cart_chest");
		csRegistry.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "model_tiller");

		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "anim_bees");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.BLOCK, "anim_bees_wild");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "anim_cart");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "anim_cart_chest");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "anim_wagon");
		csRegistry.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "anim_tiller");

	}

	@SideOnly(Dist.CLIENT)
	private static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
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

	@SideOnly(Dist.CLIENT)
	public static void regSpecial(Block block)
	{
		NonNullList<ItemStack> list = NonNullList.create();

		block.getSubBlocks(Animania.TabAnimaniaResources, list);

		for (int i = 0; i < list.size(); i++)
		{
			ItemStack stack = list.get(i);

			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), stack.getMetadata(), new ModelResourceLocation(block.getRegistryName().toString() + "_" + ((IMetaBlockName) block).getSpecialName(stack), "inventory"));
			ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ResourceLocation(block.getRegistryName().toString() + "_" + ((IMetaBlockName) block).getSpecialName(stack)));

		}

	}

}
