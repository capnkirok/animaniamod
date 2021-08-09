package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.passive.TameableEntity;

public class GenericAIOwnerHurtTarget extends OwnerHurtTargetGoal
{

	private TameableEntity entity;

	public GenericAIOwnerHurtTarget(TameableEntity theTameableEntityIn)
	{
		super(theTameableEntityIn);
		this.entity = theTameableEntityIn;
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
