package com.animania.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.animania.Animania;
import com.animania.common.helper.ReflectionUtil;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Animania.MODID)
public class AnimaniaConfig
{
	@Config.LangKey("Animania")
	public static CommonConfig.GameRules gameRules = new CommonConfig.GameRules();
//	public static CommonConfig.Drops drops = new CommonConfig.Drops();
	public static CommonConfig.Spawn spawn = new CommonConfig.Spawn();
	@Config.RequiresMcRestart
	public static CommonConfig.SpawnLocations spawnLocations = new CommonConfig.SpawnLocations();
	public static CommonConfig.CareAndFeeding careAndFeeding = new CommonConfig.CareAndFeeding();
	public static CommonConfig.FoodValues foodValues = new CommonConfig.FoodValues();

	@Mod.EventBusSubscriber(modid = Animania.MODID)
	public static class EventHandler
	{

		/**
		 * The {@link Configuration} instance.
		 */
		private static List<Configuration> configuration;

		/**
		 * Get the {@link Configuration} instance from {@link ConfigManager}.
		 * <p>
		 * TODO: Use a less hackish method of getting the
		 * {@link Configuration}/{@link IConfigElement}s when possible.
		 *
		 * @return The Configuration instance
		 */
		public static List<Configuration> getConfiguration()
		{
			if (EventHandler.configuration == null)
				try
				{
					
					List<Configuration> cfgs = new ArrayList<Configuration>();
					
					@SuppressWarnings("unchecked")
					final Map<String, Configuration> configsMap = (Map<String, Configuration>) ReflectionUtil.findField(ConfigManager.class, "CONFIGS").get(null);

					final Stream<Map.Entry<String, Configuration>> entries = configsMap.entrySet().stream().filter(entry -> entry.getKey().substring(entry.getKey().lastIndexOf(File.separator), entry.getKey().length()-1).contains(Animania.MODID));

					entries.forEach(entry -> cfgs.add(entry.getValue()));
//					entries.ifPresent(stringConfigurationEntry -> EventHandler.configuration = stringConfigurationEntry.getValue());
				
					configuration = cfgs;
				}
				catch (Throwable throwable)
				{
					throwable.printStackTrace();
				}

			return EventHandler.configuration;
		}

		/**
		 * Inject the new values and save to the config file when the config has
		 * been changed from the GUI.
		 *
		 * @param event
		 *            The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if (event.getModID().equals(Animania.MODID))
			{
				ConfigManager.load(Animania.MODID, Config.Type.INSTANCE);
				Animania.proxy.reloadManual();
			}
		}
	}
}
