package com.animania.common.entities.generic.ai;


import com.animania.common.entities.ISleeping;
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

public class GenericAILookIdle extends EntityAIBase
{
	/** The entity that is looking idle. */
	private final EntityLiving entity;
	/** X offset to look at */
	private double lookX;
	/** Z offset to look at */
	private double lookZ;
	/** A decrementing tick that stops the entity from being idle once it reaches 0. */
	private int idleTime;

	public GenericAILookIdle(EntityLiving entitylivingIn)
	{
		this.entity = entitylivingIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		boolean isSleeping = false;
		if(this.entity instanceof ISleeping)
    	{
    		if(((ISleeping) entity).getSleeping())
    		{
    			isSleeping = true;
    		}
    		
    		if(entity instanceof EntityAnimaniaPig)
    		{
    			if(entity.world.getBlockState(entity.getPosition().down()).getBlock() == BlockHandler.blockMud)
    				isSleeping = true;
    		}
    	}

		if (isSleeping) {
			return false;
		}

		return this.entity.getRNG().nextFloat() < 0.02F;
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
		double d0 = (Math.PI * 2D) * this.entity.getRNG().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.entity.getRNG().nextInt(20);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void updateTask()
	{
		--this.idleTime;
		this.entity.getLookHelper().setLookPosition(this.entity.posX + this.lookX, this.entity.posY + (double)this.entity.getEyeHeight(), this.entity.posZ + this.lookZ, (float)this.entity.getHorizontalFaceSpeed(), (float)this.entity.getVerticalFaceSpeed());
	}
}