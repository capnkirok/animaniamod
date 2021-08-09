package com.animania.addons.extra;

import com.animania.Animania;
import com.animania.addons.extra.client.ExtraAddonRenderHandler;
import com.animania.addons.extra.common.events.CapabilityLoadHandler;
import com.animania.addons.extra.common.events.CarryInteractHandler;
import com.animania.addons.extra.common.events.CarryRenderer;
import com.animania.addons.extra.common.events.ExtraAddonSpawnHandler;
import com.animania.addons.extra.common.handler.ExtraAddonBlockHandler;
import com.animania.addons.extra.common.handler.ExtraAddonCapHandler;
import com.animania.addons.extra.common.handler.ExtraAddonCompatHandler;
import com.animania.addons.extra.common.handler.ExtraAddonCraftingHandler;
import com.animania.addons.extra.common.handler.ExtraAddonEntityHandler;
import com.animania.addons.extra.common.handler.ExtraAddonInjectionHandler;
import com.animania.addons.extra.common.handler.ExtraAddonItemHandler;
import com.animania.addons.extra.common.handler.ExtraAddonLootTableHandler;
import com.animania.addons.extra.common.handler.ExtraAddonOredictHandler;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.addons.extra.network.CapSyncPacket;
import com.animania.addons.extra.network.CapSyncPacketHandler;
import com.animania.api.addons.AnimaniaAddon;
import com.animania.api.addons.LoadAddon;
import com.animania.network.NetworkHandler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;

@LoadAddon
public class ExtraAddon implements AnimaniaAddon
{

	/*
	 * Registration Order: GUI Handler, Injections, Entities, Items, Blocks
	 */
	@Override
	public void preInitCommon()
	{
		MinecraftForge.EVENT_BUS.register(CapabilityLoadHandler.class);
		MinecraftForge.EVENT_BUS.register(CarryInteractHandler.class);
		MinecraftForge.EVENT_BUS.register(CarryRenderer.class);
		MinecraftForge.EVENT_BUS.register(ExtraAddonSpawnHandler.class);

		ExtraAddonInjectionHandler.preInit();
		ExtraAddonEntityHandler.preInit();
		ExtraAddonItemHandler.preInit();
		ExtraAddonBlockHandler.preInit();
		ExtraAddonSoundHandler.preInit();
		ExtraAddonLootTableHandler.preInit();
		ExtraAddonCompatHandler.preInit();
		ExtraAddonCapHandler.preInit();
	}

	@Override
	public void initCommon()
	{
		ExtraAddonOredictHandler.init();
		ExtraAddonCraftingHandler.init();

		Animania.network.registerMessage(CapSyncPacketHandler.class, CapSyncPacket.class, NetworkHandler.packetID++, Dist.CLIENT);
	}

	@Override
	public void preInitClient()
	{
		ExtraAddonRenderHandler.preInit();
	}

	@Override
	public void initClient()
	{
		ExtraAddonRenderHandler.init();
	}

	@Override
	public String getVersion()
	{
		return "GRADLE:extra_version";
	}

	@Override
	public String getAddonID()
	{
		return "extra";
	}

	@Override
	public String getAddonName()
	{
		return "Animania - Extra";
	}

	@Override
	public String getDependencies()
	{
		return "required-after:animania@[2.0.0,);required-after:minecraft@[1.12,1.13)";
	}

}
