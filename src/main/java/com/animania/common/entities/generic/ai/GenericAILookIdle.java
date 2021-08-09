package com.animania.common.entities.generic.ai;


import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.AddonInjectionHandler;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.EntityAILookIdle;

public class GenericAILookIdle<T extends LivingEntity & ISleeping> extends EntityAILookIdle
{
	/** The entity that is looking idle. */
	private final T entity;

	public GenericAILookIdle(T LivingEntityIn)
	{
		super(LivingEntityIn);
		this.entity = LivingEntityIn;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		if (this.entity.getSleeping() || AddonInjectionHandler.runInjection("farm", "pigMudTest", Boolean.class, entity))
			return false;

		return super.shouldExecute();
	}
}