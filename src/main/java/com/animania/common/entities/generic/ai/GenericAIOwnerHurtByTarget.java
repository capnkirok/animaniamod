package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;

public class GenericAIOwnerHurtByTarget extends OwnerHurtByTargetGoal
{
	private TamableAnimal entity;

	public GenericAIOwnerHurtByTarget(TamableAnimal theDefendingTameableIn)
	{
		super(theDefendingTameableIn);
		this.entity = theDefendingTameableIn;
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.entity instanceof ISleeping)
			if (((ISleeping) this.entity).getSleeping())
				return false;

		if (this.entity.isSitting())
			return false;

		return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (this.entity instanceof ISleeping)
			if (((ISleeping) this.entity).getSleeping())
				return false;

		if (this.entity.isSitting())
			return false;

		return super.shouldContinueExecuting();
	}
}
