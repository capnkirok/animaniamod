package com.animania.common.entities.generic.ai;

import com.animania.common.entities.interfaces.ISleeping;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenericAISleep<T extends EntityCreature & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private final double speed;
	private int delay;
	private boolean isRunning;

	private Block bedBlock;
	private Block bedBlock2;

	private Class parentClass;
	
	public GenericAISleep(T entity, double speedIn, Block bed1, Block bed2, Class parentClass)
	{
		super(entity, speedIn, 16, EnumFacing.UP);
		this.entity = entity;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delay = 0;
		this.bedBlock = bed1;
		this.bedBlock2 = bed2;
		this.parentClass = parentClass;
	}

	public boolean shouldExecute()
	{
		delay++;

		if (this.delay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings + entity.getRNG().nextInt(100))
		{
			return false;
		}
		else if (delay > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{

			if (entity.world.isDaytime())
			{
				if (entity.getSleeping())
				{
					entity.setSleeping(false);
					entity.setSleepingPos(NO_POS);
					this.delay = 0;
				}
				return false;
			}

			if (entity.getSleeping() && entity.isBurning())
			{
				entity.setSleeping(false);
				entity.setSleepingPos(NO_POS);
				this.delay = 0;
				return false;
			}

			if (entity.getSleeping())
			{
				this.delay = 0;
				return false;
			}

			if (this.entity.getRNG().nextInt(3) == 0)
				return super.shouldExecute();
		}
		
		return false;
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
	protected boolean shouldMoveTo(World worldIn, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		
		if(block == this.bedBlock || block == this.bedBlock2)
			return true;
			
		return false;
	}

}
