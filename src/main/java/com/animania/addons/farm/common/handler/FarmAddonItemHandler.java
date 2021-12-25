package com.animania.addons.farm.common.handler;

import com.animania.addons.farm.common.entity.chickens.ChickenType;
import com.animania.addons.farm.common.entity.cows.CowType;
import com.animania.addons.farm.common.entity.goats.GoatType;
import com.animania.addons.farm.common.entity.pigs.PigType;
import com.animania.addons.farm.common.entity.sheep.SheepType;
import com.animania.addons.farm.common.item.ItemBrownEgg;
import com.animania.addons.farm.common.item.ItemCart;
import com.animania.addons.farm.common.item.ItemCarvingKnife;
import com.animania.addons.farm.common.item.ItemHoneyBottle;
import com.animania.addons.farm.common.item.ItemMilkBottle;
import com.animania.addons.farm.common.item.ItemRidingCrop;
import com.animania.addons.farm.common.item.ItemTiller;
import com.animania.addons.farm.common.item.ItemTruffleSoup;
import com.animania.addons.farm.common.item.ItemWagon;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.EntityGender;
import com.animania.common.items.AnimaniaItem;
import com.animania.common.items.ItemAnimaniaFood;
import com.animania.common.items.ItemAnimaniaFoodRaw;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.effect.MobEffects;

public class FarmAddonItemHandler
{

	// Items
	public static RItem truffle;
	public static RItem carvingKnife;
	public static RItem cheeseMold;
	public static RItem salt;
	public static RItem ridingCrop;
	public static RItem milkBottle;
	public static RItem honeyJar;
	public static RItem brownEgg;

	// Beef
	public static RItem rawPrimeSteak;
	public static RItem rawPrimeBeef;
	public static RItem cookedPrimeSteak;
	public static RItem cookedPrimeBeef;

	// Horse
	public static RItem rawHorse;
	public static RItem cookedHorse;

	// Pork
	public static RItem rawPrimePork;
	public static RItem rawPrimeBacon;
	public static RItem cookedPrimePork;
	public static RItem cookedPrimeBacon;

	// Chicken
	public static RItem rawPrimeChicken;
	public static RItem cookedPrimeChicken;

	// Goats
	public static RItem rawChevon;
	public static RItem cookedChevon;
	public static RItem rawPrimeChevon;
	public static RItem cookedPrimeChevon;

	// Sheep
	public static RItem rawPrimeMutton;
	public static RItem cookedPrimeMutton;

	// Other Foods
	public static RItem plainOmelette;
	public static RItem cheeseOmelette;
	public static RItem baconOmelette;
	public static RItem truffleOmelette;
	public static RItem ultimateOmelette;
	public static RItem cheeseWheelFriesian;
	public static RItem cheeseWedgeFriesian;
	public static RItem cheeseWheelHolstein;
	public static RItem cheeseWedgeHolstein;
	public static RItem cheeseWheelJersey;
	public static RItem cheeseWedgeJersey;
	public static RItem cheeseWheelGoat;
	public static RItem cheeseWedgeGoat;
	public static RItem cheeseWheelSheep;
	public static RItem cheeseWedgeSheep;
	public static RItem truffleSoup;
	public static RItem chocolateTruffle;

	// Eggs
	public static RItem entityeggrandomcow;
	public static RItem entityeggrandomchicken;
	public static RItem entityeggrandompig;
	public static RItem entityeggrandomgoat;
	public static RItem entityeggrandomsheep;

	// Moving Objects
	public static RItem cart;
	public static RItem wagon;
	public static RItem tiller;
	public static RItem wheel;

