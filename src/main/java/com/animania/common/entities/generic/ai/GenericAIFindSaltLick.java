package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;

public class GenericAIFindSaltLick<T extends PathfinderMob & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private final double speed;
	private Goal eatAI;
	private int delay;

	public GenericAIFindSaltLick(T creature, double speedIn, @Nullable Goal eatAI)
	{
		super(creature, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, Direction.HORIZONTALS);
		this.entity = creature;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.eatAI = eatAI;
		this.delay = 0;
	}

	@Override
	public boolean shouldExecute()
	{
		if (++this.delay <= AnimaniaConfig.careAndFeeding.saltLickTick)
			return false;

		if (this.entity.getHealth() >= this.entity.getMaxHealth() || this.entity.isBeingRidden() || this.entity.getSleeping() || AddonInjectionHandler.runInjection("farm", "pigMudTest", Boolean.class, this.entity))
		{
			this.delay = 0;
			return false;
		}

		if (this.entity.getRandom().nextInt(3) != 0)
			return false;

		this.delay = 0;
		return super.shouldExecute();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.isAtDestination())
		{
			BlockState state = this.level.getBlockState(this.seekingBlockPos);
			Block block = state.getBlock();

			if (block == BlockHandler.blockSaltLick)
			{
				if (this.eatAI != null)
				{
					this.eatAI.startExecuting();
				}
				BlockSaltLick salt = (BlockSaltLick) block;
				salt.useSaltLick(this.level, this.seekingBlockPos, this.entity);

				this.delay = 0;
			}
		}
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() && this.entity.getHealth() < this.entity.getMaxHealth();
	}

	@Override
	protected boolean shouldMoveTo(Level levelIn, BlockPos pos)
	{
		Block block = levelIn.getBlockState(pos).getBlock();

		return block == BlockHandler.blockSaltLick;
	}

}
