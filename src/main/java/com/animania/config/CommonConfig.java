package com.animania.config;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

public class CommonConfig
{

	public static class GameRules
	{
		@Comment("Foods give bonus effects")
		public boolean foodsGiveBonusEffects = true;

		@Comment("Show mod update notification at startup")
		public boolean showModUpdateNotification = true;

		@Comment("Show male parts (modesty flag)")
		public boolean showParts = false;

		@Comment("Show particles when animals are hungry or thirsty")
		public boolean showUnhappyParticles = true;

		@Comment("Allow dispensers to place seeds")
		public boolean allowSeedDispenserPlacement = true;

		@Comment("Shift-Right-Click for Seed Placement")
		public boolean shiftSeedPlacement = false;

		@Comment("Animals starve to death when not fed and watered")
		public boolean animalsStarve = false;

		@Comment("Allow random mobs to spawn on Animania animals")
		public boolean allowMobRiding = true;

		@Comment("Allow the trough to be automated with hoppers/pipes")
		public boolean allowTroughAutomation = true;

		@Comment("Multiplier for reducing fall damage when animals are leashed")
		public float fallDamageReduceMultiplier = 0.45f;

		@Comment("Water blocks removed after large animals drink")
		public boolean waterRemovedAfterDrinking = true;

		@Comment("Plant blocks removed after animal eats")
		public boolean plantsRemovedAfterEating = true;

		@Comment("Animals do not have to eat or drink")
		public boolean ambianceMode = false;

		@Comment("Animals sleep")
		public boolean animalsSleep = true;

		@Comment("Animals can attack others")
		public boolean animalsCanAttackOthers = true;

		@Comment("Food Items that can be placed in the trough. Animal food items must be listed on here. (use # for meta)")
		public String[] troughFood = { "minecraft:wheat", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem", "minecraft:apple", "minecraft:carrot", "minecraft:beetroot", "minecraft:potato", "minecraft:poisonous_potato", "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "biomesoplenty:turnip_seeds", "minecraft:egg", "animania:brown_egg", "listAllbeefraw", "minecraft:fish" };

		@Comment("AI Tick Countdown Timer (increase for higher performance)")
		public int ticksBetweenAIFirings = 100;

		@Comment("Tamed animals teleport to player)")
		public boolean tamedAnimalsTeleport = true;

		@RequiresMcRestart
		@Comment("Fancy entity spawn eggs")
		public boolean fancyEggs = false;

		@Comment("If fancy entity eggs rotate or not")
		public boolean fancyEggsRotate = false;

		@RequiresMcRestart
		@Comment("Eat animania food anytime")
		public boolean eatFoodAnytime = true;

		@Comment("If chickens and other birds shed their feathers naturally")
		public boolean birdsDropFeathers = true;

		@Comment("Range that Animals search blocks (like troughs or food blocks) in")
		public int aiBlockSearchRange = 16;

		@Comment("Range that the animal cap applies for around each animal")
		public int animalCapSearchRange = 80;

		@Comment("Animals that are spawned in the world and have never been interacted with (put on a leash, fed by hand, trough, lured with food) will not eat or breed.")
		public boolean requireAnimalInteractionForAI = true;

		@Comment("Allow Squids to Spawn in Fresh Water")
		public boolean spawnFreshWaterSquids = true;

	}

	public static class CareAndFeeding
	{
		@Comment("Ticks before next incremental growth")
		public int childGrowthTick = 200;
		@Comment("Ticks between feedings")
		public int feedTimer = 12000;
		@Comment("Ticks between drinking water")
		public int waterTimer = 12000;
		@Comment("Ticks between playing")
		public int playTimer = 12000;
		@Comment("Ticks between laying eggs for chickens and peafowl")
		public int laidTimer = 2000;
		@Comment("Ticks between dropping feathers for chickens and peafowl")
		public int featherTimer = 12000;
		@Comment("Ticks between birthings")
		public int gestationTimer = 20000;
		@Comment("Mammals mate only after hand-feeding")
		public boolean feedToBreed = true;
		@Comment("Ticks before wool regrowth after shearing")
		public int woolRegrowthTimer = 8000;
		@Comment("Ticks between animals taking starvation damage")
		public int starvationTimer = 400;
		@Comment("Egg hatch chance (1/x)")
		public int eggHatchChance = 2;
		@Comment("Ticks between using Salt Lick")
		public int saltLickTick = 8000;
		@Comment("Maximum uses of the salt lick")
		public int saltLickMaxUses = 200;

		@Comment("Ingredients used to make slop (use # for meta)")
		public String[] slopIngredients = { "minecraft:carrot", "minecraft:beetroot", "minecraft:potato", "minecraft:poisonous_potato", "minecraft:bread" };

		@Comment("Animals won't breed if there are more than the specified amount of animals of their type in a 30 block range.")
		public int entityBreedingLimit = 15;

		@Comment("If male animals can have multiple mates.")
		public boolean malesMateMultipleFemales = false;

		@Comment("Chance that multiple animals will be born. Gets multiplied with itself after every child. Set to 0 to disable.")
		public double birthMultipleChance = 0.1;

		@Comment("Chance that a female animal will lose its baby, if it is hungry and thirsty. Set to 0 to disable")
		public double animalLossChance = 0.0;
	}

	public static class FoodValues
	{
		@Comment("Food Value Overrides. Format: modid:name(hunger,saturationMultiplier) Example: animania:cheese_omelette(5,0.8)")
		public String[] foodValueOverrides = new String[] {};
	}

}
