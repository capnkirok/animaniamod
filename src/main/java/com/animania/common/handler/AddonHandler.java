package com.animania.common.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.animania.Animania;
import com.animania.addons.template.TemplateAddon;
import com.animania.api.addons.AnimaniaAddon;
import com.animania.api.addons.LoadAddon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.CustomModLoadingErrorDisplayException;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MissingModsException;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.MultipleModsErrored;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.DependencyParser;
import net.minecraftforge.fml.common.versioning.DependencyParser.DependencyInfo;
import net.minecraftforge.fml.relauncher.Side;

public class AddonHandler
{

	private static HashMap<String, AnimaniaAddon> loadedAddons = new HashMap<String, AnimaniaAddon>();
	private static List<MissingModsException> missingModsExceptions = new ArrayList<MissingModsException>();

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

		asmData.forEach((asm) ->
		{

			try
			{
				Class<AnimaniaAddon> clazz = (Class<AnimaniaAddon>) Class.forName(asm.getClassName()).asSubclass(AnimaniaAddon.class);

				AnimaniaAddon a = clazz.newInstance();
				loadedAddons.put(a.getAddonID(), a);
			}
			catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
			{
				Animania.LOGGER.error(e);
			}
		});

		loadedAddons.values().forEach(addon ->
		{
			register(addon);
		});

	}

	public static void throwErrors()
	{
		if (!missingModsExceptions.isEmpty())
		{
			MultipleModsErrored errors = new MultipleModsErrored(Collections.EMPTY_LIST, missingModsExceptions);

			CustomModLoadingErrorDisplayException customEx = new CustomModLoadingErrorDisplayException("Addon Loading Errors", errors)
			{

				private GuiScreen screen;

				@Override
				public void initGui(GuiErrorScreen errorScreen, FontRenderer fontRenderer)
				{
					screen = errors.createGui();
					screen.setWorldAndResolution(Minecraft.getMinecraft(), errorScreen.width, errorScreen.height);
				}

				@Override
				public void drawScreen(GuiErrorScreen errorScreen, FontRenderer fontRenderer, int mouseRelX, int mouseRelY, float tickTime)
				{
					screen.drawScreen(mouseRelX, mouseRelY, tickTime);
				}

			};

			throw customEx;
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

		if (addon instanceof TemplateAddon)
			return;

		Map<String, ModContainer> loadedMods = Loader.instance().getIndexedModList();
		DependencyParser parser = new DependencyParser(addon.getAddonID(), Side.SERVER);
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
				}
				else
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

		for (AnimaniaAddon a : loadedAddons.values())
		{
			if (a.getAddonID().equals(addon.getAddonID()))
			{
				throw new RuntimeException("The addon with the id " + addon.getAddonID() + " is already installed!");
			}
		}

		addAddonResourcePack(addon);

		Animania.LOGGER.info("Loaded Addon " + addon.getAddonName() + " with id " + addon.getAddonID() + " and Class " + addon.getClass().getName());
	}

	private static void addAddonResourcePack(AnimaniaAddon addon)
	{
		Animania.proxy.addAddonResourcePack(addon);
	}
}
