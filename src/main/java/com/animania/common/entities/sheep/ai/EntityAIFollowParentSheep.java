package com.animania.common.entities.sheep.ai;

import java.util.List;
import java.util.Random;

import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.entities.sheep.EntityEweBase;
import com.animania.common.entities.sheep.EntityLambBase;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowParentSheep extends EntityAIBase
{
	EntityAnimaniaSheep childAnimal;
	EntityAnimaniaSheep parentAnimal;
	double moveSpeed;
	private int delayCounter;
	Random rand = new Random();

	public EntityAIFollowParentSheep(EntityAnimal animal, double speed)
	{
		this.childAnimal = (EntityAnimaniaSheep) animal;
		this.moveSpeed = speed;
	}

	@Override
	public boolean shouldExecute()	{

		this.delayCounter++;
		if (this.delayCounter > 60) {
			if (this.childAnimal instanceof EntityLambBase) {
				EntityLambBase ec = (EntityLambBase) this.childAnimal;
				if (ec.getParentUniqueId() == null) {
					return false;
				} else {

					if (!this.childAnimal.world.isDaytime() || this.childAnimal.getSleeping()) {
						this.delayCounter = 0;
						return false;
					}
					
					List entities = AnimaniaHelper.getEntitiesInRange(EntityEweBase.class, 40, this.childAnimal.world, this.childAnimal);

					for (int k = 0; k <= entities.size() - 1; k++) {

						EntityEweBase entity = (EntityEweBase)entities.get(k);

						if (entity !=null && entity.getPersistentID().equals(((EntityLambBase) this.childAnimal).getParentUniqueId())) {
						
							double xt = entity.posX;
							double yt = entity.posY;
							double zt = entity.posZ;
							int x1 = MathHelper.floor(this.childAnimal.posX);
							int y1 = MathHelper.floor(this.childAnimal.posY);
							int z1 = MathHelper.floor(this.childAnimal.posZ);
							double x2 = Math.abs(xt - x1);
							double y2 = Math.abs(yt - y1);
							double z2 = Math.abs(zt - z1);
							
							if (x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
								this.parentAnimal = (EntityAnimaniaSheep) entity;
								return true;
							} else {
								return false;
							}
						}
					}
				}
			}
		}

		return false;

	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (!this.parentAnimal.isEntityAlive())
		{
			return false;
		}
		else
		{
			double d0 = this.childAnimal.getDistanceSq(this.parentAnimal);
			return d0 >= 9.0D && d0 <= 256.0D;
		}
	}

	@Override
	public void startExecuting()
	{
		this.delayCounter = 0;
	}

	@Override
	public void resetTask()
	{
		this.parentAnimal = null;
	}
	
	@Override
	public void updateTask()
	{
		if (--this.delayCounter <= 0)
		{
			this.delayCounter = 60;
			this.childAnimal.getNavigator().tryMoveToEntityLiving(this.parentAnimal, this.moveSpeed);
		}
	}
}