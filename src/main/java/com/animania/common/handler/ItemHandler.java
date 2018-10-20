package com.animania.common.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.entities.amphibians.AmphibianType;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.interfaces.ISpawnable;
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
import com.animania.common.items.ItemHamsterBall;
import com.animania.common.items.ItemHoneyBottle;
import com.animania.common.items.ItemManual;
import com.animania.common.items.ItemMilkBottle;
import com.animania.common.items.ItemRidingCrop;
import com.animania.common.items.ItemTiller;
import com.animania.common.items.ItemTruffleSoup;
import com.animania.common.items.ItemWagon;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

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
	public static Item animaniaManual;

	// Beef
	public static Item rawPrimeSteak;
	public static Item rawPrimeBeef;
	public static Item cookedPrimeSteak;
	public static Item cookedPrimeBeef;

	// Horse
	public static Item rawHorse;
	public static Item cookedHorse;

	// Pork
	public static Item rawPrimePork;
	public static Item rawPrimeBacon;
	public static Item cookedPrimePork;
	public static Item cookedPrimeBacon;

	// Chicken
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
	public static Item rawPrimeMutton;
	public static Item cookedPrimeMutton;

	// Rabbit
	public static Item rawPrimeRabbit;
	public static Item cookedPrimeRabbit;

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
		ItemHandler.animaniaManual = new ItemManual();

		// Other foods
		ItemHandler.ultimateOmelette = new ItemAnimaniaFood(5, 2f, "super_omelette", new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false), new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false), new PotionEffect(MobEffects.RESISTANCE, 600, 1, false, false));
		ItemHandler.truffleOmelette = new ItemAnimaniaFood(5, 2f, "truffle_omelette", new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false));
		ItemHandler.baconOmelette = new ItemAnimaniaFood(5, 2f, "bacon_omelette", new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));
		ItemHandler.cheeseOmelette = new ItemAnimaniaFood(5, 2f, "cheese_omelette", new PotionEffect(MobEffects.INSTANT_HEALTH, 2, 2, false, false));
		ItemHandler.plainOmelette = new ItemAnimaniaFood(5, 2f, "plain_omelette");
		ItemHandler.truffle = new ItemAnimaniaFood(2, 2f, "truffle");
		ItemHandler.truffleSoup = new ItemTruffleSoup();
		ItemHandler.chocolateTruffle = new ItemAnimaniaFood(6, 2f, "chocolate_truffle", true, new PotionEffect(MobEffects.SPEED, 1200, 3, false, false));
		
		ItemHandler.honeyJar = new ItemHoneyBottle();

		// ITEMS produced by Animals
		// COW ITEMS
		ItemHandler.rawPrimeBeef = new ItemAnimaniaFoodRaw("raw_prime_beef", true);
		ItemHandler.cookedPrimeBeef = new ItemAnimaniaFood(20, 1f, "cooked_prime_beef", true, new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 1, false, false));
		ItemHandler.rawPrimeSteak = new ItemAnimaniaFoodRaw("raw_prime_steak", true);
		ItemHandler.cookedPrimeSteak = new ItemAnimaniaFood(10, 1f, "cooked_prime_steak", true, new PotionEffect(MobEffects.INSTANT_HEALTH, 5, 1, false, false));

		// PIG ITEMS
		ItemHandler.rawPrimePork = new ItemAnimaniaFoodRaw("raw_prime_pork", true);
		ItemHandler.cookedPrimePork = new ItemAnimaniaFood(12, 1f, "cooked_prime_pork", true, new PotionEffect(MobEffects.ABSORPTION, 3000, 0, false, false));

		ItemHandler.rawPrimeBacon = new ItemAnimaniaFoodRaw("raw_prime_bacon", true);
		ItemHandler.cookedPrimeBacon = new ItemAnimaniaFood(12, 1f, "cooked_prime_bacon", true, new PotionEffect(MobEffects.ABSORPTION, 1800, 0, false, false));

		// CHICKEN ITEMS
		ItemHandler.rawPrimeChicken = new ItemAnimaniaFoodRaw("raw_prime_chicken", true);
		ItemHandler.cookedPrimeChicken = new ItemAnimaniaFood(12, 1f, "cooked_prime_chicken", true, new PotionEffect(MobEffects.HASTE, 3000, 0, false, false));
		ItemHandler.brownEgg = new ItemBrownEgg();
		ItemHandler.peacockEggBlue = new AnimaniaItem("peacock_egg_blue").setMaxStackSize(16);
		ItemHandler.peacockEggWhite = new AnimaniaItem("peacock_egg_white").setMaxStackSize(16);

		// SHEEP ITEMS
		ItemHandler.rawPrimeMutton = new ItemAnimaniaFoodRaw("raw_prime_mutton");
		ItemHandler.cookedPrimeMutton = new ItemAnimaniaFood(3, 1f, "cooked_prime_mutton", new PotionEffect(MobEffects.INSTANT_HEALTH, 5, 1, false, false));

		// RABBIT ITEMS
		ItemHandler.rawPrimeRabbit = new ItemAnimaniaFoodRaw("raw_prime_rabbit");
		ItemHandler.cookedPrimeRabbit = new ItemAnimaniaFood(4, 1f, "cooked_prime_rabbit", new PotionEffect(MobEffects.JUMP_BOOST, 600, 3, false, false));

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
	
	
	public static void regItemEggColors(World world)
	{
		if (!hasSetEggColors)
		{
			for (Item item : entityEggList)
			{
				if (item instanceof ItemEntityEgg)
				{
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
									
									EntityList.ENTITY_EGGS.put(EntityEggHandler.getEntryFromEntity(entity).getRegistryName(), new EntityEggInfo(EntityEggHandler.getEntryFromEntity(entity).getRegistryName(), ((ISpawnable) entity).getPrimaryEggColor(), ((ISpawnable) entity).getSecondaryEggColor()));
								}
								else
									ItemEntityEgg.ANIMAL_USES_COLOR.put(animal, false);

							}
						}
					}
					
				}
			}
			
			if(Loader.isModLoaded("thermalexpansion"))
			{
				try
				{
					Class itemMorb = Class.forName("cofh.thermalexpansion.item.ItemMorb");
					Method parseMorbs = ReflectionHelper.findMethod(itemMorb, "parseMorbs", null);
					parseMorbs.invoke(itemMorb);
				}
				catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
				{
				}
			}
			
			hasSetEggColors = true;
		}
	}

}
