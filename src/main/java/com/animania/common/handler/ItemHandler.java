package com.animania.common.handler;

import com.animania.common.entities.EntityGender;
import com.animania.common.entities.amphibians.AmphibianType;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.horses.HorseType;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.rodents.FerretType;
import com.animania.common.entities.rodents.HamsterType;
import com.animania.common.entities.rodents.HedgehogType;
import com.animania.common.items.ItemBrownEgg;
import com.animania.common.items.ItemCarvingKnife;
import com.animania.common.items.ItemCheeseMold;
import com.animania.common.items.ItemCheeseWedge;
import com.animania.common.items.ItemCheeseWheel;
import com.animania.common.items.ItemChocolateTruffle;
import com.animania.common.items.ItemCookedAngusRoast;
import com.animania.common.items.ItemCookedAngusSteak;
import com.animania.common.items.ItemCookedDurocBacon;
import com.animania.common.items.ItemCookedDurocRoast;
import com.animania.common.items.ItemCookedFrogLegs;
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
import com.animania.common.items.ItemHamsterBall;
import com.animania.common.items.ItemHamsterFood;
import com.animania.common.items.ItemOmelette;
import com.animania.common.items.ItemPeacockEgg;
import com.animania.common.items.ItemPeacockFeather;
import com.animania.common.items.ItemRawAngusBeef;
import com.animania.common.items.ItemRawAngusSteak;
import com.animania.common.items.ItemRawDurocBacon;
import com.animania.common.items.ItemRawDurocPork;
import com.animania.common.items.ItemRawFrogLegs;
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
import com.animania.common.items.ItemRidingCrop;
import com.animania.common.items.ItemTruffle;
import com.animania.common.items.ItemTruffleSoup;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBlock;

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

    // Other Foods
    public static Item plainOmelette;
    public static Item cheeseOmelette;
    public static Item baconOmelette;
    public static Item truffleOmelette;
    public static Item ultimateOmelette;
    public static ItemBlock cheeseWheelFriesian;
    public static Item cheeseWedgeFriesian;
    public static ItemBlock cheeseWheelHolstein;
    public static Item cheeseWedgeHolstein;
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

    // Eggs - Rodents
    public static Item entityeggfrog;
    public static Item entityeggtoad;
    public static Item entityeggdartfrog;

    public static void preInit() {
        // ITEMS
        // Items for Animals
        ItemHandler.hamsterFood = new ItemHamsterFood();
        ItemHandler.truffle = new ItemTruffle();
        ItemHandler.carvingKnife = new ItemCarvingKnife(ToolMaterial.IRON);
        ItemHandler.cheeseMold = new ItemCheeseMold();
        ItemHandler.peacockFeatherBlue = new ItemPeacockFeather("blue");
        ItemHandler.peacockFeatherWhite = new ItemPeacockFeather("white");
        ItemHandler.ridingCrop = new ItemRidingCrop();
        ItemHandler.hamsterBallClear = new ItemHamsterBall(false, "hamster_ball_clear");
        ItemHandler.hamsterBallColored = new ItemHamsterBall(true, "hamster_ball_colored");
        
        
        // Other foods
        ItemHandler.ultimateOmelette = new ItemOmelette("super");
        ItemHandler.truffleOmelette = new ItemOmelette("truffle");
        ItemHandler.baconOmelette = new ItemOmelette("bacon");
        ItemHandler.cheeseOmelette = new ItemOmelette("cheese");
        ItemHandler.plainOmelette = new ItemOmelette("plain");
        ItemHandler.truffleSoup = new ItemTruffleSoup();
        ItemHandler.chocolateTruffle = new ItemChocolateTruffle();

        // ITEMS produced by Animals
        // COW ITEMS
        ItemHandler.rawHerefordBeef = new ItemRawHerefordBeef();
        ItemHandler.rawLonghornBeef = new ItemRawLonghornBeef();
        ItemHandler.rawAngusBeef = new ItemRawAngusBeef();

        ItemHandler.rawLonghornSteak = new ItemRawLonghornSteak();
        ItemHandler.rawHerefordSteak = new ItemRawHerefordSteak();
        ItemHandler.rawAngusSteak = new ItemRawAngusSteak();

        ItemHandler.cookedLonghornRoast = new ItemCookedLonghornRoast();
        ItemHandler.cookedHerefordRoast = new ItemCookedHerefordRoast();
        ItemHandler.cookedAngusRoast = new ItemCookedAngusRoast();

        ItemHandler.cookedLonghornSteak = new ItemCookedLonghornSteak();
        ItemHandler.cookedHerefordSteak = new ItemCookedHerefordSteak();
        ItemHandler.cookedAngusSteak = new ItemCookedAngusSteak();

        ItemHandler.rawPrimeBeef = new ItemRawPrimeBeef();
        ItemHandler.cookedPrimeBeef = new ItemCookedPrimeBeef();
        ItemHandler.rawPrimeSteak = new ItemRawPrimeSteak();
        ItemHandler.cookedPrimeSteak = new ItemCookedPrimeSteak();

        // PIG ITEMS
        ItemHandler.rawLargeBlackPork = new ItemRawLargeBlackPork();
        ItemHandler.rawDurocPork = new ItemRawDurocPork();
        ItemHandler.rawOldSpotPork = new ItemRawOldSpotPork();
        ItemHandler.rawHampshirePork = new ItemRawHampshirePork();

        ItemHandler.rawLargeBlackBacon = new ItemRawLargeBlackBacon();
        ItemHandler.rawDurocBacon = new ItemRawDurocBacon();
        ItemHandler.rawOldSpotBacon = new ItemRawOldSpotBacon();
        ItemHandler.rawHampshireBacon = new ItemRawHampshireBacon();

        ItemHandler.cookedLargeBlackRoast = new ItemCookedLargeBlackRoast();
        ItemHandler.cookedDurocRoast = new ItemCookedDurocRoast();
        ItemHandler.cookedOldSpotRoast = new ItemCookedOldSpotRoast();
        ItemHandler.cookedHampshireRoast = new ItemCookedHampshireRoast();

        ItemHandler.cookedLargeBlackBacon = new ItemCookedLargeBlackBacon();
        ItemHandler.cookedDurocBacon = new ItemCookedDurocBacon();
        ItemHandler.cookedOldSpotBacon = new ItemCookedOldSpotBacon();
        ItemHandler.cookedHampshireBacon = new ItemCookedHampshireBacon();

        ItemHandler.rawPrimePork = new ItemRawPrimePork();
        ItemHandler.cookedPrimePork = new ItemCookedPrimePork();
        ItemHandler.rawPrimeBacon = new ItemRawPrimeBacon();
        ItemHandler.cookedPrimeBacon = new ItemCookedPrimeBacon();

        // CHICKEN ITEMS
        ItemHandler.rawOrpingtonChicken = new ItemRawOrpingtonChicken();
        ItemHandler.rawPlymouthRockChicken = new ItemRawPlymouthRockChicken();
        ItemHandler.rawWyandotteChicken = new ItemRawWyandotteChicken();
        ItemHandler.rawRhodeIslandRedChicken = new ItemRawRhodeIslandRedChicken();
        ItemHandler.cookedOrpingtonChicken = new ItemCookedOrpingtonChicken();
        ItemHandler.cookedPlymouthRockChicken = new ItemCookedPlymouthRockChicken();
        ItemHandler.cookedWyandotteChicken = new ItemCookedWyandotteChicken();
        ItemHandler.cookedRhodeIslandRedChicken = new ItemCookedRhodeIslandRedChicken();
        ItemHandler.rawPrimeChicken = new ItemRawPrimeChicken();
        ItemHandler.cookedPrimeChicken = new ItemCookedPrimeChicken();
        ItemHandler.brownEgg = new ItemBrownEgg();
        ItemHandler.peacockEggBlue = new ItemPeacockEgg("blue");
		ItemHandler.peacockEggWhite = new ItemPeacockEgg("white");

        // FROG ITEMS
        ItemHandler.rawFrogLegs = new ItemRawFrogLegs();
        ItemHandler.cookedFrogLegs = new ItemCookedFrogLegs();

        // CHEESE
       // ItemHandler.cheeseWheelFriesian = new ItemCheeseWheel("friesian");
        //ItemHandler.cheeseWheelHolstein = new ItemCheeseWheel("holstein");
        ItemHandler.cheeseWedgeFriesian = new ItemCheeseWedge("friesian");
        ItemHandler.cheeseWedgeHolstein = new ItemCheeseWedge("holstein");

        // Item Entity Eggs
        // COWS
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
        ItemHandler.entityegghamster = new ItemEntityEgg("hamster", HamsterType.STANDARD, EntityGender.MALE);
        ItemHandler.entityeggferretgrey = new ItemEntityEgg("ferret_grey", FerretType.GREY, EntityGender.MALE);
        ItemHandler.entityeggferretwhite = new ItemEntityEgg("ferret_white", FerretType.WHITE, EntityGender.MALE);
        ItemHandler.entityegghedgehog = new ItemEntityEgg("hedgehog", HedgehogType.NORMAL, EntityGender.MALE);
        ItemHandler.entityegghedgehogalbino = new ItemEntityEgg("hedgehog_albino", HedgehogType.ALBINO, EntityGender.MALE);

        // AMPHIBIANS
        ItemHandler.entityeggfrog = new ItemEntityEgg("frog", AmphibianType.FROG, EntityGender.MALE);
        ItemHandler.entityeggtoad = new ItemEntityEgg("toad", AmphibianType.TOAD, EntityGender.MALE);
        ItemHandler.entityeggdartfrog = new ItemEntityEgg("dart_frog", AmphibianType.DART_FROG, EntityGender.MALE);

        // HORSES
        entityeggdrafthorsestallion = new ItemEntityEgg("draft_horse_stallion", HorseType.DRAFT, EntityGender.MALE);
        entityeggdrafthorsemare = new ItemEntityEgg("draft_horse_mare", HorseType.DRAFT, EntityGender.FEMALE);
        entityeggdrafthorsefoal = new ItemEntityEgg("draft_horse_foal", HorseType.DRAFT, EntityGender.CHILD);

    }
}
