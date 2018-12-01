package com.animania.addons.catsdogs.config;

import com.animania.Animania;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;

@Config(modid = Animania.MODID, name = "animania_cats_dogs")
public class CatsDogsConfig
{
	public static CatsDogsConfig.Settings catsdogs = new CatsDogsConfig.Settings();

	
	public static class Settings
	{
		@Comment("Food that cats can eat")
		public String[] catFood = new String[]
		{
				"minecraft:fish"
		};
		
		@Comment("Cat Bed Block Preferred")
		public String catBed = "animania:block_straw";
		
		@Comment("Cat Bed Block Backup")
		public String catBed2 = "minecraft:grass";

		@Comment("Spawn limit for cats")
		public int spawnLimitCats = 20;
		
		@Comment("Food that dogs can eat")
		public String[] dogFood = new String[]
		{
				"listAllbeefraw"
		};
		
		@Comment("Items that can go in a pet bowl")
		public String[] petBowlFood = new String[]
		{
				"minecraft:fish",
				"listAllbeefraw"
		};
	}	
	
}

