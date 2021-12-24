package com.animania.common.handler;

import java.util.HashMap;
import java.util.Map;

import com.animania.api.data.AnimalContainer;

import net.minecraft.entity.EntityList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.EntityEntry;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityEggHandler
{

	public static Map<AnimalContainer, EntityEntry> ENTITY_MAP = new HashMap<AnimalContainer, EntityEntry>();
	
	public static EntityEntry getEntryFromEntity(Entity e)
	{
		ResourceLocation loc = EntityList.getKey(e);
		return ForgeRegistries.ENTITIES.getValue(loc);
	}
	
}
