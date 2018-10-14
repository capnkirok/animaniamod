package com.animania.common.entities.generic.ai;

import java.util.List;

import com.animania.common.entities.ISleeping;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GenericAISleep<T extends EntityCreature & ISleeping> extends EntityAIBase
{
	private final T entity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private int delayTemptCounter;
	private boolean isRunning;

	private Block bedBlock;
	private Block bedBlock2;

	private Class parentClass;

	public GenericAISleep(T entity, double speedIn, Block bed1, Block bed2, Class parentClass)
	{
		this.entity = entity;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
		this.bedBlock = bed1;
		this.bedBlock2 = bed2;
		this.parentClass = parentClass;
	}

	public boolean shouldExecute()
	{
		delayTemptCounter++;

		if (this.delayTemptCounter <= AnimaniaConfig.gameRules.ticksBetweenAIFirings + entity.getRNG().nextInt(100))
		{
			return false;
		}
		else if (delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{

			if (entity.world.isDaytime())
			{
				if (entity.getSleeping())
				{
					entity.setSleeping(false);
					this.delayTemptCounter = 0;
				}
				return false;
			}
			
			if (entity.getSleeping() && entity.isBurning())
			{
				entity.setSleeping(false);
				this.delayTemptCounter = 0;
				return false;
			}

			if (entity.getSleeping())
			{
				this.delayTemptCounter = 0;
				return false;
			}

			if (this.entity.getRNG().nextInt(100) == 0)
			{
				this.delayTemptCounter = 0;
				return false;
			}

			BlockPos currentpos = new BlockPos(entity.posX, entity.posY, entity.posZ);
			BlockPos trypos1 = new BlockPos(entity.posX + 1, entity.posY, entity.posZ);
			BlockPos trypos2 = new BlockPos(entity.posX - 1, entity.posY, entity.posZ);
			BlockPos trypos3 = new BlockPos(entity.posX, entity.posY, entity.posZ + 1);
			BlockPos trypos4 = new BlockPos(entity.posX, entity.posY, entity.posZ - 1);

			Block poschk = entity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = entity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = entity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = entity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = entity.world.getBlockState(trypos4).getBlock();

			if (bedBlock != Blocks.AIR)
			{
				if (poschk == bedBlock)
				{
					// do nothing
				}
				else if (poschk1 == bedBlock)
				{
					currentpos = trypos1;
				}
				else if (poschk2 == bedBlock)
				{
					currentpos = trypos2;
				}
				else if (poschk3 == bedBlock)
				{
					currentpos = trypos3;
				}
				else if (poschk4 == bedBlock)
				{
					currentpos = trypos4;
				}

				poschk = entity.world.getBlockState(currentpos).getBlock();

				if (poschk == bedBlock)
				{
					this.resetTask();
					this.delayTemptCounter = 0;
					entity.setSleeping(true);
					return false;
				}

				poschk = entity.world.getBlockState(currentpos.down()).getBlock();

				if (entity instanceof EntityFerretBase && poschk == bedBlock)
				{
					this.resetTask();
					this.delayTemptCounter = 0;
					entity.setSleeping(true);
					return false;
				}

				if (bedBlock == BlockHandler.blockStraw)
				{
					poschk = entity.world.getBlockState(currentpos.up()).getBlock();

					if (poschk == bedBlock)
					{
						this.resetTask();
						this.delayTemptCounter = 0;
						entity.setSleeping(true);
						return false;
					}

				}
			}

			if (bedBlock2 != Blocks.AIR)
			{

				poschk = entity.world.getBlockState(currentpos).getBlock();

				if (poschk == bedBlock2)
				{
					this.resetTask();
					this.delayTemptCounter = 0;
					entity.setSleeping(true);
					return false;
				}

				poschk = entity.world.getBlockState(currentpos.down()).getBlock();

				if (poschk == bedBlock2)
				{
					this.resetTask();
					this.delayTemptCounter = 0;
					entity.setSleeping(true);
					return false;
				}
			}

			double x = this.entity.posX;
			double y = this.entity.posY;
			double z = this.entity.posZ;

			boolean bedFound = false;
			boolean prefBedFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entity.world.getBlockState(pos).getBlock();

						List others = AnimaniaHelper.getEntitiesInRange(parentClass, 2, entity.world, pos);

						if (blockchk == bedBlock && others.size() < 2)
						{
							prefBedFound = true;
							bedFound = true;
							return true;
						}

						if (!prefBedFound && blockchk instanceof BlockGrass && others.size() < 2)
						{
							bedFound = true;
							return true;
						}

					}
				}
			}

			if (!bedFound)
			{
				this.delayTemptCounter = 0;
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 20, 4);
				if (vec3d != null)
				{
					this.delayTemptCounter = 0;
					this.entity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
					this.resetTask();
				}
				return false;
			}
		}

		this.delayTemptCounter = 0;
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

		BlockPos currentpos = new BlockPos(x, y - 1, z);
		Block poschk = entity.world.getBlockState(currentpos).getBlock();

		boolean bedFound = false;
		boolean prefBedFound = false;
		int loc = 24;
		int newloc = 24;
		BlockPos pos = new BlockPos(x, y - 1, z);
		BlockPos bedPos = new BlockPos(x, y - 1, z);

		for (int i = -16; i < 16; i++)
		{
			for (int j = -3; j < 3; j++)
			{
				for (int k = -16; k < 16; k++)
				{

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = entity.world.getBlockState(pos).getBlock();

					List others = AnimaniaHelper.getEntitiesInRange(parentClass, 2, entity.world, pos);

					if (blockchk == bedBlock && others.size() < 2)
					{
						bedFound = true;
						prefBedFound = true;
						newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

						if (newloc < loc)
						{

							loc = newloc;

							if (entity.posX < bedPos.getX())
							{
								BlockPos blockPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block bedBlockchk = entity.world.getBlockState(blockPoschk).getBlock();
								if (bedBlockchk == bedBlock)
								{
									i = i + 1;
								}
							}

							if (entity.posZ < bedPos.getZ())
							{
								BlockPos blockPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block bedBlockchk = entity.world.getBlockState(blockPoschk).getBlock();
								if (bedBlockchk == bedBlock)
								{
									k = k + 1;
								}
							}

							bedPos = new BlockPos(x + i, y + j, z + k);

						}
					}
				}
			}
		}

		if (!bedFound && !prefBedFound && !this.entity.hasPath())
		{

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entity.world.getBlockState(pos).getBlock();

						List others = AnimaniaHelper.getEntitiesInRange(parentClass, 2, entity.world, pos);

						if (blockchk == bedBlock2 && others.size() < 2)
						{
							bedFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (entity.posX < bedPos.getX())
								{
									BlockPos blockPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block bedBlockchk = entity.world.getBlockState(blockPoschk).getBlock();
									if (bedBlockchk == bedBlock2)
									{
										i = i + 1;
									}
								}

								if (entity.posZ < bedPos.getZ())
								{
									BlockPos blockPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block bedBlockchk = entity.world.getBlockState(blockPoschk).getBlock();
									if (bedBlockchk == bedBlock2)
									{
										k = k + 1;
									}
								}

								bedPos = new BlockPos(x + i, y + j, z + k);

							}

						}

					}
				}
			}
		}

		if (bedFound)
		{

			Block bedBlockchk = entity.world.getBlockState(bedPos).getBlock();

			if ((bedBlockchk == bedBlock || bedBlockchk == bedBlock2) && !this.entity.hasPath())
			{
				if (this.entity.getNavigator().tryMoveToXYZ(bedPos.getX(), bedPos.getY(), bedPos.getZ(), this.speed) == false)
				{
					this.entity.getLookHelper().setLookPosition(bedPos.getX(), bedPos.getY(), bedPos.getZ(), 0.0F, 0.0F);
					this.delayTemptCounter = 0;
					this.resetTask();
				}
				else
				{
					this.entity.getLookHelper().setLookPosition(bedPos.getX(), bedPos.getY(), bedPos.getZ(), 0.0F, 0.0F);
					this.entity.getNavigator().tryMoveToXYZ(bedPos.getX(), bedPos.getY(), bedPos.getZ(), this.speed);
				}

			}
			else
			{
				this.delayTemptCounter = 0;
				this.resetTask();
			}
		}
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}

}
