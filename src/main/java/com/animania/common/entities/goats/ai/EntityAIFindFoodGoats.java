package com.animania.common.entities.goats.ai;

import java.util.Random;

import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFindFoodGoats extends EntityAIBase 
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

	public EntityAIFindFoodGoats(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{
		delayTemptCounter++;
		if (this.delayTemptCounter <= 60) {
			return false;
		} else if (delayTemptCounter > 60) {
			if (temptedEntity instanceof EntityAnimaniaGoat) {
				EntityAnimaniaGoat ech = (EntityAnimaniaGoat)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;		
				}
			} 
			
			if (this.temptedEntity.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.temptedEntity, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.resetTask();
					this.temptedEntity.getNavigator().tryMoveToXYZ(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, this.speed);
				}
				return false;
			}
			
			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos1 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos2 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos3 = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos4 = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ - 1);
			BlockPos trypos5 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos6 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ - 1);
			BlockPos trypos7 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos8 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ - 1);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = temptedEntity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = temptedEntity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = temptedEntity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = temptedEntity.world.getBlockState(trypos4).getBlock();
			Block poschk5 = temptedEntity.world.getBlockState(trypos5).getBlock();
			Block poschk6 = temptedEntity.world.getBlockState(trypos6).getBlock();
			Block poschk7 = temptedEntity.world.getBlockState(trypos7).getBlock();
			Block poschk8 = temptedEntity.world.getBlockState(trypos8).getBlock();

			if (poschk == BlockHandler.blockTrough) {
				//do nothing
			} else if (poschk1 == BlockHandler.blockTrough) {
				currentpos = trypos1;
			} else if (poschk2 == BlockHandler.blockTrough) {
				currentpos = trypos2;
			} else if (poschk3 == BlockHandler.blockTrough) {
				currentpos = trypos3;
			} else if (poschk4 == BlockHandler.blockTrough) {
				currentpos = trypos4;
			} else if (poschk5 == BlockHandler.blockTrough) {
				currentpos = trypos5;
			} else if (poschk6 == BlockHandler.blockTrough) {
				currentpos = trypos6;
			} else if (poschk7 == BlockHandler.blockTrough) {
				currentpos = trypos7;
			} else if (poschk8 == BlockHandler.blockTrough) {
				currentpos = trypos8;
			}

			if (poschk == BlockHandler.blockTrough || poschk1 == BlockHandler.blockTrough || poschk2 == BlockHandler.blockTrough || poschk3 == BlockHandler.blockTrough || poschk4 == BlockHandler.blockTrough || poschk5 == BlockHandler.blockTrough || poschk6 == BlockHandler.blockTrough || poschk7 == BlockHandler.blockTrough || poschk8 == BlockHandler.blockTrough) {
				TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(currentpos);
				if (te != null && te.canConsume(EntityAnimaniaGoat.TEMPTATION_ITEMS, null)) {
					te.consumeSolid(1);

					if (temptedEntity instanceof EntityAnimaniaGoat) {
						EntityAnimaniaGoat ech = (EntityAnimaniaGoat)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} 
					this.delayTemptCounter = 0;
					return false;

				} 
			}

			if (poschk == Blocks.RED_FLOWER || poschk == Blocks.CARROTS || poschk == Blocks.WHEAT || poschk instanceof BlockFlower) {

				if (temptedEntity instanceof EntityAnimaniaGoat) {
					EntityAnimaniaGoat ech = (EntityAnimaniaGoat)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} 
				
				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating) {
					Block destchk = temptedEntity.world.getBlockState(currentpos).getBlock();
					if (destchk != BlockHandler.blockTrough) {
						temptedEntity.world.destroyBlock(currentpos, false);
					}
				}
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean foodFound = false;
			Random rand = new Random();

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(pos);
							if (te != null && te.canConsume(EntityAnimaniaGoat.TEMPTATION_ITEMS, null)) {
								foodFound = true;
								if (rand.nextInt(200) == 0) {
									this.delayTemptCounter = 0;
									return false;
								} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0 ) {
									this.delayTemptCounter = 0;
									return false;
								} else {
									return true;
								}
							}
						}

						if (blockchk == Blocks.RED_FLOWER || blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT || blockchk == Blocks.YELLOW_FLOWER) {

							foodFound = true;
							if (rand.nextInt(200) == 0) {
								this.delayTemptCounter = 0;
								return false;
							} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0 ) {
								this.delayTemptCounter = 0;
								return false;
							} else {
								return true;
							}
						}

					}

				}
			}

			if (!foodFound) {
				this.delayTemptCounter = 0;
				return false;
			}
		}
		
		return false;
	}

	public boolean continueExecuting()
	{

		return this.shouldExecute();
	}

	public void startExecuting()
	{	
		this.isRunning = true;
	}

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
		BlockPos foodPos = new BlockPos(x, y, z);

		for (int i = -16; i < 16; i++) {
			for (int j = -3; j < 3; j++) {
				for (int k = -16; k < 16; k++) {

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();


					if (blockchk == BlockHandler.blockTrough) {


						TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(pos);

						if (te != null && te.canConsume(EntityAnimaniaGoat.TEMPTATION_ITEMS, null)) {

							foodFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (temptedEntity.posX < foodPos.getX()) {
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block mudBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
									i = i + 1;
								} 

								if (temptedEntity.posZ < foodPos.getZ()) {
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block mudBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
									k = k + 1;
								}

								foodPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					} else if (blockchk == Blocks.RED_FLOWER || blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT || blockchk == Blocks.YELLOW_FLOWER) {
						foodFound = true;
						newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

						if (newloc < loc) {

							loc = newloc;

							if (temptedEntity.posX < foodPos.getX()) {
								BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block mudBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
								if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER) {
									i = i + 1;
								}
							} 

							if (temptedEntity.posZ < foodPos.getZ()) {
								BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block mudBlockchk = temptedEntity.world.getBlockState(foodPoschk).getBlock();
								if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER) {
									k = k + 1;
								} 
							}

							foodPos = new BlockPos(x + i, y + j, z + k);

						}

					}

				}

			}


		}

		if (foodFound) {

			Block mudBlockchk = temptedEntity.world.getBlockState(foodPos).getBlock();
			if ((mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER || (mudBlockchk == BlockHandler.blockTrough))) {

				this.temptedEntity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed);

			}
		}

	}


	public boolean isRunning()
	{
		return this.isRunning;
	}
}