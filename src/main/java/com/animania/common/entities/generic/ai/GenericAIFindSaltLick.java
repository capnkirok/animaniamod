package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenericAIFindSaltLick<T extends CreatureEntity & ISleeping> extends GenericAISearchBlock
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
		if (++delay <= AnimaniaConfig.careAndFeeding.saltLickTick)
			return false;

		if (entity.getHealth() >= entity.getMaxHealth() || entity.isBeingRidden() || entity.getSleeping() || AddonInjectionHandler.runInjection("farm", "pigMudTest", Boolean.class, entity))
		{
			delay = 0;
			return false;
		}

		if (entity.getRandom().nextInt(3) != 0)
			return false;

		delay = 0;
		return super.shouldExecute();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.isAtDestination())
		{
			BlockState state = world.getBlockState(seekingBlockPos);
			Block block = state.getBlock();

			if (block == BlockHandler.blockSaltLick)
			{
				if (eatAI != null)
				{
					eatAI.startExecuting();
				}
				BlockSaltLick salt = (BlockSaltLick) block;
				salt.useSaltLick(world, seekingBlockPos, this.entity);

				this.delay = 0;
			}
		}
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() && entity.getHealth() < entity.getMaxHealth();
	}

	@Override
	protected boolean shouldMoveTo(World worldIn, BlockPos pos)
	{
		Block block = worldIn.getBlockState(pos).getBlock();

		return block == BlockHandler.blockSaltLick;
	}

}
