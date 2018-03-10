package com.animania.common.entities.cows.ai;

import java.util.Random;

import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFindFood extends EntityAIBase 
{
	private final EntityCreature entity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public EntityAIFindFood(EntityCreature entityIn, double speedIn)
	{
		this.entity = entityIn;
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
			if (entity instanceof EntityAnimaniaCow) {
				EntityAnimaniaCow ech = (EntityAnimaniaCow)entity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;		
				}
			} 

			if (this.entity.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 20, 4);
				if (vec3d != null) {
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
				TileEntityTrough te = (TileEntityTrough) entity.world.getTileEntity(currentpos);
				if (te != null && te.canConsume(EntityAnimaniaCow.TEMPTATION_ITEMS, null)) {
					te.consumeSolid(1);

					if (entity instanceof EntityAnimaniaCow) {
						EntityAnimaniaCow ech = (EntityAnimaniaCow)entity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} 
					this.delayTemptCounter = 0;
					return false;

				} 
			}

			if (poschk == Blocks.RED_FLOWER || poschk instanceof BlockCrops || poschk == Blocks.WHEAT || poschk instanceof BlockFlower || poschk == Blocks.TALLGRASS || poschk == Blocks.GRASS) {

				if (entity instanceof EntityAnimaniaCow) {
					EntityAnimaniaCow ech = (EntityAnimaniaCow)entity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} 

				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating) {
					Block destchk = entity.world.getBlockState(currentpos).getBlock();
					if (destchk != BlockHandler.blockTrough) {
						entity.world.destroyBlock(currentpos, false);
					}
				}
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.entity.posX;
			double y = this.entity.posY;
			double z = this.entity.posZ;

			boolean foodFound = false;
			Random rand = new Random();

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = entity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) entity.world.getTileEntity(pos);
							if (te != null && te.canConsume(EntityAnimaniaCow.TEMPTATION_ITEMS, null)) {
								foodFound = true;
								if (rand.nextInt(200) == 0) {
									this.delayTemptCounter = 0;
									return false;
								} else if (this.entity.collidedHorizontally && this.entity.motionX == 0 && this.entity.motionZ == 0 ) {
									this.delayTemptCounter = 0;
									return false;
								} else {
									return true;
								}
							}
						}

						if (blockchk == Blocks.RED_FLOWER || blockchk instanceof BlockCrops || blockchk == Blocks.WHEAT || blockchk == Blocks.YELLOW_FLOWER || blockchk == Blocks.TALLGRASS) {

							foodFound = true;
							if (rand.nextInt(200) == 0) {
								this.delayTemptCounter = 0;
								return false;
							} else if (this.entity.collidedHorizontally && this.entity.motionX == 0 && this.entity.motionZ == 0 ) {
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

	@Override
	public boolean shouldContinueExecuting()
	{
		return !this.entity.getNavigator().noPath();
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

		for (int i = -16; i < 16; i++) {
			for (int j = -3; j < 3; j++) {
				for (int k = -16; k < 16; k++) {

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = entity.world.getBlockState(pos).getBlock();


					if (blockchk == BlockHandler.blockTrough) {

						TileEntityTrough te = (TileEntityTrough) entity.world.getTileEntity(pos);

						if (te != null && te.canConsume(EntityAnimaniaCow.TEMPTATION_ITEMS, null)) {

							foodFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (entity.posX < foodPos.getX()) {
									BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block mudBlockchk = entity.world.getBlockState(foodPoschk).getBlock();
									i = i + 1;
								} 

								if (entity.posZ < foodPos.getZ()) {
									BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block mudBlockchk = entity.world.getBlockState(foodPoschk).getBlock();
									k = k + 1;
								}

								foodPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					} else if (blockchk == Blocks.RED_FLOWER || blockchk instanceof BlockCrops || blockchk == Blocks.WHEAT || blockchk == Blocks.YELLOW_FLOWER || blockchk == Blocks.TALLGRASS) {
						foodFound = true;
						newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

						if (newloc < loc) {

							loc = newloc;

							if (entity.posX < foodPos.getX()) {
								BlockPos foodPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block mudBlockchk = entity.world.getBlockState(foodPoschk).getBlock();
								if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk instanceof BlockCrops || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER || blockchk == Blocks.TALLGRASS) {
									i = i + 1;
								}
							} 

							if (entity.posZ < foodPos.getZ()) {
								BlockPos foodPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block mudBlockchk = entity.world.getBlockState(foodPoschk).getBlock();
								if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk instanceof BlockCrops || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER || blockchk == Blocks.TALLGRASS) {
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

			Block mudBlockchk = entity.world.getBlockState(foodPos).getBlock();
			if ((mudBlockchk == Blocks.RED_FLOWER || mudBlockchk instanceof BlockCrops || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER || mudBlockchk == Blocks.TALLGRASS || (mudBlockchk == BlockHandler.blockTrough))) {


				if(this.entity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed) == false) {
					this.delayTemptCounter = 0;
				} else {
					this.entity.getNavigator().tryMoveToXYZ(foodPos.getX(), foodPos.getY(), foodPos.getZ(), this.speed);
				}


			}
		}

	}

	public void resetTask()
	{
		this.temptingPlayer = null;
		this.entity.getNavigator().clearPath();
		this.isRunning = false;
	}


	public boolean isRunning()
	{
		return this.isRunning;
	}
}