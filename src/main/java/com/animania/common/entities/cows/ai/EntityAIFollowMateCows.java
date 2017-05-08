package com.animania.common.entities.cows.ai;

import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowMateCows extends EntityAIBase
{
	/** The child that is following its parent. */
	EntityAnimal thisAnimal;
	EntityAnimal mateAnimal;
	double moveSpeed;
	private int delayCounter;

	public EntityAIFollowMateCows(EntityAnimal animal, double speed)
	{
		this.thisAnimal = animal;
		this.moveSpeed = speed;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */

	@Override
	public boolean shouldExecute()
	{
		delayCounter++;
		if (delayCounter > 60) {


			if (this.thisAnimal instanceof EntityBullHolstein) {
				EntityBullHolstein ec = (EntityBullHolstein)this.thisAnimal;
				if (ec.getMateUniqueId() == null) {
					return false;
				} else {

					int esize = this.thisAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = this.thisAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.thisAnimal.posX);
						int y1 = MathHelper.floor(this.thisAnimal.posY);
						int z1 = MathHelper.floor(this.thisAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityBullHolstein) this.thisAnimal).getMateUniqueId().toString()) && x2 <= 40 && y2 <=8 && z2 <=40 && x2 >= 5 && z2 >= 5) {
							mateAnimal = (EntityAnimal) entity;
							return true;
						}

					}


				}

			} else if (this.thisAnimal instanceof EntityBullAngus) {
				EntityBullAngus ec = (EntityBullAngus)this.thisAnimal;
				if (ec.getMateUniqueId() == null) {
					return false;
				} else {

					int esize = this.thisAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = this.thisAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.thisAnimal.posX);
						int y1 = MathHelper.floor(this.thisAnimal.posY);
						int z1 = MathHelper.floor(this.thisAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityBullAngus) this.thisAnimal).getMateUniqueId().toString()) && x2 <= 40 && y2 <=8 && z2 <=40 && x2 >= 5 && z2 >= 5) {
							mateAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}
			} else if (this.thisAnimal instanceof EntityBullLonghorn) {
				EntityBullLonghorn ec = (EntityBullLonghorn)this.thisAnimal;
				if (ec.getMateUniqueId() == null) {
					return false;
				} else {

					int esize = this.thisAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = this.thisAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.thisAnimal.posX);
						int y1 = MathHelper.floor(this.thisAnimal.posY);
						int z1 = MathHelper.floor(this.thisAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityBullLonghorn) this.thisAnimal).getMateUniqueId().toString()) && x2 <= 40 && y2 <=8 && z2 <=40 && x2 >= 5 && z2 >= 5) {
							mateAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}
			} else if (this.thisAnimal instanceof EntityBullFriesian) {
				EntityBullFriesian ec = (EntityBullFriesian)this.thisAnimal;
				if (ec.getMateUniqueId() == null) {
					return false;
				} else {

					int esize = this.thisAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = this.thisAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.thisAnimal.posX);
						int y1 = MathHelper.floor(this.thisAnimal.posY);
						int z1 = MathHelper.floor(this.thisAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityBullFriesian) this.thisAnimal).getMateUniqueId().toString()) && x2 <= 40 && y2 <=8 && z2 <=40 && x2 >= 5 && z2 >= 5) {
							mateAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}

			} else if (this.thisAnimal instanceof EntityBullHereford) {
				EntityBullHereford ec = (EntityBullHereford)this.thisAnimal;
				if (ec.getMateUniqueId() == null) {
					return false;
				} else {

					int esize = this.thisAnimal.world.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = this.thisAnimal.world.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor(this.thisAnimal.posX);
						int y1 = MathHelper.floor(this.thisAnimal.posY);
						int z1 = MathHelper.floor(this.thisAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityBullHereford) this.thisAnimal).getMateUniqueId().toString()) && x2 <= 40 && y2 <=8 && z2 <=40 && x2 >= 5 && z2 >= 5) {
							mateAnimal = (EntityAnimal) entity;
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
	@Override
	public boolean continueExecuting()
	{
		if (!this.mateAnimal.isEntityAlive())
		{
			return false;
		}
		else
		{
			double d0 = this.thisAnimal.getDistanceSqToEntity(this.mateAnimal);
			return d0 >= 9.0D && d0 <= 256.0D;
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{
		this.delayCounter = 0;
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		this.mateAnimal = null;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		if (--this.delayCounter <= 0)
		{
			this.delayCounter = 60;
			this.thisAnimal.getNavigator().tryMoveToEntityLiving(this.mateAnimal, this.moveSpeed);
		}
	}
}