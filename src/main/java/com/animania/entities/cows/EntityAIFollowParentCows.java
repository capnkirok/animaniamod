package com.animania.entities.cows;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowParentCows extends EntityAIBase
{
	/** The child that is following its parent. */
	EntityAnimal childAnimal;
	EntityAnimal parentAnimal;
	double moveSpeed;
	private int delayCounter;
	Random rand = new Random();

	public EntityAIFollowParentCows(EntityAnimal animal, double speed)
	{
		this.childAnimal = animal;
		this.moveSpeed = speed;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */

	public boolean shouldExecute()
	{

		delayCounter++;
		if (delayCounter > 60) {

			if (this.childAnimal instanceof EntityCalfHolstein && rand.nextInt(20) != 0) {
				EntityCalfHolstein ec = (EntityCalfHolstein)this.childAnimal;
				if (ec.getParentUniqueId() == null) {
					return false;
				} else {

					int esize = this.childAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = (Entity) this.childAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.childAnimal.posX);
						int y1 = MathHelper.floor(this.childAnimal.posY);
						int z1 = MathHelper.floor(this.childAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityCalfHolstein) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;
						}

					}


				}

			} else if (this.childAnimal instanceof EntityCalfAngus && rand.nextInt(20) != 0) {
				EntityCalfAngus ec = (EntityCalfAngus)this.childAnimal;
				if (ec.getParentUniqueId() == null) {
					return false;
				} else {

					int esize = this.childAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = (Entity) this.childAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.childAnimal.posX);
						int y1 = MathHelper.floor(this.childAnimal.posY);
						int z1 = MathHelper.floor(this.childAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityCalfAngus) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}
			} else if (this.childAnimal instanceof EntityCalfFriesian && rand.nextInt(20) != 0) {
				EntityCalfFriesian ec = (EntityCalfFriesian)this.childAnimal;
				if (ec.getParentUniqueId() == null) {
					return false;
				} else {

					int esize = this.childAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = (Entity) this.childAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.childAnimal.posX);
						int y1 = MathHelper.floor(this.childAnimal.posY);
						int z1 = MathHelper.floor(this.childAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityCalfFriesian) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}
			} else if (this.childAnimal instanceof EntityCalfHereford && rand.nextInt(20) != 0) {
				EntityCalfHereford ec = (EntityCalfHereford)this.childAnimal;
				if (ec.getParentUniqueId() == null) {
					return false;
				} else {

					int esize = this.childAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = (Entity) this.childAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.childAnimal.posX);
						int y1 = MathHelper.floor(this.childAnimal.posY);
						int z1 = MathHelper.floor(this.childAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityCalfHereford) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}

			} else if (this.childAnimal instanceof EntityCalfLonghorn && rand.nextInt(20) != 0) {
				EntityCalfLonghorn ec = (EntityCalfLonghorn)this.childAnimal;
				if (ec.getParentUniqueId() == null) {
					return false;
				} else {

					int esize = this.childAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = (Entity) this.childAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.childAnimal.posX);
						int y1 = MathHelper.floor(this.childAnimal.posY);
						int z1 = MathHelper.floor(this.childAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityCalfLonghorn) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}
			}
		}

		return false;

	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		if (!this.parentAnimal.isEntityAlive())
		{
			return false;
		}
		else
		{
			double d0 = this.childAnimal.getDistanceSqToEntity(this.parentAnimal);
			return d0 >= 9.0D && d0 <= 256.0D;
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		this.delayCounter = 0;
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.parentAnimal = null;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		if (--this.delayCounter <= 0)
		{
			this.delayCounter = 40;
			this.childAnimal.getNavigator().tryMoveToEntityLiving(this.parentAnimal, this.moveSpeed);
		}
	}
}