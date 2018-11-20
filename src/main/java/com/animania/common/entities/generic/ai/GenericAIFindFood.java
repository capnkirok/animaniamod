package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.common.entities.interfaces.IFoodEating;
import com.animania.common.entities.interfaces.ISleeping;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityInvisiblock;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenericAIFindFood<T extends EntityCreature & IFoodEating> extends GenericAISearchBlock
{

	private final T entity;
	private int foodDelay;

	private EntityAIBase eatAI;
	private boolean eatBlocks;

	public GenericAIFindFood(T entity, double speedIn, @Nullable EntityAIBase eatAI, boolean eatBlocks)
	{
		super(entity, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, eatBlocks, EnumFacing.HORIZONTALS);
		this.entity = entity;
		this.setMutexBits(3);
		this.foodDelay = 0;
		this.eatAI = eatAI;
		this.eatBlocks = eatBlocks;

	}

	@Override
	public boolean shouldExecute()
	{
		foodDelay++;
		if (this.foodDelay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
			return false;
		}

		else if (foodDelay > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
			if (entity instanceof ISleeping)
			{
				if (((ISleeping) entity).getSleeping())
				{
					this.foodDelay = 0;
					return false;
				}
			}

			if (entity.isBeingRidden())
			{
				this.foodDelay = 0;
				return false;
			}

			if (entity.getFed())
			{
				this.foodDelay = 0;
				return false;
			}

			if (entity.getRNG().nextInt(3) == 0)
				return super.shouldExecute();
		}

		return false;
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

			IBlockState state = world.getBlockState(seekingBlockPos);
			Block block = state.getBlock();

			if (block == BlockHandler.blockTrough || block == BlockHandler.blockInvisiblock)
			{
				TileEntityTrough trough = block == BlockHandler.blockTrough ? (TileEntityTrough) world.getTileEntity(seekingBlockPos) : ((TileEntityInvisiblock)world.getTileEntity(seekingBlockPos)).getTrough();
				if (trough != null)
				{
					if (trough.canConsume(entity.getFoodItems(), entity.getFoodFluid()))
					{
						trough.consumeSolidOrLiquid(100, 1);

						if (eatAI != null)
							eatAI.startExecuting();

						entity.setLiquidFed(true);
						entity.setFed(true);
						entity.setHandFed(true);

						entity.world.updateComparatorOutputLevel(seekingBlockPos, block);
						
						this.foodDelay = 0;
					}
				}
			}

			if (isBlockFood(block))
			{
				if (eatAI != null)
					eatAI.startExecuting();
				entity.setFed(true);

				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
					entity.world.destroyBlock(seekingBlockPos, false);
			}
			
			this.foodDelay = 0;
		}
	}

	@Override
	protected boolean shouldMoveTo(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block == BlockHandler.blockTrough || block == BlockHandler.blockInvisiblock)
		{
			TileEntityTrough trough = block == BlockHandler.blockTrough ? (TileEntityTrough) world.getTileEntity(pos) : ((TileEntityInvisiblock)world.getTileEntity(pos)).getTrough();
			if (trough != null)
			{
				if (trough.canConsume(entity.getFoodItems(), entity.getFoodFluid()))
					return true;
			}
		}

		return false;
	}
	
	@Override
	protected boolean shouldMoveToSecondary(World worldIn, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		
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
