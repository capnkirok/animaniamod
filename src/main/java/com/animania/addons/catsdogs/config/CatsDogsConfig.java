package com.animania.addons.catsdogs.config;

import com.animania.Animania;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;

//@Config(modid = Animania.MODID)
public class CatsDogsConfig
{
	public static CatsDogsConfig.Settings catsdogs = new CatsDogsConfig.Settings();

	
	public static class Settings
	{
		@Comment("Food that cats can eat")
		public static String[] catFood = new String[]
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
		public static String[] dogFood = new String[]
				{
				"minecraft:beef"
		};
	}	
	
}

