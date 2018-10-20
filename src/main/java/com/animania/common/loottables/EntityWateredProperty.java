package com.animania.common.loottables;

import java.util.Random;

import com.animania.Animania;
import com.animania.common.entities.interfaces.IFoodEating;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

import net.minecraft.entity.Entity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.properties.EntityOnFire;
import net.minecraft.world.storage.loot.properties.EntityProperty;

public class EntityWateredProperty implements EntityProperty	
{

	private final boolean isWatered;

    public EntityWateredProperty(boolean watered)
    {
        this.isWatered = watered;
    }

    public boolean testProperty(Random random, Entity entityIn)
    {
        return entityIn instanceof IFoodEating ? ((IFoodEating)entityIn).getWatered() == this.isWatered : false;
    }

    public static class Serializer extends EntityProperty.Serializer<EntityWateredProperty>
        {
            public Serializer()
            {
                super(new ResourceLocation(Animania.MODID, "watered"), EntityWateredProperty.class);
            }

            public JsonElement serialize(EntityWateredProperty property, JsonSerializationContext serializationContext)
            {
                return new JsonPrimitive(property.isWatered);
            }

            public EntityWateredProperty deserialize(JsonElement element, JsonDeserializationContext deserializationContext)
            {
                return new EntityWateredProperty(JsonUtils.getBoolean(element, Animania.MODID + ":watered"));
            }
        }

}
