package com.animania.config;

public class CommonConfig {

	public static class Entity {

		// COWS
		public int CowHolsteinID = 500;
		public int BullHolsteinID = 501;
		public int CalfHolsteinID = 502;
		public int CowFriesianID = 503;
		public int BullFriesianID = 504;
		public int CalfFriesianID = 505;
		public int CowAngusID = 506;
		public int BullAngusID = 507;
		public int CalfAngusID = 508;

		public int CowLonghornID = 509;
		public int BullLonghornID = 510;
		public int CalfLonghornID = 511;
		public int CowHerefordID = 512;
		public int BullHerefordID = 513;
		public int CalfHerefordID = 514;

		// RODENTS
		public int HamsterMaleID = 520;
		public int FerretMaleGreyID = 523;
		public int FerretMaleWhiteID = 526;
		public int HedgehogMaleID = 529;
		public int HedgehogMaleAlbinoID = 550;

		// CHICKENS
		public int HenPlymouthRockID = 530;
		public int RoosterPlymouthRockID = 531;
		public int ChickPlymouthRockID = 532;
		public int HenLeghornID = 533;
		public int RoosterLeghornID = 534;
		public int ChickLeghornID = 535;
		public int HenOrpingtonID = 536;
		public int RoosterOrpingtonID = 537;
		public int ChickOrpingtonID = 538;
		public int HenRhodeIslandRedID = 539;
		public int RoosterRhodeIslandRedID = 540;
		public int ChickRhodeIslandRedID = 541;
		public int HenWyandotteID = 542;
		public int RoosterWyandotteID = 543;
		public int ChickWyandotteID = 544;

		// PEAFOWL
		public int PeacockBlueID = 580;
		public int PeafowlBlueID = 581;
		public int PeachickBlueID = 582;
		public int PeacockWhiteID = 583;
		public int PeafowlWhiteID = 584;
		public int PeachickWhiteID = 585;

		// PIGS
		public int SowYorkshireID = 600;
		public int HogYorkshireID = 601;
		public int PigletYorkshireID = 602;

		public int SowOldSpotID = 603;
		public int HogOldSpotID = 604;
		public int PigletOldSpotID = 605;

		public int SowLargeBlackID = 606;
		public int HogLargeBlackID = 607;
		public int PigletLargeBlackID = 608;

		public int SowLargeWhiteID = 609;
		public int HogLargeWhiteID = 610;
		public int PigletLargeWhiteID = 611;

		public int SowDurocID = 612;
		public int HogDurocID = 613;
		public int PigletDurocID = 614;

		public int SowHampshireID = 615;
		public int HogHampshireID = 616;
		public int PigletHampshireID = 617;

		// HORSES
		// public int MareDraftHorseID = 701;
		// public int StallionDraftHorseID = 702;
		// public int FoalDraftHorseID = 703;

		public boolean spawnAnimaniaChickens = true;
		public boolean spawnAnimaniaCows = true;
		public boolean spawnAnimaniaPigs = true;
		public boolean spawnAnimaniaRodents = true;
		public boolean spawnAnimaniaPeacocks = true;
		public boolean spawnAnimaniaHorses = true;

		public int spawnLimitCows = 36;
		public int spawnLimitPigs = 32;
		public int spawnLimitChickens = 32;
		public int spawnLimitHedgehogs = 50;
		public int spawnLimitFerrets = 50;
		public int spawnLimitHamsters = 50;
		public int spawnLimitPeacocks = 50;

		public int spawnProbabilityCows = 12;
		public int spawnProbabilitySows = 12;
		public int spawnProbabilityHens = 16;
		public int spawnProbabilityHedgehogs = 10;
		public int spawnProbabilityFerrets = 10;
		public int spawnProbabilityHamsters = 12;
		public int spawnProbabilityPeacocks = 20;

		public int numberCowFamilies = 2;
		public int numberPigFamilies = 1;
		public int numberChickenFamilies = 1;

		public boolean customMobDrops = true;
		public String chickenDrop = "animania:raw_prime_chicken";
		public String pigDrop = "animania:raw_prime_pork";
		public String cowDrop = "animania:raw_prime_beef";
		public String horseDrop = "";
		public String peacockBlueDrop = "animania:blue_peacock_feather";
		public String peacockWhiteDrop = "animania:white_peacock_feather";
		public String ferretDrop = "";
		public String hamsterDrop = "animania:hamster_food";
		public String hedgehogDrop = "";

		public int childGrowthTick = 200;
		public int feedTimer = 12000;
		public int waterTimer = 8000;
		public int playTimer = 4000;
		public int laidTimer = 2000;
		public int gestationTimer = 20000;
		public int starvationTimer = 400;
		public int eggHatchChance = 2;
	}

	public static class GameRules {

		public boolean foodsGiveBonusEffects = true;
		public boolean showModUpdateNotification = true;
		public boolean showParts = true;
		public boolean showUnhappyParticles = true;
		public boolean enableVanillaMeatRecipes = false;
		public boolean replaceVanillaCows = true;
		public boolean replaceVanillaPigs = true;
		public boolean replaceVanillaChickens = true;
		public boolean allowEggThrowing = false;
		public boolean shiftSeedPlacement = false;
		public boolean animalsStarve = false;
	}
}