	/**
	 * Register Items
	 */
	public static void preInit()
	{
		// Moving Items

		if (!FarmConfig.settings.disableRollingVehicles)
		{
			cart = new ItemCart();
			wagon = new ItemWagon();
			tiller = new ItemTiller();
		}

		carvingKnife = new ItemCarvingKnife(ToolMaterial.IRON);
		salt = new AnimaniaItem("salt");

		ridingCrop = new ItemRidingCrop();
		milkBottle = new ItemMilkBottle();
		brownEgg = new ItemBrownEgg();
		wheel = new AnimaniaItem("wheel");

		// Other foods
		ultimateOmelette = new ItemAnimaniaFood(5, 0.9f, "super_omelette", new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false), new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false), new PotionEffect(MobEffects.RESISTANCE, 600, 1, false, false));
		truffleOmelette = new ItemAnimaniaFood(5, 0.8f, "truffle_omelette", new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false));
		baconOmelette = new ItemAnimaniaFood(5, 0.7f, "bacon_omelette", new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));
		cheeseOmelette = new ItemAnimaniaFood(5, 0.7f, "cheese_omelette", new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1, false, false));
		plainOmelette = new ItemAnimaniaFood(5, 0.6f, "plain_omelette");
		truffle = new ItemAnimaniaFood(2, 0.7f, "truffle");
		truffleSoup = new ItemTruffleSoup();
		chocolateTruffle = new ItemAnimaniaFood(6, 0.7f, "chocolate_truffle", true, new PotionEffect(MobEffects.SPEED, 1200, 3, false, false));

		honeyJar = new ItemHoneyBottle();

		// ITEMS produced by Animals
		// COW ITEMS
		rawPrimeBeef = new ItemAnimaniaFoodRaw("raw_prime_beef", true);
		cookedPrimeBeef = new ItemAnimaniaFood(12, 0.5f, "cooked_prime_beef", true, new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0, false, false));
		rawPrimeSteak = new ItemAnimaniaFoodRaw("raw_prime_steak", true);
		cookedPrimeSteak = new ItemAnimaniaFood(8, 0.5f, "cooked_prime_steak", true, new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0, false, false));

		// PIG ITEMS
		rawPrimePork = new ItemAnimaniaFoodRaw("raw_prime_pork", true);
		cookedPrimePork = new ItemAnimaniaFood(12, 0.5f, "cooked_prime_pork", true, new PotionEffect(MobEffects.ABSORPTION, 3000, 0, false, false));

		rawPrimeBacon = new ItemAnimaniaFoodRaw("raw_prime_bacon", true);
		cookedPrimeBacon = new ItemAnimaniaFood(8, 0.5f, "cooked_prime_bacon", true, new PotionEffect(MobEffects.ABSORPTION, 1800, 0, false, false));

		// CHICKEN ITEMS
		rawPrimeChicken = new ItemAnimaniaFoodRaw("raw_prime_chicken", true);
		cookedPrimeChicken = new ItemAnimaniaFood(8, 0.5f, "cooked_prime_chicken", true, new PotionEffect(MobEffects.HASTE, 3000, 0, false, false));

		// SHEEP ITEMS
		rawPrimeMutton = new ItemAnimaniaFoodRaw("raw_prime_mutton");
		cookedPrimeMutton = new ItemAnimaniaFood(12, 0.5f, "cooked_prime_mutton", new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0, false, false));

		// HORSE ITEMS
		rawHorse = new ItemAnimaniaFoodRaw("raw_horse");
		cookedHorse = new ItemAnimaniaFood(12, 0.5f, "cooked_horse", new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));

		// GOAT ITEMS
		rawChevon = new ItemAnimaniaFoodRaw("raw_chevon");
		cookedChevon = new ItemAnimaniaFood(5, 0.5f, "cooked_chevon", new PotionEffect(MobEffects.RESISTANCE, 600, 0, false, false));
		rawPrimeChevon = new ItemAnimaniaFoodRaw("raw_prime_chevon");
		cookedPrimeChevon = new ItemAnimaniaFood(10, 0.5f, "cooked_prime_chevon", new PotionEffect(MobEffects.RESISTANCE, 1200, 1, false, false));

		// CHEESE
		cheeseWedgeFriesian = new ItemAnimaniaFood(3, 0.9f, "friesian_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1, false, false));
		cheeseWedgeHolstein = new ItemAnimaniaFood(3, 0.9f, "holstein_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1, false, false));
		cheeseWedgeJersey = new ItemAnimaniaFood(3, 0.9f, "jersey_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1, false, false));
		cheeseWedgeGoat = new ItemAnimaniaFood(3, 0.9f, "goat_cheese_wedge", new PotionEffect(MobEffects.RESISTANCE, 1200, 0, false, false));
		cheeseWedgeSheep = new ItemAnimaniaFood(3, 0.9f, "sheep_cheese_wedge", new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0, false, false));

		entityeggrandomcow = new ItemEntityEgg("cow_random", CowType.ANGUS, EntityGender.RANDOM);
		entityeggrandompig = new ItemEntityEgg("pig_random", PigType.DUROC, EntityGender.RANDOM);
		entityeggrandomchicken = new ItemEntityEgg("chicken_random", ChickenType.LEGHORN, EntityGender.RANDOM);
		entityeggrandomgoat = new ItemEntityEgg("goat_random", GoatType.ALPINE, EntityGender.RANDOM);
		entityeggrandomsheep = new ItemEntityEgg("sheep_random", SheepType.FRIESIAN, EntityGender.RANDOM);
	}

}
