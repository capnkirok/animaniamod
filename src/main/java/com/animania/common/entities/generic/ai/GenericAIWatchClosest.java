package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.AddonInjectionHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWatchClosest;

public class GenericAIWatchClosest extends EntityAIWatchClosest
{
	public GenericAIWatchClosest(EntityLiving entityIn, Class<? extends Entity> watchTargetClass, float maxDistance)
	{
		super(entityIn, watchTargetClass, maxDistance);
	}

	public GenericAIWatchClosest(EntityLiving entityIn, Class<? extends Entity> watchTargetClass, float maxDistance, float chanceIn)
	{
		super(entityIn, watchTargetClass, maxDistance, chanceIn);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		boolean isSleeping = false;

		if (((ISleeping) entity).getSleeping())
			isSleeping = true;

		if (AddonInjectionHandler.runInjection("farm", "pigMudTest", Boolean.class, entity))
			isSleeping = true;

		if (isSleeping)
			return false;

		return super.shouldExecute();
	}
}