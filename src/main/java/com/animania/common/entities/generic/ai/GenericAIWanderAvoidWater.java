package com.animania.common.entities.generic.ai;


import com.animania.api.interfaces.ISleeping;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;

public class GenericAIWanderAvoidWater extends EntityAIWanderAvoidWater
{
	public GenericAIWanderAvoidWater(CreatureEntity p_i47301_1_, double p_i47301_2_)
	{
		super(p_i47301_1_, p_i47301_2_, 0.001F);
	}

	public boolean shouldExecute()
	{
		if(((ISleeping) entity).getSleeping())
    		return false;

		return super.shouldExecute();
	}
}