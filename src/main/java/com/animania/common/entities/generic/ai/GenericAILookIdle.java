package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.AddonInjectionHandler;

import net.minecraft.world.entity.LivingEntity;

public class GenericAILookIdle<T extends LivingEntity & ISleeping> extends LookIdleGoal
{
	/** The entity that is looking idle. */
	private final T entity;

	public GenericAILookIdle(T LivingEntityIn)
	{
		super(LivingEntityIn);
		this.entity = LivingEntityIn;
	}

	/**
	 * Returns whether the Goal should begin execution.
	 */
	public boolean shouldExecute()
	{
		if (this.entity.getSleeping() || AddonInjectionHandler.runInjection("farm", "pigMudTest", Boolean.class, this.entity))
			return false;

		return super.shouldExecute();
	}
}