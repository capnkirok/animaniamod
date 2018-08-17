package com.animania.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.ISpawnable;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.entities.amphibians.AmphibianType;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.rodents.rabbits.RabbitType;
import com.animania.common.entities.sheep.SheepType;
import com.animania.common.items.AnimaniaItem;
import com.animania.common.items.ItemAnimaniaFood;
import com.animania.common.items.ItemAnimaniaFoodRaw;
import com.animania.common.items.ItemBrownEgg;
import com.animania.common.items.ItemCart;
import com.animania.common.items.ItemCarvingKnife;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemEntityEggAnimated;
import com.animania.common.items.ItemHamsterBall;
import com.animania.common.items.ItemJar;
import com.animania.common.items.ItemMilkBottle;
import com.animania.common.items.ItemRidingCrop;
import com.animania.common.items.ItemTiller;
import com.animania.common.items.ItemTruffleSoup;
import com.animania.common.items.ItemWagon;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHandler
{
	public static boolean hasSetEggColors = false;

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
	public static Item salt;
	public static Item peacockFeatherBlue;
	public static Item peacockFeatherWhite;
	public static Item peacockFeatherCharcoal;
	public static Item peacockFeatherOpal;
	public static Item peacockFeatherPeach;
	public static Item peacockFeatherPurple;
	public static Item peacockFeatherTaupe;
	public static Item ridingCrop;
	public static Item wheel;
	public static Item milkBottle;
	public static Item honeyJar;

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

	// Horse
	public static Item rawHorse;
	public static Item cookedHorse;

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

	// Peacock
	public static Item rawPeacock;
	public static Item cookedPeacock;
	public static Item rawPrimePeacock;
	public static Item cookedPrimePeacock;

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
	public static Item cheeseWheelJersey;
	public static Item cheeseWedgeJersey;
	public static Item cheeseWheelGoat;
	public static Item cheeseWedgeGoat;
	public static Item cheeseWheelSheep;
	public static Item cheeseWedgeSheep;
	public static Item truffleSoup;
	public static Item chocolateTruffle;

	// Eggs
	public static Item entityeggrandomcow;
	public static Item entityeggrandomchicken;
	public static Item entityeggrandompeacock;
	public static Item entityeggrandompig;
	public static Item entityeggrandomgoat;
	public static Item entityeggrandomsheep;
	public static Item entityeggrandomrabbit;
	public static Item entityeggrandomanimal;
	public static Item entityeggdartfrog;

	// Moving Objects
	public static Item cart;
	public static Item wagon;
	public static Item tiller;

	public static List<Item> entityEggList = new ArrayList<Item>();
	
	public static void preInit()
	{
		// ITEMS

		// Moving Items

		if (!AnimaniaConfig.gameRules.disableRollingVehicles) {
			cart = new ItemCart();
			wagon = new ItemWagon();
			tiller = new ItemTiller();
		}

		// Items for Animals
		ItemHandler.hamsterFood = new AnimaniaItem("hamster_food");
		ItemHandler.truffle = new AnimaniaItem("truffle");
		ItemHandler.carvingKnife = new ItemCarvingKnife(ToolMaterial.IRON);
		ItemHandler.salt = new AnimaniaItem("salt");
		ItemHandler.peacockFeatherBlue = new AnimaniaItem("blue_peacock_feather");
		ItemHandler.peacockFeatherWhite = new AnimaniaItem("white_peacock_feather");
		ItemHandler.peacockFeatherCharcoal = new AnimaniaItem("charcoal_peacock_feather");
		ItemHandler.peacockFeatherOpal = new AnimaniaItem("opal_peacock_feather");
		ItemHandler.peacockFeatherPeach = new AnimaniaItem("peach_peacock_feather");
		ItemHandler.peacockFeatherPurple = new AnimaniaItem("purple_peacock_feather");
		ItemHandler.peacockFeatherTaupe = new AnimaniaItem("taupe_peacock_feather");
		ItemHandler.ridingCrop = new ItemRidingCrop();
		ItemHandler.hamsterBallClear = new ItemHamsterBall(false, "hamster_ball_clear");
		ItemHandler.hamsterBallColored = new ItemHamsterBall(true, "hamster_ball_colored");
		ItemHandler.wheel = new AnimaniaItem("wheel");
		ItemHandler.milkBottle = new ItemMilkBottle();

		// Other foods
		ItemHandler.ultimateOmelette = new ItemAnimaniaFood(5, 2f, "super_omelette", new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false), new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false), new PotionEffect(MobEffects.RESISTANCE, 600, 1, false, false));
		ItemHandler.truffleOmelette = new ItemAnimaniaFood(5, 2f, "truffle_omelette", new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false));
		ItemHandler.baconOmelette = new ItemAnimaniaFood(5, 2f, "bacon_omelette", new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));
		ItemHandler.cheeseOmelette = new ItemAnimaniaFood(5, 2f, "cheese_omelette", new PotionEffect(MobEffects.INSTANT_HEALTH, 2, 2, false, false));
		ItemHandler.plainOmelette = new ItemAnimaniaFood(5, 2f, "plain_omelette");
		ItemHandler.truffleSoup = new ItemTruffleSoup();
		ItemHandler.chocolateTruffle = new ItemAnimaniaFood(6, 2f, "chocolate_truffle", true, new PotionEffect(MobEffects.SPEED, 1200, 3, false, false));
		ItemHandler.honeyJar = new ItemJar();

		// ITEMS produced by Animals
		// COW ITEMS
		ItemHandler.rawHerefordBeef = new ItemAnimaniaFoodRaw("raw_hereford_beef", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawLonghornBeef = new ItemAnimaniaFoodRaw("raw_longhorn_beef", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawAngusBeef = new ItemAnimaniaFoodRaw("raw_angus_beef", AnimaniaConfig.drops.oldMeatDrops);

		ItemHandler.rawLonghornSteak = new ItemAnimaniaFoodRaw("raw_longhorn_steak", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawHerefordSteak = new ItemAnimaniaFoodRaw("raw_hereford_steak", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawAngusSteak = new ItemAnimaniaFoodRaw("raw_angus_steak", AnimaniaConfig.drops.oldMeatDrops);

		ItemHandler.cookedLonghornRoast = new ItemAnimaniaFood(10, 1f, "cooked_longhorn_roast", true, new PotionEffect(MobEffects.INSTANT_HEALTH, 6, 1, false, false));
		ItemHandler.cookedHerefordRoast = new ItemAnimaniaFood(12, 1f, "cooked_hereford_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 4, 1, false, false));
		ItemHandler.cookedAngusRoast = new ItemAnimaniaFood(20, 1f, "cooked_angus_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 1, false, false));

		ItemHandler.cookedLonghornSteak = new ItemAnimaniaFood(5, 1f, "cooked_longhorn_steak", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 3, 1, false, false));
		ItemHandler.cookedHerefordSteak = new ItemAnimaniaFood(6, 1f, "cooked_hereford_steak", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 2, 1, false, false));
		ItemHandler.cookedAngusSteak = new ItemAnimaniaFood(10, 1f, "cooked_angus_steak", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 5, 1, false, false));

		ItemHandler.rawPrimeBeef = new ItemAnimaniaFoodRaw("raw_prime_beef", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimeBeef = new ItemAnimaniaFood(20, 1f, "cooked_prime_beef", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 1, false, false));
		ItemHandler.rawPrimeSteak = new ItemAnimaniaFoodRaw("raw_prime_steak", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimeSteak = new ItemAnimaniaFood(10, 1f, "cooked_prime_steak", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.INSTANT_HEALTH, 5, 1, false, false));

		// PIG ITEMS
		ItemHandler.rawLargeBlackPork = new ItemAnimaniaFoodRaw("raw_large_black_pork", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawDurocPork = new ItemAnimaniaFoodRaw("raw_duroc_pork", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawOldSpotPork = new ItemAnimaniaFoodRaw("raw_old_spot_pork", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawHampshirePork = new ItemAnimaniaFoodRaw("raw_hampshire_pork", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedLargeBlackRoast = new ItemAnimaniaFood(16, 1f, "cooked_large_black_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 2, false, false));
		ItemHandler.cookedDurocRoast = new ItemAnimaniaFood(12, 1f, "cooked_duroc_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 1, false, false));
		ItemHandler.cookedOldSpotRoast = new ItemAnimaniaFood(10, 1f, "cooked_old_spot_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 1, false, false));
		ItemHandler.cookedHampshireRoast = new ItemAnimaniaFood(8, 1f, "cooked_hampshire_roast", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 1, false, false));
		ItemHandler.cookedLargeBlackBacon = new ItemAnimaniaFood(8, 1f, "cooked_large_black_bacon", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 2, false, false));
		ItemHandler.cookedDurocBacon = new ItemAnimaniaFood(6, 1f, "cooked_duroc_bacon", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 0, false, false));
		ItemHandler.cookedOldSpotBacon = new ItemAnimaniaFood(5, 1f, "cooked_old_spot_bacon", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 0, false, false));
		ItemHandler.cookedHampshireBacon = new ItemAnimaniaFood(4, 1f, "cooked_hampshire_bacon", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1200, 0, false, false));
		ItemHandler.rawPrimePork = new ItemAnimaniaFoodRaw("raw_prime_pork", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimePork = new ItemAnimaniaFood(12, 1f, "cooked_prime_pork", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 3000, 0, false, false));

		ItemHandler.rawPrimeBacon = new ItemAnimaniaFoodRaw("raw_prime_bacon", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimeBacon = new ItemAnimaniaFood(12, 1f, "cooked_prime_bacon", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.ABSORPTION, 1800, 0, false, false));

		// CHICKEN ITEMS
		ItemHandler.rawOrpingtonChicken = new ItemAnimaniaFoodRaw("raw_orpington_chicken", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawPlymouthRockChicken = new ItemAnimaniaFoodRaw("raw_plymouth_rock_chicken", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawWyandotteChicken = new ItemAnimaniaFoodRaw("raw_wyandotte_chicken", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawRhodeIslandRedChicken = new ItemAnimaniaFoodRaw("raw_rhode_island_red_chicken", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedOrpingtonChicken = new ItemAnimaniaFood(12, 1f, "cooked_orpington_chicken", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 3000, 0, false, false));
		ItemHandler.cookedPlymouthRockChicken = new ItemAnimaniaFood(10, 1f, "cooked_plymouth_rock_chicken", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 2400, 0, false, false));
		ItemHandler.cookedWyandotteChicken = new ItemAnimaniaFood(6, 1f, "cooked_wyandotte_chicken", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 1800, 0, false, false));
		ItemHandler.cookedRhodeIslandRedChicken = new ItemAnimaniaFood(8, 1f, "cooked_rhode_island_red_chicken", AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 1200, 0, false, false));
		ItemHandler.rawPrimeChicken = new ItemAnimaniaFoodRaw("raw_prime_chicken", !AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.cookedPrimeChicken = new ItemAnimaniaFood(12, 1f, "cooked_prime_chicken", !AnimaniaConfig.drops.oldMeatDrops, new PotionEffect(MobEffects.HASTE, 3000, 0, false, false));
		ItemHandler.brownEgg = new ItemBrownEgg();
		ItemHandler.peacockEggBlue = new AnimaniaItem("peacock_egg_blue").setMaxStackSize(16);
		ItemHandler.peacockEggWhite = new AnimaniaItem("peacock_egg_white").setMaxStackSize(16);
		ItemHandler.rawLargeBlackBacon = new ItemAnimaniaFoodRaw("raw_large_black_bacon", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawDurocBacon = new ItemAnimaniaFoodRaw("raw_duroc_bacon", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawOldSpotBacon = new ItemAnimaniaFoodRaw("raw_old_spot_bacon", AnimaniaConfig.drops.oldMeatDrops);
		ItemHandler.rawHampshireBacon = new ItemAnimaniaFoodRaw("raw_hampshire_bacon", AnimaniaConfig.drops.oldMeatDrops);

		// SHEEP ITEMS
		ItemHandler.rawMutton = new ItemAnimaniaFoodRaw("raw_prime_mutton");
		ItemHandler.cookedMutton = new ItemAnimaniaFood(3, 1f, "cooked_prime_mutton", new PotionEffect(MobEffects.INSTANT_HEALTH, 5, 1, false, false));

		// RABBIT ITEMS
		ItemHandler.rawRabbit = new ItemAnimaniaFoodRaw("raw_prime_rabbit");
		ItemHandler.cookedRabbit = new ItemAnimaniaFood(4, 1f, "cooked_prime_rabbit", new PotionEffect(MobEffects.JUMP_BOOST, 600, 3, false, false));

		// FROG ITEMS
		ItemHandler.rawFrogLegs = new ItemAnimaniaFoodRaw("raw_frog_legs");
		ItemHandler.cookedFrogLegs = new ItemAnimaniaFood(3, 1f, "cooked_frog_legs", new PotionEffect(MobEffects.JUMP_BOOST, 1200, 2, false, false));

		// HORSE ITEMS
		ItemHandler.rawHorse = new ItemAnimaniaFoodRaw("raw_horse");
		ItemHandler.cookedHorse= new ItemAnimaniaFood(20, 1f, "cooked_horse", new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));

		// GOAT ITEMS
		ItemHandler.rawChevon = new ItemAnimaniaFoodRaw("raw_chevon");
		ItemHandler.cookedChevon = new ItemAnimaniaFood(3, 1f, "cooked_chevon", new PotionEffect(MobEffects.RESISTANCE, 600, 0, false, false));
		ItemHandler.rawPrimeChevon = new ItemAnimaniaFoodRaw("raw_prime_chevon");
		ItemHandler.cookedPrimeChevon = new ItemAnimaniaFood(3, 1f, "cooked_prime_chevon", new PotionEffect(MobEffects.RESISTANCE, 1200, 1, false, false));

		// PEACOCK ITEMS
		ItemHandler.rawPeacock = new ItemAnimaniaFoodRaw("raw_peacock");
		ItemHandler.cookedPeacock = new ItemAnimaniaFood(3, 1f, "cooked_peacock", new PotionEffect(MobEffects.LUCK, 600, 0, false, false));
		ItemHandler.rawPrimePeacock = new ItemAnimaniaFoodRaw("raw_prime_peacock");
		ItemHandler.cookedPrimePeacock = new ItemAnimaniaFood(3, 1f, "cooked_prime_peacock", new PotionEffect(MobEffects.LUCK, 1200, 1, false, false));

		// CHEESE
		ItemHandler.cheeseWedgeFriesian = new ItemAnimaniaFood(2, 2f, "friesian_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 6, 2, false, false));
		ItemHandler.cheeseWedgeHolstein = new ItemAnimaniaFood(2, 2f, "holstein_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 12, 2, false, false));
		ItemHandler.cheeseWedgeJersey = new ItemAnimaniaFood(2, 2f, "jersey_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 2, false, false));
		ItemHandler.cheeseWedgeGoat = new ItemAnimaniaFood(2, 2f, "goat_cheese_wedge", new PotionEffect(MobEffects.RESISTANCE, 1200, 0, false, false));
		ItemHandler.cheeseWedgeSheep = new ItemAnimaniaFood(2, 2f, "sheep_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 0, false, false));

		// Item Entity Eggs
		ItemHandler.entityeggrandomanimal = new ItemEntityEgg("random", new RandomAnimalType(), EntityGender.RANDOM);
		ItemHandler.entityeggrandomcow = new ItemEntityEgg("cow_random", CowType.ANGUS, EntityGender.RANDOM);
		ItemHandler.entityeggrandompig = new ItemEntityEgg("pig_random", PigType.DUROC, EntityGender.RANDOM);
		ItemHandler.entityeggrandomchicken = new ItemEntityEgg("chicken_random", ChickenType.LEGHORN, EntityGender.RANDOM);
		ItemHandler.entityeggrandompeacock = new ItemEntityEgg("peacock_random", PeacockType.BLUE, EntityGender.RANDOM);
		ItemHandler.entityeggdartfrog = new ItemEntityEgg("dart_frog", AmphibianType.DART_FROG, EntityGender.NONE);
		ItemHandler.entityeggrandomgoat = new ItemEntityEgg("goat_random", GoatType.ALPINE, EntityGender.RANDOM);
		ItemHandler.entityeggrandomsheep = new ItemEntityEgg("sheep_random", SheepType.FRIESIAN, EntityGender.RANDOM);
		ItemHandler.entityeggrandomrabbit = new ItemEntityEgg("rabbit_random", RabbitType.LOP, EntityGender.RANDOM);

	}
	
	
	@SideOnly(Side.CLIENT)
	public static void regItemEggColors()
	{
		if (!hasSetEggColors)
		{
			for (Item item : entityEggList)
			{
				if (item instanceof ItemEntityEgg && !(item instanceof ItemEntityEggAnimated))
				{
					World world = Minecraft.getMinecraft().world;
					if (item != ItemHandler.entityeggrandomanimal)
					{
						AnimalContainer animal = ((ItemEntityEgg) item).getAnimal();
						EntityLivingBase entity = EntityGender.getEntity(animal.getType(), animal.getGender(), world);

						if (animal.getGender() != EntityGender.RANDOM)
						{
							if (entity != null)
							{
								if (((ISpawnable) entity).usesEggColor())
								{
									ItemEntityEgg.ANIMAL_USES_COLOR.put(animal, true);
									ItemEntityEgg.ANIMAL_COLOR_PRIMARY.put(animal, ((ISpawnable) entity).getPrimaryEggColor());
									ItemEntityEgg.ANIMAL_COLOR_SECONDARY.put(animal, ((ISpawnable) entity).getSecondaryEggColor());
								}
								else
									ItemEntityEgg.ANIMAL_USES_COLOR.put(animal, false);

							}
						}
					}
					
				}
			}
			hasSetEggColors = true;
		}
	}

}
