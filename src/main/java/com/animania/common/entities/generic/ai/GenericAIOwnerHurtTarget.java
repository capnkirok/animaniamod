package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.passive.EntityTameable;

public class GenericAIOwnerHurtTarget extends EntityAIOwnerHurtTarget
{

	private EntityTameable entity;

	public GenericAIOwnerHurtTarget(EntityTameable theEntityTameableIn)
	{
		super(theEntityTameableIn);
		this.entity = theEntityTameableIn;
	}

	@Override
	public boolean shouldExecute()
	{
		if (entity instanceof ISleeping)
			if (((ISleeping) entity).getSleeping())
				return false;

		if (entity.isSitting())
			return false;

		return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (entity instanceof ISleeping)
			if (((ISleeping) entity).getSleeping())
				return false;

		if (entity.isSitting())
			return false;

		return super.shouldContinueExecuting();
	}

}
