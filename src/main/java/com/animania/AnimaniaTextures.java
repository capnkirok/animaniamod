package com.animania;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import com.animania.blocks.BlockInvisiblock;
import com.animania.blocks.BlockMud;
import com.animania.blocks.BlockNest;
import com.animania.blocks.BlockSeeds;
import com.animania.blocks.BlockTrough;
import com.animania.items.ItemBrownEgg;
import com.animania.items.ItemBucketMilk;
import com.animania.items.ItemBucketSlop;
import com.animania.items.ItemCarvingKnife;
import com.animania.items.ItemCheeseMold;
import com.animania.items.ItemCheeseWedge;
import com.animania.items.ItemCheeseWheel;
import com.animania.items.ItemChocolateTruffle;
import com.animania.items.ItemCookedAngusRoast;
import com.animania.items.ItemCookedAngusSteak;
import com.animania.items.ItemCookedDurocBacon;
import com.animania.items.ItemCookedDurocRoast;
import com.animania.items.ItemCookedHampshireBacon;
import com.animania.items.ItemCookedHampshireRoast;
import com.animania.items.ItemCookedHerefordRoast;
import com.animania.items.ItemCookedHerefordSteak;
import com.animania.items.ItemCookedLargeBlackBacon;
import com.animania.items.ItemCookedLargeBlackRoast;
import com.animania.items.ItemCookedLonghornRoast;
import com.animania.items.ItemCookedLonghornSteak;
import com.animania.items.ItemCookedOldSpotBacon;
import com.animania.items.ItemCookedOldSpotRoast;
import com.animania.items.ItemCookedOrpingtonChicken;
import com.animania.items.ItemCookedPlymouthRockChicken;
import com.animania.items.ItemCookedPrimeBacon;
import com.animania.items.ItemCookedPrimeBeef;
import com.animania.items.ItemCookedPrimeChicken;
import com.animania.items.ItemCookedPrimePork;
import com.animania.items.ItemCookedPrimeSteak;
import com.animania.items.ItemCookedRhodeIslandRedChicken;
import com.animania.items.ItemCookedWyandotteChicken;
import com.animania.items.ItemEntityEgg;
import com.animania.items.ItemHamsterFood;
import com.animania.items.ItemOmelette;
import com.animania.items.ItemPeacockFeather;
import com.animania.items.ItemRawAngusBeef;
import com.animania.items.ItemRawAngusSteak;
import com.animania.items.ItemRawDurocBacon;
import com.animania.items.ItemRawDurocPork;
import com.animania.items.ItemRawHampshireBacon;
import com.animania.items.ItemRawHampshirePork;
import com.animania.items.ItemRawHerefordBeef;
import com.animania.items.ItemRawHerefordSteak;
import com.animania.items.ItemRawLargeBlackBacon;
import com.animania.items.ItemRawLargeBlackPork;
import com.animania.items.ItemRawLonghornBeef;
import com.animania.items.ItemRawLonghornSteak;
import com.animania.items.ItemRawOldSpotBacon;
import com.animania.items.ItemRawOldSpotPork;
import com.animania.items.ItemRawOrpingtonChicken;
import com.animania.items.ItemRawPlymouthRockChicken;
import com.animania.items.ItemRawPrimeBacon;
import com.animania.items.ItemRawPrimeBeef;
import com.animania.items.ItemRawPrimeChicken;
import com.animania.items.ItemRawPrimePork;
import com.animania.items.ItemRawPrimeSteak;
import com.animania.items.ItemRawRhodeIslandRedChicken;
import com.animania.items.ItemRawWyandotteChicken;
import com.animania.items.ItemTruffle;
import com.animania.items.ItemTruffleSoup;

public class AnimaniaTextures {

