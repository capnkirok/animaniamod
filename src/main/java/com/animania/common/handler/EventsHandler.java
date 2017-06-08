package com.animania.common.handler;

import com.animania.common.ModSoundEvents;
import com.animania.common.events.CapabilityLoadHandler;
import com.animania.common.events.EggThrowHandler;
import com.animania.common.events.EntityEventHandler;
import com.animania.common.events.EventMudDamageCanceller;
import com.animania.common.events.EventReplaceSpawnAnimals;
import com.animania.common.events.ItemSeedHandler;
import com.animania.common.events.LoginEventHandler;
import com.animania.common.events.RemoveVanillaSpawns;
import com.animania.common.events.VanillaEggHandler;

import net.minecraftforge.common.MinecraftForge;

public class EventsHandler
{

    public static void preInit() {
        ModSoundEvents.registerSounds();
        MinecraftForge.EVENT_BUS.register(new LoginEventHandler());
        MinecraftForge.EVENT_BUS.register(new ItemSeedHandler());
        MinecraftForge.EVENT_BUS.register(new EggThrowHandler());
        MinecraftForge.EVENT_BUS.register(new EventReplaceSpawnAnimals());
        MinecraftForge.EVENT_BUS.register(new EventMudDamageCanceller());
        MinecraftForge.EVENT_BUS.register(new RemoveVanillaSpawns());
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityLoadHandler());
        MinecraftForge.EVENT_BUS.register(new VanillaEggHandler());
    }
}
