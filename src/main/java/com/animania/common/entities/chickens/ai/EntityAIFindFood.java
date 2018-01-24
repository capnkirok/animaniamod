package com.animania.common.entities.chickens.ai;

import java.util.Random;

import com.animania.common.entities.chickens.EntityAnimaniaChicken;
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
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFindFood extends EntityAIBase
{
	private final EntityCreature temptedEntity;
	private final double         speed;
	private double               targetX;
	private double               targetY;
	private double               targetZ;
	private double               pitch;
	private double               yaw;
	private EntityPlayer         temptingPlayer;
	private int                  delayTemptCounter;
	private boolean              isRunning;

	public EntityAIFindFood(EntityCreature temptedEntityIn, double speedIn) {
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {

		delayTemptCounter++;
		if (this.delayTemptCounter < 60) {
			return false;
		} else if (delayTemptCounter > 60) {

			if (this.temptedEntity instanceof EntityAnimaniaChicken) {
				EntityAnimaniaChicken entity = (EntityAnimaniaChicken) temptedEntity;
				if (entity.getFed()) {
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

			BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
			BlockPos trypos1 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ);
			BlockPos trypos2 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ);
			BlockPos trypos3 = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
			BlockPos trypos4 = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
			Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = this.temptedEntity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = this.temptedEntity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = this.temptedEntity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = this.temptedEntity.world.getBlockState(trypos4).getBlock();

			if (poschk == BlockHandler.blockSeeds) {
				// do nothing
			}
			else if (poschk1 == BlockHandler.blockSeeds)
				currentpos = trypos1;
			else if (poschk2 == BlockHandler.blockSeeds)
				currentpos = trypos2;
			else if (poschk3 == BlockHandler.blockSeeds)
				currentpos = trypos3;
			else if (poschk4 == BlockHandler.blockSeeds)
				currentpos = trypos4;

			if (poschk == BlockHandler.blockSeeds || poschk1 == BlockHandler.blockSeeds || poschk2 == BlockHandler.blockSeeds
					|| poschk3 == BlockHandler.blockSeeds || poschk4 == BlockHandler.blockSeeds) {

				Random rand = new Random();

				this.temptedEntity.world.destroyBlock(currentpos, false);
				this.temptedEntity.limbSwingAmount = 50.0f;

				if (this.temptedEntity instanceof EntityAnimaniaChicken) {
					EntityAnimaniaChicken entity = (EntityAnimaniaChicken) this.temptedEntity;
					entity.setFed(true);
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

			for (int i = -16; i < 16; i++)
				for (int j = -3; j < 3; j++)
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockSeeds) {
							foodFound = true;

							if (rand.nextInt(200) == 0) {
								this.delayTemptCounter = 0;
								return false;
							}
							else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
									&& this.temptedEntity.motionZ == 0) {
								this.delayTemptCounter = 0;
								return false;
							}
							else
								return true;
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
	public boolean continueExecuting() {

		return this.shouldExecute();
	}

	@Override
	public void startExecuting() {
		this.isRunning = true;
	}

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

		for (int i = -16; i < 16; i++)
			for (int j = -3; j < 3; j++)
				for (int k = -16; k < 16; k++) {

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

					if (blockchk == BlockHandler.blockSeeds) {
						foodFound = true;
						newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);
						mudPos = new BlockPos(x + i, y + j, z + k);

					}
				}

		if (foodFound) {

			Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPos).getBlock();

			if (mudBlockchk == BlockHandler.blockSeeds)
				this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + .5, mudPos.getY(), mudPos.getZ() + .5, this.speed);
		}

	}

	public boolean isRunning() {
		return this.isRunning;
	}
}