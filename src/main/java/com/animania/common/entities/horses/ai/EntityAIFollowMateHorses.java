package com.animania.common.entities.horses.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowMateHorses extends EntityAIBase
{
	/** The child that is following its parent. */
	EntityAnimal thisAnimal;
	EntityAnimal mateAnimal;
	double moveSpeed;
	private int delayCounter;

	public EntityAIFollowMateHorses(EntityAnimal animal, double speed)
	{
		this.thisAnimal = animal;
		this.moveSpeed = speed;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */

	public boolean shouldExecute()
	{

		delayCounter++;
		if (delayCounter > 60) {

			if (this.thisAnimal instanceof EntityStallionDraftHorse) {
				EntityStallionDraftHorse ec = (EntityStallionDraftHorse)this.thisAnimal;
				if (ec.getMateUniqueId() == null) {
					return false;
				} else {

					int esize = this.thisAnimal.worldObj.loadedEntityList.size();

					for (int k = 0; k <= esize - 1; k++) {

						Entity entity = (Entity) this.thisAnimal.worldObj.loadedEntityList.get(k);

						double xt = entity.posX;
						double yt = entity.posY;
						double zt = entity.posZ;
						int x1 = MathHelper.floor_double(this.thisAnimal.posX);
						int y1 = MathHelper.floor_double(this.thisAnimal.posY);
						int z1 = MathHelper.floor_double(this.thisAnimal.posZ);
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (entity !=null && entity.getPersistentID().toString().equalsIgnoreCase(((EntityStallionDraftHorse) this.thisAnimal).getMateUniqueId().toString()) && x2 <= 40 && y2 <=8 && z2 <=40 && x2 >= 5 && z2 >= 5) {
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
	public void startExecuting()
	{
		this.delayCounter = 0;
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.mateAnimal = null;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		if (--this.delayCounter <= 0)
		{
			this.delayCounter = 60;
			this.thisAnimal.getNavigator().tryMoveToEntityLiving(this.mateAnimal, this.moveSpeed);
		}
	}
}