package common.handler;

import com.animania.api.data.EntityGender;
import com.animania.common.items.AnimaniaItem;
import com.animania.common.items.ItemAnimaniaFood;
import com.animania.common.items.ItemAnimaniaFoodRaw;
import com.animania.common.items.ItemEntityEgg;
import common.entity.amphibians.AmphibianType;
import common.entity.peafowl.PeacockType;
import common.entity.rodents.rabbits.RabbitType;
import common.item.ItemHamsterBall;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

public class ExtraAddonItemHandler
{

	public static Item hamsterBallClear;
	public static Item hamsterBallColored;
	public static Item hamsterFood;
	public static Item peacockFeatherBlue;
	public static Item peacockFeatherWhite;
	public static Item peacockFeatherCharcoal;
	public static Item peacockFeatherOpal;
	public static Item peacockFeatherPeach;
	public static Item peacockFeatherPurple;
	public static Item peacockFeatherTaupe;

	public static Item peacockEggBlue;
	public static Item peacockEggWhite;

	// Frogs
	public static Item rawFrogLegs;
	public static Item cookedFrogLegs;

	// Peacock
	public static Item rawPeacock;
	public static Item cookedPeacock;
	public static Item rawPrimePeacock;
	public static Item cookedPrimePeacock;

	// Rabbit
	public static Item rawPrimeRabbit;
	public static Item cookedPrimeRabbit;

	public static Item entityeggrandompeacock;
	public static Item entityeggrandomrabbit;
	public static Item entityeggdartfrog;

	/**
	 * Register Items
	 */
	public static void preInit()
	{
		// ITEMS
		peacockFeatherBlue = new AnimaniaItem("blue_peacock_feather");
		peacockFeatherWhite = new AnimaniaItem("white_peacock_feather");
		peacockFeatherCharcoal = new AnimaniaItem("charcoal_peacock_feather");
		peacockFeatherOpal = new AnimaniaItem("opal_peacock_feather");
		peacockFeatherPeach = new AnimaniaItem("peach_peacock_feather");
		peacockFeatherPurple = new AnimaniaItem("purple_peacock_feather");
		peacockFeatherTaupe = new AnimaniaItem("taupe_peacock_feather");

		// Items for Animals
		hamsterFood = new AnimaniaItem("hamster_food");

		hamsterBallClear = new ItemHamsterBall(false, "hamster_ball_clear");
		hamsterBallColored = new ItemHamsterBall(true, "hamster_ball_colored");

		peacockEggBlue = new AnimaniaItem("peacock_egg_blue").setMaxStackSize(16);
		peacockEggWhite = new AnimaniaItem("peacock_egg_white").setMaxStackSize(16);

		// RABBIT ITEMS
		rawPrimeRabbit = new ItemAnimaniaFoodRaw("raw_prime_rabbit");
		cookedPrimeRabbit = new ItemAnimaniaFood(8, 0.5f, "cooked_prime_rabbit", new MobEffectInstance(MobEffects.JUMP_BOOST, 600, 3, false, false));

		// FROG ITEMS
		rawFrogLegs = new ItemAnimaniaFoodRaw("raw_frog_legs");
		cookedFrogLegs = new ItemAnimaniaFood(7, 0.5f, "cooked_frog_legs", new MobEffectInstance(MobEffects.JUMP_BOOST, 1200, 2, false, false));

		// PEACOCK ITEMS
		rawPeacock = new ItemAnimaniaFoodRaw("raw_peacock");
		cookedPeacock = new ItemAnimaniaFood(6, 0.5f, "cooked_peacock", new MobEffectInstance(MobEffects.LUCK, 600, 0, false, false));
		rawPrimePeacock = new ItemAnimaniaFoodRaw("raw_prime_peacock");
		cookedPrimePeacock = new ItemAnimaniaFood(9, 0.5f, "cooked_prime_peacock", new MobEffectInstance(MobEffects.LUCK, 1200, 1, false, false));

		entityeggrandompeacock = new ItemEntityEgg("peacock_random", PeacockType.BLUE, EntityGender.RANDOM);
		entityeggrandomrabbit = new ItemEntityEgg("rabbit_random", RabbitType.LOP, EntityGender.RANDOM);
		entityeggdartfrog = new ItemEntityEgg("dart_frog", AmphibianType.DART_FROG, EntityGender.NONE);

	}

}
