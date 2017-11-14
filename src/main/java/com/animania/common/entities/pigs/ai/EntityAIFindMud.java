package com.animania.common.entities.pigs.ai;

import java.util.Random;

import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntityPigletBase;
import com.animania.common.entities.pigs.EntitySowBase;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class EntityAIFindMud extends EntityAIBase
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

	public EntityAIFindMud(EntityCreature temptedEntityIn, double speedIn) {
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	@Override
	public boolean shouldExecute() {

		delayTemptCounter++;
		if (this.delayTemptCounter < 100) {
			return false;
		} else if (delayTemptCounter > 100) {
			if (this.temptedEntity instanceof EntityAnimaniaPig) {
				EntityAnimaniaPig pig = (EntityAnimaniaPig) temptedEntity;
				if (pig.getPlayed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} 

			Random rand = new Random();
			BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
			Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
			if (poschk == BlockHandler.blockMud) {
				if (temptedEntity instanceof EntityAnimaniaPig) {
					EntityAnimaniaPig pig = (EntityAnimaniaPig) temptedEntity;
					pig.entityAIEatGrass.startExecuting();
					pig.setPlayed(true);
				} 
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean mudFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
				for (int j = -2; j < 2; j++)
					for (int k = -10; k < 10; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk != null && blockchk == BlockHandler.blockMud) {
							mudFound = true;
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

			if (!mudFound) {
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

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
		if (poschk != BlockHandler.blockMud) {

			boolean mudFound = false;
			boolean spcFlag = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos mudPos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
				for (int j = -2; j < 2; j++)
					for (int k = -10; k < 10; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();
						if (blockchk == BlockHandler.blockMud && !this.temptedEntity.hasPath()) {
							mudFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (this.temptedEntity.posX > mudPos.getX()) {
									BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
									if (mudBlockchk == BlockHandler.blockMud) {
										spcFlag = true;
										i = i + 1;
									}
								}

								if (this.temptedEntity.posZ > mudPos.getZ()) {
									BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
									if (mudBlockchk == BlockHandler.blockMud) {
										spcFlag = true;
										k = k + 1;
									}
								}

								mudPos = new BlockPos(x + i, y + j, z + k);

								if (spcFlag) {
									i = 10;
									j = 3;
									k = 10;
								}

							}

						}

					}

			if (mudFound) {

				Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPos).getBlock();
				if (mudBlockchk == BlockHandler.blockMud)
					if (this.temptedEntity instanceof EntitySowBase) {
						EntitySowBase te = (EntitySowBase) this.temptedEntity;

						if (te.getMuddy() == false) {
							if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
								this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
							else
								this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

							this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

						}
					}
					else if (this.temptedEntity instanceof EntityPigletBase) {
						EntityPigletBase te = (EntityPigletBase) this.temptedEntity;
						if (te.getMuddy() == false)
							if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
								this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
							else
								this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
					}
					else if (this.temptedEntity instanceof EntityHogBase) {
						EntityHogBase te = (EntityHogBase) this.temptedEntity;
						if (te.getMuddy() == false)
							if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
								this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
							else if (te.posX - mudPos.getX() > 0 && te.posZ - mudPos.getZ() > 0)
								this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
							else
								this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
					}
			}
		}
	}

	public boolean isRunning() {
		return this.isRunning;
	}
}