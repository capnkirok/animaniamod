package com.animania.proxy;

import com.animania.api.addons.AnimaniaAddon;
import com.animania.common.events.UpdateHandler;
import com.animania.common.handler.*;
import com.animania.network.NetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.MultipleModsErrored;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{

	public void preInit(FMLPreInitializationEvent event)
	{
		AddonHandler.throwErrors();

		ItemHandler.preInit();
		BlockHandler.preInit();
		TileEntityHandler.preInit();
		DamageSourceHandler.preInit();
		PatreonHandler.initList();
		NetworkHandler.init();
		LootTableHandler.preInit();

		// EVENTS
		EventsHandler.preInit();
		UpdateHandler.init();
		// AnimaniaAchievements.init();

		CompatHandler.preInit();
		AdvancementHandler.registerCriteria();
		AddonHandler.preInitCommon();

	}

	public void init(FMLInitializationEvent event)
	{
		DictionaryHandler.init();
		RecipeHandler.init();
		DispenserHandler.init();
		FoodValueHandler.init();

		AddonHandler.initCommon();
	}

	public void postInit(FMLPostInitializationEvent event)
	{
	}

	public void registerFluidBlockRendering(Block block, String name)
	{

	}

	public void registerCraftStudioModels()
	{
	}

	public void registerCraftStudioAnimations()
	{
	}

	public void openManualGui(ItemStack stack)
	{
	}

	public void addAddonResourcePack(AnimaniaAddon addon)
	{
	}

	public void throwCustomModLoadingErrorDisplayException(MultipleModsErrored errors)
	{
		throw errors;
	}

	// Sleep
	public void Sleep(PlayerEntity PlayerEntity)
	{
		long currentTime = 0;
		int factorTime = 0;

		for (int j = 0; j < Playerentity.level.getMinecraftServer().getServer().worlds.length; ++j)
		{
			currentTime = Playerentity.level.getMinecraftServer().getServer().worlds[j].getWorldTime() % 24000;
			factorTime = 24000 - (int) currentTime;
			Playerentity.level.getMinecraftServer().getServer().worlds[j].setWorldTime(Playerentity.level.getMinecraftServer().getServer().worlds[j].getWorldTime() + factorTime);
		}
	}

	public void reloadManual()
	{
	}

}