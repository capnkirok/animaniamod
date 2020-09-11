package com.animania.common.advancements.criterion;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.animania.common.helper.AnimaniaHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class FeedAnimalTrigger implements ICriterionTrigger<FeedAnimalTrigger.Instance>
{
	private static final ResourceLocation ID = new ResourceLocation("animania", "feed_animal");
	private final Map<PlayerAdvancements, FeedAnimalTrigger.Listeners> listeners = Maps.<PlayerAdvancements, FeedAnimalTrigger.Listeners> newHashMap();

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<FeedAnimalTrigger.Instance> listener)
	{
		FeedAnimalTrigger.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

		if (consumeitemtrigger$listeners == null)
		{
			consumeitemtrigger$listeners = new FeedAnimalTrigger.Listeners(playerAdvancementsIn);
			this.listeners.put(playerAdvancementsIn, consumeitemtrigger$listeners);
		}

		consumeitemtrigger$listeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<FeedAnimalTrigger.Instance> listener)
	{
		FeedAnimalTrigger.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

		if (consumeitemtrigger$listeners != null)
		{
			consumeitemtrigger$listeners.remove(listener);

			if (consumeitemtrigger$listeners.isEmpty())
			{
				this.listeners.remove(playerAdvancementsIn);
			}
		}
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
	{
		this.listeners.remove(playerAdvancementsIn);
	}

	@Override
	public FeedAnimalTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
	{
		ItemStack item = null;
		EntityEntry entity = null;
		boolean useOptional = false;

		ResourceLocation entityloc = new ResourceLocation(JsonUtils.getString(json, "entity"));

		if (json.has("itemstack"))
		{
			item = AnimaniaHelper.getItemStack(JsonUtils.getJsonObject(json, "itemstack"));
		} else if (json.has("optional"))
		{
			try
			{
				item = AnimaniaHelper.getItemStack(JsonUtils.getJsonObject(json, "optional"));
			} catch (Exception e)
			{
				item = ItemStack.EMPTY;
			}
			useOptional = true;
		}

		if (item.isEmpty() && !useOptional)
			throw new JsonSyntaxException("Item cannot be air '" + JsonUtils.getJsonObject(json, "item").toString() + "'");

		if (!ForgeRegistries.ENTITIES.containsKey(entityloc))
			throw new JsonSyntaxException("Unknown entity '" + entityloc + "'");
		entity = ForgeRegistries.ENTITIES.getValue(entityloc);

		return new FeedAnimalTrigger.Instance(item, entity, useOptional);
	}

	public static class Instance extends AbstractCriterionInstance
	{
		private final ItemStack item;
		private final EntityEntry entity;
		private final boolean optional;

		public Instance(@Nonnull ItemStack item, @Nullable EntityEntry entity, boolean useOptional)
		{
			super(FeedAnimalTrigger.ID);
			this.item = item;
			this.entity = entity;
			this.optional = useOptional;
		}

		public boolean test(ItemStack item, EntityEntry entry)
		{
			if (this.item.isEmpty() && optional)
				return false;

			if (item != null && this.item != null)
			{
				ItemStack s1 = this.item.copy();
				ItemStack s2 = item.copy();
				s1.setCount(1);
				s2.setCount(1);
				boolean equal = ItemStack.areItemStacksEqual(s1, s2);
				if (this.entity != null && equal && entry == this.entity)
					return true;
			}

			return false;
		}

	}

	public void trigger(EntityPlayerMP player, ItemStack item, EntityEntry entry)
	{
		FeedAnimalTrigger.Listeners enterblocktrigger$listeners = this.listeners.get(player.getAdvancements());

		if (enterblocktrigger$listeners != null)
		{
			enterblocktrigger$listeners.trigger(item, entry);
		}
	}

	static class Listeners
	{
		private final PlayerAdvancements playerAdvancements;
		private final Set<ICriterionTrigger.Listener<FeedAnimalTrigger.Instance>> listeners = Sets.<ICriterionTrigger.Listener<FeedAnimalTrigger.Instance>> newHashSet();

		public Listeners(PlayerAdvancements playerAdvancementsIn)
		{
			this.playerAdvancements = playerAdvancementsIn;
		}

		public boolean isEmpty()
		{
			return this.listeners.isEmpty();
		}

		public void add(ICriterionTrigger.Listener<FeedAnimalTrigger.Instance> listener)
		{
			this.listeners.add(listener);
		}

		public void remove(ICriterionTrigger.Listener<FeedAnimalTrigger.Instance> listener)
		{
			this.listeners.remove(listener);
		}

		public void trigger(ItemStack item, EntityEntry entry)
		{
			List<ICriterionTrigger.Listener<FeedAnimalTrigger.Instance>> list = null;

			for (ICriterionTrigger.Listener<FeedAnimalTrigger.Instance> listener : this.listeners)
			{
				if (listener.getCriterionInstance().test(item, entry))
				{
					if (list == null)
					{
						list = Lists.<ICriterionTrigger.Listener<FeedAnimalTrigger.Instance>> newArrayList();
					}

					list.add(listener);
				}
			}

			if (list != null)
			{
				for (ICriterionTrigger.Listener<FeedAnimalTrigger.Instance> listener1 : list)
				{
					listener1.grantCriterion(this.playerAdvancements);
				}
			}
		}
	}
}
