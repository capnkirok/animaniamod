package com.animania.addons.farm.common.entity.horses.ai;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.entity.pullables.EntityCart;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityAIWanderHorses extends EntityAIWander
{
	protected final float probability;

	@Override
	public boolean shouldExecute()
	{

		boolean pullingFlag = false;
		List entities = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, entity.world, entity);
		if (!entities.isEmpty())
		{
			EntityCart checkCart = (EntityCart) entities.get(0);
			if (checkCart.puller == entity)
			{
				pullingFlag = true;
			}
		}

		if (pullingFlag)
		{
			return false;
		}

		boolean isSleeping = false;
		if (this.entity instanceof EntityAnimaniaHorse)
		{
			EntityAnimaniaHorse entityAV = (EntityAnimaniaHorse) this.entity;
			if (entityAV.getSleeping())
			{
				isSleeping = true;
			}
		}

		if (!this.entity.world.isDaytime() || isSleeping)
		{
			return false;
		}

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
		} else
		{
			this.x = vec3d.x;
			this.y = vec3d.y;
			this.z = vec3d.z;
			this.mustUpdate = false;
			return true;

		}
	}

	public EntityAIWanderHorses(EntityCreature p_i47301_1_, double p_i47301_2_)
	{
		this(p_i47301_1_, p_i47301_2_, 0.001F);
	}

	public EntityAIWanderHorses(EntityCreature p_i47302_1_, double p_i47302_2_, float p_i47302_4_)
	{
		super(p_i47302_1_, p_i47302_2_);
		this.probability = p_i47302_4_;
	}

	@Override
	@Nullable
	protected Vec3d getPosition()
	{
		if (this.entity.isInWater())
		{
			Vec3d vec3d = RandomPositionGenerator.getLandPos(this.entity, 15, 7);
			return vec3d == null ? super.getPosition() : vec3d;
		} else
		{
			return this.entity.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : super.getPosition();
		}
	}
}