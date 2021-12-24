package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;

public class GenericAISit<T extends TamableAnimal & ISleeping> extends SitWhenOrderedToGoal
{

	T entity;
	
	public GenericAISit(T entityIn)
	{
		super(entityIn);
		this.entity = entityIn;
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.entity.getSleeping())
			return false;
		
		return super.shouldExecute();
	}
	
}
