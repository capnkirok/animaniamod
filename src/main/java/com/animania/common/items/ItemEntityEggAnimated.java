package com.animania.common.items;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.entity.EntityList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ItemEntityEggAnimated extends ItemEntityEgg
{

	public ItemEntityEggAnimated(String atype, AnimaniaType animal, EntityGender gender)
	{
		super(atype, animal, gender);
	}

	public static Entity getEntity(Level level, ItemStack stack)
	{
		String entityName = Animania.MODID + ":" + ((ItemEntityEggAnimated) stack.getItem()).getName().replace("entity_egg_", "");
		Entity entity = EntityList.createEntityByIDFromName(new ResourceLocation(entityName), level);

		return entity;
	}

}
