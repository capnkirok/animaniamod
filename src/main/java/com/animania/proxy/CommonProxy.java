package com.animania.proxy;

import com.animania.api.addons.AnimaniaAddon;
import com.animania.common.events.UpdateHandler;
import com.animania.common.handler.AddonHandler;
import com.animania.common.handler.AdvancementHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.handler.DictionaryHandler;
import com.animania.common.handler.DispenserHandler;
import com.animania.common.handler.EventsHandler;
import com.animania.common.handler.FoodValueHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.handler.LootTableHandler;
import com.animania.common.handler.PatreonHandler;
import com.animania.common.handler.RecipeHandler;
import com.animania.common.handler.TileEntityHandler;
import com.animania.network.NetworkHandler;

import net.minecraft.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
	public void Sleep(Player Player)
	{
		long currentTime = 0;
		int factorTime = 0;

		for (int j = 0; j < Player.level.getMinecraftServer().getServer().levels.length; ++j)
		{
			currentTime = Player.level.getMinecraftServer().getServer().levels[j].getLevelTime() % 24000;
			factorTime = 24000 - (int) currentTime;
			Player.level.getMinecraftServer().getServer().levels[j].setLevelTime(Player.level.getMinecraftServer().getServer().levels[j].getLevelTime() + factorTime);
		}
	}

	public void reloadManual()
	{
	}

}