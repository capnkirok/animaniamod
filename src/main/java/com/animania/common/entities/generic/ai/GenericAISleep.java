package com.animania.common.entities.generic.ai;

import java.util.function.Function;

import com.animania.api.interfaces.ISleeping;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import CreatureEntity;

public class GenericAISleep<T extends CreatureEntity & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private int delay;

	private Block bedBlock;
	private Block bedBlock2;

	private Function<Long, Boolean> shouldSleep;

	public GenericAISleep(T entity, double speedIn, Block bed1, Block bed2, Class parentClass)
	{
		this(entity, speedIn, bed1, bed2, parentClass, (worldtime) -> {
			return worldtime >= 13000;
		});
	}

	public GenericAISleep(T entity, double speedIn, Block bed1, Block bed2, Class parentClass, Function<Long, Boolean> shouldSleepFunction)
	{
		super(entity, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, true, EnumFacing.UP);
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

		if (++this.delay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings + entity.getRNG().nextInt(100))
		{
			return false;
		}
		if (entity.getSleeping())
		{
			if (!this.shouldSleep.apply(world.getWorldTime() % 24000) || entity.isBurning() || (entity.world.isRainingAt(entity.getPosition()) && entity.world.canSeeSky(entity.getPosition())))
			{
				entity.setSleeping(false);
				entity.setSleepingPos(NO_POS);
			}
			this.delay = 0;
			return false;
		}

		return shouldSleep.apply(world.getWorldTime() % 24000) && !entity.world.isRainingAt(entity.getPosition()) && this.entity.getRNG().nextInt(3) == 0 ? super.shouldExecute() : false;
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
	protected boolean shouldMoveTo(World worldIn, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block == this.bedBlock)
			return true;

		return false;
	}

	@Override
	protected boolean shouldMoveToSecondary(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block == this.bedBlock2)
			return true;

		return false;
	}

}
