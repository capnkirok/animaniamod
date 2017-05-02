package com.animania;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import com.animania.blocks.BlockInvisiblock;
import com.animania.blocks.BlockMud;
import com.animania.blocks.BlockNest;
import com.animania.blocks.BlockSeeds;
import com.animania.blocks.BlockTrough;
import com.animania.items.ItemBrownEgg;
import com.animania.items.ItemBucketMilk;
import com.animania.items.ItemBucketSlop;
import com.animania.items.ItemCarvingKnife;
import com.animania.items.ItemCheeseMold;
import com.animania.items.ItemCheeseWedge;
import com.animania.items.ItemCheeseWheel;
import com.animania.items.ItemChocolateTruffle;
import com.animania.items.ItemCookedAngusRoast;
import com.animania.items.ItemCookedAngusSteak;
import com.animania.items.ItemCookedDurocBacon;
import com.animania.items.ItemCookedDurocRoast;
import com.animania.items.ItemCookedHampshireBacon;
import com.animania.items.ItemCookedHampshireRoast;
import com.animania.items.ItemCookedHerefordRoast;
import com.animania.items.ItemCookedHerefordSteak;
import com.animania.items.ItemCookedLargeBlackBacon;
import com.animania.items.ItemCookedLargeBlackRoast;
import com.animania.items.ItemCookedLonghornRoast;
import com.animania.items.ItemCookedLonghornSteak;
import com.animania.items.ItemCookedOldSpotBacon;
import com.animania.items.ItemCookedOldSpotRoast;
import com.animania.items.ItemCookedOrpingtonChicken;
import com.animania.items.ItemCookedPlymouthRockChicken;
import com.animania.items.ItemCookedPrimeBacon;
import com.animania.items.ItemCookedPrimeBeef;
import com.animania.items.ItemCookedPrimeChicken;
import com.animania.items.ItemCookedPrimePork;
import com.animania.items.ItemCookedPrimeSteak;
import com.animania.items.ItemCookedRhodeIslandRedChicken;
import com.animania.items.ItemCookedWyandotteChicken;
import com.animania.items.ItemEntityEgg;
import com.animania.items.ItemHamsterFood;
import com.animania.items.ItemOmelette;
import com.animania.items.ItemPeacockFeather;
import com.animania.items.ItemRawAngusBeef;
import com.animania.items.ItemRawAngusSteak;
import com.animania.items.ItemRawDurocBacon;
import com.animania.items.ItemRawDurocPork;
import com.animania.items.ItemRawHampshireBacon;
import com.animania.items.ItemRawHampshirePork;
import com.animania.items.ItemRawHerefordBeef;
import com.animania.items.ItemRawHerefordSteak;
import com.animania.items.ItemRawLargeBlackBacon;
import com.animania.items.ItemRawLargeBlackPork;
import com.animania.items.ItemRawLonghornBeef;
import com.animania.items.ItemRawLonghornSteak;
import com.animania.items.ItemRawOldSpotBacon;
import com.animania.items.ItemRawOldSpotPork;
import com.animania.items.ItemRawOrpingtonChicken;
import com.animania.items.ItemRawPlymouthRockChicken;
import com.animania.items.ItemRawPrimeBacon;
import com.animania.items.ItemRawPrimeBeef;
import com.animania.items.ItemRawPrimeChicken;
import com.animania.items.ItemRawPrimePork;
import com.animania.items.ItemRawPrimeSteak;
import com.animania.items.ItemRawRhodeIslandRedChicken;
import com.animania.items.ItemRawWyandotteChicken;
import com.animania.items.ItemTruffle;
import com.animania.items.ItemTruffleSoup;

public class AnimaniaTextures {

