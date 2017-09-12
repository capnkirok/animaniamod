package com.animania.common.entities.goats.ai;

import java.util.List;

import com.animania.common.entities.goats.EntityBuckBase;
import com.animania.common.entities.goats.EntityDoeBase;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowMateGoats extends EntityAIBase
{
	EntityAnimal thisAnimal;
	EntityAnimal mateAnimal;
	double moveSpeed;
	private int delayCounter;

	public EntityAIFollowMateGoats(EntityAnimal animal, double speed)
	{
		this.thisAnimal = animal;
		this.moveSpeed = speed;
	}

	public boolean shouldExecute() {
		this.delayCounter++;
		if (this.delayCounter > 60)
			if (this.thisAnimal instanceof EntityBuckBase) {
				EntityBuckBase ec = (EntityBuckBase) this.thisAnimal;
				if (ec.getMateUniqueId() == null)
					return false;
				else {

					List entities = AnimaniaHelper.getEntitiesInRange(EntityDoeBase.class, 40, this.thisAnimal.world, this.thisAnimal);

					for (int k = 0; k <= entities.size() - 1; k++) {

						EntityDoeBase entity = (EntityDoeBase)entities.get(k);
						
						if (entities.get(k) != null && entity.getPersistentID().equals(((EntityBuckBase) this.thisAnimal).getMateUniqueId())) {
							double xt = entity.posX;
							double yt = entity.posY;
							double zt = entity.posZ;
							int x1 = MathHelper.floor(this.thisAnimal.posX);
							int y1 = MathHelper.floor(this.thisAnimal.posY);
							int z1 = MathHelper.floor(this.thisAnimal.posZ);
							double x2 = Math.abs(xt - x1);
							double y2 = Math.abs(yt - y1);
							double z2 = Math.abs(zt - z1);

							if (x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
								this.mateAnimal = (EntityAnimal) entity;
								return true;
							} else {
								return false;
							}
						}
					}
				}
			}

		return false;

	}

	public boolean shouldContinueExecuting()
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

	public void startExecuting()
	{
		this.delayCounter = 0;
	}

	public void resetTask()
	{
		this.mateAnimal = null;
	}

	public void updateTask()
	{
		if (--this.delayCounter <= 0)
		{
			this.delayCounter = 60;
			this.thisAnimal.getNavigator().tryMoveToEntityLiving(this.mateAnimal, this.moveSpeed);
		}
	}
}