package com.animania.common.handler;

import net.minecraft.util.DamageSource;

public class DamageSourceHandler
{

	// DamageSource
	public static DamageSource pepeDamage;
	public static DamageSource beeDamage;
	public static DamageSource killerRabbitDamage;

	public static void preInit() {
		// DAMAGE
		DamageSourceHandler.pepeDamage = new DamageSource("pepe");
		DamageSourceHandler.beeDamage = new DamageSource("animania_bee");
		DamageSourceHandler.killerRabbitDamage = new DamageSource("killer_rabbit");

	}
}
