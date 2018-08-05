package com.animania.common.entities.generic.ai;


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

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

public class EntityAILookIdle extends EntityAIBase
{
	/** The entity that is looking idle. */
	private final EntityLiving idleEntity;
	/** X offset to look at */
	private double lookX;
	/** Z offset to look at */
	private double lookZ;
	/** A decrementing tick that stops the entity from being idle once it reaches 0. */
	private int idleTime;

	public EntityAILookIdle(EntityLiving entitylivingIn)
	{
		this.idleEntity = entitylivingIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		boolean isSleeping = false;
		if (this.idleEntity instanceof EntityAnimaniaCow) {
			EntityAnimaniaCow entityCow = (EntityAnimaniaCow) this.idleEntity;
			if (entityCow.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity instanceof EntityAnimaniaChicken) {
			EntityAnimaniaChicken entityChk = (EntityAnimaniaChicken) this.idleEntity;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity instanceof EntityAnimaniaHorse) {
			EntityAnimaniaHorse entityChk = (EntityAnimaniaHorse) this.idleEntity;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity instanceof EntityAnimaniaGoat) {
			EntityAnimaniaGoat entityChk = (EntityAnimaniaGoat) this.idleEntity;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity  instanceof EntityAnimaniaPeacock) {
			EntityAnimaniaPeacock entityChk = (EntityAnimaniaPeacock) this.idleEntity ;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity  instanceof EntityAnimaniaPig) {
			EntityAnimaniaPig entityChk = (EntityAnimaniaPig) this.idleEntity ;
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

		if (this.idleEntity  instanceof EntityAnimaniaSheep) {
			EntityAnimaniaSheep entityChk = (EntityAnimaniaSheep) this.idleEntity ;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity  instanceof EntityAnimaniaRabbit) {
			EntityAnimaniaRabbit entityChk = (EntityAnimaniaRabbit) this.idleEntity ;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity  instanceof EntityHedgehogBase) {
			EntityHedgehogBase entityChk = (EntityHedgehogBase) this.idleEntity ;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity  instanceof EntityFerretBase) {
			EntityFerretBase entityChk = (EntityFerretBase) this.idleEntity ;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (this.idleEntity  instanceof EntityHamster) {
			EntityHamster entityChk = (EntityHamster) this.idleEntity ;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}

		if (isSleeping) {
			return false;
		}

		return this.idleEntity.getRNG().nextFloat() < 0.02F;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting()
	{
		return this.idleTime >= 0;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		double d0 = (Math.PI * 2D) * this.idleEntity.getRNG().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void updateTask()
	{
		--this.idleTime;
		this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + (double)this.idleEntity.getEyeHeight(), this.idleEntity.posZ + this.lookZ, (float)this.idleEntity.getHorizontalFaceSpeed(), (float)this.idleEntity.getVerticalFaceSpeed());
	}
}