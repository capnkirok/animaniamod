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
		this(entity, speedIn, bed1, bed2, parentClass, leveltime -> (leveltime >= 13000));
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
		if (this.entity instanceof TameableEntity && ((TameableEntity) this.entity).isSitting() || ++this.delay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings + this.entity.getRandom().nextInt(100))
		{
			return false;
		}
		if (this.entity.getSleeping())
		{
			if (!this.shouldSleep.apply(this.level.getLevelTime() % 24000) || this.entity.isBurning() || this.entity.level.isRainingAt(this.entity.getPosition()) && this.entity.level.canSeeSky(this.entity.getPosition()))
			{
				this.entity.setSleeping(false);
				this.entity.setSleepingPos(NO_POS);
			}
			this.delay = 0;
			return false;
		}

		return this.shouldSleep.apply(this.level.getLevelTime() % 24000) && !this.entity.level.isRainingAt(this.entity.getPosition()) && this.entity.getRandom().nextInt(3) == 0 ? super.shouldExecute() : false;
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.isAtDestination())
		{
			this.entity.setSleeping(true);
			this.entity.setSleepingPos(this.entity.getPosition());

			this.delay = 0;
		}
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() && !this.entity.getSleeping();
	}

	@Override
	public void resetTask()
	{
		super.resetTask();
	}

	@Override
	protected boolean shouldMoveTo(Level levelIn, BlockPos pos)
	{
		BlockState state = this.level.getBlockState(pos);
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
