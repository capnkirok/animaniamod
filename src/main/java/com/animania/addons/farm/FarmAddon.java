package com.animania.addons.farm;

import com.animania.addons.farm.client.FarmAddonRenderHandler;
import com.animania.addons.farm.common.event.EggThrowHandler;
import com.animania.addons.farm.common.event.EventBeehiveDecorator;
import com.animania.addons.farm.common.event.EventMudDamageCanceller;
import com.animania.addons.farm.common.event.FarmAddonInteractHandler;
import com.animania.addons.farm.common.event.FarmAddonSpawnHandler;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.handler.FarmAddonCompatHandler;
import com.animania.addons.farm.common.handler.FarmAddonCraftingHandler;
import com.animania.addons.farm.common.handler.FarmAddonEntityHandler;
import com.animania.addons.farm.common.handler.FarmAddonGUIHandler;
import com.animania.addons.farm.common.handler.FarmAddonInjectionHandler;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.handler.FarmAddonLootTableHandler;
import com.animania.addons.farm.common.handler.FarmAddonOredictHandler;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.api.addons.AnimaniaAddon;
import com.animania.api.addons.IAddonGuiHandler;
import com.animania.api.addons.LoadAddon;
import com.animania.common.handler.AddonHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.UniversalBucket;

@LoadAddon
public class FarmAddon implements AnimaniaAddon
{
	public static IAddonGuiHandler guiHandler;

	@Override
	public void preInitCommon()
	{
		AddonHandler.registerAddonGuiHandler(this.getAddonID(), guiHandler = new FarmAddonGUIHandler());

		MinecraftForge.EVENT_BUS.register(EggThrowHandler.class);
		MinecraftForge.EVENT_BUS.register(EventBeehiveDecorator.class);
		MinecraftForge.EVENT_BUS.register(EventMudDamageCanceller.class);
		MinecraftForge.EVENT_BUS.register(FarmAddonInteractHandler.class);
		MinecraftForge.EVENT_BUS.register(FarmAddonSpawnHandler.class);

		FarmAddonInjectionHandler.preInit();
		FarmAddonEntityHandler.preInit();
		FarmAddonItemHandler.preInit();
		FarmAddonBlockHandler.preInit();
		FarmAddonCompatHandler.preInit();
		FarmAddonSoundHandler.preInit();
		FarmAddonLootTableHandler.preInit();
	}

	@Override
	public void initCommon()
	{
		FarmAddonCraftingHandler.init();
		FarmAddonOredictHandler.init();

		ItemHandler.addItemToTab(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkFriesian));
		ItemHandler.addItemToTab(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkHolstein));
		ItemHandler.addItemToTab(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkJersey));
		ItemHandler.addItemToTab(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkGoat));
		ItemHandler.addItemToTab(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkSheep));

	}

	@Override
	public void preInitClient()
	{
		FarmAddonRenderHandler.preInit();
	}

	@Override
	public void initClient()
	{
		FarmAddonRenderHandler.init();
	}

	@Override
	public String getVersion()
	{
		return "GRADLE:farm_version";
	}

	@Override
	public String getAddonID()
	{
		return "farm";
	}

	@Override
	public String getAddonName()
	{
		return "Animania - Farm";
	}

	@Override
	public String getDependencies()
	{
		return "required-after:animania@[2.0.0,);required-after:minecraft@[1.12,1.13)";
	}

}
