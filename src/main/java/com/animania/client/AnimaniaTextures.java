package com.animania.client;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class AnimaniaTextures {

	public static void registerTextures() { 


		//Items
		register(ItemHandler.hamsterFood);
		register(ItemHandler.truffle);
		register(ItemHandler.brownEgg);
		//register(ItemHandler.bucketSlop);
		register(ItemHandler.carvingKnife);
		register(ItemHandler.cheeseMold);
		register(ItemHandler.cheeseWheelFriesian);
		register(ItemHandler.cheeseWedgeFriesian);
		register(ItemHandler.cheeseWheelHolstein);
		register(ItemHandler.cheeseWedgeHolstein);
		register(ItemHandler.truffleSoup);
		register(ItemHandler.chocolateTruffle);
		register(ItemHandler.plainOmelette);
		register(ItemHandler.cheeseOmelette);
		register(ItemHandler.baconOmelette);
		register(ItemHandler.truffleOmelette);
		register(ItemHandler.ultimateOmelette);
		register(ItemHandler.milkBucketFriesian);
		register(ItemHandler.milkBucketHolstein);
		register(ItemHandler.peacockFeatherBlue);
		register(ItemHandler.peacockFeatherWhite);


		//Beef
		register(ItemHandler.rawAngusBeef);
		register(ItemHandler.rawHerefordBeef);
		register(ItemHandler.rawLonghornBeef);

		register(ItemHandler.cookedAngusRoast);
		register(ItemHandler.cookedHerefordRoast);
		register(ItemHandler.cookedLonghornRoast);

		register(ItemHandler.rawAngusSteak);
		register(ItemHandler.rawHerefordSteak);
		register(ItemHandler.rawLonghornSteak);

		register(ItemHandler.cookedAngusSteak);
		register(ItemHandler.cookedHerefordSteak);
		register(ItemHandler.cookedLonghornSteak);

		//Beef Generics
		register(ItemHandler.rawPrimeBeef);
		register(ItemHandler.cookedPrimeBeef);
		register(ItemHandler.rawPrimeSteak);
		register(ItemHandler.cookedPrimeSteak);


		//Pork
		register(ItemHandler.rawLargeBlackPork);
		register(ItemHandler.rawDurocPork);
		register(ItemHandler.rawOldSpotPork);
		register(ItemHandler.rawHampshirePork);

		register(ItemHandler.rawLargeBlackBacon);
		register(ItemHandler.rawDurocBacon);
		register(ItemHandler.rawOldSpotBacon);
		register(ItemHandler.rawHampshireBacon);

		register(ItemHandler.cookedLargeBlackRoast);
		register(ItemHandler.cookedDurocRoast);
		register(ItemHandler.cookedOldSpotRoast);
		register(ItemHandler.cookedHampshireRoast);

		register(ItemHandler.cookedLargeBlackBacon);
		register(ItemHandler.cookedDurocBacon);
		register(ItemHandler.cookedOldSpotBacon);
		register(ItemHandler.cookedHampshireBacon);

		//Pork Generics
		register(ItemHandler.rawPrimePork);
		register(ItemHandler.cookedPrimePork);
		register(ItemHandler.rawPrimeBacon);
		register(ItemHandler.cookedPrimeBacon);


		//Chicken
		register(ItemHandler.rawOrpingtonChicken);
		register(ItemHandler.rawPlymouthRockChicken);
		register(ItemHandler.rawWyandotteChicken);
		register(ItemHandler.rawRhodeIslandRedChicken);

		register(ItemHandler.cookedOrpingtonChicken);
		register(ItemHandler.cookedPlymouthRockChicken);
		register(ItemHandler.cookedWyandotteChicken);
		register(ItemHandler.cookedRhodeIslandRedChicken);

		//Chicken Generics
		register(ItemHandler.rawPrimeChicken);
		register(ItemHandler.cookedPrimeChicken);


		//Cows
		register(ItemHandler.entityeggbullholstein);
		register(ItemHandler.entityeggcowholstein);
		register(ItemHandler.entityeggcalfholstein);

		register(ItemHandler.entityeggbullfriesian);
		register(ItemHandler.entityeggcowfriesian);
		register(ItemHandler.entityeggcalffriesian);

		register(ItemHandler.entityeggbullangus);
		register(ItemHandler.entityeggcowangus);
		register(ItemHandler.entityeggcalfangus);

		register(ItemHandler.entityeggbulllonghorn);
		register(ItemHandler.entityeggcowlonghorn);
		register(ItemHandler.entityeggcalflonghorn);

		register(ItemHandler.entityeggbullhereford);
		register(ItemHandler.entityeggcowhereford);
		register(ItemHandler.entityeggcalfhereford);

		register(ItemHandler.entityeggrandomcow);

		//Chickens
		register(ItemHandler.entityeggchickplymouth);
		register(ItemHandler.entityegghenplymouth);
		register(ItemHandler.entityeggroosterplymouth);

		register(ItemHandler.entityeggchickleghorn);
		register(ItemHandler.entityegghenleghorn);
		register(ItemHandler.entityeggroosterleghorn);

		register(ItemHandler.entityeggchickred);
		register(ItemHandler.entityegghenred);
		register(ItemHandler.entityeggroosterred);

		register(ItemHandler.entityeggchickorpington);
		register(ItemHandler.entityegghenorpington);
		register(ItemHandler.entityeggroosterorpington);

		register(ItemHandler.entityeggchickwyandotte);
		register(ItemHandler.entityegghenwyandotte);
		register(ItemHandler.entityeggroosterwyandotte);

		register(ItemHandler.entityeggrandomchicken);

		//Peacocks
		register(ItemHandler.entityeggpeacockblue);
		register(ItemHandler.entityeggpeafowlblue);
		register(ItemHandler.entityeggpeachickblue);

		register(ItemHandler.entityeggpeacockwhite);
		register(ItemHandler.entityeggpeafowlwhite);
		register(ItemHandler.entityeggpeachickwhite);

		//Pigs
		register(ItemHandler.entityeggsowyorkshire);
		register(ItemHandler.entityegghogyorkshire);
		register(ItemHandler.entityeggpigletyorkshire);

		register(ItemHandler.entityeggsowoldspot);
		register(ItemHandler.entityegghogoldspot);
		register(ItemHandler.entityeggpigletoldspot);

		register(ItemHandler.entityeggsowlargeblack);
		register(ItemHandler.entityegghoglargeblack);
		register(ItemHandler.entityeggpigletlargeblack);

		register(ItemHandler.entityeggsowlargewhite);
		register(ItemHandler.entityegghoglargewhite);
		register(ItemHandler.entityeggpigletlargewhite);

		register(ItemHandler.entityeggsowduroc);
		register(ItemHandler.entityegghogduroc);
		register(ItemHandler.entityeggpigletduroc);

		register(ItemHandler.entityeggsowhampshire);
		register(ItemHandler.entityegghoghampshire);
		register(ItemHandler.entityeggpiglethampshire);

		register(ItemHandler.entityeggrandompig);

		//Rodents
		register(ItemHandler.entityegghamster);
		register(ItemHandler.entityeggferretgrey);
		register(ItemHandler.entityeggferretwhite);

		register(ItemHandler.entityegghedgehog);
		register(ItemHandler.entityegghedgehogalbino);

		//Blocks
		register(Item.getItemFromBlock(BlockHandler.blockMud));
		register(Item.getItemFromBlock(BlockHandler.blockTrough));
		register(Item.getItemFromBlock(BlockHandler.blockNest));

		
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockSlop, "slop");
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockMilkFriesian, "milk_friesian");
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockMilkHolstein, "milk_holstein");

	}
	
	
	/**
	 * Registers Render for an Item
	 * @param item
	 */
	public static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));		
	}

	
}