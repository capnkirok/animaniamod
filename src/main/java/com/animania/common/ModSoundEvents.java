package com.animania.common;

import com.animania.Animania;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSoundEvents
{

    public static SoundEvent zap;
    public static SoundEvent combo;

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
    public static SoundEvent ferretLiving2;;
    public static SoundEvent ferretLiving3;
    public static SoundEvent ferretLiving4;
    public static SoundEvent ferretLiving5;
    public static SoundEvent ferretLiving6;

    // Hedgehogs
    public static SoundEvent hedgehogHurt1;
    public static SoundEvent hedgehogHurt2;
    public static SoundEvent hedgehogLiving1;
    public static SoundEvent hedgehogLiving2;;
    public static SoundEvent hedgehogLiving3;
    public static SoundEvent hedgehogLiving4;
    public static SoundEvent hedgehogLiving5;

    // Amphibians
    public static SoundEvent frogLiving1;
    public static SoundEvent frogLiving2;
    public static SoundEvent frogLiving3;
    public static SoundEvent toadLiving1;
    public static SoundEvent toadLiving2;;
    public static SoundEvent toadLiving3;
    public static SoundEvent toadLiving4;
    public static SoundEvent dartfrogLiving1;
    public static SoundEvent dartfrogLiving2;
    public static SoundEvent dartfrogLiving3;
    public static SoundEvent dartfrogLiving4;
    public static SoundEvent reeee;

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

    public static void registerSounds() {

        ModSoundEvents.zap = registerSound("zap");
        ModSoundEvents.combo = registerSound("combo");

        // Rodents
        ModSoundEvents.hamsterDeath = registerSound("hamsterDeath");
        ModSoundEvents.hamsterEat1 = registerSound("hamsterEat1");
        ModSoundEvents.hamsterEat2 = registerSound("hamsterEat2");
        ModSoundEvents.hamsterHurt1 = registerSound("hamsterHurt1");
        ModSoundEvents.hamsterLiving1 = registerSound("hamsterLiving1");
        ModSoundEvents.hamsterLiving2 = registerSound("hamsterLiving2");
        ModSoundEvents.hamsterLiving3 = registerSound("hamsterLiving3");

        ModSoundEvents.ferretHurt1 = registerSound("ferretHurt1");
        ModSoundEvents.ferretLiving1 = registerSound("ferretLiving1");
        ModSoundEvents.ferretLiving2 = registerSound("ferretLiving2");
        ModSoundEvents.ferretLiving3 = registerSound("ferretLiving3");
        ModSoundEvents.ferretLiving4 = registerSound("ferretLiving4");
        ModSoundEvents.ferretLiving5 = registerSound("ferretLiving5");
        ModSoundEvents.ferretLiving6 = registerSound("ferretLiving6");

        ModSoundEvents.hedgehogHurt1 = registerSound("hedgehogHurt1");
        ModSoundEvents.hedgehogLiving1 = registerSound("hedgehogLiving1");
        ModSoundEvents.hedgehogLiving2 = registerSound("hedgehogLiving2");
        ModSoundEvents.hedgehogLiving3 = registerSound("hedgehogLiving3");
        ModSoundEvents.hedgehogLiving4 = registerSound("hedgehogLiving4");
        ModSoundEvents.hedgehogLiving5 = registerSound("hedgehogLiving5");

        // Amphibians
        ModSoundEvents.frogLiving1 = registerSound("frogLiving1");
        ModSoundEvents.frogLiving2 = registerSound("frogLiving2");
        ModSoundEvents.frogLiving3 = registerSound("frogLiving3");
        ModSoundEvents.toadLiving1 = registerSound("toadLiving1");
        ModSoundEvents.toadLiving2 = registerSound("toadLiving2");
        ModSoundEvents.toadLiving3 = registerSound("toadLiving3");
        ModSoundEvents.toadLiving4 = registerSound("toadLiving4");
        ModSoundEvents.dartfrogLiving1 = registerSound("dartfrogLiving1");
        ModSoundEvents.dartfrogLiving2 = registerSound("dartfrogLiving2");
        ModSoundEvents.dartfrogLiving3 = registerSound("dartfrogLiving3");
        ModSoundEvents.dartfrogLiving4 = registerSound("dartfrogLiving4");
        ModSoundEvents.reeee = registerSound("reeee");

        // Chickens
        ModSoundEvents.chickenCrow1 = registerSound("crow1");
        ModSoundEvents.chickenCrow2 = registerSound("crow2");
        ModSoundEvents.chickenCrow3 = registerSound("crow3");
        ModSoundEvents.chickenCluck1 = registerSound("cluck1");
        ModSoundEvents.chickenCluck2 = registerSound("cluck2");
        ModSoundEvents.chickenCluck3 = registerSound("cluck3");
        ModSoundEvents.chickenCluck4 = registerSound("cluck4");
        ModSoundEvents.chickenCluck5 = registerSound("cluck5");
        ModSoundEvents.chickenCluck6 = registerSound("cluck6");
        ModSoundEvents.chickenHurt1 = registerSound("hurt1");
        ModSoundEvents.chickenHurt2 = registerSound("hurt2");
        ModSoundEvents.chickenDeath1 = registerSound("death1");
        ModSoundEvents.chickenDeath2 = registerSound("death2");

        // Cows
        ModSoundEvents.angryBull1 = registerSound("angryBull1");
        ModSoundEvents.angryBull2 = registerSound("angryBull2");
        ModSoundEvents.angryBull3 = registerSound("angryBull3");
        ModSoundEvents.bullMoo1 = registerSound("bullMoo1");
        ModSoundEvents.bullMoo2 = registerSound("bullMoo2");
        ModSoundEvents.bullMoo3 = registerSound("bullMoo3");
        ModSoundEvents.bullMoo4 = registerSound("bullMoo4");
        ModSoundEvents.bullMoo5 = registerSound("bullMoo5");
        ModSoundEvents.bullMoo6 = registerSound("bullMoo6");
        ModSoundEvents.bullMoo7 = registerSound("bullMoo7");
        ModSoundEvents.bullMoo8 = registerSound("bullMoo8");
        ModSoundEvents.cowDeath1 = registerSound("cowDeath1");
        ModSoundEvents.cowDeath2 = registerSound("cowDeath2");
        ModSoundEvents.cowEat1 = registerSound("cowEat1");
        ModSoundEvents.cowEat2 = registerSound("cowEat2");
        ModSoundEvents.hurtCalf1 = registerSound("hurtCalf1");
        ModSoundEvents.hurtCalf2 = registerSound("hurtCalf2");
        ModSoundEvents.cowHurt1 = registerSound("cowHurt1");
        ModSoundEvents.cowHurt2 = registerSound("cowHurt2");
        ModSoundEvents.mooCalf1 = registerSound("mooCalf1");
        ModSoundEvents.mooCalf2 = registerSound("mooCalf2");
        ModSoundEvents.mooCalf3 = registerSound("mooCalf3");
        ModSoundEvents.moo1 = registerSound("moo1");
        ModSoundEvents.moo2 = registerSound("moo2");
        ModSoundEvents.moo3 = registerSound("moo3");
        ModSoundEvents.moo4 = registerSound("moo4");
        ModSoundEvents.moo5 = registerSound("moo5");
        ModSoundEvents.moo6 = registerSound("moo6");
        ModSoundEvents.moo7 = registerSound("moo7");
        ModSoundEvents.moo8 = registerSound("moo8");

        // Peacocks
        ModSoundEvents.peacock1 = registerSound("peacock1");
        ModSoundEvents.peacock2 = registerSound("peacock2");
        ModSoundEvents.peacock3 = registerSound("peacock3");
        ModSoundEvents.peacock4 = registerSound("peacock4");
        ModSoundEvents.peacock5 = registerSound("peacock5");
        ModSoundEvents.peacock6 = registerSound("peacock6");
        ModSoundEvents.peacock7 = registerSound("peacock7");
        ModSoundEvents.peacock8 = registerSound("peacock8");
        ModSoundEvents.peacock9 = registerSound("peacock9");
        ModSoundEvents.peacock10 = registerSound("peacock10");
        ModSoundEvents.peacockHurt1 = registerSound("peacockHurt1");
        ModSoundEvents.peacockHurt2 = registerSound("peacockHurt2");

        // Pigs
        ModSoundEvents.hog1 = registerSound("hog1");
        ModSoundEvents.hog2 = registerSound("hog2");
        ModSoundEvents.hog3 = registerSound("hog3");
        ModSoundEvents.hog4 = registerSound("hog4");
        ModSoundEvents.hog5 = registerSound("hog5");
        ModSoundEvents.pig1 = registerSound("pig1");
        ModSoundEvents.pig2 = registerSound("pig2");
        ModSoundEvents.pig3 = registerSound("pig3");
        ModSoundEvents.pig4 = registerSound("pig4");
        ModSoundEvents.pig5 = registerSound("pig5");
        ModSoundEvents.pig6 = registerSound("pig6");
        ModSoundEvents.pig7 = registerSound("pig7");
        ModSoundEvents.pigHurt1 = registerSound("pigHurt1");
        ModSoundEvents.pigHurt2 = registerSound("pigHurt2");
        ModSoundEvents.pigletHurt1 = registerSound("pigletHurt1");
        ModSoundEvents.pigletHurt2 = registerSound("pigletHurt2");
        ModSoundEvents.pigletHurt3 = registerSound("pigletHurt3");
        ModSoundEvents.piglet1 = registerSound("piglet1");
        ModSoundEvents.piglet2 = registerSound("piglet2");
        ModSoundEvents.piglet3 = registerSound("piglet3");

    }

    private static SoundEvent registerSound(String soundName) {
        final ResourceLocation soundID = new ResourceLocation(Animania.MODID, soundName);
        return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
    }

}