package com.animania.proxy;

import com.animania.api.addons.AnimaniaAddon;
import com.animania.common.capabilities.CapabilitiesPlayerStorage;
import com.animania.common.capabilities.CapabilityPlayer;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.events.UpdateHandler;
import com.animania.common.handler.AddonHandler;
import com.animania.common.handler.AdvancementHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.handler.DictionaryHandler;
import com.animania.common.handler.DispenserHandler;
import com.animania.common.handler.EntityHandler;
import com.animania.common.handler.EventsHandler;
import com.animania.common.handler.FoodValueHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.handler.LootTableHandler;
import com.animania.common.handler.PatreonHandler;
import com.animania.common.handler.RecipeHandler;
import com.animania.common.handler.TileEntityHandler;
import com.animania.common.loottables.AddMoreFunction;
import com.animania.common.loottables.EntityFedProperty;
import com.animania.common.loottables.EntityWateredProperty;
import com.animania.network.NetworkHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraft.world.storage.loot.properties.EntityPropertyManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{

	public void preInit(FMLPreInitializationEvent event)
	{
		CapabilityManager.INSTANCE.register(ICapabilityPlayer.class, new CapabilitiesPlayerStorage(), CapabilityPlayer.class);
		EntityHandler.preInit();
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
		//AnimaniaAchievements.init();

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
	{}

	public void registerCraftStudioAnimations()
	{}
	
	public void openManualGui(ItemStack stack)
	{
	}
	
	public void addAddonResourcePack(AnimaniaAddon addon)
	{
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	//Sleep
	public void Sleep(EntityPlayer entityplayer) {
		long currentTime = 0;
		int factorTime = 0;

		for (int j = 0; j < entityplayer.world.getMinecraftServer().getServer().worlds.length; ++j)
		{
			currentTime = entityplayer.world.getMinecraftServer().getServer().worlds[j].getWorldTime() %24000;
			factorTime = 24000 - (int)currentTime;
			entityplayer.world.getMinecraftServer().getServer().worlds[j].setWorldTime(entityplayer.world.getMinecraftServer().getServer().worlds[j].getWorldTime() + factorTime) ;
		}
	}

	public void reloadManual()
	{		
	}

}