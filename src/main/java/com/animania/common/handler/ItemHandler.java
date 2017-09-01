package com.animania.common.handler;

import com.animania.common.entities.EntityGender;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.entities.amphibians.AmphibianType;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.horses.HorseType;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.rodents.FerretType;
import com.animania.common.entities.rodents.HamsterType;
import com.animania.common.entities.rodents.HedgehogType;
import com.animania.common.items.ItemAnimaniaFood;
import com.animania.common.items.ItemAnimaniaFoodRaw;
import com.animania.common.items.ItemBrownEgg;
import com.animania.common.items.ItemCarvingKnife;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemHamsterBall;
import com.animania.common.items.ItemHamsterFood;
import com.animania.common.items.ItemPeacockEgg;
import com.animania.common.items.ItemPeacockFeather;
import com.animania.common.items.ItemRidingCrop;
import com.animania.common.items.ItemTruffle;
import com.animania.common.items.ItemTruffleSoup;
import com.animania.config.AnimaniaConfig;

import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;

public class ItemHandler
{
	// Items
	public static Item hamsterFood;
	public static Item truffle;
	public static Item brownEgg;
	public static Item carvingKnife;
	public static Item cheeseMold;
	public static Item hamsterBallClear;
	public static Item hamsterBallColored;
	public static Item peacockEggBlue;
	public static Item peacockEggWhite;

	public static Item peacockFeatherBlue;
	public static Item peacockFeatherWhite;
	public static Item peacockFeatherCharcoal;
	public static Item peacockFeatherOpal;
	public static Item peacockFeatherPeach;
	public static Item peacockFeatherPurple;
	public static Item peacockFeatherTaupe;

	public static Item ridingCrop;

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

	// Frogs
	public static Item rawFrogLegs;
	public static Item cookedFrogLegs;

	// Goats
	public static Item rawChevon;
	public static Item cookedChevon;
	public static Item rawPrimeChevon;
	public static Item cookedPrimeChevon;

	// Sheep
	public static Item rawMutton;
	public static Item cookedMutton;

	// Rabbit
	public static Item rawRabbit;
	public static Item cookedRabbit;

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
	public static Item cheeseWheelGoat;
	public static Item cheeseWedgeGoat;
	public static Item cheeseWheelSheep;
	public static Item cheeseWedgeSheep;
	public static Item truffleSoup;
	public static Item chocolateTruffle;

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

	public static Item entityeggpeachickcharcoal;
	public static Item entityeggpeafowlcharcoal;
	public static Item entityeggpeacockcharcoal;

	public static Item entityeggpeachickopal;
	public static Item entityeggpeafowlopal;
	public static Item entityeggpeacockopal;

	public static Item entityeggpeachickpeach;
	public static Item entityeggpeafowlpeach;
	public static Item entityeggpeacockpeach;

	public static Item entityeggpeachickpurple;
	public static Item entityeggpeafowlpurple;
	public static Item entityeggpeacockpurple;

	public static Item entityeggpeachicktaupe;
	public static Item entityeggpeafowltaupe;
	public static Item entityeggpeacocktaupe;
	
	public static Item entityeggrandompeacock;

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
	public static Item entityeggdrafthorsestallion;
	public static Item entityeggdrafthorsemare;
	public static Item entityeggdrafthorsefoal;

	// Eggs - Amphibians
	public static Item entityeggfrog;
	public static Item entityeggtoad;
	public static Item entityeggdartfrog;

	// Eggs - Goats
	public static Item entityeggkidalpine;
	public static Item entityeggbuckalpine;
	public static Item entityeggdoealpine;

	public static Item entityeggkidangora;
	public static Item entityeggbuckangora;
	public static Item entityeggdoeangora;

	public static Item entityeggkidfainting;
	public static Item entityeggbuckfainting;
	public static Item entityeggdoefainting;

	public static Item entityeggkidkiko;
	public static Item entityeggbuckkiko;
	public static Item entityeggdoekiko;

	public static Item entityeggkidkinder;
	public static Item entityeggbuckkinder;
	public static Item entityeggdoekinder;

