package com.animania.addons.farm.common.entity.horses.ai;

import java.util.List;

import com.animania.addons.farm.common.entity.pullables.EntityCart;
import com.animania.addons.farm.common.entity.pullables.EntityWagon;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILookIdleHorses extends EntityAIBase
{
	/** The entity that is looking idle. */
	private final CreatureEntity idleEntity;
	/** X offset to look at */
	private double lookX;
	/** Z offset to look at */
	private double lookZ;
	/**
	 * A decrementing tick that stops the entity from being idle once it reaches
	 * 0.
	 */
	private int idleTime;
	private int delayCounter;

	public EntityAILookIdleHorses(CreatureEntity LivingEntityIn)
	{
		this.idleEntity = LivingEntityIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{

		if (!this.idleentity.level.isDaytime())
		{
			this.delayCounter = 0;
			return false;
		}

		boolean pullingFlag = false;
		List entities = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, idleentity.level, idleEntity);
		if (!entities.isEmpty())
		{
			EntityCart checkCart = (EntityCart) entities.get(0);
			if (checkCart.puller == idleEntity)
			{
				pullingFlag = true;
			}
		}

		entities = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, idleentity.level, idleEntity);
		if (!entities.isEmpty())
		{
			EntityWagon checkWagon = (EntityWagon) entities.get(0);
			if (checkWagon.puller == idleEntity)
			{
				pullingFlag = true;
			}
		}

		return this.idleEntity.getRNG().nextFloat() < 0.02F;

	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean shouldContinueExecuting()
	{
		return this.idleTime >= 0;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
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
	@Override
	public void updateTask()
	{
		--this.idleTime;
		this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.getX() + this.lookX, this.idleEntity.getY() + this.idleEntity.getEyeHeight(), this.idleEntity.getZ() + this.lookZ, this.idleEntity.getHorizontalFaceSpeed(), this.idleEntity.getVerticalFaceSpeed());
	}
}
