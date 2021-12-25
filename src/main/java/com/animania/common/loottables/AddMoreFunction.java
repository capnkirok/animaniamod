package com.animania.common.loottables;

import java.util.Random;

import com.animania.Animania;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.RandomValueBounds;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.storage.loot.conditions.LootCondition;

public class AddMoreFunction extends LootItemConditionalFunction
{
	private final RandomValueBounds count;

	public AddMoreFunction(LootCondition[] conditions, RandomValueRange countIn)
	{
		super(conditions);
		this.count = countIn;
	}

	public ItemStack apply(ItemStack stack, Random rand, LootContext context)
	{

		float f = (float) this.count.generateFloat(rand);
		stack.grow(Math.round(f));
		return stack;

	}

	public static class Serializer extends LootFunction.Serializer<AddMoreFunction>
	{
		public Serializer()
		{
			super(new ResourceLocation(Animania.MODID, "add_more"), AddMoreFunction.class);
		}

		public void serialize(JsonObject object, AddMoreFunction functionClazz, JsonSerializationContext serializationContext)
		{
			object.add("count", serializationContext.serialize(functionClazz.count));
		}

		public AddMoreFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootCondition[] conditionsIn)
		{
			return new AddMoreFunction(conditionsIn, (RandomValueRange) JsonUtils.deserializeClass(object, "count", deserializationContext, RandomValueRange.class));
		}
	}
}