	public static Item entityeggkidnigeriandwarf;
	public static Item entityeggbucknigeriandwarf;
	public static Item entityeggdoenigeriandwarf;

	public static Item entityeggkidpygmy;
	public static Item entityeggbuckpygmy;
	public static Item entityeggdoepygmy;
	
	public static Item entityeggrandomgoat;
	
	public static Item entityeggrandomanimal;


	public static void preInit() {
		// ITEMS
		// Items for Animals
		ItemHandler.hamsterFood = new ItemHamsterFood();
		ItemHandler.truffle = new ItemTruffle();
		ItemHandler.carvingKnife = new ItemCarvingKnife(ToolMaterial.IRON);
		ItemHandler.peacockFeatherBlue = new ItemPeacockFeather("blue");
		ItemHandler.peacockFeatherWhite = new ItemPeacockFeather("white");
		ItemHandler.peacockFeatherCharcoal = new ItemPeacockFeather("charcoal");
		ItemHandler.peacockFeatherOpal = new ItemPeacockFeather("opal");
		ItemHandler.peacockFeatherPeach = new ItemPeacockFeather("peach");
		ItemHandler.peacockFeatherPurple = new ItemPeacockFeather("purple");
		ItemHandler.peacockFeatherTaupe = new ItemPeacockFeather("taupe");

		ItemHandler.ridingCrop = new ItemRidingCrop();
		ItemHandler.hamsterBallClear = new ItemHamsterBall(false, "hamster_ball_clear");
		ItemHandler.hamsterBallColored = new ItemHamsterBall(true, "hamster_ball_colored");

		// Other foods
		ItemHandler.ultimateOmelette = new ItemAnimaniaFood(5, 5f, "super_omelette", new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false), new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false), new PotionEffect(MobEffects.RESISTANCE, 600, 1, false, false));
		ItemHandler.truffleOmelette = new ItemAnimaniaFood(5, 5f, "truffle_omelette", new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false));
		ItemHandler.baconOmelette = new ItemAnimaniaFood(5, 5f, "bacon_omelette", new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));
		ItemHandler.cheeseOmelette = new ItemAnimaniaFood(5, 5f, "cheese_omelette", new PotionEffect(MobEffects.INSTANT_HEALTH, 2, 2, false, false));
		ItemHandler.plainOmelette = new ItemAnimaniaFood(5, 5f, "plain_omelette");
		ItemHandler.truffleSoup = new ItemTruffleSoup();
		ItemHandler.chocolateTruffle = new ItemAnimaniaFood(6, 6f, "chocolate_truffle", new PotionEffect(MobEffects.SPEED, 1200, 3, false, false));

		// ITEMS produced by Animals
		// COW ITEMS
		ItemHandler.rawHerefordBeef = new ItemAnimaniaFoodRaw("raw_hereford_beef", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawLonghornBeef = new ItemAnimaniaFoodRaw("raw_longhorn_beef", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawAngusBeef = new ItemAnimaniaFoodRaw("raw_angus_beef", AnimaniaConfig.drops.oldMeatDrops);

		ItemHandler.rawLonghornSteak = new ItemAnimaniaFoodRaw("raw_longhorn_steak", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawHerefordSteak = new ItemAnimaniaFoodRaw("raw_hereford_steak", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawAngusSteak = new ItemAnimaniaFoodRaw("raw_angus_steak", AnimaniaConfig.drops.oldMeatDrops);

		ItemHandler.cookedLonghornRoast = new ItemAnimaniaFood(10, 10f, "cooked_longhorn_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 6, 1, false, false));
		ItemHandler.cookedHerefordRoast = new ItemAnimaniaFood(12, 12f, "cooked_hereford_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 4, 1, false, false));
		ItemHandler.cookedAngusRoast = new ItemAnimaniaFood(20, 20f, "cooked_angus_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 1, false, false));

		ItemHandler.cookedLonghornSteak = new ItemAnimaniaFood(5, 5f, "cooked_longhorn_steak", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 3, 1, false, false));
		ItemHandler.cookedHerefordSteak = new ItemAnimaniaFood(6, 6f, "cooked_hereford_steak", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 2, 1, false, false));
		ItemHandler.cookedAngusSteak = new ItemAnimaniaFood(10, 10f, "cooked_angus_steak", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 5, 1, false, false));

		ItemHandler.rawPrimeBeef = new ItemAnimaniaFoodRaw("raw_prime_beef", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimeBeef = new ItemAnimaniaFood(20, 20f, "cooked_prime_beef", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 1, false, false));
		ItemHandler.rawPrimeSteak = new ItemAnimaniaFoodRaw("raw_prime_steak", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimeSteak = new ItemAnimaniaFood(10, 10f, "cooked_prime_steak", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 5, 1, false, false));

		// PIG ITEMS
		ItemHandler.rawLargeBlackPork = new ItemAnimaniaFoodRaw("raw_large_black_pork", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawDurocPork = new ItemAnimaniaFoodRaw("raw_duroc_pork", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawOldSpotPork = new ItemAnimaniaFoodRaw("raw_old_spot_pork", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawHampshirePork = new ItemAnimaniaFoodRaw("raw_hampshire_pork", AnimaniaConfig.drops.oldMeatDrops);

		ItemHandler.rawLargeBlackBacon = new ItemAnimaniaFoodRaw("raw_large_black_bacon", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawDurocBacon = new ItemAnimaniaFoodRaw("raw_duroc_bacon", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawOldSpotBacon = new ItemAnimaniaFoodRaw("raw_old_spot_bacon", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawHampshireBacon = new ItemAnimaniaFoodRaw("raw_hampshire_bacon", AnimaniaConfig.drops.oldMeatDrops);

		ItemHandler.cookedLargeBlackRoast = new ItemAnimaniaFood(16, 16f, "cooked_large_black_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 2, false, false));
		ItemHandler.cookedDurocRoast = new ItemAnimaniaFood(12, 12f, "cooked_duroc_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 1, false, false));
		ItemHandler.cookedOldSpotRoast = new ItemAnimaniaFood(10, 10f, "cooked_old_spot_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 1, false, false));
		ItemHandler.cookedHampshireRoast = new ItemAnimaniaFood(8, 8f, "cooked_hampshire_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 1, false, false));

		ItemHandler.cookedLargeBlackBacon = new ItemAnimaniaFood(8, 16f, "cooked_large_black_bacon", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 2, false, false));
		ItemHandler.cookedDurocBacon = new ItemAnimaniaFood(6, 12f, "cooked_duroc_bacon", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 0, false, false));
		ItemHandler.cookedOldSpotBacon = new ItemAnimaniaFood(5, 10f, "cooked_old_spot_bacon", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 0, false, false));
		ItemHandler.cookedHampshireBacon = new ItemAnimaniaFood(4, 8f, "cooked_hampshire_bacon", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 0, false, false));

		ItemHandler.rawPrimePork = new ItemAnimaniaFoodRaw("raw_prime_pork", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimePork = new ItemAnimaniaFood(16, 16f, "cooked_prime_pork", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 2, false, false));
		ItemHandler.rawPrimeBacon = new ItemAnimaniaFoodRaw("raw_prime_bacon", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimeBacon = new ItemAnimaniaFood(8, 16f, "cooked_prime_bacon", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 2, false, false));

		// CHICKEN ITEMS
		ItemHandler.rawOrpingtonChicken = new ItemAnimaniaFoodRaw("raw_orpington_chicken", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawPlymouthRockChicken = new ItemAnimaniaFoodRaw("raw_plymouth_rock_chicken", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawWyandotteChicken = new ItemAnimaniaFoodRaw("raw_wyandotte_chicken", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawRhodeIslandRedChicken = new ItemAnimaniaFoodRaw("raw_rhode_island_red_chicken", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedOrpingtonChicken = new ItemAnimaniaFood(12, 12f, "cooked_orpington_chicken", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 3000, 0, false, false));
		ItemHandler.cookedPlymouthRockChicken = new ItemAnimaniaFood(10, 10f, "cooked_plymouth_rock_chicken", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 2400, 0, false, false));
		ItemHandler.cookedWyandotteChicken = new ItemAnimaniaFood(6, 6f, "cooked_wyandotte_chicken", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 1800, 0, false, false));
		ItemHandler.cookedRhodeIslandRedChicken = new ItemAnimaniaFood(8, 8f, "cooked_rhode_island_red_chicken", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 1200, 0, false, false));
		ItemHandler.rawPrimeChicken = new ItemAnimaniaFoodRaw("raw_prime_chicken", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimeChicken = new ItemAnimaniaFood(12, 12f, "cooked_prime_chicken", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 3000, 0, false, false));
		ItemHandler.brownEgg = new ItemBrownEgg();
		ItemHandler.peacockEggBlue = new ItemPeacockEgg("blue");
		ItemHandler.peacockEggWhite = new ItemPeacockEgg("white");

		// FROG ITEMS
		ItemHandler.rawFrogLegs = new ItemAnimaniaFoodRaw("raw_frog_legs");
		ItemHandler.cookedFrogLegs = new ItemAnimaniaFood(3, 5f, "cooked_frog_legs", new PotionEffect(MobEffects.JUMP_BOOST, 1200, 2, false, false));

		// GOAT ITEMS
		ItemHandler.rawChevon = new ItemAnimaniaFoodRaw("raw_chevon");
		ItemHandler.cookedChevon = new ItemAnimaniaFood(3, 5f, "cooked_chevon", new PotionEffect(MobEffects.RESISTANCE, 600, 0, false, false));
		ItemHandler.rawPrimeChevon = new ItemAnimaniaFoodRaw("raw_prime_chevon");
		ItemHandler.cookedPrimeChevon = new ItemAnimaniaFood(3, 5f, "cooked_prime_chevon", new PotionEffect(MobEffects.RESISTANCE, 1200, 1, false, false));

		// SHEEP ITEMS
		ItemHandler.rawMutton = new ItemAnimaniaFoodRaw("raw_prime_mutton");
		ItemHandler.cookedMutton = new ItemAnimaniaFood(3, 5f, "cooked_prime_mutton", new PotionEffect(MobEffects.INSTANT_HEALTH, 5, 1, false, false));

		// RABBIT ITEMS
		ItemHandler.rawRabbit = new ItemAnimaniaFoodRaw("raw_prime_rabbit");
		ItemHandler.cookedRabbit = new ItemAnimaniaFood(4, 6f, "cooked_prime_rabbit", new PotionEffect(MobEffects.JUMP_BOOST, 600, 3, false, false));

		// CHEESE
		ItemHandler.cheeseWedgeFriesian = new ItemAnimaniaFood(2, 2f, "friesian_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 6, 2, false, false));
		ItemHandler.cheeseWedgeHolstein = new ItemAnimaniaFood(2, 2f, "holstein_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 12, 2, false, false));
		ItemHandler.cheeseWedgeGoat = new ItemAnimaniaFood(2, 2f, "goat_cheese_wedge", new PotionEffect(MobEffects.RESISTANCE, 1200, 0, false, false));
		ItemHandler.cheeseWedgeSheep = new ItemAnimaniaFood(2, 2f, "sheep_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 0, false, false));

		// Item Entity Eggs
		// COWS
		ItemHandler.entityeggrandomanimal = new ItemEntityEgg("random", new RandomAnimalType(), EntityGender.RANDOM);
		
		ItemHandler.entityeggcalfholstein = new ItemEntityEgg("calf_holstein", CowType.HOLSTEIN, EntityGender.CHILD);
		ItemHandler.entityeggcowholstein = new ItemEntityEgg("cow_holstein", CowType.HOLSTEIN, EntityGender.FEMALE);
		ItemHandler.entityeggbullholstein = new ItemEntityEgg("bull_holstein", CowType.HOLSTEIN, EntityGender.MALE);

		ItemHandler.entityeggcalffriesian = new ItemEntityEgg("calf_friesian" , CowType.FRIESIAN, EntityGender.CHILD);
		ItemHandler.entityeggcowfriesian = new ItemEntityEgg("cow_friesian", CowType.FRIESIAN, EntityGender.FEMALE);
		ItemHandler.entityeggbullfriesian = new ItemEntityEgg("bull_friesian", CowType.FRIESIAN, EntityGender.MALE);

		ItemHandler.entityeggcalfangus = new ItemEntityEgg("calf_angus", CowType.ANGUS, EntityGender.CHILD);
		ItemHandler.entityeggcowangus = new ItemEntityEgg("cow_angus", CowType.ANGUS, EntityGender.FEMALE);
		ItemHandler.entityeggbullangus = new ItemEntityEgg("bull_angus", CowType.ANGUS, EntityGender.MALE);

		ItemHandler.entityeggcalflonghorn = new ItemEntityEgg("calf_longhorn", CowType.LONGHORN, EntityGender.CHILD);
		ItemHandler.entityeggcowlonghorn = new ItemEntityEgg("cow_longhorn", CowType.LONGHORN, EntityGender.FEMALE);
		ItemHandler.entityeggbulllonghorn = new ItemEntityEgg("bull_longhorn", CowType.LONGHORN, EntityGender.MALE);

		ItemHandler.entityeggcalfhereford = new ItemEntityEgg("calf_hereford", CowType.HEREFORD, EntityGender.CHILD);
		ItemHandler.entityeggcowhereford = new ItemEntityEgg("cow_hereford", CowType.HEREFORD, EntityGender.FEMALE);
		ItemHandler.entityeggbullhereford = new ItemEntityEgg("bull_hereford", CowType.HEREFORD, EntityGender.MALE);

		ItemHandler.entityeggrandomcow = new ItemEntityEgg("cow_random", CowType.ANGUS, EntityGender.RANDOM);

		// CHICKENS
		ItemHandler.entityeggchickplymouth = new ItemEntityEgg("chick_plymouth", ChickenType.PLYMOUTH_ROCK, EntityGender.CHILD);
		ItemHandler.entityegghenplymouth = new ItemEntityEgg("hen_plymouth", ChickenType.PLYMOUTH_ROCK, EntityGender.FEMALE);
		ItemHandler.entityeggroosterplymouth = new ItemEntityEgg("rooster_plymouth", ChickenType.PLYMOUTH_ROCK, EntityGender.MALE);

		ItemHandler.entityeggchickleghorn = new ItemEntityEgg("chick_leghorn", ChickenType.LEGHORN, EntityGender.CHILD);
		ItemHandler.entityegghenleghorn = new ItemEntityEgg("hen_leghorn", ChickenType.LEGHORN, EntityGender.FEMALE);
		ItemHandler.entityeggroosterleghorn = new ItemEntityEgg("rooster_leghorn", ChickenType.LEGHORN, EntityGender.MALE);

		ItemHandler.entityeggchickorpington = new ItemEntityEgg("chick_orpington", ChickenType.ORPINGTON, EntityGender.CHILD);
		ItemHandler.entityegghenorpington = new ItemEntityEgg("hen_orpington", ChickenType.ORPINGTON, EntityGender.FEMALE);
		ItemHandler.entityeggroosterorpington = new ItemEntityEgg("rooster_orpington", ChickenType.ORPINGTON, EntityGender.MALE);

		ItemHandler.entityeggchickred = new ItemEntityEgg("chick_red", ChickenType.RHODE_ISLAND_RED, EntityGender.CHILD);
		ItemHandler.entityegghenred = new ItemEntityEgg("hen_red", ChickenType.RHODE_ISLAND_RED, EntityGender.FEMALE);
		ItemHandler.entityeggroosterred = new ItemEntityEgg("rooster_red", ChickenType.RHODE_ISLAND_RED, EntityGender.MALE);

		ItemHandler.entityeggchickwyandotte = new ItemEntityEgg("chick_wyandotte", ChickenType.WYANDOTTE, EntityGender.CHILD);
		ItemHandler.entityegghenwyandotte = new ItemEntityEgg("hen_wyandotte", ChickenType.WYANDOTTE, EntityGender.FEMALE);
		ItemHandler.entityeggroosterwyandotte = new ItemEntityEgg("rooster_wyandotte", ChickenType.WYANDOTTE, EntityGender.MALE);

		ItemHandler.entityeggrandomchicken = new ItemEntityEgg("chicken_random", ChickenType.LEGHORN, EntityGender.RANDOM);

		// PEAFOWL
		ItemHandler.entityeggpeachickblue = new ItemEntityEgg("peachick_blue", PeacockType.BLUE, EntityGender.CHILD);
		ItemHandler.entityeggpeafowlblue = new ItemEntityEgg("peafowl_blue", PeacockType.BLUE, EntityGender.FEMALE);
		ItemHandler.entityeggpeacockblue = new ItemEntityEgg("peacock_blue", PeacockType.BLUE, EntityGender.MALE);

		ItemHandler.entityeggpeachickwhite = new ItemEntityEgg("peachick_white", PeacockType.WHITE, EntityGender.CHILD);
		ItemHandler.entityeggpeafowlwhite = new ItemEntityEgg("peafowl_white", PeacockType.WHITE, EntityGender.FEMALE);
		ItemHandler.entityeggpeacockwhite = new ItemEntityEgg("peacock_white", PeacockType.WHITE, EntityGender.MALE);

		ItemHandler.entityeggpeachickcharcoal = new ItemEntityEgg("peachick_charcoal", PeacockType.CHARCOAL, EntityGender.CHILD);
		ItemHandler.entityeggpeafowlcharcoal = new ItemEntityEgg("peafowl_charcoal", PeacockType.CHARCOAL, EntityGender.FEMALE);
		ItemHandler.entityeggpeacockcharcoal = new ItemEntityEgg("peacock_charcoal", PeacockType.CHARCOAL, EntityGender.MALE);

		ItemHandler.entityeggpeachickopal = new ItemEntityEgg("peachick_opal", PeacockType.OPAL, EntityGender.CHILD);
		ItemHandler.entityeggpeafowlopal = new ItemEntityEgg("peafowl_opal", PeacockType.OPAL, EntityGender.FEMALE);
		ItemHandler.entityeggpeacockopal = new ItemEntityEgg("peacock_opal", PeacockType.OPAL, EntityGender.MALE);

		ItemHandler.entityeggpeachickpeach = new ItemEntityEgg("peachick_peach", PeacockType.PEACH, EntityGender.CHILD);
		ItemHandler.entityeggpeafowlpeach = new ItemEntityEgg("peafowl_peach", PeacockType.PEACH, EntityGender.FEMALE);
		ItemHandler.entityeggpeacockpeach = new ItemEntityEgg("peacock_peach", PeacockType.PEACH, EntityGender.MALE);

		ItemHandler.entityeggpeachickpurple = new ItemEntityEgg("peachick_purple", PeacockType.PURPLE, EntityGender.CHILD);
		ItemHandler.entityeggpeafowlpurple = new ItemEntityEgg("peafowl_purple", PeacockType.PURPLE, EntityGender.FEMALE);
		ItemHandler.entityeggpeacockpurple = new ItemEntityEgg("peacock_purple", PeacockType.PURPLE, EntityGender.MALE);

		ItemHandler.entityeggpeachicktaupe = new ItemEntityEgg("peachick_taupe", PeacockType.TAUPE, EntityGender.CHILD);
		ItemHandler.entityeggpeafowltaupe = new ItemEntityEgg("peafowl_taupe", PeacockType.TAUPE, EntityGender.FEMALE);
		ItemHandler.entityeggpeacocktaupe = new ItemEntityEgg("peacock_taupe", PeacockType.TAUPE, EntityGender.MALE);
		
		ItemHandler.entityeggrandompeacock = new ItemEntityEgg("peacock_random", PeacockType.BLUE, EntityGender.RANDOM);

		// PIGS
		ItemHandler.entityeggpigletyorkshire = new ItemEntityEgg("piglet_yorkshire", PigType.YORKSHIRE, EntityGender.CHILD);
		ItemHandler.entityeggsowyorkshire = new ItemEntityEgg("sow_yorkshire", PigType.YORKSHIRE, EntityGender.FEMALE);
		ItemHandler.entityegghogyorkshire = new ItemEntityEgg("hog_yorkshire", PigType.YORKSHIRE, EntityGender.MALE);

		ItemHandler.entityeggpigletoldspot = new ItemEntityEgg("piglet_oldspot", PigType.OLD_SPOT, EntityGender.CHILD);
		ItemHandler.entityeggsowoldspot = new ItemEntityEgg("sow_oldspot", PigType.OLD_SPOT, EntityGender.FEMALE);
		ItemHandler.entityegghogoldspot = new ItemEntityEgg("hog_oldspot", PigType.OLD_SPOT, EntityGender.MALE);

		ItemHandler.entityeggpigletlargeblack = new ItemEntityEgg("piglet_largeblack", PigType.LARGE_BLACK, EntityGender.CHILD);
		ItemHandler.entityeggsowlargeblack = new ItemEntityEgg("sow_largeblack", PigType.LARGE_BLACK, EntityGender.FEMALE);
		ItemHandler.entityegghoglargeblack = new ItemEntityEgg("hog_largeblack", PigType.LARGE_BLACK, EntityGender.MALE);

		ItemHandler.entityeggpigletlargewhite = new ItemEntityEgg("piglet_largewhite", PigType.LARGE_WHITE, EntityGender.CHILD);
		ItemHandler.entityeggsowlargewhite = new ItemEntityEgg("sow_largewhite", PigType.LARGE_WHITE, EntityGender.FEMALE);
		ItemHandler.entityegghoglargewhite = new ItemEntityEgg("hog_largewhite", PigType.LARGE_WHITE, EntityGender.MALE);

		ItemHandler.entityeggpigletduroc = new ItemEntityEgg("piglet_duroc", PigType.DUROC, EntityGender.CHILD);
		ItemHandler.entityeggsowduroc = new ItemEntityEgg("sow_duroc", PigType.DUROC, EntityGender.FEMALE);
		ItemHandler.entityegghogduroc = new ItemEntityEgg("hog_duroc", PigType.DUROC, EntityGender.MALE);

		ItemHandler.entityeggpiglethampshire = new ItemEntityEgg("piglet_hampshire", PigType.HAMPSHIRE, EntityGender.CHILD);
		ItemHandler.entityeggsowhampshire = new ItemEntityEgg("sow_hampshire", PigType.HAMPSHIRE, EntityGender.FEMALE);
		ItemHandler.entityegghoghampshire = new ItemEntityEgg("hog_hampshire", PigType.HAMPSHIRE, EntityGender.MALE);

		ItemHandler.entityeggrandompig = new ItemEntityEgg("pig_random", PigType.DUROC, EntityGender.RANDOM);

		// RODENTS
		ItemHandler.entityegghamster = new ItemEntityEgg("hamster", HamsterType.STANDARD, EntityGender.NONE);
		ItemHandler.entityeggferretgrey = new ItemEntityEgg("ferret_grey", FerretType.GREY, EntityGender.NONE);
		ItemHandler.entityeggferretwhite = new ItemEntityEgg("ferret_white", FerretType.WHITE, EntityGender.NONE);
		ItemHandler.entityegghedgehog = new ItemEntityEgg("hedgehog", HedgehogType.NORMAL, EntityGender.NONE);
		ItemHandler.entityegghedgehogalbino = new ItemEntityEgg("hedgehog_albino", HedgehogType.ALBINO, EntityGender.NONE);

		// AMPHIBIANS
		ItemHandler.entityeggfrog = new ItemEntityEgg("frog", AmphibianType.FROG, EntityGender.NONE);
		ItemHandler.entityeggtoad = new ItemEntityEgg("toad", AmphibianType.TOAD, EntityGender.NONE);
		ItemHandler.entityeggdartfrog = new ItemEntityEgg("dart_frog", AmphibianType.DART_FROG, EntityGender.NONE);

		// HORSES
		ItemHandler.entityeggdrafthorsestallion = new ItemEntityEgg("draft_horse_stallion", HorseType.DRAFT, EntityGender.MALE);
		ItemHandler.entityeggdrafthorsemare = new ItemEntityEgg("draft_horse_mare", HorseType.DRAFT, EntityGender.FEMALE);
		ItemHandler.entityeggdrafthorsefoal = new ItemEntityEgg("draft_horse_foal", HorseType.DRAFT, EntityGender.CHILD);

		// GOATS
		ItemHandler.entityeggkidalpine = new ItemEntityEgg("kid_alpine", GoatType.ALPINE, EntityGender.CHILD);
		ItemHandler.entityeggbuckalpine = new ItemEntityEgg("buck_alpine", GoatType.ALPINE, EntityGender.MALE);
		ItemHandler.entityeggdoealpine = new ItemEntityEgg("doe_alpine", GoatType.ALPINE, EntityGender.FEMALE);

		ItemHandler.entityeggkidangora = new ItemEntityEgg("kid_angora", GoatType.ANGORA, EntityGender.CHILD);
		ItemHandler.entityeggbuckangora = new ItemEntityEgg("buck_angora", GoatType.ANGORA, EntityGender.MALE);
		ItemHandler.entityeggdoeangora = new ItemEntityEgg("doe_angora", GoatType.ANGORA, EntityGender.FEMALE);

		ItemHandler.entityeggkidfainting = new ItemEntityEgg("kid_fainting", GoatType.FAINTING, EntityGender.CHILD);
		ItemHandler.entityeggbuckfainting = new ItemEntityEgg("buck_fainting", GoatType.FAINTING, EntityGender.MALE);
		ItemHandler.entityeggdoefainting = new ItemEntityEgg("doe_fainting", GoatType.FAINTING, EntityGender.FEMALE);

		ItemHandler.entityeggkidkiko = new ItemEntityEgg("kid_kiko", GoatType.KIKO, EntityGender.CHILD);
		ItemHandler.entityeggbuckkiko = new ItemEntityEgg("buck_kiko", GoatType.KIKO, EntityGender.MALE);
		ItemHandler.entityeggdoekiko = new ItemEntityEgg("doe_kiko", GoatType.KIKO, EntityGender.FEMALE);

		ItemHandler.entityeggkidkinder = new ItemEntityEgg("kid_kinder", GoatType.KINDER, EntityGender.CHILD);
		ItemHandler.entityeggbuckkinder = new ItemEntityEgg("buck_kinder", GoatType.KINDER, EntityGender.MALE);
		ItemHandler.entityeggdoekinder = new ItemEntityEgg("doe_kinder", GoatType.KINDER, EntityGender.FEMALE);

		ItemHandler.entityeggkidnigeriandwarf = new ItemEntityEgg("kid_nigeriandwarf", GoatType.NIGERIAN_DWARF, EntityGender.CHILD);
		ItemHandler.entityeggbucknigeriandwarf = new ItemEntityEgg("buck_nigeriandwarf", GoatType.NIGERIAN_DWARF, EntityGender.MALE);
		ItemHandler.entityeggdoenigeriandwarf = new ItemEntityEgg("doe_nigeriandwarf", GoatType.NIGERIAN_DWARF, EntityGender.FEMALE);

		ItemHandler.entityeggkidpygmy = new ItemEntityEgg("kid_pygmy", GoatType.PYGMY, EntityGender.CHILD);
		ItemHandler.entityeggbuckpygmy = new ItemEntityEgg("buck_pygmy", GoatType.PYGMY, EntityGender.MALE);
		ItemHandler.entityeggdoepygmy = new ItemEntityEgg("doe_pygmy", GoatType.PYGMY, EntityGender.FEMALE);
		
		ItemHandler.entityeggrandomgoat = new ItemEntityEgg("goat_random", GoatType.ALPINE, EntityGender.RANDOM);

	}
}
