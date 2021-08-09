package com.animania.addons.farm.common.entity.pigs.ai;

import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAITemptItemStack extends Goal
{
	private final EntityAnimaniaPig temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private PlayerEntity temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;
	private final ItemStack temptItem;

	public EntityAITemptItemStack(EntityAnimaniaPig temptedEntityIn, double speedIn, ItemStack temptItemIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.temptItem = temptItemIn;
		this.setMutexBits(3);

		if (!(temptedEntityIn.getNavigator() instanceof PathNavigateGround))
			throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.delayTemptCounter > 0)
		{
			--this.delayTemptCounter;
			return false;
		}
		else if (this.temptedEntity.getSleeping())
		{
			this.delayTemptCounter = AnimaniaConfig.gameRules.ticksBetweenAIFirings;
			return false;
		}
		else
		{

			this.temptingPlayer = this.temptedentity.level.getClosestPlayerToEntity(this.temptedEntity, 10.0D);
			return this.temptingPlayer == null ? false : this.isTempting(this.temptingPlayer.getHeldItemMainhand()) || this.isTempting(this.temptingPlayer.getHeldItemOffhand());
		}
	}

	protected boolean isTempting(ItemStack stack)
	{
		return ItemStack.areItemStacksEqual(stack, this.temptItem);
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return this.shouldExecute();
	}

	@Override
	public void startExecuting()
	{
		this.targetX = this.temptingPlayer.getX();
		this.targetY = this.temptingPlayer.getY();
		this.targetZ = this.temptingPlayer.getZ();
		this.isRunning = true;
	}

	@Override
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPath();
		this.delayTemptCounter = AnimaniaConfig.gameRules.ticksBetweenAIFirings;
		this.isRunning = false;
	}

	@Override
	public void updateTask()
	{
		if (temptedEntity != null && this.temptingPlayer != null)
			this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, this.temptedEntity.getHorizontalFaceSpeed() + 20, this.temptedEntity.getVerticalFaceSpeed());

		if (this.temptedEntity.getDistanceSq(this.temptingPlayer) < 6.25D)
			this.temptedEntity.getNavigator().clearPath();
		else
			this.temptedEntity.getNavigator().tryMoveToLivingEntity(this.temptingPlayer, this.speed);
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}

}
