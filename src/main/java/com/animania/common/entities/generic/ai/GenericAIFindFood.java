package com.animania.common.entities.generic.ai;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.common.entities.IFoodEating;
import com.animania.common.entities.ISleeping;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GenericAIFindFood<T extends EntityCreature & IFoodEating> extends EntityAIBase
{
	private final T entity;
	private final double speed;
	private boolean isRunning;
	private int foodDelay;

	private EntityAIBase eatAI;
	private boolean eatBlocks;

	public GenericAIFindFood(T entity, double speedIn, @Nullable EntityAIBase eatAI, boolean eatBlocks)
	{
		this.entity = entity;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.foodDelay = 0;
		this.eatAI = eatAI;
		this.eatBlocks = eatBlocks;
	}

	public boolean shouldExecute()
	{
		foodDelay++;
		if (this.foodDelay <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
			return false;
		}
		else if (foodDelay > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{

			if (!entity.world.isDaytime())
			{
				this.foodDelay = 0;
				return false;
			}

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

			if (this.entity.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 20, 4);
				if (vec3d != null)
				{
					this.foodDelay = 0;
					this.resetTask();
					this.entity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
				}
				return false;
			}

			BlockPos currentpos = new BlockPos(entity.posX, entity.posY, entity.posZ);
			BlockPos trypos1 = new BlockPos(entity.posX + 1, entity.posY, entity.posZ);
			BlockPos trypos2 = new BlockPos(entity.posX - 1, entity.posY, entity.posZ);
			BlockPos trypos3 = new BlockPos(entity.posX, entity.posY, entity.posZ + 1);
			BlockPos trypos4 = new BlockPos(entity.posX, entity.posY, entity.posZ - 1);
			BlockPos trypos5 = new BlockPos(entity.posX + 1, entity.posY, entity.posZ + 1);
			BlockPos trypos6 = new BlockPos(entity.posX - 1, entity.posY, entity.posZ - 1);
			BlockPos trypos7 = new BlockPos(entity.posX - 1, entity.posY, entity.posZ + 1);
			BlockPos trypos8 = new BlockPos(entity.posX + 1, entity.posY, entity.posZ - 1);
			Block poschk = entity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = entity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = entity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = entity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = entity.world.getBlockState(trypos4).getBlock();
			Block poschk5 = entity.world.getBlockState(trypos5).getBlock();
			Block poschk6 = entity.world.getBlockState(trypos6).getBlock();
			Block poschk7 = entity.world.getBlockState(trypos7).getBlock();
			Block poschk8 = entity.world.getBlockState(trypos8).getBlock();

			if (poschk == BlockHandler.blockTrough)
			{
				// do nothing
			}
			else if (poschk1 == BlockHandler.blockTrough)
			{
				currentpos = trypos1;
			}
			else if (poschk2 == BlockHandler.blockTrough)
			{
				currentpos = trypos2;
			}
			else if (poschk3 == BlockHandler.blockTrough)
			{
				currentpos = trypos3;
			}
			else if (poschk4 == BlockHandler.blockTrough)
			{
				currentpos = trypos4;
			}
			else if (poschk5 == BlockHandler.blockTrough)
			{
				currentpos = trypos5;
			}
			else if (poschk6 == BlockHandler.blockTrough)
			{
				currentpos = trypos6;
			}
			else if (poschk7 == BlockHandler.blockTrough)
			{
				currentpos = trypos7;
			}
			else if (poschk8 == BlockHandler.blockTrough)
			{
				currentpos = trypos8;
			}

			if (poschk == BlockHandler.blockTrough || poschk1 == BlockHandler.blockTrough || poschk2 == BlockHandler.blockTrough || poschk3 == BlockHandler.blockTrough || poschk4 == BlockHandler.blockTrough || poschk5 == BlockHandler.blockTrough || poschk6 == BlockHandler.blockTrough || poschk7 == BlockHandler.blockTrough || poschk8 == BlockHandler.blockTrough)
			{
				TileEntityTrough te = (TileEntityTrough) entity.world.getTileEntity(currentpos);
				if (te != null && te.canConsume(entity.getFoodItems(), entity.getFoodFluid()))
				{
					te.consumeSolidOrLiquid(100, 1);

					if (eatAI != null)
						eatAI.startExecuting();
					entity.setLiquidFed(true);
					entity.setFed(true);
					entity.setHandFed(true);
					entity.setWatered(true);

					this.foodDelay = 0;
					return false;

				}
			}

			if (eatBlocks)
			{
				if (isBlockFood(poschk))
				{
					if (eatAI != null)
						eatAI.startExecuting();
					entity.setFed(true);

					if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
					{
						Block destchk = entity.world.getBlockState(currentpos).getBlock();
						if (destchk != BlockHandler.blockTrough)
						{
							entity.world.destroyBlock(currentpos, false);
						}
					}

					this.foodDelay = 0;
					return false;
				}
			}

			double x = this.entity.posX;
			double y = this.entity.posY;
			double z = this.entity.posZ;

			boolean foodFound = false;
			Random rand = new Random();

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = entity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockTrough)
						{
							TileEntityTrough te = (TileEntityTrough) entity.world.getTileEntity(pos);
							if (te != null && (te.fluidHandler.getFluid() != null && entity.getFoodFluid() != null && te.fluidHandler.getFluid().getFluid() == entity.getFoodFluid()) || te.canConsume(entity.getFoodItems(), null))
							{
								foodFound = true;
								if (rand.nextInt(200) == 0)
								{
									this.foodDelay = 0;
									return false;
								}
								else if (this.entity.collidedHorizontally && this.entity.motionX == 0 && this.entity.motionZ == 0)
								{
									this.foodDelay = 0;
									return false;
								}
								else
								{
									return true;
								}
							}
						}

						if (eatBlocks)
						{
							if (isBlockFood(blockchk))
							{

								foodFound = true;
								if (rand.nextInt(200) == 0)
								{
									this.foodDelay = 0;
									return false;
								}
								else if (this.entity.collidedHorizontally && this.entity.motionX == 0 && this.entity.motionZ == 0)
								{
									this.foodDelay = 0;
									return false;
								}
								else
								{
									return true;
								}
							}

						}
					}

				}
			}

			if (!foodFound)
			{
				this.foodDelay = 0;
				return false;
			}
		}

		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return !this.entity.getNavigator().noPath();
	}

	public void resetTask()
	{
		this.entity.getNavigator().clearPath();
		this.isRunning = false;
	}

	public void startExecuting()
	{

		double x = this.entity.posX;
		double y = this.entity.posY;
		double z = this.entity.posZ;

		boolean foodFound = false;
		int loc = 24;
		int newloc = 24;
		BlockPos pos = new BlockPos(x, y, z);
		BlockPos foodPos = new BlockPos(x, y, z);

		for (int i = -16; i < 16; i++)
		{
			for (int j = -3; j < 3; j++)
			{
				for (int k = -16; k < 16; k++)
				{

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = entity.world.getBlockState(pos).getBlock();

					if (blockchk == BlockHandler.blockTrough)
					{

						TileEntityTrough te = (TileEntityTrough) entity.world.getTileEntity(pos);

						if (te != null && (te.fluidHandler.getFluid() != null && entity.getFoodFluid() != null && te.fluidHandler.getFluid().getFluid() == entity.getFoodFluid()) || te.canConsume(entity.getFoodItems(), null))
						{

							foodFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (entity.posX < foodPos.getX())
								{
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block foodBlockchk = entity.world.getBlockState(foodPoschk).getBlock();
									// i = i + 1;
								}

								if (entity.posZ < foodPos.getZ())
								{
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block foodBlockchk = entity.world.getBlockState(foodPoschk).getBlock();
									// k = k + 1;
								}

								foodPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					}

				}

			}

		}

		if (!foodFound && eatBlocks)
		{

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entity.world.getBlockState(pos).getBlock();

						if (isBlockFood(blockchk))
						{
							foodFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (entity.posX < foodPos.getX())
								{
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block foodBlockchk = entity.world.getBlockState(foodPoschk).getBlock();
									if (isBlockFood(foodBlockchk))
									{
										i = i + 1;
									}
								}

								if (entity.posZ < foodPos.getZ())
								{
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block foodBlockchk = entity.world.getBlockState(foodPoschk).getBlock();
									if (isBlockFood(foodBlockchk))
									{
										k = k + 1;
									}
								}

								foodPos = new BlockPos(x + i, y + j, z + k);

							}

						}
					}
				}
			}
		}

		if (foodFound)
		{

			Block foodBlockchk = entity.world.getBlockState(foodPos).getBlock();

			if (foodBlockchk == BlockHandler.blockTrough)
			{
				TileEntityTrough te = (TileEntityTrough) this.entity.world.getTileEntity(foodPos);

				if (te.canConsume(entity.getFoodItems(), entity.getFoodFluid()))
				{
					if (this.entity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed) == false)
					{
						this.resetTask();
					}
					else
					{
						this.entity.getLookHelper().setLookPosition(foodPos.getX(), foodPos.getY(), foodPos.getZ(), 0.0F, 0.0F);
						this.entity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed);

					}
				}
			}

			if (eatBlocks)
			{
				if (isBlockFood(foodBlockchk))
				{
					if (this.entity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed) == false)
					{
						this.entity.getLookHelper().setLookPosition(foodPos.getX(), foodPos.getY(), foodPos.getZ(), 0.0F, 0.0F);
						this.resetTask();
					}
					else
					{
						this.entity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed);
						this.entity.getLookHelper().setLookPosition(foodPos.getX(), foodPos.getY(), foodPos.getZ(), 0.0F, 0.0F);
					}
				}
			}
		}
		else
		{
			foodDelay = 0;
		}

	}

	public boolean isRunning()
	{
		return this.isRunning;
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
