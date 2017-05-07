package com.animania.client;

import com.animania.Animania;
import com.animania.common.blocks.BlockInvisiblock;
import com.animania.common.blocks.BlockMud;
import com.animania.common.blocks.BlockNest;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.blocks.BlockTrough;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemBrownEgg;
import com.animania.common.items.ItemBucketMilk;
import com.animania.common.items.ItemBucketSlop;
import com.animania.common.items.ItemCarvingKnife;
import com.animania.common.items.ItemCheeseMold;
import com.animania.common.items.ItemCheeseWedge;
import com.animania.common.items.ItemCheeseWheel;
import com.animania.common.items.ItemChocolateTruffle;
import com.animania.common.items.ItemCookedAngusRoast;
import com.animania.common.items.ItemCookedAngusSteak;
import com.animania.common.items.ItemCookedDurocBacon;
import com.animania.common.items.ItemCookedDurocRoast;
import com.animania.common.items.ItemCookedHampshireBacon;
import com.animania.common.items.ItemCookedHampshireRoast;
import com.animania.common.items.ItemCookedHerefordRoast;
import com.animania.common.items.ItemCookedHerefordSteak;
import com.animania.common.items.ItemCookedLargeBlackBacon;
import com.animania.common.items.ItemCookedLargeBlackRoast;
import com.animania.common.items.ItemCookedLonghornRoast;
import com.animania.common.items.ItemCookedLonghornSteak;
import com.animania.common.items.ItemCookedOldSpotBacon;
import com.animania.common.items.ItemCookedOldSpotRoast;
import com.animania.common.items.ItemCookedOrpingtonChicken;
import com.animania.common.items.ItemCookedPlymouthRockChicken;
import com.animania.common.items.ItemCookedPrimeBacon;
import com.animania.common.items.ItemCookedPrimeBeef;
import com.animania.common.items.ItemCookedPrimeChicken;
import com.animania.common.items.ItemCookedPrimePork;
import com.animania.common.items.ItemCookedPrimeSteak;
import com.animania.common.items.ItemCookedRhodeIslandRedChicken;
import com.animania.common.items.ItemCookedWyandotteChicken;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemHamsterFood;
import com.animania.common.items.ItemOmelette;
import com.animania.common.items.ItemPeacockFeather;
import com.animania.common.items.ItemRawAngusBeef;
import com.animania.common.items.ItemRawAngusSteak;
import com.animania.common.items.ItemRawDurocBacon;
import com.animania.common.items.ItemRawDurocPork;
import com.animania.common.items.ItemRawHampshireBacon;
import com.animania.common.items.ItemRawHampshirePork;
import com.animania.common.items.ItemRawHerefordBeef;
import com.animania.common.items.ItemRawHerefordSteak;
import com.animania.common.items.ItemRawLargeBlackBacon;
import com.animania.common.items.ItemRawLargeBlackPork;
import com.animania.common.items.ItemRawLonghornBeef;
import com.animania.common.items.ItemRawLonghornSteak;
import com.animania.common.items.ItemRawOldSpotBacon;
import com.animania.common.items.ItemRawOldSpotPork;
import com.animania.common.items.ItemRawOrpingtonChicken;
import com.animania.common.items.ItemRawPlymouthRockChicken;
import com.animania.common.items.ItemRawPrimeBacon;
import com.animania.common.items.ItemRawPrimeBeef;
import com.animania.common.items.ItemRawPrimeChicken;
import com.animania.common.items.ItemRawPrimePork;
import com.animania.common.items.ItemRawPrimeSteak;
import com.animania.common.items.ItemRawRhodeIslandRedChicken;
import com.animania.common.items.ItemRawWyandotteChicken;
import com.animania.common.items.ItemTruffle;
import com.animania.common.items.ItemTruffleSoup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class AnimaniaTextures {

	public static void registerTextures() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

		// Items
		renderItem.getItemModelMesher().register(ItemHandler.hamsterFood, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemHamsterFood) ItemHandler.hamsterFood).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.truffle, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemTruffle) ItemHandler.truffle).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.brownEgg, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemBrownEgg) ItemHandler.brownEgg).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.bucketSlop, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemBucketSlop) ItemHandler.bucketSlop).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.carvingKnife, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCarvingKnife) ItemHandler.carvingKnife).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cheeseMold, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCheeseMold) ItemHandler.cheeseMold).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cheeseWheelFriesian, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCheeseWheel) ItemHandler.cheeseWheelFriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cheeseWedgeFriesian, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCheeseWedge) ItemHandler.cheeseWedgeFriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cheeseWheelHolstein, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCheeseWheel) ItemHandler.cheeseWheelHolstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cheeseWedgeHolstein, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCheeseWedge) ItemHandler.cheeseWedgeHolstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.truffleSoup, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemTruffleSoup) ItemHandler.truffleSoup).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.chocolateTruffle, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemChocolateTruffle) ItemHandler.chocolateTruffle).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.plainOmelette, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemOmelette) ItemHandler.plainOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cheeseOmelette, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemOmelette) ItemHandler.cheeseOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.baconOmelette, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemOmelette) ItemHandler.baconOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.truffleOmelette, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemOmelette) ItemHandler.truffleOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.ultimateOmelette, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemOmelette) ItemHandler.ultimateOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.milkBucketFriesian, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemBucketMilk) ItemHandler.milkBucketFriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.milkBucketHolstein, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemBucketMilk) ItemHandler.milkBucketHolstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.peacockFeatherBlue, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemPeacockFeather) ItemHandler.peacockFeatherBlue).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.peacockFeatherWhite, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemPeacockFeather) ItemHandler.peacockFeatherWhite).getName(), "inventory"));

		// Beef
		renderItem.getItemModelMesher().register(ItemHandler.rawAngusBeef, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawAngusBeef) ItemHandler.rawAngusBeef).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawHerefordBeef, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawHerefordBeef) ItemHandler.rawHerefordBeef).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawLonghornBeef, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawLonghornBeef) ItemHandler.rawLonghornBeef).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.cookedAngusRoast, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCookedAngusRoast) ItemHandler.cookedAngusRoast).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedHerefordRoast, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedHerefordRoast) ItemHandler.cookedHerefordRoast).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedLonghornRoast, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedLonghornRoast) ItemHandler.cookedLonghornRoast).getName(),
						"inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.rawAngusSteak, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawAngusSteak) ItemHandler.rawAngusSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawHerefordSteak, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawHerefordSteak) ItemHandler.rawHerefordSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawLonghornSteak, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawLonghornSteak) ItemHandler.rawLonghornSteak).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.cookedAngusSteak, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCookedAngusSteak) ItemHandler.cookedAngusSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedHerefordSteak, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedHerefordSteak) ItemHandler.cookedHerefordSteak).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedLonghornSteak, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedLonghornSteak) ItemHandler.cookedLonghornSteak).getName(),
						"inventory"));

		// Beef Generics
		renderItem.getItemModelMesher().register(ItemHandler.rawPrimeBeef, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawPrimeBeef) ItemHandler.rawPrimeBeef).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedPrimeBeef, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCookedPrimeBeef) ItemHandler.cookedPrimeBeef).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawPrimeSteak, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawPrimeSteak) ItemHandler.rawPrimeSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedPrimeSteak, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCookedPrimeSteak) ItemHandler.cookedPrimeSteak).getName(), "inventory"));

		// Pork
		renderItem.getItemModelMesher().register(ItemHandler.rawLargeBlackPork, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawLargeBlackPork) ItemHandler.rawLargeBlackPork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawDurocPork, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawDurocPork) ItemHandler.rawDurocPork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawOldSpotPork, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawOldSpotPork) ItemHandler.rawOldSpotPork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawHampshirePork, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawHampshirePork) ItemHandler.rawHampshirePork).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.rawLargeBlackBacon, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemRawLargeBlackBacon) ItemHandler.rawLargeBlackBacon).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawDurocBacon, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawDurocBacon) ItemHandler.rawDurocBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawOldSpotBacon, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawOldSpotBacon) ItemHandler.rawOldSpotBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawHampshireBacon, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawHampshireBacon) ItemHandler.rawHampshireBacon).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.cookedLargeBlackRoast, 0,
				new ModelResourceLocation(
						Animania.MODID + ":"
								+ ((ItemCookedLargeBlackRoast) ItemHandler.cookedLargeBlackRoast).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedDurocRoast, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCookedDurocRoast) ItemHandler.cookedDurocRoast).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedOldSpotRoast, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedOldSpotRoast) ItemHandler.cookedOldSpotRoast).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedHampshireRoast, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedHampshireRoast) ItemHandler.cookedHampshireRoast).getName(),
						"inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.cookedLargeBlackBacon, 0,
				new ModelResourceLocation(
						Animania.MODID + ":"
								+ ((ItemCookedLargeBlackBacon) ItemHandler.cookedLargeBlackBacon).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedDurocBacon, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCookedDurocBacon) ItemHandler.cookedDurocBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedOldSpotBacon, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedOldSpotBacon) ItemHandler.cookedOldSpotBacon).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedHampshireBacon, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedHampshireBacon) ItemHandler.cookedHampshireBacon).getName(),
						"inventory"));

		// Pork Generics
		renderItem.getItemModelMesher().register(ItemHandler.rawPrimePork, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawPrimePork) ItemHandler.rawPrimePork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedPrimePork, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCookedPrimePork) ItemHandler.cookedPrimePork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawPrimeBacon, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawPrimeBacon) ItemHandler.rawPrimeBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedPrimeBacon, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemCookedPrimeBacon) ItemHandler.cookedPrimeBacon).getName(), "inventory"));

		// Chicken
		renderItem.getItemModelMesher().register(ItemHandler.rawOrpingtonChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemRawOrpingtonChicken) ItemHandler.rawOrpingtonChicken).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawPlymouthRockChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":"
								+ ((ItemRawPlymouthRockChicken) ItemHandler.rawPlymouthRockChicken).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawWyandotteChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemRawWyandotteChicken) ItemHandler.rawWyandotteChicken).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.rawRhodeIslandRedChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":"
								+ ((ItemRawRhodeIslandRedChicken) ItemHandler.rawRhodeIslandRedChicken).getName(),
						"inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.cookedOrpingtonChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":"
								+ ((ItemCookedOrpingtonChicken) ItemHandler.cookedOrpingtonChicken).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedPlymouthRockChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":"
								+ ((ItemCookedPlymouthRockChicken) ItemHandler.cookedPlymouthRockChicken).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedWyandotteChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":"
								+ ((ItemCookedWyandotteChicken) ItemHandler.cookedWyandotteChicken).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedRhodeIslandRedChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":"
								+ ((ItemCookedRhodeIslandRedChicken) ItemHandler.cookedRhodeIslandRedChicken).getName(),
						"inventory"));

		// Chicken Generics
		renderItem.getItemModelMesher().register(ItemHandler.rawPrimeChicken, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemRawPrimeChicken) ItemHandler.rawPrimeChicken).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.cookedPrimeChicken, 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((ItemCookedPrimeChicken) ItemHandler.cookedPrimeChicken).getName(),
						"inventory"));

		// Cows
		renderItem.getItemModelMesher().register(ItemHandler.entityeggbullholstein, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggbullholstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcowholstein, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcowholstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcalfholstein, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcalfholstein).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggbullfriesian, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggbullfriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcowfriesian, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcowfriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcalffriesian, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcalffriesian).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggbullangus, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggbullangus).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcowangus, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcowangus).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcalfangus, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcalfangus).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggbulllonghorn, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggbulllonghorn).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcowlonghorn, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcowlonghorn).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcalflonghorn, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcalflonghorn).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggbullhereford, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggbullhereford).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcowhereford, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcowhereford).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggcalfhereford, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggcalfhereford).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggrandomcow, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggrandomcow).getName(), "inventory"));

		// Chickens
		renderItem.getItemModelMesher().register(ItemHandler.entityeggchickplymouth, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggchickplymouth).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghenplymouth, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghenplymouth).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggroosterplymouth, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggroosterplymouth).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggchickleghorn, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggchickleghorn).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghenleghorn, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghenleghorn).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggroosterleghorn, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggroosterleghorn).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggchickred, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggchickred).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghenred, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghenred).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggroosterred, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggroosterred).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggchickorpington, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggchickorpington).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghenorpington, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghenorpington).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggroosterorpington, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggroosterorpington).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggchickwyandotte, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggchickwyandotte).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghenwyandotte, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghenwyandotte).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggroosterwyandotte, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggroosterwyandotte).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggrandomchicken, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggrandomchicken).getName(), "inventory"));

		// Peacocks
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpeacockblue, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpeacockblue).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpeafowlblue, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpeafowlblue).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpeachickblue, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpeachickblue).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggpeacockwhite, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpeacockwhite).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpeafowlwhite, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpeafowlwhite).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpeachickwhite, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpeachickwhite).getName(), "inventory"));

		// Pigs
		renderItem.getItemModelMesher().register(ItemHandler.entityeggsowyorkshire, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggsowyorkshire).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghogyorkshire, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghogyorkshire).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpigletyorkshire, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpigletyorkshire).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggsowoldspot, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggsowoldspot).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghogoldspot, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghogoldspot).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpigletoldspot, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpigletoldspot).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggsowlargeblack, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggsowlargeblack).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghoglargeblack, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghoglargeblack).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpigletlargeblack, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpigletlargeblack).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggsowlargewhite, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggsowlargewhite).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghoglargewhite, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghoglargewhite).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpigletlargewhite, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpigletlargewhite).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggsowduroc, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggsowduroc).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghogduroc, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghogduroc).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpigletduroc, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpigletduroc).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggsowhampshire, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggsowhampshire).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghoghampshire, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghoghampshire).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggpiglethampshire, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggpiglethampshire).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityeggrandompig, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggrandompig).getName(), "inventory"));

		// Rodents
		renderItem.getItemModelMesher().register(ItemHandler.entityegghamster, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghamster).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggferretgrey, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggferretgrey).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityeggferretwhite, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityeggferretwhite).getName(), "inventory"));

		renderItem.getItemModelMesher().register(ItemHandler.entityegghedgehog, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghedgehog).getName(), "inventory"));
		renderItem.getItemModelMesher().register(ItemHandler.entityegghedgehogalbino, 0, new ModelResourceLocation(
				Animania.MODID + ":" + ((ItemEntityEgg) ItemHandler.entityegghedgehogalbino).getName(), "inventory"));

		// Blocks
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockHandler.blockMud), 0,
				new ModelResourceLocation(Animania.MODID + ":" + ((BlockMud) BlockHandler.blockMud).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockHandler.blockTrough), 0,
				new ModelResourceLocation(Animania.MODID + ":" + ((BlockTrough) BlockHandler.blockTrough).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockHandler.blockInvisiblock), 0,
				new ModelResourceLocation(
						Animania.MODID + ":" + ((BlockInvisiblock) BlockHandler.blockInvisiblock).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockHandler.blockNest), 0,
				new ModelResourceLocation(Animania.MODID + ":" + ((BlockNest) BlockHandler.blockNest).getName(),
						"inventory"));
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(BlockHandler.blockSeeds), 0,
				new ModelResourceLocation(Animania.MODID + ":" + ((BlockSeeds) BlockHandler.blockSeeds).getName(),
						"inventory"));

	}
}