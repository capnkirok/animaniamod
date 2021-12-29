package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

public class GenericAIAvoidEntity<T extends LivingEntity> extends AvoidEntityGoal<T>
{
	public GenericAIAvoidEntity(PathfinderMob entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
	{
		super(entityIn, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
	}

	public GenericAIAvoidEntity(PathfinderMob entityIn, Class<T> classToAvoidIn, Predicate<? super T> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
	{
		super(entityIn, classToAvoidIn, avoidTargetSelectorIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
	}

	@Override
	public boolean canUse() {
		if (((ISleeping) this.predicateOnAvoidEntity).getSleeping())
			return false;

		return super.canUse();
	}
}