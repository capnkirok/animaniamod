package com.animania.config;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.util.Map;
import java.util.Optional;

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

    public static CommonConfig.GameRules      gameRules      = new CommonConfig.GameRules();
    public static CommonConfig.Drops          drops          = new CommonConfig.Drops();
    public static CommonConfig.Spawn          spawn          = new CommonConfig.Spawn();
    public static CommonConfig.CareAndFeeding careAndFeeding = new CommonConfig.CareAndFeeding();
    public static CommonConfig.FoodValues 	  foodValues 	 = new CommonConfig.FoodValues();

    @Mod.EventBusSubscriber(modid = Animania.MODID)
    public static class EventHandler
    {

        /**
         * The {@link ConfigManager#CONFIGS} getter.
         */
        private static final MethodHandle CONFIGS_GETTER = ReflectionUtil.findFieldGetter(ConfigManager.class, "CONFIGS");

        /**
         * The {@link Configuration} instance.
         */
        private static Configuration      configuration;

        /**
         * Get the {@link Configuration} instance from {@link ConfigManager}.
         * <p>
         * TODO: Use a less hackish method of getting the
         * {@link Configuration}/{@link IConfigElement}s when possible.
         *
         * @return The Configuration instance
         */
        public static Configuration getConfiguration() {
            if (EventHandler.configuration == null)
                try {
                    final String fileName = Animania.MODID + ".cfg";

                    @SuppressWarnings("unchecked")
                    final Map<String, Configuration> configsMap = (Map<String, Configuration>) EventHandler.CONFIGS_GETTER.invokeExact();

                    final Optional<Map.Entry<String, Configuration>> entryOptional = configsMap.entrySet().stream()
                            .filter(entry -> fileName.equals(new File(entry.getKey()).getName())).findFirst();

                    entryOptional.ifPresent(stringConfigurationEntry -> EventHandler.configuration = stringConfigurationEntry.getValue());
                } catch (Throwable throwable) {
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
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Animania.MODID))
                ConfigManager.load(Animania.MODID, Config.Type.INSTANCE);
        }
    }
}
