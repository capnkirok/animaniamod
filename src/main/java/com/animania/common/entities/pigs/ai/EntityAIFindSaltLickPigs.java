package com.animania.common.entities.pigs.ai;

import java.util.Random;

import com.animania.common.blocks.BlockSaltLick;
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
import net.minecraft.world.biome.Biome;

public class EntityAIFindSaltLickPigs extends EntityAIBase 
{
	private final EntityAnimaniaPig entityIn;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;

	public EntityAIFindSaltLickPigs(EntityAnimaniaPig temptedEntityIn, double speedIn)
	{
		this.entityIn = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{

		delayTemptCounter++;
		//System.out.println(delayTemptCounter);
		if (delayTemptCounter >  AnimaniaConfig.careAndFeeding.saltLickTick || entityIn.getHealth() < entityIn.getMaxHealth()) {
			
			if (!entityIn.world.isDaytime() || entityIn.getSleeping()) {
				this.delayTemptCounter = 0;
				return false;
			}
			
			if (this.entityIn.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entityIn, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.resetTask();
					this.entityIn.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
				}
				return false;
			}

			Random rand = new Random();

			BlockPos currentpos = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ);
			BlockPos trypos1 = new BlockPos(entityIn.posX + 1, entityIn.posY, entityIn.posZ);
			BlockPos trypos2 = new BlockPos(entityIn.posX - 1, entityIn.posY, entityIn.posZ);
			BlockPos trypos3 = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ + 1);
			BlockPos trypos4 = new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ - 1);

			Block poschk = entityIn.world.getBlockState(currentpos).getBlock();
			Block poschk1 = entityIn.world.getBlockState(trypos1).getBlock();
			Block poschk2 = entityIn.world.getBlockState(trypos2).getBlock();
			Block poschk3 = entityIn.world.getBlockState(trypos3).getBlock();
			Block poschk4 = entityIn.world.getBlockState(trypos4).getBlock();

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

			poschk = entityIn.world.getBlockState(currentpos).getBlock(); 

			if (entityIn instanceof EntityAnimaniaPig && poschk == BlockHandler.blockSaltLick) {
				EntityAnimaniaPig ech = (EntityAnimaniaPig)entityIn;
				ech.entityAIEatGrass.startExecuting();
				BlockSaltLick salt = (BlockSaltLick)poschk;
				salt.useSaltLick(this.entityIn.world, currentpos, this.entityIn);
				this.resetTask();
				this.delayTemptCounter = 0;
				return false;
			} 

			double x = this.entityIn.posX;
			double y = this.entityIn.posY;
			double z = this.entityIn.posZ;

			boolean waterFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = entityIn.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockSaltLick && entityIn.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1.0)) {

							waterFound = true;
							return true;

						}
					}
				}
			}


			if (!waterFound) {
				this.delayTemptCounter = 0;
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entityIn, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.entityIn.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
					this.resetTask();
				}
				return false;
			}

		}

		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return !this.entityIn.getNavigator().noPath();
	}
	
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.entityIn.getNavigator().clearPath();
		this.isRunning = false;
	}

	public void startExecuting()
	{

		double x = this.entityIn.posX;
		double y = this.entityIn.posY;
		double z = this.entityIn.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = entityIn.world.getBlockState(currentpos).getBlock();

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
						Block blockchk = entityIn.world.getBlockState(pos).getBlock();

						Biome biomegenbase = entityIn.world.getBiome(pos); 

						if (blockchk == BlockHandler.blockSaltLick) {
							waterFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (entityIn.posX < saltPos.getX()) {
									BlockPos saltPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block waterBlockchk = entityIn.world.getBlockState(saltPoschk).getBlock();
									if (waterBlockchk == BlockHandler.blockSaltLick ) {
										i = i + 1;
									}
								} 

								if (entityIn.posZ < saltPos.getZ()) {
									BlockPos saltPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block waterBlockchk = entityIn.world.getBlockState(saltPoschk).getBlock();
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

				Block waterBlockchk = entityIn.world.getBlockState(saltPos).getBlock();

				if (waterBlockchk == BlockHandler.blockSaltLick && !this.entityIn.hasPath()) {
					if(this.entityIn.getNavigator().tryMoveToXYZ(saltPos.getX(), saltPos.getY(), saltPos.getZ(), this.speed) == false) {
						this.resetTask();
					} else {
						this.entityIn.getNavigator().tryMoveToXYZ(saltPos.getX(), saltPos.getY(), saltPos.getZ(), this.speed);
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