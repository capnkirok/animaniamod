package com.animania.config;

import net.minecraftforge.common.config.Config.Comment;

public class CommonConfig
{

    public static class Entity
    {

        // COWS
        @Comment("Holstein Cow Entity ID")
        public int CowHolsteinID           = 500;

        @Comment("Holstein Bull Entity ID")
        public int BullHolsteinID          = 501;

        @Comment("Holstein Calf Entity ID")
        public int CalfHolsteinID          = 502;

        @Comment("Fresian Cow Entity ID")
        public int CowFriesianID           = 503;

        @Comment("Fresian Bull Entity ID")
        public int BullFriesianID          = 504;

        @Comment("Fresian Calf Entity ID")
        public int CalfFriesianID          = 505;

        @Comment("Angus Cow Entity ID")
        public int CowAngusID              = 506;

        @Comment("Angus Bull Entity ID")
        public int BullAngusID             = 507;

        @Comment("Angus Calf Entity ID")
        public int CalfAngusID             = 508;

        @Comment("Longhorn Cow Entity ID")
        public int CowLonghornID           = 509;

        @Comment("Longhorn Bull Entity ID")
        public int BullLonghornID          = 510;

        @Comment("Longhorn Calf Entity ID")
        public int CalfLonghornID          = 511;

        @Comment("Hereford Cow Entity ID")
        public int CowHerefordID           = 512;

        @Comment("Hereford Bull Entity ID")
        public int BullHerefordID          = 513;

        @Comment("Hereford Calf Entity ID")
        public int CalfHerefordID          = 514;

        // RODENTS
        @Comment("Hamster Male Entity ID")
        public int HamsterMaleID           = 520;

        @Comment("Grey Ferret Male Entity ID")
        public int FerretMaleGreyID        = 523;

        @Comment("White Ferret Male Entity ID")
        public int FerretMaleWhiteID       = 526;

        @Comment("Hedgehog Male Entity ID")
        public int HedgehogMaleID          = 529;

        @Comment("Albino Hedgehog Male Entity ID")
        public int HedgehogMaleAlbinoID    = 550;

        // CHICKENS

        @Comment("Hen Plymouth Rock Entity ID")
        public int HenPlymouthRockID       = 530;

        @Comment("Rooster Plymouth Rock Entity ID")
        public int RoosterPlymouthRockID   = 531;

        @Comment("Chick Plymouth Rock Entity ID")
        public int ChickPlymouthRockID     = 532;

        @Comment("Hen Leghorn Entity ID")
        public int HenLeghornID            = 533;

        @Comment("Rooster Leghorn Entity ID")
        public int RoosterLeghornID        = 534;

        @Comment("Chick Leghorn Entity ID")
        public int ChickLeghornID          = 535;

        @Comment("Hen Orpington Entity ID")
        public int HenOrpingtonID          = 536;

        @Comment("Rooster Orpington Entity ID")
        public int RoosterOrpingtonID      = 537;

        @Comment("Chick Orpington Entity ID")
        public int ChickOrpingtonID        = 538;

        @Comment("Hen Rode Island Red Entity ID")
        public int HenRhodeIslandRedID     = 539;

        @Comment("Rooster Rode Island Red Entity ID")
        public int RoosterRhodeIslandRedID = 540;

        @Comment("Chick Rode Island Red Entity ID")
        public int ChickRhodeIslandRedID   = 541;

        @Comment("Hen Wyandotte Entity ID")
        public int HenWyandotteID          = 542;

        @Comment("Rooster Wyandotte Entity ID")
        public int RoosterWyandotteID      = 543;

        @Comment("Chick Wyandotte Entity ID")
        public int ChickWyandotteID        = 544;

        // PEAFOWL

        @Comment("Indian Blue Peacock Entity ID")
        public int PeacockBlueID           = 580;

        @Comment("Indian Blue Peafowl Entity ID")
        public int PeafowlBlueID           = 581;

        @Comment("Indian Blue Peachick Entity ID")
        public int PeachickBlueID          = 582;

        @Comment("Indian White Peacock Entity ID")
        public int PeacockWhiteID          = 583;

        @Comment("Indian White Peafowl Entity ID")
        public int PeafowlWhiteID          = 584;

        @Comment("Indian White Peachick Entity ID")
        public int PeachickWhiteID         = 585;

        // PIGS

