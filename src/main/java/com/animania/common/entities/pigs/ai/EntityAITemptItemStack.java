package com.animania.common.entities.pigs.ai;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAITemptItemStack extends EntityAIBase
{
	/** The entity using this AI that is tempted by the player. */
	private final EntityCreature temptedEntity;
	private final double speed;
	/** X position of player tempting this mob */
	private double targetX;
	/** Y position of player tempting this mob */
	private double targetY;
	/** Z position of player tempting this mob */
	private double targetZ;
	/** Tempting player's pitch */
	private double pitch;
	/** Tempting player's yaw */
	private double yaw;
	/** The player that is tempting the entity that is using this AI. */
	private EntityPlayer temptingPlayer;
	/**
	 * A counter that is decremented each time the shouldExecute method is called. The shouldExecute method will always
	 * return false if delayTemptCounter is greater than 0.
	 */
	private int delayTemptCounter;
	/** True if this EntityAITempt task is running */
	private boolean isRunning;
	private final ItemStack temptItem;
	/** Whether the entity using this AI will be scared by the tempter's sudden movement. */



	public EntityAITemptItemStack(EntityCreature temptedEntityIn, double speedIn, ItemStack temptItemIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.temptItem = temptItemIn;
		this.setMutexBits(3);

		if (!(temptedEntityIn.getNavigator() instanceof PathNavigateGround))
		{
			throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
		}
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		if (this.delayTemptCounter > 0)
		{
			--this.delayTemptCounter;
			return false;
		}
		else
		{
			this.temptingPlayer = this.temptedEntity.world.getClosestPlayerToEntity(this.temptedEntity, 10.0D);
			return this.temptingPlayer == null ? false : this.isTempting(this.temptingPlayer.getHeldItemMainhand()) || this.isTempting(this.temptingPlayer.getHeldItemOffhand());
		}
	}

	protected boolean isTempting(ItemStack stack)
	{
		return ItemStack.areItemStacksEqual(stack, temptItem);
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		this.targetX = this.temptingPlayer.posX;
		this.targetY = this.temptingPlayer.posY;
		this.targetZ = this.temptingPlayer.posZ;
		this.isRunning = true;
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.delayTemptCounter = 100;
		this.isRunning = false;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, (float)(this.temptedEntity.getHorizontalFaceSpeed() + 20), (float)this.temptedEntity.getVerticalFaceSpeed());

		if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D)
		{
			this.temptedEntity.getNavigator().clearPathEntity();
		}
		else
		{
			this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.speed);
		}
	}

	/**
	 * @see #isRunning
	 */
	public boolean isRunning()
	{
		return this.isRunning;
	}


}
