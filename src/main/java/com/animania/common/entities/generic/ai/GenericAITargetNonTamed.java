package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.google.common.base.Predicate;

import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.passive.TameableEntity;

public class GenericAITargetNonTamed extends NonTamedTargetGoal
{
	TameableEntity entity;

	public GenericAITargetNonTamed(TameableEntity entityIn, Class classTarget, boolean checkSight, Predicate targetSelector)
	{
		super(entityIn, classTarget, checkSight, targetSelector);
		this.entity = entityIn;
	}

	@Override
	public boolean shouldExecute()
	{
		if (entity instanceof ISleeping)
			if (((ISleeping) entity).getSleeping())
				return false;

		if (entity.isTamed())
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

		if (entity.isTamed())
			return false;

		if (entity.isSitting())
			return false;

		return super.shouldContinueExecuting();
	}
}
