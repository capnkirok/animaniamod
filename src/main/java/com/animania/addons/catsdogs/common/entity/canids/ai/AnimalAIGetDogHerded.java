package com.animania.addons.catsdogs.common.entity.canids.ai;

import java.util.List;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;

public class AnimalAIGetDogHerded<T extends PathfinderMob & ISleeping, U extends TamableAnimal & ISleeping> extends Goal
{
	private final T herdAnimal;
	private final Class<? extends U> herderType;

	public AnimalAIGetDogHerded(T entity, Class<? extends U> herderType)
	{
		this.herdAnimal = entity;
		this.herderType = herderType;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		if (herdAnimal.getSleeping())
			return false;

		List<U> list = AnimaniaHelper.getEntitiesInRangeWithPredicate(herderType, 10, herdAnimal.level, herdAnimal, e -> !e.getSleeping() && e.isTamed() && !e.isSitting());
		if (list.isEmpty())
			return false;

		return true;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (!shouldExecute())
			return false;

		return super.shouldContinueExecuting();
	}

	@Override
	public void updateTask()
	{
		List<U> list = AnimaniaHelper.getEntitiesInRangeWithPredicate(herderType, 10, herdAnimal.level, herdAnimal, e -> !e.getSleeping() && e.isTamed() && !e.isSitting());
		if (list.size() > 0)
		{
			U herder = list.get(0);
			PathNavigate herderNav = herder.getNavigation();
			Path herderPath = herderNav.getPath();
			if (herderPath != null)
			{
				PathPoint herderDest = herderPath.getFinalPathPoint();
				herdAnimal.getNavigation().tryMoveToXYZ(herderDest.x, herderDest.y, herderDest.z, 1.0f);
			}
		}
	}
}
