package com.animania.common.entities.generic.ai;

import java.util.List;

import com.animania.api.interfaces.IChild;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class GenericAIFollowParents<T extends PathfinderMob & IChild & ISleeping, O extends PathfinderMob & IMateable> extends Goal
{
	T childAnimal;
	O parentAnimal;
	double moveSpeed;
	private int delayCounter;
	Class mother;

	public GenericAIFollowParents(T animal, double speed, Class mother)
	{
		this.childAnimal = animal;
		this.moveSpeed = speed;
		this.mother = mother;
	}

	@Override
	public boolean shouldExecute()
	{

		this.delayCounter++;
		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{

			if (!this.childAnimal.level.isDay() || this.childAnimal.getSleeping())
			{
				this.delayCounter = 0;
				return false;
			}

			if (this.childAnimal.getParentUniqueId() == null)
			{
			}
			else
			{
				List entities = AnimaniaHelper.getEntitiesInRange(this.mother, 40, this.childAnimal.level, this.childAnimal);

				for (int k = 0; k <= entities.size() - 1; k++)
				{

					O mother = (O) entities.get(k);

					if (mother != null && mother.getUUID().equals(this.childAnimal.getParentUniqueId()))
					{

						double xt = mother.getX();
						double yt = mother.getY();
						double zt = mother.getZ();
						int x1 = Mth.floor(this.childAnimal.getX());
						int y1 = Mth.floor(this.childAnimal.getY());
						int z1 = Mth.floor(this.childAnimal.getZ());
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (x2 <= 20 && y2 <= 8 && z2 <= 20 && x2 >= 3 && z2 >= 3)
						{
							this.parentAnimal = mother;
							return true;
						}
						else
						{
							return false;
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
		if (!this.parentAnimal.isAlive())
			return false;
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
			this.delayCounter = 40;
			this.childAnimal.getNavigation().tryMoveToLivingEntity(this.parentAnimal, this.moveSpeed);
		}
	}
}