	public static void registerTextures() { 


		//Items
		register(Animania.hamsterFood);
		register(Animania.truffle);
		register(Animania.brownEgg);
		register(Animania.bucketSlop);
		register(Animania.carvingKnife);
		register(Animania.cheeseMold);
		register(Animania.cheeseWheelFriesian);
		register(Animania.cheeseWedgeFriesian);
		register(Animania.cheeseWheelHolstein);
		register(Animania.cheeseWedgeHolstein);
		register(Animania.truffleSoup);
		register(Animania.chocolateTruffle);
		register(Animania.plainOmelette);
		register(Animania.cheeseOmelette);
		register(Animania.baconOmelette);
		register(Animania.truffleOmelette);
		register(Animania.ultimateOmelette);
		register(Animania.milkBucketFriesian);
		register(Animania.milkBucketHolstein);
		register(Animania.peacockFeatherBlue);
		register(Animania.peacockFeatherWhite);


		//Beef
		register(Animania.rawAngusBeef);
		register(Animania.rawHerefordBeef);
		register(Animania.rawLonghornBeef);

		register(Animania.cookedAngusRoast);
		register(Animania.cookedHerefordRoast);
		register(Animania.cookedLonghornRoast);

		register(Animania.rawAngusSteak);
		register(Animania.rawHerefordSteak);
		register(Animania.rawLonghornSteak);

		register(Animania.cookedAngusSteak);
		register(Animania.cookedHerefordSteak);
		register(Animania.cookedLonghornSteak);

		//Beef Generics
		register(Animania.rawPrimeBeef);
		register(Animania.cookedPrimeBeef);
		register(Animania.rawPrimeSteak);
		register(Animania.cookedPrimeSteak);


		//Pork
		register(Animania.rawLargeBlackPork);
		register(Animania.rawDurocPork);
		register(Animania.rawOldSpotPork);
		register(Animania.rawHampshirePork);

		register(Animania.rawLargeBlackBacon);
		register(Animania.rawDurocBacon);
		register(Animania.rawOldSpotBacon);
		register(Animania.rawHampshireBacon);

		register(Animania.cookedLargeBlackRoast);
		register(Animania.cookedDurocRoast);
		register(Animania.cookedOldSpotRoast);
		register(Animania.cookedHampshireRoast);

		register(Animania.cookedLargeBlackBacon);
		register(Animania.cookedDurocBacon);
		register(Animania.cookedOldSpotBacon);
		register(Animania.cookedHampshireBacon);

		//Pork Generics
		register(Animania.rawPrimePork);
		register(Animania.cookedPrimePork);
		register(Animania.rawPrimeBacon);
		register(Animania.cookedPrimeBacon);


		//Chicken
		register(Animania.rawOrpingtonChicken);
		register(Animania.rawPlymouthRockChicken);
		register(Animania.rawWyandotteChicken);
		register(Animania.rawRhodeIslandRedChicken);

		register(Animania.cookedOrpingtonChicken);
		register(Animania.cookedPlymouthRockChicken);
		register(Animania.cookedWyandotteChicken);
		register(Animania.cookedRhodeIslandRedChicken);

		//Chicken Generics
		register(Animania.rawPrimeChicken);
		register(Animania.cookedPrimeChicken);


		//Cows
		register(Animania.entityeggbullholstein);
		register(Animania.entityeggcowholstein);
		register(Animania.entityeggcalfholstein);

		register(Animania.entityeggbullfriesian);
		register(Animania.entityeggcowfriesian);
		register(Animania.entityeggcalffriesian);

		register(Animania.entityeggbullangus);
		register(Animania.entityeggcowangus);
		register(Animania.entityeggcalfangus);

		register(Animania.entityeggbulllonghorn);
		register(Animania.entityeggcowlonghorn);
		register(Animania.entityeggcalflonghorn);

		register(Animania.entityeggbullhereford);
		register(Animania.entityeggcowhereford);
		register(Animania.entityeggcalfhereford);

		register(Animania.entityeggrandomcow);

		//Chickens
		register(Animania.entityeggchickplymouth);
		register(Animania.entityegghenplymouth);
		register(Animania.entityeggroosterplymouth);

		register(Animania.entityeggchickleghorn);
		register(Animania.entityegghenleghorn);
		register(Animania.entityeggroosterleghorn);

		register(Animania.entityeggchickred);
		register(Animania.entityegghenred);
		register(Animania.entityeggroosterred);

		register(Animania.entityeggchickorpington);
		register(Animania.entityegghenorpington);
		register(Animania.entityeggroosterorpington);

		register(Animania.entityeggchickwyandotte);
		register(Animania.entityegghenwyandotte);
		register(Animania.entityeggroosterwyandotte);

		register(Animania.entityeggrandomchicken);

		//Peacocks
		register(Animania.entityeggpeacockblue);
		register(Animania.entityeggpeafowlblue);
		register(Animania.entityeggpeachickblue);

		register(Animania.entityeggpeacockwhite);
		register(Animania.entityeggpeafowlwhite);
		register(Animania.entityeggpeachickwhite);

		//Pigs
		register(Animania.entityeggsowyorkshire);
		register(Animania.entityegghogyorkshire);
		register(Animania.entityeggpigletyorkshire);

		register(Animania.entityeggsowoldspot);
		register(Animania.entityegghogoldspot);
		register(Animania.entityeggpigletoldspot);

		register(Animania.entityeggsowlargeblack);
		register(Animania.entityegghoglargeblack);
		register(Animania.entityeggpigletlargeblack);

		register(Animania.entityeggsowlargewhite);
		register(Animania.entityegghoglargewhite);
		register(Animania.entityeggpigletlargewhite);

		register(Animania.entityeggsowduroc);
		register(Animania.entityegghogduroc);
		register(Animania.entityeggpigletduroc);

		register(Animania.entityeggsowhampshire);
		register(Animania.entityegghoghampshire);
		register(Animania.entityeggpiglethampshire);

		register(Animania.entityeggrandompig);

		//Rodents
		register(Animania.entityegghamster);
		register(Animania.entityeggferretgrey);
		register(Animania.entityeggferretwhite);

		register(Animania.entityegghedgehog);
		register(Animania.entityegghedgehogalbino);

		//Blocks
		register(Item.getItemFromBlock(Animania.blockMud));
		register(Item.getItemFromBlock(Animania.blockTrough));
		register(Item.getItemFromBlock(Animania.blockInvisiblock));
		register(Item.getItemFromBlock(Animania.blockNest));
		register(Item.getItemFromBlock(Animania.blockSeeds));


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