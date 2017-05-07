package com.animania.common.entities.chickens.ai;

import java.util.Random;

import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class EntityAIFindFood extends EntityAIBase {
	private final EntityCreature temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;

	public EntityAIFindFood(EntityCreature temptedEntityIn, double speedIn) {
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {

		delayTemptCounter++;
		if (delayTemptCounter > 20) {
			if (this.temptedEntity instanceof EntityChickLeghorn) {
				EntityChickLeghorn entity = (EntityChickLeghorn) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityChickOrpington) {
				EntityChickOrpington entity = (EntityChickOrpington) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityChickPlymouthRock) {
				EntityChickPlymouthRock entity = (EntityChickPlymouthRock) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityChickRhodeIslandRed) {
				EntityChickRhodeIslandRed entity = (EntityChickRhodeIslandRed) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityChickWyandotte) {
				EntityChickWyandotte entity = (EntityChickWyandotte) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHenLeghorn) {
				EntityHenLeghorn entity = (EntityHenLeghorn) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHenOrpington) {
				EntityHenOrpington entity = (EntityHenOrpington) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHenPlymouthRock) {
				EntityHenPlymouthRock entity = (EntityHenPlymouthRock) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHenRhodeIslandRed) {
				EntityHenRhodeIslandRed entity = (EntityHenRhodeIslandRed) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityHenWyandotte) {
				EntityHenWyandotte entity = (EntityHenWyandotte) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterLeghorn) {
				EntityRoosterLeghorn entity = (EntityRoosterLeghorn) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterOrpington) {
				EntityRoosterOrpington entity = (EntityRoosterOrpington) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterPlymouthRock) {
				EntityRoosterPlymouthRock entity = (EntityRoosterPlymouthRock) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterRhodeIslandRed) {
				EntityRoosterRhodeIslandRed entity = (EntityRoosterRhodeIslandRed) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityRoosterWyandotte) {
				EntityRoosterWyandotte entity = (EntityRoosterWyandotte) temptedEntity;
				if (entity.getFed()) {
					return false;
				}
			}

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

			if (poschk == BlockHandler.blockSeeds) {
				// do nothing
			} else if (poschk1 == BlockHandler.blockSeeds) {
				currentpos = trypos1;
			} else if (poschk2 == BlockHandler.blockSeeds) {
				currentpos = trypos2;
			} else if (poschk3 == BlockHandler.blockSeeds) {
				currentpos = trypos3;
			} else if (poschk4 == BlockHandler.blockSeeds) {
				currentpos = trypos4;
			}

			if (poschk == BlockHandler.blockSeeds || poschk1 == BlockHandler.blockSeeds
					|| poschk2 == BlockHandler.blockSeeds || poschk3 == BlockHandler.blockSeeds
					|| poschk4 == BlockHandler.blockSeeds) {

				Random rand = new Random();

				if (temptedEntity.world.getGameRules().getBoolean("mobGriefing")) {

					temptedEntity.world.destroyBlock(currentpos, false);

					temptedEntity.limbSwingAmount = 50.0f;
				}

				if (this.temptedEntity instanceof EntityChickLeghorn) {
					EntityChickLeghorn entity = (EntityChickLeghorn) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityChickOrpington) {
					EntityChickOrpington entity = (EntityChickOrpington) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityChickPlymouthRock) {
					EntityChickPlymouthRock entity = (EntityChickPlymouthRock) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityChickRhodeIslandRed) {
					EntityChickRhodeIslandRed entity = (EntityChickRhodeIslandRed) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityChickWyandotte) {
					EntityChickWyandotte entity = (EntityChickWyandotte) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityHenLeghorn) {
					EntityHenLeghorn entity = (EntityHenLeghorn) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityHenOrpington) {
					EntityHenOrpington entity = (EntityHenOrpington) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityHenPlymouthRock) {
					EntityHenPlymouthRock entity = (EntityHenPlymouthRock) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityHenRhodeIslandRed) {
					EntityHenRhodeIslandRed entity = (EntityHenRhodeIslandRed) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityHenWyandotte) {
					EntityHenWyandotte entity = (EntityHenWyandotte) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityRoosterLeghorn) {
					EntityRoosterLeghorn entity = (EntityRoosterLeghorn) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityRoosterOrpington) {
					EntityRoosterOrpington entity = (EntityRoosterOrpington) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityRoosterPlymouthRock) {
					EntityRoosterPlymouthRock entity = (EntityRoosterPlymouthRock) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityRoosterRhodeIslandRed) {
					EntityRoosterRhodeIslandRed entity = (EntityRoosterRhodeIslandRed) temptedEntity;
					entity.setFed(true);
				} else if (temptedEntity instanceof EntityRoosterWyandotte) {
					EntityRoosterWyandotte entity = (EntityRoosterWyandotte) temptedEntity;
					entity.setFed(true);
				}

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

						if (blockchk == BlockHandler.blockSeeds) {
							foodFound = true;

							if (rand.nextInt(20) == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
									&& this.temptedEntity.motionZ == 0) {
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
				this.delayTemptCounter = 0;
				this.resetTask();
				return false;
			}
		}

		return false;
	}

	@Override
	public boolean continueExecuting() {

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		this.isRunning = true;
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;

	}

	@Override
	public void updateTask() {

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

					if (blockchk == BlockHandler.blockSeeds) {
						foodFound = true;
						newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);
						mudPos = new BlockPos(x + i, y + j, z + k);

					}
				}
			}
		}

		if (foodFound) {

			Block mudBlockchk = temptedEntity.world.getBlockState(mudPos).getBlock();

			if (mudBlockchk == BlockHandler.blockSeeds) {
				this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + .5, mudPos.getY(), mudPos.getZ() + .5,
						this.speed);
			}
		}

	}

	public boolean isRunning() {
		return this.isRunning;
	}
}