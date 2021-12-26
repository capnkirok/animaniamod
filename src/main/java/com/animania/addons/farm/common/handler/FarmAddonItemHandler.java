package com.animania.addons.farm.common.handler;

import static com.animania.common.handler.ItemHandler.ITEMS;

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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class FarmAddonItemHandler
{

	// RegistryObject<Item>s
	public static RegistryObject<Item> truffle;
	public static RegistryObject<Item> carvingKnife;
	public static RegistryObject<Item> cheeseMold;
	public static RegistryObject<Item> salt;
	public static RegistryObject<Item> ridingCrop;
	public static RegistryObject<Item> milkBottle;
	public static RegistryObject<Item> honeyJar;
	public static RegistryObject<Item> brownEgg;

	// Beef
	public static RegistryObject<Item> rawPrimeSteak;
	public static RegistryObject<Item> rawPrimeBeef;
	public static RegistryObject<Item> cookedPrimeSteak;
	public static RegistryObject<Item> cookedPrimeBeef;

	// Horse
	public static RegistryObject<Item> rawHorse;
	public static RegistryObject<Item> cookedHorse;

	// Pork
	public static RegistryObject<Item> rawPrimePork;
	public static RegistryObject<Item> rawPrimeBacon;
	public static RegistryObject<Item> cookedPrimePork;
	public static RegistryObject<Item> cookedPrimeBacon;

	// Chicken
	public static RegistryObject<Item> rawPrimeChicken;
	public static RegistryObject<Item> cookedPrimeChicken;

	// Goats
	public static RegistryObject<Item> rawChevon;
	public static RegistryObject<Item> cookedChevon;
	public static RegistryObject<Item> rawPrimeChevon;
	public static RegistryObject<Item> cookedPrimeChevon;

	// Sheep
	public static RegistryObject<Item> rawPrimeMutton;
	public static RegistryObject<Item> cookedPrimeMutton;

	// Other Foods
	public static RegistryObject<Item> plainOmelette;
	public static RegistryObject<Item> cheeseOmelette;
	public static RegistryObject<Item> baconOmelette;
	public static RegistryObject<Item> truffleOmelette;
	public static RegistryObject<Item> ultimateOmelette;
	public static RegistryObject<Item> cheeseWheelFriesian;
	public static RegistryObject<Item> cheeseWedgeFriesian;
	public static RegistryObject<Item> cheeseWheelHolstein;
	public static RegistryObject<Item> cheeseWedgeHolstein;
	public static RegistryObject<Item> cheeseWheelJersey;
	public static RegistryObject<Item> cheeseWedgeJersey;
	public static RegistryObject<Item> cheeseWheelGoat;
	public static RegistryObject<Item> cheeseWedgeGoat;
	public static RegistryObject<Item> cheeseWheelSheep;
	public static RegistryObject<Item> cheeseWedgeSheep;
	public static RegistryObject<Item> truffleSoup;
	public static RegistryObject<Item> chocolateTruffle;

	// Eggs
	public static RegistryObject<Item> entityeggrandomcow;
	public static RegistryObject<Item> entityeggrandomchicken;
	public static RegistryObject<Item> entityeggrandompig;
	public static RegistryObject<Item> entityeggrandomgoat;
	public static RegistryObject<Item> entityeggrandomsheep;

	// Moving Objects
	public static RegistryObject<Item> cart;
	public static RegistryObject<Item> wagon;
	public static RegistryObject<Item> tiller;
	public static RegistryObject<Item> wheel;

	/**
	 * Register Items
	 */
	public static void preInit()
	{
		// Moving Items

		if (!FarmConfig.settings.disableRollingVehicles)
		{
			cart = ITEMS.register("TODO", () -> new ItemCart());
			wagon = ITEMS.register("TODO", () -> new ItemWagon());
			tiller = ITEMS.register("TODO", () -> new ItemTiller());
		}

		carvingKnife = ITEMS.register("TODO", () -> new ItemCarvingKnife(ToolMaterial.IRON));
		salt = ITEMS.register("TODO", () -> new AnimaniaItem("salt"));

		ridingCrop = ITEMS.register("TODO", () -> new ItemRidingCrop());
		milkBottle = ITEMS.register("TODO", () -> new ItemMilkBottle());
		brownEgg = ITEMS.register("TODO", () -> new ItemBrownEgg());
		wheel = ITEMS.register("TODO", () -> new AnimaniaItem("wheel"));

		// Other foods
		ultimateOmelette = ITEMS.register("super_omelette", () -> new ItemAnimaniaFood(5, 0.9f, "super_omelette", new MobEffectInstance(MobEffects.REGENERATION, 600, 1, false, false), new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0, false, false), new MobEffectInstance(MobEffects.RESISTANCE, 600, 1, false, false)));
		truffleOmelette = ITEMS.register("truffle_omelette", () -> new ItemAnimaniaFood(5, 0.8f, "truffle_omelette", new MobEffectInstance(MobEffects.REGENERATION, 600, 1, false, false)));
		baconOmelette = ITEMS.register("bacon_omelette", () -> new ItemAnimaniaFood(5, 0.7f, "bacon_omelette", new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0, false, false)));
		cheeseOmelette = ITEMS.register("cheese_omelette", () -> new ItemAnimaniaFood(5, 0.7f, "cheese_omelette", new MobEffectInstance(MobEffects.HEAL, 1, 1, false, false)));
		plainOmelette = ITEMS.register("plain_omelette", () -> new ItemAnimaniaFood(5, 0.6f, "plain_omelette"));
		truffle = ITEMS.register("truffle", () -> new ItemAnimaniaFood(2, 0.7f, "truffle"));
		truffleSoup = ITEMS.register("truffle_soup", () -> new ItemTruffleSoup());
		chocolateTruffle = ITEMS.register("chocolate_truffle", () -> new ItemAnimaniaFood(6, 0.7f, "chocolate_truffle", true, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 3, false, false)));

		honeyJar = ITEMS.register("honey_bottle", () -> new ItemHoneyBottle());

		// ITEMS produced by Animals
		// COW ITEMS
		rawPrimeBeef = ITEMS.register("raw_prime_beef", () -> new ItemAnimaniaFoodRaw("raw_prime_beef", true));
		cookedPrimeBeef = ITEMS.register("cooked_prime_beef", () -> new ItemAnimaniaFood(12, 0.5f, "cooked_prime_beef", true, new MobEffectInstance(MobEffects.HEAL, 1, 0, false, false)));
		rawPrimeSteak = ITEMS.register("raw_prime_steak", () -> new ItemAnimaniaFoodRaw("raw_prime_steak", true));
		cookedPrimeSteak = ITEMS.register("cooked_prime_steak", () -> new ItemAnimaniaFood(8, 0.5f, "cooked_prime_steak", true, new MobEffectInstance(MobEffects.HEAL, 1, 0, false, false)));

		// PIG ITEMS
		rawPrimePork = ITEMS.register("raw_prime_pork", () -> new ItemAnimaniaFoodRaw("raw_prime_pork", true));
		cookedPrimePork = ITEMS.register("cooked_prime_pork", () -> new ItemAnimaniaFood(12, 0.5f, "cooked_prime_pork", true, new MobEffectInstance(MobEffects.ABSORPTION, 3000, 0, false, false)));

		rawPrimeBacon = ITEMS.register("raw_prime_bacon", () -> new ItemAnimaniaFoodRaw("raw_prime_bacon", true));
		cookedPrimeBacon = ITEMS.register("cooked_prime_bacon", () -> new ItemAnimaniaFood(8, 0.5f, "cooked_prime_bacon", true, new MobEffectInstance(MobEffects.ABSORPTION, 1800, 0, false, false)));

		// CHICKEN ITEMS
		rawPrimeChicken = ITEMS.register("raw_prime_chicken", () -> new ItemAnimaniaFoodRaw("raw_prime_chicken", true));
		cookedPrimeChicken = ITEMS.register("cooked_prime_chicken", () -> new ItemAnimaniaFood(8, 0.5f, "cooked_prime_chicken", true, new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0, false, false)));

		// SHEEP ITEMS
		rawPrimeMutton = ITEMS.register("raw_prime_mutton", () -> new ItemAnimaniaFoodRaw("raw_prime_mutton"));
		cookedPrimeMutton = ITEMS.register("cooked_prime_mutton", () -> new ItemAnimaniaFood(12, 0.5f, "cooked_prime_mutton", new MobEffectInstance(MobEffects.HEAL, 1, 0, false, false)));

		// HORSE ITEMS
		rawHorse = ITEMS.register("raw_horse", () -> new ItemAnimaniaFoodRaw("raw_horse"));
		cookedHorse = ITEMS.register("cooked_horse", () -> new ItemAnimaniaFood(12, 0.5f, "cooked_horse", new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0, false, false)));

		// GOAT ITEMS
		rawChevon = ITEMS.register("raw_chevon", () -> new ItemAnimaniaFoodRaw("raw_chevon"));
		cookedChevon = ITEMS.register("cooked_chevon", () -> new ItemAnimaniaFood(5, 0.5f, "cooked_chevon", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0, false, false)));
		rawPrimeChevon = ITEMS.register("raw_prime_chevon", () -> new ItemAnimaniaFoodRaw("raw_prime_chevon"));
		cookedPrimeChevon = ITEMS.register("cooked_prime_chevon", () -> new ItemAnimaniaFood(10, 0.5f, "cooked_prime_chevon", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1, false, false)));

		// CHEESE
		cheeseWedgeFriesian = ITEMS.register("friesian_cheese_wedge", () -> new ItemAnimaniaFood(3, 0.9f, "friesian_cheese_wedge", new MobEffectInstance(MobEffects.HEAL, 1, 1, false, false)));
		cheeseWedgeHolstein = ITEMS.register("holstein_cheese_wedge", () -> new ItemAnimaniaFood(3, 0.9f, "holstein_cheese_wedge", new MobEffectInstance(MobEffects.HEAL, 1, 1, false, false)));
		cheeseWedgeJersey = ITEMS.register("jersey_cheese_wedge", () -> new ItemAnimaniaFood(3, 0.9f, "jersey_cheese_wedge", new MobEffectInstance(MobEffects.HEAL, 1, 1, false, false)));
		cheeseWedgeGoat = ITEMS.register("goat_cheese_wedge", () -> new ItemAnimaniaFood(3, 0.9f, "goat_cheese_wedge", new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0, false, false)));
		cheeseWedgeSheep = ITEMS.register("sheep_cheese_wedge", () -> new ItemAnimaniaFood(3, 0.9f, "sheep_cheese_wedge", new MobEffectInstance(MobEffects.HEAL, 1, 0, false, false)));

		entityeggrandomcow = ITEMS.register("entity_egg_cow_random", () -> new ItemEntityEgg("cow_random", CowType.ANGUS, EntityGender.RANDOM));
		entityeggrandompig = ITEMS.register("entity_egg_pig_random", () -> new ItemEntityEgg("pig_random", PigType.DUROC, EntityGender.RANDOM));
		entityeggrandomchicken = ITEMS.register("entity_egg_chicken_random", () -> new ItemEntityEgg("chicken_random", ChickenType.LEGHORN, EntityGender.RANDOM));
		entityeggrandomgoat = ITEMS.register("entity_egg_goat_random", () -> new ItemEntityEgg("goat_random", GoatType.ALPINE, EntityGender.RANDOM));
		entityeggrandomsheep = ITEMS.register("entity_egg_sheep_random", () -> new ItemEntityEgg("sheep_random", SheepType.FRIESIAN, EntityGender.RANDOM));
	}

}
