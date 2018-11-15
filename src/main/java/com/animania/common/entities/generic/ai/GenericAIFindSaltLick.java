package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.entities.interfaces.ISleeping;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenericAIFindSaltLick<T extends EntityCreature & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private final double speed;
	private EntityAIBase eatAI;
	private int delay;

	public GenericAIFindSaltLick(T creature, double speedIn, @Nullable EntityAIBase eatAI)
	{
		super(creature, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, EnumFacing.HORIZONTALS);
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
		delay = 0;

		if (entity.getHealth() >= entity.getMaxHealth() ||
				entity.isBeingRidden() ||
				entity.getSleeping() ||
				(entity instanceof EntityAnimaniaPig && entity.world.getBlockState(entity.getPosition().down()).getBlock() == BlockHandler.blockMud) ||
				entity.getRNG().nextInt(3) != 0)
			return false;

		return super.shouldExecute();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.isAtDestination())
		{
			IBlockState state = world.getBlockState(seekingBlockPos);
			Block block = state.getBlock();

			if (block == BlockHandler.blockSaltLick)
			{
				eatAI.startExecuting();
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
