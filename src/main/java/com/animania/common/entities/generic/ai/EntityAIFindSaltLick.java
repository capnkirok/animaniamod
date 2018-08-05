package com.animania.common.entities.generic.ai;

import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFindSaltLick extends EntityAIBase 
{
	private final EntityCreature entityIn;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;

	public EntityAIFindSaltLick(EntityCreature entityInIn, double speedIn)
	{
		this.entityIn = entityInIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{
		delayTemptCounter++;
		
		if (delayTemptCounter >  AnimaniaConfig.careAndFeeding.saltLickTick || entityIn.getHealth() < entityIn.getMaxHealth()) {
			
			this.delayTemptCounter = 0;			
			
			
			boolean isSleeping = false;
			if (this.entityIn instanceof EntityAnimaniaCow) {
				EntityAnimaniaCow entityCow = (EntityAnimaniaCow) this.entityIn;
				if (entityCow.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn instanceof EntityAnimaniaChicken) {
				EntityAnimaniaChicken entityChk = (EntityAnimaniaChicken) this.entityIn;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn instanceof EntityAnimaniaHorse) {
				EntityAnimaniaHorse entityChk = (EntityAnimaniaHorse) this.entityIn;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn instanceof EntityAnimaniaGoat) {
				EntityAnimaniaGoat entityChk = (EntityAnimaniaGoat) this.entityIn;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn  instanceof EntityAnimaniaPeacock) {
				EntityAnimaniaPeacock entityChk = (EntityAnimaniaPeacock) this.entityIn ;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn  instanceof EntityAnimaniaPig) {
				EntityAnimaniaPig entityChk = (EntityAnimaniaPig) this.entityIn ;
				BlockPos currentpos = new BlockPos(entityChk.posX, entityChk.posY, entityChk.posZ);
				Block poschk = entityChk.world.getBlockState(currentpos.down()).getBlock();
				boolean isMuddy = false;
				if (poschk == BlockHandler.blockMud || poschk.getUnlocalizedName().equals("tile.mud")) {
					isMuddy = true;
				}
				if (entityChk.getSleeping() || isMuddy) {
					isSleeping = true;
				}
			}

			if (this.entityIn  instanceof EntityAnimaniaSheep) {
				EntityAnimaniaSheep entityChk = (EntityAnimaniaSheep) this.entityIn ;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn  instanceof EntityAnimaniaRabbit) {
				EntityAnimaniaRabbit entityChk = (EntityAnimaniaRabbit) this.entityIn ;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn  instanceof EntityHedgehogBase) {
				EntityHedgehogBase entityChk = (EntityHedgehogBase) this.entityIn ;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn  instanceof EntityFerretBase) {
				EntityFerretBase entityChk = (EntityFerretBase) this.entityIn ;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (this.entityIn  instanceof EntityHamster) {
				EntityHamster entityChk = (EntityHamster) this.entityIn ;
				if (entityChk.getSleeping()) {
					isSleeping = true;
				}
			}

			if (isSleeping) {
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

			if (entityIn instanceof EntityAnimaniaCow && poschk == BlockHandler.blockSaltLick) {
				EntityAnimaniaCow ech = (EntityAnimaniaCow)entityIn;
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
		this.delayTemptCounter = 0;
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
						this.delayTemptCounter = 0;
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