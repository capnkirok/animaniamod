package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.passive.TameableEntity;

public class GenericAIOwnerHurtByTarget extends OwnerHurtByTargetGoal
{
	private TameableEntity entity;

	public GenericAIOwnerHurtByTarget(TameableEntity theDefendingTameableIn)
	{
		super(theDefendingTameableIn);
		this.entity = theDefendingTameableIn;
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
