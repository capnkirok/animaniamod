package com.animania.catsdogs.common.entity.canids.ai;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.helper.AnimaniaHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.Path;

import java.util.List;

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
	public boolean canUse()
	{
		if (this.herdAnimal.getSleeping())
			return false;

		List<U> list = AnimaniaHelper.getEntitiesInRangeWithPredicate(this.herderType, 10, this.herdAnimal.level, this.herdAnimal, e -> !e.getSleeping() && e.isTame() && !e.isInSittingPose());
		return !list.isEmpty();
	}

	@Override
	public boolean canContinueToUse()
	{
		if (!this.canUse())
			return false;

		return super.canContinueToUse();
	}

	@Override
	public void tick()
	{
		List<U> list = AnimaniaHelper.getEntitiesInRangeWithPredicate(this.herderType, 10, this.herdAnimal.level, this.herdAnimal, e -> !e.getSleeping() && e.isTame() && !e.isInSittingPose());
		if (list.size() > 0)
		{
			U herder = list.get(0);
			PathNavigation herderNav = herder.getNavigation();
			Path herderPath = herderNav.getPath();
			if (herderPath != null)
			{
				BlockPos herderDest = herderPath.getTarget();
				this.herdAnimal.getNavigation().moveTo(herderDest.getX(), herderDest.getY(), herderDest.getZ(), 1.0f);
			}
		}
	}
}
