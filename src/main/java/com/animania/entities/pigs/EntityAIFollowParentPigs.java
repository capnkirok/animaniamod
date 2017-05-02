package com.animania.entities.pigs;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowParentPigs extends EntityAIBase
{
	/** The child that is following its parent. */
	EntityAnimal childAnimal;
	EntityAnimal parentAnimal;
	double moveSpeed;
	private int delayCounter;
	Random rand = new Random();

	public EntityAIFollowParentPigs(EntityAnimal animal, double speed)
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
		if (delayCounter > 40) {

			if (this.childAnimal instanceof EntityPigletDuroc && rand.nextInt(20) != 0) {
				EntityPigletDuroc ec = (EntityPigletDuroc)this.childAnimal;
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

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityPigletDuroc) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;
						}

					}


				}

			} else if (this.childAnimal instanceof EntityPigletHampshire && rand.nextInt(20) != 0) {
				EntityPigletHampshire ec = (EntityPigletHampshire)this.childAnimal;
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

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityPigletHampshire) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}
			} else if (this.childAnimal instanceof EntityPigletLargeBlack && rand.nextInt(20) != 0) {
				EntityPigletLargeBlack ec = (EntityPigletLargeBlack)this.childAnimal;
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

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityPigletLargeBlack) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}
			} else if (this.childAnimal instanceof EntityPigletLargeWhite && rand.nextInt(20) != 0) {
				EntityPigletLargeWhite ec = (EntityPigletLargeWhite)this.childAnimal;
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

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityPigletLargeWhite) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;

						}

					}


				}

			} else if (this.childAnimal instanceof EntityPigletOldSpot && rand.nextInt(20) != 0) {
				EntityPigletOldSpot ec = (EntityPigletOldSpot)this.childAnimal;
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

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityPigletOldSpot) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
							parentAnimal = (EntityAnimal) entity;
							return true;

						}
					}
				}

			} else if (this.childAnimal instanceof EntityPigletYorkshire && rand.nextInt(20) != 0) {
				EntityPigletYorkshire ec = (EntityPigletYorkshire)this.childAnimal;
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

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityPigletYorkshire) this.childAnimal).getParentUniqueId().toString()) && x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
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