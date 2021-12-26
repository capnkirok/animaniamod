package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.api.interfaces.IFoodProviderTE;
import com.animania.api.interfaces.ISleeping;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GenericAIFindWater<T extends PathfinderMob & IFoodEating & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private final double speed;
	private int waterFindTimer;
	private boolean isRunning;
	private Goal eatAI;
	private Class parentClass;
	private boolean halfAmount;

	public GenericAIFindWater(T entity, double speedIn, @Nullable Goal eatAI, Class parentClass, boolean halfAmount)
	{
		super(entity, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, true, Direction.UP);
		this.entity = entity;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.waterFindTimer = 0;
		this.eatAI = eatAI;
		this.parentClass = parentClass;
		this.halfAmount = halfAmount;
	}

	public GenericAIFindWater(T entity, double speedIn, @Nullable Goal eatAI, Class parentClass)
	{
		this(entity, speedIn, eatAI, parentClass, false);
	}

	@Override
	public boolean shouldExecute()
	{
		if (++this.waterFindTimer <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
			return false;

		if (this.entity.getWatered() || this.entity.isBeingRidden() || this.entity.getSleeping() || (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? !this.entity.getInteracted() : false))
		{
			this.waterFindTimer = 0;
			return false;
		}

		if (this.entity.getRandom().nextInt(3) != 0)
			return false;

		this.waterFindTimer = 0;
		return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() && !this.entity.getWatered();
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
				if (te instanceof IFoodProviderTE trough && trough.canConsume(new FluidStack(FluidRegistry.WATER, this.halfAmount ? 50 : 100), null))
				{
					trough.consumeLiquid(this.halfAmount ? 50 : 100);

					this.entity.level.updateComparatorOutputLevel(this.seekingBlockPos, block);

					if (this.eatAI != null)
						this.eatAI.startExecuting();
					this.entity.setWatered(true);
					this.entity.setInteracted(true);

					this.waterFindTimer = 0;
				}
			}

			if (block == Blocks.WATER)
			{
				if (this.eatAI != null)
					this.eatAI.startExecuting();
				this.entity.setWatered(true);

				if (AnimaniaConfig.gameRules.waterRemovedAfterDrinking && !this.halfAmount)
					this.entity.level.setBlockToAir(this.seekingBlockPos);

				this.waterFindTimer = 0;
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
			if (te instanceof IFoodProviderTE && ((IFoodProviderTE) te).canConsume(new FluidStack(FluidRegistry.WATER, this.halfAmount ? 50 : 100), null))
				return true;
		}

		return false;
	}

	@Override
	protected boolean shouldMoveToSecondary(Level levelIn, BlockPos pos)
	{
		BlockState state = this.level.getBlockState(pos);
		Block block = state.getBlock();
		Biome biome = this.level.getBiome(pos);

		if (block == Blocks.WATER && !BiomeDictionary.hasType(biome, Type.OCEAN) && !BiomeDictionary.hasType(biome, Type.BEACH))
		{
			return true;
		}

		return false;
	}

}