        @Comment("Sow Yorkshire Entity ID")
        public int SowYorkshireID          = 600;

        @Comment("Hog Yorkshire Entity ID")
        public int HogYorkshireID          = 601;

        @Comment("Piglet Yorkshire Entity ID")
        public int PigletYorkshireID       = 602;

        @Comment("Sow Old Spot Entity ID")
        public int SowOldSpotID            = 603;

        @Comment("Hog Old Spot Entity ID")
        public int HogOldSpotID            = 604;

        @Comment("Piglet Old Spot Entity ID")
        public int PigletOldSpotID         = 605;

        @Comment("Sow Large Black Entity ID")
        public int SowLargeBlackID         = 606;

        @Comment("Hog Large Black Entity ID")
        public int HogLargeBlackID         = 607;

        @Comment("Piglet Large Black Entity ID")
        public int PigletLargeBlackID      = 608;

        @Comment("Sow Large White Entity ID")
        public int SowLargeWhiteID         = 609;

        @Comment("Hog Large Black Entity ID")
        public int HogLargeWhiteID         = 610;

        @Comment("Piglet Large Black Entity ID")
        public int PigletLargeWhiteID      = 611;

        @Comment("Sow Duroc Entity ID")
        public int SowDurocID              = 612;

        @Comment("Hog Duroc Entity ID")
        public int HogDurocID              = 613;

        @Comment("Piglet Duroc Entity ID")
        public int PigletDurocID           = 614;

        @Comment("Sow Hampshire Entity ID")
        public int SowHampshireID          = 615;

        @Comment("Hog Hampshire Entity ID")
        public int HogHampshireID          = 616;

        @Comment("Piglet Hampshire Entity ID")
        public int PigletHampshireID       = 617;

        // HORSES
        // public int MareDraftHorseID = 701;
        // public int StallionDraftHorseID = 702;
        // public int FoalDraftHorseID = 703;

    }

    public static class GameRules
    {

        @Comment("Foods give bonus effects")
        public boolean foodsGiveBonusEffects      = true;

        @Comment("Show mod update notification at startup")
        public boolean showModUpdateNotification  = true;

        @Comment("Show male parts (modesty flag)")
        public boolean showParts                  = true;

        @Comment("Show particles when animals are unhappy")
        public boolean showUnhappyParticles       = true;

        @Comment("Enable recipes to exchange special meats for vanilla")
        public boolean enableVanillaMeatRecipes   = false;

        @Comment("Remove vanilla Cows")
        public boolean replaceVanillaCows         = true;

        @Comment("Remove vanilla Pigs")
        public boolean replaceVanillaPigs         = true;

        @Comment("Remove vanilla Chickens")
        public boolean replaceVanillaChickens     = true;

        @Comment("Allow eggs to be thrown")
        public boolean allowEggThrowing           = false;

        @Comment("Shift-Right-Click for Seed Placement")
        public boolean shiftSeedPlacement         = false;

        @Comment("Animals starve to death when not fed and watered")
        public boolean animalsStarve              = false;

        @Comment("Allow the trough to be automated with hoppers/pipes")
        public boolean allowTroughAutomation      = true;

        @Comment("Multiplier for reducing fall damage when animals are leashed")
        public float   fallDamageReduceMultiplier = 0.45f;
        
        @Comment("Capacity of RF that the Hamster Wheel has")
        public int hamsterWheelCapacity = 200000;
        
        @Comment("RF/tick that the Hamster wheel generates while in use")
        public int hamsterWheelRFGeneration = 20;
        
        @Comment("RF/tick that the Hamster wheel generates while in use")
        public int hamsterWheelUseTime = 2700;
    }

    public static class Spawn
    {
        @Comment("Spawn Animania Chickens in world")
        public boolean spawnAnimaniaChickens      = true;
        @Comment("Spawn Animania Cows in world")
        public boolean spawnAnimaniaCows          = true;
        @Comment("Spawn Animania Pigs in world")
        public boolean spawnAnimaniaPigs          = true;
        @Comment("Spawn Animania Rodents in world")
        public boolean spawnAnimaniaRodents       = true;
        @Comment("Spawn Animania Peacocks in world")
        public boolean spawnAnimaniaPeacocks      = true;
        @Comment("Spawn Animania Horses in world")
        public boolean spawnAnimaniaHorses        = true;
        @Comment("Spawn Animania Amphibians in world")
        public boolean spawnAnimaniaAmphibians    = true;

