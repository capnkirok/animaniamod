package com.animania.common.handler;

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

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemHandler {
	// Items
	public static Item hamsterBall;
	public static Item hamsterFood;
	public static Item truffle;
	public static Item brownEgg;
	//public static Item bucketSlop;
	public static Item carvingKnife;
	public static Item cheeseMold;

	public static Item peacockFeatherBlue;
	public static Item peacockFeatherWhite;

	// Beef
	public static Item rawHerefordBeef;
	public static Item rawLonghornBeef;
	public static Item rawAngusBeef;
	public static Item rawHerefordSteak;
	public static Item rawLonghornSteak;
	public static Item rawAngusSteak;
	public static Item cookedHerefordRoast;
	public static Item cookedLonghornRoast;
	public static Item cookedAngusRoast;
	public static Item cookedHerefordSteak;
	public static Item cookedLonghornSteak;
	public static Item cookedAngusSteak;

	public static Item rawPrimeSteak;
	public static Item rawPrimeBeef;
	public static Item cookedPrimeSteak;
	public static Item cookedPrimeBeef;

	// Pork
	public static Item rawLargeBlackPork;
	public static Item rawDurocPork;
	public static Item rawOldSpotPork;
	public static Item rawHampshirePork;
	public static Item rawLargeBlackBacon;
	public static Item rawDurocBacon;
	public static Item rawOldSpotBacon;
	public static Item rawHampshireBacon;
	public static Item cookedLargeBlackRoast;
	public static Item cookedDurocRoast;
	public static Item cookedOldSpotRoast;
	public static Item cookedHampshireRoast;
	public static Item cookedLargeBlackBacon;
	public static Item cookedDurocBacon;
	public static Item cookedOldSpotBacon;
	public static Item cookedHampshireBacon;

	public static Item rawPrimePork;
	public static Item rawPrimeBacon;
	public static Item cookedPrimePork;
	public static Item cookedPrimeBacon;

	// Chicken
	public static Item rawOrpingtonChicken;
	public static Item rawPlymouthRockChicken;
	public static Item rawWyandotteChicken;
	public static Item rawRhodeIslandRedChicken;
	public static Item cookedOrpingtonChicken;
	public static Item cookedPlymouthRockChicken;
	public static Item cookedWyandotteChicken;
	public static Item cookedRhodeIslandRedChicken;

	public static Item rawPrimeChicken;
	public static Item cookedPrimeChicken;

	// Other Foods
	public static Item plainOmelette;
	public static Item cheeseOmelette;
	public static Item baconOmelette;
	public static Item truffleOmelette;
	public static Item ultimateOmelette;
	public static Item cheeseWheelFriesian;
	public static Item cheeseWedgeFriesian;
	public static Item cheeseWheelHolstein;
	public static Item cheeseWedgeHolstein;
	public static Item truffleSoup;
	public static Item chocolateTruffle;
	public static Item milkBucketHolstein;
	public static Item milkBucketFriesian;

	// Eggs - Cows

	public static Item entityeggcalfholstein;
	public static Item entityeggcowholstein;
	public static Item entityeggbullholstein;

	public static Item entityeggcalffriesian;
	public static Item entityeggcowfriesian;
	public static Item entityeggbullfriesian;

	public static Item entityeggcalfangus;
	public static Item entityeggcowangus;
	public static Item entityeggbullangus;

	public static Item entityeggcalflonghorn;
	public static Item entityeggcowlonghorn;
	public static Item entityeggbulllonghorn;

	public static Item entityeggcalfhereford;
	public static Item entityeggcowhereford;
	public static Item entityeggbullhereford;

	public static Item entityeggrandomcow;

	// Eggs - Chickens
	public static Item entityeggchickplymouth;
	public static Item entityegghenplymouth;
	public static Item entityeggroosterplymouth;

	public static Item entityeggchickleghorn;
	public static Item entityegghenleghorn;
	public static Item entityeggroosterleghorn;

	public static Item entityeggchickorpington;
	public static Item entityegghenorpington;
	public static Item entityeggroosterorpington;

	public static Item entityeggchickred;
	public static Item entityegghenred;
	public static Item entityeggroosterred;

	public static Item entityeggchickwyandotte;
	public static Item entityegghenwyandotte;
	public static Item entityeggroosterwyandotte;

	public static Item entityeggrandomchicken;

	// Eggs - Peacocks
	public static Item entityeggpeachickblue;
	public static Item entityeggpeafowlblue;
	public static Item entityeggpeacockblue;

	public static Item entityeggpeachickwhite;
	public static Item entityeggpeafowlwhite;
	public static Item entityeggpeacockwhite;

	// Eggs - Pigs
	public static Item entityeggpigletyorkshire;
	public static Item entityeggsowyorkshire;
	public static Item entityegghogyorkshire;

	public static Item entityeggpigletoldspot;
	public static Item entityeggsowoldspot;
	public static Item entityegghogoldspot;

	public static Item entityeggpigletlargeblack;
	public static Item entityeggsowlargeblack;
	public static Item entityegghoglargeblack;

	public static Item entityeggpigletlargewhite;
	public static Item entityeggsowlargewhite;
	public static Item entityegghoglargewhite;

	public static Item entityeggpigletduroc;
	public static Item entityeggsowduroc;
	public static Item entityegghogduroc;

	public static Item entityeggpiglethampshire;
	public static Item entityeggsowhampshire;
	public static Item entityegghoghampshire;

	public static Item entityeggrandompig;

	// Eggs - Rodents
	public static Item entityegghamster;
	public static Item entityeggferretgrey;
	public static Item entityeggferretwhite;
	public static Item entityegghedgehog;
	public static Item entityegghedgehogalbino;

	// Eggs - Horses
	// public static Item entityeggdrafthorsestallion;
	// public static Item entityeggdrafthorsemare;
	// public static Item entityeggdrafthorsefoal;

	public static void preInit() {
		// ITEMS
		// Items for Animals
		hamsterFood = new ItemHamsterFood();
		truffle = new ItemTruffle();
		//bucketSlop = new ItemBucketSlop();
		carvingKnife = new ItemCarvingKnife(ToolMaterial.IRON);
		cheeseMold = new ItemCheeseMold();
		milkBucketFriesian = new ItemBucketMilk("friesian");
		milkBucketHolstein = new ItemBucketMilk("holstein");
		peacockFeatherBlue = new ItemPeacockFeather("blue");
		peacockFeatherWhite = new ItemPeacockFeather("white");

		// Other foods
		ultimateOmelette = new ItemOmelette("super");
		truffleOmelette = new ItemOmelette("truffle");
		baconOmelette = new ItemOmelette("bacon");
		cheeseOmelette = new ItemOmelette("cheese");
		plainOmelette = new ItemOmelette("plain");
		truffleSoup = new ItemTruffleSoup();
		chocolateTruffle = new ItemChocolateTruffle();

		// ITEMS produced by Animals
		// COW ITEMS
		rawHerefordBeef = new ItemRawHerefordBeef();
		rawLonghornBeef = new ItemRawLonghornBeef();
		rawAngusBeef = new ItemRawAngusBeef();

		rawLonghornSteak = new ItemRawLonghornSteak();
		rawHerefordSteak = new ItemRawHerefordSteak();
		rawAngusSteak = new ItemRawAngusSteak();

		cookedLonghornRoast = new ItemCookedLonghornRoast();
		cookedHerefordRoast = new ItemCookedHerefordRoast();
		cookedAngusRoast = new ItemCookedAngusRoast();

		cookedLonghornSteak = new ItemCookedLonghornSteak();
		cookedHerefordSteak = new ItemCookedHerefordSteak();
		cookedAngusSteak = new ItemCookedAngusSteak();

		rawPrimeBeef = new ItemRawPrimeBeef();
		cookedPrimeBeef = new ItemCookedPrimeBeef();
		rawPrimeSteak = new ItemRawPrimeSteak();
		cookedPrimeSteak = new ItemCookedPrimeSteak();

		// PIG ITEMS
		rawLargeBlackPork = new ItemRawLargeBlackPork();
		rawDurocPork = new ItemRawDurocPork();
		rawOldSpotPork = new ItemRawOldSpotPork();
		rawHampshirePork = new ItemRawHampshirePork();

		rawLargeBlackBacon = new ItemRawLargeBlackBacon();
		rawDurocBacon = new ItemRawDurocBacon();
		rawOldSpotBacon = new ItemRawOldSpotBacon();
		rawHampshireBacon = new ItemRawHampshireBacon();

		cookedLargeBlackRoast = new ItemCookedLargeBlackRoast();
		cookedDurocRoast = new ItemCookedDurocRoast();
		cookedOldSpotRoast = new ItemCookedOldSpotRoast();
		cookedHampshireRoast = new ItemCookedHampshireRoast();

		cookedLargeBlackBacon = new ItemCookedLargeBlackBacon();
		cookedDurocBacon = new ItemCookedDurocBacon();
		cookedOldSpotBacon = new ItemCookedOldSpotBacon();
		cookedHampshireBacon = new ItemCookedHampshireBacon();

		rawPrimePork = new ItemRawPrimePork();
		cookedPrimePork = new ItemCookedPrimePork();
		rawPrimeBacon = new ItemRawPrimeBacon();
		cookedPrimeBacon = new ItemCookedPrimeBacon();

		// CHICKEN ITEMS
		rawOrpingtonChicken = new ItemRawOrpingtonChicken();
		rawPlymouthRockChicken = new ItemRawPlymouthRockChicken();
		rawWyandotteChicken = new ItemRawWyandotteChicken();
		rawRhodeIslandRedChicken = new ItemRawRhodeIslandRedChicken();
		cookedOrpingtonChicken = new ItemCookedOrpingtonChicken();
		cookedPlymouthRockChicken = new ItemCookedPlymouthRockChicken();
		cookedWyandotteChicken = new ItemCookedWyandotteChicken();
		cookedRhodeIslandRedChicken = new ItemCookedRhodeIslandRedChicken();
		rawPrimeChicken = new ItemRawPrimeChicken();
		cookedPrimeChicken = new ItemCookedPrimeChicken();
		brownEgg = new ItemBrownEgg();

		// CHEESE
		cheeseWheelFriesian = new ItemCheeseWheel("friesian");
		cheeseWheelHolstein = new ItemCheeseWheel("holstein");
		cheeseWedgeFriesian = new ItemCheeseWedge("friesian");
		cheeseWedgeHolstein = new ItemCheeseWedge("holstein");

		// Item Entity Eggs
		// COWS
		entityeggcalfholstein = new ItemEntityEgg("calf_holstein");
		entityeggcowholstein = new ItemEntityEgg("cow_holstein");
		entityeggbullholstein = new ItemEntityEgg("bull_holstein");

		entityeggcalffriesian = new ItemEntityEgg("calf_friesian");
		entityeggcowfriesian = new ItemEntityEgg("cow_friesian");
		entityeggbullfriesian = new ItemEntityEgg("bull_friesian");

		entityeggcalfangus = new ItemEntityEgg("calf_angus");
		entityeggcowangus = new ItemEntityEgg("cow_angus");
		entityeggbullangus = new ItemEntityEgg("bull_angus");

		entityeggcalflonghorn = new ItemEntityEgg("calf_longhorn");
		entityeggcowlonghorn = new ItemEntityEgg("cow_longhorn");
		entityeggbulllonghorn = new ItemEntityEgg("bull_longhorn");

		entityeggcalfhereford = new ItemEntityEgg("calf_hereford");
		entityeggcowhereford = new ItemEntityEgg("cow_hereford");
		entityeggbullhereford = new ItemEntityEgg("bull_hereford");

		entityeggrandomcow = new ItemEntityEgg("cow_random");

		// CHICKENS
		entityeggchickplymouth = new ItemEntityEgg("chick_plymouth");
		entityegghenplymouth = new ItemEntityEgg("hen_plymouth");
		entityeggroosterplymouth = new ItemEntityEgg("rooster_plymouth");

		entityeggchickleghorn = new ItemEntityEgg("chick_leghorn");
		entityegghenleghorn = new ItemEntityEgg("hen_leghorn");
		entityeggroosterleghorn = new ItemEntityEgg("rooster_leghorn");

		entityeggchickorpington = new ItemEntityEgg("chick_orpington");
		entityegghenorpington = new ItemEntityEgg("hen_orpington");
		entityeggroosterorpington = new ItemEntityEgg("rooster_orpington");

		entityeggchickred = new ItemEntityEgg("chick_red");
		entityegghenred = new ItemEntityEgg("hen_red");
		entityeggroosterred = new ItemEntityEgg("rooster_red");

		entityeggchickwyandotte = new ItemEntityEgg("chick_wyandotte");
		entityegghenwyandotte = new ItemEntityEgg("hen_wyandotte");
		entityeggroosterwyandotte = new ItemEntityEgg("rooster_wyandotte");

		entityeggrandomchicken = new ItemEntityEgg("chicken_random");

		// PEAFOWL
		entityeggpeachickblue = new ItemEntityEgg("peachick_blue");
		entityeggpeafowlblue = new ItemEntityEgg("peafowl_blue");
		entityeggpeacockblue = new ItemEntityEgg("peacock_blue");

		entityeggpeachickwhite = new ItemEntityEgg("peachick_white");
		entityeggpeafowlwhite = new ItemEntityEgg("peafowl_white");
		entityeggpeacockwhite = new ItemEntityEgg("peacock_white");

		// PIGS
		entityeggpigletyorkshire = new ItemEntityEgg("piglet_yorkshire");
		entityeggsowyorkshire = new ItemEntityEgg("sow_yorkshire");
		entityegghogyorkshire = new ItemEntityEgg("hog_yorkshire");

		entityeggpigletoldspot = new ItemEntityEgg("piglet_oldspot");
		entityeggsowoldspot = new ItemEntityEgg("sow_oldspot");
		entityegghogoldspot = new ItemEntityEgg("hog_oldspot");

		entityeggpigletlargeblack = new ItemEntityEgg("piglet_largeblack");
		entityeggsowlargeblack = new ItemEntityEgg("sow_largeblack");
		entityegghoglargeblack = new ItemEntityEgg("hog_largeblack");

		entityeggpigletlargewhite = new ItemEntityEgg("piglet_largewhite");
		entityeggsowlargewhite = new ItemEntityEgg("sow_largewhite");
		entityegghoglargewhite = new ItemEntityEgg("hog_largewhite");

		entityeggpigletduroc = new ItemEntityEgg("piglet_duroc");
		entityeggsowduroc = new ItemEntityEgg("sow_duroc");
		entityegghogduroc = new ItemEntityEgg("hog_duroc");

		entityeggpiglethampshire = new ItemEntityEgg("piglet_hampshire");
		entityeggsowhampshire = new ItemEntityEgg("sow_hampshire");
		entityegghoghampshire = new ItemEntityEgg("hog_hampshire");

		entityeggrandompig = new ItemEntityEgg("pig_random");

		// RODENTS
		entityegghamster = new ItemEntityEgg("hamster");
		entityeggferretgrey = new ItemEntityEgg("ferret_grey");
		entityeggferretwhite = new ItemEntityEgg("ferret_white");
		entityegghedgehog = new ItemEntityEgg("hedgehog");
		entityegghedgehogalbino = new ItemEntityEgg("hedgehog_albino");

		// HORSES
		// entityeggdrafthorsestallion = new
		// ItemEntityEgg("draft_horse_stallion");
		// entityeggdrafthorsemare = new ItemEntityEgg("draft_horse_mare");
		// entityeggdrafthorsefoal = new ItemEntityEgg("draft_horse_foal");

	}
}
