package com.animania.common;

import com.animania.Animania;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSoundEvents {

	public static SoundEvent zap;
	public static SoundEvent combo;

	//Hamsters
	public static SoundEvent hamsterDeath;
	public static SoundEvent hamsterEat1;
	public static SoundEvent hamsterEat2;
	public static SoundEvent hamsterHurt1;
	public static SoundEvent hamsterLiving1;
	public static SoundEvent hamsterLiving2;
	public static SoundEvent hamsterLiving3;

	//Ferrets
	public static SoundEvent ferretHurt1;
	public static SoundEvent ferretLiving1;
	public static SoundEvent ferretLiving2;;
	public static SoundEvent ferretLiving3;
	public static SoundEvent ferretLiving4;
	public static SoundEvent ferretLiving5;
	public static SoundEvent ferretLiving6;

	//Hedgehogs
	public static SoundEvent hedgehogHurt1;
	public static SoundEvent hedgehogHurt2;
	public static SoundEvent hedgehogLiving1;
	public static SoundEvent hedgehogLiving2;;
	public static SoundEvent hedgehogLiving3;
	public static SoundEvent hedgehogLiving4;
	public static SoundEvent hedgehogLiving5;
	

	//Chickens
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

	//Cows
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

	//Peacocks
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

	//Pigs
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



	public static void registerSounds() {

		zap = registerSound("zap");	
		combo = registerSound("combo");	

		//Rodents
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
	
		//Chickens
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

		//Cows
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

		//Peacocks
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

		//Pigs
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

	}

	private static SoundEvent registerSound(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(Animania.modid, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}

}