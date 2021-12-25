package com.animania.common.entities.generic.ai;

import java.util.List;
import java.util.Random;

import com.animania.api.interfaces.IPlaying;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.helper.AnimaniaHelper;

import PathNavigate;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class GenericAIPlay<T extends PathfinderMob & ISleeping & IPlaying, U extends PathfinderMob & ISleeping & IPlaying> extends Goal
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
		this.navigator = entity.getNavigation();
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.isRunning)
			return true;

		if (this.entity.getSleeping())
			return false;

		List<U> list = AnimaniaHelper.getEntitiesInRangeWithPredicate(this.playmateClass, 5, this.entity.level, this.entity, e -> !e.getSleeping() && !e.getPlayAI().isRunning);
		if (list.isEmpty())
			return false;

		if (rand.nextDouble() < 0.2)
			return true;

		return false;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (rand.nextDouble() < 0.1 || !this.shouldExecute())
			return false;

		return super.shouldContinueExecuting();
	}

	@Override
	public void resetTask()
	{
		this.isRunning = false;
		this.isChaser = false;
		this.playmate = null;
		this.path = null;
	}

	@Override
	public void startExecuting()
	{
		if (!this.isRunning)
		{
			List<U> list = AnimaniaHelper.getEntitiesInRangeWithPredicate(this.playmateClass, 5, this.entity.level, this.entity, e -> !e.getSleeping() && !e.getPlayAI().isRunning);
			if (!list.isEmpty())
			{
				this.playmate = list.get(0);
				this.isRunning = true;
				this.isChaser = true;
				GenericAIPlay otherAI = this.playmate.getPlayAI();
				otherAI.playmate = this.entity;
				otherAI.isRunning = true;
				otherAI.isChaser = false;
			}
		}
	}

	@Override
	public void updateTask()
	{
		if (this.isRunning)
		{
			if (this.isChaser)
			{
				if (this.path == null || this.navigator.noPath())
				{
					this.path = this.navigator.getPathToLivingEntity(this.playmate);
				}

				this.navigator.setPath(this.path, 1.0);

				if (this.entity.getDistance(this.playmate) <= 0.5)
				{
					GenericAIPlay otherAI = this.playmate.getPlayAI();
					otherAI.isChaser = true;
					this.isChaser = false;
				}
			}
			else
			{
				if (this.path == null || this.navigator.noPath())
				{
					Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(this.playmate.getX(), this.playmate.getY(), this.playmate.getZ()));
					if (vec3d != null)
					{
						this.path = this.navigator.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
					}
				}

				this.navigator.setPath(this.path, 1.0);
			}
		}
	}
}
