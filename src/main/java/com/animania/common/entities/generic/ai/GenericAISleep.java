package com.animania.common.entities.generic.ai;

import java.util.function.Function;

import com.animania.api.interfaces.ISleeping;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.block.state.BlockState;

public class GenericAISleep<T extends PathfinderMob & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private int delay;

	private Block bedBlock;
	private Block bedBlock2;

	private Function<Long, Boolean> shouldSleep;

	public GenericAISleep(T entity, double speedIn, Block bed1, Block bed2, Class parentClass)
	{
		this(entity, speedIn, bed1, bed2, parentClass, (leveltime) -> {
			return leveltime >= 13000;
		});
	}

	public GenericAISleep(T entity, double speedIn, Block bed1, Block bed2, Class parentClass, Function<Long, Boolean> shouldSleepFunction)
	{
		super(entity, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, true, Direction.UP);
		this.entity = entity;
		this.setMutexBits(3);
		this.delay = 0;
		this.bedBlock = bed1;
		this.bedBlock2 = bed2;
		this.shouldSleep = shouldSleepFunction;
	}

	@Override
	public boolean shouldExecute()
	{
		if (entity instanceof TameableEntity)
		{
			if (((TameableEntity) entity).isSitting())
				return false;
		}

		if (++this.delay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings + entity.getRandom().nextInt(100))
		{
			return false;
		}
		if (entity.getSleeping())
		{
			if (!this.shouldSleep.apply(level.getLevelTime() % 24000) || entity.isBurning() || (entity.level.isRainingAt(entity.getPosition()) && entity.level.canSeeSky(entity.getPosition())))
			{
				entity.setSleeping(false);
				entity.setSleepingPos(NO_POS);
			}
			this.delay = 0;
			return false;
		}

		return shouldSleep.apply(level.getLevelTime() % 24000) && !entity.level.isRainingAt(entity.getPosition()) && this.entity.getRandom().nextInt(3) == 0 ? super.shouldExecute() : false;
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.isAtDestination())
		{
			entity.setSleeping(true);
			entity.setSleepingPos(entity.getPosition());

			this.delay = 0;
		}
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() && !entity.getSleeping();
	}

	@Override
	public void resetTask()
	{
		super.resetTask();
	}

	@Override
	protected boolean shouldMoveTo(Level levelIn, BlockPos pos)
	{
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();

		if (block == this.bedBlock)
			return true;

		return false;
	}

	@Override
	protected boolean shouldMoveToSecondary(Level level, BlockPos pos)
	{
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();

		if (block == this.bedBlock2)
			return true;

		return false;
	}

}
