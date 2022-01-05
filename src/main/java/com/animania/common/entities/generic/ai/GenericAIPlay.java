package com.animania.common.entities.generic.ai;

import java.util.List;
import java.util.Random;

import com.animania.api.interfaces.IPlaying;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class GenericAIPlay<T extends PathfinderMob & ISleeping & IPlaying, U extends PathfinderMob & ISleeping & IPlaying> extends Goal
{
	private T entity;
	private Class<? extends U> playmateClass;
	private Path path = null;
	private PathNavigation navigator;

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
	public boolean canUse()
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
	public boolean canContinueToUse()
	{
		if (rand.nextDouble() < 0.1 || !this.canUse())
			return false;

		return super.canContinueToUse();
	}

	@Override
	public void stop()
	{
		this.isRunning = false;
		this.isChaser = false;
		this.playmate = null;
		this.path = null;
	}

	@Override
	public void start()
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
	public void tick()
	{
		if (this.isRunning)
		{
			if (this.isChaser)
			{
				if (this.path == null || this.navigator.isDone())
				{
					this.path = this.navigator.getPathToLivingEntity(this.playmate);
				}

				this.navigator.moveTo(this.path, 1.0);

				if (this.entity.getDistance(this.playmate) <= 0.5)
				{
					GenericAIPlay otherAI = this.playmate.getPlayAI();
					otherAI.isChaser = true;
					this.isChaser = false;
				}
			}
			else
			{
				if (this.path == null || this.navigator.isDone())
				{
					Vec3 vector = RandomPos.getPosAvoid(this.entity, 16, 7, new Vec3d(this.playmate.getX(), this.playmate.getY(), this.playmate.getZ()));
					if (vector != null)
					{
						this.path = this.navigator.moveTo(vector.x, vector.y, vector.z);
					}
				}

				this.navigator.moveTo(this.path, 1.0);
			}
		}
	}
}
