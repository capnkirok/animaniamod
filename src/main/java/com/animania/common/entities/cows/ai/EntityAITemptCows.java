package com.animania.common.entities.cows.ai;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.common.entities.cows.EntityBullBase;
import com.google.common.collect.Sets;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAITemptCows extends EntityAIBase
{
	private final EntityCreature temptedEntity;
	private final double         speed;
	private double               targetX;
	private double               targetY;
	private double               targetZ;
	private double               pitch;
	private double               yaw;
	private EntityPlayer         temptingPlayer;
	private int                  delayTemptCounter;
	private boolean              isRunning;
	private final Set<Item>      temptItem;
	private final boolean        scaredByPlayerMovement;

	public EntityAITemptCows(EntityCreature temptedEntityIn, double speedIn, Item temptItemIn, boolean scaredByPlayerMovementIn) {
		this(temptedEntityIn, speedIn, scaredByPlayerMovementIn, Sets.newHashSet(new Item[] { temptItemIn }));
	}

	public EntityAITemptCows(EntityCreature temptedEntityIn, double speedIn, boolean scaredByPlayerMovementIn, Set<Item> temptItemIn) {
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.temptItem = temptItemIn;
		this.scaredByPlayerMovement = scaredByPlayerMovementIn;
		this.setMutexBits(3);

		if (!(temptedEntityIn.getNavigator() instanceof PathNavigateGround))
			throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
	}

	@Override
	public boolean shouldExecute() {

		if (this.temptedEntity instanceof EntityBullBase) {
			EntityBullBase ebc = (EntityBullBase) this.temptedEntity;
			if (ebc.getFighting()) {
				return false;
			}
		}

		if (this.delayTemptCounter > 0) {
			--this.delayTemptCounter;
			return false;
		} else {
			this.temptingPlayer = this.temptedEntity.world.getClosestPlayerToEntity(this.temptedEntity, 10.0D);
			return this.temptingPlayer == null ? false
					: this.isTempting(this.temptingPlayer.getHeldItemMainhand()) || this.isTempting(this.temptingPlayer.getHeldItemOffhand());
		}

	}

	protected boolean isTempting(@Nullable ItemStack stack) {
		return stack == null ? false : this.temptItem.contains(stack.getItem());
	}

	@Override
	public boolean continueExecuting() {
		if (this.scaredByPlayerMovement) {
			if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0D) {
				if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D)
					return false;

				if (Math.abs(this.temptingPlayer.rotationPitch - this.pitch) > 5.0D || Math.abs(this.temptingPlayer.rotationYaw - this.yaw) > 5.0D)
					return false;
			}
			else {
				this.targetX = this.temptingPlayer.posX;
				this.targetY = this.temptingPlayer.posY;
				this.targetZ = this.temptingPlayer.posZ;
			}

			this.pitch = this.temptingPlayer.rotationPitch;
			this.yaw = this.temptingPlayer.rotationYaw;
		}

		return this.shouldExecute();
	}

	@Override
	public void startExecuting() {
		this.targetX = this.temptingPlayer.posX;
		this.targetY = this.temptingPlayer.posY;
		this.targetZ = this.temptingPlayer.posZ;
		this.isRunning = true;
	}

	@Override
	public void resetTask() {
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.delayTemptCounter = 100;
		this.isRunning = false;
	}

	@Override
	public void updateTask() {
		this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, this.temptedEntity.getHorizontalFaceSpeed() + 20,
				this.temptedEntity.getVerticalFaceSpeed());

		if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D)
			this.temptedEntity.getNavigator().clearPathEntity();
		else
			this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.speed);
	}

	public boolean isRunning() {
		return this.isRunning;
	}
}