	public static void registerTextures() { 
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();


		//Items
		renderItem.getItemModelMesher().register(Animania.hamsterFood, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemHamsterFood)Animania.hamsterFood).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.truffle, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemTruffle)Animania.truffle).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.brownEgg, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemBrownEgg)Animania.brownEgg).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.bucketSlop, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemBucketSlop)Animania.bucketSlop).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.carvingKnife, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCarvingKnife)Animania.carvingKnife).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cheeseMold, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCheeseMold)Animania.cheeseMold).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cheeseWheelFriesian, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCheeseWheel)Animania.cheeseWheelFriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cheeseWedgeFriesian, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCheeseWedge)Animania.cheeseWedgeFriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cheeseWheelHolstein, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCheeseWheel)Animania.cheeseWheelHolstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cheeseWedgeHolstein, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCheeseWedge)Animania.cheeseWedgeHolstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.truffleSoup, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemTruffleSoup)Animania.truffleSoup).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.chocolateTruffle, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemChocolateTruffle)Animania.chocolateTruffle).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.plainOmelette, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemOmelette)Animania.plainOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cheeseOmelette, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemOmelette)Animania.cheeseOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.baconOmelette, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemOmelette)Animania.baconOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.truffleOmelette, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemOmelette)Animania.truffleOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.ultimateOmelette, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemOmelette)Animania.ultimateOmelette).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.milkBucketFriesian, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemBucketMilk)Animania.milkBucketFriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.milkBucketHolstein, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemBucketMilk)Animania.milkBucketHolstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.peacockFeatherBlue, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemPeacockFeather)Animania.peacockFeatherBlue).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.peacockFeatherWhite, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemPeacockFeather)Animania.peacockFeatherWhite).getName(), "inventory"));


		//Beef
		renderItem.getItemModelMesher().register(Animania.rawAngusBeef, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawAngusBeef)Animania.rawAngusBeef).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawHerefordBeef, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawHerefordBeef)Animania.rawHerefordBeef).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawLonghornBeef, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawLonghornBeef)Animania.rawLonghornBeef).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.cookedAngusRoast, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedAngusRoast)Animania.cookedAngusRoast).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedHerefordRoast, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedHerefordRoast)Animania.cookedHerefordRoast).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedLonghornRoast, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedLonghornRoast)Animania.cookedLonghornRoast).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.rawAngusSteak, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawAngusSteak)Animania.rawAngusSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawHerefordSteak, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawHerefordSteak)Animania.rawHerefordSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawLonghornSteak, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawLonghornSteak)Animania.rawLonghornSteak).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.cookedAngusSteak, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedAngusSteak)Animania.cookedAngusSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedHerefordSteak, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedHerefordSteak)Animania.cookedHerefordSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedLonghornSteak, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedLonghornSteak)Animania.cookedLonghornSteak).getName(), "inventory"));

		//Beef Generics
		renderItem.getItemModelMesher().register(Animania.rawPrimeBeef, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawPrimeBeef)Animania.rawPrimeBeef).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedPrimeBeef, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedPrimeBeef)Animania.cookedPrimeBeef).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawPrimeSteak, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawPrimeSteak)Animania.rawPrimeSteak).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedPrimeSteak, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedPrimeSteak)Animania.cookedPrimeSteak).getName(), "inventory"));


		//Pork
		renderItem.getItemModelMesher().register(Animania.rawLargeBlackPork, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawLargeBlackPork)Animania.rawLargeBlackPork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawDurocPork, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawDurocPork)Animania.rawDurocPork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawOldSpotPork, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawOldSpotPork)Animania.rawOldSpotPork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawHampshirePork, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawHampshirePork)Animania.rawHampshirePork).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.rawLargeBlackBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawLargeBlackBacon)Animania.rawLargeBlackBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawDurocBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawDurocBacon)Animania.rawDurocBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawOldSpotBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawOldSpotBacon)Animania.rawOldSpotBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawHampshireBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawHampshireBacon)Animania.rawHampshireBacon).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.cookedLargeBlackRoast, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedLargeBlackRoast)Animania.cookedLargeBlackRoast).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedDurocRoast, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedDurocRoast)Animania.cookedDurocRoast).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedOldSpotRoast, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedOldSpotRoast)Animania.cookedOldSpotRoast).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedHampshireRoast, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedHampshireRoast)Animania.cookedHampshireRoast).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.cookedLargeBlackBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedLargeBlackBacon)Animania.cookedLargeBlackBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedDurocBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedDurocBacon)Animania.cookedDurocBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedOldSpotBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedOldSpotBacon)Animania.cookedOldSpotBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedHampshireBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedHampshireBacon)Animania.cookedHampshireBacon).getName(), "inventory"));

		//Pork Generics
		renderItem.getItemModelMesher().register(Animania.rawPrimePork, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawPrimePork)Animania.rawPrimePork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedPrimePork, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedPrimePork)Animania.cookedPrimePork).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawPrimeBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawPrimeBacon)Animania.rawPrimeBacon).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedPrimeBacon, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedPrimeBacon)Animania.cookedPrimeBacon).getName(), "inventory"));


		//Chicken
		renderItem.getItemModelMesher().register(Animania.rawOrpingtonChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawOrpingtonChicken)Animania.rawOrpingtonChicken).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawPlymouthRockChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawPlymouthRockChicken)Animania.rawPlymouthRockChicken).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawWyandotteChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawWyandotteChicken)Animania.rawWyandotteChicken).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.rawRhodeIslandRedChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawRhodeIslandRedChicken)Animania.rawRhodeIslandRedChicken).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.cookedOrpingtonChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedOrpingtonChicken)Animania.cookedOrpingtonChicken).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedPlymouthRockChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedPlymouthRockChicken)Animania.cookedPlymouthRockChicken).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedWyandotteChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedWyandotteChicken)Animania.cookedWyandotteChicken).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedRhodeIslandRedChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedRhodeIslandRedChicken)Animania.cookedRhodeIslandRedChicken).getName(), "inventory"));

		//Chicken Generics
		renderItem.getItemModelMesher().register(Animania.rawPrimeChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemRawPrimeChicken)Animania.rawPrimeChicken).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.cookedPrimeChicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemCookedPrimeChicken)Animania.cookedPrimeChicken).getName(), "inventory"));


		//Cows
		renderItem.getItemModelMesher().register(Animania.entityeggbullholstein, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggbullholstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcowholstein, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcowholstein).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcalfholstein, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcalfholstein).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggbullfriesian, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggbullfriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcowfriesian, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcowfriesian).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcalffriesian, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcalffriesian).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggbullangus, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggbullangus).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcowangus, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcowangus).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcalfangus, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcalfangus).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggbulllonghorn, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggbulllonghorn).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcowlonghorn, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcowlonghorn).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcalflonghorn, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcalflonghorn).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggbullhereford, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggbullhereford).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcowhereford, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcowhereford).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggcalfhereford, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggcalfhereford).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggrandomcow, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggrandomcow).getName(), "inventory"));

		//Chickens
		renderItem.getItemModelMesher().register(Animania.entityeggchickplymouth, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggchickplymouth).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghenplymouth, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghenplymouth).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggroosterplymouth, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggroosterplymouth).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggchickleghorn, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggchickleghorn).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghenleghorn, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghenleghorn).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggroosterleghorn, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggroosterleghorn).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggchickred, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggchickred).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghenred, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghenred).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggroosterred, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggroosterred).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggchickorpington, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggchickorpington).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghenorpington, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghenorpington).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggroosterorpington, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggroosterorpington).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggchickwyandotte, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggchickwyandotte).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghenwyandotte, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghenwyandotte).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggroosterwyandotte, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggroosterwyandotte).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggrandomchicken, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggrandomchicken).getName(), "inventory"));

		//Peacocks
		renderItem.getItemModelMesher().register(Animania.entityeggpeacockblue, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpeacockblue).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpeafowlblue, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpeafowlblue).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpeachickblue, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpeachickblue).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggpeacockwhite, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpeacockwhite).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpeafowlwhite, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpeafowlwhite).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpeachickwhite, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpeachickwhite).getName(), "inventory"));

		//Pigs
		renderItem.getItemModelMesher().register(Animania.entityeggsowyorkshire, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggsowyorkshire).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghogyorkshire, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghogyorkshire).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpigletyorkshire, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpigletyorkshire).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggsowoldspot, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggsowoldspot).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghogoldspot, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghogoldspot).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpigletoldspot, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpigletoldspot).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggsowlargeblack, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggsowlargeblack).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghoglargeblack, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghoglargeblack).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpigletlargeblack, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpigletlargeblack).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggsowlargewhite, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggsowlargewhite).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghoglargewhite, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghoglargewhite).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpigletlargewhite, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpigletlargewhite).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggsowduroc, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggsowduroc).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghogduroc, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghogduroc).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpigletduroc, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpigletduroc).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggsowhampshire, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggsowhampshire).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghoghampshire, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghoghampshire).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggpiglethampshire, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggpiglethampshire).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityeggrandompig, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggrandompig).getName(), "inventory"));

		//Rodents
		renderItem.getItemModelMesher().register(Animania.entityegghamster, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghamster).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggferretgrey, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggferretgrey).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityeggferretwhite, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityeggferretwhite).getName(), "inventory"));

		renderItem.getItemModelMesher().register(Animania.entityegghedgehog, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghedgehog).getName(), "inventory"));
		renderItem.getItemModelMesher().register(Animania.entityegghedgehogalbino, 0, new ModelResourceLocation(Animania.modid + ":" + ((ItemEntityEgg)Animania.entityegghedgehogalbino).getName(), "inventory"));

		//Blocks
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Animania.blockMud), 0, new ModelResourceLocation(Animania.modid + ":" + ((BlockMud) Animania.blockMud).getName(), "inventory")); 
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Animania.blockTrough), 0, new ModelResourceLocation(Animania.modid + ":" + ((BlockTrough) Animania.blockTrough).getName(), "inventory")); 
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Animania.blockInvisiblock), 0, new ModelResourceLocation(Animania.modid + ":" + ((BlockInvisiblock) Animania.blockInvisiblock).getName(), "inventory")); 
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Animania.blockNest), 0, new ModelResourceLocation(Animania.modid + ":" + ((BlockNest) Animania.blockNest).getName(), "inventory")); 
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(Animania.blockSeeds), 0, new ModelResourceLocation(Animania.modid + ":" + ((BlockSeeds) Animania.blockSeeds).getName(), "inventory")); 



	}
}