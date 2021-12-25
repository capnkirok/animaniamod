package com.animania.common.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class RecipeHandler
{

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static File RECIPE_DIR = null;
	private static final Set<String> USED_OD_NAMES = new TreeSet<>();
	private static boolean hasDirectory = false;

	public static void init()
	{
		ItemStack slopBucket = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
	}

	public static File getMcDir()
	{
		if (FMLCommonHandler.instance().getMinecraftServerInstance() != null && FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer())
		{
			return new File(".");
		}
		return Minecraft.getMinecraft().mcDataDir;
	}

	public static void addShapedRecipe(ItemStack result, Object... components)
	{

		setupDir();

		Map<String, Object> json = new HashMap<>();

		List<String> pattern = new ArrayList<>();
		int i = 0;
		while (i < components.length && components[i] instanceof String)
		{
			pattern.add((String) components[i]);
			i++;
		}
		json.put("pattern", pattern);

		boolean isOreDict = false;
		Map<String, Map<String, Object>> key = new HashMap<>();
		Character curKey = null;
		for (; i < components.length; i++)
		{
			Object o = components[i];
			if (o instanceof Character)
			{
				if (curKey != null)
					throw new IllegalArgumentException("Provided two char keys in a row");
				curKey = (Character) o;
			}
			else
			{
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

		while (f.exists())
		{
			suffix += "_alt";
			f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");
		}

		try (FileWriter w = new FileWriter(f))
		{
			GSON.toJson(json, w);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public static void addShapelessRecipe(ItemStack result, Object... components)
	{

		setupDir();

		Map<String, Object> json = new HashMap<>();

		boolean isOreDict = false;
		List<Map<String, Object>> ingredients = new ArrayList<>();
		for (Object o : components)
		{
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

		while (f.exists())
		{
			suffix += "_alt";
			f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");
		}

		try (FileWriter w = new FileWriter(f))
		{
			GSON.toJson(json, w);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private static Map<String, Object> serializeItem(Object thing)
	{
		if (thing instanceof RItem)
		{
			return serializeItem(new ItemStack((RItem) thing));
		}
		if (thing instanceof Block)
		{
			return serializeItem(new ItemStack((Block) thing));
		}
		if (thing instanceof ItemStack stack)
		{
			Map<String, Object> ret = new HashMap<>();
			ret.put("item", stack.getItem().getRegistryName().toString());
			if (stack.getItem().getHasSubtypes() || stack.getItemDamage() != 0)
			{
				ret.put("data", stack.getItemDamage());
			}
			if (stack.getCount() > 1)
			{
				ret.put("count", stack.getCount());
			}

			if (stack.hasTagCompound())
			{
				throw new IllegalArgumentException("nbt not implemented");
			}

			return ret;
		}
		if (thing instanceof String)
		{
			Map<String, Object> ret = new HashMap<>();
			USED_OD_NAMES.add((String) thing);
			// OLD ret.put("item", "#" + ((String)
			// thing).toUpperCase(Locale.ROOT));
			ret.put("type", "forge:ore_dict");
			ret.put("ore", thing);
			return ret;
		}

		throw new IllegalArgumentException("Not a block, item, stack, or od name");
	}

	private static void generateConstants()
	{
		List<Map<String, Object>> json = new ArrayList<>();
		for (String s : USED_OD_NAMES)
		{
			Map<String, Object> entry = new HashMap<>();

			entry.put("name", s);
			entry.put("ingredient", ImmutableMap.of("type", "forge:ore_dict", "ore", s));

			json.add(entry);
		}

		try (FileWriter w = new FileWriter(new File(RECIPE_DIR, "_constants.json")))
		{
			GSON.toJson(json, w);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void setupDir()
	{
		if (RECIPE_DIR == null)
		{
			RECIPE_DIR = new File(getMcDir(), "../src/main/resources/assets/animania/recipes/");
		}

		if (!RECIPE_DIR.exists())
		{
			RECIPE_DIR.mkdir();

		}
	}

	private static boolean checkDir()
	{
		if (RECIPE_DIR == null)
		{
			RECIPE_DIR = new File(getMcDir(), "../src/main/resources/assets/animania/recipes/");
		}

		return RECIPE_DIR.exists();

	}

}
