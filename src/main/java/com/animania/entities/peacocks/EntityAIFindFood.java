package com.animania.entities.peacocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import com.animania.Animania;
import com.animania.blocks.BlockTrough;
import com.animania.tileentities.TileEntityTrough;

public class EntityAIFindFood extends EntityAIBase 
{
	private final EntityCreature temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public EntityAIFindFood(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{

		delayTemptCounter++;
		if (delayTemptCounter > 20) {
			if (this.temptedEntity instanceof EntityPeachickBlue) {
				EntityPeachickBlue entity = (EntityPeachickBlue) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPeachickWhite) {
				EntityPeachickWhite entity = (EntityPeachickWhite)temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPeacockBlue) {
				EntityPeacockBlue entity = (EntityPeacockBlue)temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPeacockWhite) {
				EntityPeacockWhite entity = (EntityPeacockWhite)temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPeafowlBlue) {
				EntityPeafowlBlue entity = (EntityPeafowlBlue)temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityPeafowlWhite) {
				EntityPeafowlWhite entity = (EntityPeafowlWhite)temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			}

			Random rand = new Random();
			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos1 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos2 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos3 = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos4 = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ - 1);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = temptedEntity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = temptedEntity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = temptedEntity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = temptedEntity.world.getBlockState(trypos4).getBlock();

			if (poschk == Animania.blockSeeds) {
				//do nothing
			} else if (poschk1 == Animania.blockSeeds) {
				currentpos = trypos1;
			} else if (poschk2 == Animania.blockSeeds) {
				currentpos = trypos2;
			} else if (poschk3 == Animania.blockSeeds) {
				currentpos = trypos3;
			} else if (poschk4 == Animania.blockSeeds) {
				currentpos = trypos4;
			}

			if (poschk == Animania.blockSeeds || poschk1 == Animania.blockSeeds || poschk2 == Animania.blockSeeds || poschk3 == Animania.blockSeeds || poschk4 == Animania.blockSeeds) {

				if (this.temptedEntity instanceof EntityPeachickBlue) {
					EntityPeachickBlue entity = (EntityPeachickBlue) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityPeachickWhite) {
					EntityPeachickWhite entity = (EntityPeachickWhite)temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityPeacockBlue) {
					EntityPeacockBlue entity = (EntityPeacockBlue)temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityPeacockWhite) {
					EntityPeacockWhite entity = (EntityPeacockWhite)temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityPeafowlBlue) {
					EntityPeafowlBlue entity = (EntityPeafowlBlue)temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityPeafowlWhite) {
					EntityPeafowlWhite entity = (EntityPeafowlWhite)temptedEntity;
					entity.setFed(true);
				}

				if (temptedEntity.world.getGameRules().getBoolean("mobGriefing"))
				{
					temptedEntity.world.destroyBlock(currentpos, false);
					temptedEntity.limbSwingAmount = 50.0f;
				}

				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean foodFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == Animania.blockSeeds) {
							foodFound = true;
							if (rand.nextInt(20) == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0 ) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else {
								return true;
							}
						}

					}

				}
			}

			if (!foodFound) {
				return false;
			}
		}

		return false;
	}



	public boolean continueExecuting()
	{

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{	
		this.isRunning = true;
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;

	}


	public void updateTask()
	{

		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		boolean foodFound = false;
		int loc = 24;
		int newloc = 24;
		BlockPos pos = new BlockPos(x, y, z);
		BlockPos mudPos = new BlockPos(x, y, z);

		for (int i = -16; i < 16; i++) {
			for (int j = -3; j < 3; j++) {
				for (int k = -16; k < 16; k++) {

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

					if (blockchk == Animania.blockSeeds) {
						foodFound = true;
						newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);
						mudPos = new BlockPos(x + i, y + j, z + k);

					}
				}
			}
		}



		if (foodFound) {

			Block mudBlockchk = temptedEntity.world.getBlockState(mudPos).getBlock();

			if (mudBlockchk == Animania.blockSeeds) {
				this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + .5, mudPos.getY(), mudPos.getZ() + .5, this.speed);
			}
		}

	}


	public boolean isRunning()
	{
		return this.isRunning;
	}
}