package com.animania.common.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.animania.config.AnimaniaConfig;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;

public class RecipeHandler
{

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static File RECIPE_DIR = null;
	private static final Set<String> USED_OD_NAMES = new TreeSet<>();
	private static boolean hasDirectory = false;

	public static void init()
	{
		ItemStack slopBucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
		ItemStack milkHolstein = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkHolstein);
		ItemStack milkFriesian = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);
		ItemStack milkGoat = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkGoat);
		ItemStack milkSheep = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkSheep);

		
		

		/*
		
		if (!checkDir()) {

		//addShapedRecipe(new ItemStack(ItemHandler.wagon, 1), new Object[] { "OOP", "BPC", "WAW", 'O', "blockWool", 'P', "plankWood", 'B', Items.BED, 'C', Blocks.CHEST, 'W', ItemHandler.wheel, 'A', ItemHandler.cart });
		//addShapelessRecipe(new ItemStack(ItemHandler.milkBottle, 4), new Object[] { "listAllmilk", Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE });
			
			// HAMSTERS

			addShapedRecipe(new ItemStack(BlockHandler.blockHamsterWheel, 1), new Object[] { " I ", "I I", "SIS", 'I', "ingotIron", 'S', new ItemStack(Blocks.STONE_SLAB) });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallClear), new Object[] { "xxx", "x x", "xxx", 'x', Blocks.GLASS_PANE });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 0), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeBlack" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 1), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeRed" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 2), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeGreen" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 3), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeBrown" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 4), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeBlue" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 5), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyePurple" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 6), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeCyan" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 7), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeLightGray" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 8), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeGray" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 9), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyePink" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 10), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeLime" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 11), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeYellow" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 12), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeLightBlue" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 13), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeMagenta" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 14), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeOrange" });
			addShapedRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 15), new Object[] { "xxx", "xdx", "xxx", 'x', Blocks.GLASS_PANE, 'd', "dyeWhite" });

			addShapelessRecipe(new ItemStack(ItemHandler.hamsterFood, 3), new Object[] { Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS });
			addShapelessRecipe(new ItemStack(ItemHandler.hamsterFood, 3), new Object[] { "listAllseed", "listAllseed", "listAllseed" });

			// CARVING KNIFE
			addShapedRecipe(new ItemStack(ItemHandler.carvingKnife, 1), new Object[] { "   ", "ii ", "s  ", 'i', "ingotIron", 's', "stickWood" });

			// RIDING CROP
			addShapedRecipe(new ItemStack(ItemHandler.ridingCrop, 1), new Object[] { "  l", " s ", "l  ", 'l', "leather", 's', "stickWood"});

			// BLOCKS
			// Nest
			addShapelessRecipe(new ItemStack(BlockHandler.blockNest, 1), new Object[] { "wool", "stickWood", "treeLeaves" });

			// Trough
			addShapedRecipe(new ItemStack(BlockHandler.blockTrough, 1), new Object[] { "p p", "pip", "s s", 'p', "plankWood", 'i', "ingotIron", 's', "stickWood" });

			// Cheese Mold
			addShapedRecipe(new ItemStack(ItemHandler.cheeseMold, 1), new Object[] { "   ", "pip", " p ", 'p', "plankWood", 'i', "ingotIron" });

			// Salt Lick
			addShapelessRecipe(new ItemStack(BlockHandler.blockSaltLick, 1), new Object[] { "dustSalt", "dustSalt", "dustSalt", "dustSalt", "dustSalt", "dustSalt", "dustSalt", "dustSalt" , Items.WATER_BUCKET });

			// Wool
			addShapelessRecipe(new ItemStack(Blocks.WOOL, 1, 12), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 0));
			addShapelessRecipe(new ItemStack(Blocks.WOOL, 1, 12), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 3));
			addShapelessRecipe(new ItemStack(Blocks.WOOL, 1, 12), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 5));
			addShapelessRecipe(new ItemStack(Blocks.WOOL, 1, 15), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 1));
			addShapelessRecipe(new ItemStack(Blocks.WOOL, 1, 0), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 2));
			addShapelessRecipe(new ItemStack(Blocks.WOOL, 1, 0), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 4));

			//Custom Wool Recipes
			//Banners
			addShapedRecipe(new ItemStack(Items.BANNER, 1, 15), new Object[] {
					"www","www"," s ",'w', new ItemStack(BlockHandler.blockAnimaniaWool, 1, 5), 's', "stickWood"
			});

			addShapedRecipe(new ItemStack(Items.BANNER, 1, 3), new Object[] {
					"www","www"," s ",'w', new ItemStack(BlockHandler.blockAnimaniaWool, 1, 2), 's', "stickWood"
			});

			addShapedRecipe(new ItemStack(Items.BANNER, 1, 3), new Object[] {
					"www","www"," s ",'w', new ItemStack(BlockHandler.blockAnimaniaWool, 1, 4), 's', "stickWood"
			});

			addShapedRecipe(new ItemStack(Items.BANNER, 1, 3), new Object[] {
					"www","www"," s ",'w', new ItemStack(BlockHandler.blockAnimaniaWool, 1, 0), 's', "stickWood"
			});

			addShapedRecipe(new ItemStack(Items.BANNER, 1, 3), new Object[] {
					"www","www"," s ",'w', new ItemStack(BlockHandler.blockAnimaniaWool, 1, 6), 's', "stickWood"
			});

			addShapedRecipe(new ItemStack(Items.BANNER, 1, 0), new Object[] {
					"www","www"," s ",'w', new ItemStack(BlockHandler.blockAnimaniaWool, 1, 1), 's', "stickWood"
			});

			addShapedRecipe(new ItemStack(Items.BANNER, 1, 7), new Object[] {
					"www","www"," s ",'w', new ItemStack(BlockHandler.blockAnimaniaWool, 1, 3), 's', "stickWood"
			});

			//Bed
			addShapedRecipe(new ItemStack(Items.BED, 1), new Object[] {
					"   ","www","ppp",'w', "wool", 'p', "plankWood"
			});

			//Painting
			addShapedRecipe(new ItemStack(Items.PAINTING, 1), new Object[] {
					"sss", "sws","sss",'w', "wool", 's', "stickWood"
			});

			//Carpet
			addShapelessRecipe(new ItemStack(Blocks.CARPET, 3, 0), new Object[] {
					new ItemStack(BlockHandler.blockAnimaniaWool, 1, 5), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 5)
			});

			addShapelessRecipe(new ItemStack(Blocks.CARPET, 3, 12), new Object[] {
					new ItemStack(BlockHandler.blockAnimaniaWool, 1, 0), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 0)
			});

			addShapelessRecipe(new ItemStack(Blocks.CARPET, 3, 12), new Object[] {
					new ItemStack(BlockHandler.blockAnimaniaWool, 1, 2), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 2)
			});

			addShapelessRecipe(new ItemStack(Blocks.CARPET, 3, 12), new Object[] {
					new ItemStack(BlockHandler.blockAnimaniaWool, 1, 4), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 4)
			});

			addShapelessRecipe(new ItemStack(Blocks.CARPET, 3, 12), new Object[] {
					new ItemStack(BlockHandler.blockAnimaniaWool, 1, 6), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 6)
			});

			addShapelessRecipe(new ItemStack(Blocks.CARPET, 3, 15), new Object[] {
					new ItemStack(BlockHandler.blockAnimaniaWool, 1, 1), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 1)
			});

			addShapelessRecipe(new ItemStack(Blocks.CARPET, 3, 8), new Object[] {
					new ItemStack(BlockHandler.blockAnimaniaWool, 1, 3), new ItemStack(BlockHandler.blockAnimaniaWool, 1, 3)
			});
			
			// Misc
			addShapelessRecipe(new ItemStack(Items.LEAD, 1), new Object[] { "leather", "leather", "string", "string" });
			addShapelessRecipe(new ItemStack(Items.NAME_TAG, 1), new Object[] { "string", "nuggetGold" });

			// Foods
			addShapelessRecipe(new ItemStack(ItemHandler.truffleSoup, 1), new Object[] { ItemHandler.truffle, ItemHandler.truffle, Items.BOWL });
			addShapelessRecipe(new ItemStack(ItemHandler.cheeseOmelette, 1), new Object[] { ItemHandler.plainOmelette, "foodCheese" });
			addShapelessRecipe(new ItemStack(ItemHandler.baconOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedDurocBacon });
			addShapelessRecipe(new ItemStack(ItemHandler.baconOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedHampshireBacon });
			addShapelessRecipe(new ItemStack(ItemHandler.baconOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedLargeBlackBacon });
			addShapelessRecipe(new ItemStack(ItemHandler.baconOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedOldSpotBacon });
			addShapelessRecipe(new ItemStack(ItemHandler.baconOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedPrimeBacon });
			addShapelessRecipe(new ItemStack(ItemHandler.truffleOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.truffle });
			addShapelessRecipe(new ItemStack(ItemHandler.ultimateOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedDurocBacon, ItemHandler.truffle, "foodCheese" });
			addShapelessRecipe(new ItemStack(ItemHandler.ultimateOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedHampshireBacon, ItemHandler.truffle, "foodCheese" });
			addShapelessRecipe(new ItemStack(ItemHandler.ultimateOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedLargeBlackBacon, ItemHandler.truffle, "foodCheese" });
			addShapelessRecipe(new ItemStack(ItemHandler.ultimateOmelette, 1), new Object[] { ItemHandler.plainOmelette, ItemHandler.cookedPrimeBacon, ItemHandler.truffle, "foodCheese" });

			addShapelessRecipe(new ItemStack(ItemHandler.chocolateTruffle, 1), new Object[] { ItemHandler.truffle, new ItemStack(Items.DYE, 1, 3), "listAllsugar" });

			// Blocks
			addShapelessRecipe(new ItemStack(BlockHandler.blockMud, 2), new Object[] { Items.WATER_BUCKET, Blocks.DIRT });

			// Vanilla transfer meats recipes
			if (AnimaniaConfig.gameRules.enableVanillaMeatRecipes)
			{
				addShapelessRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawAngusBeef });
				addShapelessRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawLonghornBeef });
				addShapelessRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawHerefordBeef });
				addShapelessRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawAngusSteak });
				addShapelessRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawLonghornSteak });
				addShapelessRecipe(new ItemStack(Items.BEEF), new Object[] { ItemHandler.rawHerefordSteak });

				addShapelessRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawLargeBlackPork });
				addShapelessRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawLargeBlackBacon });
				addShapelessRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawDurocPork });
				addShapelessRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawDurocBacon });
				addShapelessRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawHampshirePork });
				addShapelessRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawHampshireBacon });
				addShapelessRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawOldSpotPork });
				addShapelessRecipe(new ItemStack(Items.PORKCHOP), new Object[] { ItemHandler.rawOldSpotBacon });

				addShapelessRecipe(new ItemStack(Items.CHICKEN), new Object[] { ItemHandler.rawOrpingtonChicken });
				addShapelessRecipe(new ItemStack(Items.CHICKEN), new Object[] { ItemHandler.rawPlymouthRockChicken });
				addShapelessRecipe(new ItemStack(Items.CHICKEN), new Object[] { ItemHandler.rawWyandotteChicken });
				addShapelessRecipe(new ItemStack(Items.CHICKEN), new Object[] { ItemHandler.rawRhodeIslandRedChicken });
			}

			System.out.println("Animania recipes have been regenerated. A restart is required to reflect changes. If you enabled this in the config, you can now disable.");

		}
		*/

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
		GameRegistry.addSmelting(ItemHandler.peacockEggBlue, new ItemStack(ItemHandler.plainOmelette, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.peacockEggWhite, new ItemStack(ItemHandler.plainOmelette, 1), .3F);
		
		GameRegistry.addSmelting(ItemHandler.rawRabbit, new ItemStack(ItemHandler.cookedRabbit, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawMutton, new ItemStack(ItemHandler.cookedMutton, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawChevon, new ItemStack(ItemHandler.cookedChevon, 1), .3F);
		GameRegistry.addSmelting(ItemHandler.rawPrimeChevon, new ItemStack(ItemHandler.cookedPrimeChevon, 1), .3F);

	}


	public static File getMcDir()
	{
		if (FMLCommonHandler.instance().getMinecraftServerInstance() != null && FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer())
		{
			return new File(".");
		}
		return Minecraft.getMinecraft().mcDataDir;
	}

	public static void addShapedRecipe(ItemStack result, Object... components) {

		setupDir();

		Map<String, Object> json = new HashMap<>();

		List<String> pattern = new ArrayList<>();
		int i = 0;
		while (i < components.length && components[i] instanceof String) {
			pattern.add((String) components[i]);
			i++;
		}
		json.put("pattern", pattern);

		boolean isOreDict = false;
		Map<String, Map<String, Object>> key = new HashMap<>();
		Character curKey = null;
		for (; i < components.length; i++) {
			Object o = components[i];
			if (o instanceof Character) {
				if (curKey != null)
					throw new IllegalArgumentException("Provided two char keys in a row");
				curKey = (Character) o;
			} else {
				if (curKey == null)
					throw new IllegalArgumentException("Providing object without a char key");
				if (o instanceof String)
					isOreDict = true;
				key.put(Character.toString(curKey), serializeItem(o));
				curKey = null;
			}
		}
		json.put("key", key);
		json.put("type", isOreDict ? "forge:ore_shaped" : "minecraft:crafting_shaped");
		json.put("result", serializeItem(result));

		// names the json the same name as the output's registry name
		// repeatedly adds _alt if a file already exists
		// janky I know but it works
		String suffix = "";
		File f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");

		while (f.exists()) {
			suffix += "_alt";
			f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");
		}

		try (FileWriter w = new FileWriter(f)) {
			GSON.toJson(json, w);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void addShapelessRecipe(ItemStack result, Object... components) {

		setupDir();

		Map<String, Object> json = new HashMap<>();

		boolean isOreDict = false;
		List<Map<String, Object>> ingredients = new ArrayList<>();
		for (Object o : components) {
			if (o instanceof String)
				isOreDict = true;
			ingredients.add(serializeItem(o));
		}
		json.put("ingredients", ingredients);
		json.put("type", isOreDict ? "forge:ore_shapeless" : "minecraft:crafting_shapeless");
		json.put("result", serializeItem(result));

		// names the json the same name as the output's registry name
		// repeatedly adds _alt if a file already exists
		// janky I know but it works
		String suffix = result.getItem().getHasSubtypes() ? "_" + result.getItemDamage() : "";
		File f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");

		while (f.exists()) {
			suffix += "_alt";
			f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");
		}


		try (FileWriter w = new FileWriter(f)) {
			GSON.toJson(json, w);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	private static Map<String, Object> serializeItem(Object thing) {
		if (thing instanceof Item) {
			return serializeItem(new ItemStack((Item) thing));
		}
		if (thing instanceof Block) {
			return serializeItem(new ItemStack((Block) thing));
		}
		if (thing instanceof ItemStack) {
			ItemStack stack = (ItemStack) thing;
			Map<String, Object> ret = new HashMap<>();
			ret.put("item", stack.getItem().getRegistryName().toString());
			if (stack.getItem().getHasSubtypes() || stack.getItemDamage() != 0) {
				ret.put("data", stack.getItemDamage());
			}
			if (stack.getCount() > 1) {
				ret.put("count", stack.getCount());
			}

			if (stack.hasTagCompound()) {
				throw new IllegalArgumentException("nbt not implemented");
			}

			return ret;
		}
		if (thing instanceof String) {
			Map<String, Object> ret = new HashMap<>();
			USED_OD_NAMES.add((String) thing);
			//OLD ret.put("item", "#" + ((String) thing).toUpperCase(Locale.ROOT));
			ret.put("type", "forge:ore_dict");
			ret.put("ore", ((String) thing));
			return ret;
		}

		throw new IllegalArgumentException("Not a block, item, stack, or od name");
	}

	private static void generateConstants() {
		List<Map<String, Object>> json = new ArrayList<>();
		for (String s : USED_OD_NAMES) {
			Map<String, Object> entry = new HashMap<>();

			entry.put("name", s);
			entry.put("ingredient", ImmutableMap.of("type", "forge:ore_dict", "ore", s));

			json.add(entry);
		}

		try (FileWriter w = new FileWriter(new File(RECIPE_DIR, "_constants.json"))) {
			GSON.toJson(json, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setupDir() {
		if (RECIPE_DIR == null) {
			RECIPE_DIR = new File(getMcDir(), "../src/main/resources/assets/animania/recipes/");
		}

		if (!RECIPE_DIR.exists()) {
			RECIPE_DIR.mkdir();

		} 
	}

	private static boolean checkDir() {
		if (RECIPE_DIR == null) {
			RECIPE_DIR = new File(getMcDir(), "../src/main/resources/assets/animania/recipes/");
		}

		return RECIPE_DIR.exists();

	}


}

