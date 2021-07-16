package com.animania.addons.farm.common.entity.horses.ai;

import java.util.List;

import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.entity.horses.EntityMareBase;
import com.animania.addons.farm.common.entity.horses.EntityStallionBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowMateHorses extends EntityAIBase
{
	EntityAnimaniaHorse thisAnimal;
	AnimalEntity mateAnimal;
	double moveSpeed;
	private int delayCounter;

	public EntityAIFollowMateHorses(EntityAnimaniaHorse animal, double speed)
	{
		this.thisAnimal = animal;
		this.moveSpeed = speed;
	}

	public boolean shouldExecute() {
		
		this.delayCounter++;
		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) 
			
			if (!thisAnimal.world.isDaytime() || thisAnimal.getSleeping()) {
				this.delayCounter = 0;
				return false;
			}
		
			if (this.thisAnimal instanceof EntityStallionBase) {
				EntityStallionBase ec = (EntityStallionBase) this.thisAnimal;
				if (ec.getMateUniqueId() == null)
					return false;
				else {

					List entities = AnimaniaHelper.getEntitiesInRange(EntityMareBase.class, 40, this.thisAnimal.world, this.thisAnimal);

					for (int k = 0; k <= entities.size() - 1; k++) {

						EntityMareBase entity = (EntityMareBase)entities.get(k);
						
						if (entities.get(k) != null && entity.getPersistentID().equals(((EntityStallionBase) this.thisAnimal).getMateUniqueId())) {
							double xt = entity.getX();
							double yt = entity.getY();
							double zt = entity.getZ();
							int x1 = MathHelper.floor(this.thisAnimal.getX());
							int y1 = MathHelper.floor(this.thisAnimal.getY());
							int z1 = MathHelper.floor(this.thisAnimal.getZ());
							double x2 = Math.abs(xt - x1);
							double y2 = Math.abs(yt - y1);
							double z2 = Math.abs(zt - z1);

							if (x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
								this.mateAnimal = (AnimalEntity) entity;
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
			double d0 = this.thisAnimal.getDistanceSq(this.mateAnimal);
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
			this.thisAnimal.getNavigator().tryMoveToLivingEntity(this.mateAnimal, this.moveSpeed);
		}
	}
}