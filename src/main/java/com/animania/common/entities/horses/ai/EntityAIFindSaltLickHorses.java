package com.animania.common.entities.horses.ai;

import java.util.Random;

import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class EntityAIFindSaltLickHorses extends EntityAIBase 
{
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

	public EntityAIFindSaltLickHorses(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{

		delayTemptCounter++;
		//System.out.println(delayTemptCounter);
		if (delayTemptCounter >  AnimaniaConfig.careAndFeeding.saltLickTick || temptedEntity.getHealth() < temptedEntity.getMaxHealth()) {
			if (temptedEntity instanceof EntityAnimaniaHorse) {
				EntityAnimaniaHorse ech = (EntityAnimaniaHorse)temptedEntity;
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

			if (poschk == BlockHandler.blockSaltLick) {
				//do nothing
			} else if (poschk1 == BlockHandler.blockSaltLick) {
				currentpos = trypos1;
			} else if (poschk2 == BlockHandler.blockSaltLick) {
				currentpos = trypos2;
			} else if (poschk3 == BlockHandler.blockSaltLick) {
				currentpos = trypos3;
			} else if (poschk4 == BlockHandler.blockSaltLick) {
				currentpos = trypos4;
			}

			poschk = temptedEntity.world.getBlockState(currentpos).getBlock(); 

			if (temptedEntity instanceof EntityAnimaniaHorse && poschk == BlockHandler.blockSaltLick) {
				EntityAnimaniaHorse ech = (EntityAnimaniaHorse)temptedEntity;
				ech.entityAIEatGrass.startExecuting();
				BlockSaltLick salt = (BlockSaltLick)poschk;
				salt.useSaltLick(this.temptedEntity.world, currentpos, this.temptedEntity);
				this.resetTask();
				this.delayTemptCounter = 0;
				return false;
				//ech.setWatered(true);
			} 


			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean waterFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockSaltLick) {
							waterFound = true;
							return true;

						}
					}
				}
			}


			if (!waterFound) {
				this.delayTemptCounter = 0;
				return false;
			}

		}
		this.delayTemptCounter = 0;
		return false;
	}

	public boolean shouldContinueExecuting()
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

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();

		if (poschk != BlockHandler.blockSaltLick) {

			boolean waterFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos saltPos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						Biome biomegenbase = temptedEntity.world.getBiome(pos); 

						if (blockchk == BlockHandler.blockSaltLick) {
							waterFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (temptedEntity.posX < saltPos.getX()) {
									BlockPos saltPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block waterBlockchk = temptedEntity.world.getBlockState(saltPoschk).getBlock();
									if (waterBlockchk == BlockHandler.blockSaltLick ) {
										i = i + 1;
									}
								} 

								if (temptedEntity.posZ < saltPos.getZ()) {
									BlockPos saltPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block waterBlockchk = temptedEntity.world.getBlockState(saltPoschk).getBlock();
									if (waterBlockchk == BlockHandler.blockSaltLick ) {
										k = k + 1;
									} 
								}

								saltPos = new BlockPos(x + i, y + j, z + k);

							}
						} 
					}
				}
			}

			if (waterFound) {

				Block waterBlockchk = temptedEntity.world.getBlockState(saltPos).getBlock();

				if (waterBlockchk == BlockHandler.blockSaltLick && !this.temptedEntity.hasPath()) {
					if(this.temptedEntity.getNavigator().tryMoveToXYZ(saltPos.getX(), saltPos.getY(), saltPos.getZ(), this.speed) == false) {
						this.resetTask();
					} else {
						this.temptedEntity.getNavigator().tryMoveToXYZ(saltPos.getX(), saltPos.getY(), saltPos.getZ(), this.speed);
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