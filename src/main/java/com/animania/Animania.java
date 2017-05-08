package com.animania;

import com.animania.common.creativeTab.TabAnimaniaEntities;
import com.animania.common.creativeTab.TabAnimaniaResources;
import com.animania.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Animania.MODID, name = Animania.NAME, version = Animania.VERSION, guiFactory = "com.animania.client.gui.GuiFactoryAnimania")
public class Animania {

	@SidedProxy(clientSide = "com.animania.proxy.ClientProxy", serverSide = "com.animania.proxy.ServerProxy")
	public static CommonProxy proxy;

	// Instance
	@Instance(Animania.MODID)
	public static Animania instance;

	public static final String MODID = "animania";
	public static final String VERSION = "1.0.4.7";
	public static final String NAME = "Animania";

	// Tabs
	public static CreativeTabs TabAnimaniaEggs = new TabAnimaniaEntities(CreativeTabs.getNextID(), "Animania");
	public static CreativeTabs TabAnimaniaResources = new TabAnimaniaResources(CreativeTabs.getNextID(), "Animania");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}

}