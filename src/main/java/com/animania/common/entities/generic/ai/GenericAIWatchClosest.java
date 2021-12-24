package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.AddonInjectionHandler;

import net.minecraft.world.entity.LivingEntity;

public class GenericAIWatchClosest extends WatchClosestGoal
{
	public GenericAIWatchClosest(LivingEntity entityIn, Class<? extends Entity> watchTargetClass, float maxDistance)
	{
		super(entityIn, watchTargetClass, maxDistance);
	}

	public GenericAIWatchClosest(LivingEntity entityIn, Class<? extends Entity> watchTargetClass, float maxDistance, float chanceIn)
	{
		super(entityIn, watchTargetClass, maxDistance, chanceIn);
	}

	/**
	 * Returns whether the Goal should begin execution.
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