package com.animania.common.entities.generic.ai;

import java.util.List;
import java.util.Random;

import com.animania.api.interfaces.IPlaying;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.Vec3d;

public class GenericAIPlay<T extends CreatureEntity & ISleeping & IPlaying, U extends CreatureEntity & ISleeping & IPlaying> extends Goal
{
	private T entity;
	private Class<? extends U> playmateClass;
	private Path path = null;
	private PathNavigate navigator;

	public boolean isRunning = false;
	public boolean isChaser = false;
	public U playmate = null;

	private static final Random rand = new Random();

	public GenericAIPlay(T entity, Class<? extends U> playmateType)
	{
		this.entity = entity;
		this.playmateClass = playmateType;
		this.navigator = entity.getNavigator();
	}

	@Override
	public boolean shouldExecute()
	{
		if (isRunning)
			return true;

		if (entity.getSleeping())
			return false;

		List<U> list = AnimaniaHelper.getEntitiesInRangeWithPredicate(playmateClass, 5, entity.level, entity, e -> !e.getSleeping() && !e.getPlayAI().isRunning);
		if (list.isEmpty())
			return false;

		if (rand.nextDouble() < 0.2)
			return true;

		return false;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (rand.nextDouble() < 0.1)
			return false;

		if (!shouldExecute())
			return false;

		return super.shouldContinueExecuting();
	}

	@Override
	public void resetTask()
	{
		isRunning = false;
		isChaser = false;
		playmate = null;
		path = null;
	}

	@Override
	public void startExecuting()
	{
		if (!isRunning)
		{
			List<U> list = AnimaniaHelper.getEntitiesInRangeWithPredicate(playmateClass, 5, entity.level, entity, e -> !e.getSleeping() && !e.getPlayAI().isRunning);
			if (!list.isEmpty())
			{
				this.playmate = list.get(0);
				this.isRunning = true;
				this.isChaser = true;
				GenericAIPlay otherAI = playmate.getPlayAI();
				otherAI.playmate = entity;
				otherAI.isRunning = true;
				otherAI.isChaser = false;
			}
		}
	}

	@Override
	public void updateTask()
	{
		if (isRunning)
		{
			if (isChaser)
			{
				if (path == null || navigator.noPath())
				{
					path = navigator.getPathToLivingEntity(playmate);
				}

				navigator.setPath(path, 1.0);

				if (entity.getDistance(playmate) <= 0.5)
				{
					GenericAIPlay otherAI = playmate.getPlayAI();
					otherAI.isChaser = true;
					this.isChaser = false;
				}
			} else
			{
				if (path == null || navigator.noPath())
				{
					Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(playmate.getX(), playmate.getY(), playmate.getZ()));
					if (vec3d != null)
					{
						path = navigator.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
					}
				}

				navigator.setPath(path, 1.0);
			}
		}
	}
}
