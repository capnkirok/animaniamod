package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.google.common.base.Predicate;

import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.passive.EntityTameable;

public class GenericAITargetNonTamed extends EntityAITargetNonTamed
{
	EntityTameable entity;
	
	public GenericAITargetNonTamed(EntityTameable entityIn, Class classTarget, boolean checkSight, Predicate targetSelector)
	{
		super(entityIn, classTarget, checkSight, targetSelector);
		this.entity = entityIn;
	}
	
	@Override
	public boolean shouldExecute()
	{
		if(entity instanceof ISleeping)
			if(((ISleeping) entity).getSleeping())
				return false;
		
		if(entity.isTamed())
			return false;
		
		if(entity.isSitting())
			return false;
		
		return super.shouldExecute();
	}

}