        @Comment("Spawn limit for Cows in loaded chunks")
        public int     spawnLimitCows             = 40;
        @Comment("Spawn limit for Pigs in loaded chunks")
        public int     spawnLimitPigs             = 40;
        @Comment("Spawn limit for Chickens in loaded chunks")
        public int     spawnLimitChickens         = 40;
        @Comment("Spawn limit for Hedgehogs in loaded chunks")
        public int     spawnLimitHedgehogs        = 50;
        @Comment("Spawn limit for Ferrets in loaded chunks")
        public int     spawnLimitFerrets          = 50;
        @Comment("Spawn limit for Hamsters in loaded chunks")
        public int     spawnLimitHamsters         = 50;
        @Comment("Spawn limit for Peacocks in loaded chunks")
        public int     spawnLimitPeacocks         = 50;
        @Comment("Spawn limit for Amphibians in loaded chunks")
        public int     spawnLimitAmphibians       = 60;

        @Comment("Spawn probability Cows")
        public int     spawnProbabilityCows       = 12;
        @Comment("Spawn probability Sows")
        public int     spawnProbabilitySows       = 12;
        @Comment("Spawn probability Hens")
        public int     spawnProbabilityHens       = 16;
        @Comment("Spawn probability Hedgehogs")
        public int     spawnProbabilityHedgehogs  = 10;
        @Comment("Spawn probability Ferrets")
        public int     spawnProbabilityFerrets    = 10;
        @Comment("Spawn probability Hamsters")
        public int     spawnProbabilityHamsters   = 12;
        @Comment("Spawn probability Peacocks")
        public int     spawnProbabilityPeacocks   = 20;
        @Comment("Spawn probability Amphibians")
        public int     spawnProbabilityAmphibians = 10;

        @Comment("Number of potential Cow families per chunk")
        public int     numberCowFamilies          = 2;
        @Comment("Number of potential Pig families per chunk")
        public int     numberPigFamilies          = 1;
        @Comment("Number of potential Chicken families per chunk")
        public int     numberChickenFamilies      = 1;
    }

    public static class CareAndFeeding
    {
        @Comment("Ticks before next incremental growth")
        public int childGrowthTick = 200;
        @Comment("Ticks between feedings")
        public int feedTimer       = 12000;
        @Comment("Ticks between drinking water")
        public int waterTimer      = 8000;
        @Comment("Ticks between playing")
        public int playTimer       = 4000;
        @Comment("Ticks between laying eggs")
        public int laidTimer       = 2000;
        @Comment("Ticks between birthings")
        public int gestationTimer  = 20000;
        @Comment("Ticks between animals taking starvation damage")
        public int starvationTimer = 400;
        @Comment("Egg hatch chance (1/x)")
        public int eggHatchChance  = 2;
    }

    public static class Drops
    {

        @Comment("Enable Animal Drops from Config")
        public boolean customMobDrops   = true;
        @Comment("Set Custom Chicken Drop (if enabled)")
        public String  chickenDrop      = "animania:raw_prime_chicken";
        @Comment("Set Custom Pig Drop (if enabled)")
        public String  pigDrop          = "animania:raw_prime_pork";
        @Comment("Set Custom Cow Drop (if enabled)")
        public String  cowDrop          = "animania:raw_prime_beef";
        @Comment("Set Custom Horse Drop (if enabled)")
        public String  horseDrop        = "";
        @Comment("Set Custom Blue Peacock Drop (if enabled)")
        public String  peacockBlueDrop  = "animania:blue_peacock_feather";
        @Comment("Set Custom White Peacock Drop (if enabled)")
        public String  peacockWhiteDrop = "animania:white_peacock_feather";
        @Comment("Set Custom Ferret Drop (if enabled)")
        public String  ferretDrop       = "";
        @Comment("Set Custom Hamster Drop (if enabled)")
        public String  hamsterDrop      = "animania:hamster_food";
        @Comment("Set Custom Hedgehog Drop (if enabled)")
        public String  hedgehogDrop     = "";
        @Comment("Set Custom Frog Drop")
        public String  frogDrop         = "animania:raw_frog_legs";
        @Comment("Set Custom Toad Drop")
        public String  toadDrop         = "";
        @Comment("Set Custom Dart Frog Drop")
        public String  dartFrogDrop     = "";
    }
}
