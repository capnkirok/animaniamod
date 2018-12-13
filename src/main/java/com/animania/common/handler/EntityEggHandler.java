package com.animania.common.handler;

import java.util.HashMap;
import java.util.Map;

import com.animania.api.data.AnimalContainer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntityEggHandler
{

	public static Map<AnimalContainer, EntityEntry> ENTITY_MAP = new HashMap<AnimalContainer, EntityEntry>();
	
	public static EntityEntry getEntryFromEntity(Entity e)
	{
		ResourceLocation loc = EntityList.getKey(e);
		return ForgeRegistries.ENTITIES.getValue(loc);
	}
	
}
