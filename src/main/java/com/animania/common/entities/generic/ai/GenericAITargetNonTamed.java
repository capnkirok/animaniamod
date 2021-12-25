package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.google.common.base.Predicate;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;

public class GenericAITargetNonTamed extends NonTameRandomTargetGoal
{
	TamableAnimal entity;

	public GenericAITargetNonTamed(TamableAnimal entityIn, Class classTarget, boolean checkSight, Predicate targetSelector)
	{
		super(entityIn, classTarget, checkSight, targetSelector);
		this.entity = entityIn;
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.entity instanceof ISleeping)
			if (((ISleeping) this.entity).getSleeping())
				return false;

		if (this.entity.isTamed() || this.entity.isSitting())
			return false;

		return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (this.entity instanceof ISleeping)
			if (((ISleeping) this.entity).getSleeping())
				return false;

		if (this.entity.isTamed() || this.entity.isSitting())
			return false;

		return super.shouldContinueExecuting();
	}
}
