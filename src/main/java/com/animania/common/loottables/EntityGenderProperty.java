package com.animania.common.loottables;

import java.util.Random;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.IGendered;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

import net.minecraft.entity.Entity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.properties.EntityProperty;

public class EntityGenderProperty implements EntityProperty	
{

	private final boolean isMale;

    public EntityGenderProperty(boolean isMale)
    {
        this.isMale = isMale;
    }

    public boolean testProperty(Random random, Entity entityIn)
    {
        return entityIn instanceof IGendered ? ((IGendered)entityIn).getEntityGender() == EntityGender.MALE : false;
    }

    public static class Serializer extends EntityProperty.Serializer<EntityGenderProperty>
        {
            public Serializer()
            {
                super(new ResourceLocation(Animania.MODID, "is_male"), EntityGenderProperty.class);
            }

            public JsonElement serialize(EntityGenderProperty property, JsonSerializationContext serializationContext)
            {
                return new JsonPrimitive(property.isMale);
            }

            public EntityGenderProperty deserialize(JsonElement element, JsonDeserializationContext deserializationContext)
            {
                return new EntityGenderProperty(JsonUtils.getBoolean(element, Animania.MODID + ":is_male"));
            }
        }

}
