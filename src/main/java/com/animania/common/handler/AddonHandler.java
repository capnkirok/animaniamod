package com.animania.common.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.animania.Animania;
import com.animania.addons.AnimaniaAddon;
import com.animania.addons.LoadAddon;
import com.animania.addons.template.TemplateAddon;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.InvalidVersionSpecificationException;
import net.minecraftforge.fml.common.versioning.VersionRange;

public class AddonHandler
{

	private static List<AnimaniaAddon> loadedAddons = new ArrayList<AnimaniaAddon>();

	public static void preInitCommon()
	{
		for (AnimaniaAddon a : loadedAddons)
			a.preInitCommon();
	}

	public static void initCommon()
	{
		for (AnimaniaAddon a : loadedAddons)
			a.initCommon();
	}

	public static void preInitClient()
	{
		for (AnimaniaAddon a : loadedAddons)
			a.preInitClient();
	}

	public static void initClient()
	{
		for (AnimaniaAddon a : loadedAddons)
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
				register(a);
			}
			catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
			{
				Animania.LOGGER.error(e);
			}
		});
	}

	public static boolean isAddonLoaded(String addonID)
	{
		for (AnimaniaAddon a : loadedAddons)
		{
			if (a.getAddonID().equals(addonID))
			{
				return true;
			}
		}

		return false;
	}

	private static void register(AnimaniaAddon addon)
	{
		if (addon == null)
			return;
		
		if(addon instanceof TemplateAddon)
			return;

		try
		{
			VersionRange animaniaVersionRange = VersionRange.createFromVersionSpec(addon.getRequiredAnimaniaVersion());
			VersionRange minecraftVersionRange = VersionRange.createFromVersionSpec(addon.getRequiredMinecraftVersion());
			ArtifactVersion animaniaVersion = new DefaultArtifactVersion(Animania.VERSION);
			ArtifactVersion minecraftVersion = new DefaultArtifactVersion(MinecraftForge.MC_VERSION);

			String upper = "";
			if (!animaniaVersionRange.containsVersion(animaniaVersion))
				throw new AddonLoadException("Animania Version must be at least " + AnimaniaHelper.getLowerBound(animaniaVersionRange) + ((upper = AnimaniaHelper.getUpperBound(animaniaVersionRange)) == null ? "" : " and at most " + upper));

			if (!minecraftVersionRange.containsVersion(minecraftVersion))
				throw new AddonLoadException("Minecraft Version must be at least " + AnimaniaHelper.getLowerBound(minecraftVersionRange) + ((upper = AnimaniaHelper.getUpperBound(minecraftVersionRange)) == null ? "" : " and at most " + upper));
		}
		catch (InvalidVersionSpecificationException e)
		{
			e.printStackTrace();
		}

		for (AnimaniaAddon a : loadedAddons)
		{
			if (a.getAddonID().equals(addon.getAddonID()))
			{
				throw new AddonLoadException("The addon with the id " + addon.getAddonID() + " is already installed!");
			}
		}

		loadedAddons.add(addon);
		Animania.LOGGER.info("Loaded Addon " + addon.getAddonName() + " with id " + addon.getAddonID() + " and Class " + addon.getClass().getName());
	}

	public static class AddonLoadException extends RuntimeException
	{
		public AddonLoadException(String message)
		{
			super(message);
		}
	}
}
