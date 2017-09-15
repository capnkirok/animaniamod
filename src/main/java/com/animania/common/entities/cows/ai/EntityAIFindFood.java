package com.animania.common.entities.cows.ai;

import java.util.Random;

import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

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
	private boolean              isRunning;
	private int                  delayTemptCounter;

	public EntityAIFindFood(EntityCreature temptedEntityIn, double speedIn) {
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	@Override
	public boolean shouldExecute() {

		delayTemptCounter++;
		if (this.delayTemptCounter <= 32) {
			return false;
		} else if (delayTemptCounter > 32) {
			if (temptedEntity instanceof EntityAnimaniaCow) {
				EntityAnimaniaCow ech = (EntityAnimaniaCow)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} 

			BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
			BlockPos trypos1 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ);
			BlockPos trypos2 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ);
			BlockPos trypos3 = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
			BlockPos trypos4 = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
			BlockPos trypos5 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
			BlockPos trypos6 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
			BlockPos trypos7 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
			BlockPos trypos8 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
			Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = this.temptedEntity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = this.temptedEntity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = this.temptedEntity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = this.temptedEntity.world.getBlockState(trypos4).getBlock();
			Block poschk5 = this.temptedEntity.world.getBlockState(trypos5).getBlock();
			Block poschk6 = this.temptedEntity.world.getBlockState(trypos6).getBlock();
			Block poschk7 = this.temptedEntity.world.getBlockState(trypos7).getBlock();
			Block poschk8 = this.temptedEntity.world.getBlockState(trypos8).getBlock();

			if (poschk == BlockHandler.blockTrough) {
				// do nothing
			}
			else if (poschk1 == BlockHandler.blockTrough)
				currentpos = trypos1;
			else if (poschk2 == BlockHandler.blockTrough)
				currentpos = trypos2;
			else if (poschk3 == BlockHandler.blockTrough)
				currentpos = trypos3;
			else if (poschk4 == BlockHandler.blockTrough)
				currentpos = trypos4;
			else if (poschk5 == BlockHandler.blockTrough)
				currentpos = trypos5;
			else if (poschk6 == BlockHandler.blockTrough)
				currentpos = trypos6;
			else if (poschk7 == BlockHandler.blockTrough)
				currentpos = trypos7;
			else if (poschk8 == BlockHandler.blockTrough)
				currentpos = trypos8;

			if (poschk == BlockHandler.blockTrough || poschk1 == BlockHandler.blockTrough || poschk2 == BlockHandler.blockTrough || poschk3 == BlockHandler.blockTrough || poschk4 == BlockHandler.blockTrough || poschk5 == BlockHandler.blockTrough || poschk6 == BlockHandler.blockTrough || poschk7 == BlockHandler.blockTrough || poschk8 == BlockHandler.blockTrough) {
				TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(currentpos);
				if (te != null && te.canConsume(EntityAnimaniaCow.TEMPTATION_ITEMS, null)) {
					te.consumeSolid(1);

					if (this.temptedEntity instanceof EntityAnimaniaCow) {
						EntityAnimaniaCow ech = (EntityAnimaniaCow) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					}
					
					return false;

				}

			}

			if (poschk == Blocks.RED_FLOWER || poschk == Blocks.CARROTS || poschk == Blocks.WHEAT || poschk == Blocks.YELLOW_FLOWER) {

				if (temptedEntity instanceof EntityAnimaniaCow) {
					EntityAnimaniaCow ech = (EntityAnimaniaCow)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} 

				temptedEntity.world.destroyBlock(currentpos, false);

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

						if (blockchk == BlockHandler.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);
							if (te != null && te.canConsume(EntityAnimaniaCow.TEMPTATION_ITEMS, null)) {
								foodFound = true;
								if (rand.nextInt(20) == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								}
								else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
										&& this.temptedEntity.motionZ == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								}
								else
									return true;
							}
						}

						if (blockchk == Blocks.RED_FLOWER || blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT
								|| blockchk == Blocks.YELLOW_FLOWER) {

							foodFound = true;
							if (rand.nextInt(20) == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							}
							else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
									&& this.temptedEntity.motionZ == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							}
							else
								return true;
						}

					}

			if (!foodFound)
				this.delayTemptCounter = 0;
			return false;
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

					if (blockchk == BlockHandler.blockTrough) {

						TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);

						if (te != null && te.canConsume(EntityAnimaniaCow.TEMPTATION_ITEMS, null)) {

							foodFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (this.temptedEntity.posX < mudPos.getX()) {
									BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
									i = i + 1;
								}

								if (this.temptedEntity.posZ < mudPos.getZ()) {
									BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
									k = k + 1;
								}

								mudPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					}
					else if (blockchk == Blocks.RED_FLOWER || blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT
							|| blockchk == Blocks.YELLOW_FLOWER) {
						foodFound = true;
						newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

						if (newloc < loc) {

							loc = newloc;

							if (this.temptedEntity.posX < mudPos.getX()) {
								BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
								if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT
										|| mudBlockchk == Blocks.YELLOW_FLOWER)
									i = i + 1;
							}

							if (this.temptedEntity.posZ < mudPos.getZ()) {
								BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
								if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT
										|| mudBlockchk == Blocks.YELLOW_FLOWER)
									k = k + 1;
							}

							mudPos = new BlockPos(x + i, y + j, z + k);

						}

					}

				}

		if (foodFound) {

			Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPos).getBlock();
			if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT
					|| mudBlockchk == Blocks.YELLOW_FLOWER || mudBlockchk == BlockHandler.blockTrough)
				this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
		}

	}

	public boolean isRunning() {
		return this.isRunning;
	}
}