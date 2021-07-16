package com.animania.common.loottables;

import java.util.List;
import java.util.Random;

import com.animania.Animania;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class WoolColorFunction extends LootFunction
{
	public WoolColorFunction(LootCondition[] conditions)
	{
		super(conditions);
	}

	public ItemStack apply(ItemStack stack, Random rand, LootContext context)
	{
		Entity e = context.getLootedEntity();

		if (e instanceof SheepEntity)
		{
			SheepEntity entityAnimaniaSheep = (SheepEntity) e;
			boolean isSheared = entityAnimaniaSheep.getSheared();
			
			List<ItemStack> stacks = entityAnimaniaSheep.onSheared(ItemStack.EMPTY, e.world, e.getPosition(), 0);
			if (stacks != null && !stacks.isEmpty())
			{
				if (isSheared)
					return ItemStack.EMPTY;

				ItemStack s = stacks.get(0);
				s.setCount(1);
				return s;
			}
		}

		return stack;

	}

	public static class Serializer extends LootFunction.Serializer<WoolColorFunction>
	{
		public Serializer()
		{
			super(new ResourceLocation(Animania.MODID, "wool_color"), WoolColorFunction.class);
		}

		public void serialize(JsonObject object, WoolColorFunction functionClazz, JsonSerializationContext serializationContext)
		{
		}

		public WoolColorFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootCondition[] conditionsIn)
		{
			return new WoolColorFunction(conditionsIn);
		}
	}
}