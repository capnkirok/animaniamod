package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.IFoodProviderBlock;
import com.animania.api.interfaces.IFoodProviderTE;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityInvisiblock;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GenericAIFindWater<T extends EntityCreature & IFoodEating & ISleeping> extends GenericAISearchBlock
{

	private final T entity;
	private final double speed;
	private int waterFindTimer;
	private boolean isRunning;
	private EntityAIBase eatAI;
	private Class parentClass;
	private boolean halfAmount;

	public GenericAIFindWater(T entity, double speedIn, @Nullable EntityAIBase eatAI, Class parentClass, boolean halfAmount)
	{
		super(entity, speedIn, AnimaniaConfig.gameRules.aiBlockSearchRange, true, EnumFacing.UP);
		this.entity = entity;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.waterFindTimer = 0;
		this.eatAI = eatAI;
		this.parentClass = parentClass;
		this.halfAmount = halfAmount;
	}

	public GenericAIFindWater(T entity, double speedIn, @Nullable EntityAIBase eatAI, Class parentClass)
	{
		this(entity, speedIn, eatAI, parentClass, false);
	}

	@Override
	public boolean shouldExecute()
	{
		if (++waterFindTimer <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
			return false;

		if (entity.getWatered() || entity.isBeingRidden() || entity.getSleeping() || !entity.getHandFed())
		{
			waterFindTimer = 0;
			return false;
		}
		
		if(entity.getRNG().nextInt(3) != 0)
			return false;

		waterFindTimer = 0;
		return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() && !entity.getWatered();
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

			if (block instanceof IFoodProviderBlock)
			{
				TileEntity te = world.getTileEntity(seekingBlockPos);
				if (te != null && te instanceof IFoodProviderTE)
				{
					IFoodProviderTE trough = (IFoodProviderTE) te;
					if (trough.canConsume(new FluidStack(FluidRegistry.WATER, this.halfAmount ? 50 : 100), null))
					{
						trough.consumeLiquid(halfAmount ? 50 : 100);

						entity.world.updateComparatorOutputLevel(seekingBlockPos, block);

						if (eatAI != null)
							eatAI.startExecuting();
						entity.setWatered(true);

						this.waterFindTimer = 0;
					}
				}
			}

			if (block == Blocks.WATER)
			{
				if (eatAI != null)
					eatAI.startExecuting();
				entity.setWatered(true);

				if (AnimaniaConfig.gameRules.waterRemovedAfterDrinking)
				{
					if (!halfAmount)
						this.entity.world.setBlockToAir(seekingBlockPos);
				}

				this.waterFindTimer = 0;
			}

		}
	}

	
	@Override
	protected boolean shouldMoveTo(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof IFoodProviderBlock)
		{
			TileEntity te = world.getTileEntity(pos);
			if (te != null && te instanceof IFoodProviderTE)
			{
				if (((IFoodProviderTE)te).canConsume(new FluidStack(FluidRegistry.WATER, this.halfAmount ? 50 : 100), null))
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
		Biome biome = world.getBiome(pos);

		if (block == Blocks.WATER && !BiomeDictionary.hasType(biome, Type.OCEAN) && !BiomeDictionary.hasType(biome, Type.BEACH))
		{
			return true;
		}

		return false;
	}

}
