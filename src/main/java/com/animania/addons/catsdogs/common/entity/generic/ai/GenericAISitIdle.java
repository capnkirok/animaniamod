package com.animania.addons.catsdogs.common.entity.generic.ai;

import com.animania.addons.catsdogs.common.entity.felids.EntityAnimaniaCat;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.EntityAIBase;

public class GenericAISitIdle extends EntityAIBase
{
	/** The entity that is looking idle. */
	private final LivingEntity idleEntity;
	/** X offset to look at */
	private double lookX;
	/** Z offset to look at */
	private double lookZ;
	/** A decrementing tick that stops the entity from being idle once it reaches 0. */
	private int idleTime;

	public GenericAISitIdle(LivingEntity LivingEntityIn)
	{
		this.idleEntity = LivingEntityIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		return this.idleEntity.getRNG().nextFloat() < 0.002F;
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
		this.idleTime = 50 + this.idleEntity.getRNG().nextInt(20);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void updateTask()
	{
		--this.idleTime;
		if (this.idleEntity instanceof EntityAnimaniaCat) {
			EntityAnimaniaCat entityCat = (EntityAnimaniaCat)this.idleEntity;
			if (!entityCat.isSitting() && this.idleTime > 0) {
				entityCat.setSitting(true);
			} else if (entityCat.isSitting() && this.idleTime == 0) {
				entityCat.setSitting(false);
			}
		} 
		this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.getX() + this.lookX, this.idleEntity.getY() + (double)this.idleEntity.getEyeHeight(), this.idleEntity.getZ() + this.lookZ, (float)this.idleEntity.getHorizontalFaceSpeed(), (float)this.idleEntity.getVerticalFaceSpeed());
	}
}