package com.animania.common.entities.horses.ai;


import java.util.List;

import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILookIdleHorses extends EntityAIBase
{
	/** The entity that is looking idle. */
	private final EntityCreature idleEntity;
	/** X offset to look at */
	private double lookX;
	/** Z offset to look at */
	private double lookZ;
	/** A decrementing tick that stops the entity from being idle once it reaches 0. */
	private int idleTime;
	private int delayCounter;

	public EntityAILookIdleHorses(EntityCreature entitylivingIn)
	{
		this.idleEntity = entitylivingIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		
		if (!this.idleEntity.world.isDaytime()) {
			this.delayCounter = 0;
			return false;
		}
		
		
		boolean pullingFlag = false;
		List entities = AnimaniaHelper.getCartsInRange(EntityCart.class, 3, idleEntity.world, idleEntity);
		if (!entities.isEmpty()) {
			EntityCart checkCart = (EntityCart) entities.get(0);
			if (checkCart.puller == idleEntity) {
				pullingFlag = true;
			}
		} 

		entities = AnimaniaHelper.getWagonsInRange(EntityWagon.class, 3, idleEntity.world, idleEntity);
		if (!entities.isEmpty()) {
			EntityWagon checkWagon = (EntityWagon) entities.get(0);
			if (checkWagon.puller == idleEntity) {
				pullingFlag = true;
			}
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
