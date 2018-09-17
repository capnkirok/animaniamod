package com.animania.common.entities.generic.ai;

import javax.annotation.Nullable;

import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.entities.ISleeping;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GenericAIFindSaltLick extends EntityAIBase
{
	private final EntityCreature entity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;
	private EntityAIBase eatAI;

	public GenericAIFindSaltLick(EntityCreature entityInIn, double speedIn, @Nullable EntityAIBase eatAI)
	{
		this.entity = entityInIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
		this.eatAI = eatAI;

	}

	public boolean shouldExecute()
	{
		delayTemptCounter++;

		if (delayTemptCounter > AnimaniaConfig.careAndFeeding.saltLickTick || entity.getHealth() < entity.getMaxHealth())
		{

			this.delayTemptCounter = 0;

			boolean isSleeping = false;
			if (this.entity instanceof ISleeping)
			{
				if (((ISleeping) entity).getSleeping())
				{
					isSleeping = true;
				}

				if (entity instanceof EntityAnimaniaPig)
				{
					if (entity.world.getBlockState(entity.getPosition().down()).getBlock() == BlockHandler.blockMud)
						isSleeping = true;
				}
			}

			if (isSleeping)
			{
				return false;
			}

			if (this.entity.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 20, 4);
				if (vec3d != null)
				{
					this.delayTemptCounter = 0;
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

			Block poschk = entity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = entity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = entity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = entity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = entity.world.getBlockState(trypos4).getBlock();

			if (poschk == BlockHandler.blockSaltLick)
			{
				// do nothing
			}
			else if (poschk1 == BlockHandler.blockSaltLick)
			{
				currentpos = trypos1;
			}
			else if (poschk2 == BlockHandler.blockSaltLick)
			{
				currentpos = trypos2;
			}
			else if (poschk3 == BlockHandler.blockSaltLick)
			{
				currentpos = trypos3;
			}
			else if (poschk4 == BlockHandler.blockSaltLick)
			{
				currentpos = trypos4;
			}

			poschk = entity.world.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockSaltLick)
			{
				eatAI.startExecuting();
				BlockSaltLick salt = (BlockSaltLick) poschk;
				salt.useSaltLick(this.entity.world, currentpos, this.entity);
				this.resetTask();
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.entity.posX;
			double y = this.entity.posY;
			double z = this.entity.posZ;

			boolean waterFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockSaltLick && entity.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.0))
						{

							waterFound = true;
							return true;

						}
					}
				}
			}

			if (!waterFound)
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
		this.temptingPlayer = null;
		this.entity.getNavigator().clearPath();
		this.isRunning = false;
	}

	public void startExecuting()
	{

		double x = this.entity.posX;
		double y = this.entity.posY;
		double z = this.entity.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = entity.world.getBlockState(currentpos).getBlock();

		if (poschk != BlockHandler.blockSaltLick)
		{

			boolean waterFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos saltPos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -16; k < 16; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockSaltLick)
						{
							waterFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc)
							{

								loc = newloc;

								if (entity.posX < saltPos.getX())
								{
									BlockPos saltPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block waterBlockchk = entity.world.getBlockState(saltPoschk).getBlock();
									if (waterBlockchk == BlockHandler.blockSaltLick)
									{
										i = i + 1;
									}
								}

								if (entity.posZ < saltPos.getZ())
								{
									BlockPos saltPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block waterBlockchk = entity.world.getBlockState(saltPoschk).getBlock();
									if (waterBlockchk == BlockHandler.blockSaltLick)
									{
										k = k + 1;
									}
								}

								saltPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					}
				}
			}

			if (waterFound)
			{

				Block waterBlockchk = entity.world.getBlockState(saltPos).getBlock();

				if (waterBlockchk == BlockHandler.blockSaltLick && !this.entity.hasPath())
				{
					if (this.entity.getNavigator().tryMoveToXYZ(saltPos.getX(), saltPos.getY(), saltPos.getZ(), this.speed) == false)
					{
						this.delayTemptCounter = 0;
						this.resetTask();
					}
					else
					{
						this.entity.getNavigator().tryMoveToXYZ(saltPos.getX(), saltPos.getY(), saltPos.getZ(), this.speed);
					}
				}
			}
		}
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}