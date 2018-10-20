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

public class EntityFedProperty implements EntityProperty	
{

	private final boolean isFed;

    public EntityFedProperty(boolean isFed)
    {
        this.isFed = isFed;
    }

    public boolean testProperty(Random random, Entity entityIn)
    {
        return entityIn instanceof IFoodEating ? ((IFoodEating)entityIn).getFed() == this.isFed : false;
    }

    public static class Serializer extends EntityProperty.Serializer<EntityFedProperty>
        {
            public Serializer()
            {
                super(new ResourceLocation(Animania.MODID, "fed"), EntityFedProperty.class);
            }

            public JsonElement serialize(EntityFedProperty property, JsonSerializationContext serializationContext)
            {
                return new JsonPrimitive(property.isFed);
            }

            public EntityFedProperty deserialize(JsonElement element, JsonDeserializationContext deserializationContext)
            {
                return new EntityFedProperty(JsonUtils.getBoolean(element, Animania.MODID + ":fed"));
            }
        }

}
