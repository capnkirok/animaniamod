package com.animania.common.entities.generic.ai;


import javax.annotation.Nullable;

import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.interfaces.ISleeping;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.handler.BlockHandler;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class GenericAIWanderAvoidWater extends EntityAIWander
{
	protected final float probability;


	public boolean shouldExecute()
	{
		if(this.entity instanceof ISleeping && ((ISleeping) entity).getSleeping())
    		return false;

		if (!this.mustUpdate)
		{
			if (this.entity.getIdleTime() >= 100)
			{
				return false;
			}

			if (this.entity.getRNG().nextInt(this.executionChance) != 0)
			{
				return false;
			}
		}

		Vec3d vec3d = this.getPosition();
		
		if (vec3d == null)
		{
			return false;
		}
		else
		{
			this.x = vec3d.x;
			this.y = vec3d.y;
			this.z = vec3d.z;
			this.mustUpdate = false;
			return true;
		}
	}

	public GenericAIWanderAvoidWater(EntityCreature p_i47301_1_, double p_i47301_2_)
	{
		this(p_i47301_1_, p_i47301_2_, 0.001F);
	}

	public GenericAIWanderAvoidWater(EntityCreature p_i47302_1_, double p_i47302_2_, float p_i47302_4_)
	{
		super(p_i47302_1_, p_i47302_2_);
		this.probability = p_i47302_4_;
	}

	@Nullable
	protected Vec3d getPosition()
	{
		if (this.entity.isInWater())
		{
			Vec3d vec3d = RandomPositionGenerator.getLandPos(this.entity, 15, 7);
			return vec3d == null ? super.getPosition() : vec3d;
		}
		else
		{
			return this.entity.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : super.getPosition();
		}
	}
}