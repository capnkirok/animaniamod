package com.animania.addons;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
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

	public static class AddonRegistryEvent extends Event
	{
		public AddonRegistryEvent()
		{
		}

		public void register(AnimaniaAddon addon)
		{
			if (addon == null)
				return;
			
			try
			{
				VersionRange animaniaVersionRange = VersionRange.createFromVersionSpec(addon.getRequiredAnimaniaVersion());
				VersionRange minecraftVersionRange = VersionRange.createFromVersionSpec(addon.getRequiredMinecraftVersion());
				ArtifactVersion animaniaVersion = new DefaultArtifactVersion(Animania.VERSION);
				ArtifactVersion minecraftVersion = new DefaultArtifactVersion(MinecraftForge.MC_VERSION);
				
				String upper = "";
				if(!animaniaVersionRange.containsVersion(animaniaVersion))
					throw new AddonLoadException("Animania Version must be at least " + AnimaniaHelper.getLowerBound(animaniaVersionRange) + ((upper = AnimaniaHelper.getUpperBound(animaniaVersionRange)) == null ? "" : " and at most " + upper));
				
				
				if(!minecraftVersionRange.containsVersion(minecraftVersion))
					throw new AddonLoadException("Minecraft Version must be at least " + AnimaniaHelper.getLowerBound(minecraftVersionRange) + ((upper = AnimaniaHelper.getUpperBound(minecraftVersionRange)) == null ? "" : " and at most " + upper));
			}
			catch(InvalidVersionSpecificationException e)
			{
				e.printStackTrace();
			}
			
			loadedAddons.add(addon);
			Animania.LOGGER.info("Loaded Addon " + addon.getAddonName() + " with id " + addon.getAddonID());
		}
	}
	
	public static class AddonLoadException extends RuntimeException
	{
		public AddonLoadException(String message)
		{
			super(message);
		}
	}
}
