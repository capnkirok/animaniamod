package com.animania.proxy;

import com.animania.common.capabilities.CapabilitiesPlayerStorage;
import com.animania.common.capabilities.CapabilityPlayer;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.events.UpdateHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.handler.DictionaryHandler;
import com.animania.common.handler.DispenserHandler;
import com.animania.common.handler.EntityHandler;
import com.animania.common.handler.EventsHandler;
import com.animania.common.handler.FoodValueHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.handler.PatreonHandler;
import com.animania.common.handler.RecipeHandler;
import com.animania.common.handler.TileEntityHandler;
import com.animania.network.NetworkHandler;

import net.minecraft.block.Block;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy
{

    public void preInit()
    {
        CapabilityManager.INSTANCE.register(ICapabilityPlayer.class, new CapabilitiesPlayerStorage(), CapabilityPlayer.class);
        EntityHandler.preInit();
        ItemHandler.preInit();
        BlockHandler.preInit();
        TileEntityHandler.preInit();
        DamageSourceHandler.preInit();
        PatreonHandler.initList();
        NetworkHandler.init();

        // EVENTS
        EventsHandler.preInit();
        UpdateHandler.init();
        //AnimaniaAchievements.init();

        CompatHandler.preInit();

    }

    public void init()
    {
        DictionaryHandler.init();
        RecipeHandler.init();
        DispenserHandler.init();
        FoodValueHandler.init();


    }
    

    public void registerFluidBlockRendering(Block block, String name)
    {

    }

    public void registerCraftStudioModels()
    {}

    public void registerCraftStudioAnimations()
    {}

}