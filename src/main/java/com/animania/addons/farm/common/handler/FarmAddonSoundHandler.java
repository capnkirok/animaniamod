package com.animania.addons.farm.common.handler;

import com.animania.Animania;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class FarmAddonSoundHandler
{

	// Chickens
	public static SoundEvent chickenCrow1;
	public static SoundEvent chickenCrow2;
	public static SoundEvent chickenCrow3;
	public static SoundEvent chickenCluck1;
	public static SoundEvent chickenCluck2;
	public static SoundEvent chickenCluck3;
	public static SoundEvent chickenCluck4;
	public static SoundEvent chickenCluck5;
	public static SoundEvent chickenCluck6;
	public static SoundEvent chickenHurt1;
	public static SoundEvent chickenHurt2;
	public static SoundEvent chickenDeath1;
	public static SoundEvent chickenDeath2;

	// Cows
	public static SoundEvent angryBull1;
	public static SoundEvent angryBull2;
	public static SoundEvent angryBull3;
	public static SoundEvent bullMoo1;
	public static SoundEvent bullMoo2;
	public static SoundEvent bullMoo3;
	public static SoundEvent bullMoo4;
	public static SoundEvent bullMoo5;
	public static SoundEvent bullMoo6;
	public static SoundEvent bullMoo7;
	public static SoundEvent bullMoo8;
	public static SoundEvent cowDeath1;
	public static SoundEvent cowDeath2;
	public static SoundEvent cowEat1;
	public static SoundEvent cowEat2;
	public static SoundEvent hurtCalf1;
	public static SoundEvent hurtCalf2;
	public static SoundEvent cowHurt1;
	public static SoundEvent cowHurt2;
	public static SoundEvent mooCalf1;
	public static SoundEvent mooCalf2;
	public static SoundEvent mooCalf3;
	public static SoundEvent moo1;
	public static SoundEvent moo2;
	public static SoundEvent moo3;
	public static SoundEvent moo4;
	public static SoundEvent moo5;
	public static SoundEvent moo6;
	public static SoundEvent moo7;
	public static SoundEvent moo8;

	// Horses
	public static SoundEvent horsehurt1;
	public static SoundEvent horsehurt2;
	public static SoundEvent horsehurt3;
	public static SoundEvent horseliving1;
	public static SoundEvent horseliving2;
	public static SoundEvent horseliving3;
	public static SoundEvent horseliving4;
	public static SoundEvent horseliving5;
	public static SoundEvent horseliving6;

	// Pigs
	public static SoundEvent hog1;
	public static SoundEvent hog2;
	public static SoundEvent hog3;
	public static SoundEvent hog4;
	public static SoundEvent hog5;
	public static SoundEvent pig1;
	public static SoundEvent pig2;
	public static SoundEvent pig3;
	public static SoundEvent pig4;
	public static SoundEvent pig5;
	public static SoundEvent pig6;
	public static SoundEvent pig7;
	public static SoundEvent pigHurt1;
	public static SoundEvent pigHurt2;
	public static SoundEvent pigletHurt1;
	public static SoundEvent pigletHurt2;
	public static SoundEvent pigletHurt3;
	public static SoundEvent piglet1;
	public static SoundEvent piglet2;
	public static SoundEvent piglet3;

	// Sheep
	public static SoundEvent sheepHurt1;
	public static SoundEvent sheepLiving1;
	public static SoundEvent sheepLiving2;;
	public static SoundEvent sheepLiving3;
	public static SoundEvent sheepLiving4;
	public static SoundEvent sheepLiving5;
	public static SoundEvent sheepLiving6;
	public static SoundEvent sheepLiving7;
	public static SoundEvent lambLiving1;
	public static SoundEvent lambLiving2;

	// Goats
	public static SoundEvent goatLiving1;
	public static SoundEvent goatLiving2;;
	public static SoundEvent goatLiving3;
	public static SoundEvent goatLiving4;
	public static SoundEvent goatLiving5;
	public static SoundEvent kidLiving1;
	public static SoundEvent kidLiving2;;
	public static SoundEvent kidLiving3;
	public static SoundEvent goatHurt1;
	public static SoundEvent goatHurt2;
	public static SoundEvent kidHurt1;
	public static SoundEvent kidHurt2;

	// Props
	public static SoundEvent hitch;
	public static SoundEvent unhitch;

	public static void preInit()
	{
		// Chickens
		chickenCrow1 = registerSound("crow1");
		chickenCrow2 = registerSound("crow2");
		chickenCrow3 = registerSound("crow3");
		chickenCluck1 = registerSound("cluck1");
		chickenCluck2 = registerSound("cluck2");
		chickenCluck3 = registerSound("cluck3");
		chickenCluck4 = registerSound("cluck4");
		chickenCluck5 = registerSound("cluck5");
		chickenCluck6 = registerSound("cluck6");
		chickenHurt1 = registerSound("hurt1");
		chickenHurt2 = registerSound("hurt2");
		chickenDeath1 = registerSound("death1");
		chickenDeath2 = registerSound("death2");

		// Cows
		angryBull1 = registerSound("angryBull1");
		angryBull2 = registerSound("angryBull2");
		angryBull3 = registerSound("angryBull3");
		bullMoo1 = registerSound("bullMoo1");
		bullMoo2 = registerSound("bullMoo2");
		bullMoo3 = registerSound("bullMoo3");
		bullMoo4 = registerSound("bullMoo4");
		bullMoo5 = registerSound("bullMoo5");
		bullMoo6 = registerSound("bullMoo6");
		bullMoo7 = registerSound("bullMoo7");
		bullMoo8 = registerSound("bullMoo8");
		cowDeath1 = registerSound("cowDeath1");
		cowDeath2 = registerSound("cowDeath2");
		cowEat1 = registerSound("cowEat1");
		cowEat2 = registerSound("cowEat2");
		hurtCalf1 = registerSound("hurtCalf1");
		hurtCalf2 = registerSound("hurtCalf2");
		cowHurt1 = registerSound("cowHurt1");
		cowHurt2 = registerSound("cowHurt2");
		mooCalf1 = registerSound("mooCalf1");
		mooCalf2 = registerSound("mooCalf2");
		mooCalf3 = registerSound("mooCalf3");
		moo1 = registerSound("moo1");
		moo2 = registerSound("moo2");
		moo3 = registerSound("moo3");
		moo4 = registerSound("moo4");
		moo5 = registerSound("moo5");
		moo6 = registerSound("moo6");
		moo7 = registerSound("moo7");
		moo8 = registerSound("moo8");

		// Horses
		horsehurt1 = registerSound("horsehurt1");
		horsehurt2 = registerSound("horsehurt2");
		horsehurt3 = registerSound("horsehurt3");
		horseliving1 = registerSound("horseliving1");
		horseliving2 = registerSound("horseliving2");
		horseliving3 = registerSound("horseliving3");
		horseliving4 = registerSound("horseliving4");
		horseliving5 = registerSound("horseliving5");
		horseliving6 = registerSound("horseliving6");

		// Pigs
		hog1 = registerSound("hog1");
		hog2 = registerSound("hog2");
		hog3 = registerSound("hog3");
		hog4 = registerSound("hog4");
		hog5 = registerSound("hog5");
		pig1 = registerSound("pig1");
		pig2 = registerSound("pig2");
		pig3 = registerSound("pig3");
		pig4 = registerSound("pig4");
		pig5 = registerSound("pig5");
		pig6 = registerSound("pig6");
		pig7 = registerSound("pig7");
		pigHurt1 = registerSound("pigHurt1");
		pigHurt2 = registerSound("pigHurt2");
		pigletHurt1 = registerSound("pigletHurt1");
		pigletHurt2 = registerSound("pigletHurt2");
		pigletHurt3 = registerSound("pigletHurt3");
		piglet1 = registerSound("piglet1");
		piglet2 = registerSound("piglet2");
		piglet3 = registerSound("piglet3");

		// Sheep
		sheepHurt1 = registerSound("sheephurt1");
		sheepLiving1 = registerSound("sheepliving1");
		sheepLiving2 = registerSound("sheepliving2");
		sheepLiving3 = registerSound("sheepliving3");
		sheepLiving4 = registerSound("sheepliving4");
		sheepLiving5 = registerSound("sheepliving5");
		sheepLiving6 = registerSound("sheepliving6");
		lambLiving1 = registerSound("lambLiving1");
		lambLiving2 = registerSound("lambLiving2");

		// Goats
		goatLiving1 = registerSound("goatliving1");
		goatLiving2 = registerSound("goatliving2");
		goatLiving3 = registerSound("goatliving3");
		goatLiving4 = registerSound("goatliving4");
		goatLiving5 = registerSound("goatliving5");
		goatHurt1 = registerSound("goathurt1");
		goatHurt2 = registerSound("goathurt2");
		kidLiving1 = registerSound("kidLiving1");
		kidLiving2 = registerSound("kidLiving2");
		kidLiving3 = registerSound("kidLiving3");
		kidHurt1 = registerSound("kidhurt1");
		kidHurt2 = registerSound("kidhurt2");

		// Props
		hitch = registerSound("hitch");
		unhitch = registerSound("unhitch");

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
