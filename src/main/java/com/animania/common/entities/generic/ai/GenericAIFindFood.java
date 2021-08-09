package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.api.interfaces.IFoodProviderTE;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.blocks.fluids.BlockFluidBase;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.fluid.Fluid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenericAIFindFood<T extends CreatureEntity & IFoodEating & ISleeping> extends GenericAISearchBlock
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
		if (++foodDelay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
			return false;

		if (entity.getFed() || entity.isBeingRidden() || entity.getSleeping() || (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? !entity.getInteracted() : false))
		{
			foodDelay = 0;
			return false;
		}

		if (entity.getRandom().nextInt(3) != 0)
			return false;

		foodDelay = 0;
		return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() && !entity.getFed();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		if (this.isAtDestination())
		{
			this.creature.getLookHelper().setLookPosition((double) this.seekingBlockPos.getX() + 0.5D, (double) (this.seekingBlockPos.getY()), (double) this.seekingBlockPos.getZ() + 0.5D, 10.0F, (float) this.creature.getVerticalFaceSpeed());

			BlockState state = world.getBlockState(seekingBlockPos);
			Block block = state.getBlock();

			if (block instanceof IFoodProviderBlock)
			{
				TileEntity te = world.getTileEntity(seekingBlockPos);
				if (te != null && te instanceof IFoodProviderTE)
				{
					IFoodProviderTE trough = (IFoodProviderTE) te;
					if (trough.canConsume(entity.getFoodItems(), entity.getFoodFluids()))
					{
						trough.consumeSolidOrLiquid(100, 1);

						if (eatAI != null)
							eatAI.startExecuting();

						entity.setLiquidFed(true);
						entity.setFed(true);
						entity.setHandFed(true);
						entity.setInteracted(true);

						entity.level.updateComparatorOutputLevel(seekingBlockPos, block);

						this.foodDelay = 0;
						return;
					}
				}
			}

			if (isBlockFood(block))
			{
				if (eatAI != null)
					eatAI.startExecuting();
				entity.setFed(true);

				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
					entity.level.destroyBlock(seekingBlockPos, false);

				this.foodDelay = 0;
				return;
			}

			if (block instanceof BlockFluidBase)
			{
				if (eatAI != null)
					eatAI.startExecuting();
				entity.setFed(true);
				entity.setLiquidFed(true);

				entity.level.destroyBlock(seekingBlockPos, false);

				this.foodDelay = 0;
				return;
			}

		}

	}

	@Override
	protected boolean shouldMoveTo(World world, BlockPos pos)
	{
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof IFoodProviderBlock)
		{
			TileEntity te = world.getTileEntity(pos);
			if (te != null && te instanceof IFoodProviderTE)
			{
				if (((IFoodProviderTE) te).canConsume(entity.getFoodItems(), entity.getFoodFluids()))
					return true;
			}
		}

		return false;
	}

	@Override
	protected boolean shouldMoveToSecondary(World worldIn, BlockPos pos)
	{
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof BlockFluidBase)
		{
			Fluid[] foodFluids = entity.getFoodFluids();
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

		if (eatBlocks)
		{
			if (isBlockFood(block))
			{
				return true;
			}
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
