package com.animania.common.entities.generic.ai;

import java.util.Collections;

import com.animania.common.api.interfaces.ISleeping;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenericAISleep<T extends EntityCreature & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private int delay;

	private Block bedBlock;
	private Block bedBlock2;

	public GenericAISleep(T entity, double speedIn, Block bed1, Block bed2, Class parentClass)
	{
		super(entity, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, true, EnumFacing.UP);
		this.entity = entity;
		this.setMutexBits(3);
		this.delay = 0;
		this.bedBlock = bed1;
		this.bedBlock2 = bed2;
	}

	public boolean shouldExecute()
	{
		if (++this.delay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings + entity.getRNG().nextInt(100))
		{
			return false;
		}
		if (entity.getSleeping())
		{
			if (entity.world.isDaytime() || entity.isBurning() || (entity.world.isRainingAt(entity.getPosition()) && entity.world.canSeeSky(entity.getPosition())))
			{
				entity.setSleeping(false);
				entity.setSleepingPos(NO_POS);
			}
			this.delay = 0;
			return false;
		}

		return !entity.world.isDaytime() && !entity.world.isRainingAt(entity.getPosition()) && this.entity.getRNG().nextInt(3) == 0 ? super.shouldExecute() : false;
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
