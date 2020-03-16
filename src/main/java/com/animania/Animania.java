package com.animania;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.animania.common.creativeTab.TabAnimaniaEntities;
import com.animania.common.creativeTab.TabAnimaniaResources;
import com.animania.common.handler.AddonHandler;
import com.animania.common.handler.GuiHandlerAnimania;
import com.animania.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@EventBusSubscriber
@Mod(modid = Animania.MODID, name = Animania.NAME, version = Animania.VERSION, acceptedMinecraftVersions = Animania.ACCEPTED_VERSIONS, guiFactory = "com.animania.client.gui.GuiFactoryAnimania", dependencies = Animania.DEPENDENCIES)
public class Animania
{

	@SidedProxy(clientSide = "com.animania.proxy.ClientProxy", serverSide = "com.animania.proxy.ServerProxy")
	public static CommonProxy proxy;

	// Instance
	@Instance(Animania.MODID)
	public static Animania instance;

	public static final String MODID = "animania";
	public static final String VERSION = "GRADLE:VERSION";
	public static final String NAME = "Animania";
	public static final Logger LOGGER = LogManager.getFormatterLogger("Animania");
	public final static String ACCEPTED_VERSIONS = "[1.12,1.13)";
	public static final String DEPENDENCIES = "required-after:craftstudioapi;after:cofhcore;after:harvestcraft;after:natura;after:botania;after:biomesoplenty;after:twilightforest;after:aroma1997sdimension;after:openterraingenerator;before:thermalexpansion;required-after:forge@[14.23.5.2779,)";
	public static boolean IS_DEV = true;
	
	public static SimpleNetworkWrapper network;
	public static Random RANDOM = new Random();

	//GUI
	public static int horseCartGUI_ID = 0;
	public static int coveredWagonGUI_ID = 1;
	public static int tillerGUI_ID = 2;
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
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandlerAnimania);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		Animania.proxy.postInit(e);
	}

}