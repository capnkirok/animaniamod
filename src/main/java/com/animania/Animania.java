package com.animania;

import com.animania.core.init.BlockInit;
import com.animania.core.init.ItemInit;
import com.animania.proxy.ClientProxy;
import com.animania.proxy.ServerProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
//import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.forgespi.language.IModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfig;

import java.io.File;

@Mod(value = Animania.MODID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class Animania
{

	//public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public static final String MODID = "animania";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String DEPENDENCIES = "required-after:craftstudioapi;after:cofhcore;after:harvestcraft;" +
			"after:natura;after:botania;after:biomesoplenty;after:twilightforest;after:aroma1997sdimension;" +
			"after:openterraingenerator;before:thermalexpansion;required-after:forge@[14.23.5.2847,)";
	public static File CONFIGURATION_FILE;

	public static boolean FINGERPRINT_VIOLATED = false;

	public static SimpleChannel network;
	public static IModInfo info;

	public Animania()
	{
		// Register the setup method for modloading
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);


		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Configs.CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Configs.SERVER_CONFIG);

		info = ModLoadingContext.get().getActiveContainer().getModInfo();
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		RegistrationHandler.regCommonEvents();

		// Init
		RegistrationHandler.regOverrideList();
		RegistrationHandler.regCaps();

		proxy.setup(event);

	}

	@SubscribeEvent
	public static void onRegistry(RegistryEvent.Register<Item> event)
	{
		RegistrationHandler.regItems();

		event.getRegistry().register(RegistrationHandler.itemEntity);
		event.getRegistry().register(RegistrationHandler.itemTile);
	}

	public static final ItemGroup TabAnimaniaEntities = new ItemGroup("TabAnimaniaEntities") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack();
		}
	};
	public static final ItemGroup TabAnimaniaResources = new ItemGroup("TabAnimaniaResources") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack();
		}
	};

	/*


	@SidedProxy(clientSide = "com.animania.proxy.ClientProxy", serverSide = "com.animania.proxy.ServerProxy")
	public static CommonProxy proxy;

	// Instance
	@Instance(Animania.MODID)
	public static Animania instance;

	public static final String VERSION = "GRADLE:VERSION";
	public static final String NAME = "Animania";
	public static final Logger LOGGER = LogManager.getFormatterLogger("Animania");
	public final static String ACCEPTED_VERSIONS = "[1.12,1.13)";
	public static final String DEPENDENCIES = "required-after:craftstudioapi;after:cofhcore;after:harvestcraft;after:natura;after:botania;after:biomesoplenty;after:twilightforest;after:aroma1997sdimension;after:openterraingenerator;before:thermalexpansion;required-after:forge@[14.23.5.2847,)";
	public static boolean IS_DEV = true;

	public static SimpleNetworkWrapper network;
	public static Random RANDOM = new Random();

	// Gui
	private GuiHandlerAnimania guiHandlerAnimania = new GuiHandlerAnimania();

	// Tabs
	public static CreativeTabs TabAnimaniaEggs = new TabAnimaniaEntities(CreativeTabs.getNextID(), "Animania");
	public static CreativeTabs TabAnimaniaResources = new TabAnimaniaResources(CreativeTabs.getNextID(), "Animania");

	@EventHandler
	public void construction(FMLConstructionEvent event)
	{
		FluidRegistry.enableUniversalBucket();
		AddonHandler.loadAddons(event.getASMHarvestedData());
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Animania.proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Animania.proxy.init(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandlerAnimania);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		Animania.proxy.postInit(e);
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent e)
	{
		e.registerServerCommand(new AnimaniaCommand());
	}
*/
}