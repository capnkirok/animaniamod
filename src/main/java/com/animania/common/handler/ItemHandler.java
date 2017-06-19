package com.animania.common.handler;

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
    public static Item cheeseWheelFriesian;
    public static Item cheeseWedgeFriesian;
    public static Item cheeseWheelHolstein;
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

        // FROG ITEMS
        ItemHandler.rawFrogLegs = new ItemRawFrogLegs();
        ItemHandler.cookedFrogLegs = new ItemCookedFrogLegs();

        // CHEESE
        ItemHandler.cheeseWheelFriesian = new ItemCheeseWheel("friesian");
        ItemHandler.cheeseWheelHolstein = new ItemCheeseWheel("holstein");
        ItemHandler.cheeseWedgeFriesian = new ItemCheeseWedge("friesian");
        ItemHandler.cheeseWedgeHolstein = new ItemCheeseWedge("holstein");

        // Item Entity Eggs
        // COWS
        ItemHandler.entityeggcalfholstein = new ItemEntityEgg("calf_holstein");
        ItemHandler.entityeggcowholstein = new ItemEntityEgg("cow_holstein");
        ItemHandler.entityeggbullholstein = new ItemEntityEgg("bull_holstein");

        ItemHandler.entityeggcalffriesian = new ItemEntityEgg("calf_friesian");
        ItemHandler.entityeggcowfriesian = new ItemEntityEgg("cow_friesian");
        ItemHandler.entityeggbullfriesian = new ItemEntityEgg("bull_friesian");

        ItemHandler.entityeggcalfangus = new ItemEntityEgg("calf_angus");
        ItemHandler.entityeggcowangus = new ItemEntityEgg("cow_angus");
        ItemHandler.entityeggbullangus = new ItemEntityEgg("bull_angus");

        ItemHandler.entityeggcalflonghorn = new ItemEntityEgg("calf_longhorn");
        ItemHandler.entityeggcowlonghorn = new ItemEntityEgg("cow_longhorn");
        ItemHandler.entityeggbulllonghorn = new ItemEntityEgg("bull_longhorn");

        ItemHandler.entityeggcalfhereford = new ItemEntityEgg("calf_hereford");
        ItemHandler.entityeggcowhereford = new ItemEntityEgg("cow_hereford");
        ItemHandler.entityeggbullhereford = new ItemEntityEgg("bull_hereford");

        ItemHandler.entityeggrandomcow = new ItemEntityEgg("cow_random");

        // CHICKENS
        ItemHandler.entityeggchickplymouth = new ItemEntityEgg("chick_plymouth");
        ItemHandler.entityegghenplymouth = new ItemEntityEgg("hen_plymouth");
        ItemHandler.entityeggroosterplymouth = new ItemEntityEgg("rooster_plymouth");

        ItemHandler.entityeggchickleghorn = new ItemEntityEgg("chick_leghorn");
        ItemHandler.entityegghenleghorn = new ItemEntityEgg("hen_leghorn");
        ItemHandler.entityeggroosterleghorn = new ItemEntityEgg("rooster_leghorn");

        ItemHandler.entityeggchickorpington = new ItemEntityEgg("chick_orpington");
        ItemHandler.entityegghenorpington = new ItemEntityEgg("hen_orpington");
        ItemHandler.entityeggroosterorpington = new ItemEntityEgg("rooster_orpington");

        ItemHandler.entityeggchickred = new ItemEntityEgg("chick_red");
        ItemHandler.entityegghenred = new ItemEntityEgg("hen_red");
        ItemHandler.entityeggroosterred = new ItemEntityEgg("rooster_red");

        ItemHandler.entityeggchickwyandotte = new ItemEntityEgg("chick_wyandotte");
        ItemHandler.entityegghenwyandotte = new ItemEntityEgg("hen_wyandotte");
        ItemHandler.entityeggroosterwyandotte = new ItemEntityEgg("rooster_wyandotte");

        ItemHandler.entityeggrandomchicken = new ItemEntityEgg("chicken_random");

        // PEAFOWL
        ItemHandler.entityeggpeachickblue = new ItemEntityEgg("peachick_blue");
        ItemHandler.entityeggpeafowlblue = new ItemEntityEgg("peafowl_blue");
        ItemHandler.entityeggpeacockblue = new ItemEntityEgg("peacock_blue");

        ItemHandler.entityeggpeachickwhite = new ItemEntityEgg("peachick_white");
        ItemHandler.entityeggpeafowlwhite = new ItemEntityEgg("peafowl_white");
        ItemHandler.entityeggpeacockwhite = new ItemEntityEgg("peacock_white");

        // PIGS
        ItemHandler.entityeggpigletyorkshire = new ItemEntityEgg("piglet_yorkshire");
        ItemHandler.entityeggsowyorkshire = new ItemEntityEgg("sow_yorkshire");
        ItemHandler.entityegghogyorkshire = new ItemEntityEgg("hog_yorkshire");

        ItemHandler.entityeggpigletoldspot = new ItemEntityEgg("piglet_oldspot");
        ItemHandler.entityeggsowoldspot = new ItemEntityEgg("sow_oldspot");
        ItemHandler.entityegghogoldspot = new ItemEntityEgg("hog_oldspot");

        ItemHandler.entityeggpigletlargeblack = new ItemEntityEgg("piglet_largeblack");
        ItemHandler.entityeggsowlargeblack = new ItemEntityEgg("sow_largeblack");
        ItemHandler.entityegghoglargeblack = new ItemEntityEgg("hog_largeblack");

        ItemHandler.entityeggpigletlargewhite = new ItemEntityEgg("piglet_largewhite");
        ItemHandler.entityeggsowlargewhite = new ItemEntityEgg("sow_largewhite");
        ItemHandler.entityegghoglargewhite = new ItemEntityEgg("hog_largewhite");

        ItemHandler.entityeggpigletduroc = new ItemEntityEgg("piglet_duroc");
        ItemHandler.entityeggsowduroc = new ItemEntityEgg("sow_duroc");
        ItemHandler.entityegghogduroc = new ItemEntityEgg("hog_duroc");

        ItemHandler.entityeggpiglethampshire = new ItemEntityEgg("piglet_hampshire");
        ItemHandler.entityeggsowhampshire = new ItemEntityEgg("sow_hampshire");
        ItemHandler.entityegghoghampshire = new ItemEntityEgg("hog_hampshire");

        ItemHandler.entityeggrandompig = new ItemEntityEgg("pig_random");

        // RODENTS
        ItemHandler.entityegghamster = new ItemEntityEgg("hamster");
        ItemHandler.entityeggferretgrey = new ItemEntityEgg("ferret_grey");
        ItemHandler.entityeggferretwhite = new ItemEntityEgg("ferret_white");
        ItemHandler.entityegghedgehog = new ItemEntityEgg("hedgehog");
        ItemHandler.entityegghedgehogalbino = new ItemEntityEgg("hedgehog_albino");

        // AMPHIBIANS
        ItemHandler.entityeggfrog = new ItemEntityEgg("frog");
        ItemHandler.entityeggtoad = new ItemEntityEgg("toad");
        ItemHandler.entityeggdartfrog = new ItemEntityEgg("dart_frog");

        // HORSES
        entityeggdrafthorsestallion = new ItemEntityEgg("draft_horse_stallion");
        entityeggdrafthorsemare = new ItemEntityEgg("draft_horse_mare");
        entityeggdrafthorsefoal = new ItemEntityEgg("draft_horse_foal");

    }
}
