package com.animania.common.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.animania.Animania;
import com.animania.addons.AddonResourcePack;
import com.animania.api.addons.AnimaniaAddon;
import com.animania.api.addons.IAddonGuiHandler;
import com.animania.api.addons.LoadAddon;
import com.animania.common.helper.ReflectionUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.AdvancementTreeNode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.FactoryLoader;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MissingModsException;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.MultipleModsErrored;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.DependencyParser;
import net.minecraftforge.fml.common.versioning.DependencyParser.DependencyInfo;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = Animania.MODID)
public class AddonHandler
{
	private static int guiHandlerCounter = 1000;

	private static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	private static Map<String, AnimaniaAddon> loadedAddons = new HashMap<String, AnimaniaAddon>();
	private static List<MissingModsException> missingModsExceptions = new ArrayList<MissingModsException>();
	private static Map<String, IAddonGuiHandler> addonGuiHandlers = new HashMap<String, IAddonGuiHandler>();

	private static Method loadFactories;
	private static Method loadConstants;
	private static Method loadBuildtInAdvancements;
	private static Method loadCustomAdvancements;
	private static Field advancementManager;
	private static Field ADVANCEMENT_LIST;
	private static Field hasErrored;

	static
	{
		try
		{
			loadFactories = ReflectionUtil.findMethod(CraftingHelper.class, "loadFactories", null, JsonObject.class, JsonContext.class, CraftingHelper.FactoryLoader[].class);
			loadFactories.setAccessible(true);

			loadConstants = ReflectionUtil.findMethod(JsonContext.class, "loadConstants", null, JsonObject[].class);
			loadConstants.setAccessible(true);

			advancementManager = ReflectionUtil.findField(World.class, "field_191951_C", "advancementManager");
			advancementManager.setAccessible(true);

			hasErrored = ReflectionUtil.findField(AdvancementManager.class, "field_193768_e", "hasErrored");
			hasErrored.setAccessible(true);

			loadBuildtInAdvancements = ReflectionUtil.findMethod(AdvancementManager.class, "loadBuiltInAdvancements", "func_192777_a", Map.class);
			loadBuildtInAdvancements.setAccessible(true);

			loadCustomAdvancements = ReflectionUtil.findMethod(AdvancementManager.class, "loadCustomAdvancements", "func_192781_c");
			loadCustomAdvancements.setAccessible(true);

			ADVANCEMENT_LIST = ReflectionUtil.findField(AdvancementManager.class, "field_192784_c", "ADVANCEMENT_LIST");
			ADVANCEMENT_LIST.setAccessible(true);

		} catch (SecurityException e)
		{
			e.printStackTrace();
		}

	}

	public static void preInitCommon()
	{

		for (AnimaniaAddon a : loadedAddons.values())
			a.preInitCommon();
	}

	public static void initCommon()
	{
		for (AnimaniaAddon a : loadedAddons.values())
			a.initCommon();
	}

	public static void preInitClient()
	{
		for (AnimaniaAddon a : loadedAddons.values())
			a.preInitClient();
	}

	public static void initClient()
	{
		for (AnimaniaAddon a : loadedAddons.values())
			a.initClient();

	}

