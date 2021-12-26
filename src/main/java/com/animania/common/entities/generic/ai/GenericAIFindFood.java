package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.api.interfaces.IFoodProviderTE;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.blocks.fluids.BlockFluidBase;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GenericAIFindFood<T extends PathfinderMob & IFoodEating & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private int foodDelay;

	private Goal eatAI;
	private boolean eatBlocks;

	public GenericAIFindFood(T entity, double speedIn, @Nullable Goal eatAI, boolean eatBlocks)
	{
		super(entity, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, eatBlocks, Direction.HORIZONTALS);
		this.entity = entity;
		this.setMutexBits(3);
		this.foodDelay = 0;
		this.eatAI = eatAI;
		this.eatBlocks = eatBlocks;

	}

	@Override
	public boolean shouldExecute()
	{
		if (++this.foodDelay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
			return false;

		if (this.entity.getFed() || this.entity.isBeingRidden() || this.entity.getSleeping() || (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? !this.entity.getInteracted() : false))
		{
			this.foodDelay = 0;
			return false;
		}

		if (this.entity.getRandom().nextInt(3) != 0)
			return false;

		this.foodDelay = 0;
		return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() && !this.entity.getFed();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.isAtDestination())
		{
			this.creature.getLookHelper().setLookPosition(this.seekingBlockPos.getX() + 0.5D, (double) this.seekingBlockPos.getY(), this.seekingBlockPos.getZ() + 0.5D, 10.0F, (float) this.creature.getVerticalFaceSpeed());

			BlockState state = this.level.getBlockState(this.seekingBlockPos);
			Block block = state.getBlock();

			if (block instanceof IFoodProviderBlock)
			{
				BlockEntity te = this.level.getBlockEntity(this.seekingBlockPos);
				if ((te instanceof IFoodProviderTE trough) && trough.canConsume(this.entity.getFoodItems(), this.entity.getFoodFluids()))
				{
					trough.consumeSolidOrLiquid(100, 1);

					if (this.eatAI != null)
						this.eatAI.startExecuting();

					this.entity.setLiquidFed(true);
					this.entity.setFed(true);
					this.entity.setHandFed(true);
					this.entity.setInteracted(true);

					this.entity.level.updateComparatorOutputLevel(this.seekingBlockPos, block);

					this.foodDelay = 0;
					return;
				}
			}

			if (this.isBlockFood(block))
			{
				if (this.eatAI != null)
					this.eatAI.startExecuting();
				this.entity.setFed(true);

				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
					this.entity.level.destroyBlock(this.seekingBlockPos, false);

				this.foodDelay = 0;
				return;
			}

			if (block instanceof BlockFluidBase)
			{
				if (this.eatAI != null)
					this.eatAI.startExecuting();
				this.entity.setFed(true);
				this.entity.setLiquidFed(true);

				this.entity.level.destroyBlock(this.seekingBlockPos, false);

				this.foodDelay = 0;
			}

		}

	}

	@Override
	protected boolean shouldMoveTo(Level level, BlockPos pos)
	{
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof IFoodProviderBlock)
		{
			BlockEntity te = level.getBlockEntity(pos);
			if (te instanceof IFoodProviderTE && ((IFoodProviderTE) te).canConsume(this.entity.getFoodItems(), this.entity.getFoodFluids()))
				return true;
		}

		return false;
	}

	@Override
	protected boolean shouldMoveToSecondary(Level levelIn, BlockPos pos)
	{
		BlockState state = this.level.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof BlockFluidBase)
		{
			Fluid[] foodFluids = this.entity.getFoodFluids();
			if (foodFluids != null && foodFluids.length > 0)
			{
				BlockFluidBase fluidBlock = (BlockFluidBase) block;
				int level = state.getValue(BlockFluidBase.LEVEL);
				if (level == 0)
				{
					Fluid fluid = fluidBlock.getFluid();
					for (Fluid f : foodFluids)
					{
						if (f.equals(fluid))
						{
							return true;
						}
					}
				}

			}

		}

		if (this.eatBlocks && this.isBlockFood(block))
		{
			return true;
		}

		return false;
	}

	public boolean isBlockFood(Block block)
	{
		Class[] foodBlocks = this.entity.getFoodBlocks();
		for (Class c : foodBlocks)
		{
			if (c.isInstance(block))
				return true;
		}

		return false;
	}

}
