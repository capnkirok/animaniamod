package com.animania.common.entities.rodents.ai;

import java.util.Random;

import com.animania.common.entities.rodents.EntityHamster;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIWanderRodent extends EntityAIBase
{
	private final EntityCreature entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private final double speed;
	private int executionChance;
	private boolean mustUpdate;

	public EntityAIWanderRodent(EntityCreature creatureIn, double speedIn)
	{
		this(creatureIn, speedIn, 120);
	}

	public EntityAIWanderRodent(EntityCreature creatureIn, double speedIn, int chance)
	{
		this.entity = creatureIn;
		this.speed = speedIn;
		this.executionChance = chance;
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		
		if (this.entity.isRiding()) {
			return false;
		}
		
		if (!this.mustUpdate)
		{

			if (this.entity.getRNG().nextInt(this.executionChance) != 0)
			{
				return false;
			}
		}
		
		Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

		if (vec3d == null)
		{
			return false;
		}
		else
		{
			this.xPosition = vec3d.xCoord;
			this.yPosition = vec3d.yCoord;
			this.zPosition = vec3d.zCoord;
			this.mustUpdate = false;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting()
	{
		return !this.entity.getNavigator().noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{

		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;

		BlockPos pos = new BlockPos(x, y, z);

		Block blockchk = entity.world.getBlockState(pos).getBlock();

		Random rand = new Random();

		this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);

	}

	/**
	 * Makes task to bypass chance
	 */
	public void makeUpdate()
	{
		this.mustUpdate = true;
	}

	/**
	 * Changes task random possibility for execution
	 */
	public void setExecutionChance(int newchance)
	{
		this.executionChance = newchance;
	}
}