	public static void loadAddons(ASMDataTable table)
	{
		Set<ASMData> asmData = table.getAll(LoadAddon.class.getCanonicalName());

		asmData.forEach((asm) -> {
			try
			{
				Class<AnimaniaAddon> clazz = (Class<AnimaniaAddon>) Class.forName(asm.getClassName()).asSubclass(AnimaniaAddon.class);

				AnimaniaAddon a = clazz.newInstance();

				if (loadedAddons.containsKey(a.getAddonID()))
					throw new RuntimeException("The addon with the id " + a.getAddonID() + " is already installed!");

				if (a.getAddonID().equals("template"))
					return;

				loadedAddons.put(a.getAddonID(), a);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
			{
				Animania.LOGGER.error(e);
			}
		});

		loadedAddons.values().forEach(addon -> {
			register(addon);
		});

	}

	public static void registerAddonGuiHandler(String addonId, IAddonGuiHandler handler)
	{
		handler.setGuiIdOffset(guiHandlerCounter);
		addonGuiHandlers.put(addonId, handler);
		guiHandlerCounter += 1000;
	}

	public static Object openAddonGui(EntityPlayer player, int guiId, World world, int x, int y, int z, Side side)
	{
		Object returnObject = null;

		for (IAddonGuiHandler handler : addonGuiHandlers.values())
		{
			if (side == Dist.CLIENT)
				returnObject = handler.getClientGuiElement(guiId - handler.getGuiIdOffset(), player, world, x, y, z);
			else
				returnObject = handler.getServerGuiElement(guiId - handler.getGuiIdOffset(), player, world, x, y, z);

			if (returnObject != null)
				return returnObject;
		}

		return null;
	}

	public static void throwErrors()
	{
		if (!missingModsExceptions.isEmpty() && !Animania.IS_DEV)
		{
			MultipleModsErrored errors = new MultipleModsErrored(Collections.EMPTY_LIST, missingModsExceptions);
			Animania.proxy.throwCustomModLoadingErrorDisplayException(errors);
		}
	}

	public static boolean isAddonLoaded(String addonID)
	{
		return loadedAddons.get(addonID) != null;
	}

	private static void register(AnimaniaAddon addon)
	{
		if (addon == null)
			return;

		if (addon.getAddonID().equals("template"))
			return;

		Map<String, ModContainer> loadedMods = Loader.instance().getIndexedModList();
		DependencyParser parser = new DependencyParser(addon.getAddonID(), Dist.DEDICATED_SERVER);
		DependencyInfo info = parser.parseDependencies(addon.getDependencies());

		for (ArtifactVersion dependency : info.dependencies)
		{
			String modid = dependency.getLabel();
			if (modid == null)
				throw new RuntimeException("Modid Dependency for " + addon.getAddonID() + " cannot be null.");

			ModContainer container = loadedMods.get(modid);
			if (container == null)
			{
				if (isAddonLoaded(modid))
				{
					AnimaniaAddon depAddon = loadedAddons.get(modid);
					ArtifactVersion addonVersion = new DefaultArtifactVersion(modid, depAddon.getVersion());

					if (!dependency.containsVersion(addonVersion))
					{
						MissingModsException ex = new MissingModsException(addon.getAddonID(), addon.getAddonName());
						ex.addMissingMod(dependency, addonVersion, true);
						ex.missingMods.add(dependency);

						missingModsExceptions.add(ex);
						continue;
					}
				} else
				{
					MissingModsException ex = new MissingModsException(addon.getAddonID(), addon.getAddonName());
					ex.addMissingMod(dependency, null, true);
					ex.missingMods.add(dependency);

					missingModsExceptions.add(ex);
					continue;
				}
			}

			ArtifactVersion modVersion = container.getProcessedVersion();

			if (!dependency.containsVersion(modVersion))
			{
				MissingModsException ex = new MissingModsException(addon.getAddonID(), addon.getAddonName());
				ex.addMissingMod(dependency, modVersion, true);
				ex.missingMods.add(dependency);

				missingModsExceptions.add(ex);
			}
		}

		addAddonResourcePack(addon);

		Animania.LOGGER.info("Loaded Addon " + addon.getAddonName() + " with id " + addon.getAddonID() + " and Class " + addon.getClass().getName());
	}

	private static void addAddonResourcePack(AnimaniaAddon addon)
	{
		Animania.proxy.addAddonResourcePack(addon);
	}

	@SubscribeEvent
	public static void onRecipeRegistry(Register<IRecipe> event)
	{
		for (AnimaniaAddon addon : loadedAddons.values())
		{
			registerAddonFactories(addon);
			loadAddonRecipes(addon);
		}
	}

	private static void registerAddonFactories(AnimaniaAddon addon)
	{
		ModContainer mod = Loader.instance().activeModContainer();
		boolean isJar = mod.getSource().isDirectory() ? false : true;

		FileSystem fs = null;
		try
		{
			Tuple<FileSystem, Path> tuple = AddonResourcePack.getAddonPath(addon);
			Path fPath = tuple.getSecond();
			fs = tuple.getFirst();
			JsonContext ctx = new JsonContext(mod.getModId());

			// if (mod.getSource().isFile())
			// {
			// fs =
			// FileSystems.newFileSystem(AddonResourcePack.getAddonFile(addon).toPath(),
			// null);
			// fPath = fs.getPath("/assets/" + addon.getAddonID() + "/" +
			// ctx.getModId() + "/recipes/_factories.json");
			// } else if (mod.getSource().isDirectory())
			// {
			// fPath = mod.getSource().toPath().resolve("assets/" +
			// addon.getAddonID() + "/" + ctx.getModId() +
			// "/recipes/_factories.json");
			// }

			if (isJar)
				fPath = fs.getPath("/assets/" + addon.getAddonID() + "/" + ctx.getModId() + "/recipes/_factories.json");
			else
				fPath = fPath.resolve(ctx.getModId() + "/recipes/_factories.json");

			if (fPath != null && Files.exists(fPath))
			{
				try (BufferedReader reader = Files.newBufferedReader(fPath))
				{
					JsonObject json = JsonUtils.fromJson(GSON, reader, JsonObject.class);
					loadFactories.invoke(CraftingHelper.class, json, ctx, new FactoryLoader[] { CraftingHelper.RECIPES, CraftingHelper.INGREDIENTS, CraftingHelper.CONDITIONS });
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JsonParseException | IOException e)
		{
			FMLLog.log.error("Error loading _factories.json: ", e);
		} finally
		{
			IOUtils.closeQuietly(fs);
		}

	}

	private static void loadAddonRecipes(AnimaniaAddon addon)
	{
		ModContainer mod = Loader.instance().activeModContainer();

		JsonContext ctx = new JsonContext(mod.getModId());

		findFiles(addon, mod, mod.getModId() + "/recipes", root -> {
			Path fPath = root.resolve("_constants.json");
			if (fPath != null && Files.exists(fPath))
			{
				try (BufferedReader reader = Files.newBufferedReader(fPath))
				{
					JsonObject[] json = JsonUtils.fromJson(GSON, reader, JsonObject[].class);
					loadConstants.invoke(ctx, new Object[] { json });
				} catch (JsonParseException | IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
				{
					FMLLog.log.error("Error loading _constants.json: ", e);
					return false;
				}
			}
			return true;
		}, (root, file) -> {
			Loader.instance().setActiveModContainer(mod);

			String relative = root.relativize(file).toString();
			if (!"json".equals(FilenameUtils.getExtension(file.toString())) || relative.startsWith("_"))
				return true;

			String name = FilenameUtils.removeExtension(relative).replaceAll("\\\\", "/");
			ResourceLocation key = new ResourceLocation(ctx.getModId(), name);

			try (BufferedReader reader = Files.newBufferedReader(file))
			{
				JsonObject json = JsonUtils.fromJson(GSON, reader, JsonObject.class);
				if (!CraftingHelper.processConditions(json, "conditions", ctx))
					return true;
				IRecipe recipe = CraftingHelper.getRecipe(json, ctx);
				ForgeRegistries.RECIPES.register(recipe.setRegistryName(key));
			} catch (JsonParseException e)
			{
				FMLLog.log.error("Parsing error loading recipe {}", key, e);
				return false;
			} catch (IOException e)
			{
				FMLLog.log.error("Couldn't read recipe {} from {}", key, file, e);
				return false;
			}
			return true;
		}, true, true);
	}

	private static boolean findFiles(AnimaniaAddon addon, ModContainer mod, String base, Function<Path, Boolean> preprocessor, BiFunction<Path, Path, Boolean> processor, boolean defaultUnfoundRoot, boolean visitAllFiles)
	{
		boolean isJar = mod.getSource().isDirectory() ? false : true;

		boolean success = true;
		Tuple<FileSystem, Path> tuple = AddonResourcePack.getAddonPath(addon);

		try
		{
			Path root = null;
			if (isJar)
				root = tuple.getFirst().getPath("/assets/" + addon.getAddonID() + "/" + base);
			else
				root = tuple.getSecond().resolve(base);

			if (root == null || !Files.exists(root))
			{
				return defaultUnfoundRoot;
			}

			if (preprocessor != null)
			{
				Boolean cont = preprocessor.apply(root);
				if (cont == null || !cont.booleanValue())
					return false;
			}

			if (processor != null)
			{
				Iterator<Path> itr = null;
				try
				{
					itr = Files.walk(root).iterator();
				} catch (IOException e)
				{
					FMLLog.log.error("Error iterating filesystem for: {}", addon.getAddonID(), e);
					return false;
				}

				while (itr != null && itr.hasNext())
				{
					Boolean cont = processor.apply(root, itr.next());

					if (visitAllFiles)
					{
						success &= cont != null && cont;
					} else if (cont == null || !cont)
					{
						return false;
					}
				}
			}
		} finally
		{
			if (tuple.getFirst() != null)
				IOUtils.closeQuietly(tuple.getFirst());
		}

		return success;
	}

	private static void loadAddonAdvancements(AnimaniaAddon addon, Map<ResourceLocation, Advancement.Builder> map)
	{
		ModContainer mod = Loader.instance().activeModContainer();

		JsonContext ctx = new JsonContext(mod.getModId());

		findFiles(addon, mod, Animania.MODID + "/advancements", null, (root, file) -> {

			String relative = root.relativize(file).toString();
			if (!"json".equals(FilenameUtils.getExtension(file.toString())) || relative.startsWith("_"))
				return true;

			String name = FilenameUtils.removeExtension(relative).replaceAll("\\\\", "/");
			ResourceLocation key = new ResourceLocation(mod.getModId(), name);

			if (!map.containsKey(key))
			{
				BufferedReader reader = null;

				try
				{
					reader = Files.newBufferedReader(file);
					String contents = IOUtils.toString(reader);
					JsonObject json = JsonUtils.gsonDeserialize(CraftingHelper.GSON, contents, JsonObject.class);
					if (!CraftingHelper.processConditions(json, "conditions", ctx))
						return true;
					Advancement.Builder builder = JsonUtils.gsonDeserialize(AdvancementManager.GSON, contents, Advancement.Builder.class);
					map.put(key, builder);
				} catch (JsonParseException jsonparseexception)
				{
					FMLLog.log.error("Parsing error loading built-in advancement " + key, jsonparseexception);
					return false;
				} catch (IOException ioexception)
				{
					FMLLog.log.error("Couldn't read advancement " + key + " from " + file, ioexception);
					return false;
				} finally
				{
					IOUtils.closeQuietly(reader);
				}
			}

			return true;
		}, true, true);

	}

	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event)
	{
		World world = event.getWorld();
		if (!world.isRemote)
		{
			try
			{
				AdvancementManager manager = (AdvancementManager) advancementManager.get(world);
				AdvancementList list = (AdvancementList) ADVANCEMENT_LIST.get(manager);

				hasErrored.set(manager, false);
				list.clear();
				Map<ResourceLocation, Advancement.Builder> map = (Map<ResourceLocation, Builder>) loadCustomAdvancements.invoke(manager);
				loadBuildtInAdvancements.invoke(manager, map);
				boolean error = net.minecraftforge.common.ForgeHooks.loadAdvancements(map);
				if (error)
					hasErrored.set(manager, true);

				for (AnimaniaAddon addon : loadedAddons.values())
				{
					if (addon.getAddonID().equals("template"))
						continue;

					loadAddonAdvancements(addon, map);
				}

				list.loadAdvancements(map);

				for (Advancement advancement : list.getRoots())
				{
					if (advancement.getDisplay() != null)
					{
						AdvancementTreeNode.layout(advancement);
					}
				}

			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
	}
}
