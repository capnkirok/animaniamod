package com.animania.common.handler;

import com.animania.common.recipes.CheeseRecipe1;
import com.animania.common.recipes.CheeseRecipe2;
import com.animania.common.recipes.CheeseRecipe3;
import com.animania.common.recipes.CheeseRecipe4;
import com.animania.common.recipes.MilkConversionRecipe;
import com.animania.common.recipes.SlopBucketRecipe1;
import com.animania.common.recipes.SlopBucketRecipe2;
import com.animania.common.recipes.SlopBucketRecipe3;
import com.animania.common.recipes.SlopBucketRecipe4;
import com.animania.common.recipes.SlopBucketRecipe5;
import com.animania.common.recipes.SlopBucketRecipe6;
import com.animania.config.AnimaniaConfig;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHandler
{

	public static void init() {
		ItemStack slopBucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
		ItemStack milkHolstein = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkHolstein);
		ItemStack milkFriesian = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);
		// Recipes

		// HAMSTERS

		/*
		 * GameRegistry.addRecipe(new ItemStack(hamsterBall,1,0), new Object[]{
		 * "xxx","x x","xxx",'x', Blocks.GLASS, }); GameRegistry.addRecipe(new
		 * ItemStack(hamsterBall,1,1), new Object[]{ "xxx","xdx","xxx",'x',
		 * Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 14) });
		 * GameRegistry.addRecipe(new ItemStack(hamsterBall,1,2), new Object[]{
		 * "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1,
		 * 13) }); GameRegistry.addRecipe(new ItemStack(hamsterBall,1,3), new
		 * Object[]{ "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new
		 * ItemStack(Items.DYE, 1, 12) }); GameRegistry.addRecipe(new
		 * ItemStack(hamsterBall,1,4), new Object[]{ "xxx","xdx","xxx",'x',
		 * Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 11) });
		 * GameRegistry.addRecipe(new ItemStack(hamsterBall,1,5), new Object[]{
		 * "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1,
		 * 10) }); GameRegistry.addRecipe(new ItemStack(hamsterBall,1,6), new
		 * Object[]{ "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new
		 * ItemStack(Items.DYE, 1, 9) }); GameRegistry.addRecipe(new
		 * ItemStack(hamsterBall,1,7), new Object[]{ "xxx","xdx","xxx",'x',
		 * Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 8) });
		 * GameRegistry.addRecipe(new ItemStack(hamsterBall,1,8), new Object[]{
		 * "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1,
		 * 7) }); GameRegistry.addRecipe(new ItemStack(hamsterBall,1,9), new
		 * Object[]{ "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new
		 * ItemStack(Items.DYE, 1, 6) }); GameRegistry.addRecipe(new
		 * ItemStack(hamsterBall,1,10), new Object[]{ "xxx","xdx","xxx",'x',
		 * Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 5) });
		 * GameRegistry.addRecipe(new ItemStack(hamsterBall,1,11), new Object[]{
		 * "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1,
		 * 4) }); GameRegistry.addRecipe(new ItemStack(hamsterBall,1,12), new
		 * Object[]{ "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new
		 * ItemStack(Items.DYE, 1, 3) }); GameRegistry.addRecipe(new
		 * ItemStack(hamsterBall,1,13), new Object[]{ "xxx","xdx","xxx",'x',
		 * Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1, 2) });
		 * GameRegistry.addRecipe(new ItemStack(hamsterBall,1,14), new Object[]{
		 * "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new ItemStack(Items.DYE, 1,
		 * 1) }); GameRegistry.addRecipe(new ItemStack(hamsterBall,1,15), new
		 * Object[]{ "xxx","xdx","xxx",'x', Blocks.GLASS, 'd', new
		 * ItemStack(Items.DYE, 1, 0) }); /* GameRegistry.addRecipe(new
		 * ItemStack(WaterBottle,1,0), new Object[]{ "x","w","i",'x',
		 * Blocks.glass, 'w', new ItemStack(Items.potionitem, 1, 0), 'i',
		 * Items.iron_ingot });
		 */

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.hamsterFood, 3),
				new Object[] { Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.hamsterFood, 3),
				new Object[] { "listAllseed", Items.PUMPKIN_SEEDS, Items.MELON_SEEDS }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.hamsterFood, 3),
				new Object[] { Items.WHEAT_SEEDS, "listAllseed", Items.MELON_SEEDS }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.hamsterFood, 3),
				new Object[] { Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, "listAllseed" }));

		// CARVING KNIFE
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.carvingKnife, 1),
				new Object[] { "   ", "ii ", "s  ", 'i', "ingotIron", 's', Items.STICK }));

		//RIDING CROP
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.ridingCrop, 1), 
				new Object[] {"  l"," s ","l  ",'l', "leather", 's', "stickWood"
		}));

		// BEEF Recipes
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawAngusSteak, 5),
				new Object[] { ItemHandler.rawAngusBeef, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawHerefordSteak, 4),
				new Object[] { ItemHandler.rawHerefordBeef, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawLonghornSteak, 4),
				new Object[] { ItemHandler.rawLonghornBeef, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawPrimeSteak, 4),
				new Object[] { ItemHandler.rawPrimeBeef, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));

		// PORK Recipes
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawLargeBlackBacon, 5),
				new Object[] { ItemHandler.rawLargeBlackPork, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawDurocBacon, 4),
				new Object[] { ItemHandler.rawDurocPork, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawOldSpotBacon, 4),
				new Object[] { ItemHandler.rawOldSpotPork, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawHampshireBacon, 4),
				new Object[] { ItemHandler.rawHampshirePork, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.rawPrimeBacon, 4),
				new Object[] { ItemHandler.rawPrimePork, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.LEAD, 1), new Object[] { "leather", "leather", "string", "string" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.NAME_TAG, 1), new Object[] { "string", "nuggetGold" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.truffleSoup, 1),
				new Object[] { ItemHandler.truffle, ItemHandler.truffle, Items.BOWL }));

		/*
		 * GameRegistry.addRecipe(new ShapelessOreRecipe(new
		 * ItemStack(ItemHandler.cheeseWheelHolstein, 1), new Object[] {
		 * ItemHandler.milkBucketHolstein, new ItemStack(ItemHandler.cheeseMold,
		 * 1, OreDictionary.WILDCARD_VALUE) }));
		 *
		 * GameRegistry.addRecipe(new ShapelessOreRecipe(new
		 * ItemStack(ItemHandler.cheeseWheelFriesian, 1), new Object[] {
		 * ItemHandler.milkBucketFriesian, new ItemStack(ItemHandler.cheeseMold,
		 * 1, OreDictionary.WILDCARD_VALUE) }));
		 */

		GameRegistry.addRecipe(
				new ShapelessOreRecipe(new ItemStack(ItemHandler.cheeseOmelette, 1), new Object[] { ItemHandler.plainOmelette, "foodCheese" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.baconOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedDurocBacon }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.baconOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedHampshireBacon }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.baconOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedLargeBlackBacon }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.baconOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedOldSpotBacon }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.baconOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedPrimeBacon }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.truffleOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.truffle }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.ultimateOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedDurocBacon, ItemHandler.truffle, "foodCheese" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.ultimateOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedHampshireBacon, ItemHandler.truffle, "foodCheese" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.ultimateOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedLargeBlackBacon, ItemHandler.truffle, "foodCheese" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.ultimateOmelette, 1),
				new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedPrimeBacon, ItemHandler.truffle, "foodCheese" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.chocolateTruffle, 1),
				new Object[] { ItemHandler.truffle, new ItemStack(Items.DYE, 1, 3), "listAllsugar" }));

		// BLOCKS
		// Nest
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BlockHandler.blockNest, 1), new Object[] { "wool", "stickWood", "treeLeaves" }));

		// Trough
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockHandler.blockTrough, 1),
				new Object[] { "p p", "pip", "s s", 'p', "plankWood", 'i', "ingotIron", 's', "stickWood" }));

		// Cheese Mold
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.cheeseMold, 1),
				new Object[] { "   ", "pip", " p ", 'p', "plankWood", 'i', "ingotIron" }));

		GameRegistry.addRecipe(new SlopBucketRecipe1());
		GameRegistry.addRecipe(new SlopBucketRecipe2());
		GameRegistry.addRecipe(new SlopBucketRecipe3());
		GameRegistry.addRecipe(new SlopBucketRecipe4());
		GameRegistry.addRecipe(new SlopBucketRecipe5());
		GameRegistry.addRecipe(new SlopBucketRecipe6());

		// Slop Normal
		/*
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkHolstein, Items.CARROT, Items.POTATO });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkHolstein, Items.CARROT, Items.BEETROOT });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkHolstein, Items.CARROT, Items.BREAD });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkHolstein, Items.BEETROOT, Items.POTATO });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkHolstein, Items.BEETROOT, Items.BREAD });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkHolstein, Items.POTATO, Items.BREAD });
		 *
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkFriesian, Items.CARROT, Items.POTATO });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkFriesian, Items.CARROT, Items.BEETROOT });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkFriesian, Items.CARROT, Items.BREAD });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkFriesian, Items.BEETROOT, Items.POTATO });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkFriesian, Items.BEETROOT, Items.BREAD });
		 * GameRegistry.addShapelessRecipe(slopBucket, new Object[] {
		 * milkFriesian, Items.POTATO, Items.BREAD });
		 */

		RecipeSorter.register("animania:bucketslop1", SlopBucketRecipe1.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop2", SlopBucketRecipe2.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop3", SlopBucketRecipe3.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop4", SlopBucketRecipe4.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop5", SlopBucketRecipe5.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:bucketslop6", SlopBucketRecipe6.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		RecipeSorter.register("animania:milkconversion", MilkConversionRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

		// Slop OreDict
		GameRegistry.addRecipe(new ShapelessOreRecipe(slopBucket,
				new Object[] { "listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropCarrot", "cropPotato", Items.BUCKET }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(slopBucket,
				new Object[] { "listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropCarrot", "bread", Items.BUCKET }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(slopBucket,
				new Object[] { "listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropBeet", "cropPotato", Items.BUCKET }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(slopBucket,
				new Object[] { "listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropBeet", "bread", Items.BUCKET }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(slopBucket,
				new Object[] { "listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropPotato", "bread", Items.BUCKET }));

		// Cheese Mold Recipes
		GameRegistry.addRecipe(new CheeseRecipe1());
		RecipeSorter.register("animania:cheesewheelholstein", CheeseRecipe1.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.cheeseWheelHolstein), new Object[] { ItemHandler.cheeseMold, milkHolstein });

		GameRegistry.addRecipe(new CheeseRecipe2());
		RecipeSorter.register("animania:cheesewheelfriesian", CheeseRecipe2.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
		GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.cheeseWheelFriesian), new Object[] { ItemHandler.cheeseMold, milkFriesian });

		/*
		 * GameRegistry.addRecipe(new ShapelessOreRecipe(new
		 * ItemStack(ItemHandler.cheeseWheelHolstein, 1), new Object[] {
		 * ItemHandler.milkBucketHolstein, new ItemStack(ItemHandler.cheeseMold,
		 * 1, OreDictionary.WILDCARD_VALUE) }));
		 *
		 * GameRegistry.addRecipe(new ShapelessOreRecipe(new
		 * ItemStack(ItemHandler.cheeseWheelFriesian, 1), new Object[] {
		 * ItemHandler.milkBucketFriesian, new ItemStack(ItemHandler.cheeseMold,
		 * 1, OreDictionary.WILDCARD_VALUE) }));
		 */

		// Carving Knife Recipes
		GameRegistry.addRecipe(new CheeseRecipe3());
		RecipeSorter.register("animania:cheesewedgeholstein", CheeseRecipe3.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

		GameRegistry.addRecipe(new CheeseRecipe4());
		RecipeSorter.register("animania:cheesewedgefriesian", CheeseRecipe4.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.cheeseWedgeHolstein, 4),
				new Object[] { ItemHandler.cheeseWheelHolstein, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.cheeseWedgeFriesian, 4),
				new Object[] { ItemHandler.cheeseWheelFriesian, new ItemStack(ItemHandler.carvingKnife, 1, OreDictionary.WILDCARD_VALUE) }));

		// Slop Bucket Special Recipe

		GameRegistry.addRecipe(new ShapelessOreRecipe(slopBucket,
				new Object[] { "listAllmilk", "listAllmilk", "listAllmilk", "listAllmilk", "cropCarrot", "cropBeet", Items.BUCKET }));

		// Blocks
		GameRegistry.addShapelessRecipe(new ItemStack(BlockHandler.blockMud, 2), new Object[] { Items.WATER_BUCKET, Blocks.DIRT });

		// Vanilla transfer meats recipes
		if (AnimaniaConfig.gameRules.enableVanillaMeatRecipes) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawAngusBeef }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawLonghornBeef }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawHerefordBeef }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawAngusSteak }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawLonghornSteak }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawHerefordSteak }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawLargeBlackPork }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawLargeBlackBacon }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawDurocPork }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawDurocBacon }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawHampshirePork }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawHampshireBacon }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawOldSpotPork }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawOldSpotBacon }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.CHICKEN), new Object[] { ItemHandler.rawOrpingtonChicken }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.CHICKEN), new Object[] { ItemHandler.rawPlymouthRockChicken }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.CHICKEN), new Object[] { ItemHandler.rawWyandotteChicken }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.CHICKEN), new Object[] { ItemHandler.rawRhodeIslandRedChicken }));

		}

		GameRegistry.addRecipe(new MilkConversionRecipe(new ItemStack(Items.MILK_BUCKET), new Object[] { milkFriesian }));

		GameRegistry.addRecipe(new MilkConversionRecipe(new ItemStack(Items.MILK_BUCKET), new Object[] { milkHolstein }));

		// Smelting Recipes
		GameRegistry.addSmelting(ItemHandler.rawAngusBeef, new ItemStack(ItemHandler.cookedAngusRoast, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawLonghornBeef, new ItemStack(ItemHandler.cookedLonghornRoast, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawHerefordBeef, new ItemStack(ItemHandler.cookedHerefordRoast, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawAngusSteak, new ItemStack(ItemHandler.cookedAngusSteak, 1), .2F);
		GameRegistry.addSmelting(ItemHandler.rawLonghornSteak, new ItemStack(ItemHandler.cookedLonghornSteak, 1), .2F);
		GameRegistry.addSmelting(ItemHandler.rawHerefordSteak, new ItemStack(ItemHandler.cookedHerefordSteak, 1), .2F);

		GameRegistry.addSmelting(ItemHandler.rawLargeBlackPork, new ItemStack(ItemHandler.cookedLargeBlackRoast, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawDurocPork, new ItemStack(ItemHandler.cookedDurocRoast, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawOldSpotPork, new ItemStack(ItemHandler.cookedOldSpotRoast, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawHampshirePork, new ItemStack(ItemHandler.cookedHampshireRoast, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawLargeBlackBacon, new ItemStack(ItemHandler.cookedLargeBlackBacon, 1), .2F);
		GameRegistry.addSmelting(ItemHandler.rawDurocBacon, new ItemStack(ItemHandler.cookedDurocBacon, 1), .2F);
		GameRegistry.addSmelting(ItemHandler.rawHampshireBacon, new ItemStack(ItemHandler.cookedHampshireBacon, 1), .2F);
		GameRegistry.addSmelting(ItemHandler.rawOldSpotBacon, new ItemStack(ItemHandler.cookedOldSpotBacon, 1), .2F);

		GameRegistry.addSmelting(ItemHandler.rawOrpingtonChicken, new ItemStack(ItemHandler.cookedOrpingtonChicken, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawPlymouthRockChicken, new ItemStack(ItemHandler.cookedPlymouthRockChicken, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawWyandotteChicken, new ItemStack(ItemHandler.cookedWyandotteChicken, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawRhodeIslandRedChicken, new ItemStack(ItemHandler.cookedRhodeIslandRedChicken, 1), .3F);

		GameRegistry.addSmelting(ItemHandler.rawPrimeBeef, new ItemStack(ItemHandler.cookedPrimeBeef, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawPrimeSteak, new ItemStack(ItemHandler.cookedPrimeSteak, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawPrimePork, new ItemStack(ItemHandler.cookedPrimePork, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawPrimeBacon, new ItemStack(ItemHandler.cookedPrimeBacon, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawPrimeChicken, new ItemStack(ItemHandler.cookedPrimeChicken, 1), .3F);

		GameRegistry.addSmelting(ItemHandler.rawFrogLegs, new ItemStack(ItemHandler.cookedFrogLegs, 1), .3F);

		GameRegistry.addSmelting(Items.EGG, new ItemStack(ItemHandler.plainOmelette, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.brownEgg, new ItemStack(ItemHandler.plainOmelette, 1), .3F);

	}
}
