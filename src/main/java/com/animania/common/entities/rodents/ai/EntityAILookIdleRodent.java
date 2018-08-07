package com.animania.common.entities.rodents.ai;

import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;

public class EntityAILookIdleRodent extends EntityAIBase
{
	private final EntityAnimal idleEntity;
	private double             lookX;
	private double             lookZ;
	private int                idleTime;

	public EntityAILookIdleRodent(EntityAnimal entitylivingIn) {
		this.idleEntity = entitylivingIn;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		
		if (this.idleEntity instanceof EntityHamster) {
			EntityHamster er = (EntityHamster) this.idleEntity;
			if (er.getSleeping()) {
				return false;
			}
		}

		if (this.idleEntity instanceof EntityFerretBase) {
			EntityFerretBase er = (EntityFerretBase) this.idleEntity;
			if (er.getSleeping()) {
				return false;
			}
		}

		if (this.idleEntity instanceof EntityHedgehogBase) {
			EntityHedgehogBase er = (EntityHedgehogBase) this.idleEntity;
			if (er.getSleeping()) {
				return false;
			}
		}
		
		return this.idleEntity.getRNG().nextFloat() < 0.02F && !this.idleEntity.isRiding();
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.idleTime >= 0;
	}

	@Override
	public void startExecuting() {
		double d0 = Math.PI * 2D * this.idleEntity.getRNG().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
	}

	@Override
	public void updateTask() {
		--this.idleTime;
		this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + this.idleEntity.getEyeHeight(),
				this.idleEntity.posZ + this.lookZ, this.idleEntity.getHorizontalFaceSpeed(), this.idleEntity.getVerticalFaceSpeed());
	}
}