package com.animania.addons.extra.common.handler;

import com.animania.Animania;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtraAddonSoundHandler
{

	// Hamsters
	public static SoundEvent hamsterDeath;
	public static SoundEvent hamsterEat1;
	public static SoundEvent hamsterEat2;
	public static SoundEvent hamsterHurt1;
	public static SoundEvent hamsterLiving1;
	public static SoundEvent hamsterLiving2;
	public static SoundEvent hamsterLiving3;

	// Ferrets
	public static SoundEvent ferretHurt1;
	public static SoundEvent ferretLiving1;
	public static SoundEvent ferretLiving2;
	public static SoundEvent ferretLiving3;
	public static SoundEvent ferretLiving4;
	public static SoundEvent ferretLiving5;
	public static SoundEvent ferretLiving6;

	// Hedgehogs
	public static SoundEvent hedgehogHurt1;
	public static SoundEvent hedgehogHurt2;
	public static SoundEvent hedgehogLiving1;
	public static SoundEvent hedgehogLiving2;
	public static SoundEvent hedgehogLiving3;
	public static SoundEvent hedgehogLiving4;
	public static SoundEvent hedgehogLiving5;

	// Amphibians
	public static SoundEvent frogLiving1;
	public static SoundEvent frogLiving2;
	public static SoundEvent frogLiving3;
	public static SoundEvent toadLiving1;
	public static SoundEvent toadLiving2;
	public static SoundEvent toadLiving3;
	public static SoundEvent toadLiving4;
	public static SoundEvent dartfrogLiving1;
	public static SoundEvent dartfrogLiving2;
	public static SoundEvent dartfrogLiving3;
	public static SoundEvent dartfrogLiving4;
	public static SoundEvent reeee;
	public static SoundEvent oooohhh;

	// Peacocks
	public static SoundEvent peacock1;
	public static SoundEvent peacock2;
	public static SoundEvent peacock3;
	public static SoundEvent peacock4;
	public static SoundEvent peacock5;
	public static SoundEvent peacock6;
	public static SoundEvent peacock7;
	public static SoundEvent peacock8;
	public static SoundEvent peacock9;
	public static SoundEvent peacock10;
	public static SoundEvent peacockHurt1;
	public static SoundEvent peacockHurt2;

	// Rabbits
	public static SoundEvent rabbit1;
	public static SoundEvent rabbit2;
	public static SoundEvent rabbit3;
	public static SoundEvent rabbit4;
	public static SoundEvent rabbitHurt1;
	public static SoundEvent rabbitHurt2;

	public static void preInit()
	{
		// Rodents
		hamsterDeath = registerSound("hamsterDeath");
		hamsterEat1 = registerSound("hamsterEat1");
		hamsterEat2 = registerSound("hamsterEat2");
		hamsterHurt1 = registerSound("hamsterHurt1");
		hamsterLiving1 = registerSound("hamsterLiving1");
		hamsterLiving2 = registerSound("hamsterLiving2");
		hamsterLiving3 = registerSound("hamsterLiving3");

		ferretHurt1 = registerSound("ferretHurt1");
		ferretLiving1 = registerSound("ferretLiving1");
		ferretLiving2 = registerSound("ferretLiving2");
		ferretLiving3 = registerSound("ferretLiving3");
		ferretLiving4 = registerSound("ferretLiving4");
		ferretLiving5 = registerSound("ferretLiving5");
		ferretLiving6 = registerSound("ferretLiving6");

		hedgehogHurt1 = registerSound("hedgehogHurt1");
		hedgehogLiving1 = registerSound("hedgehogLiving1");
		hedgehogLiving2 = registerSound("hedgehogLiving2");
		hedgehogLiving3 = registerSound("hedgehogLiving3");
		hedgehogLiving4 = registerSound("hedgehogLiving4");
		hedgehogLiving5 = registerSound("hedgehogLiving5");

		rabbitHurt1 = registerSound("rabbitHurt1");
		rabbitHurt2 = registerSound("rabbitHurt2");
		rabbit1 = registerSound("rabbit1");
		rabbit2 = registerSound("rabbit2");
		rabbit3 = registerSound("rabbit3");
		rabbit4 = registerSound("rabbit4");

		// Amphibians
		frogLiving1 = registerSound("frogLiving1");
		frogLiving2 = registerSound("frogLiving2");
		frogLiving3 = registerSound("frogLiving3");
		toadLiving1 = registerSound("toadLiving1");
		toadLiving2 = registerSound("toadLiving2");
		toadLiving3 = registerSound("toadLiving3");
		toadLiving4 = registerSound("toadLiving4");
		dartfrogLiving1 = registerSound("dartfrogLiving1");
		dartfrogLiving2 = registerSound("dartfrogLiving2");
		dartfrogLiving3 = registerSound("dartfrogLiving3");
		dartfrogLiving4 = registerSound("dartfrogLiving4");
		reeee = registerSound("reeee");
		oooohhh = registerSound("ooooohh");

		// Peacocks
		peacock1 = registerSound("peacock1");
		peacock2 = registerSound("peacock2");
		peacock3 = registerSound("peacock3");
		peacock4 = registerSound("peacock4");
		peacock5 = registerSound("peacock5");
		peacock6 = registerSound("peacock6");
		peacock7 = registerSound("peacock7");
		peacock8 = registerSound("peacock8");
		peacock9 = registerSound("peacock9");
		peacock10 = registerSound("peacock10");
		peacockHurt1 = registerSound("peacockHurt1");
		peacockHurt2 = registerSound("peacockHurt2");
	}

	private static SoundEvent registerSound(String soundName)
	{
		final ResourceLocation soundID = new ResourceLocation(Animania.MODID, soundName);
		SoundEvent s = new SoundEvent(soundID);
		s.setRegistryName(soundID);
		ForgeRegistries.SOUND_EVENTS.register(s);
		return s;
	}
}
