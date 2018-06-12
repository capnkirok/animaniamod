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
		
		GameRegistry.addSmelting(ItemHandler.rawHorse, new ItemStack(ItemHandler.cookedHorse, 1), .3F);

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

