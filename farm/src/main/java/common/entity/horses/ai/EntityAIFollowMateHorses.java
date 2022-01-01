package common.entity.horses.ai;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;
import common.entity.horses.EntityAnimaniaHorse;
import common.entity.horses.EntityMareBase;
import common.entity.horses.EntityStallionBase;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;

import java.util.List;

public class FollowMateHorsesGoal extends Goal
{
	EntityAnimaniaHorse thisAnimal;
	Animal mateAnimal;
	double moveSpeed;
	private int delayCounter;

	public FollowMateHorsesGoal(EntityAnimaniaHorse animal, double speed)
	{
		this.thisAnimal = animal;
		this.moveSpeed = speed;
	}

	public boolean shouldExecute()
	{

		this.delayCounter++;
		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings && (!this.thisAnimal.level.isDay() || this.thisAnimal.getSleeping()))
		{
			this.delayCounter = 0;
			return false;
		}

		if (this.thisAnimal instanceof EntityStallionBase ec)
		{
			if (ec.getMateUniqueId() == null)
			{
			}
			else
			{

				List entities = AnimaniaHelper.getEntitiesInRange(EntityMareBase.class, 40, this.thisAnimal.level, this.thisAnimal);

				for (int k = 0; k <= entities.size() - 1; k++)
				{

					EntityMareBase entity = (EntityMareBase) entities.get(k);

					if (entities.get(k) != null && entity.getUUID().equals(((EntityStallionBase) this.thisAnimal).getMateUniqueId()))
					{
						double xt = entity.getX();
						double yt = entity.getY();
						double zt = entity.getZ();
						int x1 = Mth.floor(this.thisAnimal.getX());
						int y1 = Mth.floor(this.thisAnimal.getY());
						int z1 = Mth.floor(this.thisAnimal.getZ());
						double x2 = Math.abs(xt - x1);
						double y2 = Math.abs(yt - y1);
						double z2 = Math.abs(zt - z1);

						if (x2 <= 20 && y2 <= 8 && z2 <= 20 && x2 >= 3 && z2 >= 3)
						{
							this.mateAnimal = entity;
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

	public boolean shouldContinueExecuting()
	{
		if (!this.mateAnimal.isAlive())
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
			this.thisAnimal.getNavigation().tryMoveToLivingEntity(this.mateAnimal, this.moveSpeed);
		}
	}
}